package com.example.onlinestore.contents.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.onlinestore.LoginActivity;
import com.example.onlinestore.MainActivity;

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


        String currentUser = sharedPreferences.getString("currentUser","");

        if (currentUser!=null && currentUser.equals("")){
            startActivity(new Intent(com.example.onlinestore.contents.splash.SplashActivity.this, LoginActivity.class));
            finish();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("currentUser", currentUser);
            startActivity(intent);
            finish();
        }


    }
}