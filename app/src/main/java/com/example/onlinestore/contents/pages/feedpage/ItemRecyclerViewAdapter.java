package com.example.onlinestore.contents.pages.feedpage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.example.onlinestore.MainActivity;
import com.example.onlinestore.R;
import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {

    private List<ProductEntity> itemCardModelArrayList;
    private Context context;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout expandableLayout;
        public MaterialCardView itemTitleCard, callSellerCard;
        public ShapeableImageView itemProfileImage;
        public TextView itemTitleTop, itemId;
        public ImageView itemPicture;
        public TextView itemTitleBottom, itemDescription;
        public TextView itemSize, itemGender, itemCategory;
        public TextView itemCity;
        public TextView itemRawPrice, itemFinalPrice;
        public ImageView bookmarkButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemProfileImage = itemView.findViewById(R.id.feed_card_profile_picture);
            itemTitleTop = itemView.findViewById(R.id.feed_card_top_title_textview);
            itemId = itemView.findViewById(R.id.feed_card_post_id);
            itemPicture = itemView.findViewById(R.id.feed_card_main_item_picture);
            itemTitleBottom = itemView.findViewById(R.id.feed_card_bottom_title_textview);
            itemDescription = itemView.findViewById(R.id.feed_card_description_textview);
            itemSize = itemView.findViewById(R.id.feed_size_textview);
            itemGender = itemView.findViewById(R.id.feed_gender_textview);
            itemCategory = itemView.findViewById(R.id.feed_category_textview);
            itemCity = itemView.findViewById(R.id.feed_card_city_textview);
            itemRawPrice = itemView.findViewById(R.id.feed_card_price_raw_textview);
            itemFinalPrice = itemView.findViewById(R.id.feed_card_price_final_textview);
            itemTitleCard = itemView.findViewById(R.id.feed_title_card);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            bookmarkButton = itemView.findViewById(R.id.feed_card_bookmark_icon);
            callSellerCard = itemView.findViewById(R.id.feed_card_call);

        }
    }

    public ItemRecyclerViewAdapter(List<ProductEntity> itemCardModelArrayList, Context context) {
        this.itemCardModelArrayList = itemCardModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_items_card_layout, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ProductEntity instanceItemCard = itemCardModelArrayList.get(position);

        //preparation
        UserEntity seller = instanceItemCard.getSeller();

        Uri sellerPicture;
        Uri itemPicture;
        String itemId = "#" + instanceItemCard.getId();
        double discount = Double.parseDouble(instanceItemCard.getItemDiscount());
        double rawPrice = Double.parseDouble(instanceItemCard.getItemRawPrice());
        double finalPrice = (rawPrice * (100 - discount)) / 100;
        DecimalFormat priceFormat = new DecimalFormat("#.##");
        String finalPriceString = priceFormat.format(finalPrice);
        String sellerName = seller.getFirstName() + " " + seller.getLastName();

        if (seller.getProfilePicture()!=null) {
            sellerPicture = Uri.parse(seller.getProfilePicture());
            Glide.with(context).load(sellerPicture).into(holder.itemProfileImage);
        }
        holder.itemTitleTop.setText(sellerName);
        holder.itemId.setText(itemId);
        if (instanceItemCard.getItemPicture() == null) {
            holder.itemPicture.setVisibility(View.GONE);
        } else {
            itemPicture  = Uri.parse(instanceItemCard.getItemPicture());
            Glide.with(context).load(itemPicture).into(holder.itemPicture);
        }
        holder.itemTitleBottom.setText(instanceItemCard.getItemTitle());
        holder.itemDescription.setText(instanceItemCard.getItemDescription());
        holder.itemSize.setText(instanceItemCard.getItemSize());
        holder.itemCategory.setText(instanceItemCard.getItemCategory());
        holder.itemGender.setText(instanceItemCard.getItemGender());
        holder.itemCity.setText(instanceItemCard.getItemCity());
        if (discount==0){
            holder.itemRawPrice.setVisibility(View.INVISIBLE);
        }else {
            holder.itemRawPrice.setText(instanceItemCard.getItemRawPrice());
        }
        holder.itemFinalPrice.setText(finalPriceString);



        holder.itemTitleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandableCard(holder);
            }
        });

        //TODO: call seller
        holder.callSellerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //TODO: bookmark functionality -> need to update database
//        holder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.bookmarkButton.getTag().equals("unselected")){
//                    holder.bookmarkButton.setTag("selected");
//                    holder.bookmarkButton.setImageResource(R.drawable.ic_bookmark_selected);
//                }else{
//                    holder.bookmarkButton.setTag("unselected");
//                    holder.bookmarkButton.setImageResource(R.drawable.ic_bookmark);
//                }
//            }
//        });
    }


    private void toggleExpandableCard(ItemViewHolder holder) {
        if (holder.expandableLayout.getVisibility() == View.VISIBLE) {
            TransitionManager.beginDelayedTransition(holder.itemTitleCard, new AutoTransition());
            holder.expandableLayout.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.itemTitleCard, new AutoTransition());
            holder.expandableLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return itemCardModelArrayList.size();
    }
}
