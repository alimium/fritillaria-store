package com.example.onlinestore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    ImageView loginLogo;
    MaterialButton registerButton;
    MaterialButton loginButton;
    TextInputEditText usernameTextField, passwordTextField;
    SharedPreferences currentLoggedInUser = getSharedPreferences("currentLoggedUser", MODE_PRIVATE);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findElements();
        AppSharedViewModel sharedViewModel = new ViewModelProvider(this).get(AppSharedViewModel.class);
        List<UserEntity> users = new ArrayList<>();

        sharedViewModel.getAllUsers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                users.clear();
                users.addAll(userEntities);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usernameTextField.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Username Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordTextField.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (UserEntity usr : users) {
                    if (usr.getEmail().equals(usernameTextField.getText().toString())) {
                        if (usr.getPassword().equals(passwordTextField.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Happy Shopping!", Toast.LENGTH_SHORT).show();
                            String loggedInUser = new Gson().toJson(usr);
                            currentLoggedInUser.edit().putString("currentUser", loggedInUser).apply();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            return;
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                Toast.makeText(getApplicationContext(), "User Not Found", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    private void findElements() {
        registerButton = findViewById(R.id.login_to_register_button);
        loginButton = findViewById(R.id.login_login_button);
        loginLogo = findViewById(R.id.login_logo);
        usernameTextField = findViewById(R.id.username_login);
        passwordTextField = findViewById(R.id.password_login);


        if (isDarkMode()) {
            loginLogo.setImageResource(R.drawable.ic_logo_login_dark);
        }
    }

    private boolean isDarkMode() {
        return (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }
}
