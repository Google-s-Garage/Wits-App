package com.example.thewitsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccomAdapter extends RecyclerView.Adapter<AccomAdapter.myViewHolder>{

    Context mContext;
    List<accom_item> mData;

    public AccomAdapter(Context mContext, List<accom_item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.accom_card_item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.backgroundImage.setImageResource(mData.get(position).getBackground());
        holder.accomLogo.setImageResource(mData.get(position).getAccomLogo());
        holder.accom_name.setText(mData.get(position).getAccomName());
        holder.accom_price.setText("R" + mData.get(position).getAccomPrice());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView accomLogo,backgroundImage;
        TextView accom_name,accom_price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            accomLogo = itemView.findViewById(R.id.accom_profile_img);
            backgroundImage = itemView.findViewById(R.id.accom_card_bground);
            accom_name = itemView.findViewById(R.id.accom_card_name);
            accom_price = itemView.findViewById(R.id.accom_card_price);
        }
    }
}