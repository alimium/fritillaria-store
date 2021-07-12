package com.example.onlinestore.contents.pages.postpage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.onlinestore.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class PostFragment extends Fragment {

    public PostFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialToolbar toolbar = view.findViewById(R.id.top_app_bar_post);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ArrayList<String> categoryDropdownArrayList = new ArrayList<>();
        ArrayList<String> genderDropdownArrayList = new ArrayList<>();

        categoryDropdownArrayList.add("Accessories");
        categoryDropdownArrayList.add("Clothing");
        categoryDropdownArrayList.add("Shoes");
        categoryDropdownArrayList.add("Underwear");

        genderDropdownArrayList.add("Men");
        genderDropdownArrayList.add("Women");
        genderDropdownArrayList.add("Kids");

        ArrayAdapter<String> categoryDropdownListAdapter = new ArrayAdapter<String>(requireContext()
                , R.layout.list_item_layout,categoryDropdownArrayList);
        MaterialAutoCompleteTextView categoryDropDown = view.findViewById(R.id.post_category_autocomplete);
        categoryDropDown.setAdapter(categoryDropdownListAdapter);



        TextInputLayout sizeTextInputLayout = view.findViewById(R.id.post_size);
        TextInputEditText sizeTextView = view.findViewById(R.id.post_size_text_view);




    }

}