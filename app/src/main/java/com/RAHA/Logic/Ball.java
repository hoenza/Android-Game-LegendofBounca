package com.RAHA.Logic;

import android.content.pm.ConfigurationInfo;
import android.util.Log;
import android.widget.ImageView;

import com.RAHA.Config.Config;
import com.RAHA.Utils.Utils;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ball {
    float x;
    float y;
    float vx = 0;
    float vy = 0;
    float ax = 0;
    float ay = 0;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void shoot() {
        vx = (float) ((Math.random() - 0.5) * 4000);
        vy = -(float) ((Math.random()) * 2000);
    }

    public void updatePlace(double dt, Room room) {
        x += (float) (ax * Math.pow(dt, 2) / 2 + vx * dt);
        y += (float) (ay * Math.pow(dt, 2) / 2 + vy * dt);
        handleWallCollision(room);
    }

    public void updateAcceleration(float angleX, float angleY) {
        double fX = Config.ballMass * Config.g * Math.sin(angleY);
        double fY = Config.ballMass * Config.g * Math.sin(angleX);
        double theta = Math.atan(Utils.magnitude(Math.sin(angleX), Math.sin(angleY)) / (Math.cos(angleX) + Math.cos(angleY)));
        double N = Config.ballMass * Config.g * Math.cos(theta);

        if (isStaticFriction(fX, fY, N)) {
            ax = 0; ay = 0;
            return;
        }
        if (Utils.magnitude(vx, vy) == 0) {
            ax = (float) (fX / Config.ballMass / Config.ballMass);
            ay = (float) (fY / Config.ballMass / Config.ballMass);
            return;
        }

        double frictionX = frictionMagnitude(N) * Math.abs(vx) / Utils.magnitude(vx, vy);
        double frictionY = frictionMagnitude(N) * Math.abs(vy) / Utils.magnitude(vx, vy);
        ax = (float) (fX- (Math.signum(vx) * frictionX)) / Config.ballMass / Config.ballMass;
        ay = (float) (fY- (Math.signum(vy) * frictionY)) / Config.ballMass / Config.ballMass;
    }

    public void updateVelocity(double dt) {
        vx += ax * dt;
        vy += ay * dt;
    }

    private boolean canMove(double fX, double fY, double N) {
        double forceMagnitude = Utils.magnitude(fX, fY);
        double staticFricitionTreshold = Config.muS * N;
        return forceMagnitude > staticFricitionTreshold;
    }

    private boolean isMoving() {
        return Utils.magnitude(vx, vy) < Config.stopThreshold;
    }

    private double frictionMagnitude(double N) {
        return N * Config.muK;
    }

    private boolean isStaticFriction(double fX, double fY, double N) {
        return !isMoving() && !canMove(fX, fY, N);
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
