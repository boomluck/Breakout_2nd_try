package com.nhnacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InterfacedWorldDemo extends Application {
    InterfacedWorld interfacedWorld;
    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer loop;
    Pane pane;
    Scene scene;
    Stage stage;
    Boolean gameRunning = false;

    @Override
    public void start(Stage stage) {
        interfacedWorld = new InterfacedWorld(800, 600);
        canvas = new Canvas(interfacedWorld.getWidth(), interfacedWorld.getHeight());
        gc = canvas.getGraphicsContext2D();

        gameInit();

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                interfacedWorld.update();
                interfacedWorld.draw(gc);

                if(interfacedWorld.gameOver) {
                    interfacedWorld.gameOver(gc);
                    loop.stop();
                }
            }
        };

        interfacedWorld.draw(gc);

        pane = new Pane(canvas);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        pressToStartMessage();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                interfacedWorld.leftKeyPressed();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                interfacedWorld.rightKeyPressed();
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                interfacedWorld.leftKeyReleased();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                interfacedWorld.rightKeyReleased();
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE && !gameRunning) {
                loop.start();
                gameRunning = true;
            }
            if (event.getCode() == KeyCode.SPACE && interfacedWorld.gameOver) {
                gameReset();
                gameInit();
                interfacedWorld.gameOver = false;
                gameRunning = false;
                screenClear();
                interfacedWorld.draw(gc);
                pressToStartMessage();
            }
        });
    }

    public void gameReset() {
        interfacedWorld.score = 0;
        interfacedWorld.bricks.clear();
        interfacedWorld.balls.clear();
        interfacedWorld.paddle = null;
    }

    public void gameInit() {
        interfacedWorld.addBricks();
        interfacedWorld.addBall(new PlayableBall(new Point(300, 300), 10, Color.RED, new Vector(2, 2)));
        interfacedWorld.addPaddle();
    }

    public void pressToStartMessage() {
        String input = "Press SPACE BAR to start";
        gc.setFont(Font.font(24));
        gc.fillText(input, 300, 300);
    }

    public void screenClear() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, interfacedWorld.getWidth(), interfacedWorld.getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
