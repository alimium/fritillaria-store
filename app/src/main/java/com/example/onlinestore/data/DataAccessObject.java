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

    @Query("SELECT * FROM products WHERE product_seller = :seller")
    public LiveData<List<ProductEntity>> getCurrentUserProducts(UserEntity seller);

    @Query("SELECT * FROM users WHERE email LIKE :email")
    UserEntity getSingleUser(String email);






    @Insert
    void insertProduct(ProductEntity product);

    @Delete
    void deleteProduct(ProductEntity product);

    @Update
    void updateProduct(ProductEntity product);

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> getAllProducts();

    @Query("SELECT * FROM products WHERE product_title LIKE :query OR product_description LIKE :query OR product_category LIKE :query OR product_city LIKE :query")
    List<ProductEntity> productsMatchingSearchQuery(String query);





    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("DELETE FROM products")
    void deleteAllProducts();
}