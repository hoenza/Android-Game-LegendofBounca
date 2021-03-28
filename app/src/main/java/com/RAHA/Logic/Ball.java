package com.RAHA.Logic;

public class Ball {
    int m = 10;
    double x;
    double y;
    double vx = 0, vy = 0;
    double ax = 0, ay = 0;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void shoot() {
        vx = (Math.random() - 0.5) * 10 ;
        vy = (Math.random()) * 5;
    }

    public void updatePlace(double dt) {
        x += (1/2) * ax * Math.pow(dt, 2) + vx * dt;
        y += (1/2) * ay * Math.pow(dt, 2) + vy * dt;
    }
}
