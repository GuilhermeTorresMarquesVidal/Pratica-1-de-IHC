package com.example.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        send.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToSecondActivity();
                    }
                }
        );
    }

    private void initialize(){
        message = findViewById(R.id.message);

        send = findViewById(R.id.send);
        send.setBackgroundColor(Color.GRAY);
    }

    private void goToSecondActivity(){

        Intent intent = new Intent(this, Receive.class);
        intent.putExtra("message",message.getText().toString());

        startActivity(intent);
    }
}