package com.example.onlinestore.contents.pages.feedpage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinestore.R;

import java.util.ArrayList;


public class FeedFragment extends Fragment {

    private RecyclerView featuredRecyclerView;
    private RecyclerView.Adapter featuredRecyclerViewAdapter;
    private RecyclerView.LayoutManager featuredRecyclerViewLayoutManager;
    ArrayList<FeaturedCardModel> featuredCardModelArrayList;

    public FeedFragment(){}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        featuredCardModelArrayList = new ArrayList<>();
        featuredCardModelArrayList.add(new FeaturedCardModel(R.drawable.default_profile_picture, "Item Title", "item description item description item description.","34.99", "23,89"));
        featuredCardModelArrayList.add(new FeaturedCardModel(R.drawable.default_profile_picture, "Item Title", "item description item description item description.","34.99", "23,89"));
        featuredCardModelArrayList.add(new FeaturedCardModel(R.drawable.default_profile_picture, "Item Title", "item description item description item description.","34.99", "23,89"));
        featuredCardModelArrayList.add(new FeaturedCardModel(R.drawable.default_profile_picture, "Item Title", "item description item description item description.","34.99", "23,89"));
        featuredCardModelArrayList.add(new FeaturedCardModel(R.drawable.default_profile_picture, "Item Title", "item description item description item description.","34.99", "23,89"));
        featuredCardModelArrayList.add(new FeaturedCardModel(R.drawable.default_profile_picture, "Item Title", "item description item description item description.","34.99", "23,89"));

        featuredRecyclerView = view.findViewById(R.id.feed_featured_list_recyclerview);
        featuredRecyclerViewLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        featuredRecyclerViewAdapter = new FeaturedRecyclerViewAdapter(featuredCardModelArrayList);
        featuredRecyclerView.setHasFixedSize(true);
        featuredRecyclerView.setLayoutManager(featuredRecyclerViewLayoutManager);
        featuredRecyclerView.setAdapter(featuredRecyclerViewAdapter);


    }
}