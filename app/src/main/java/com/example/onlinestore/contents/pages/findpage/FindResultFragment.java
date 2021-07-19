package com.example.onlinestore.contents.pages.findpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.FeaturedRecyclerViewAdapter;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class FindResultFragment extends Fragment {

    private RecyclerView featuredRecyclerView, itemsRecyclerView;
    private RecyclerView.Adapter featuredRecyclerViewAdapter, itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager featuredRecyclerViewLayoutManager, itemsRecyclerViewLayoutManager;
    List<ProductEntity> featuredCardModelArrayList;
    List<ProductEntity> itemCardModelArrayList;

    public FindResultFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemCardModelArrayList = new ArrayList<>();
        featuredCardModelArrayList = new ArrayList<>();


        featuredRecyclerView = view.findViewById(R.id.find_featured_list_recyclerview);
        featuredRecyclerViewLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        featuredRecyclerViewAdapter = new FeaturedRecyclerViewAdapter(featuredCardModelArrayList, getActivity().getSupportFragmentManager(), getContext());
        featuredRecyclerView.setHasFixedSize(true);
        featuredRecyclerView.setLayoutManager(featuredRecyclerViewLayoutManager);
        featuredRecyclerView.setAdapter(featuredRecyclerViewAdapter);


        itemsRecyclerView = view.findViewById(R.id.find_item_list_recyclerview);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList, getContext());
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);

    }
}
