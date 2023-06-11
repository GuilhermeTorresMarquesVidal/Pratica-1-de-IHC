package com.example.partiexerii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Receive extends AppCompatActivity {

    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Initialize();
    }

    private void Initialize(){

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        messageView = findViewById(R.id.messageReceive);
        messageView.setText(message);
    }
}