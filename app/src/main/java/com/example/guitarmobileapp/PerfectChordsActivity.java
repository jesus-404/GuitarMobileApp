package com.example.guitarmobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

public class PerfectChordsActivity extends MainActivity {

    private long startTimeSpent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_chords);

        startTimer();
        startTimeSpent = System.currentTimeMillis();

        Button closeBtn = findViewById(R.id.perfect_close_btn);
        closeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PerfectChordsActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        long endTimeSpent = System.currentTimeMillis() - startTimeSpent;
        int totalTimeSpent = (int) (endTimeSpent / 1000);
        setTimePracticed(totalTimeSpent);
    }

    private void startTimer() {
        TextView oneMinTimer = findViewById(R.id.perfect_chord_timer);
        new CountDownTimer(61000, 1000) {
            public void onTick(long millisUntilFinished) {
                int timeRemaining = (int) (millisUntilFinished / 1000);
                int min = timeRemaining / 60;
                int sec = timeRemaining % 60;
                String timeFormatted = String.format("%d:%02d", min, sec);

                oneMinTimer.setText(timeFormatted);
            }

            public void onFinish() {
                oneMinTimer.setText("0:00");
            }
        }.start();
    }

    private void setTimePracticed(int curTime) {
        String sharedPrefFile = "com.example.android.sharedprefs";
        String key = "timePracticed";

        SharedPreferences preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        int prevTime = preferences.getInt(key, 0);
        int totalTime = prevTime + curTime;
        preferences.edit().putInt(key, totalTime).apply();
    }
}