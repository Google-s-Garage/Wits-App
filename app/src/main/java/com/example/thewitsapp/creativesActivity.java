package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class creativesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatives);

        final CardView photographyCard, musicCard, visualArts, interestGroup;


        photographyCard = findViewById(R.id.creatives_photography_card);
        musicCard = findViewById(R.id.creatives_music_card);
        visualArts = findViewById(R.id.creatives_visualArts_card);
        interestGroup = findViewById(R.id.creatives_interestGroupd_card);

        photographyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(creativesActivity.this,photographyActivity.class));
            }
        });

        musicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        visualArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        interestGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
