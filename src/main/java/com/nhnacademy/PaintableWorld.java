package com.nhnacademy;

import java.util.Random;

import javafx.scene.paint.Color;

public class PaintableWorld extends World {
    Random random = new Random();

    public PaintableWorld(double width, double height) {
        super(width, height);
    }

    public void addRandomBall(int numberOfBalls) {
        for(int i = 0; i < numberOfBalls; i++) {
            double randomRadius = random.nextDouble(10, 50);
            double randomX = random.nextDouble(0, getHeight());
            double randomY = random.nextDouble(0, getHeight());
            Color randomColor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

            balls.add(new PaintableBall(new Point(randomX, randomY), randomRadius, randomColor));
        }
    }
}
