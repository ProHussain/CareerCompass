package com.hashmac.careercompass.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hashmac.careercompass.beans.user.User;

public class SharedUtils {
    public static final String PREFS_NAME = "CareerCompassPrefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUser(User user) {
        String value = new Gson().toJson(user);
        editor.putString("user", value);
        editor.apply();
    }

    public User getUser() {
        String user = sharedPreferences.getString("user", "");
        if (user.isEmpty()) {
            return null;
        } else {
            return new Gson().fromJson(user, User.class);
        }
    }

    public void saveLoginStatus(boolean status) {
        editor.putBoolean("loginStatus", status);
        editor.apply();
    }

    public boolean getLoginStatus() {
        return sharedPreferences.getBoolean("loginStatus", false);
    }

    public void logoutUser() {
        saveLoginStatus(false);
        editor.remove("user");
        editor.apply();
    }
}
