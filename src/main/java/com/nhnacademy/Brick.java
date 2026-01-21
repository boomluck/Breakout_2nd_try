package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick {
    Point point;
    double width;
    double height;
    Color color;

    public Brick(Point point, double width, double height, Color color) {
        this.point = point;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void drawBricks(GraphicsContext gc) {
        double x = point.getX();
        double y = point.getY();

        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }
}
