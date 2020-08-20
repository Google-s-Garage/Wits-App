package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActiveDiscussionsB extends AppCompatActivity {
    LinearLayout linearLayout;
    FloatingActionButton activDiscButton;
    EditText activDiscEditText;
    String safeUsername;
    int id;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_discussions_b);
        //Setting up
        //The initial things
        init();

    }
    public void init(){

        final LinearLayout rLayout = (LinearLayout) findViewById(R.id.activ_rLayout);
        rLayout.removeAllViews();
        //inflate to the left
        mtoolbar = findViewById(R.id.activDisc_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Messages");


        @SuppressLint("StaticFieldLeak")
        final View view1 = View.inflate(ActiveDiscussionsB.this, R.layout.chat_item_left, null);
        final TextView msg = (TextView) view1.findViewById(R.id.itemLeft);
        msg.setText(ActiveDiscussions.Msg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        rLayout.addView(view1,params);

        //inflate to the right

        ContentValues contentValues = new ContentValues();
        contentValues.put("SAFE_MSG_ID", ActiveDiscussions.msgID);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/SafeMsgResponses.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    Log.i("tagconvertstr", "["+ActiveDiscussions.safeUsername+"]");
                    JSONArray jsonArray = new JSONArray(output);

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        safeUsername = jsonObject.getString("SAFE_NAME").trim();
                        Log.i("tagconvertstr", "["+safeUsername+"]");
                        //displays the safeUsername we get from SAFE_NAME
                        if(safeUsername.equals(ActiveDiscussions.safeUsername)){
                            @SuppressLint("StaticFieldLeak")
                            final View view0 = View.inflate(ActiveDiscussionsB.this, R.layout.chat_item_left, null);
                            TextView msg = (TextView) view0.findViewById(R.id.itemLeft);
                            msg.setText(jsonObject.getString("SAFE_RESP_MSG"));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);
                            rLayout.addView(view0,params);

                        }
                        else{
                            @SuppressLint("StaticFieldLeak")
                            final View view0 = View.inflate(ActiveDiscussionsB.this, R.layout.chat_item_right, null);
                            TextView resp = (TextView) view0.findViewById(R.id.itemRight);
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

        activDiscButton = findViewById(R.id.activDiscBsendButton);
        activDiscEditText = findViewById(R.id.activDiscEditText);

        activDiscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //I want u to post to the database as a response but it should come back and be infalted to the left!
                if(!TextUtils.isEmpty(activDiscEditText.getText().toString())){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("USER_ID",MainActivity.userID);
                    contentValues.put("SAFE_NAME", MainActivity.safeName);
                    contentValues.put("SAFE_MSG_ID", ActiveDiscussions.msgID);
                    contentValues.put("SAFE_RESP_MSG", activDiscEditText.getText().toString().trim());

                    new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/sendDiscMsg.php",contentValues) {
                        @Override
                        protected void onPostExecute(String output) {

                            if(output.equals("1")){

                                Toast.makeText(ActiveDiscussionsB.this,"Replied",Toast.LENGTH_SHORT).show();
                                activDiscEditText.setText(" ");
                                init();

                            }

                            else{
                                Toast.makeText(ActiveDiscussionsB.this,"Network error!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }.execute();

                }
            }
        });
    }
}
