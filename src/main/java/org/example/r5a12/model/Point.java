package org.example.r5a12.model;

import org.example.r5a12.controller.Lagrange;
import org.example.r5a12.view.rekop;

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

    public static List<Point> Interpolate(List<Point> points) {
        List<Point> result = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);


            result.add(p1);

            double stepSize = (p2.x - p1.x) ;
            for (int j = 1; j < 1; j++) {
                double x = p1.x + j * stepSize;
                double y = p1.y + (p2.y - p1.y) / (p2.x - p1.x) * (x - p1.x);
                result.add(new Point((float)x,(float)y));
            }
        }
        result.add(points.get(points.size() - 1));

        return result;
    }

}
