package com.nhnacademy;

import javafx.scene.paint.Color;

public class BoundedWorld extends MovableWorld{
    public BoundedWorld(double width, double height) {
        super(width, height);
    }

    public void addRandomBoundedBalls(int numberOfBalls) {
        int created = 0;

        while(created < numberOfBalls) {
            double randomRadius = random.nextDouble(10, 50);
            double randomX = random.nextDouble(randomRadius, getWidth() - randomRadius);
            double randomY = random.nextDouble(randomRadius, getHeight() - randomRadius);
            Color randomColor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
            Vector randomVector = new Vector(random.nextDouble(-4, 4), random.nextDouble(-4, 4));

            boolean canSpawn = true;

            Ball candidate = new BoundedBall(new Point(randomX, randomY), randomRadius, randomColor, randomVector);

            for (int i = 0; i < getBalls().size(); i++) {
                Ball a = balls.get(i);

                if(a.getPoint().distanceTo(candidate.getPoint()) < a.getRadius() + candidate.getRadius()) {
                    canSpawn = false;
                    break;
                }
            }

            if(canSpawn) {
                balls.add(candidate);
                created++;
            }
        }
    }

    public void checkBallCollision() {
        for(int i = 0; i < getBalls().size(); i++) {
            for(int j = i + 1; j < getBalls().size(); j++) {
                Ball a = balls.get(i);
                Ball b = balls.get(j);

                if(a.getPoint().distanceTo(b.getPoint()) <= a.getRadius() + b.getRadius()) {
                    a.resolveCollisionWithBall(b);
                }
            }
        }
    }

    @Override
    public void update() {
        // 1. 공과 벽의 충돌 판단 로직
        for(Ball ball : getBalls()) {
            ball.move();

            Wall wall = ball.checkWallCollision(this);

            if (wall != Wall.NONE) {
                ball.resolveCollisionWithWall(wall);
            }
        }

        // 2. 공과 공의 충돌 판단 로직
        checkBallCollision();
    }
}
