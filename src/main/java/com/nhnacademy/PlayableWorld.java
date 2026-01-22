package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;

public class PlayableWorld extends MixedWorld {
    Paddle paddle;

    public PlayableWorld(double width, double height) {
        super(width, height);
    }

    public void addPaddle() {
        paddle = new Paddle(new Point(350, 550), 100, 20, new Vector(0, 0));
    }

    @Override
    public void draw(GraphicsContext gc) {
        for(Brick brick : getBricks()) {
            brick.drawBricks(gc);
        }
        for(Ball ball : getBalls()) {
            ball.drawBalls(gc);
        }
        paddle.drawPaddle(gc);
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

        // 4. 공과 패들의 충돌 판단 로직
        checkPaddleCollision();
    }

    public void leftKeyPressed() {
        if(paddle.getPoint().getX() > 0) {
            paddle.leftKeyPressed();
        }
    }

    public void rightKeyPressed() {
        if(paddle.getPoint().getX() + paddle.getWidth() < width) {
            paddle.rightKeyPressed();
        }
    }

    public void leftKeyReleased() {
        paddle.leftKeyReleased();
    }

    public void rightKeyReleased() {
        paddle.rightKeyReleased();
    }

    public void checkPaddleCollision() {
        Ball ball = balls.getFirst();

        double ballX = ball.getPoint().getX();
        double ballY = ball.getPoint().getY();
        double r = ball.getRadius();

        double paddleX = paddle.getPoint().getX();
        double paddleY = paddle.getPoint().getY();
        double paddleW = paddle.getWidth();
        double paddleH = paddle.getHeight();

        // 좌측면을 충돌하는 경우
        if(ballX - r >= 0
                && ballX + r <= paddleX
                && ballY >= paddleY
                && ballY <= paddleY + paddleH) {
            if(ballX + r >= paddleX) {
                ball.resolveCollisionWithPaddle(Wall.RIGHT, paddle);
            }
        }
        // 우측면을 충돌하는 경우
        if(ballX - r >= paddleW + paddleX
                && ballX + r <= getWidth()
                && ballY >= paddleY
                && ballY <= paddleY + paddleH) {
            if(ballX - r <= paddleX + paddleW) {
                ball.resolveCollisionWithPaddle(Wall.LEFT, paddle);
            }
        }
        // 상측면을 충돌하는 경우
        if(ballY - r >= 0
                && ballY + r <= paddleY
                && ballX >= paddleX
                && ballX <= paddleX + paddleW) {
            if(ballY + r >= paddleY) {
                ball.resolveCollisionWithPaddle(Wall.BOTTOM, paddle);
            }
        }
    }
}
