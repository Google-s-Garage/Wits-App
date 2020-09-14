package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class beforeHealth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_health);

        CardView rest, recipe, foods, tips;

        FloatingActionButton floatingActionButton = findViewById(R.id.healthadd);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //There we go!!
                startActivity(new Intent(beforeHealth.this, PostOnHealth.class));
            }
        });

        rest = findViewById(R.id.restCard);
        recipe = findViewById(R.id.recipeCard);
        foods = findViewById(R.id.foodsCard);
        tips = findViewById(R.id.tipsCard);

        actionDoer(rest,"Rest");
        actionDoer(recipe,"Recipes");
        actionDoer(foods,"Food");
        actionDoer(tips,"Tips");
    }


    public void actionDoer(CardView cardView, final String category){

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(beforeHealth.this, Health.class));
                intent.putExtra("CAT",category);
                startActivity(intent);

            }
        });


    }
}
