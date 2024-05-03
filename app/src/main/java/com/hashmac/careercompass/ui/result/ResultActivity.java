package com.hashmac.careercompass.ui.result;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.CountTokensResponse;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.hashmac.careercompass.beans.result.CareerPath;
import com.hashmac.careercompass.beans.result.CareerPaths;
import com.hashmac.careercompass.databinding.ActivityResultBinding;
import com.hashmac.careercompass.databinding.ItemCareerBinding;
import com.hashmac.careercompass.ui.base.BaseActivity;
import com.hashmac.careercompass.utils.Constants;

import java.util.Arrays;
import java.util.concurrent.Executor;

import timber.log.Timber;

public class ResultActivity extends BaseActivity {

    private enum State {
        PREDICTING,
        RESULT
    }


    private ActivityResultBinding binding;

    private State state = State.PREDICTING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String from = getIntent().getStringExtra("from");
        String prompt = getIntent().getStringExtra("prompt");
        if (from.equals("home")) {
            fetchCompleteAnalysis(prompt);
        } else {
            if (prompt != null) {
                makeApiCall(prompt);
            }
        }

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (from.equals("home")) {
                    Timber.d("Back Pressed Home");
                    finish();
                } else {
                    if (state == State.RESULT) {
                        Timber.d("Back Pressed B");
                        state = State.PREDICTING;
                        binding.tvDetails.setVisibility(View.GONE);
                        binding.llResult.setVisibility(View.VISIBLE);
                    } else {
                        Timber.d("Back Pressed");
                        finish();
                    }
                }
            }
        });
    }

    private void makeApiCall(String prompt) {
        GenerativeModel generativeModel = new GenerativeModel("gemini-pro", "REDACTED_GEMINI_API_KEY");
        GenerativeModelFutures generativeModelFutures = GenerativeModelFutures.from(generativeModel);
        Content content = new Content.Builder()
                .addText(prompt.toString())
                .build();

        Executor executor = Runnable::run;
        ListenableFuture<GenerateContentResponse> response = generativeModelFutures.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                Timber.d("Result: %s", resultText);
                try {
                    binding.loadingAnimation.setVisibility(View.GONE);
                    binding.svResult.setVisibility(View.VISIBLE);
                    resultText = resultText.replace("```", "");
                    if (resultText.startsWith("json") || resultText.startsWith("JSON")) {
                        resultText = resultText.substring(4);
                    }
                    Gson gson = new Gson();
                    CareerPaths careerPaths = gson.fromJson(resultText, CareerPaths.class);
                    if (careerPaths != null) {
                        for (int i = 0; i < careerPaths.getCareerPaths().size(); i++) {
                            ItemCareerBinding itemCareerBinding = ItemCareerBinding.inflate(getLayoutInflater());
                            CareerPath careerPath = careerPaths.getCareerPaths().get(i);
                            itemCareerBinding.tvTitle.setText(careerPath.getCareer_path_name());
                            StringBuilder description = new StringBuilder();
                            description.append("Matching Skills: ");
                            for (String skill : careerPath.getMatching_skills()) {
                                description.append(skill).append(", ");
                            }
                            description.append("\nMatching Interests: ");
                            for (String interest : careerPath.getMatching_interests()) {
                                description.append(interest).append(", ");
                            }
                            description.append("\nMatching Strengths: ");
                            for (String strength : careerPath.getMatching_strengths()) {
                                description.append(strength).append(", ");
                            }
                            description.append("\nMatching Aspirations: ");
                            for (String aspiration : careerPath.getMatching_aspirations()) {
                                description.append(aspiration).append(", ");
                            }
                            description.append("\nMatching Work Environment: ");
                            description.append(careerPath.getMatching_work_environment());
                            itemCareerBinding.tvDescription.setText(description.toString());
                            itemCareerBinding.arcProgress.setProgress(careerPath.getFit_percentage());

                            itemCareerBinding.btnDetails.setOnClickListener(v -> fetchCompleteAnalysis(careerPath.getCareer_path_name()));

                            binding.llResult.addView(itemCareerBinding.getRoot());
                            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) itemCareerBinding.getRoot().getLayoutParams();
                            layoutParams.setMargins(0, 8, 0, 8);
                            itemCareerBinding.getRoot().setLayoutParams(layoutParams);
                        }
                    }
                } catch (Exception e) {
                    Timber.e(e);
                    showErrorDialog(prompt);
                }
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                Timber.e(t);
            }
        }, executor);
    }

    private void showErrorDialog(String prompt) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Error");
        builder.setMessage("Something went wrong. Please try again later.");
        builder.setPositiveButton("Try Again", (dialog, which) -> {
            dialog.dismiss();
            makeApiCall(prompt);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        builder.show();
    }

    private void fetchCompleteAnalysis(String title) {
        state = State.RESULT;
        binding.loadingAnimation.setVisibility(View.VISIBLE);
        binding.llResult.setVisibility(View.GONE);

        String prompt = Constants.getDetailsPrompt(title);
        GenerativeModel generativeModel = new GenerativeModel("gemini-pro", "REDACTED_GEMINI_API_KEY");
        GenerativeModelFutures generativeModelFutures = GenerativeModelFutures.from(generativeModel);
        Content content = new Content.Builder()
                .addText(prompt)
                .build();

        Executor executor = Runnable::run;
        ListenableFuture<GenerateContentResponse> response = generativeModelFutures.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                resultText = resultText.replace("*", "");
                Timber.d("Result: %s", resultText);
                binding.tvDetails.setText(resultText);
                binding.tvDetails.setVisibility(View.VISIBLE);
                binding.svResult.setVisibility(View.VISIBLE);
                binding.loadingAnimation.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                Timber.e(t);
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
                builder.setTitle("Error");
                builder.setMessage("Google AI model is not responding. Please try again later.");
                builder.setPositiveButton("Try Again", (dialog, which) -> {
                    fetchCompleteAnalysis(title);
                    dialog.dismiss();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                });
                builder.show();
            }
        }, executor);
    }
}