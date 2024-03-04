package com.hashmac.careercompass.ui.auth.fragments.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hashmac.careercompass.beans.ResultCall;
import com.hashmac.careercompass.beans.user.User;
import com.hashmac.careercompass.utils.SharedUtils;

public class LoginViewModel extends AndroidViewModel {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private SharedUtils sharedUtils;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
    }

    MutableLiveData<ResultCall> resultLiveData = new MutableLiveData<>();
    MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        fetchDataFromFireStore();
                    } else {
                        resultLiveData.setValue(new ResultCall(false, task.getException().getMessage()));
                    }
                });
    }

    private void fetchDataFromFireStore() {
        mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user = task.getResult().toObject(User.class);
                        sharedUtils = new SharedUtils(getApplication());
                        sharedUtils.saveUser(user);
                        if (user.getEducation() != null && user.getExperience() != null) {
                            sharedUtils.saveLoginStatus(true);
                        }
                        userLiveData.setValue(user);
                        resultLiveData.setValue(new ResultCall(true, "User logged in successfully"));
                    } else {
                        resultLiveData.setValue(new ResultCall(false, task.getException().getMessage()));
                    }
                });
    }
}
