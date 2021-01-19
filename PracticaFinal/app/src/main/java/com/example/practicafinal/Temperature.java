package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Temperature extends AppCompatActivity implements SensorEventListener {

    public TextView tTemperatura;
    private SensorManager sensorManager;
    private Sensor sensorTemperature;
    private boolean isTemperatureSensorAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        tTemperatura = (TextView) findViewById(R.id.tTemperatura);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSensorAvailable = true;
        } else{
            tTemperatura.setText("Temperature sensor is not available");
            isTemperatureSensorAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        tTemperatura.setText(sensorEvent.values[0] + " ºC");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(isTemperatureSensorAvailable){
            sensorManager.registerListener(this, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(isTemperatureSensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}