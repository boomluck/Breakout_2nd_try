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

    public void checkBallCollision() {
        for(int i = 0; i < getBalls().size(); i++) {
            for(int j = i + 1; j < getBalls().size(); j++) {
                Ball a = balls.get(i);
                Ball b = balls.get(j);

                if(a.getPoint().distanceTo(b.getPoint()) <= a.getRadius() + b.getRadius()) {
                    a.collisionWithBall(b);
                }
            }
        }
    }

    @Override
    public void update() {
        for(Ball ball : getBalls()) {
            ball.move();

            Wall wallCollision = ball.checkWallCollision(this);

            if (wallCollision != Wall.NONE) {
                ball.collisionWithWall(wallCollision);
            }
        }
    }
}
