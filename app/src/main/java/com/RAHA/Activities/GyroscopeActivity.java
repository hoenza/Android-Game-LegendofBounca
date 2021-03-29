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
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        System.out.println("gyro sensor changed");
        System.out.println("AAAAAAAAAAAAAAAAARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRr");
        // This time step's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        if (timestamp != 0) {
            final float dt = (event.timestamp - timestamp) * Config.NS2S;
            // Axis of the rotation sample, not normalized yet.
            //values[0]: Angular speed around the x-axis
            //values[1]: Angular speed around the y-axis
            //values[2]: Angular speed around the z-axis
            float axisY = -event.values[0];
            float axisX = event.values[1];
            float axisZ = event.values[2];
            this.updateAngles(axisX, axisY, axisZ, dt);
//            room.

            ////////////////////////////////////
            // Code goes here
            ///////////////////////////////////

        }
        timestamp = event.timestamp;
    }

    @Override
    protected void updateAngles(float axisX, float axisY, float axisZ, float dt) {
        System.out.println("here in gyro");
        room.angleX += axisX * dt;
        room.angleY += axisY * dt;
        room.angleX += axisZ * dt;
        System.out.println(String.format("angleX = %f, angleY = %f, angleZ=%f", room.angleX, room.angleY, room.angleZ));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}