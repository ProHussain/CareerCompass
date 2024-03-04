package com.hashmac.careercompass.ui.auth.fragments.complete_profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hashmac.careercompass.beans.ResultCall;
import com.hashmac.careercompass.beans.user.Education;
import com.hashmac.careercompass.beans.user.Experience;
import com.hashmac.careercompass.beans.user.User;
import com.hashmac.careercompass.utils.SharedUtils;

public class CompleteProfileViewModel extends AndroidViewModel {
    private FirebaseFirestore mFirestore;
    private SharedUtils sharedUtils;
    MutableLiveData<ResultCall> resultCall = new MutableLiveData<>();
    public CompleteProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void completeProfile(Education education, Experience experience) {
        mFirestore = FirebaseFirestore.getInstance();
        sharedUtils = new SharedUtils(getApplication());
        User user = sharedUtils.getUser();
        user.setEducation(education);
        user.setExperience(experience);
        mFirestore.collection("users").document(user.getAuth().getId()).set(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                sharedUtils.saveUser(user);
                sharedUtils.saveLoginStatus(true);
                resultCall.setValue(new ResultCall(true, "Profile completed successfully"));
            } else {
                resultCall.setValue(new ResultCall(false, "Profile completion failed"));
            }
        });

    }
}
