package com.example.thewitsapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyDiscussions extends Fragment {

    private ArrayList<String> arrayList;
    public static String MsgId; //should be initialised with the clicked message id
    public static String Msg;
    public static String safeName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_discussions, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


    @SuppressLint("StaticFieldLeak")
    private void init(View view) {

        final LinearLayout linearLayout = (LinearLayout)  getActivity().findViewById(R.id.myDiscLayout);
        linearLayout.removeAllViews();

        final LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_ID", MainActivity.userID);

        //firstly need to change and create a php to give u all your messages
        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/safeMsgs.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {

                try {

                    JSONArray jsonArray = new JSONArray(output);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        final Context context = getActivity().getApplicationContext();

                        @SuppressLint("StaticFieldLeak")
                        final View view = vi.inflate(R.layout.messages, null);
                        final TextView name = (TextView) view.findViewById(R.id.name);
                        final TextView message = (TextView) view.findViewById(R.id.message);
                        TextView date = (TextView) view.findViewById(R.id.date);
                        final TextView id = (TextView) view.findViewById(R.id.MSG_ID);

                        name.setText(jsonObject.getString("SAFE_NAME"));
                        message.setText(jsonObject.getString("SAFE_MSG"));
                        date.setText(jsonObject.getString("SAFE_DATE"));
                        id.setText(jsonObject.getString("SAFE_MSG_ID"));

                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //set the message_id to equal the msg_id we retrieved from the server
                                MsgId = ((TextView) view.findViewById(R.id.MSG_ID)).getText().toString();
                                Msg = message.getText().toString().trim();
                                safeName = name.getText().toString().trim();
                                Toast.makeText(context, MsgId, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(context, safeSpaceB.class));
                            }
                        });

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
