package com.example.onlinestore.contents.pages.bookmarkspage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.FeaturedRecyclerViewAdapter;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.ProductEntity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment {

    private RecyclerView featuredRecyclerView, itemsRecyclerView;
    private RecyclerView.Adapter featuredRecyclerViewAdapter, itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager featuredRecyclerViewLayoutManager, itemsRecyclerViewLayoutManager;
    List<ProductEntity> featuredCardModelArrayList;
    List<ProductEntity> itemCardModelArrayList;
    ArrayList<String> filterSizeList, filterCityList, filterCategoryList, filterGenderList, filterSortList;
    LinearLayout filterDetail;
    MaterialButton filterButton;
    AppBarLayout topBar;
    MaterialAutoCompleteTextView feedFilterCategory, feedFilterGender, feedFilterSort, feedFilterSize, feedFilterCity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filterDetail = view.findViewById(R.id.bookmarks_filter_expandable_layout);
        filterButton = view.findViewById(R.id.bookmarks_filter_button);
        topBar = view.findViewById(R.id.top_app_bar_bookmarks);
        feedFilterCategory = view.findViewById(R.id.bookmarks_filter_category_autocomplete);
        feedFilterGender = view.findViewById(R.id.bookmarks_filter_gender_autocomplete);
        feedFilterSort = view.findViewById(R.id.bookmarks_filter_sort_autocomplete);


        itemCardModelArrayList = new ArrayList<>();

        featuredCardModelArrayList = new ArrayList<>();


        featuredRecyclerView = view.findViewById(R.id.bookmarks_featured_list_recyclerview);
        featuredRecyclerViewLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        featuredRecyclerViewAdapter = new FeaturedRecyclerViewAdapter(featuredCardModelArrayList, getActivity().getSupportFragmentManager(), getContext());
        featuredRecyclerView.setHasFixedSize(true);
        featuredRecyclerView.setLayoutManager(featuredRecyclerViewLayoutManager);
        featuredRecyclerView.setAdapter(featuredRecyclerViewAdapter);

        itemsRecyclerView = view.findViewById(R.id.bookmarks_item_list_recyclerview);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList, getContext());
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);


        filterCategoryList = new ArrayList<String>();
        filterGenderList = new ArrayList<>();
        filterSortList = new ArrayList<>();


        filterCategoryList.add("No Filter");
        filterCategoryList.add("Accessories");
        filterCategoryList.add("Clothing");
        filterCategoryList.add("Shoes");
        filterCategoryList.add("Underwear");

        filterGenderList.add("No Filter");
        filterGenderList.add("Men");
        filterGenderList.add("Women");
        filterGenderList.add("Kids");

        filterSortList.add("No Filter");
        filterSortList.add("Lowest Price");
        filterSortList.add("Highest Price");
        filterSortList.add("Fritillaria's Choice");


        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandable();
            }
        });


        ArrayAdapter<String> categoryDropdownListAdapter = new ArrayAdapter<String>(requireContext()
                , R.layout.list_item_layout, filterCategoryList);
        feedFilterCategory.setAdapter(categoryDropdownListAdapter);

        ArrayAdapter<String> genderDropDownListAdapter = new ArrayAdapter<String>(requireContext(), R.layout.list_item_layout, filterGenderList);
        feedFilterGender.setAdapter(genderDropDownListAdapter);

        ArrayAdapter<String> sortDropdownListAdapter = new ArrayAdapter<String>(requireContext(), R.layout.list_item_layout, filterSortList);
        feedFilterSort.setAdapter(sortDropdownListAdapter);






    }


    private void toggleExpandable() {
        if (filterDetail.getVisibility()==View.VISIBLE){
            TransitionManager.beginDelayedTransition(topBar, new ChangeBounds());
            filterDetail.setVisibility(View.GONE);
        }else {
            TransitionManager.beginDelayedTransition(topBar, new ChangeBounds());
            filterDetail.setVisibility(View.VISIBLE);
        }
    }
}