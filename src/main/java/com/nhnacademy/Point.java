package com.nhnacademy;

public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void add(Vector velocity) {
        this.x += velocity.getDx();
        this.y += velocity.getDy();
    }
}
