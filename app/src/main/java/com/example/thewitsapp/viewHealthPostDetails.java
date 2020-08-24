package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;

public class viewHealthPostDetails extends AppCompatActivity {

    String nameFromHealth, stdNumFromHealth;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_health_post_details);

        linearLayout = findViewById(R.id.detailsHolder);

        Bundle intent = getIntent().getExtras();
        nameFromHealth = intent.getString("NameOfPost");
        stdNumFromHealth = intent.getString("STUDENT_NUM");

        viewPost(viewHealthPostDetails.this,stdNumFromHealth,nameFromHealth);
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewPost(viewHealthPostDetails.this,stdNumFromHealth,nameFromHealth);
    }

    @SuppressLint("StaticFieldLeak")
    public void viewPost(final Context context, String stdNum, String Post){

        linearLayout.removeAllViews();

        ContentValues params = new ContentValues();
        params.put("STUDENT_NUM",stdNum);
        params.put("POST_NAME",Post);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/getHealthPostDetails.php", params) {
            @Override
            protected void onPostExecute(String output) {

                if(output.equals("0")){

                    Toast.makeText(context,"Can't get details",Toast.LENGTH_SHORT).show();

                }

                else{

                    try {
                        JSONArray jsonArray = new JSONArray(output);

                        for(int i=0; i < jsonArray.length(); i++){

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            View view = View.inflate(context,R.layout.health_view_post,null);

                            String nameOfPoster = jsonObject.getString("STUDENT_NUM");
                            String nameOfPost = jsonObject.getString("POST_NAME");
                            String descrip = jsonObject.getString("POST_DESCRIP");
                            String category = jsonObject.getString("CAT");
                            String imgURL = "https://lamp.ms.wits.ac.za/~s1872817/HealthPostImgs/"+nameOfPoster+nameOfPost+".JPG";

                            TextView Poster = view.findViewById(R.id.nameOfPoster);
                            TextView PostName = view.findViewById(R.id.nameOfHealthPost);
                            TextView desc = view.findViewById(R.id.nameOfHealthDescrip);
                            TextView cat = view.findViewById(R.id.nameOfCat);
                            ImageView postImage = view.findViewById(R.id.imageOfPost);

                            if(nameOfPoster.equals("100")) {

                                Poster.setText("WITS TEAM");
                                cat.setText("FROM US");
                            }

                            else {

                                Poster.setText(nameOfPoster);
                                cat.setText(category);
                            }

                            PostName.setText(nameOfPost);
                            desc.setText(descrip);



                            Glide.with(context)
                                    .load(imgURL)
                                    .into(postImage);

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);

                            linearLayout.addView(view,params);

                        }



                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context,"An error has occurred",Toast.LENGTH_SHORT).show();
                    }


                }

            }
        }.execute();

    }



}