package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    private SensorManager sensorManager;
    private Sensor light, gy;

    private TextView lightView, x, y, z, getGPSBtn, lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        initializeLightSensor();

        initializeGyroscopic();

        initializeGPS();
    }

    private void initialize(){

        // Initialize Text View
        lightView = findViewById(R.id.light);
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);

        // Initialize GPS
        getGPSBtn = (Button) findViewById(R.id.getGPSBtn);
        lat = findViewById(R.id.Lat);
        lon = findViewById(R.id.Long);

        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    private void initializeLightSensor(){

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(light != null){
            sensorManager.registerListener(
                    new SensorEventListener(){
                        @Override
                        public void onSensorChanged(SensorEvent event){
                            lightView.setText("Itensidade luminosa: " + decfor.format(event.values[0]));
                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int i) {

                        }
                    },light, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void initializeGyroscopic(){

        gy = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gy != null){
            sensorManager.registerListener(
                    new SensorEventListener(){
                        @Override
                        public void onSensorChanged(SensorEvent event){
                            x.setText("X: " + decfor.format(event.values[0]));
                            y.setText("Y: " + decfor.format(event.values[1]));
                            z.setText("Z: " + decfor.format(event.values[2]));
                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int i) {

                        }
                    },gy, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void initializeGPS(){
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        getGPSBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        GPSTracker g = new GPSTracker(getApplicationContext());
                        Location l = g.getLocation();
                        if(l != null){
                            /*double lat = l.getLatitude();
                            double lon = l.getLongitude();
                            Toast.makeText(getApplicationContext(),
                                "LAT:" + lat + "LONG:" + lon, Toast.LENGTH_LONG).show();*/
                            lat.setText("Lat: " + decfor.format(l.getLatitude()));
                            lon.setText("Long: " + decfor.format(l.getLongitude()));
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Fail on get location!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        }


}

