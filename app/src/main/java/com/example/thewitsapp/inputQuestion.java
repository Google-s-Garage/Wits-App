package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class inputQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_question);

        final EditText questionBox = findViewById(R.id.question_box);
        Button postButton = findViewById(R.id.post_button);

        postButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                if(TextUtils.isDigitsOnly(questionBox.getText().toString().trim())){

                    questionBox.setError("Question must be inputted");
                }


                else{

                    //final View view = View.inflate(inputQuestion.this,R.layout.comments_on_the_overflow,null);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("QUESTION_MSG",questionBox.getText().toString().trim());
                    contentValues.put("USER_ID",MainActivity.userID);

                    new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/inputoverflowQuestion.php",contentValues) {
                        @Override
                        protected void onPostExecute(String output) {

                            if(output.equals("1")){

                                Toast.makeText(inputQuestion.this,"Question Posted",Toast.LENGTH_SHORT).show();
                                questionBox.setText(" ");
                            }

                            else{

                                Toast.makeText(inputQuestion.this,"Couldn't Post Question",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }.execute();


                }

            }
        });




    }
}
