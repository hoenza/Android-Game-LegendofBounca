package com.RAHA.Activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.RAHA.Config.Config;

public class GyroscopeActivity extends GameActivity implements SensorEventListener {
    protected void initSensor() {
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (timestamp != 0) {
            final float dt = (event.timestamp - timestamp) * Config.NS2S;
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];
            this.updateAngles(axisX, axisY, axisZ, dt);
        }
        timestamp = event.timestamp;
    }

    @Override
    protected void updateAngles(float axisX, float axisY, float axisZ, float dt) {
        room.angleX += axisX * dt;
        room.angleY += axisY * dt;
        room.angleX += axisZ * dt;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}