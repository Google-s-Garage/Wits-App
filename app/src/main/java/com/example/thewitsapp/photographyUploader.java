package com.example.thewitsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class photographyUploader extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView photo;
    String strImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_uploader);

        //Select image from the gallery
        //when click the image you must be able to upload

        //Initializing our views
        photo = findViewById(R.id.photography_image_view);
        final EditText editContacts = findViewById(R.id.enter_contacts);
        final EditText editLocation = findViewById(R.id.enter_location);
        final EditText editDate = findViewById(R.id.enter_date);
        final EditText editTime = findViewById(R.id.enter_time);
        final EditText editDisc = findViewById(R.id.enter_description);
        Button Post = findViewById(R.id.post_to_creatives);




        //On clicks of clickable views
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //goes to the gallery app for user to pick image
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
            }
        });


        //Have to take our post to the database
        Post.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                //check if the text fields are filled or not

                if(TextUtils.isEmpty(editContacts.getText().toString().trim())) editContacts.setError("Input Required");
                if(TextUtils.isEmpty(editDate.getText().toString().trim())) editContacts.setError("Input Required");
                if(TextUtils.isEmpty(editLocation.getText().toString().trim())) editContacts.setError("Input Required");
                if(TextUtils.isEmpty(editTime.getText().toString().trim())) editContacts.setError("Input Required");
                if(TextUtils.isEmpty(editDisc.getText().toString().trim())) editContacts.setError("Input Required");


                else{

                    //String we will need
                    final String contacts = editContacts.getText().toString().trim();
                    final String location = editLocation.getText().toString().trim();
                    final String date = editDate.getText().toString().trim();
                    final String time = editTime.getText().toString().trim();
                    final String disc = editDisc.getText().toString().trim();


                    //Hopefully if there's no image it will just stop rather than crash
                    try{

                        //we try to get the image as base64

                        //get the bitmap representation of the image
                        Bitmap actualImage = ((BitmapDrawable) photo.getDrawable()).getBitmap();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        actualImage.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                        strImg = Base64.encodeToString(outputStream.toByteArray(),Base64.DEFAULT);


                        ContentValues params = new ContentValues();
                        params.put("CONTACTS",contacts);
                        params.put("LOCATION",location);
                        params.put("DATE",date);
                        params.put("TIME",time);
                        params.put("DESCRIPTION",disc);
                        params.put("USER_ID",MainActivity.userID);
                        params.put("USERNAME",MainActivity.studentNum);
                        params.put("IMG",strImg);


                        //sending our stuff the php will then send the picture in some dir namely, public_html/creativeImgs
                        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/uploadPhotography.php", params) {
                            @Override
                            protected void onPostExecute(String output) {

                                if(output.equals("1")){

                                    Log.d("From Web",output);
                                    Toast.makeText(photographyUploader.this,"Successfully Posted",Toast.LENGTH_SHORT).show();
                                }

                                else if(output.equals("0")){

                                    Log.d("From Web",output);
                                    Toast.makeText(photographyUploader.this,"Couldn't Post",Toast.LENGTH_SHORT).show();
                                }

                                else{

                                    Log.d("From Web",output);
                                    Toast.makeText(photographyUploader.this,"Check Internet Connectivity",Toast.LENGTH_SHORT).show();
                                }

                            }
                        }.execute();


                    }
                    catch (Exception e){
                        e.printStackTrace();

                        Toast.makeText(photographyUploader.this,"Please Insert Image",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    //gets called when our activity is loaded again
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if it is from the gallery our request code will be returned as RESULT_LOAD_IMAGE
        //if we get RESULT_OK then set the image we got from the gallery. BOOM!
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data != null){

            Uri selectedImage = data.getData();
            photo.setImageURI(selectedImage); //hopefully this sets the image
        }
    }
}
