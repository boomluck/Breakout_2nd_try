package com.nhnacademy;

import javafx.scene.paint.Color;

public class MovableWorld extends PaintableWorld{
    public MovableWorld(double width, double height) {
        super(width, height);
    }

    public void addRandomMovableBalls(int numberOfBalls) {
        for(int i = 0; i < numberOfBalls; i++) {
            double randomRadius = random.nextDouble(10, 50);
            double randomX = random.nextDouble(0, getHeight());
            double randomY = random.nextDouble(0, getHeight());
            Color randomColor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
            Vector randomVector = new Vector(random.nextDouble(-4, 4), random.nextDouble(-4, 4));

            balls.add(new MovableBall(new Point(randomX, randomY), randomRadius, randomColor, randomVector));
        }
    }

    public void update() {
        for(Ball ball : getBalls()) {
            ball.move();
        }
    }
}