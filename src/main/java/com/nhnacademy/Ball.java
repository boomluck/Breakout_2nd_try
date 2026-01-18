package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
}
