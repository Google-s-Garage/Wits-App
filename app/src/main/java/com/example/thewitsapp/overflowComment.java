package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class overflowComment extends AppCompatActivity {

    static ArrayList<String> tempCommentStorer;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_comment);

        tempCommentStorer = new ArrayList<>();

        final EditText commentBar = findViewById(R.id.commentBar);
        FloatingActionButton sendButton = findViewById(R.id.sendCommentButton);
        linearLayout = findViewById(R.id.commentsHolder);

        sendButton.setOnClickListener(new View.OnClickListener() { //this supposed to inflate this activity
            @Override
            public void onClick(View v) {

                View view = View.inflate(overflowComment.this,R.layout.comments_on_the_overflow,null);

                if(TextUtils.isEmpty(commentBar.getText().toString().trim())) commentBar.setError("");

                else{

                    TextView commentView = view.findViewById(R.id.commentBubbleHolder);
                    String theText = commentBar.getText().toString().trim();
                    commentView.setText(theText);
                    commentBar.setText("");
                    tempCommentStorer.add(theText);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(1,3,1,3);

                    linearLayout.addView(view,params);

                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        View view = View.inflate(overflowComment.this,R.layout.comments_on_the_overflow,null);

        for(int i=0;i<tempCommentStorer.size();i++){

            TextView commentView = view.findViewById(R.id.commentBubbleHolder);
            commentView.setText(tempCommentStorer.get(i));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(1,3,1,3);

            linearLayout.addView(view,params);

        }

    }
}
