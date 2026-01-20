package com.nhnacademy;

public class Vector {
    double dx;
    double dy;

    public Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void reflectDx() { dx = -dx; }

    public void reflectDy() { dy = -dy; }

    public double magnitude() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    public Vector normalize() {
        double magnitude = magnitude();
        
        return new Vector(this.dx/magnitude, this.dy/magnitude);
    }
}