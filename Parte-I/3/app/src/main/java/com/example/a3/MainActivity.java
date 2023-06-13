package com.example.a3;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;

    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    private TextView x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        initializeGravitySensor();
    }

    private void initialize(){

        // Initialize Text View
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
    }

    private void initializeGravitySensor(){

        // Initialize sensor Manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(sensor != null){
            sensorManager.registerListener(
                    new SensorEventListener(){
                        @Override
                        public void onSensorChanged(SensorEvent event){
                            if(Math.abs(event.values[1]) > 9.3){
                                goToSecondActivity();
                            }
                            x.setText("X   " + decfor.format(event.values[0]));
                            y.setText("Y   " + decfor.format(event.values[1]));
                            z.setText("Z   " + decfor.format(event.values[2]));
                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int i) {

                        }
                    },sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void goToSecondActivity(){
        Intent intent = new Intent(this, Message.class);
        startActivity(intent);
    }
}