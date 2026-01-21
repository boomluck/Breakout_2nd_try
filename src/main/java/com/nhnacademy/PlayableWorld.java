package com.nhnacademy;

public class PlayableWorld extends MixedWorld {
    public PlayableWorld(double width, double height) {
        super(width, height);
    }

    public void leftKeyPressed() {

    }

    public Vector rightKeyPressed() {
        return new Vector(2, 0);
    }
}
