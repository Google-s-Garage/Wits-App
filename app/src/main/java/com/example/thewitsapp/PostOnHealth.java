package com.example.thewitsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class PostOnHealth extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView photo;
    String strImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_on_health);

        final Button postButton = findViewById(R.id.health_post_button);
        final EditText postName = findViewById(R.id.health_post_name);
        final EditText healthDescrip = findViewById(R.id.health_descrip);
        final EditText healthCat = findViewById(R.id.health_category);
        final EditText healthDate = findViewById(R.id.health_date);

        photo = findViewById(R.id.health_image_view);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);

            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if the editTexts are valid
                if(TextUtils.isEmpty(postName.getText().toString().trim())) postName.setError("Input required");
                if(TextUtils.isEmpty(healthDescrip.getText().toString().trim())) postName.setError("Input required");
                if(TextUtils.isEmpty(healthCat.getText().toString().trim())) postName.setError("Input required");
                if(TextUtils.isEmpty(healthDate.getText().toString().trim())) postName.setError("Input required");

                else{

                    String name = postName.getText().toString().trim();
                    String description = healthDescrip.getText().toString().trim();
                    String category = healthCat.getText().toString().trim();
                    String date = healthDate.getText().toString().trim();

                    //We must have valid things as categories
                    if(((!category.equals("Recipes")) || (!category.equals("Food")) || (!category.equals("Tips")) || (!category.equals("Rest"))) && MainActivity.studentNum!=100) {
                        healthCat.setError("Set Accepted Category");
                    }

                    //Then if all is good you can post
                    else{

                        SendPostToServer(PostOnHealth.this,name,description,category,date);
                    }
                }
            }
        });
    }


    @SuppressLint("StaticFieldLeak")
    public void SendPostToServer(final Context context, String name, String description, String category, String date){

        ContentValues params = new ContentValues();



        try{

            Bitmap actualImage = ((BitmapDrawable) photo.getDrawable()).getBitmap();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            actualImage.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            strImg = Base64.encodeToString(outputStream.toByteArray(),Base64.DEFAULT);

            params.put("POST_NAME",name);
            params.put("POST_DESCRIP",description);
            params.put("POST_DATE",date);
            params.put("CAT",category);
            params.put("USER_ID",MainActivity.userID);
            params.put("USERNAME",MainActivity.studentNum);
            params.put("IMG",strImg);

            new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/uploadHealthPost.php",params) {
                @Override
                protected void onPostExecute(String output) {

                    if(output.equals("1")){

                        Toast.makeText(context,"Upload Completed",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context,Health.class));
                    }

                    else if(output.equals("0")) Toast.makeText(context,"Couldn't Post, Check Input",Toast.LENGTH_SHORT).show();

                    else{

                        Toast.makeText(context,"Check Connectivity, Something Went Wrong",Toast.LENGTH_SHORT).show();
                    }

                }
            }.execute();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context,"Please Insert Image",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data != null){

            Uri selectedImage = data.getData();
            photo.setImageURI(selectedImage); //hopefully this sets the image
        }
    }
}
