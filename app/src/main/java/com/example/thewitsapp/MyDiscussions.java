package com.example.thewitsapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;

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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyDiscussions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyDiscussions extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout linearLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyDiscussions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyDiscussions.
     */
    // TODO: Rename and change types and number of parameters
    public static MyDiscussions newInstance(String param1, String param2) {
        MyDiscussions fragment = new MyDiscussions();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_discussions, container, false);
        init(view);
        return view;
    }
       
    @SuppressLint("StaticFieldLeak")
    private void init(View view) {

        linearLayout = view.findViewById(R.id.linearlayout3);
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
                        //TextView name = view.findViewById(R.id.name);
                        TextView message = view.findViewById(R.id.message);
                        //TextView date = view.findViewById(R.id.date);

                        //name.setText(jsonObject.getString("NAME"));
                        message.setText(jsonObject.getString("SAFE_MSG"));
                        //date.setText(jsonObject.getString("DATE"));

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        //params.setMargins(1,1,1,1);

                        linearLayout.addView(view,params);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }       

}
