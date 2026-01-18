package com.nhnacademy;

public class Vector {
    double x;
    double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector add(Vector vector) {
        return new Vector(this.x + vector.getX(), this.y + vector.getY());
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector normalize() {
        double magnitude = magnitude();
        
        return new Vector(this.x/magnitude, this.y/magnitude);
    }
}
