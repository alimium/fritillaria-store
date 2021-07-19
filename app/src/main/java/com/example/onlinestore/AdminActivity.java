package com.example.onlinestore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.contents.pages.feedpage.ItemRecyclerViewAdapter;
import com.example.onlinestore.data.AppSharedViewModel;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {


    AppSharedViewModel sharedViewModel;
    SharedPreferences sharedPreferences;

    List<UserEntity> allUsers = new ArrayList<>();
    List<ProductEntity> allProducts = new ArrayList<>();

    RecyclerView sellersRecycler, productsRecycler;
    SellersAdapter sellersAdapter;
    ProductsAdapter productsAdapter;

    MaterialCardView sellersCard, itemsCard;
    TextInputEditText productIdText;
    MaterialButton promoteButton, logoutButton;
    TextView productsCountText, totalValueText, sellerCountText, bestSellerName, bestSellerEmail, bestSellerProducts, bestSellerLogins;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initElements();

        sharedViewModel = new ViewModelProvider(this).get(AppSharedViewModel.class);
        sharedPreferences = getSharedPreferences("currentLoggedUser", MODE_PRIVATE);

        sharedViewModel.getAllUsers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                allUsers.clear();
                allUsers.addAll(userEntities);
                sellersAdapter.notifyDataSetChanged();
            }
        });

        sharedViewModel.getAllProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                allProducts.clear();
                allProducts.addAll(productEntities);
                productsAdapter.notifyDataSetChanged();
            }
        });



    }

    private void initElements(){
        productIdText = findViewById(R.id.admin_feature_text);
        promoteButton = findViewById(R.id.admin_submit_button);

        sellersCard = findViewById(R.id.admin_seller_card);
        sellerCountText = findViewById(R.id.admin_seller_count_text);
        bestSellerName = findViewById(R.id.admin_best_seller_name_text);
        bestSellerEmail = findViewById(R.id.admin_best_seller_email_text);
        bestSellerProducts = findViewById(R.id.admin_best_seller_item_count_text);
        bestSellerLogins = findViewById(R.id.admin_best_seller_login_count_text);
        sellersRecycler = findViewById(R.id.admin_seller_recyclerview);

        itemsCard = findViewById(R.id.admin_product_card);
        productsCountText = findViewById(R.id.admin_product_count_text);
        productsRecycler = findViewById(R.id.admin_product_recyclerview);

        totalValueText = findViewById(R.id.admin_product_value_text);

        logoutButton = findViewById(R.id.admin_logout_button);


        sellersRecycler.setHasFixedSize(true);
        sellersAdapter = new SellersAdapter(allUsers);
        sellersRecycler.setAdapter(sellersAdapter);
        sellersRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        productsRecycler.setHasFixedSize(true);
        productsAdapter = new ProductsAdapter(allProducts);
        productsRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        productsRecycler.setAdapter(productsAdapter);

        sellerCountText.setText(String.valueOf(allUsers.size()));
        setBestSellerValues();
        productsCountText.setText(String.valueOf(allProducts.size()));
        setTotalValue();

        sellersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandable(sellersRecycler, sellersCard);
            }
        });

        itemsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandable(productsRecycler, itemsCard);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("currentUser", "").apply();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });


    }

    private void setTotalValue() {
        double total = 0;
        for (ProductEntity product : allProducts){
            total+=Double.parseDouble(product.getItemRawPrice());
        }
        totalValueText.setText(String.valueOf(total));
    }

    private void setBestSellerValues() {
        int MAX = 0;
        UserEntity bestUser = null;
        
        for (UserEntity user : allUsers){
            if (user.getProducts()>=MAX){
                bestUser = user;
                MAX = user.getProducts();
            }
        }
        String bestUserNameStr = bestUser.getFirstName()+" "+bestUser.getLastName();
        String bestUserEmailStr = bestUser.getEmail();
        String bestUserProductsStr = "Products On Sale: "+bestUser.getProducts();
        String bestUserLoginsStr = "Logins: "+bestUser.getLogins();

        bestSellerName.setText(bestUserNameStr);
        bestSellerEmail.setText(bestUserEmailStr);
        bestSellerProducts.setText(bestUserProductsStr);
        bestSellerLogins.setText(bestUserLoginsStr);


    }

    private void toggleExpandable(RecyclerView recyclerView, MaterialCardView card) {
        if (recyclerView.getVisibility() == View.VISIBLE) {
            TransitionManager.beginDelayedTransition(card, new ChangeBounds());
            recyclerView.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(card, new ChangeBounds());
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

}
