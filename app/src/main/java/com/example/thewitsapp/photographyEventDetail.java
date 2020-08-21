package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class photographyEventDetail extends AppCompatActivity {

    //This activity will view content in more details
    //Description, Time, Date, Event organizer and all


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_event_detail);

        String stdNum = getIntent().getStringExtra("STD_NUM");
        String desc = getIntent().getStringExtra("DESC");
        String contacts = getIntent().getStringExtra("CONTACTS");
        String location = getIntent().getStringExtra("LOCATION");
        String date = getIntent().getStringExtra("DATE");
        String time = getIntent().getStringExtra("TIME");


        //Initializing the views
        TextView stdNUmView = findViewById(R.id.willBeStdNum);
        TextView descView = findViewById(R.id.descriptionId);
        TextView contactsView = findViewById(R.id.creativesContacts);
        TextView locationView = findViewById(R.id.creativesLocation);
        TextView dateView = findViewById(R.id.creativesDate);
        TextView timeView = findViewById(R.id.creativesTime);
        ImageView displayImage = findViewById(R.id.imageToDisplay);
        ImageView displayImage2 = findViewById(R.id.imageToDisplay2);
        ImageView editPost = findViewById(R.id.editPost);


        //editing what you have posted...
        editPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(photographyEventDetail.this,"I want to edit",Toast.LENGTH_SHORT).show();
            }
        });

        String imageURL = "https://lamp.ms.wits.ac.za/~s1872817/creativeImgs/"+stdNum+date+time+".JPG";


        //let's see:

        //setting the image on the view
        Glide.with(photographyEventDetail.this)
                .load(imageURL)
                .into(displayImage);

        Glide.with(photographyEventDetail.this)
                .load(imageURL)
                .into(displayImage2);

        //setting the details
        stdNUmView.setText(stdNum);
        descView.setText(desc);
        contactsView.setText(contacts);
        locationView.setText(location);
        dateView.setText(date);
        timeView.setText(time);

    }
}
