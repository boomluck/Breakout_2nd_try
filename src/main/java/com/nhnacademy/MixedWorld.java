package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MixedWorld extends BoundedWorld {
    List<Brick> bricks = new ArrayList<>();

    public MixedWorld(double width, double height) {
        super(width, height);
    }

    public void addBricks() {
        int COLUMNS = 5;
        double brickWidth = 38;
        double brickHeight = 20;
        double wallGap = 2;
        double brickGap = 1;
        double usableWidth = getWidth() - wallGap * 2;
        int brickPerRow = (int) (usableWidth / (brickWidth + brickGap * 2));
        double totalBrickWidth = brickPerRow * brickWidth + (brickPerRow - 1) * brickGap;
        double remain = usableWidth - totalBrickWidth;
        double x = wallGap + remain / 2;
        double y = wallGap;
        Color brickColor = Color.BLUE;

        for(int i=0; i < COLUMNS; i++) {
            for (int j = 0; j < brickPerRow; j++) {
                bricks.add(new Brick(new Point(x + j * (brickWidth + brickGap), y + i * (brickHeight + brickGap * 2)), brickWidth, brickHeight, brickColor));
            }
        }
    }

    public List<Brick> getBricks() {
        return new ArrayList<>(bricks);
    }

    @Override
    public void draw(GraphicsContext gc) {
        for(Ball ball : getBalls()) {
            ball.drawBalls(gc);
        }
        for(Brick brick : getBricks()) {
            brick.drawBricks(gc);
        }
    }
}
