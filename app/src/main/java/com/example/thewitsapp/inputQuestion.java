package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inputQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_question);

        final EditText questionBox = findViewById(R.id.question_box);
        Button postButton = findViewById(R.id.post_button);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isDigitsOnly(questionBox.getText().toString().trim())){

                    questionBox.setError("Question must be inputted");
                }


                else{
                    overflow.tempQuestions.add(questionBox.getText().toString().trim());
                    questionBox.setText(" ");
                }

            }
        });
    }
}
