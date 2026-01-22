package com.nhnacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayableWorldDemo extends Application {
    PlayableWorld playableWorld;
    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer loop;
    Pane pane;
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage stage) {
        playableWorld = new PlayableWorld(800, 600);
        canvas = new Canvas(playableWorld.getWidth(), playableWorld.getHeight());
        gc = canvas.getGraphicsContext2D();

        // 그려야 할 객체
        playableWorld.addBricks();
        playableWorld.addBall(new PlayableBall(new Point(300, 300), 20, Color.RED, new Vector(2, 2)));
        playableWorld.addPaddle();

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                playableWorld.update();
                playableWorld.draw(gc);
            }
        };

        playableWorld.draw(gc);

        pane = new Pane(canvas);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        String input = "Press SPACE BAR to start";
        gc.fillText(input, 300, 300);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                loop.start();
            }
            if (event.getCode() == KeyCode.LEFT) {
                playableWorld.leftKeyPressed();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                playableWorld.rightKeyPressed();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
