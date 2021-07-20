package com.example.onlinestore.contents.pages.findpage;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.FeaturedRecyclerViewAdapter;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindResultFragment extends Fragment {

    private RecyclerView itemsRecyclerView, featuredRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter, featuredRecyclerViewAdapter;
    private LinearLayout noResult;
    private List<ProductEntity> allItemProducts = new ArrayList<>();
    private List<ProductEntity> allFeaturedProducts = new ArrayList<>();
    private List<ProductEntity> itemCardModelArrayList;
    private List<ProductEntity> featuredCardModelArrayList;

    private AppSharedViewModel sharedViewModel;
    private SharedPreferences sharedPreferences;

    private UserEntity currentUser;

    private FindResultFragmentArgs args;
    private String query;

    ArrayList<String> filterSizeList, filterCityList, filterCategoryList, filterGenderList, filterSortList;
    LinearLayout filterDetail;
    MaterialButton filterButton;
    AppBarLayout topBar;
    MaterialAutoCompleteTextView feedFilterCategory, feedFilterGender, feedFilterSort, feedFilterSize, feedFilterCity;

    private String category = "";
    private String gender = "";
    private String sort = "";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //no result
        noResult = view.findViewById(R.id.find_no_result_layout);

        //get query from find fragment
        args = FindResultFragmentArgs.fromBundle(getArguments());
        query = args.getQuery();

        //setup current user and viewmodel
        sharedPreferences = getActivity().getSharedPreferences("currentLoggedUser", Context.MODE_PRIVATE);
        currentUser = new Gson().fromJson(sharedPreferences.getString("currentUser", ""), UserEntity.class);
        sharedViewModel = new ViewModelProvider(getActivity()).get(AppSharedViewModel.class);

        //initialize the lists that we give to the adapters
        itemCardModelArrayList = new ArrayList<>();
        featuredCardModelArrayList = new ArrayList<>();

        //init filter
        filterDetail = view.findViewById(R.id.find_filter_expandable_layout);
        filterButton = view.findViewById(R.id.find_filter_button);
        topBar = view.findViewById(R.id.top_app_bar_find);
        feedFilterCategory = view.findViewById(R.id.find_filter_category_autocomplete);
        feedFilterGender = view.findViewById(R.id.find_filter_gender_autocomplete);
        feedFilterSort = view.findViewById(R.id.find_filter_sort_autocomplete);

        filterButton.setOnClickListener(v -> toggleExpandable());

        //populate temporary lists to start filtering
        sharedViewModel.getAllProducts().observe(getViewLifecycleOwner(), productEntities -> {
            allItemProducts.clear();
            allItemProducts.addAll(productEntities);
            excludeCurrentUserProducts(allItemProducts);

            //add valid items to final list
            includeValidProducts(allItemProducts, itemCardModelArrayList);
            if (itemCardModelArrayList == null || itemCardModelArrayList.isEmpty()) {
                noResult.setVisibility(View.VISIBLE);
            }
            itemsRecyclerViewAdapter.notifyDataSetChanged();
        });

        setFilterCategory();
        setFilterGender();
        setFilterSort();


//        sharedViewModel.getAllFeaturedProducts().observe(getViewLifecycleOwner(), productEntities -> {
//            allFeaturedProducts.clear();
//            allFeaturedProducts.addAll(productEntities);
//            excludeCurrentUserProducts(allFeaturedProducts);
//
//            //add valid items to final list
//            includeValidProducts(allItemProducts, itemCardModelArrayList);
//            featuredRecyclerViewAdapter.notifyDataSetChanged();
//        });

        //setup recyclerview adapters and stuff
        itemsRecyclerView = view.findViewById(R.id.find_item_list_recyclerview);
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList, getContext(), "find_result", sharedViewModel);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);

