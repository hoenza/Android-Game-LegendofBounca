package com.RAHA.Activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.RAHA.Config.Config;

public class GravityActivity extends GameActivity implements SensorEventListener {

    protected void initSensor() {
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (timestamp != 0) {
            final float dt = (event.timestamp - timestamp) * Config.NS2S;
            float axisY = -event.values[0];
            float axisX = event.values[1];
            float axisZ = event.values[2];
            this.updateAngles(axisX, axisY, axisZ, dt);
        }
        timestamp = event.timestamp;
    }

    @Override
    protected void updateAngles(float axisX, float axisY, float axisZ, float dt) {
        room.angleX = (float) Math.asin(axisX / Config.g);
        room.angleY = (float) Math.asin(axisY / Config.g);
        room.angleZ = (float) Math.asin(axisZ / Config.g);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}