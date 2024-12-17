package org.example.r5a12.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.r5a12.model.Generator;
import org.example.r5a12.controller.Lagrange;
import org.example.r5a12.model.Point;

import java.util.ArrayList;

public class PageAffichage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Mise en place de la VBox principale et du titre
        VBox vboxPrincipale = new VBox();
        Text titrePage = new Text("Graphe Crée");

        // Création des boutons
        HBox hBoxButton = new HBox();
        Button sauv = new Button("Sauvegarder");
        Button nouvGraphe = new Button("Créer un nouveau graphe");
        hBoxButton.getChildren().addAll(sauv, nouvGraphe);
        vboxPrincipale.getChildren().addAll(titrePage, hBoxButton);

        // Création des axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        // Création du ScatterChart
        final ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);
        sc.setTitle("Graphique des points");

        // Créer les points de votre graphique (exemples de points)
        ArrayList<Point> points = Generator.generatePoints(1, 5, 10);

        // Ajouter les points originaux à la série de données
        XYChart.Series<Number, Number> seriesOriginal = new XYChart.Series<>();
        seriesOriginal.setName("Points Originaux");

        for (Point p : points) {
            System.out.println("position point original : "+p.getX()+" "+p.getY());
            seriesOriginal.getData().add(new XYChart.Data<>(p.getX(), p.getY()));
        }

        // Ajouter la série des points originaux au graphique
        sc.getData().add(seriesOriginal);

        // Calculer l'interpolation de Lagrange et ajouter la courbe
        ArrayList<Point> resultats = Lagrange.getInterpolation(points, 0.1f); // Interpolation avec un pas de 0.1

        XYChart.Series<Number, Number> seriesLagrange = new XYChart.Series<>();
        seriesLagrange.setName("Courbe Interpolée");

        // Ajouter chaque point interpolé à la série
        for (Point p : resultats) {
            seriesLagrange.getData().add(new XYChart.Data<>(p.getX(), p.getY()));
        }

        // Ajouter la série de la courbe au graphique
        sc.getData().add(seriesLagrange);

        // Ajouter le graphique à la VBox
        vboxPrincipale.getChildren().add(sc);

        // Afficher la scène
        Scene scene = new Scene(vboxPrincipale, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Affichage du Graphe");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
