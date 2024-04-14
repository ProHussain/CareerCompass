package com.hashmac.careercompass.ui.main.fragments.profile;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.beans.user.Auth;
import com.hashmac.careercompass.beans.user.Education;
import com.hashmac.careercompass.beans.user.Experience;
import com.hashmac.careercompass.beans.user.User;
import com.hashmac.careercompass.databinding.FragmentProfileBinding;
import com.hashmac.careercompass.ui.auth.activity.AuthenticationActivity;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        initObserver();
        initClickListeners();
    }

    private void initClickListeners() {
        binding.btnLogout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Logout");
            builder.setMessage("Are you sure you want to logout?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                viewModel.logout(requireContext());
                startActivity(new Intent(requireContext(), AuthenticationActivity.class));
                requireActivity().finishAffinity();
            });
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    private void initObserver() {
        viewModel.getUser(requireContext());
        viewModel.userMutableLiveData.observe(getViewLifecycleOwner(), user -> {
            initAuthInfo(user.getAuth());
            initEducationInfo(user.getEducation());
            initAboutInfo(user.getExperience());
        });
    }

    private void initAuthInfo(Auth auth) {
        binding.tvName.setText(auth.getName());
        binding.tvEmail.setText(auth.getEmail());
        binding.tvPhone.setText(auth.getPhone());
    }

    private void initEducationInfo(Education education) {
        binding.tvDegree.setText(education.getDegree());
        binding.tvField.setText(education.getSubject());
        binding.tvInstitute.setText(education.getInstitute());
        binding.tvCGPA.setText(education.getCgpa());
        binding.tvPassingYear.setText(education.getPassingYear());
        binding.tvFavoriteSubjects.setText(education.getFavSubjects());
    }

    private void initAboutInfo(Experience experience) {
        binding.tvBio.setText(experience.getDescription());
    }
}