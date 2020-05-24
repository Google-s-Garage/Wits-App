package com.example.thewitsapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyDiscussions extends Fragment {


    private LinearLayout linearLayout;
    private ArrayList<String> arrayList;


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

    private void init(View view) {

        linearLayout = view.findViewById(R.id.myDiscLayout);
        linearLayout.removeAllViews();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_ID", MainActivity.userID);

        //firstly need to change and create a php to give u all your messages
        new ServerCommunicator("http://lamp.ms.wits.ac.za/~s1872817/safeMsgs.php", contentValues) {
            @Override
            protected void onPostExecute(String output) {

                try {

                    JSONArray jsonArray = new JSONArray(output);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        final Context context = getActivity().getApplicationContext();

                        @SuppressLint("StaticFieldLeak")
                        final View view = View.inflate(context, R.layout.messages, null);
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
