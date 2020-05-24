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
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_register);

       final EditText nameAndSurname = findViewById(R.id.nameAndSurname);
       final EditText studentNumber = findViewById(R.id.stdNumber);
       final EditText createPass = findViewById(R.id.createPass);
       final EditText veriPass = findViewById(R.id.veriPass);
       final EditText Email = findViewById(R.id.email);
       final EditText veriEmail = findViewById(R.id.veriEmail);
       Button singUpButton = findViewById(R.id.regiButton);

       singUpButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //


               //variables to be passed
               String[] splitter = nameAndSurname.getText().toString().trim().split(" "); //split name and surname
               String name = splitter[0];
               String surname = splitter[1];
               String student_num = studentNumber.getText().toString().trim();
               String email = Email.getText().toString().trim();
               String password = createPass.getText().toString().trim();

               //checking if we have input
               if(TextUtils.isEmpty(nameAndSurname.getText().toString().trim())){
                   nameAndSurname.setError("Input Required");
               }

               if(TextUtils.isEmpty(studentNumber.getText().toString().trim())){
                   studentNumber.setError("Input required");
               }

               if(TextUtils.isEmpty(createPass.getText().toString().trim())){
                   createPass.setError("Input required");
               }

               if(TextUtils.isEmpty(veriPass.getText().toString().trim())){
                   veriPass.setError("Input required");
               }

               if(TextUtils.isEmpty(Email.getText().toString().trim())){
                   Email.setError("Input required");
               }

               if(TextUtils.isEmpty(veriEmail.getText().toString().trim())){
                   veriEmail.setError("Input required");
               }

               if(!password.equals(veriPass.getText().toString().trim())){
                   veriPass.setError("Password mismatch");
               }

               if(!email.equals(veriEmail.getText().toString().trim())){
                   veriEmail.setError("Email mismatch");
               }

               else{
                   int intPass = Integer.parseInt(password);
                   int intStdNumber = Integer.parseInt(student_num);
                   Register(Register.this,name,surname,email,intPass,intStdNumber);
               }
           }
       });
    }

    @SuppressLint("StaticFieldLeak")
    public void Register(final Context context, String username, String surname, String email, int password, int studentNumber){

        ContentValues contentValues = new ContentValues();

        contentValues.put("USER_ID",studentNumber);
        contentValues.put("USER_NAME",username);
        contentValues.put("USER_SURNAME",surname);
        contentValues.put("USER_EMAIL",email);
        contentValues.put("USER_PASSWORD",password);

        new ServerCommunicator("http://lamp.ms.wits.ac.za/~s1872817/Register.php",contentValues){


            @Override
            protected void onPostExecute(String output) {

                if(!output.equals("1")){

                    Toast.makeText(context,"Change Password or Username", Toast.LENGTH_SHORT).show();

                }

                else{

                    Toast.makeText(context,"Successfully Registered",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, Login.class));
                }

            }
        }.execute();


    }
}
