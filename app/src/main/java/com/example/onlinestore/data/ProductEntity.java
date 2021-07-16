package com.example.onlinestore.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "products")
public class ProductEntity {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "productId")
    private int id;

    @Nullable
    @ColumnInfo(name = "product_picture")
    private String itemPicture;

    @NonNull
    @ColumnInfo(name = "product_picture")
    private String itemTitle;

    @NonNull
    @ColumnInfo(name = "product_description")
    private String itemDescription;

    @NonNull
    @ColumnInfo(name = "product_raw_price")
    private String itemRawPrice;

    @Nullable
    @ColumnInfo(name = "product_discount", defaultValue = "0")
    private String itemDiscount;

    @NonNull
    @ColumnInfo(name = "product_category")
    private String itemCategory;

    @NonNull
    @ColumnInfo(name = "product_gender")
    private String itemGender;

    @NonNull
    @ColumnInfo(name = "product_size")
    private String itemSize;

    @NonNull
    @ColumnInfo(name = "product_city")
    private String itemCity;

    @NonNull
    @ColumnInfo(name = "product_seller")
    private int sellerId;

    @NonNull
    @ColumnInfo(name = "is_featured", defaultValue = "false")
    private String isFeatured;

    @Nullable
    @ColumnInfo(name = "bookmarks")
    private List<Integer> bookmarks;


    public ProductEntity(int id, @Nullable String itemPicture, @NonNull String itemTitle,
                         @NonNull String itemDescription, @NonNull String itemRawPrice,
                         @Nullable String itemDiscount, @NonNull String itemCategory,
                         @NonNull String itemGender, @NonNull String itemSize,
                         @NonNull String itemCity, int sellerId, @NonNull String isFeatured,
                         @Nullable List<Integer> bookmarks) {
        this.id = id;
        this.itemPicture = itemPicture;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemRawPrice = itemRawPrice;
        this.itemDiscount = itemDiscount;
        this.itemCategory = itemCategory;
        this.itemGender = itemGender;
        this.itemSize = itemSize;
        this.itemCity = itemCity;
        this.sellerId = sellerId;
        this.isFeatured = isFeatured;
        this.bookmarks = bookmarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(@Nullable String itemPicture) {
        this.itemPicture = itemPicture;
    }

    @NonNull
    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(@NonNull String itemTitle) {
        this.itemTitle = itemTitle;
    }

    @NonNull
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(@NonNull String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @NonNull
    public String getItemRawPrice() {
        return itemRawPrice;
    }

    public void setItemRawPrice(@NonNull String itemRawPrice) {
        this.itemRawPrice = itemRawPrice;
    }

    @Nullable
    public String getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(@Nullable String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    @NonNull
    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(@NonNull String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @NonNull
    public String getItemGender() {
        return itemGender;
    }

    public void setItemGender(@NonNull String itemGender) {
        this.itemGender = itemGender;
    }

    @NonNull
    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(@NonNull String itemSize) {
        this.itemSize = itemSize;
    }

    @NonNull
    public String getItemCity() {
        return itemCity;
    }

    public void setItemCity(@NonNull String itemCity) {
        this.itemCity = itemCity;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @NonNull
    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(@NonNull String isFeatured) {
        this.isFeatured = isFeatured;
    }

    @Nullable
    public List<Integer> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(@Nullable List<Integer> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
