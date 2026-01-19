package com.nhnacademy;

import javafx.scene.paint.Color;

public class BoundedBall extends MovableBall {
    public BoundedBall(Point point, double radius, Color color, Vector velocity) {
    super(point,radius,color,velocity);
    }

    @Override
    public boolean isOutOfBounds(World world) {
        Point p = getPoint();

        return p.getX() <= 0
            || p.getX() >= world.getWidth()
            || p.getY() <= 0
            || p.getY() >= world.getHeight();
    }

    public void onBoundaryCollision(World world) {
        
    }
}
