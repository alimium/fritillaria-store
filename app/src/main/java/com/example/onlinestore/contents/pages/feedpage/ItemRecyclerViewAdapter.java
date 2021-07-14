package com.example.onlinestore.contents.pages.feedpage;

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

import com.example.onlinestore.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {

    private ArrayList<ItemCardModel> itemCardModelArrayList;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout expandableLayout;
        public MaterialCardView itemCard;
        public ShapeableImageView itemProfileImage;
        public TextView itemTitleTop, itemId;
        public ImageView itemPicture;
        public TextView itemTitleBottom, itemDescription;
        public TextView itemSize, itemGender, itemCategory;
        public TextView itemCity;
        public TextView itemRawPrice, itemFinalPrice;

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
            itemCard = itemView.findViewById(R.id.feed_main_item_card);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);


        }
    }

    public ItemRecyclerViewAdapter(ArrayList<ItemCardModel> itemCardModelArrayList) {
        this.itemCardModelArrayList = itemCardModelArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_items_card_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(v);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemCardModel instanceItemCard = itemCardModelArrayList.get(position);

        holder.itemProfileImage.setImageResource(instanceItemCard.getProfileImage());
        holder.itemTitleTop.setText(instanceItemCard.getItemTitle());
        holder.itemId.setText(instanceItemCard.getItemId());
        holder.itemPicture.setImageResource(instanceItemCard.getItemPicture());
        holder.itemTitleBottom.setText(instanceItemCard.getItemTitle());
        holder.itemDescription.setText(instanceItemCard.getItemDescription());
        holder.itemSize.setText(instanceItemCard.getItemSize());
        holder.itemGender.setText(instanceItemCard.getItemGender());
        holder.itemCity.setText(instanceItemCard.getItemCity());
        holder.itemRawPrice.setText(instanceItemCard.getItemRawPrice());
        holder.itemFinalPrice.setText(instanceItemCard.getItemFinalPrice());


        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandableCard(holder);
            }
        });

    }

    private void toggleExpandableCard(ItemViewHolder holder) {
        if (holder.expandableLayout.getVisibility()==View.VISIBLE){
//            TransitionManager.beginDelayedTransition(holder.itemCard);
            holder.expandableLayout.setVisibility(View.GONE);
        }else {
            TransitionManager.beginDelayedTransition(holder.itemCard);
            holder.expandableLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return itemCardModelArrayList.size();
    }

}
