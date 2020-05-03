package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SafeSpace extends AppCompatActivity {
    Button chatRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_space);

        chatRoom = findViewById(R.id.chatRoomBtn);
        chatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SafeSpace.this, safeSpaceDiscussions.class));
            }
        });

    }
}
