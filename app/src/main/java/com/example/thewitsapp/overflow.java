package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onResume() { //when we come back..
        super.onResume();

        linearLayout.removeAllViews(); //to remove what was then putting what must be =)

        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_ID", MainActivity.userID);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowQuestion.php",contentValues) {
            @Override
            protected void onPostExecute(String output) {

                try {

                    JSONArray jsonArray = new JSONArray(output);

                    for(int i=0; i < jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        View view = View.inflate(overflow.this,R.layout.forum_questions_layout,null); //what we are inflating with

                        String question = jsonObject.getString("QUESTION_MSG");
                        final String the_id = jsonObject.getString("QUESTION_ID");

                        String userID = Integer.toString(MainActivity.userID);

                        ((TextView) view.findViewById(R.id.userID)).setText(userID); //set user
                        ((TextView) view.findViewById(R.id.comment_textview)).setText(question);

                        TextView commentView = view.findViewById(R.id.commentButton); //it textview which will be clickable

                        commentView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(overflow.this,overflowComment.class);
                                intent.putExtra("QUESTION_ID",the_id);
                                startActivity(intent); //going to comment...

                            }
                        });

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(10,10,10,10);

                        linearLayout.addView(view,params);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(overflow.this,output,Toast.LENGTH_LONG).show();
            }
        }.execute();


    }
}
