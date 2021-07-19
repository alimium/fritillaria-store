package com.example.onlinestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.data.ProductEntity;
import com.example.onlinestore.data.UserEntity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<ProductEntity> allProducts;

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {

        public TextView productTitle, productId, sellerEmail;


        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.item_title);
            productId = itemView.findViewById(R.id.item_id);
            sellerEmail = itemView.findViewById(R.id.item_seller_username);
        }
    }

    public ProductsAdapter(List<ProductEntity> allProducts) {
        this.allProducts = allProducts;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_items_template, parent, false);
        return new ProductsAdapter.ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductsViewHolder holder, int position) {
        ProductEntity instanceOfProduct = allProducts.get(position);

        String productItemTitleStr = instanceOfProduct.getItemTitle();
        String productIdStr = "Product ID: #"+String.valueOf(instanceOfProduct.getId());
        String sellerEmailStr = "Seller Username: "+instanceOfProduct.getSeller().getEmail();

        holder.productTitle.setText(productItemTitleStr);
        holder.productId.setText(productIdStr);
        holder.sellerEmail.setText(sellerEmailStr);

    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }


}
