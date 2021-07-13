package com.example.onlinestore.contents.pages.feedpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class FeaturedRecyclerViewAdapter extends RecyclerView.Adapter<FeaturedRecyclerViewAdapter.FeaturedViewHolder> {

    private ArrayList<FeaturedCardModel> featuredCardModelArrayList;


    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        public ShapeableImageView itemPicture;
        public MaterialTextView itemTitle,itemDescription,itemRawPrice,itemFinalPrice;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPicture = itemView.findViewById(R.id.feed_featured_card_picture);
            itemTitle = itemView.findViewById(R.id.feed_featured_title_textview);
            itemDescription = itemView.findViewById(R.id.feed_card_featured_description_textview);
            itemRawPrice = itemView.findViewById(R.id.feed_card_featured_raw_price_textview);
            itemFinalPrice = itemView.findViewById(R.id.feed_card_featured_final_price_textview);
        }
    }

    //adapter constructor
    public FeaturedRecyclerViewAdapter (ArrayList<FeaturedCardModel> featuredCardModels) {this.featuredCardModelArrayList = featuredCardModels;}

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_featured_card_layout, parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(v);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedCardModel instanceItemCard = featuredCardModelArrayList.get(position);

        holder.itemPicture.setImageResource(instanceItemCard.getItemPicture());
        holder.itemTitle.setText(instanceItemCard.getItemTitle());
        holder.itemDescription.setText(instanceItemCard.getItemDescription());
        holder.itemRawPrice.setText(instanceItemCard.getItemRawPrice());
        holder.itemFinalPrice.setText(instanceItemCard.getItemFinalPrice());
    }

    @Override
    public int getItemCount() {
        return featuredCardModelArrayList.size();
    }


}
