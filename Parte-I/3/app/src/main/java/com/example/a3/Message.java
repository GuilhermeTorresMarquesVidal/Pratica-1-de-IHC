package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Message extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        initializeGravitySensor();
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
                            if(Math.abs(event.values[1]) < 8.8){
                                finish();
                            }
                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int i) {

                        }
                    },sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}