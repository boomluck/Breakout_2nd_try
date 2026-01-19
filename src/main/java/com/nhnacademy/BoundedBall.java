package com.nhnacademy;

import javafx.scene.paint.Color;

public class BoundedBall extends MovableBall {
    public BoundedBall(Point point, double radius, Color color, Vector velocity) {
    super(point,radius,color,velocity);
    }

    @Override
    public boolean isOutOfBounds(World world) {
        for(Ball ball : world.getBalls()) {
            return ball.getPoint().getX() <= 0
                    || ball.getPoint().getX() >= world.getWidth()
                    || ball.getPoint().getY() <= 0
                    || ball.getPoint().getY() >= world.getHeight();
        }
        return false;
    }
}
