package com.example.onlinestore.contents.pages.profilepage;


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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.onlinestore.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class ProfileFragment extends Fragment{

    ConstraintLayout main_layout;
    private MaterialCardView bookmarksCard, itemsOnSaleCard, editProfileCard, logoutCard, aboutCard, aboutCardDetail;
    MaterialToolbar toolbar;
    NavController navController;

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
        main_layout = view.findViewById(R.id.profile_main_layout);
        toolbar = view.findViewById(R.id.top_app_bar_profile);
        navController = Navigation.findNavController(view);

        //TODO: set text to match data





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
                Toast.makeText(getContext(), "logout", Toast.LENGTH_SHORT).show();
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