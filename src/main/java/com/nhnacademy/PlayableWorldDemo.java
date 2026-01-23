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

public class PlayableWorldDemo extends Application {
    PlayableWorld playableWorld;
    Canvas canvas;
    GraphicsContext gc;
    AnimationTimer loop;
    Pane pane;
    Scene scene;
    Stage stage;
    Boolean gameRunning = false;

    @Override
    public void start(Stage stage) {
        playableWorld = new PlayableWorld(800, 600);
        canvas = new Canvas(playableWorld.getWidth(), playableWorld.getHeight());
        gc = canvas.getGraphicsContext2D();

        gameInit();

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                playableWorld.update();
                playableWorld.draw(gc);

                if(playableWorld.gameOver) {
                    playableWorld.gameOver(gc);
                    loop.stop();
                }
            }
        };

        playableWorld.draw(gc);

        pane = new Pane(canvas);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        pressToStartMessage();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                playableWorld.leftKeyPressed();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                playableWorld.rightKeyPressed();
            }
        });

        scene.setOnKeyReleased(event -> {
           if (event.getCode() == KeyCode.LEFT) {
               playableWorld.leftKeyReleased();
           }
           if (event.getCode() == KeyCode.RIGHT) {
               playableWorld.rightKeyReleased();
           }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE && !gameRunning) {
                loop.start();
                gameRunning = true;
            }
            if (event.getCode() == KeyCode.SPACE && playableWorld.gameOver) {
                gameReset();
                gameInit();
                playableWorld.gameOver = false;
                gameRunning = false;
                screenClear();
                playableWorld.draw(gc);
                pressToStartMessage();
            }
        });
    }

    public void gameReset() {
        playableWorld.score = 0;
        playableWorld.bricks.clear();
        playableWorld.balls.clear();
        playableWorld.paddle = null;
    }

    public void gameInit() {
        playableWorld.addBricks();
        playableWorld.addBall(new PlayableBall(new Point(300, 300), 10, Color.RED, new Vector(2, 2)));
        playableWorld.addPaddle();
    }

    public void pressToStartMessage() {
        String input = "Press SPACE BAR to start";
        gc.setFont(Font.font(24));
        gc.fillText(input, 300, 300);
    }

    public void screenClear() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, playableWorld.getWidth(), playableWorld.getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
