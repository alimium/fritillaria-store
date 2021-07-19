package com.example.onlinestore.contents.pages.feedpage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.example.onlinestore.R;
import com.example.onlinestore.data.ProductEntity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.DecimalFormat;


public class FeaturedBottomSheet extends BottomSheetDialogFragment {

    public ProductEntity clickedFeaturedCard;
    public ConstraintLayout expandableLayout;
    public MaterialCardView itemTitleCard, itemMainCard, callSellerCard;
    public ShapeableImageView itemProfileImage;
    public TextView itemTitleTop, itemId;
    public ImageView itemPicture;
    public TextView itemTitleBottom, itemDescription;
    public TextView itemSize, itemGender, itemCategory;
    public TextView itemCity;
    public TextView itemRawPrice, itemFinalPrice;
    public ImageView bookmarkButton;
    public Context context;


    public FeaturedBottomSheet(ProductEntity instanceFeaturedItemCard, Context context) {
        clickedFeaturedCard = instanceFeaturedItemCard;
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.feed_featured_bottom_sheet_layout, container, false);

        itemProfileImage = v.findViewById(R.id.feed_featured_sheet_profile_picture);
        itemTitleTop = v.findViewById(R.id.feed_featured_sheet_top_title_textview);
        itemId = v.findViewById(R.id.feed_featured_sheet_post_id);
        itemPicture = v.findViewById(R.id.feed_featured_sheet_main_item_picture);
        itemTitleBottom = v.findViewById(R.id.feed_featured_sheet_bottom_title_textview);
        itemDescription = v.findViewById(R.id.feed_featured_sheet_description_textview);
        itemSize = v.findViewById(R.id.feed_featured_sheet_size_textview);
        itemGender = v.findViewById(R.id.feed_featured_sheet_gender_textview);
        itemCategory = v.findViewById(R.id.feed_featured_sheet_category_textview);
        itemCity = v.findViewById(R.id.feed_featured_sheet_city_textview);
        itemRawPrice = v.findViewById(R.id.feed_featured_sheet_price_raw_textview);
        itemFinalPrice = v.findViewById(R.id.feed_featured_sheet_price_final_textview);
        itemTitleCard = v.findViewById(R.id.feed_featured_sheet_title_card);
        expandableLayout = v.findViewById(R.id.expandable_featured_sheet_layout);
        bookmarkButton = v.findViewById(R.id.feed_featured_sheet_bookmark_icon);
        itemMainCard = v.findViewById(R.id.feed_featured_sheet_card);
        callSellerCard = v.findViewById(R.id.feed_featured_sheet_call);

        String itemId = "#" + clickedFeaturedCard.getId();
        String seller = clickedFeaturedCard.getSeller().getFirstName()+" "+clickedFeaturedCard.getSeller().getLastName();
        double discount = Double.parseDouble(clickedFeaturedCard.getItemDiscount());
        double rawPrice = Double.parseDouble(clickedFeaturedCard.getItemRawPrice());
        double finalPrice = (rawPrice * (100 - discount)) / 100;
        DecimalFormat priceFormat = new DecimalFormat("#.##");
        String finalPriceString = priceFormat.format(finalPrice);

        if (clickedFeaturedCard.getSeller().getProfilePicture()!=null) {
            Uri sellerPicture = Uri.parse(clickedFeaturedCard.getSeller().getProfilePicture());
            Glide.with(context).load(sellerPicture).into(this.itemProfileImage);
        }
        itemTitleTop.setText(seller);
        this.itemId.setText(itemId);
        if (clickedFeaturedCard.getItemPicture() == null) {
            this.itemPicture.setVisibility(View.GONE);
        } else {
            Uri itemPicture  = Uri.parse(clickedFeaturedCard.getItemPicture());
            Glide.with(context).load(itemPicture).into(this.itemPicture);
        }
        itemTitleBottom.setText(clickedFeaturedCard.getItemTitle());
        itemDescription.setText(clickedFeaturedCard.getItemDescription());
        itemSize.setText(clickedFeaturedCard.getItemSize());
        itemCategory.setText(clickedFeaturedCard.getItemCategory());
        itemGender.setText(clickedFeaturedCard.getItemGender());
        itemCity.setText(clickedFeaturedCard.getItemCity());
        if (discount==0){
            itemRawPrice.setVisibility(View.INVISIBLE);
        }else {
            itemRawPrice.setText(clickedFeaturedCard.getItemRawPrice());
        }

        itemFinalPrice.setText(finalPriceString);


        itemTitleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandableCard();
            }
        });


        //TODO: call seller
        callSellerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //TODO: bookmark functionality -> need to update database
//        bookmarkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clickedFeaturedCard.isBookmarked()){
//                    bookmarkButton.setImageResource(R.drawable.ic_bookmark);
//                    clickedFeaturedCard.setBookmarked(false);
//                }else{
//                    bookmarkButton.setImageResource(R.drawable.ic_bookmark_selected);
//                    clickedFeaturedCard.setBookmarked(true);
//
//                }
//            }
//        });


        return v;
    }


    private void toggleExpandableCard() {
        if (expandableLayout.getVisibility() == View.VISIBLE) {
//            TransitionManager.beginDelayedTransition(itemMainCard, new AutoTransition());
            expandableLayout.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(itemMainCard, new AutoTransition());
            expandableLayout.setVisibility(View.VISIBLE);
        }
    }
}
