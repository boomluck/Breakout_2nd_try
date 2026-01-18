package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {
    int width;
    int height;
    List<Ball> balls = new ArrayList<>();

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public List<Ball> getBalls() {
        return new ArrayList<>(balls);
    }

    public void drawBall(GraphicsContext gc) {
        for(Ball ball : getBalls()) {
            Point point = ball.getPoint();
            double radius = ball.getRadius();
            
            double x = point.getX() - radius;
            double y = point.getY() - radius;
            double w = radius * 2;
            double h = radius * 2;
            
            gc.setFill(Color.BLACK);
            gc.fillOval(x, y, w, h);
        }
    }
}
