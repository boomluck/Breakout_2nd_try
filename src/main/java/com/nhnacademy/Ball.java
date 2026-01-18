package com.nhnacademy;

public class Ball {
    private Point point;
    private double radius;

    public Ball(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    public Ball(double x, double y, double radius) {
        this.point = new Point(x, y);
        this.radius = radius;
    }

    public Point getPoint() {
        return point;
    }

    public double getRadius() {
        return radius;
    }
}
