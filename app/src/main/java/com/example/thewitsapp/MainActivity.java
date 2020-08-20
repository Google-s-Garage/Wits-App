package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity { /*we will use this activity as the login activity. I am lazy to do the copy
                                                       I am lazy to copy and rearrange. Khutso*/
    public static int userID, studentNum;
    public static String safeName;

//the xml can be edited at this point I am concerned with the functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText studentNumber = findViewById(R.id.studentNumberLogin);
        final EditText password = findViewById(R.id.passwordLogin);
        Button login_button = findViewById(R.id.login_button);
        TextView forgortPassword = findViewById(R.id.textView2);

        forgortPassword.setOnClickListener(new View.OnClickListener() { //will deal with
            @Override
            public void onClick(View v) {

            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(studentNumber.getText().toString().trim())){
                    studentNumber.setError("Input required");
                }

                else if(TextUtils.isEmpty(password.getText().toString().trim())){
                    password.setError("Input required");
                }

                else if(!TextUtils.isDigitsOnly(studentNumber.getText().toString().trim())){
                    studentNumber.setError("Input numbers only");
                }

                else{
                    int stud_num = Integer.parseInt(studentNumber.getText().toString().trim());
                    String pass = password.getText().toString().trim();

                    studentNum = stud_num;
                    Login(MainActivity.this,stud_num,pass);

                }
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    public void Login(final Context context, final int student_number, String password){

        ContentValues contentValues = new ContentValues();

        final String userString = Integer.toString(student_number);
        final String passString = password;

        contentValues.put("STUDENT_NUM",userString);
        contentValues.put("USER_PASSWORD",passString);

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/Login.php",contentValues) {
            @Override
            protected void onPostExecute(String output) {

                    if (output.equals("0")) {
                        Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show();
                    }

                    else {
                        try {

                            JSONArray jsonArray = new JSONArray(output);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            final String id = jsonObject.getString("USER_ID");

                            userID = Integer.parseInt(id);
                            safeName = jsonObject.getString("SAFE_NAME");


                            Toast.makeText(context,"Successfully logged in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, MenuActivity.class));
                            finish();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
            }
        }.execute();
    }
}
