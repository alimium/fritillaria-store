package com.example.onlinestore.contents.pages.feedpage;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinestore.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public class FeedFragment extends Fragment {

    private RecyclerView featuredRecyclerView, itemsRecyclerView;
    private RecyclerView.Adapter featuredRecyclerViewAdapter, itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager featuredRecyclerViewLayoutManager, itemsRecyclerViewLayoutManager;
    ArrayList<ItemCardModel> featuredCardModelArrayList;
    ArrayList<ItemCardModel> itemCardModelArrayList;
    MaterialCardView itemCard;
    ConstraintLayout expandableLayout;

    public FeedFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemCardModelArrayList = new ArrayList<>();
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));

        featuredCardModelArrayList = new ArrayList<>();
        featuredCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        featuredCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_profile_picture, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        featuredCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        featuredCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        featuredCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        featuredCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));
        featuredCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99",false));

        featuredRecyclerView = view.findViewById(R.id.feed_featured_list_recyclerview);
        featuredRecyclerViewLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        featuredRecyclerViewAdapter = new FeaturedRecyclerViewAdapter(featuredCardModelArrayList, getActivity().getSupportFragmentManager());
        featuredRecyclerView.setHasFixedSize(true);
        featuredRecyclerView.setLayoutManager(featuredRecyclerViewLayoutManager);
        featuredRecyclerView.setAdapter(featuredRecyclerViewAdapter);


        itemsRecyclerView = view.findViewById(R.id.feed_item_list_recyclerview);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);


    }
}