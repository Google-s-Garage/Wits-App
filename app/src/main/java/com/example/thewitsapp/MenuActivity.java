package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //individual cards...
        CardView userProfile = findViewById(R.id.profileCard);
        final CardView overflowCard = findViewById(R.id.overflowCard);
        CardView safeSpaceCard = findViewById(R.id.safespaceCard);
        CardView healthCard = findViewById(R.id.healthCard);
        CardView accomodationCard = findViewById(R.id.accomodationCard);
        CardView creativesCard = findViewById(R.id.creativesCard);

        //onclicks of the cards
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this,"userProfile clicked",Toast.LENGTH_SHORT).show();
            }
        });

        overflowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MenuActivity.this,overflowCategory.class));
                //I won't finish for now.
            }
        });

        safeSpaceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this,"Safe Space",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuActivity.this, safeSpaceDiscussions.class);
                startActivity(intent);
                //finish();
                }
        });

        healthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        accomodationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        creativesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MenuActivity.this,creativesActivity.class));
            }
        });
    }
}
