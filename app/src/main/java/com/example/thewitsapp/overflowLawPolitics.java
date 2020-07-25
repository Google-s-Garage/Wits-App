package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class overflowLawPolitics extends AppCompatActivity {

    public LinearLayout linearLayout;
    public String tag = "LawPolitics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_law_politics);

        linearLayout = findViewById(R.id.law_questions_holder);
        linearLayout.removeAllViews();

        FloatingActionButton addQuestion = findViewById(R.id.add_question_law);

        //Posting the new Question
        //Then we will inflate again if the post was successful
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //No longer does this but....
                //startActivity(new Intent(overflow.this,inputQuestion.class));

                //It does this
                final Dialog addCommentDialog = new Dialog(overflowLawPolitics.this);
                addCommentDialog.setContentView(R.layout.add_comment_dialog_overflow);

                ImageView cancelDialog = addCommentDialog.findViewById(R.id.add_comment_dialog_cancel);
                final EditText commentField = addCommentDialog.findViewById(R.id.add_dialog_comment);
                Button postButton = addCommentDialog.findViewById(R.id.add_comment_dialog_post_button);

                postButton.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onClick(View v) {

                        if(TextUtils.isEmpty(commentField.getText().toString().trim())) commentField.setError("Input Required");

                        else{

                            ContentValues contentValues = new ContentValues();
                            contentValues.put("QUESTION_MSG",commentField.getText().toString().trim());
                            contentValues.put("USER_ID",MainActivity.userID);
                            contentValues.put("USER_NAME", MainActivity.studentNum);
                            contentValues.put("CATEGORY",tag);

                            new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowPostCat.php", contentValues) {
                                @Override
                                protected void onPostExecute(String output) {

                                    if(output.equals("1")){

                                        Toast.makeText(overflowLawPolitics.this,"Question Posted",Toast.LENGTH_SHORT).show();
                                        commentField.setText("");

                                        //inflate again here
                                        getScienceQuestion();
                                    }

                                    else{

                                        Toast.makeText(overflowLawPolitics.this,output,Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }.execute();

                        }

                    }
                });

                //Cancel wanting to add comment
                cancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addCommentDialog.dismiss();
                    }
                });

                addCommentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                addCommentDialog.show();


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        getScienceQuestion();
    }


    //used to get science questions
    @SuppressLint("StaticFieldLeak")
    public void getScienceQuestion(){

        linearLayout.removeAllViews();

        ContentValues params = new ContentValues();
        params.put("CATEGORY",tag);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowQuestionsCat.php", params) {
            @Override
            protected void onPostExecute(String output) {

                if(output.equals("0")){

                    Toast.makeText(overflowLawPolitics.this,"Can't Get Questions",Toast.LENGTH_SHORT).show();
                }


                else{

                    try {

                        JSONArray jsonArray = new JSONArray(output);

                        for(int i=0;i < jsonArray.length();i++){

                            View view = View.inflate(overflowLawPolitics.this,R.layout.forum_questions_layout,null);

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            //The view on the layout
                            TextView stdNumView = view.findViewById(R.id.userID);
                            TextView questionView = view.findViewById(R.id.comment_textview);
                            TextView commentButton = view.findViewById(R.id.commentButton);
                            TextView commentList = view.findViewById(R.id.list_of_comments);

                            //String I got from the database
                            String stdNum = jsonObject.getString("USER_NAME");
                            String question = jsonObject.getString("QUESTION_MSG");
                            final String question_id = jsonObject.getString("QUESTION_ID");

                            //Setting question and
                            //Student number of person who posted the question
                            stdNumView.setText(stdNum);
                            questionView.setText(question);

                            //Now Implementing what happens when we click these two
                            //SPACE. intentionally left blank

                            //The comment must prompt a dialog to comment on the question
                            //More like answer
                            commentButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    final Dialog commentDialog = new Dialog(overflowLawPolitics.this);
                                    commentDialog.setContentView(R.layout.comment_dialog_pop_up);

                                    final Button commentPost = commentDialog.findViewById(R.id.dialog_post_button);
                                    final EditText getComment = commentDialog.findViewById(R.id.dialog_comment);
                                    ImageView cancelImg = commentDialog.findViewById(R.id.dialog_cancel_button);

                                    //Closes dialog when X image is clicked
                                    cancelImg.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            commentDialog.dismiss();
                                        }
                                    });


                                    //When the comment on the card is clicked
                                    commentPost.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if(TextUtils.isEmpty(getComment.getText().toString().trim())) getComment.setError("Input required");


                                                //if we managed to type the question
                                                //Then let's post in the database
                                            else{
                                                String userComment = getComment.getText().toString().trim();

                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("USER_ID",MainActivity.userID);
                                                contentValues.put("STUDENT_NUM",MainActivity.studentNum);
                                                contentValues.put("QUESTION_ID",question_id);
                                                contentValues.put("RESP_MSG",userComment);

                                                new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/overflowPostComment.php", contentValues) {
                                                    @Override
                                                    protected void onPostExecute(String output) {



                                                        if(output.equals("1")){

                                                            Toast.makeText(overflowLawPolitics.this,"Comment Posted",Toast.LENGTH_SHORT).show();

                                                            getComment.setText("");
                                                        }

                                                        else{

                                                            Toast.makeText(overflowLawPolitics.this,"Couldn't Comment",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }.execute();
                                            } //After the posting is done then...
                                        }
                                    });

                                    commentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    commentDialog.show();
                                }
                            });


                            commentList.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(overflowLawPolitics.this,overflowComment.class);
                                    intent.putExtra("QUESTION_ID",question_id);
                                    startActivity(intent); //going to comments...
                                }
                            });

                            //Inflating each and every card
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);

                            linearLayout.addView(view,params);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }.execute();
    }

}
