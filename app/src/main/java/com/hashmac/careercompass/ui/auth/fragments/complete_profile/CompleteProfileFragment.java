package com.hashmac.careercompass.ui.auth.fragments.complete_profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.beans.user.Education;
import com.hashmac.careercompass.beans.user.Experience;
import com.hashmac.careercompass.databinding.FragmentCompleteProfileBinding;
import com.hashmac.careercompass.ui.main.activity.MainActivity;
import com.hashmac.careercompass.utils.DialogWaiting;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class CompleteProfileFragment extends Fragment {

    private FragmentCompleteProfileBinding binding;
    private DialogWaiting dialogWaiting;
    private CompleteProfileViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompleteProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CompleteProfileViewModel.class);
        initAutoComplete();
        initClickListeners();
        initObservers();
    }

    private void initObservers() {
        viewModel.resultCall.observe(getViewLifecycleOwner(), resultCall -> {
            dialogWaiting.hideDialog();
            if (resultCall.isSuccess()) {
                Toasty.success(requireContext(), resultCall.getMessage()).show();
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finishAffinity();
            } else {
                Toasty.error(requireContext(), resultCall.getMessage()).show();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initClickListeners() {
        binding.etSelectDegree.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                binding.etSelectDegree.showDropDown();
                return true;
            }
            return false;
        });

        binding.btnCompleteProfile.setOnClickListener(v -> completeProfile());
    }

    private void completeProfile() {
        String degree = binding.etSelectDegree.getText().toString().trim();
        String subject = binding.etSubject.getText().toString().trim();
        String passingYear = binding.etPassingYear.getText().toString().trim();
        String cgpa = binding.etCGPA.getText().toString().trim();
        String institute = binding.etInstitute.getText().toString().trim();
        String favoriteSubject = binding.etSelectFavSubjects.getText().toString().trim();
        String bio = binding.etAbout.getText().toString().trim();

        if (degree.isEmpty()) {
            binding.etSelectDegree.setError("Please select degree");
            return;
        }
        if (subject.isEmpty()) {
            binding.etSubject.setError("Please enter subject");
            return;
        }
        if (passingYear.isEmpty()) {
            binding.etPassingYear.setError("Please enter passing year");
            return;
        }
        if (cgpa.isEmpty()) {
            binding.etCGPA.setError("Please enter CGPA");
            return;
        }
        if (institute.isEmpty()) {
            binding.etInstitute.setError("Please enter institute");
            return;
        }
        if (favoriteSubject.isEmpty()) {
            binding.etSelectFavSubjects.setError("Please enter favorite subjects");
            return;
        }
        if (bio.isEmpty()) {
            binding.etAbout.setError("Please enter bio");
            return;
        }
        Timber.d("completeProfile: %s, %s, %s, %s, %s, %s, %s", degree, subject, passingYear, cgpa, institute, favoriteSubject, bio);
        dialogWaiting = new DialogWaiting(requireContext());
        dialogWaiting.showDialog("Completing profile");
        Education education = new Education(degree, subject, passingYear, cgpa, favoriteSubject, institute);
        Experience experience = new Experience(bio);
        viewModel.completeProfile(education,experience);
    }

    private void initAutoComplete() {
        String[] degrees = getResources().getStringArray(R.array.degree);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, degrees);
        binding.etSelectDegree.setAdapter(adapter);

        String[] subjects = getResources().getStringArray(R.array.fields_of_study);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, subjects);
        binding.etSubject.setAdapter(adapter1);
    }
}