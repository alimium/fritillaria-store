package com.example.onlinestore.contents.pages.bookmarkspage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookmarksFragment extends Fragment {

    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    List<ProductEntity> itemCardModelArrayList = new ArrayList();

    ArrayList<String> filterSizeList, filterCityList, filterCategoryList, filterGenderList, filterSortList;
    LinearLayout filterDetail;
    MaterialButton filterButton;
    AppBarLayout topBar;
    MaterialAutoCompleteTextView feedFilterCategory, feedFilterGender, feedFilterSort, feedFilterSize, feedFilterCity;

    SharedPreferences sharedPreferences;
    UserEntity currentUser;
    AppSharedViewModel sharedViewModel;

    private String category = "";
    private String gender = "";
    private String sort = "";

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
        sharedViewModel = new ViewModelProvider(getActivity()).get(AppSharedViewModel.class);

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

    private void setupRecyclerViewAndTouchHelper() {
        if (currentUser.getBookmarks() != null)
            itemCardModelArrayList.addAll(currentUser.getBookmarks());
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
                currentUser.getBookmarks().remove(delBookmark);

                saveUserToSharedPref();
                updateUserInDb();

                Snackbar.make(itemsRecyclerView, "Product with ID #" + delBookmark.getId() + "removed from bookmarks.", Snackbar.LENGTH_LONG)
                        .setAction("Undo", view -> {
                            itemCardModelArrayList.add(position, delBookmark);
                            itemsRecyclerViewAdapter.notifyItemInserted(position);
                            currentUser.getBookmarks().add(delBookmark);

                            saveUserToSharedPref();
                            updateUserInDb();

                            Toast.makeText(getContext(), "Deleted card Restored successfully", Toast.LENGTH_SHORT).show();
                        });
            }
        };
        ItemTouchHelper swipeGestures = new ItemTouchHelper(simpleCallback);
        swipeGestures.attachToRecyclerView(itemsRecyclerView);
    }

    private void updateUserInDb() {
        sharedViewModel.updateUser(currentUser);
    }

    private void saveUserToSharedPref() {
        sharedPreferences.edit().putString("currentUser", new Gson().toJson(currentUser)).apply();
    }

    private void setFilterSort() {
        filterSortList = new ArrayList<>();
        filterSortList.add("No Filter");
        filterSortList.add("Lowest Price");
        filterSortList.add("Highest Price");
        filterSortList.add("Fritillaria's Choice");

        sort = filterSortList.get(0);

        feedFilterSort.setAdapter(new ArrayAdapter<>(requireContext(),
                R.layout.list_item_layout, filterSortList));

        feedFilterSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sort = filterSortList.get(i);
                onFilterChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setFilterGender() {
        filterGenderList = new ArrayList<>();
        filterGenderList.add("No Filter");
        filterGenderList.add("Men");
        filterGenderList.add("Women");
        filterGenderList.add("Kids");

        gender = filterGenderList.get(0);

        feedFilterGender.setAdapter(new ArrayAdapter<>(requireContext(),
                R.layout.list_item_layout, filterGenderList));

        feedFilterGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = filterGenderList.get(i);
                onFilterChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setFilterCategory() {
        filterCategoryList = new ArrayList<>();
        filterCategoryList.add("No Filter");
        filterCategoryList.add("Accessories");
        filterCategoryList.add("Clothing");
        filterCategoryList.add("Shoes");
        filterCategoryList.add("Underwear");

        category = filterCategoryList.get(0);

        feedFilterCategory.setAdapter(new ArrayAdapter<>(requireContext()
                , R.layout.list_item_layout, filterCategoryList));

        feedFilterCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = filterCategoryList.get(i);
                onFilterChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void onFilterChange() {
        itemCardModelArrayList.clear();
        if (currentUser.getBookmarks() != null)
            for (ProductEntity productEntity : currentUser.getBookmarks())
                if (isCategoryMatched(productEntity) & isGenderMatched(productEntity))
                    itemCardModelArrayList.add(productEntity);

        if (!sort.equals("No Filter")) {
            ProductEntity product1, product2;
            for (int i = 0; i < itemCardModelArrayList.size() - 1; i++) {
                product1 = itemCardModelArrayList.get(i);
                for (int j = i + 1; j < itemCardModelArrayList.size(); j++) {
                    product2 = itemCardModelArrayList.get(j);
                    if (shouldSortLowestPrice(product1, product2)
                            || shouldSortHighestPrice(product1, product2))
                        Collections.swap(itemCardModelArrayList, i, j);
                }
            }
        }

        itemsRecyclerViewAdapter.notifyDataSetChanged();
    }

    private boolean isCategoryMatched(ProductEntity productEntity) {
        return category.equals("No Filter") || (productEntity.getItemCategory().equals(category));
    }

    private boolean isGenderMatched(ProductEntity productEntity) {
        return gender.equals("No Filter") || (productEntity.getItemGender().equals(gender));
    }

    private boolean shouldSortHighestPrice(ProductEntity product1, ProductEntity product2) {
        return sort.equals("Highest Price") &
                (product1.getItemRawPrice().compareTo(product2.getItemRawPrice())) < 0;
    }

    private boolean shouldSortLowestPrice(ProductEntity product1, ProductEntity product2) {
        return sort.equals("Lowest Price") &
                (product1.getItemRawPrice().compareTo(product2.getItemRawPrice())) > 0;
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