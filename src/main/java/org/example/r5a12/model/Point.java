package org.example.r5a12.model;

import org.example.r5a12.controller.Lagrange;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
    public float x;
    public float y;
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

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static List<Float> toPrime(ArrayList<Point> points) {
        ArrayList<Float> prime = new ArrayList<>();
        if (points.size() > 2) {
            for (int i = 0; i < points.size()-1; i++) {
                prime.add((points.get(i+1).y - points.get(i).y) / (points.get(i+1).x - points.get(i).x ));
            }
        }
        return prime;
    }

}
