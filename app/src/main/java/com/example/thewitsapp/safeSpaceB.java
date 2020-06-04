package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class safeSpaceB extends AppCompatActivity {

    Button myDiscButton, activeDiscButton;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_space_b);
       // String msg_id = MyDiscussions.MsgId;
        //I want to inflate different relative layouts depending on whether or not they are sent by a sender or a receiver

        relativeLayout = (RelativeLayout) findViewById(R.id.rLayout);
        relativeLayout.removeAllViews();

        ContentValues contentValues = new ContentValues();
        contentValues.put("SAFE_MSG_ID", MyDiscussions.MsgId);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/returnSafeMsgs.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {
                    try {
                        Log.i("tagconvertstr", "["+output+"]");
                        JSONArray jsonArray = new JSONArray(output);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        @SuppressLint("StaticFieldLeak")
                        final View view = View.inflate(safeSpaceB.this, R.layout.chat_item_left, null);
                        TextView msg = (TextView) view.findViewById(R.id.itemLeft);
                        msg.setText(jsonObject.getString("SAFE_MSG"));

                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(10,10,10,10);
                        relativeLayout.addView(view,params);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        }.execute();




    }
}
