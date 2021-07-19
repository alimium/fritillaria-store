package com.example.onlinestore.contents.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.onlinestore.AdminActivity;
import com.example.onlinestore.LoginActivity;
import com.example.onlinestore.MainActivity;
import com.example.onlinestore.data.UserEntity;
import com.google.gson.Gson;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("currentLoggedUser", MODE_PRIVATE);

//        if we want delay in splash screen

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(com.example.onlinestore.contents.splash.SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 500);


        UserEntity currentUser = new Gson().fromJson(sharedPreferences.getString("currentUser",""),UserEntity.class);

        if (currentUser==null || sharedPreferences.getString("currentUser","").equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else if (currentUser.getEmail().equals("admin")){
            startActivity(new Intent(this, AdminActivity.class));
            finish();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}