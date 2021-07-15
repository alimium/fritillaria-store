package com.example.onlinestore.contents.pages.findpage;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.FeaturedRecyclerViewAdapter;
import com.example.onlinestore.contents.pages.feedpage.ItemCardModel;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;

import java.util.ArrayList;

public class FindResultActivity extends AppCompatActivity {


    private RecyclerView featuredRecyclerView, itemsRecyclerView;
    private RecyclerView.Adapter featuredRecyclerViewAdapter, itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager featuredRecyclerViewLayoutManager, itemsRecyclerViewLayoutManager;
    ArrayList<ItemCardModel> featuredCardModelArrayList;
    ArrayList<ItemCardModel> itemCardModelArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        featuredCardModelArrayList = (ArrayList<ItemCardModel>) getIntent().getSerializableExtra("FEATURED_RESULT_LIST");
        itemCardModelArrayList = (ArrayList<ItemCardModel>) getIntent().getSerializableExtra("MAIN_RESULT_LIST");


        featuredRecyclerView = findViewById(R.id.find_featured_list_recyclerview);
        featuredRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        featuredRecyclerViewAdapter = new FeaturedRecyclerViewAdapter(featuredCardModelArrayList, getSupportFragmentManager());
        featuredRecyclerView.setHasFixedSize(true);
        featuredRecyclerView.setLayoutManager(featuredRecyclerViewLayoutManager);
        featuredRecyclerView.setAdapter(featuredRecyclerViewAdapter);


        itemsRecyclerView = findViewById(R.id.find_item_list_recyclerview);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);

    }
}
