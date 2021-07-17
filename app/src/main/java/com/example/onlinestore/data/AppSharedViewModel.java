package com.example.onlinestore.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppSharedViewModel extends AndroidViewModel {

    private Repository dataRepository;
    private final LiveData<List<UserEntity>> allUsersData;
    private final LiveData<List<ProductEntity>> allProductsData;



    public AppSharedViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new Repository(application);
        allUsersData = dataRepository.getAllUsers();
        allProductsData = dataRepository.getAllProducts();
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsersData;
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProductsData;
    }

    public void insertUser(UserEntity user) {
        dataRepository.insertUser(user);
    }

    public void deleteUser(UserEntity user) {
        dataRepository.deleteUser(user);
    }

    public void updateUser(UserEntity user) {
        dataRepository.updateUser(user);
    }

    public void insertProduct(ProductEntity product) {
        dataRepository.insertProduct(product);
    }

    public void deleteProduct(ProductEntity product) {
        dataRepository.deleteProduct(product);
    }

    public void updateProduct(ProductEntity product) {
        dataRepository.updateProduct(product);
    }


}