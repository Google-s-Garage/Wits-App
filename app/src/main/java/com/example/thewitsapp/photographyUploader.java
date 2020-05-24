package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class photographyUploader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_uploader);

        //see how you will upload the picture and caption into the database. That's all for today. 19/05/20

        FloatingActionButton caption, addPhoto;
        EditText editCaption = findViewById(R.id.photography_caption);
        ImageView photo = findViewById(R.id.photography_image_view);

        caption = findViewById(R.id.photography_flaot_caption);
        addPhoto = findViewById(R.id.image_uploader);

        caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
