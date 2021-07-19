package com.example.onlinestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.data.UserEntity;

import java.util.List;

public class SellersAdapter extends RecyclerView.Adapter<SellersAdapter.SellersViewHolder> {

    private List<UserEntity> allUsers;

    public static class SellersViewHolder extends RecyclerView.ViewHolder {

        public TextView sellerName, sellerEmail, sellerProducts, sellerLogins;


        public SellersViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerName = itemView.findViewById(R.id.seller_name);
            sellerEmail = itemView.findViewById(R.id.seller_email);
            sellerLogins = itemView.findViewById(R.id.seller_login_count);
            sellerProducts = itemView.findViewById(R.id.seller_product_count);
        }
    }

    public SellersAdapter(List<UserEntity> allUsers) {
        this.allUsers = allUsers;
    }

    @NonNull
    @Override
    public SellersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_sellers_template, parent, false);
        return new SellersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SellersViewHolder holder, int position) {
        UserEntity instanceOfUser = allUsers.get(position);

        String sellerNameStr = instanceOfUser.getFirstName()+" "+instanceOfUser.getLastName();
        String sellerEmailStr = instanceOfUser.getEmail();
        String sellerProductCountStr = "Products On Sale: "+String.valueOf(instanceOfUser.getProducts());
        String sellerLoginCountStr = "Logins: "+String.valueOf(instanceOfUser.getLogins());

        holder.sellerName.setText(sellerNameStr);
        holder.sellerEmail.setText(sellerEmailStr);
        holder.sellerProducts.setText(sellerProductCountStr);
        holder.sellerLogins.setText(sellerLoginCountStr);
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }



}
