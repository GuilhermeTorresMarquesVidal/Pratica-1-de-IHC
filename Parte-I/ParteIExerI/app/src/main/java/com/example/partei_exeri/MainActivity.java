package com.example.partei_exeri;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText first_number, second_number;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){

                        double first, second, sum;

                        first = Double.parseDouble(first_number.getText().toString());
                        second = Double.parseDouble(second_number.getText().toString());
                        sum = first + second;

                        result.setText("RESULT " + sum);
                    }
                }
        );
    }

    private void initialize(){
        result = findViewById(R.id.result);

        first_number = findViewById(R.id.first_number);
        second_number = findViewById(R.id.second_number);

        button = findViewById(R.id.calculate_button);
        button.setBackgroundColor(Color.BLUE);
    }
}