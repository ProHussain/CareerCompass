package com.hashmac.careercompass.ui.main.fragments.profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hashmac.careercompass.beans.user.User;
import com.hashmac.careercompass.utils.SharedUtils;

public class ProfileViewModel extends ViewModel {
    MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    public void getUser(Context context) {
        SharedUtils sharedUtils = new SharedUtils(context);
        User user = sharedUtils.getUser();
        userMutableLiveData.setValue(user);
    }

    public void logout(Context context) {
        SharedUtils sharedUtils = new SharedUtils(context);
        sharedUtils.logoutUser();
    }
}
