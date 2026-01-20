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

    public void translate(Vector velocity) {
        this.x += velocity.getDx();
        this.y += velocity.getDy();
    }

    public double distanceTo(Point other) {
        double dx = getX() - other.getX();
        double dy = getY() - other.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }
}
