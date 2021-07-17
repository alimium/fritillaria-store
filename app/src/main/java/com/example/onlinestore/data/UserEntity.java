package com.example.onlinestore.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;

    @Nullable
    @ColumnInfo(name = "profile_picture")
    private String profilePicture;

    @Nullable
    @ColumnInfo(name = "bookmarks")
    private List<ProductEntity> bookmarks;


    public UserEntity(@NonNull String email, @NonNull String password, @NonNull String phone,
                      @NonNull String firstName, @NonNull String lastName,
                      @Nullable String profilePicture, @Nullable List<ProductEntity> bookmarks) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.bookmarks = bookmarks;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(@Nullable String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Nullable
    public List<ProductEntity> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(@Nullable List<ProductEntity> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
