package com.nhnacademy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WorldDemo extends Application {
    World world;
    GraphicsContext gc;
    Canvas canvas;
    Color DEFAULT_COLOR = Color.BLACK;
    Pane pane;
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage stage) {
        world = new World(800, 600);
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        world.addBall(new Ball(400, 300, 20));

        world.drawBall(gc);

        pane = new Pane(canvas);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}