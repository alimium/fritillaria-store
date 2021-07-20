package com.example.onlinestore.contents.pages.profilepage;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.onlinestore.LoginActivity;
import com.example.onlinestore.R;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment{


    ConstraintLayout main_layout;
    private MaterialCardView bookmarksCard, itemsOnSaleCard, editProfileCard, logoutCard, aboutCard, aboutCardDetail;
    MaterialTextView userNameText, userEmailText, userPhoneText, itemCountText, bookmarksCountText;
    ShapeableImageView userProfileImage;
    MaterialToolbar toolbar;
    NavController navController;
    UserEntity currentUser = null;
    SharedPreferences sharedPreferences;
    AppSharedViewModel sharedViewModel;




    public ProfileFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("currentLoggedUser", Context.MODE_PRIVATE);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(AppSharedViewModel.class);

        currentUser = new Gson().fromJson(sharedPreferences.getString("currentUser",""), UserEntity.class);

        bookmarksCard = view.findViewById(R.id.bookmarks_count_card);
        itemsOnSaleCard = view.findViewById(R.id.items_sold_card);
        editProfileCard = view.findViewById(R.id.edit_profile_card);
        logoutCard = view.findViewById(R.id.logout_card);
        aboutCard = view.findViewById(R.id.about_card);
        aboutCardDetail = view.findViewById(R.id.about_card_details);
        main_layout = view.findViewById(R.id.profile_main_layout);
        toolbar = view.findViewById(R.id.top_app_bar_profile);
        userNameText = view.findViewById(R.id.name_text_view);
        userEmailText = view.findViewById(R.id.email_text_view);
        userPhoneText = view.findViewById(R.id.optional_phone_text_view);
        userProfileImage = view.findViewById(R.id.profile_picture);
        itemCountText = view.findViewById(R.id.items_sold_text_view);
        bookmarksCountText = view.findViewById(R.id.bookmarks_count_text_view);
        navController = Navigation.findNavController(view);

        //TODO: set text to match data

        String userNameAndLastName = currentUser.getFirstName().toString()+" "+currentUser.getLastName().toString();
        String userProfilePicture = currentUser.getProfilePicture();
        userNameText.setText(userNameAndLastName);
        userEmailText.setText(currentUser.getEmail());
        userPhoneText.setText(currentUser.getPhone());
        if (userProfilePicture!=null){
            Glide.with(getContext()).load(Uri.parse(userProfilePicture)).into(userProfileImage);
        }
        itemCountText.setText(String.valueOf(currentUser.getProducts()));
        if (currentUser.getBookmarks() == null) {
            bookmarksCountText.setText("0");
        }else {
            bookmarksCountText.setText(String.valueOf(currentUser.getBookmarks().size()));
        }


        bookmarksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_page_to_bookmarks_page);
            }
        });
        
        itemsOnSaleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_page_to_library_page);
            }
        });
        
        editProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_page_to_edit_profile_page);
            }
        });

        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("currentUser", "").apply();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
        
        aboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandedView();
            }
        });



    }

    private void toggleExpandedView() {
        if (aboutCardDetail.getVisibility()==View.VISIBLE){
            TransitionManager.beginDelayedTransition(main_layout, new AutoTransition());
            aboutCardDetail.setVisibility(View.GONE);
        }else {
            TransitionManager.beginDelayedTransition(main_layout, new AutoTransition());
            aboutCardDetail.setVisibility(View.VISIBLE);
        }
    }
}