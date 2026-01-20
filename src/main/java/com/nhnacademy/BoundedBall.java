package com.nhnacademy;

import javafx.scene.paint.Color;

public class BoundedBall extends MovableBall {
    public BoundedBall(Point point, double radius, Color color, Vector velocity) {
    super(point,radius,color,velocity);
    }

    @Override
    public Walls collidesTo(World world) {
        Point p = getPoint();
        double r = getRadius();

        if(p.getX() - r <= 0) { return Walls.LEFT; }
        if(p.getX() + r >= world.getWidth()) { return Walls.RIGHT; }
        if(p.getY() - r <= 0) { return Walls.TOP; }
        if(p.getY() + r >= world.getHeight()) { return Walls.BOTTOM; }

        return Walls.NONE;
    }

    @Override
    public void reflect(Walls collisionTo) {
        switch(collisionTo) {
            case TOP:
            case BOTTOM: {
                velocity.reflectDy();
                break;
            }
            case LEFT:
            case RIGHT: {
                velocity.reflectDx();
                break;
            }
            default:
        }
    }
}
