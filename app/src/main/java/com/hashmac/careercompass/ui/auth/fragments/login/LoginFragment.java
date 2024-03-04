package com.hashmac.careercompass.ui.auth.fragments.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentLoginBinding;
import com.hashmac.careercompass.ui.main.activity.MainActivity;
import com.hashmac.careercompass.utils.DialogWaiting;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;
    private DialogWaiting dialogWaiting;
    private NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        navController = Navigation.findNavController(binding.getRoot());
        initView();
        initClicks();
        initObservers();
    }

    private void initView() {
        String txtLoginBottom = getString(R.string.txt_login_bottom)+" ";
        String gotoRegister = getString(R.string.btn_register_now);
        SpannableString spannableString = new SpannableString(txtLoginBottom + gotoRegister);
        ForegroundColorSpan colorSpan = new android.text.style.ForegroundColorSpan(getResources().getColor(R.color.colorPrimary, null));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        };
        spannableString.setSpan(clickableSpan, txtLoginBottom.length(), txtLoginBottom.length() + gotoRegister.length(), 0);
        spannableString.setSpan(colorSpan, txtLoginBottom.length(), txtLoginBottom.length() + gotoRegister.length(), 0);
        binding.tvNoAccount.setText(spannableString);
        binding.tvNoAccount.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initObservers() {
        viewModel.resultLiveData.observe(getViewLifecycleOwner(), result -> {
            if (result.isSuccess()) {
                dialogWaiting.hideDialog();
                Timber.d("initObservers: %s",result.getMessage());
            } else {
                dialogWaiting.hideDialog();
                Timber.e("initObservers: %s",result.getMessage());
                Toasty.error(requireContext(),result.getMessage()).show();
            }
        });

        viewModel.userLiveData.observe(getViewLifecycleOwner(), user -> {
            dialogWaiting.hideDialog();
            Timber.d("initObservers: %s",user.getAuth().getEmail());
            if (user.getEducation() == null || user.getExperience() == null) {
                Toasty.info(requireContext(),"Please complete your profile").show();
                navController.navigate(R.id.action_loginFragment_to_completeProfileFragment);
            } else {
                Toasty.success(requireContext(),"Welcome back").show();
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finishAffinity();
            }
        });
    }

    private void initClicks() {
        binding.tvNoAccount.setOnClickListener(v -> navController.navigate(R.id.action_loginFragment_to_registerFragment));
        binding.tvForgotPassword.setOnClickListener(v -> forgotPassword());
        binding.btnLogin.setOnClickListener(v -> loginUser());
        binding.ivTogglePassword.setOnClickListener(v -> {
            if (binding.etPassword.getTransformationMethod() == null) {
                binding.etPassword.setTransformationMethod(new android.text.method.PasswordTransformationMethod());
                binding.ivTogglePassword.setImageResource(R.drawable.ic_view_password);
            } else {
                binding.etPassword.setTransformationMethod(null);
                binding.ivTogglePassword.setImageResource(R.drawable.ic_hide_password);
            }
        });
    }

    private void forgotPassword() {
        navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment);
    }

    private void loginUser() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        if (email.isEmpty()) {
            binding.etEmail.setError("Email is required");
            binding.etEmail.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError("Enter a valid email");
            binding.etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            binding.etPassword.setError("Password is required");
            binding.etPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            binding.etPassword.setError("Password should be at least 6 characters");
            binding.etPassword.requestFocus();
            return;
        }
        dialogWaiting = new DialogWaiting(requireContext());
        dialogWaiting.showDialog("Logging in...");
        Timber.d("loginUser: %s %s",email,password);
        viewModel.loginUser(email,password);
    }
}