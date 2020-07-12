package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyDiscussionsB extends AppCompatActivity {
    LinearLayout linearLayout;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_discussions_b);

        linearLayout = findViewById(R.id.myDiscLayoutB);
        linearLayout.removeAllViews();

        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_ID", MainActivity.userID);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/safeMsgs.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {

                if(output.equals("0")){
                    Toast.makeText(MyDiscussionsB.this,"Returning 0",Toast.LENGTH_SHORT).show();
                }
                else{

                  try {

                    JSONArray jsonArray = new JSONArray(output);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        final View view = View.inflate(MyDiscussionsB.this,R.layout.messages, null);

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        @SuppressLint("StaticFieldLeak")

                        TextView name = (TextView) view.findViewById(R.id.name);
                        TextView message = (TextView) view.findViewById(R.id.message);
                        TextView date = (TextView) view.findViewById(R.id.date);
                        TextView id = (TextView) view.findViewById(R.id.MSG_ID);


                        name.setText(jsonObject.getString("SAFE_NAME"));
                        message.setText(jsonObject.getString("SAFE_MSG"));
                        date.setText(jsonObject.getString("SAFE_DATE"));
                        id.setText(jsonObject.getString("SAFE_MSG_ID"));

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
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
