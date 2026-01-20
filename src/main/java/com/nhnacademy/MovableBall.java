package com.nhnacademy;

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
}
