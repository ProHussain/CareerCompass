package com.hashmac.careercompass.ui.main.fragments.career;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hashmac.careercompass.beans.chat.Chat;
import com.hashmac.careercompass.databinding.FragmentCareerBinding;
import com.hashmac.careercompass.ui.main.activity.MainActivity;
import com.hashmac.careercompass.ui.main.fragments.career.adapter.ChatAdapter;
import com.hashmac.careercompass.ui.result.ResultActivity;
import com.hashmac.careercompass.utils.Constants;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class CareerFragment extends Fragment {

    private FragmentCareerBinding binding;
    private CareerViewModel viewModel;
    private ChatAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCareerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CareerViewModel.class);
        initView();
        initClickListeners();
        initObserver();
    }

    private void initClickListeners() {
        binding.startAnimation.setOnClickListener(v -> {
            viewModel.startCareerChat();
            binding.rvChat.setVisibility(View.VISIBLE);
            binding.startAnimation.setVisibility(View.GONE);
            binding.careerAnimation.setVisibility(View.GONE);
            binding.etMessage.setVisibility(View.VISIBLE);
            binding.ivSend.setVisibility(View.VISIBLE);
        });

        binding.ivSend.setOnClickListener(v -> {
            sendResponse();
        });
    }

    private void initObserver() {
        viewModel.careerChatList.observe(getViewLifecycleOwner(), chats -> {
            adapter.setChatList(chats);
            binding.rvChat.smoothScrollToPosition(chats.size());
        });

        viewModel.isChatCompleted.observe(getViewLifecycleOwner(), isCompleted -> {
            if (Boolean.TRUE.equals(isCompleted)) {
                generatePrompt();
            }
        });
    }

    private void generatePrompt() {
        List<Chat> chatList = viewModel.careerChatList.getValue();
        // Remove the first 3 messages from the list
        assert chatList != null;
        chatList.remove(0);
        chatList.remove(0);
        chatList.remove(0);
        StringBuilder prompt = new StringBuilder(Constants.getStartPrompt(Objects.requireNonNull(viewModel.userMutableLiveData.getValue())));
        for (Chat chat : chatList) {
            prompt.append(chat.getMessage()).append("\n");
            if (chat.getType() == 0) {
                prompt.append("\n");
            }
        }
        prompt.append(Constants.finalWords);
        prompt.append("\n\n").append(Constants.exampleJSON);
        Timber.d("Prompt: %s", prompt.toString());
        // Send Prompt to Result
        Intent intent = new Intent(requireContext(), ResultActivity.class);
        intent.putExtra("from", "career");
        intent.putExtra("prompt", prompt.toString());
        startActivity(intent);
        viewModel.clearChat();
    }

    private void initView() {
        viewModel.getUser(requireContext());
        adapter = new ChatAdapter();
        binding.rvChat.setAdapter(adapter);
    }

    private void sendResponse() {
        String response = binding.etMessage.getText().toString();
        if (!response.isEmpty()) {
            viewModel.sendAnswerToServer(response);
            binding.etMessage.setText("");
        } else {
            Toasty.error(requireContext(), "Please enter a response").show();
        }
    }
}