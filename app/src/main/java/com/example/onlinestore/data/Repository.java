package com.example.onlinestore.data;

import android.app.Application;
import android.provider.Contacts;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private DataAccessObject dao;
    private LiveData<List<UserEntity>> allUsers;
    private LiveData<List<ProductEntity>> allProducts;
    private UserEntity singleUser;

    Repository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        dao = db.dao();
        allUsers = dao.getAllUsers();
        allProducts = dao.getAllProducts();
    }

    public UserEntity getSingleUser(String email){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                singleUser = dao.getSingleUser(email);
            }
        });
        return singleUser;
    }


    public void insertUser(UserEntity user) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertUser(user);
            }
        });
    }

    public void deleteUser(UserEntity user) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteUser(user);
            }
        });
    }

    public void updateUser(UserEntity user) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateUser(user);
            }
        });
    }

    public void insertProduct(ProductEntity product) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertProduct(product);
            }
        });
    }

    public void deleteProduct(ProductEntity product) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteProduct(product);
            }
        });
    }

    public void updateProduct(ProductEntity product) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateProduct(product);
            }
        });
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProducts;
    }

}