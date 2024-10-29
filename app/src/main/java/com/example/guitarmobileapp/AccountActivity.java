package com.example.guitarmobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AccountActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setAccountTitle();
        
        Button close_btn = findViewById(R.id.account_close_btn);
        close_btn.setOnClickListener(v -> {
            TextInputEditText nameInput = findViewById(R.id.nameInput);
            String inputText = Objects.requireNonNull(nameInput.getText()).toString().trim();

            TextInputLayout nameLayout = findViewById(R.id.nameLayout);
            if (inputText.isEmpty()) {
                nameLayout.setError("This field is required");
            } else {
                nameLayout.setError(null);
                setProfileName(inputText);
                newIntent();
            }
        });

        if (getWindow() != null) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.color_accent_p));
            window.getDecorView().setSystemUiVisibility(0);
        }
    }
    
    private void setProfileName(String name) {
        String sharedPrefFile = "com.example.android.sharedprefs";
        String key = "profileName";

        SharedPreferences preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        preferences.edit().putString(key, name).apply();
    }
    
    private void newIntent() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    private void setAccountTitle() {
        TextView title = findViewById(R.id.account_text_title);
        Intent intent = getIntent();
        String text = intent.getStringExtra("key");

        if (text == null || text.isEmpty()) {
            title.setText("Create an Account");
        } else {
            title.setText(text);
        }
    }
}