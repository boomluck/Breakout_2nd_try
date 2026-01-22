package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle {
    Point point;
    double width;
    double height;

    public Paddle(Point point, double width, double height) {
        this.point = point;
        this.width = width;
        this.height = height;
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

    public void leftKeyPressed() {
        point = new Point(point.getX() - 5, point.getY());
    }

    public void rightKeyPressed() {
        point = new Point(point.getX() + 5, point.getY());
    }

    public void drawPaddle(GraphicsContext gc) {
        double x = point.getX();
        double y = point.getY();

        gc.setFill(Color.BLACK);
        gc.fillRoundRect(x, y, width, height, 10, 10);
    }
}
