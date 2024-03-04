package com.hashmac.careercompass.ui.auth.fragments.forgot_password;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentForgotPasswordBinding;
import com.hashmac.careercompass.utils.DialogWaiting;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class ForgotPasswordFragment extends Fragment {
    private FragmentForgotPasswordBinding binding;
    private ForgotPasswordViewModel viewModel;
    private DialogWaiting dialogWaiting;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        initClicks();
        initObserver();
    }

    private void initObserver() {
        viewModel.resultCall.observe(getViewLifecycleOwner(), resultCall -> {
            dialogWaiting.hideDialog();
            if (resultCall.isSuccess()) {
                Toasty.success(requireActivity(), resultCall.getMessage(), Toasty.LENGTH_SHORT).show();
            } else {
                Toasty.error(requireActivity(), resultCall.getMessage(), Toasty.LENGTH_SHORT).show();
            }
        });
    }

    private void initClicks() {
        binding.btnReset.setOnClickListener(v -> forgotPassword());
    }

    private void forgotPassword() {
        String email = binding.etEmail.getText().toString().trim();
        if (email.isEmpty()) {
            binding.etEmail.setError("Email is required");
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError("Enter a valid email");
            return;
        }
        Timber.d("forgotPassword: " + email);
        dialogWaiting = new DialogWaiting(requireActivity());
        dialogWaiting.showDialog("Sending email...");
        viewModel.forgotPassword(email);
    }
}