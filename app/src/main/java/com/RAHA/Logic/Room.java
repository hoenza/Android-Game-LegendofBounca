package com.RAHA.Logic;

import android.widget.ImageView;

import com.RAHA.Config.Config;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Room {
    int width = 0;
    int height = 0;
    Ball ball = null;
    int ballRadius = Config.ballRadius;
    public float angleX, angleY, angleZ;
    ImageView ballImage;

    class RefreshRoom extends TimerTask {
        public void run() {
            runRoom(0.015);
        }
    }

    public Room() {
        Timer t = new Timer();
        t.schedule(new RefreshRoom(), 500, (long) Config.REFRESH_RATE);
    }

    private void refreshScene() {
        if(ballImage != null) {
            ballImage.setX(ball.x - ballRadius);
            ballImage.setY(ball.y - ballRadius);
        }
    }

    protected synchronized void runRoom(double dt) {
        ball.updateAcceleration(angleX, angleY, angleZ);
        ball.updateVelocity(dt);
        ball.updatePlace(dt, this);
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

    public Vector<Integer> hitWallStatus(float x, float y) {
        Vector<Integer> output = new Vector<>();
        if (y - Config.ballRadius <= 0)
            output.add(1);
        if (y + Config.ballRadius >= this.height)
            output.add(3);
        if (x - Config.ballRadius <= 0)
            output.add(4);
        if (x + Config.ballRadius >= this.width)
            output.add(2);
        return output;
    }
}
