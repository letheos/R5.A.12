package org.example.r5a12.controller;

import org.example.r5a12.model.Point;
import java.util.ArrayList;

public class Lagrange {

    public Lagrange() {
    }

    private static float InterpolationLagrangienne(float X, ArrayList<Point> points) {
        float result = 0.0f;
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            float currentx = p.getX();
            float currenty = p.getY();

            float polynome = 1.0f;
            for (int j = 0; j < points.size(); j++) {
                if (j != i) {
                    Point p1 = points.get(j);
                    float otherx = p1.getX();
                    polynome *= (X - otherx) / (currentx - otherx);
                }
            }
            result += currenty * polynome;
        }
        return result;
    }

    /***
     * @param points
     * @param intervalle Intervalle entre chaque point généré pour créer la courbe de Lagrange.
     *                    Plus l'intervalle est petit, plus la courbe est fidèle.
     * @return Liste des points interpolés
     */
    public static ArrayList<Point> getInterpolation(ArrayList<Point> points, float intervalle) {
        ArrayList<Point> resultats = new ArrayList<>();

        float minX = points.get(0).getX();
        float maxX = points.get(0).getX();
        for (Point p : points) {
            if (p.getX() < minX) {
                minX = p.getX();
            }
            if (p.getX() > maxX) {
                maxX = p.getX();
            }
        }

        // Interpoler entre minX et maxX avec l'intervalle donné
        for (float x = minX; x <= maxX; x += intervalle) {
            float y = InterpolationLagrangienne(x, points);
            resultats.add(new Point(x, y));
        }
        return resultats;
    }
}
