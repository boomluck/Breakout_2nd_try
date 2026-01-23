package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintableBall extends Ball {
    Color color;
    Color DEFAULT_COLOR = Color.BLACK;

    public PaintableBall(Point point, double radius, Color color) {
        super(point, radius);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Point point = getPoint();
        double radius = getRadius();
        Color color = getColor();

        double x = point.getX() - radius;
        double y = point.getY() - radius;
        double w = radius * 2;
        double h = radius * 2;
        
        gc.setFill(color);
        gc.fillOval(x, y, w, h);
    }
}
