package com.example.onlinestore.contents.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.onlinestore.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if we want delay in splash screen

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(com.example.onlinestore.contents.splash.SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 500);


        startActivity(new Intent(com.example.onlinestore.contents.splash.SplashActivity.this, MainActivity.class));
        finish();


    }
}