package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle {
    Point point;
    double width;
    double height;
    Vector velocity;

    public Paddle(Point point, double width, double height, Vector velocity) {
        this.point = point;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }

    public Point getPoint() {
        return point;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void leftKeyPressed() {
        velocity = new Vector(-10, 0);
        point.translate(velocity);
    }

    public void rightKeyPressed() {
        velocity = new Vector(10, 0);
        point.translate(velocity);
    }

    public void leftKeyReleased() {
        velocity = new Vector(0, 0);
        point.translate(velocity);
    }

    public void rightKeyReleased() {
        velocity = new Vector(0, 0);
        point.translate(velocity);
    }

    public void drawPaddle(GraphicsContext gc) {
        double x = point.getX();
        double y = point.getY();

        gc.setFill(Color.BLACK);
        gc.fillRoundRect(x, y, width, height, 10, 10);
    }
}
