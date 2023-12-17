package com.hashmac.careercompass.ui.onboard.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentOnBoardTwoBinding;

public class OnBoardTwoFragment extends Fragment {
    private FragmentOnBoardTwoBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardTwoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(binding.getRoot());
        binding.btnNext.setOnClickListener(v -> {
            navController.navigate(R.id.action_onBoardTwoFragment_to_onBoardThreeFragment);
        });
        binding.tvSkip.setOnClickListener(v -> {
            // Todo: Implement skip onboarding logic here
        });
    }
}