package com.example.onlinestore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinestore.utility.toast.CustomToast;
import com.example.onlinestore.utility.toast.CustomToastMode;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {


    LayoutInflater customToastInflater;
    View customToastLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        customToastInflater = getLayoutInflater();
        customToastLayout = customToastInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
        CustomToast toast = new CustomToast(getApplicationContext(), customToastLayout);


        MaterialButton registerButton = findViewById(R.id.login_to_register_button);
        MaterialButton loginButton = findViewById(R.id.login_login_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
