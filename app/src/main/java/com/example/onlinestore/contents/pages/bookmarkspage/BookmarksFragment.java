package com.example.onlinestore.contents.pages.bookmarkspage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment {

    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    List<ProductEntity> itemCardModelArrayList;

    ArrayList<String> filterSizeList, filterCityList, filterCategoryList, filterGenderList, filterSortList;
    LinearLayout filterDetail;
    MaterialButton filterButton;
    AppBarLayout topBar;
    MaterialAutoCompleteTextView feedFilterCategory, feedFilterGender, feedFilterSort, feedFilterSize, feedFilterCity;

    SharedPreferences sharedPreferences;
    UserEntity currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmarks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("currentLoggedUser", Context.MODE_PRIVATE);
        currentUser = new Gson().fromJson(sharedPreferences.getString("currentUser", ""), UserEntity.class);

        filterDetail = view.findViewById(R.id.bookmarks_filter_expandable_layout);
        filterButton = view.findViewById(R.id.bookmarks_filter_button);
        topBar = view.findViewById(R.id.top_app_bar_bookmarks);
        feedFilterCategory = view.findViewById(R.id.bookmarks_filter_category_autocomplete);
        feedFilterGender = view.findViewById(R.id.bookmarks_filter_gender_autocomplete);
        feedFilterSort = view.findViewById(R.id.bookmarks_filter_sort_autocomplete);
        itemsRecyclerView = view.findViewById(R.id.bookmarks_item_list_recyclerview);

        setupRecyclerViewAndTouchHelper();

        setFilterCategory();
        setFilterGender();
        setFilterSort();

        filterButton.setOnClickListener(v -> toggleExpandable());
    }

    private void setFilterSort() {
        filterSortList = new ArrayList<>();
        filterSortList.add("No Filter");
        filterSortList.add("Lowest Price");
        filterSortList.add("Highest Price");
        filterSortList.add("Fritillaria's Choice");

        ArrayAdapter<String> sortDropdownListAdapter = new ArrayAdapter(requireContext(), R.layout.list_item_layout, filterSortList);
        feedFilterSort.setAdapter(sortDropdownListAdapter);
    }

    private void setFilterGender() {
        filterGenderList = new ArrayList<>();
        filterGenderList.add("No Filter");
        filterGenderList.add("Men");
        filterGenderList.add("Women");
        filterGenderList.add("Kids");

        ArrayAdapter<String> genderDropDownListAdapter = new ArrayAdapter(requireContext(), R.layout.list_item_layout, filterGenderList);
        feedFilterGender.setAdapter(genderDropDownListAdapter);
    }

    private void setFilterCategory() {
        filterCategoryList = new ArrayList<>();
        filterCategoryList.add("No Filter");
        filterCategoryList.add("Accessories");
        filterCategoryList.add("Clothing");
        filterCategoryList.add("Shoes");
        filterCategoryList.add("Underwear");

        ArrayAdapter<String> categoryDropdownListAdapter = new ArrayAdapter(requireContext()
                , R.layout.list_item_layout, filterCategoryList);
        feedFilterCategory.setAdapter(categoryDropdownListAdapter);
    }

    private void setupRecyclerViewAndTouchHelper() {
        itemCardModelArrayList = currentUser.getBookmarks();
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList, getContext(), "bookmarks");
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ProductEntity delBookmark = itemCardModelArrayList.get(position);
                itemCardModelArrayList.remove(position);
                itemsRecyclerViewAdapter.notifyItemRemoved(position);

                saveUserToSharedPref();

                Snackbar.make(itemsRecyclerView, "Product with ID #" + delBookmark.getId() + "removed from bookmarks.", Snackbar.LENGTH_LONG)
                        .setAction("Undo", view -> {
                            itemCardModelArrayList.add(position, delBookmark);
                            itemsRecyclerViewAdapter.notifyItemInserted(position);

                            saveUserToSharedPref();

                            Toast.makeText(getContext(), "Deleted card Restored", Toast.LENGTH_SHORT).show();
                        });
            }
        };
        ItemTouchHelper swipeGestures = new ItemTouchHelper(simpleCallback);
        swipeGestures.attachToRecyclerView(itemsRecyclerView);
    }

    private void saveUserToSharedPref() {
        sharedPreferences.edit().putString("currentUser", new Gson().toJson(currentUser)).apply();
    }


    private void toggleExpandable() {
        if (filterDetail.getVisibility() == View.VISIBLE) {
            TransitionManager.beginDelayedTransition(topBar, new ChangeBounds());
            filterDetail.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(topBar, new ChangeBounds());
            filterDetail.setVisibility(View.VISIBLE);
        }
    }
}