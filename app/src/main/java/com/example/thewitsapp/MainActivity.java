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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity { /*we will use this activity as the login activity. I am lazy to do the copy
                                                       I am lazy to copy and rearrange. Khutso*/
    public static int userID;

//the xml can be edited at this point I am concerned with the functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //

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
                    int pass = Integer.parseInt(password.getText().toString().trim());

                    Login(MainActivity.this,stud_num,pass);
                }
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    public void Login(final Context context, final int student_number, int password){

        ContentValues contentValues = new ContentValues();

        contentValues.put("USER_ID",student_number);
        contentValues.put("USER_PASSWORD",password);

        new ServerCommunicator("http://lamp.ms.wits.ac.za/~s1872817/Login.php",contentValues) {
            @Override
            protected void onPostExecute(String output) {
                if(output.equals("1")){
                    userID = student_number;
                    Intent intent = new Intent(context,MenuActivity.class);
                    Toast.makeText(context,"Successfully Logged in",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                    //make sure the are sure you want to exit is done
                }

                else{
                    userID = student_number;
                    Toast.makeText(context,"Invalid Login input",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.execute();
    }

}
