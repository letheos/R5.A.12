package org.example.r5a12.view;

import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.r5a12.model.Point;

import java.util.ArrayList;


public class PageAffichage {
    private final Scene scene;
    private Scene previousScene;
    private ArrayList<Point> points;
    public PageAffichage(Stage stage,ArrayList<Point> points) {
        VBox vboxPrincipale = new VBox(10);
        this.points = points;
        Text titrePage = new Text("Graphe Créé");
        vboxPrincipale.getChildren().add(titrePage);


        final NumberAxis xAxis = new NumberAxis(-10, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-10, 10, 1);
        final ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();

        if (points != null && !points.isEmpty()) {
            for (Point point : points) {
                if (point != null) {
                    series1.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
                }
            }
        } else {
            System.err.println("La liste de points est vide ou nulle.");
        }
        scatterChart.getData().add(series1);
        vboxPrincipale.getChildren().add(scatterChart);

        HBox hBoxButton = new HBox(10);
        Button saveButton = new Button("Sauvegarder");
        Button newGraphButton = new Button("Créer un nouveau graphe");
        newGraphButton.setOnAction(e -> {
            if (previousScene != null) {
                stage.setScene(previousScene);
            }
        });
        hBoxButton.getChildren().addAll(saveButton, newGraphButton);
        vboxPrincipale.getChildren().add(hBoxButton);

        scene = new Scene(vboxPrincipale, 500, 400);
    }

    public Scene getScene() {
        return scene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

}