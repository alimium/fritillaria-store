package com.example.onlinestore.contents.pages.findpage;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinestore.R;
import com.example.onlinestore.data.ProductEntity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {
    NavController navController;
    LayoutInflater customToastInflater;
    View customToastLayout;
    MaterialButton findButton;
    TextInputEditText searchInputBox;
    String query;
    List<ProductEntity> resultListFeatured;
    List<ProductEntity> resultListMain;

    public FindFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_find, container, false);

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findButton = view.findViewById(R.id.find_find_button);
        searchInputBox = view.findViewById(R.id.find_search_textview);
        navController = Navigation.findNavController(view);

        String query = searchInputBox.getText().toString();

        //----search database------
        //          ...
        //_________________________

        resultListMain = new ArrayList<>();

        resultListFeatured = new ArrayList<>();


        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_find_page_to_find_result_page);
            }
        });


    }
}