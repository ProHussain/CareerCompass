package com.hashmac.careercompass.ui.onboard.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentOnBoardThreeBinding;
import com.hashmac.careercompass.ui.auth.activity.AuthenticationActivity;

public class OnBoardThreeFragment extends Fragment {
    private FragmentOnBoardThreeBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardThreeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), AuthenticationActivity.class));
            requireActivity().overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
            requireActivity().finish();
        });
    }
}