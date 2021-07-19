package com.example.onlinestore.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class UserConverter {

    @TypeConverter
    public String UsertoString (UserEntity user){
        return new Gson().toJson(user);
    }

    @TypeConverter
    public UserEntity StringToUser(String json){
        return new Gson().fromJson(json, UserEntity.class);
    }
}
