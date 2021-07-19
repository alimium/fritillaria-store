package com.example.onlinestore.contents.pages.postpage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onlinestore.R;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    private static final String TAG = "CHIPGROUP";

    Uri itemImageUri;
    String itemImageString;
    ShapeableImageView itemImage;
    TextInputEditText itemTitle, itemDescription, itemPrice, itemDiscount, itemSize, itemCity;
    ChipGroup itemGenderGroup;
    Chip itemGender;
    AutoCompleteTextView itemCategory;
    MaterialButton changePictureButton, savePostButton;
    SharedPreferences sharedPreferences;
    AppSharedViewModel sharedViewModel;
    MaterialToolbar toolbar;
    UserEntity seller;
    NavController navController;


    String itemImagePath, itemTitleStr, itemDescriptionStr, itemPriceStr,
            itemDiscountStr, itemCategoryStr, itemGenderStr, itemSizeStr,
            itemCityStr;


    ActivityResultLauncher<String[]> getItemImage = registerForActivityResult(new ActivityResultContracts.OpenDocument(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    itemImageString = result.toString();
                    ContentResolver cr = getActivity().getApplicationContext().getContentResolver();
                    int perms = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
                    cr.takePersistableUriPermission(result, perms);
                    Glide.with(requireContext()).load(result).into(itemImage);
                }
            });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("currentLoggedUser", Context.MODE_PRIVATE);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(AppSharedViewModel.class);
        initializeElements(view);

        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemImage.launch(new String[]{"image/*"});
            }
        });

        itemGenderGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                itemGender = view.findViewById(itemGenderGroup.getCheckedChipId());
            }
        });

        savePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemTitle.getText().toString().equals("")) {
                    Toast.makeText(requireContext(), "Product Title Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemDescription.getText().toString().equals("")) {
                    Toast.makeText(requireContext(), "Product Description Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemPrice.getText().toString().equals("")) {
                    Toast.makeText(requireContext(), "Product Price Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemCategory.getText().toString().equals("")) {
                    Toast.makeText(requireContext(), "Product Category Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemGenderGroup.getCheckedChipId() == View.NO_ID) {
                    Toast.makeText(requireContext(), "Product Gender Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemSize.getText().toString().equals("")) {
                    Toast.makeText(requireContext(), "Product Size Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (itemCity.getText().toString().equals("")) {
                    Toast.makeText(requireContext(), "Product City Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!itemDiscount.getText().toString().equals("")) {
                    if (Double.parseDouble(itemDiscount.getText().toString()) >= 100 || Double.parseDouble(itemDiscount.getText().toString()) < 0) {
                        Toast.makeText(requireContext(), "Invalid Discount Value", Toast.LENGTH_SHORT).show();
                    }
                }


                itemImagePath = itemImageString;
                itemTitleStr = itemTitle.getText().toString();
                itemDescriptionStr = itemDescription.getText().toString();
                itemPriceStr = itemPrice.getText().toString();
                if (itemDiscount.getText().toString().equals("")) {
                    itemDiscountStr = "0";
                } else {
                    itemDiscountStr = itemDiscount.getText().toString();
                }
                itemCategoryStr = itemCategory.getText().toString();
                itemGenderStr = (String) itemGender.getText();
                itemSizeStr = itemSize.getText().toString();
                itemCityStr = itemCity.getText().toString();

                seller.setProducts(seller.getProducts()+1);
                sharedViewModel.updateUser(seller);

                ProductEntity newProduct = new ProductEntity(itemImagePath, itemTitleStr,
                        itemDescriptionStr, itemPriceStr, itemDiscountStr, itemCategoryStr,
                        itemGenderStr, itemSizeStr, itemCityStr, seller, 0);

                sharedViewModel.insertProduct(newProduct);
                Toast.makeText(requireContext(), "New Product Added. Check Library For More Info", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.feed_page);
            }
        });

    }


    private void initializeElements(@NonNull View view) {
        itemImage = view.findViewById(R.id.post_item_image_view);
        itemTitle = view.findViewById(R.id.post_title_textview);
        itemDescription = view.findViewById(R.id.post_description_textview);
        itemPrice = view.findViewById(R.id.post_price_textview);
        itemDiscount = view.findViewById(R.id.post_discount_text);
        itemCategory = view.findViewById(R.id.post_category_autocomplete);
        itemGenderGroup = view.findViewById(R.id.post_chipGroup);
        itemGender = view.findViewById(itemGenderGroup.getCheckedChipId());
        itemSize = view.findViewById(R.id.post_size_text_view);
        itemCity = view.findViewById(R.id.post_city_textview);
        changePictureButton = view.findViewById(R.id.post_change_picture_button);
        savePostButton = view.findViewById(R.id.post_next_button);
        seller = new Gson().fromJson(sharedPreferences.getString("currentUser", ""), UserEntity.class);
        navController = Navigation.findNavController(view);


        toolbar = view.findViewById(R.id.top_app_bar_post);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ArrayList<String> categoryDropdownArrayList = new ArrayList<>();
        ArrayList<String> genderDropdownArrayList = new ArrayList<>();

        categoryDropdownArrayList.add("Accessory");
        categoryDropdownArrayList.add("Clothing");
        categoryDropdownArrayList.add("Shoe");
        categoryDropdownArrayList.add("Underwear");

        genderDropdownArrayList.add("Men");
        genderDropdownArrayList.add("Women");
        genderDropdownArrayList.add("Kids");

        ArrayAdapter<String> categoryDropdownListAdapter = new ArrayAdapter<String>(requireContext()
                , R.layout.list_item_layout, categoryDropdownArrayList);
        itemCategory.setAdapter(categoryDropdownListAdapter);
    }

}