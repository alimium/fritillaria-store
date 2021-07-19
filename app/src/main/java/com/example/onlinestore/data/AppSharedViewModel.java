package com.example.onlinestore.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

public class AppSharedViewModel extends AndroidViewModel {

    private final Repository dataRepository;
    private final LiveData<List<UserEntity>> allUsersData;
    private final LiveData<List<ProductEntity>> allProductsData;

    LiveData<List<ProductEntity>> allItemProducts;
    LiveData<List<ProductEntity>> allFeaturedProducts;
    LiveData<List<ProductEntity>> allCurrentUserProducts;
    LiveData<UserEntity> currentUser;
    UserEntity singleUser;

    public AppSharedViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new Repository(application);
        allUsersData = dataRepository.getAllUsers();
        allProductsData = dataRepository.getAllProducts();
        allItemProducts = Transformations.switchMap(allProductsData, new Function<List<ProductEntity>, LiveData<List<ProductEntity>>>() {
            @Override
            public LiveData<List<ProductEntity>> apply(List<ProductEntity> input) {
                MutableLiveData<List<ProductEntity>> itemsToReturn = new MutableLiveData<>();
                List<ProductEntity> listOfItems = new ArrayList<>();
                for (ProductEntity item : input) {
                    if (item.getIsFeatured() == 0) {
                        listOfItems.add(item);
                    }
                }
                itemsToReturn.postValue(listOfItems);
                return itemsToReturn;
            }
        });
        allFeaturedProducts = Transformations.switchMap(allProductsData, new Function<List<ProductEntity>, LiveData<List<ProductEntity>>>() {
            @Override
            public LiveData<List<ProductEntity>> apply(List<ProductEntity> input) {
                MutableLiveData<List<ProductEntity>> itemsToReturn = new MutableLiveData<>();
                List<ProductEntity> listOfItems = new ArrayList<>();
                for (ProductEntity item : input) {
                    if (item.getIsFeatured() == 1) {
                        listOfItems.add(item);
                    }
                }
                itemsToReturn.postValue(listOfItems);
                return itemsToReturn;
            }
        });
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsersData;
    }
    public LiveData<List<ProductEntity>> getAllProducts() {return allProductsData;}


    public LiveData<List<ProductEntity>> getAllItemProducts() {
        return allItemProducts;
    }
    public LiveData<List<ProductEntity>> getAllFeaturedProducts() {
        return allFeaturedProducts;
    }

    public LiveData<UserEntity> getCurrentUser(String email){
        currentUser = Transformations.switchMap(allUsersData, new Function<List<UserEntity>, LiveData<UserEntity>>() {
            @Override
            public LiveData<UserEntity> apply(List<UserEntity> input) {
                UserEntity currentUser = allUsersData.getValue().get(0);
                MutableLiveData<UserEntity> userToReturn = new MutableLiveData<>();
                for (UserEntity user : input){
                    if (user.getEmail().equals(email)){
                        currentUser = user;
                        break;
                    }
                }
                userToReturn.postValue(currentUser);
                return userToReturn;
            }
        });
        return currentUser;
    }

    public UserEntity getSingleUser(String email){
        singleUser = dataRepository.getSingleUser(email);
        return singleUser;
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