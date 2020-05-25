package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_comment);

        linearLayout = findViewById(R.id.commentsHolder);

        Intent intent = getIntent();
        final String message = intent.getStringExtra("QUESTION_ID");

        final View view = View.inflate(overflowComment.this,R.layout.comments_on_the_overflow,null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("QUESTION_ID",message);



        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowGetComments.php",contentValues) {
            @Override
            protected void onPostExecute(String output) {

                try {
                    JSONArray jsonArray = new JSONArray(output);

                    linearLayout.removeAllViews();

                    for(int i=0; i < jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String comment = jsonObject.getString("RESP_MSG");
                        String id = jsonObject.getString("USER_ID");

                        TextView user = view.findViewById(R.id.user_id_comment);
                        TextView commentView = view.findViewById(R.id.commentBubbleHolder);

                        user.setText(id);
                        commentView.setText(comment);

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(10,10,10,10);

                        if(view.getParent() != null) ((ViewGroup)view.getParent()).removeView(view);

                        linearLayout.addView(view,params);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }.execute();


        final EditText commentBar = findViewById(R.id.commentBar);
        FloatingActionButton sendButton = findViewById(R.id.sendCommentButton);


        sendButton.setOnClickListener(new View.OnClickListener() { //this supposed to inflate this activity
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                linearLayout.removeAllViews();

                final View view = View.inflate(overflowComment.this,R.layout.comments_on_the_overflow,null);

                if(TextUtils.isEmpty(commentBar.getText().toString().trim())) commentBar.setError("");

                else{

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("USER_ID",MainActivity.userID);
                    contentValues.put("QUESTION_ID",message);
                    contentValues.put("RESP_MSG",commentBar.getText().toString().trim());

                    new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowPostComment.php",contentValues) {
                        @Override
                        protected void onPostExecute(String output) {

                                try {
                                    JSONArray jsonArray = new JSONArray(output);

                                    for(int i=0; i < jsonArray.length();i++){

                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String comment = jsonObject.getString("RESP_MSG");
                                        String id = jsonObject.getString("USER_ID");

                                        TextView user = view.findViewById(R.id.user_id_comment);
                                        TextView commentView = view.findViewById(R.id.commentBubbleHolder);

                                        user.setText(id);
                                        commentView.setText(comment);

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(10,10,10,10);

                                        if(view.getParent() != null) ((ViewGroup)view.getParent()).removeView(view);

                                        linearLayout.addView(view,params);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                    }.execute();

                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        View view = View.inflate(overflowComment.this,R.layout.comments_on_the_overflow,null);



    }
}
