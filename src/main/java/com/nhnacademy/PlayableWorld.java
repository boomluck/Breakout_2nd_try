package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class PlayableWorld extends MixedWorld {
    Paddle paddle;
    Boolean gameOver = false;

    public PlayableWorld(double width, double height) {
        super(width, height);
    }

    public void addPaddle() {
        paddle = new Paddle(new Point(350, 550), 100, 20, new Vector(0, 0));
    }

    public void setGameOver() {
        gameOver = true;
    }

    public void gameOver(GraphicsContext gc) {
        gc.setFont(Font.font(24));
        gc.setFill(Color.BLACK);
        gc.fillText("GAME OVER", 350, 300);
        gc.fillText("Press SPACE BAR to restart", 300, 330);
    }

    @Override
    public void draw(GraphicsContext gc) {
//        for(Brick brick : getBricks()) {
//            brick.draw(gc);
//        }
//        for(Ball ball : getBalls()) {
//            ball.draw(gc);
//        }
//        paddle.draw(gc);

        for(Drawable obj : objects) {
            obj.draw(gc);
        }

        drawScore(gc);
    }

    @Override
    public void update() {
        // 1. 공과 벽의 충돌 판단 로직
        for(Ball ball : getBalls()) {
            ball.move();

            Wall wall = ball.checkWallCollision(this);

            if (wall != Wall.NONE) {
                ball.resolveCollisionWithWall(wall, this);
            }
        }

        // 2. 공과 공의 충돌 판단 로직
        //checkBallCollision();

        // 3. 공과 브릭의 충돌 판단 로직
        checkBrickCollision();

        // 4. 공과 패들의 충돌 판단 로직
        checkPaddleCollision();

        // 5. 점수

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
