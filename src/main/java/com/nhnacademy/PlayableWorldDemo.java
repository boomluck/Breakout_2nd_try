package com.nhnacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayableWorldDemo extends Application {
    PlayableWorld playableWorld;
    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer loop;
    Pane pane;
    Scene scene;
    Stage stage;

    Paddle paddle;

    @Override
    public void start(Stage stage) {
        playableWorld = new PlayableWorld(800, 600);
        canvas = new Canvas(playableWorld.getWidth(), playableWorld.getHeight());
        gc = canvas.getGraphicsContext2D();

        // 그려야 할 객체
        //playableWorld.addBricks();
        //playableWorld.addBall();
        paddle = new Paddle(new Point(350, 550), 100, 20, new Vector(0, 0));

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playableWorld.update();
                playableWorld.draw(gc);
            }
        };

        pane = new Pane(canvas);
        scene = new Scene(pane);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                playableWorld.leftKeyPressed();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                playableWorld.rightKeyPressed();
            }
        });

        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.LEFT) {
                playableWorld.leftKeyReleased();
            }
            if(event.getCode() == KeyCode.RIGHT) {
                playableWorld.rightKeyReleased();
            }
        });

        stage.setScene(scene);
        stage.show();

        loop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
