package com.nhnacademy;

import javafx.scene.canvas.GraphicsContext;

public class PlayableWorld extends MixedWorld {
    Paddle paddle;

    public PlayableWorld(double width, double height) {
        super(width, height);
    }

    public void addPaddle() {
        paddle = new Paddle(new Point(350, 550), 100, 20);
    }

    @Override
    public void draw(GraphicsContext gc) {
        for(Ball ball : getBalls()) {
            ball.drawBalls(gc);
        }
        for(Brick brick : getBricks()) {
            brick.drawBricks(gc);
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
        paddle.leftKeyPressed();
    }

    public void rightKeyPressed() {
        paddle.rightKeyPressed();
    }

    public void checkPaddleCollision() {

    }
}
