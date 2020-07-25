package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class overflowCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_category);

        ConstraintLayout scienceView, mathView, artsView, accountingView, bioView, lawPoliticsView;

        scienceView = findViewById(R.id.science_category);
        mathView = findViewById(R.id.math_category);
        artsView = findViewById(R.id.arts_category);
        accountingView = findViewById(R.id.acc_category);
        bioView = findViewById(R.id.bio_category);
        lawPoliticsView = findViewById(R.id.law_category);


        //This could be done smartly with a loop
        //Or a function
        //But @khutso is a bad coder
        mathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(overflowCategory.this,overflowMath.class));
            }
        });

        scienceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(overflowCategory.this,overflowScience.class));
            }
        });

        artsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(overflowCategory.this,overflowArts.class));
            }
        });

        accountingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(overflowCategory.this,overflowAccounting.class));
            }
        });

        bioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(overflowCategory.this,overflowBiology.class));
            }
        });

        lawPoliticsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(overflowCategory.this,overflowLawPolitics.class));
            }
        });
    }
}
