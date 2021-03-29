package com.RAHA.Logic;

import android.widget.ImageView;

public class Ball {
    int m = 10;
    float x;
    float y;
    float vx = 0, vy = 0;
    float ax = 0, ay = 0;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void shoot() {
        vx = (float) ((Math.random() - 0.5) * 300);
        vy = -(float) ((Math.random()) * 150);
    }

    public void updatePlace(double dt) {
        x += (1/2) * ax * Math.pow(dt, 2) + vx * dt;
        y += (1/2) * ay * Math.pow(dt, 2) + vy * dt;
    }

    public void updateAcceleration(float angleX, float angleY, float angleZ) {

    }
}
