package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class World {
    double width;
    double height;
    List<Ball> balls = new ArrayList<>();

    public World(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void removeBalls(Ball ball) {
        balls.remove(ball);
    }

    public List<Ball> getBalls() {
        return new ArrayList<>(balls);
    }

    public void draw(GraphicsContext gc) {
        for(Ball ball : getBalls()) {
            ball.drawBalls(gc);
        }
    }
}
