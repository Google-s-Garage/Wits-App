package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Recipe_activity extends AppCompatActivity {

    private TextView foodtitle,foodDescription;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_activity);

        foodtitle = findViewById(R.id.dish_nameTxtView);
        foodDescription =  findViewById(R.id.recipeTxtView);
        img =  findViewById(R.id.foodthumbnail);

        //receive the data we need to display
        Intent intent = getIntent();
        String Food_name = intent.getExtras().getString("Dish_Name");
        String Recipe = intent.getExtras().getString("Instructions");
        String thumbnail = intent.getExtras().getString("foodthumbnail");

        //setting values

        foodtitle.setText(Food_name);
        foodDescription.setText(Recipe);

        Glide.with(Recipe_activity.this)
                .load(thumbnail)
                .into(img);
    }
}