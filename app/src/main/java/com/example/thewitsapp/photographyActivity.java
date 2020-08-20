package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class photographyActivity extends AppCompatActivity {

    LinearLayout postsHolder;
    public static String publicContact,publicLocation,publicDate,publicTime,publicDesc,publicStdNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography);

        postsHolder = findViewById(R.id.photography_posts_holder);

        FloatingActionButton add = findViewById(R.id.add_photography_post);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(photographyActivity.this, photographyUploader.class));
            }
        });

    }



    //inflate after uploading hopefully
    //After the activity is fully loaded and started we then inflate
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onResume() {
        super.onResume();

        //remove whatever was there in the linearLayout
        //This helps if we're from the post activity and we want our recent post to appear


        ContentValues params = new ContentValues();

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/getPhotographyPosts.php", params) {
            @Override
            protected void onPostExecute(String output) {

                postsHolder.removeAllViews();

                try {

                    JSONArray jsonArray = new JSONArray(output);

                    //inflate the view
                    for(int i=0; i < jsonArray.length();i++){

                        View view = View.inflate(photographyActivity.this,R.layout.post_on_creatives,null);

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        TextView descView = view.findViewById(R.id.Caption);
                        ImageView thePost = view.findViewById(R.id.thePostImage);

                        final String desc = jsonObject.getString("DESCRIPTION");
                        final String date = jsonObject.getString("DATE");
                        final String time = jsonObject.getString("TIME");
                        final String location = jsonObject.getString("LOCATION");
                        final String contacts = jsonObject.getString("CONTACTS");
                        final String username = jsonObject.getString("STUDENT_NUM");

                        //These will be used in the Details activity for display the details
                        publicDesc = desc;
                        publicContact = contacts;
                        publicDate = date;
                        publicTime = time;
                        publicLocation = location;
                        publicStdNum = username;

                        //an image name is made up of username, date and time
                        //For assumed uniqueness of names for each post
                        String imageURL = "https://lamp.ms.wits.ac.za/~s1872817/creativeImgs/"+username+date+time+".JPG";

                        //setting the post card some details
                        Glide.with(view)
                                .load(imageURL)
                                .into(thePost);
                        descView.setText(desc);

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(10,10,10,10);

                        //More info of the post
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Hopefully this works.....
                                Intent intent = new Intent(photographyActivity.this,photographyEventDetail.class);
                                intent.putExtra("STD_NUM",username);
                                intent.putExtra("DESC",desc);
                                intent.putExtra("CONTACTS",contacts);
                                intent.putExtra("LOCATION",location);
                                intent.putExtra("DATE",date);
                                intent.putExtra("TIME",time);

                                startActivity(intent);
                                Toast.makeText(photographyActivity.this,"More Details",Toast.LENGTH_SHORT).show();
                            }
                        });

                        postsHolder.addView(view,params);


                    }

                }catch (JSONException e){}





            }
        }.execute();


    }
}
