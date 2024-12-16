package org.example.r5a12.model;

import org.example.r5a12.controller.Lagrange;

import java.util.ArrayList;
import java.util.Random;

public class Point {
    private float x;
    private float y;
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


}
