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
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Update
    void update(UserEntity user);

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAllUsers();

}
