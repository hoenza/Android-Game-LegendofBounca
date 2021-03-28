package com.RAHA.Activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import com.RAHA.Config.Config;

public class GravityActivity extends GameActivity {

    protected void initSensor() {
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    public void onSensorChanged(SensorEvent event) {
        // This time step's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        if (timestamp != 0) {
            final float dT = (event.timestamp - timestamp) * Config.NS2S;
            // Axis of the rotation sample, not normalized yet.
            //values[0]: Angular speed around the x-axis
            //values[1]: Angular speed around the y-axis
            //values[2]: Angular speed around the z-axis
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            ////////////////////////////////////
            // Code goes here
            ///////////////////////////////////

        }
        timestamp = event.timestamp;
    }
}