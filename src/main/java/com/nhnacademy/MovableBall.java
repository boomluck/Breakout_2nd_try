package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MovableBall extends PaintableBall {
    Vector velocity;

    public MovableBall(Point point, double radius, Color color, Vector velocity) {
        super(point, radius, color);
        this.velocity = velocity;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void move() {
        point.translate(velocity);
    }

    @Override
    public void drawBalls(GraphicsContext gc) {
        Point point = getPoint();
        double radius = getRadius();
        Color color = getColor();

        double x = point.getX() - radius;
        double y = point.getY() - radius;
        double w = radius * 2;
        double h = radius * 2;

        gc.setFill(color);
        gc.fillOval(x, y, w, h);

        Vector velocity = getVelocity();
        gc.setFill(Color.BLACK);
        gc.fillText(Double.toString(velocity.getDx()) + Double.toString(velocity.getDy()), x, y);
    }
}
