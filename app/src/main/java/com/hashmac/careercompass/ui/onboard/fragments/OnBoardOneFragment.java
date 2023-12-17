package com.hashmac.careercompass.ui.onboard.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentOnBoardOneBinding;

public class OnBoardOneFragment extends Fragment {
    private FragmentOnBoardOneBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardOneBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(binding.getRoot());

        binding.btnNext.setOnClickListener(v -> {
            navController.navigate(R.id.action_onBoardOneFragment_to_onBoardTwoFragment);
        });

        binding.tvSkip.setOnClickListener(v -> {
           // Todo: Implement skip onboarding logic here
        });
    }
}