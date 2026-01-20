package com.nhnacademy;

import javafx.scene.paint.Color;

public class BoundedWorld extends MovableWorld{
    public BoundedWorld(double width, double height) {
        super(width, height);
    }

    public void addRandomBoundedBalls(int numberOfBalls) {
        for(int i = 0; i < numberOfBalls; i++) {
            double randomRadius = random.nextDouble(10, 50);
            double randomX = random.nextDouble(0, getHeight());
            double randomY = random.nextDouble(0, getHeight());
            Color randomColor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
            Vector randomVector = new Vector(random.nextDouble(-4, 4), random.nextDouble(-4, 4));

            balls.add(new BoundedBall(new Point(randomX, randomY), randomRadius, randomColor, randomVector));
        }
    }

    @Override
    public void update() {
        for(Ball ball : getBalls()) {
            ball.move();

            Walls collisionTo = ball.collidesTo(this);

            if (collisionTo != Walls.NONE) {
                ball.reflect(collisionTo);
            }
        }
    }
}
