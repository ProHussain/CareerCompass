package com.hashmac.careercompass.ui.onboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.ActivityOnBoardBinding;
import com.hashmac.careercompass.ui.base.BaseActivity;
import com.hashmac.careercompass.ui.main.activity.MainActivity;
import com.hashmac.careercompass.utils.SharedUtils;

public class OnBoardActivity extends BaseActivity {
    private final long splashScreenDuration = 2000L;
    private boolean splashScreenOn = true;

    private ActivityOnBoardBinding boardBinding;
    private SharedUtils sharedUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this)
                        .setKeepOnScreenCondition(() -> splashScreenOn);
        boardBinding = ActivityOnBoardBinding.inflate(getLayoutInflater());
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            splashScreenOn = false;
            checkLogin();
        }, splashScreenDuration);
    }

    private void checkLogin() {
        sharedUtils = new SharedUtils(this);
        if (sharedUtils.getLoginStatus()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            setContentView(boardBinding.getRoot());
        }
    }
}