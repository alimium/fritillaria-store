package com.example.onlinestore.contents.pages.profilepage;


import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import com.example.onlinestore.MainActivity;
import com.example.onlinestore.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class ProfileFragment extends Fragment{

    private MaterialCardView bookmarksCard, itemsOnSaleCard, editProfileCard, logoutCard, aboutCard, aboutCardDetail;
    MaterialToolbar toolbar;

    public ProfileFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookmarksCard = view.findViewById(R.id.bookmarks_count_card);
        itemsOnSaleCard = view.findViewById(R.id.items_sold_card);
        editProfileCard = view.findViewById(R.id.edit_profile_card);
        logoutCard = view.findViewById(R.id.logout_card);
        aboutCard = view.findViewById(R.id.about_card);
        aboutCardDetail = view.findViewById(R.id.about_card_details);
        toolbar = view.findViewById(R.id.top_app_bar_profile);

        //TODO: set text to match data





        bookmarksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "bookmarks", Toast.LENGTH_SHORT).show();
            }
        });
        
        itemsOnSaleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "items sold", Toast.LENGTH_SHORT).show();
            }
        });
        
        editProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "edit profile", Toast.LENGTH_SHORT).show();
            }
        });

        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "logout", Toast.LENGTH_SHORT).show();
            }
        });
        
        aboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandedView();
            }
        });






        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void toggleExpandedView() {
        if (aboutCardDetail.getVisibility()==View.VISIBLE){
            TransitionManager.beginDelayedTransition(aboutCardDetail);
            aboutCardDetail.setVisibility(View.GONE);
        }else {
            TransitionManager.beginDelayedTransition(aboutCardDetail);
            aboutCardDetail.setVisibility(View.VISIBLE);
        }
    }
}