package com.example.onlinestore.contents.pages.feedpage;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.onlinestore.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;


public class FeaturedBottomSheet extends BottomSheetDialogFragment {

    public ItemCardModel clickedFeaturedCard;
    public ConstraintLayout expandableLayout;
    public MaterialCardView itemTitleCard, itemMainCard;
    public ShapeableImageView itemProfileImage;
    public TextView itemTitleTop, itemId;
    public ImageView itemPicture;
    public TextView itemTitleBottom, itemDescription;
    public TextView itemSize, itemGender, itemCategory;
    public TextView itemCity;
    public TextView itemRawPrice, itemFinalPrice;
    public ImageView bookmarkButton;




    public FeaturedBottomSheet(ItemCardModel instanceFeaturedItemCard) {
        clickedFeaturedCard = instanceFeaturedItemCard;
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

        itemProfileImage.setImageResource(clickedFeaturedCard.getProfileImage());
        itemTitleTop.setText(clickedFeaturedCard.getItemTitle());
        itemId.setText(clickedFeaturedCard.getItemId());
        itemPicture.setImageResource(clickedFeaturedCard.getItemPicture());
        itemTitleBottom.setText(clickedFeaturedCard.getItemTitle());
        itemDescription.setText(clickedFeaturedCard.getItemDescription());
        itemSize.setText(clickedFeaturedCard.getItemSize());
        itemGender.setText(clickedFeaturedCard.getItemGender());
        itemCity.setText(clickedFeaturedCard.getItemCity());
        itemRawPrice.setText(clickedFeaturedCard.getItemRawPrice());
        itemFinalPrice.setText(clickedFeaturedCard.getItemFinalPrice());


        itemTitleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandableCard();
            }
        });


        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedFeaturedCard.isBookmarked()){
                    bookmarkButton.setImageResource(R.drawable.ic_bookmark);
                    clickedFeaturedCard.setBookmarked(false);
                }else{
                    bookmarkButton.setImageResource(R.drawable.ic_bookmark_selected);
                    clickedFeaturedCard.setBookmarked(true);

                }
            }
        });


        return v;
    }


    private void toggleExpandableCard() {
        if (expandableLayout.getVisibility()==View.VISIBLE){
//            TransitionManager.beginDelayedTransition(itemMainCard, new AutoTransition());
            expandableLayout.setVisibility(View.GONE);
        }else {
            TransitionManager.beginDelayedTransition(itemMainCard, new AutoTransition());
            expandableLayout.setVisibility(View.VISIBLE);
        }
    }
}
