package com.example.onlinestore.contents.pages.findpage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.FeaturedRecyclerViewAdapter;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FindResultFragment extends Fragment {

    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    private List<ProductEntity> allItemProducts = new ArrayList<>();
    private List<ProductEntity> allFeaturedProducts = new ArrayList<>();
    private List<ProductEntity> itemCardModelArrayList;
    private List<ProductEntity> featuredCardModelArrayList;

    private AppSharedViewModel sharedViewModel;
    private SharedPreferences sharedPreferences;

    private UserEntity currentUser;

    private FindResultFragmentArgs args;
    private String query;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get query from find fragment
        args = FindResultFragmentArgs.fromBundle(getArguments());
        query = args.getQuery();

        //setup current user and viewmodel
        sharedPreferences = getActivity().getSharedPreferences("currentLoggedUser", Context.MODE_PRIVATE);
        currentUser = new Gson().fromJson(sharedPreferences.getString("currentUser",""), UserEntity.class);
        sharedViewModel = new ViewModelProvider(getActivity()).get(AppSharedViewModel.class);

        //initialize the lists that we give to the adapters
        itemCardModelArrayList = new ArrayList<>();
        featuredCardModelArrayList = new ArrayList<>();

        //populate temporary lists to start filtering
        sharedViewModel.getAllItemProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                allItemProducts.clear();
                allItemProducts.addAll(productEntities);
                excludeCurrentUserProducts(allItemProducts);

                //add valid items to final list
                includeValidProducts(allItemProducts, itemCardModelArrayList);
                itemsRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        sharedViewModel.getAllFeaturedProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                allFeaturedProducts.clear();
                allFeaturedProducts.addAll(productEntities);
                excludeCurrentUserProducts(allFeaturedProducts);

                //add valid items to final list
                includeValidProducts(allItemProducts, itemCardModelArrayList);
                //TODO:
                //  featuredRecyclerViewAdapter.notifyDataSetChanged();
            }
        });


        //setup recyclerview adapters and stuff
        itemsRecyclerView = view.findViewById(R.id.find_item_list_recyclerview);
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList, getContext(), "find_result", sharedViewModel);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);

        //TODO:
    //        featuredRecyclerView = view.findViewById(R.id.feed_featured_list_recyclerview);
    //        featuredRecyclerViewLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    //        featuredRecyclerViewAdapter = new FeaturedRecyclerViewAdapter(featuredCardModelArrayList, getActivity().getSupportFragmentManager(), getContext());
    //        featuredRecyclerView.setHasFixedSize(true);
    //        featuredRecyclerView.setLayoutManager(featuredRecyclerViewLayoutManager);
    //        featuredRecyclerView.setAdapter(featuredRecyclerViewAdapter);

    }


    private void includeValidProducts(List<ProductEntity> allItemProducts, List<ProductEntity> itemCardModelArrayList) {

        //TODO: get valid cards from arraylists ----------------------------------------
        //              .......
        // use .... .toString().toLowerCase(); to check if query matches product data
        // _____________________________________________________________________________

    }

    private void excludeCurrentUserProducts(List<ProductEntity> list) {
        list.removeIf(product -> product.getSeller().getEmail().equals(currentUser.getEmail()));
    }
}
