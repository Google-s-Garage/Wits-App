package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class overflowComment extends AppCompatActivity {

    LinearLayout linearLayout;
    String message;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_comment);

        linearLayout = findViewById(R.id.commentsHolder);

        //getting the question id so we can use it to inflate this view
        message = getIntent().getStringExtra("QUESTION_ID");


    }


    //This method is better suited for inflating
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onResume() {
        super.onResume();

        //remove whatever was there before:
        linearLayout.removeAllViews();

        //View view = View.inflate(overflowComment.this,R.layout.comments_on_the_overflow,null);
        ContentValues params = new ContentValues();
        params.put("QUESTION_ID",message);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowGetComments.php", params) {
            @Override
            protected void onPostExecute(String output) {


                if(output.equals("0")){

                    Toast.makeText(overflowComment.this,"No Comments Yet",Toast.LENGTH_SHORT).show();
                }

                else{


                    try {
                        JSONArray jsonArray = new JSONArray(output);

                        for(int i=0; i < jsonArray.length();i++){

                            View view = View.inflate(overflowComment.this,R.layout.comments_on_the_overflow,null);

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String username = jsonObject.getString("STUDENT_NUM");
                            String answer = jsonObject.getString("RESP_MSG");
                            String dateOfAnswer = jsonObject.getString("RESP_DATE");

                            ((TextView) view.findViewById(R.id.user_id_comment)).setText(username);
                            ((TextView) view.findViewById(R.id.commentBubbleHolder)).setText(answer);
                            ((TextView) view.findViewById(R.id.date_of_answer)).setText(dateOfAnswer);

                            ImageView likeImage = view.findViewById(R.id.like_button);

                            //On click of the like button:
                            likeImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Toast.makeText(overflowComment.this,"Clicked Like",Toast.LENGTH_SHORT).show();
                                }
                            });


                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);

                            linearLayout.addView(view,params);


                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                        Toast.makeText(overflowComment.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }.execute();
    }
}
