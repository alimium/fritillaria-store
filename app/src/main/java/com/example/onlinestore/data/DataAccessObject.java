package com.example.onlinestore.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataAccessObject {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAllUsers();

    @Insert
    void insertProduct(ProductEntity product);

    @Delete
    void deleteProduct(ProductEntity product);

    @Update
    void updateProduct(ProductEntity product);

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> getAllProducts();
}