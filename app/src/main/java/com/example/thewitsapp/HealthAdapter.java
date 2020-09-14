package com.example.thewitsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelFood> mList;
    HealthAdapter(Context context, ArrayList<ModelFood> list){
        mContext = context;
        mList = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_food_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        ModelFood foodItem = mList.get(position);

        ImageView image = holder.item_image;
        TextView name,place,price,ingredients;

        name = holder.item_name;
        place = holder.item_place;
        price = holder.item_price;
        ingredients = holder.item_ingredients;

        Glide.with(holder.cardView)
                .load(foodItem.getImage())
                .into(image);

        //image.setImageResource(foodItem.getImage());

        name.setText(foodItem.getName());
        place.setText(foodItem.getPlace());
        price.setText(foodItem.getPrice());
        ingredients.setText(foodItem.getIngredients());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,Recipe_activity.class);

                //passing data to the recipe activity

                intent.putExtra("Dish_Name", mList.get(position).getName());
                intent.putExtra("Instructions", mList.get(position).getRecipe());
                intent.putExtra("foodthumbnail", mList.get(position).getImage());

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView item_image;
        TextView item_name, item_place, item_price, item_ingredients;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);

            item_name = itemView.findViewById(R.id.item_name);
            item_place = itemView.findViewById(R.id.item_place);
            item_price = itemView.findViewById(R.id.item_price);
            item_ingredients = itemView.findViewById(R.id.item_ingredients);

            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }
}
