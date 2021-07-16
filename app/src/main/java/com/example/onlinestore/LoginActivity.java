package com.example.onlinestore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinestore.utility.toast.CustomToast;
import com.example.onlinestore.utility.toast.CustomToastMode;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    ImageView loginLogo;
    MaterialButton registerButton;
    MaterialButton loginButton;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        registerButton = findViewById(R.id.login_to_register_button);
        loginButton = findViewById(R.id.login_login_button);
        loginLogo = findViewById(R.id.login_logo);

        if (isDarkMode()){
            loginLogo.setImageResource(R.drawable.ic_logo_login_dark);
        }



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

    private boolean isDarkMode(){
        return (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)==Configuration.UI_MODE_NIGHT_YES;
    }
}
