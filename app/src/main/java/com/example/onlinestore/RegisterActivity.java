package com.example.onlinestore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private static final int IMAGE_SELECT_CODE = 1;
    SharedPreferences sharedPreferences = getSharedPreferences("currentLoggedUser", MODE_PRIVATE);

    TextInputEditText firstNameText, lastNameText, emailText, phoneNumberText, passwordText;
    ShapeableImageView userProfilePicture;
    Uri profilePictureUri;
    String profilePicturePath = "empty";
    MaterialToolbar topAppBar;
    MaterialButton createNewProfileButton, choosePictureButton;
    AppSharedViewModel sharedViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedViewModel = new ViewModelProvider(this).get(AppSharedViewModel.class);
        List<UserEntity> allUsers = new ArrayList<>();
        sharedViewModel.getAllUsers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                allUsers.clear();
                allUsers.addAll(userEntities);
            }
        });
        elementInit();

        choosePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent, "select a picture"), IMAGE_SELECT_CODE);
            }
        });


        createNewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNameText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "First Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lastNameText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Last Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emailText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Email Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNumberText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Phone Number Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "PasswordCannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (UserEntity user : allUsers){
                    if (user.getEmail().equals(emailText.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                UserEntity newUser = new UserEntity(emailText.getText().toString(), passwordText.getText().toString(),
                        phoneNumberText.getText().toString(), firstNameText.getText().toString(),
                        lastNameText.getText().toString(), profilePicturePath, new ArrayList<ProductEntity>() {
                });
                sharedViewModel.insertUser(newUser);
                Toast.makeText(getApplicationContext(),"Welcome To Your Account "+firstNameText.getText().toString(), Toast.LENGTH_SHORT).show();
                //TODO: add shared preferences
                String newUserString = new Gson().toJson(newUser);
                sharedPreferences.edit().putString("currentUser", newUserString).apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_SELECT_CODE) {
            if(resultCode == RESULT_OK){
                profilePictureUri = data.getData();
                profilePicturePath = profilePictureUri.getPath();
                userProfilePicture.setImageURI(profilePictureUri);
            }
        }
    }

    private void elementInit() {
        firstNameText = findViewById(R.id.register_first_name_text_view);
        lastNameText = findViewById(R.id.register_last_name_text_view);
        emailText = findViewById(R.id.register_email_text_view);
        phoneNumberText = findViewById(R.id.register_phone_text_view);
        passwordText = findViewById(R.id.register_password_text_view);
        userProfilePicture = findViewById(R.id.register_profile_picture);
        choosePictureButton = findViewById(R.id.register_choose_profile_picture_button);
        createNewProfileButton = findViewById(R.id.register_button);
        topAppBar = findViewById(R.id.top_app_bar_register);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }
}
