package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActiveDiscussionsB extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_discussions_b);

        linearLayout = findViewById(R.id.activeDiscLayoutB);
        linearLayout.removeAllViews();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_ID", MainActivity.userID);

        //firstly need to change and create a php to give u all your messages
        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/allSafeMsgs.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {

                try {

                    JSONArray jsonArray = new JSONArray(output);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        @SuppressLint("StaticFieldLeak")
                        final View view = View.inflate(ActiveDiscussionsB.this, R.layout.messages, null);
                        TextView name = view.findViewById(R.id.name);
                        TextView message = view.findViewById(R.id.message);
                        TextView date = view.findViewById(R.id.date);
                        TextView id = view.findViewById(R.id.MSG_ID);


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
        }.execute();
    }
}
