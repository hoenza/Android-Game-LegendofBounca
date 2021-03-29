package com.RAHA.Logic;

import android.util.Pair;
import android.widget.ImageView;

import com.RAHA.Config.Config;

import java.util.Timer;
import java.util.TimerTask;

public class Room {
    int width = 0;
    int height = 0;
    Ball ball = null;
    int ballRadius = 200;
    public float angleX, angleY, angleZ;
    ImageView ballImage;

    public Room() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(this.hasDimensions() && ball != null){
                    runRoom(((double) Config.REFRESH_RATE) / 1000);
                }
            }

            private boolean hasDimensions() {
                return width != 0 && height != 0;
            }
        }, 0, (long) Config.REFRESH_RATE);
    }

    private void refreshScene() {
        if(ballImage != null) {
            ballImage.setX(ball.x);
            ballImage.setY(ball.y);
        }
    }

    protected synchronized void runRoom(double dt) {
//        ball.updateVelocity(intervalSeconds, this);
//        Pair<Double, Double> ballNewPositions = ball.getNextPosition(intervalSeconds);
//        ball.handleWallCollision(ballNewPositions.first, ballNewPositions.second, this);
//        ball.updateAccelerationByAngles(angleX, angleY, angleZ);
//        ball.updateVelocity(intervalSeconds, this);
        ball.updateAcceleration(angleX, angleY, angleZ);
        ball.updateVelocity(dt);
        ball.updatePlace(dt);
        refreshScene();
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addBall(ImageView ballImage, int x, int y) {
        this.ballImage = ballImage;
        this.ball = new Ball(x, y);
    }

    public int getBallRadius() {
        return ballRadius;
    }

    public void shootBall() {
        ball.shoot();
    }
}
