package com.example.onlinestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.onlinestore.contents.pages.profilepage.ProfileFragment;
import com.example.onlinestore.contents.splash.SplashActivity;
import com.example.onlinestore.utility.toast.CustomToast;
import com.example.onlinestore.utility.toast.CustomToastMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;
    NavController navController;
    LayoutInflater customToastInflater;
    View customToastLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //bottom navigation bar functionality
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_controller);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //custom toast feature functionality
        customToastInflater = getLayoutInflater();
        customToastLayout = customToastInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
        CustomToast toast = new CustomToast(this, customToastLayout);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId()==R.id.post_page || destination.getId()==R.id.profile_page){
                    bottomNavigationView.clearAnimation();
                    bottomNavigationView.animate().translationY(bottomNavigationView.getHeight()).setDuration(200);
                }else {
                    bottomNavigationView.clearAnimation();
                    bottomNavigationView.animate().translationY(0).setDuration(200);
                }
            }
        });

    }

}