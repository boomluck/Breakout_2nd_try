package com.nhnacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MovableWorldDemo extends Application {
    MovableWorld movableWorld;
    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer loop;
    Pane pane;
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage stage) {
        movableWorld = new MovableWorld(800, 600);
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        movableWorld.addRandomMovableBalls(5);

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                movableWorld.update();
                movableWorld.draw(gc);
            }
        };

        movableWorld.draw(gc);

        pane = new Pane(canvas);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        loop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
