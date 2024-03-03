package com.hashmac.careercompass;

import android.app.Application;

import timber.log.Timber;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
