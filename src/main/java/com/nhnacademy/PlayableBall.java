package com.nhnacademy;

import javafx.scene.paint.Color;

public class PlayableBall extends MixedBall {
    public PlayableBall(Point point, double radius, Color color, Vector velocity) {
        super(point, radius, color, velocity);
    }

    @Override
    public void resolveCollisionWithPaddle(Wall wall, Paddle paddle) {
        double reflectPower = 0.2;

        switch(wall) {
            case TOP:
            case BOTTOM: {
                velocity.setDx(velocity.getDx() + paddle.getVelocity().getDx() * reflectPower);
                velocity.reflectDy();
                break;
            }
            case LEFT:
            case RIGHT: {
                velocity.reflectDx();
                velocity.setDy(velocity.getDy() + paddle.getVelocity().getDy() * reflectPower);
                break;
            }
            default:
        }
    }

    public void resolveCollisionWithWall(Wall wall, PlayableWorld playableWorld) {
        switch(wall) {
            case TOP: {
                velocity.reflectDy();
                break;
            }
            case BOTTOM: {
                playableWorld.setGameOver();
            }
            case LEFT:
            case RIGHT: {
                velocity.reflectDx();
                break;
            }
            default:
        }
    }
}
