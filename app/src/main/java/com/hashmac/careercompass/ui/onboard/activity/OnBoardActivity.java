package com.hashmac.careercompass.ui.onboard.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.ActivityOnBoardBinding;
import com.hashmac.careercompass.ui.base.BaseActivity;

public class OnBoardActivity extends BaseActivity {

    private static final String TAG = "OnBoardActivity";
    private long splashScreenDuration = 2000L;
    private boolean splashScreenOn = true;

    private ActivityOnBoardBinding boardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the splash screen animation duration to 2 seconds.
        SplashScreen.installSplashScreen(this)
                        .setKeepOnScreenCondition(() -> splashScreenOn);

        // Set the content view to the activity layout.
        boardBinding = ActivityOnBoardBinding.inflate(getLayoutInflater());
        setContentView(boardBinding.getRoot());

        // Simulate a long loading process on application startup.
        new Handler().postDelayed(() -> {
            splashScreenOn = false;
        }, splashScreenDuration);
    }
}