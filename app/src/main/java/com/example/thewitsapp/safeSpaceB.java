package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class safeSpaceB extends AppCompatActivity {

    Button myDiscButton, activeDiscButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_space_b);

        myDiscButton = findViewById(R.id.myDiscBtnB);
        activeDiscButton = findViewById(R.id.actDiscBtnB);

        myDiscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(safeSpaceB.this, MyDiscussionsB.class));
            }
        });

        activeDiscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(safeSpaceB.this, ActiveDiscussionsB.class));
            }
        });


    }
}
