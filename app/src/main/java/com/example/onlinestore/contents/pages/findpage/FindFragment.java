package com.example.onlinestore.contents.pages.findpage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlinestore.MainActivity;
import com.example.onlinestore.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class FindFragment extends Fragment {

    MaterialButton findButton;
    TextInputEditText searchInputBox;
    String query;

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

                if (isAdded()) {
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.nav_controller,new FindResultFragment(query)).commit();
                }else {
                    throw new IllegalStateException();
                }
            }
        });












        return v;
    }
}