package com.hashmac.careercompass.ui.auth.fragments.forgot_password;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.hashmac.careercompass.beans.ResultCall;

public class ForgotPasswordViewModel extends AndroidViewModel {
    private FirebaseAuth mAuth;
    public ForgotPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    MutableLiveData<ResultCall> resultCall = new MutableLiveData<>();

    public void forgotPassword(String email) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                resultCall.setValue(new ResultCall(true, "We have sent you instructions to reset your password!"));
            } else {
                resultCall.setValue(new ResultCall(false, task.getException().getMessage()));
            }
        });

    }
}
