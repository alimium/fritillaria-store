package com.example.onlinestore.contents.pages.feedpage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinestore.R;
import com.example.onlinestore.data.ProductEntity;
import com.google.android.material.card.MaterialCardView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FeaturedRecyclerViewAdapter extends RecyclerView.Adapter<FeaturedRecyclerViewAdapter.FeaturedViewHolder> {

    private List<ProductEntity> featuredCardModelArrayList;
    FragmentManager fragmentManager;
    Context context;

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        public MaterialCardView itemFeaturedCard;
        public TextView itemTitleTop;
        public ImageView itemPicture;
        public TextView itemDescription;
        public TextView itemRawPrice, itemFinalPrice;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPicture = itemView.findViewById(R.id.feed_featured_card_picture);
            itemTitleTop = itemView.findViewById(R.id.feed_featured_title_textview);
            itemDescription = itemView.findViewById(R.id.feed_card_featured_description_textview);
            itemRawPrice = itemView.findViewById(R.id.feed_card_featured_raw_price_textview);
            itemFinalPrice = itemView.findViewById(R.id.feed_card_featured_final_price_textview);
            itemFeaturedCard = itemView.findViewById(R.id.feed_featured_main_card);
        }
    }


    //adapter constructor
    public FeaturedRecyclerViewAdapter(List<ProductEntity> featuredCardModels, FragmentManager fragmentManager, Context context) {
        this.featuredCardModelArrayList = featuredCardModels;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_featured_card_layout, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(v);
        return featuredViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        ProductEntity instanceFeaturedItemCard = featuredCardModelArrayList.get(position);
        Uri itemPicture;
        String itemId = "#" + instanceFeaturedItemCard.getId();
        double discount = Double.parseDouble(instanceFeaturedItemCard.getItemDiscount());
        double rawPrice = Double.parseDouble(instanceFeaturedItemCard.getItemRawPrice());
        double finalPrice = (rawPrice * (100 - discount)) / 100;
        DecimalFormat priceFormat = new DecimalFormat("#.##");
        String finalPriceString = priceFormat.format(finalPrice);

        if (instanceFeaturedItemCard.getItemPicture() != null) {
            itemPicture  = Uri.parse(instanceFeaturedItemCard.getItemPicture());
            Glide.with(context).load(itemPicture).into(holder.itemPicture);
        }
        holder.itemTitleTop.setText(instanceFeaturedItemCard.getItemTitle());
        holder.itemDescription.setText(instanceFeaturedItemCard.getItemDescription());
        if (discount==0){
            holder.itemRawPrice.setVisibility(View.INVISIBLE);
        }else {
            holder.itemRawPrice.setText(instanceFeaturedItemCard.getItemRawPrice());
        }
        holder.itemFinalPrice.setText(finalPriceString);

        holder.itemFeaturedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeaturedBottomSheet featuredBottomSheet = new FeaturedBottomSheet(instanceFeaturedItemCard, v.getContext());
                featuredBottomSheet.show(fragmentManager, "clickedFeaturedItem");
            }
        });
    }


    @Override
    public int getItemCount() {
        return featuredCardModelArrayList.size();
    }
}
