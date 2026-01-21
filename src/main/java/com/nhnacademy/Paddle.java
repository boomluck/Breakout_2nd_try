package com.nhnacademy;

public class Paddle {
    Point point;
    double width;
    double height;
    Vector vector;

    public Paddle(Point point, double width, double height, Vector vector) {
        this.point = point;
        this.width = width;
        this.height = height;
        this.vector = vector;
    }
}
