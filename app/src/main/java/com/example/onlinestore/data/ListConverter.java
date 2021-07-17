package com.example.onlinestore.data;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;


public class ListConverter {

    @TypeConverter
    public String listToString(List<ProductEntity> stringList){
        return new Gson().toJson(stringList);
    }

    @TypeConverter
    public List<ProductEntity> stringToList(String str){
        Type listOfStringsType = new TypeToken<List<ProductEntity>>(){}.getType();
        return new Gson().<List<ProductEntity>>fromJson(str, listOfStringsType);
    }
}
