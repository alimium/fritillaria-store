package com.example.onlinestore.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private DataAccessObject dao;
    private LiveData<List<UserEntity>> allUsers;
    private LiveData<List<ProductEntity>> allProducts;
    private UserEntity singleUser;
    private List<ProductEntity> searchResults = new ArrayList<>();
    Repository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        dao = db.dao();
        allUsers = dao.getAllUsers();
        allProducts = dao.getAllProducts();
    }

    public UserEntity getSingleUser(String email) {
        AppDatabase.databaseWriteExecutor.execute(() -> singleUser = dao.getSingleUser(email));
        return singleUser;
    }


    public void insertUser(UserEntity user) {
        AppDatabase.databaseWriteExecutor.execute(() -> dao.insertUser(user));
    }

    public void deleteUser(UserEntity user) {
        AppDatabase.databaseWriteExecutor.execute(() -> dao.deleteUser(user));
    }

    public void updateUser(UserEntity user) {
        AppDatabase.databaseWriteExecutor.execute(() -> dao.updateUser(user));
    }

    public void insertProduct(ProductEntity product) {
        AppDatabase.databaseWriteExecutor.execute(() -> dao.insertProduct(product));
    }

    public void deleteProduct(ProductEntity product) {
        AppDatabase.databaseWriteExecutor.execute(() -> dao.deleteProduct(product));
    }

    public void updateProduct(ProductEntity product) {
        AppDatabase.databaseWriteExecutor.execute(() -> dao.updateProduct(product));
    }

    public List<ProductEntity> searchProducts(String query) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                searchResults = dao.productsMatchingSearchQuery(query);
            }
        });
        return searchResults;
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProducts;
    }

}