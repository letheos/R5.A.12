package org.example.r5a12.controller;

import org.example.r5a12.model.Point;

import java.util.ArrayList;
import java.util.List;

public class Hermite {
    private final List<Point> points;
    private final List<Float> derivatives;

    public Hermite(List<Point> points) {
        this.points = points;
        this.derivatives = calculateDerivatives(points);
    }

    // Calcul automatique des dérivées (différences finies)
    private List<Float> calculateDerivatives(List<Point> points) {
        List<Float> derivatives = new ArrayList<>();
        int n = points.size();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                // Différence avant pour le premier point
                float derivative = (points.get(i + 1).getY() - points.get(i).getY()) /
                        (points.get(i + 1).getX() - points.get(i).getX());
                derivatives.add(derivative);
            } else if (i == n - 1) {
                // Différence arrière pour le dernier point
                float derivative = (points.get(i).getY() - points.get(i - 1).getY()) /
                        (points.get(i).getX() - points.get(i - 1).getX());
                derivatives.add(derivative);
            } else {
                // Différence centrale pour les points intermédiaires
                float derivative = (points.get(i + 1).getY() - points.get(i - 1).getY()) /
                        (points.get(i + 1).getX() - points.get(i - 1).getX());
                derivatives.add(derivative);
            }
        }
        return derivatives;
    }

    // Fonction de base Hermite H_i(x)
    private float H(int i, float x) {
        float result = 1.0f;
        for (int k = 0; k < points.size(); k++) {
            if (k != i) {
                result *= (x - points.get(k).getX()) / (points.get(i).getX() - points.get(k).getX());
            }
        }
        return result * result * (1 - 2 * (x - points.get(i).getX()) * derivativeLagrange(i));
    }

    // Dérivée de la fonction de Lagrange pour H_i'(x)
    private float derivativeLagrange(int i) {
        float result = 0.0f;
        for (int k = 0; k < points.size(); k++) {
            if (k != i) {
                float prod = 1.0f / (points.get(i).getX() - points.get(k).getX());
                for (int j = 0; j < points.size(); j++) {
                    if (j != i && j != k) {
                        prod *= (points.get(i).getX() - points.get(j).getX());
                    }
                }
                result += prod;
            }
        }
        return 2 * result;
    }

    // Fonction interpolée d'Hermite P(x)
    private float interpolate(float x) {
        float result = 0.0f;
        for (int i = 0; i < points.size(); i++) {
            float h = H(i, x);
            float hp = derivativeLagrange(i) * (x - points.get(i).getX());
            result += h * points.get(i).getY() + hp * derivatives.get(i);
        }
        return result;
    }

    // Génération des points interpolés (accessible publiquement)
    public List<Point> getInterpolatedPoints() {
        List<Point> interpolatedPoints = new ArrayList<>();
        float xMin = points.get(0).getX();
        float xMax = points.get(points.size() - 1).getX();
        int steps = 100; // Par défaut, 100 points interpolés
        float stepSize = (xMax - xMin) / (steps - 1);
        for (int i = 0; i < steps; i++) {
            float x = xMin + i * stepSize;
            float y = interpolate(x);
            interpolatedPoints.add(new Point(x, y));
        }
        return interpolatedPoints;
    }

    // Méthode statique pour obtenir les points interpolés à partir d'une liste de points d'entrée
    public static List<Point> interpolatePoints(List<Point> points) {
        Hermite hermite = new Hermite(points);
        return hermite.getInterpolatedPoints();
    }
}
