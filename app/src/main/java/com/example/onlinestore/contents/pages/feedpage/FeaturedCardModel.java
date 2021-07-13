package com.example.onlinestore.contents.pages.feedpage;

public class FeaturedCardModel {

    private int itemPicture;
    private String itemTitle;
    private String itemDescription;
    private String itemRawPrice;
    private String itemFinalPrice;

    public FeaturedCardModel(int itemPicture, String itemTitle, String itemDescription, String itemRawPrice, String itemFinalPrice) {
        this.itemPicture = itemPicture;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemRawPrice = itemRawPrice;
        this.itemFinalPrice = itemFinalPrice;
    }

    public int getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(int itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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
