package com.nhnacademy;

import javafx.scene.paint.Color;

public class PaintableBall extends Ball {
    Color color;
    Color DEFAULT_COLOR = Color.BLACK;

    public PaintableBall(Point point, double radius, Color color) {
        super(point, radius);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
