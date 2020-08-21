package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccomodationMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomodation_main);

        CardView applyCard = (findViewById(R.id.accomApplyCard));
        CardView statusCard = (findViewById(R.id.accomStatusCard));

        applyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccomodationMain.this, AccomTypes.class);
                startActivity(intent);
            }
        });
    }
}