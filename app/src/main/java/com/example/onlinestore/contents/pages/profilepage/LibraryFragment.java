package com.example.onlinestore.contents.pages.profilepage;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.example.onlinestore.contents.pages.feedpage.ItemCardModel;
import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    private NavController navController;
    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    ArrayList<ItemCardModel> itemCardModelArrayList;
    MaterialToolbar topBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_library, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemsRecyclerView = view.findViewById(R.id.library_item_list_recyclerview);
        topBar = view.findViewById(R.id.top_app_bar_library);
        navController = Navigation.findNavController(view);


        itemCardModelArrayList = new ArrayList<>();
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));
        itemCardModelArrayList.add(new ItemCardModel(R.drawable.default_profile_picture, R.drawable.default_item_image, "#24586", "Item Title", "Item Description. Long Text Long Text. Long Text.", "XXL", "Men", "Clothing", "Paris, France", "139.99", "99.99", false));

        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        itemsRecyclerViewAdapter = new ItemRecyclerViewAdapter(itemCardModelArrayList);
        itemsRecyclerView.setHasFixedSize(true);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
        itemsRecyclerView.setAdapter(itemsRecyclerViewAdapter);


        ItemTouchHelper.SimpleCallback swipeManager = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }


            ItemCardModel deletedCard = null;
            ItemCardModel cardToEdit = null;

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        deletedCard = itemCardModelArrayList.get(position);
                        itemCardModelArrayList.remove(position);
                        itemsRecyclerViewAdapter.notifyItemRemoved(position);
                        Snackbar.make(itemsRecyclerView, "Product with ID #" + deletedCard.getItemId() + " was removed", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        itemCardModelArrayList.add(position, deletedCard);
                                        itemsRecyclerViewAdapter.notifyItemInserted(position);
                                        Toast.makeText(getContext(), "Deleted card Restored", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                        break;

                    case ItemTouchHelper.RIGHT:
                        LibraryFragmentDirections.ActionLibraryPageToEditCardPage toEditCardPage = LibraryFragmentDirections.actionLibraryPageToEditCardPage(itemCardModelArrayList.get(position));
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
}
