package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class safeSpaceB extends AppCompatActivity {

    FloatingActionButton MyDiscCommentButton;
    EditText myDiscEditText;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_space_b);

        init();

    }
    public void init(){

        final LinearLayout rLayout = (LinearLayout) findViewById(R.id.rLayout);
        rLayout.removeAllViews();
        //inflate to the left

        @SuppressLint("StaticFieldLeak")
        final View view1 = View.inflate(safeSpaceB.this, R.layout.chat_item_right, null);
        final TextView msg = (TextView) view1.findViewById(R.id.itemRight);
        msg.setText(MyDiscussions.Msg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        rLayout.addView(view1,params);

        //inflate to the right

        ContentValues contentValues = new ContentValues();
        contentValues.put("SAFE_MSG_ID", MyDiscussions.MsgId);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/SafeMsgResponses.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    Log.i("tagconvertstr", "["+MyDiscussions.MsgId+"]");
                    JSONArray jsonArray = new JSONArray(output);

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        id = Integer.parseInt(jsonObject.getString("USER_ID").trim());
                        Log.i("tagconvertstr", "["+id+"]");
                        if(id == MainActivity.userID){
                            @SuppressLint("StaticFieldLeak")
                            final View view0 = View.inflate(safeSpaceB.this, R.layout.chat_item_right, null);
                            TextView msg = (TextView) view0.findViewById(R.id.itemRight);
                            msg.setText(jsonObject.getString("SAFE_RESP_MSG"));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);
                            rLayout.addView(view0,params);

                        }
                        else{
                            @SuppressLint("StaticFieldLeak")
                            final View view0 = View.inflate(safeSpaceB.this, R.layout.chat_item_left, null);
                            TextView resp = (TextView) view0.findViewById(R.id.itemLeft);
                            resp.setText(jsonObject.getString("SAFE_RESP_MSG"));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);
                            rLayout.addView(view0,params);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();

        MyDiscCommentButton = findViewById(R.id.MyDiscsendButton);
        myDiscEditText = findViewById(R.id.myDiscEditText);

        MyDiscCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //I want u to post to the database as a response but it should come back and be infalted to the left!
                if(!TextUtils.isEmpty(myDiscEditText.getText().toString())){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("USER_ID",MainActivity.userID);
                    contentValues.put("SAFE_NAME", MyDiscussions.safeName);
                    contentValues.put("SAFE_MSG_ID", MyDiscussions.MsgId);
                    contentValues.put("SAFE_RESP_MSG", myDiscEditText.getText().toString().trim());

                    new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/sendDiscMsg.php",contentValues) {
                        @Override
                        protected void onPostExecute(String output) {

                            if(output.equals("1")){

                                Toast.makeText(safeSpaceB.this,"Question Posted",Toast.LENGTH_SHORT).show();
                                myDiscEditText.setText(" ");

                            }

                            else{
                                Toast.makeText(safeSpaceB.this,"Network error!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }.execute();

                }
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();

        final LinearLayout rLayout = (LinearLayout) findViewById(R.id.rLayout);
        rLayout.removeAllViews();

        ContentValues contentValues = new ContentValues();
        contentValues.put("SAFE_MSG_ID", MyDiscussions.MsgId);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/SafeMsgResponses.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    Log.i("tagconvertstr", "["+MyDiscussions.MsgId+"]");
                    JSONArray jsonArray = new JSONArray(output);

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        id = Integer.parseInt(jsonObject.getString("USER_ID").trim());
                        Log.i("tagconvertstr", "["+id+"]");
                        if(id == MainActivity.userID){
                            @SuppressLint("StaticFieldLeak")
                            final View view0 = View.inflate(safeSpaceB.this, R.layout.chat_item_right, null);
                            TextView msg = (TextView) view0.findViewById(R.id.itemRight);
                            msg.setText(jsonObject.getString("SAFE_RESP_MSG"));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);
                            rLayout.addView(view0,params);

                        }
                        else{
                            @SuppressLint("StaticFieldLeak")
                            final View view0 = View.inflate(safeSpaceB.this, R.layout.chat_item_left, null);
                            TextView resp = (TextView) view0.findViewById(R.id.itemLeft);
                            resp.setText(jsonObject.getString("SAFE_RESP_MSG"));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);
                            rLayout.addView(view0,params);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();


    }
}
