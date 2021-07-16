package com.example.onlinestore.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class OneToManyUserProduct {
    @Embedded public UserEntity user;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "product_seller"
    )
    public List<ProductEntity> products;
}
