package com.example.onlinestore.contents.pages.feedpage;

public class ItemCardModel {
    private boolean isBookmarked;
    private int profileImage, itemPicture;
    private String itemId;
    private String itemTitle;
    private String itemDescription;
    private String itemSize, itemGender, itemCategory;
    private String itemCity;
    private String itemRawPrice, itemFinalPrice;

    public ItemCardModel(int profileImage, int itemPicture, String itemId, String itemTitle,
                         String itemDescription, String itemSize,
                         String itemGender, String itemCategory, String itemCity,
                         String itemRawPrice, String itemFinalPrice, boolean isBookmarked) {

        this.profileImage = profileImage;
        this.itemPicture = itemPicture;
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemSize = itemSize;
        this.itemGender = itemGender;
        this.itemCategory = itemCategory;
        this.itemCity = itemCity;
        this.itemRawPrice = itemRawPrice;
        this.itemFinalPrice = itemFinalPrice;
        this.isBookmarked = isBookmarked;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public int getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(int itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitleTop) {
        this.itemTitle = itemTitleTop;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemGender() {
        return itemGender;
    }

    public void setItemGender(String itemGender) {
        this.itemGender = itemGender;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemCity() {
        return itemCity;
    }

    public void setItemCity(String itemCity) {
        this.itemCity = itemCity;
    }

    public String getItemRawPrice() {
        return itemRawPrice;
    }

    public void setItemRawPrice(String itemRawPrice) {
        this.itemRawPrice = itemRawPrice;
    }

    public String getItemFinalPrice() {
        return itemFinalPrice;
    }

    public void setItemFinalPrice(String itemFinalPrice) {
        this.itemFinalPrice = itemFinalPrice;
    }
}
