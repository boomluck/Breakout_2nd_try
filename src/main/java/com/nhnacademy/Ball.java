package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
    Point point;
    double radius;

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

    public void drawBalls(GraphicsContext gc) {
        Point point = getPoint();
        double radius = getRadius();

        double x = point.getX() - radius;
        double y = point.getY() - radius;
        double w = radius * 2;
        double h = radius * 2;
        
        gc.setFill(Color.BLACK);
        gc.fillOval(x, y, w, h);
    }

    public Walls collidesTo(World world) {
        // BoundedBall에 최초로 등장하는 isOutOfBounds(World world)메서드의 타입 충돌 문제를 해결하기 위한 임시 방편
        return Walls.NONE;
    }

    public void move() {
        // MovableBall에 최초로 등장하는 move()메서드의 타입 충돌 문제를 해결하기 위한 임시 방편
    }

    public void reflect(Walls collisionTo) {
    }
}
