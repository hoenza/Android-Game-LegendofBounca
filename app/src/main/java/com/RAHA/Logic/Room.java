package com.RAHA.Logic;

import android.util.Pair;

import com.RAHA.Config.Config;

import java.util.Timer;
import java.util.TimerTask;

public class Room {
    int width = 0;
    int height = 0;
    Ball ball = null;
    int ballRadius = 200;
    float muS = (float) 0.15;
    float muK = (float) 0.07;

    public Room() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(this.hasDimensions() && ball != null){
                    System.out.println("room is being ran");
                    runRoom(((double) Config.REFRESH_RATE) / 1000);
                }
            }

            private boolean hasDimensions() {
                return width != 0 && height != 0;
            }
        }, 0, (long) Config.REFRESH_RATE);
    }

    protected synchronized void runRoom(double dt) {
//        ball.updateAccelerationByAngles(angleX, angleY, angleZ);
//        ball.updateVelocity(intervalSeconds, this);
//        Pair<Double, Double> ballNewPositions = ball.getNextPosition(intervalSeconds);
//        ball.handleWallCollision(ballNewPositions.first, ballNewPositions.second, this);
//        ball.updateAccelerationByAngles(angleX, angleY, angleZ);
//        ball.updateVelocity(intervalSeconds, this);
        ball.updatePlace(dt);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addBall(int x, int y) {
        this.ball = new Ball(x, y);
        System.out.println("add ball called");
    }

    public int getBallRadius() {
        return ballRadius;
    }

    public void shootBall() {
        System.out.println("ball is being shot");
        ball.shoot();
    }
}
