package com.RAHA.Logic;

import android.util.Log;
import android.widget.ImageView;

import com.RAHA.Config.Config;
import com.RAHA.Utils.Utils;

import java.util.Vector;

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
        vx = (float) ((Math.random() - 0.5) * 3000);
        vy = -(float) ((Math.random()) * 1500);
    }

    public void updatePlace(double dt, Room room) {
        x = (float) ((1/2) * ax * Math.pow(dt, 2) + vx * dt + x);
        y = (float) ((1/2) * ay * Math.pow(dt, 2) + vy * dt + y);
        handleWallCollision(room);
//        System.out.println(String.format("x = %f, y=%f\nvx = %f, vy = %f\nax=%f, ay = %f", x, y, vx, vy, ax, ay));
    }

    public void updateAcceleration(float angleX, float angleY) {
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
        } else {
            fX = 0;
            fY = 0;
        }
        ax = (float) fX / m;
        ay = (float) fY / m;

        ax *= 200;
        ay *= 200;

        Integer aMax = 10000;
        if (ax <= -aMax)
            ax = -aMax;
        if (ax >= aMax)
            ax = aMax;
        if (ay <= -aMax)
            ay = -aMax;
        if (ay >= aMax)
            ay = aMax;
    }

    public void updateVelocity(double dt) {
        vx += ax * dt;
        vy += ay * dt;
    }

    private boolean canMove(double fX, double fY, double N) {
        double fMagnitude = Utils.magnitude(fX, fY);
        double frictionMagnitude = N * Config.muS;
        return fMagnitude > frictionMagnitude;
    }

    private boolean isMoving() {
        if (Utils.magnitude(vx, vy) < 0.1) {
            return false;
        } else {
            return true;
        }
    }

    private void handleWallCollision(Room room) {
        Vector<Integer> hitStatus = room.hitWallStatus(x, y);
        if (hitStatus.contains(1)) {
            y = Config.ballRadius;
            if (vy < 0) {
                vy = (float) (Math.abs(this.vy) * Math.sqrt(Config.elasticLoss));
            }
        }
        if(hitStatus.contains(2)) {
            x = room.width - Config.ballRadius;
            if (vx > 0) {
                vx = (float) (-Math.abs(vx) * Math.sqrt(Config.elasticLoss));
            }
        }
        if(hitStatus.contains(3)) {
            y = room.height - Config.ballRadius;
            if (vy > 0) {
                vy = (float) (-Math.abs(vy) * Math.sqrt(Config.elasticLoss));
            }
        }
        if(hitStatus.contains(4)) {
            x = Config.ballRadius;
            if (vx < 0) {
                vx = (float) (Math.abs(vx) * Math.sqrt(Config.elasticLoss));
            }
        }
    }
}
