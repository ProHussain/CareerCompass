package com.hashmac.careercompass.ui.auth.fragments.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hashmac.careercompass.beans.user.Auth;
import com.hashmac.careercompass.beans.user.User;
import com.hashmac.careercompass.beans.ResultCall;
import com.hashmac.careercompass.utils.SharedUtils;

public class RegisterViewModel extends AndroidViewModel {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private SharedUtils sharedUtils;
    MutableLiveData<ResultCall> resultLiveData = new MutableLiveData<>();
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
        sharedUtils = new SharedUtils(application);
    }

    public void registerUser(
            String name,
            String email,
            String phone,
            String password) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        updateProfile(name, email, phone);
                    } else {
                        resultLiveData.setValue(new ResultCall(false, task.getException().getMessage()));
                    }
                });

    }

    private void updateProfile(String name, String email, String phone) {
        mFirestore = FirebaseFirestore.getInstance();
        Auth auth = new Auth(mAuth.getCurrentUser().getUid(),name,email,phone);
        User user = new User(auth,null,null);
        mFirestore.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .set(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        sharedUtils.saveUser(user);
                        resultLiveData.setValue(new ResultCall(true, "User registered successfully"));
                    } else {
                        resultLiveData.setValue(new ResultCall(false, task.getException().getMessage()));
                    }
                });
    }

}
