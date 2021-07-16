package com.example.onlinestore.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(ProductEntity product);

    @Delete
    void delete(ProductEntity product);

    @Update
    void update(ProductEntity product);

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> getAllProducts();


}
