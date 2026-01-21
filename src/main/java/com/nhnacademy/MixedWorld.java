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

    public void addBrick(Brick brick) {
        bricks.add(brick);
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

    public void removeBrick(Brick brick) {
        bricks.remove(brick);
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

    @Override
    public void update() {
        // 1. 공과 벽의 충돌 판단 로직
        for(Ball ball : getBalls()) {
            ball.move();

            Wall wallCollision = ball.checkWallCollision(this);

            if (wallCollision != Wall.NONE) {
                ball.resolveCollisionWithWall(wallCollision);
            }
        }

        // 2. 공과 공의 충돌 판단 로직
        checkBallCollision();

        // 3. 공과 브릭의 충돌 판단 로직
        checkBrickCollision();
    }

    public void checkBrickCollision() {
        Ball ball = balls.getFirst();

        double ballX = ball.getPoint().getX();
        double ballY = ball.getPoint().getY();
        double r = ball.getRadius();

        for(Brick brick : getBricks()) {
            double brickX = brick.getPoint().getX();
            double brickY = brick.getPoint().getY();
            double brickW = brick.getWidth();
            double brickH = brick.getHeight();

            // 브릭의 좌측면을 충돌하는 경우
            if(ballX - r >= 0
            && ballX + r <= brickX
            && ballY >= brickY
            && ballY <= brickY + brickH) {
                if(ballX + r >= brickX) {
                    ball.resolveCollisionWithWall(Wall.RIGHT);
                }
            }
            // 브릭의 우측면을 충돌하는 경우
            if(ballX - r >= brickW + brickX
            && ballX + r <= getWidth()
            && ballY >= brickY
            && ballY <= brickY + brickH) {
                if(ballX - r <= brickX + brickW) {
                    ball.resolveCollisionWithWall(Wall.LEFT);
                }
            }
            // 브릭의 상측면을 충돌하는 경우
            if(ballY - r >= 0
            && ballY + r <= brickY
            && ballX >= brickX
            && ballX <= brickX + brickW) {
                if(ballY + r >= brickY) {
                    ball.resolveCollisionWithWall(Wall.BOTTOM);
                }
            }
            // 브릭의 하측면을 충돌하는 경우
            if(ballY - r >= brickY + brickH
            && ballY + r <= getHeight()
            && ballX >= brickX
            && ballX <= brickX + brickW) {
                if(ballY - r <= brickY + brickH) {
                    ball.resolveCollisionWithWall(Wall.TOP);
                }
            }
        }
    }
}
