package com.example.onlinestore.contents.pages.profilepage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.onlinestore.R;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import static android.app.Activity.RESULT_OK;

import java.util.ArrayList;
import java.util.List;

public class EditProfileFragment extends Fragment {
    private static final int IMAGE_REQUEST_CODE = 1001;

    private MaterialToolbar topBar;
    private ShapeableImageView userEditImageView;
    private MaterialButton chooseProfileImageButton, applyChangesButton;
    NavController navController;
    UserEntity currentUser;
    private String profilePicturePath;

    SharedPreferences sharedPreferences;
    AppSharedViewModel sharedViewModel;

    List<ProductEntity> allProducts = new ArrayList<>();
    List<ProductEntity> toModifyProducts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("currentLoggedUser", Context.MODE_PRIVATE);
        sharedViewModel = new ViewModelProvider(getActivity()).get(AppSharedViewModel.class);

        sharedViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                allProducts = productEntities;
            }
        });

        topBar = view.findViewById(R.id.top_app_bar_edit_profile);
        userEditImageView = view.findViewById(R.id.edit_profile_profile_picture);
        chooseProfileImageButton = view.findViewById(R.id.edit_profile_choose_profile_picture_button);
        applyChangesButton = view.findViewById(R.id.edit_profile_button);
        TextInputEditText firstNameEditText = view.findViewById(R.id.edit_profile_first_name_text_view);
        TextInputEditText lastNameEditText = view.findViewById(R.id.edit_profile_last_name_text_view);
        TextInputEditText emailEditText = view.findViewById(R.id.edit_profile_email_text_view);
        TextInputEditText phoneEditText = view.findViewById(R.id.edit_profile_phone_text_view);
        TextInputEditText passwordEditText = view.findViewById(R.id.edit_profile_password_text_view);
        navController = Navigation.findNavController(view);

        String currentUserJson = sharedPreferences.getString("currentUser", "");
        currentUser = new Gson().fromJson(currentUserJson, UserEntity.class);

        String userProfilePicture = currentUser.getProfilePicture();
        firstNameEditText.setText(currentUser.getFirstName());
        lastNameEditText.setText(currentUser.getLastName());
        emailEditText.setText(currentUser.getEmail());
        phoneEditText.setText(currentUser.getPhone());
        profilePicturePath = currentUser.getProfilePicture();
        passwordEditText.setText(currentUser.getPassword());
        if (userProfilePicture != null) {
            userEditImageView.setImageURI(Uri.parse(userProfilePicture));
        }

        topBar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        chooseProfileImageButton.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(Intent.createChooser(intent, "select a picture"), IMAGE_REQUEST_CODE);
        });

        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNameEditText.getText().toString().equals("")) {
                    Toast.makeText(EditProfileFragment.this.requireContext(), "First Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lastNameEditText.getText().toString().equals("")) {
                    Toast.makeText(EditProfileFragment.this.requireContext(), "Last Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneEditText.getText().toString().equals("")) {
                    Toast.makeText(EditProfileFragment.this.requireContext(), "Phone Number Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordEditText.getText().toString().equals("")) {
                    Toast.makeText(EditProfileFragment.this.requireContext(), "PasswordCannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                currentUser.setFirstName(firstNameEditText.getText().toString());
                currentUser.setLastName(lastNameEditText.getText().toString());
                currentUser.setPhone(phoneEditText.getText().toString());
                currentUser.setPassword(passwordEditText.getText().toString());
                currentUser.setProfilePicture(profilePicturePath);

                // Update user in DB
                sharedViewModel.updateUser(currentUser);

                //Modify all Products of User
                toModifyProducts = allProducts;
                for (ProductEntity item : toModifyProducts){
                    if (item.getSeller().getEmail().equals(currentUser.getEmail())){
                        item.setSeller(currentUser);
                        sharedViewModel.updateProduct(item);
                    }
                }

                // Add user in Shared Preferences
                String userJson = new Gson().toJson(currentUser);
                sharedPreferences.edit().putString("currentUser", userJson).apply();

                // Pop back stack
                navController.popBackStack();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri profilePictureUri = data.getData();
            profilePicturePath = profilePictureUri.toString();
            userEditImageView.setImageURI(Uri.parse(profilePicturePath));
            ContentResolver cr = requireContext().getContentResolver();
            int perms = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
            cr.takePersistableUriPermission(profilePictureUri, perms);
        }
    }
}
