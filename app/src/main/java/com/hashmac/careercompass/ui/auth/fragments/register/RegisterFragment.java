package com.hashmac.careercompass.ui.auth.fragments.register;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentRegisterBinding;
import com.hashmac.careercompass.utils.DialogWaiting;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;
    private NavController navController;
    private DialogWaiting dialogWaiting;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        navController = Navigation.findNavController(binding.getRoot());
        initClicks();
        initObserver();
    }

    private void initObserver() {
        viewModel.resultLiveData.observe(getViewLifecycleOwner(), result -> {
            if (result.isSuccess()) {
                dialogWaiting.hideDialog();
                Timber.d("initObserver: %s",result.getMessage());
                Toasty.success(requireContext(),result.getMessage()).show();
                navController.navigate(R.id.action_registerFragment_to_completeProfileFragment);
            } else {
                dialogWaiting.hideDialog();
                Toasty.error(requireContext(),result.getMessage()).show();
                Timber.e("initObserver: %s",result.getMessage());
            }
        });
    }

    private void initClicks() {
        binding.btnRegister.setOnClickListener(v -> {
            registerUser();
        });
    }

    private void registerUser() {
        String name = binding.etName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String phone = binding.etPhone.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (name.isEmpty()) {
            binding.etName.setError("Name is required");
            return;
        }
        if (email.isEmpty()) {
            binding.etEmail.setError("Email is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError("Please provide valid email");
            return;
        }
        if (phone.isEmpty()) {
            binding.etPhone.setError("Phone is required");
            return;
        }
        if (!Patterns.PHONE.matcher(phone).matches()) {
            binding.etPhone.setError("Please provide valid phone number");
            return;
        }
        if (password.isEmpty()) {
            binding.etPassword.setError("Password is required");
            return;
        }
        if (password.length() < 6) {
            binding.etPassword.setError("Password must be at least 6 characters");
            return;
        }
        Timber.d("registerUser: %s %s %s %s",name,email,phone,password);
        dialogWaiting = new DialogWaiting(requireContext());
        dialogWaiting.showDialog("Registering user...");
        viewModel.registerUser(name,email,phone,password);
    }
}