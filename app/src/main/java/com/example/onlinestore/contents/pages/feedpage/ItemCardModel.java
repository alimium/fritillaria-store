package com.example.onlinestore.contents.pages.feedpage;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemCardModel implements Parcelable {
    private boolean isBookmarked;
    private int profileImage, itemPicture;
    private String itemId;
    private String itemTitle;
    private String itemDescription;
    private String itemSize, itemGender, itemCategory;
    private String itemCity;
    private String itemRawPrice, itemDiscount;

    public ItemCardModel(int profileImage, int itemPicture, String itemId, String itemTitle,
                         String itemDescription, String itemSize,
                         String itemGender, String itemCategory, String itemCity,
                         String itemRawPrice, String itemDiscount, boolean isBookmarked) {

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
        this.itemDiscount = itemDiscount;
        this.isBookmarked = isBookmarked;
    }

    protected ItemCardModel(Parcel in) {
        isBookmarked = in.readByte() != 0;
        profileImage = in.readInt();
        itemPicture = in.readInt();
        itemId = in.readString();
        itemTitle = in.readString();
        itemDescription = in.readString();
        itemSize = in.readString();
        itemGender = in.readString();
        itemCategory = in.readString();
        itemCity = in.readString();
        itemRawPrice = in.readString();
        itemDiscount = in.readString();
    }

    public static final Creator<ItemCardModel> CREATOR = new Creator<ItemCardModel>() {
        @Override
        public ItemCardModel createFromParcel(Parcel in) {
            return new ItemCardModel(in);
        }

        @Override
        public ItemCardModel[] newArray(int size) {
            return new ItemCardModel[size];
        }
    };

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

    public String getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isBookmarked ? 1 : 0));
        dest.writeInt(profileImage);
        dest.writeInt(itemPicture);
        dest.writeString(itemId);
        dest.writeString(itemTitle);
        dest.writeString(itemDescription);
        dest.writeString(itemSize);
        dest.writeString(itemGender);
        dest.writeString(itemCategory);
        dest.writeString(itemCity);
        dest.writeString(itemRawPrice);
        dest.writeString(itemDiscount);
    }
}
