package com.RAHA.Logic;

public class Room {
    int width;
    int height;
    Ball ball = null;
    int ballRadius = 200;

    public void setSize(int width, int Height) {
        this.width = width;
        this.height = height;
    }

    public void addBall(int x, int y) {
        this.ball = new Ball(x, y);
    }

    public int getBallRadius() {
        return ballRadius;
    }
}