//        featuredRecyclerView = view.findViewById(R.id.feed_featured_list_recyclerview);
//        featuredRecyclerViewAdapter = new FeaturedRecyclerViewAdapter(featuredCardModelArrayList, getActivity().getSupportFragmentManager(), getContext());
//        featuredRecyclerView.setHasFixedSize(true);
//        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        featuredRecyclerView.setAdapter(featuredRecyclerViewAdapter);
    }


    private void includeValidProducts(List<ProductEntity> allItemProducts, List<ProductEntity> itemCardModelArrayList) {
        String queryLowerCase = query.toLowerCase();
        for (ProductEntity product : allItemProducts) {
            if (productContainsQuery(product, queryLowerCase))
                itemCardModelArrayList.add(product);
        }
    }

    private void excludeCurrentUserProducts(List<ProductEntity> list) {
        list.removeIf(product -> product.getSeller().getEmail().equals(currentUser.getEmail()));
    }

    private boolean productContainsQuery(ProductEntity productEntity, String query) {
        return productEntity.getItemTitle().toLowerCase().contains(query)
                || productEntity.getItemDescription().toLowerCase().contains(query)
                || productEntity.getItemCity().toLowerCase().contains(query)
                || productEntity.getItemCategory().toLowerCase().contains(query)
                || productEntity.getItemGender().toLowerCase().contains(query);
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

//        feedFilterSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                sort = filterSortList.get(i);
//                onFilterChange();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        feedFilterSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sort = filterSortList.get(position);
                onFilterChange();
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

//        feedFilterGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                gender = filterGenderList.get(i);
//                onFilterChange();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        feedFilterSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gender = filterGenderList.get(position);
                onFilterChange();
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

//        feedFilterCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(), "papaya", Toast.LENGTH_SHORT).show();
//                category = filterCategoryList.get(i);
//                onFilterChange();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        feedFilterCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = filterCategoryList.get(position);
                onFilterChange();
            }
        });
    }


    private void onFilterChange() {
        List<ProductEntity> filtered = itemCardModelArrayList;


        if (currentUser.getBookmarks() != null)
            for (ProductEntity productEntity : currentUser.getBookmarks())
                if (isCategoryMatched(productEntity) & isGenderMatched(productEntity))
                    filtered.add(productEntity);

//        if (!sort.equals("No Filter")) {
//            ProductEntity product1, product2;
//            for (int i = 0; i < itemCardModelArrayList.size() - 1; i++) {
//                product1 = itemCardModelArrayList.get(i);
//                for (int j = i + 1; j < itemCardModelArrayList.size(); j++) {
//                    product2 = itemCardModelArrayList.get(j);
//                    if (shouldSortLowestPrice(product1, product2)
//                            || shouldSortHighestPrice(product1, product2))
//                        Collections.swap(itemCardModelArrayList, i, j);
//                }
//            }
//        }

        if (sort.equals("Highest Price")) {
            filtered.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {

                    double discount1 = Double.parseDouble(o1.getItemDiscount());
                    double rawPrice1 = Double.parseDouble(o1.getItemRawPrice());
                    double finalPrice1 = (rawPrice1 * (100 - discount1)) / 100;
                    double discount2 = Double.parseDouble(o2.getItemDiscount());
                    double rawPrice2 = Double.parseDouble(o2.getItemRawPrice());
                    double finalPrice2 = (rawPrice1 * (100 - discount2)) / 100;

                    return ((int)(finalPrice2-finalPrice1));

                }
            });
        }else if (sort.equals("Lowest Price")){
            filtered.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity o1, ProductEntity o2) {

                    double discount1 = Double.parseDouble(o1.getItemDiscount());
                    double rawPrice1 = Double.parseDouble(o1.getItemRawPrice());
                    double finalPrice1 = (rawPrice1 * (100 - discount1)) / 100;
                    double discount2 = Double.parseDouble(o2.getItemDiscount());
                    double rawPrice2 = Double.parseDouble(o2.getItemRawPrice());
                    double finalPrice2 = (rawPrice1 * (100 - discount2)) / 100;

                    return ((int)(finalPrice1-finalPrice2));
                }
            });
        }




        itemCardModelArrayList = filtered;
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
