package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { /*we will use this activity as the login activity. I am lazy to do the copy
                                                       I am lazy to copy and rearrange. Khutso*/


//the xml can be edited at this point I am concerned with the functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login_button = findViewById(R.id.login_button);
        TextView forgortPassword = findViewById(R.id.textView2);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        forgortPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

}
