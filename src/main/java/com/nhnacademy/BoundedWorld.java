package com.nhnacademy;

public class BoundedWorld extends MovableWorld{
    public BoundedWorld(double width, double height) {
        super(width, height);
    }

    @Override
    public void update() {
        for(Ball ball : getBalls()) {
            ball.move();

            if(ball.isOutOfBounds(this)) {
                ball.onBoundaryCollision(this);
            }
        }
    }
}
