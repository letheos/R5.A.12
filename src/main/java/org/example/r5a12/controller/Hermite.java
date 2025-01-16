//comprendre la dérivé

package org.example.r5a12.controller;

import org.example.r5a12.model.Point;

import java.util.ArrayList;
import java.util.List;


import static org.example.r5a12.controller.Lagrange.InterpolationLagrangienne;


public class Hermite {
    public static List<Point> lesPoints;
    public static List<Float> lesPointsPrime;

    public Hermite(ArrayList<Point> points) {
        lesPoints = points;
        lesPointsPrime = Point.toPrime(points);

    }

    public float H(int i, float x) {
        return (float) Math.pow((InterpolationLagrangienne(x, (ArrayList<Point>) lesPoints)),2);
    }

    public Float Hp(int i, float x) {
        float Hp = 0;
        for (int k = 0; k < lesPoints.size(); i++) {
            if (k != i) {
                float fact = 1;
                for (int j = 0; j < lesPoints.size(); k++) {
                    if (j != i && k != i) {
                        fact = fact * (x - lesPoints.get(j).getX()) / (lesPoints.get(i).getX() - lesPoints.get(j).getX());
                    }
                    Hp += 2*fact/(lesPoints.get(j).getX() - lesPoints.get(k).getX());
                }
            }
        }
        return Hp;
    }

    public float InterPh(int x){
        float P = 0;
        for (int i = 0; i < lesPoints.size(); i++) {
            P += H(i, x) * (lesPoints.get(i).getY() + (lesPointsPrime.get(i) - lesPoints.get(i).getX())* (lesPoints.get(i).getY()) - Hp(i, x) * lesPoints.get(i).getY());
        }
        return P;
    }


}