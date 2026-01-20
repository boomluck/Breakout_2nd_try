package com.nhnacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BoundedWorldDemo extends Application {
    BoundedWorld boundedWorld;
    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer loop;
    Pane pane;
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage stage) {
        boundedWorld = new BoundedWorld(800, 600);
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        boundedWorld.addRandomBoundedBalls(5);

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                boundedWorld.update();
                boundedWorld.draw(gc);
            }
        };

        boundedWorld.draw(gc);

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
