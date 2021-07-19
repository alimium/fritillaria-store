package com.example.onlinestore.contents.pages.profilepage;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {

    private NavController navController;
    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    List<ProductEntity> itemCardModelArrayList = new ArrayList<>();
    MaterialToolbar topBar;
    AppSharedViewModel sharedViewModel;
    SharedPreferences sharedPreferences;
    UserEntity currentUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("currentLoggedUser", Context.MODE_PRIVATE);
        currentUser = new Gson().fromJson(sharedPreferences.getString("currentUser",""), UserEntity.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(AppSharedViewModel.class);
        sharedViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                List<ProductEntity> allProducts = new ArrayList<>(productEntities);
                extractCurrentUserDataFromProducts(allProducts);
            }
        });


        itemsRecyclerView = view.findViewById(R.id.library_item_list_recyclerview);
        topBar = view.findViewById(R.id.top_app_bar_library);
        navController = Navigation.findNavController(view);

        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList, getContext(), "library");
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);


        ItemTouchHelper.SimpleCallback swipeManager = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }


            ProductEntity deletedCard = null;
            ProductEntity cardToEdit = null;

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        deletedCard = itemCardModelArrayList.get(position);
                        itemCardModelArrayList.remove(position);
                        itemsRecyclerViewAdapter.notifyItemRemoved(position);
                        Snackbar.make(itemsRecyclerView, "Product with ID #" + deletedCard.getId() + " was removed", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        itemCardModelArrayList.add(position, deletedCard);
                                        itemsRecyclerViewAdapter.notifyItemInserted(position);
                                        Toast.makeText(getContext(), "Deleted card Restored", Toast.LENGTH_SHORT).show();
                                        sharedViewModel.insertProduct(deletedCard);
                                        currentUser.setProducts(currentUser.getProducts()+1);
                                        sharedViewModel.updateUser(currentUser);

                                    }
                                }).show();
                            sharedViewModel.deleteProduct(deletedCard);
                            currentUser.setProducts(currentUser.getProducts()-1);
                            sharedViewModel.updateUser(currentUser);
                            break;

                    case ItemTouchHelper.RIGHT:
                        LibraryFragmentDirections.ActionLibraryPageToEditCardPage toEditCardPage = LibraryFragmentDirections.actionLibraryPageToEditCardPage(new Gson().toJson(itemCardModelArrayList.get(position)));
                        navController.navigate(toEditCardPage);
                        break;

                }
            }
        };

        ItemTouchHelper swipeGestures = new ItemTouchHelper(swipeManager);
        swipeGestures.attachToRecyclerView(itemsRecyclerView);


        topBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }

    private void extractCurrentUserDataFromProducts(List<ProductEntity> allProducts) {
        itemCardModelArrayList.clear();
        for (ProductEntity item : allProducts){
            if (item.getSeller().getEmail().equals(currentUser.getEmail())){
                itemCardModelArrayList.add(item);
            }
        }
        itemsRecyclerViewAdapter.notifyDataSetChanged();
    }
}
