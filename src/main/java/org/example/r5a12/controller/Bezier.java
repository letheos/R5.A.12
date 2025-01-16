package org.example.r5a12.controller;

import org.example.r5a12.model.Point;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.round;

public class Bezier {
    static ArrayList<Point> selectedPoints = new ArrayList<>();

    //p0-3 sont des points de contrôles, t doit être en 0 et 1
    public static Point bezierCubique(Point p0, Point p1, Point p2, Point p3, float t) {
        float x = (float) (Math.pow(1 - t, 3) * p0.x +
                3 * Math.pow(1 - t, 2) * t * p1.x +
                3 * (1 - t) * Math.pow(t, 2) * p2.x +
                Math.pow(t, 3) * p3.x);

        float y = (float) (Math.pow(1 - t, 3) * p0.y +
                3 * Math.pow(1 - t, 2) * t * p1.y +
                3 * (1 - t) * Math.pow(t, 2) * p2.y +
                Math.pow(t, 3) * p3.y);

        return new Point(x, y);
    }

    public static ArrayList<Point> getInterpolation(ArrayList<Point> points) {
        ArrayList<Point> res = new ArrayList<>();
        Point p0 = points.getFirst();
        Point p1 = points.get(round(points.size()/3));
        Point p2 = points.get(round(points.size()/3)*2);
        Point p3 = points.get(-1);
        float t = 0.10f;

        while (t <= 1f) {
            res.add(bezierCubique(p0, p1, p2, p3, t));
            t+=0.10f;
        }

        return res;
    }



}
