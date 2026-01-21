package com.nhnacademy;

import javafx.scene.paint.Color;

public class BoundedBall extends MovableBall {
    public BoundedBall(Point point, double radius, Color color, Vector velocity) {
    super(point,radius,color,velocity);
    }

    @Override
    public Wall checkWallCollision(World world) {
        Point p = getPoint();
        double r = getRadius();

        if(p.getX() - r <= 0) { return Wall.LEFT; }
        if(p.getX() + r >= world.getWidth()) { return Wall.RIGHT; }
        if(p.getY() - r <= 0) { return Wall.TOP; }
        if(p.getY() + r >= world.getHeight()) { return Wall.BOTTOM; }

        return Wall.NONE;
    }

    @Override
    public void resolveCollisionWithWall(Wall wall) {
        switch(wall) {
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

    @Override
    public void resolveCollisionWithBall(Ball other) {
        Vector temp = getVelocity();

        if (other instanceof BoundedBall) {
            setVelocity(((BoundedBall) other).getVelocity());
            ((BoundedBall) other).setVelocity(temp);
        }
    }
}
