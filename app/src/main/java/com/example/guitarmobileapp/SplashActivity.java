package com.example.guitarmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;

public class SplashActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Splash screen wait, then fade to home screen
        new Handler().postDelayed(() -> {
            Intent intent;
            if (isFirstTime()) {
                intent = new Intent(SplashActivity.this, AccountActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, HomeActivity.class);
            }
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }, 3000);
    }

    private boolean isFirstTime() {
        String sharedPrefFile = "com.example.android.sharedprefs";
        String key = "isFirstTime";

        SharedPreferences preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean(key, true);

        if (isFirstTime) {
            preferences.edit().putBoolean(key, false).apply();
        }

        return isFirstTime;
    }
}