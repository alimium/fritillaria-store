package com.example.onlinestore.contents.pages.findpage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinestore.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class FindFragment extends Fragment {

    MaterialButton findButton;
    MaterialTextView searchInputBox;

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

        findButton = v.findViewById(R.id.find_find_button);
        searchInputBox = v.findViewById(R.id.find_search_textview);

        String query = searchInputBox.getText().toString();

        //get data from database
        //        ...
        //______________________



        //replace this fragment with feed fragment with the given arraylist
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });












        return v;
    }
}