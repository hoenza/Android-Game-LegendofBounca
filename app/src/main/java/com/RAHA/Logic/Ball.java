package com.RAHA.Logic;

import android.widget.ImageView;

import com.RAHA.Config.Config;
import com.RAHA.Utils.Utils;

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
        x = (float) ((1/2) * ax * Math.pow(dt, 2) + vx * dt + x);
        y = (float) ((1/2) * ay * Math.pow(dt, 2) + vy * dt + y);
        System.out.println(String.format("x = %f, y=%f\nvx = %f, vy = %f\nax=%f, ay = %f", x, y, vx, vy, ax, ay));
    }

    public void updateAcceleration(float angleX, float angleY, float angleZ) {
        double fX = m * Config.g * Math.sin(angleY);
        double fY = m * Config.g * Math.sin(angleX);
        double N = m * Config.g * Math.cos(Math.atan(Utils.magnitude(Math.sin(angleX), Math.sin(angleY)) / (Math.cos(angleX) + Math.cos(angleY))));

        if (this.isMoving() || this.canMove(fX, fY, N)) {
            double frictionMagnitude = N * Config.muK;
            double frictionX = 0;
            double frictionY = 0;
            if (Utils.magnitude(vx, vy) > 0) {
                frictionX = frictionMagnitude * vx / Utils.magnitude(vx, vy);
                frictionY = frictionMagnitude * vy / Utils.magnitude(vx, vy);
            }
            fX += -Math.signum(vx) * Math.abs(frictionX);
            fY += -Math.signum(vy) * Math.abs(frictionY);
//            Log.d("NG2", fX + "|" + fY + "|" + frictionMagnitude + "|" + frictionX + "|" + vx + "|" + vy);
        } else {
            fX = 0;
            fY = 0;
        }
        ax = (float) fX / m;
        ay = (float) fY / m;

        ax *= 200;
        ay *= 200;
    }

    public void updateVelocity(double dt) {
        vx += ax * dt;
        vy += ay * dt;
    }

    private boolean canMove(double fX, double fY, double N) {
        double fMagnitude = Utils.magnitude(fX, fY);
        double frictionMagnitude = N * Config.muK;
        return fMagnitude > frictionMagnitude;
    }

    private boolean isMoving() {
        if (Utils.magnitude(vx, vy) < 0.1) {
            return false;
        } else {
            return true;
        }
    }
}
