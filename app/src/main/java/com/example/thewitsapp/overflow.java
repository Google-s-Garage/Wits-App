package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
        //contentValues.put("USER_ID", MainActivity.userID);

        //WE want all questions not the ones by the current logged in user only

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
                        String username = jsonObject.getString("USER_NAME"); //aka student number

                        //We might need this so I will just leave it here and not comment it
                        String userID = Integer.toString(MainActivity.userID);

                        ((TextView) view.findViewById(R.id.userID)).setText(username); //set the student number as the username
                        ((TextView) view.findViewById(R.id.comment_textview)).setText(question);

                        TextView commentView = view.findViewById(R.id.commentButton); //it textview which will be clickable
                        TextView listComments = view.findViewById(R.id.list_of_comments); //list of comments


                        listComments.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(overflow.this,overflowComment.class);
                                intent.putExtra("QUESTION_ID",the_id);
                                startActivity(intent); //going to comments...

                            }
                        });


                        //The dialog is implemented here
                        //I am reusing used a lot of stuff
                        //Hope that doesn't break the app =)
                        commentView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Let's go
                                final Dialog commentDialog = new Dialog(overflow.this);

                                //The resource layout that we gonna use as the pop alert box
                                //
                                commentDialog.setContentView(R.layout.comment_dialog_pop_up);

                                //our views on the dialog
                                ImageView cancelImage = commentDialog.findViewById(R.id.dialog_cancel_button);
                                final EditText editComment = commentDialog.findViewById(R.id.dialog_comment);
                                Button postButton = commentDialog.findViewById(R.id.dialog_post_button);

                                cancelImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //close the dialog
                                        commentDialog.dismiss();
                                    }
                                });

                                //When clicking this the comment must be taken to the database
                                //we have the question id no biggie
                                postButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String comment = editComment.getText().toString().trim();

                                        ContentValues params = new ContentValues();
                                        params.put("USER_ID",MainActivity.userID);
                                        params.put("STUDENT_NUM",MainActivity.studentNum);
                                        params.put("QUESTION_ID",the_id);
                                        params.put("RESP_MSG",comment);


                                        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowPostComment.php", params) {
                                            @Override
                                            protected void onPostExecute(String output) {

                                                if(output.equals("1")){

                                                    Toast.makeText(overflow.this,"Comment Posted",Toast.LENGTH_SHORT).show();

                                                    editComment.setText("");
                                                }

                                                else{

                                                    Toast.makeText(overflow.this,output,Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        }.execute();


                                    }
                                });

                                commentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                commentDialog.show();

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
