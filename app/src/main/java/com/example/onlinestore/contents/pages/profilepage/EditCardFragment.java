package com.example.onlinestore.contents.pages.profilepage;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.onlinestore.R;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EditCardFragment extends Fragment {


    EditCardFragmentArgs args;
    String cardToEditStr;
    ProductEntity cardToEdit;
    ArrayList<String> categoryDropdownArrayList = new ArrayList<>();
    ArrayList<String> genderDropdownArrayList = new ArrayList<>();

    ShapeableImageView itemImage;
    TextInputEditText itemTitle, itemDescription, itemPrice, itemDiscount, itemSize, itemCity;
    MaterialAutoCompleteTextView itemCategory;
    ChipGroup itemGenderChipGroup;
    Chip itemGender;
    MaterialButton applyChangesButton, changePictureButton;

    AppSharedViewModel sharedViewModel;
    MaterialToolbar toolbar;
    NavController navController;

    String itemImageString;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(getActivity()).get(AppSharedViewModel.class);
        navController = Navigation.findNavController(view);

        args = EditCardFragmentArgs.fromBundle(getArguments());
        cardToEditStr = args.getCardToEdit();
        cardToEdit = new Gson().fromJson(cardToEditStr, ProductEntity.class);

        setupNavigationIconBackButton(view);
        setupDropdownLists(view);
        initializeElements(view);
        setValuesToFields();


        //TODO: SET VALUES AND STUFF
        //          ......
        //--------------------------

        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemImage.launch(new String[]{"image/*"});
            }
        });

        itemGenderChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                itemGender = view.findViewById(itemGenderChipGroup.getCheckedChipId());
            }
        });

        applyChangesButton.setOnClickListener(new View.OnClickListener() {
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
                if (itemGenderChipGroup.getCheckedChipId() == View.NO_ID) {
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



                cardToEdit.setItemPicture(itemImagePath);
                cardToEdit.setItemTitle(itemTitleStr);
                cardToEdit.setItemDescription(itemDescriptionStr);
                cardToEdit.setItemRawPrice(itemPriceStr);
                cardToEdit.setItemDiscount(itemDiscountStr);
                cardToEdit.setItemCategory(itemCategoryStr);
                cardToEdit.setItemGender(itemGenderStr);
                cardToEdit.setItemSize(itemSizeStr);
                cardToEdit.setItemCity(itemCityStr);

                sharedViewModel.updateProduct(cardToEdit);
                navController.popBackStack();
            }
        });




    }

    private void setValuesToFields() {

        if (cardToEdit.getItemPicture()!=null){
            Glide.with(getContext()).load(Uri.parse(cardToEdit.getItemPicture())).into(itemImage);

        }
        itemTitle.setText(cardToEdit.getItemTitle());
        itemDescription.setText(cardToEdit.getItemDescription());
        itemPrice.setText(cardToEdit.getItemRawPrice());
        itemDiscount.setText(cardToEdit.getItemDiscount());
        itemCategory.setText(cardToEdit.getItemCategory());
//        String gender = cardToEdit.getItemGender();
//        switch (gender){
//            case "Men":
//                itemGenderChipGroup.check(R.id.chip_men_edit_card);
//                break;
//            case "Women":
//                itemGenderChipGroup.check(R.id.chip_women_edit_card);
//                break;
//            case "Kid":
//                itemGenderChipGroup.check(R.id.chip_kids_edit_card);
//                break;
//        }
        itemSize.setText(cardToEdit.getItemSize());
        itemCity.setText(cardToEdit.getItemCity());
    }
    private void initializeElements(@NonNull View view) {
        itemImage = view.findViewById(R.id.edit_card_item_image_view);
        itemTitle = view.findViewById(R.id.edit_card_title_textview);
        itemDescription = view.findViewById(R.id.edit_card_description_textview);
        itemPrice = view.findViewById(R.id.edit_card_price_textview);
        itemDiscount = view.findViewById(R.id.edit_card_discount_textview);
        itemCategory = view.findViewById(R.id.edit_card_category_autocomplete);
        itemGenderChipGroup = view.findViewById(R.id.chipGroup_edit_card);
        itemSize = view.findViewById(R.id.edit_card_size_text_view);
        itemCity = view.findViewById(R.id.edit_card_city_textview);
        changePictureButton = view.findViewById(R.id.edit_card_change_picture_button);
        applyChangesButton = view.findViewById(R.id.edit_card_next_button);
    }
    private void setupNavigationIconBackButton(@NonNull View view) {
        toolbar = view.findViewById(R.id.top_app_bar_edit_card);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
    private void setupDropdownLists(@NonNull View view) {

        categoryDropdownArrayList.add("Accessories");
        categoryDropdownArrayList.add("Clothing");
        categoryDropdownArrayList.add("Shoes");
        categoryDropdownArrayList.add("Underwear");

        genderDropdownArrayList.add("Men");
        genderDropdownArrayList.add("Women");
        genderDropdownArrayList.add("Kids");

        ArrayAdapter<String> categoryDropdownListAdapter = new ArrayAdapter<String>(requireContext()
                , R.layout.list_item_layout, categoryDropdownArrayList);
        MaterialAutoCompleteTextView categoryDropDown = view.findViewById(R.id.edit_card_category_autocomplete);
        categoryDropDown.setAdapter(categoryDropdownListAdapter);
    }









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_card, container, false);
    }

}