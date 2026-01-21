package com.nhnacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MixedWorldDemo extends Application {
    MixedWorld mixedWorld;
    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer loop;
    Pane pane;
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage stage) {
        mixedWorld = new MixedWorld(800, 600);
        canvas = new Canvas(mixedWorld.getWidth(), mixedWorld.getHeight());
        gc = canvas.getGraphicsContext2D();

        // mixedWorld.addBricks();
        mixedWorld.addBrick(new Brick(new Point(200, 150), 400, 300, Color.BLACK));
        mixedWorld.addBall(new MixedBall(new Point(100, 100), 20, Color.BLACK, new Vector(2, 2)));

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                mixedWorld.update();
                mixedWorld.draw(gc);
            }
        };

        mixedWorld.draw(gc);

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
