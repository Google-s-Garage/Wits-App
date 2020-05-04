package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class overflow extends AppCompatActivity {

    LinearLayout linearLayout;
    static ArrayList<String> tempQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow);

        FloatingActionButton addButton = findViewById(R.id.add_comment);
        linearLayout = findViewById(R.id.questions_holder);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(overflow.this,inputQuestion.class));

            }
        });
    }

    @Override
    protected void onResume() { //when we come back..
        super.onResume();

        linearLayout.removeAllViews(); //to remove what was then putting what must be =)

        for(int i =0; i < tempQuestions.size(); i++){

            View view = View.inflate(overflow.this,R.layout.forum_questions_layout,null); //what we are inflating with
            ((TextView) view.findViewById(R.id.comment_textview)).setText(tempQuestions.get(i));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);

            linearLayout.addView(view,params);
        }


    }
}
