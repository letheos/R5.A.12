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
    ArrayList<Float> listePoints = new ArrayList<Float>(); //Remplacer le float par un Point à terme

    @Override
    public void start(Stage stage) throws Exception {
        VBox vboxPrincipale = new VBox(); //vertical box
        Text titrePage = new Text("Graphe Crée");
        vboxPrincipale.getChildren().add(titrePage);


        //graph wip
        //ajoute le graphe ici

        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(0, 10, 1);
        final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);

        //https://docs.oracle.com/javafx/2/charts/scatter-chart.htm#CIHDEACI exemple 6-2 pour comment afficher ou non certains graphes
        XYChart.Series series1 = new XYChart.Series();
        for(int i = 0 ; i < listePoints.size() ; i++){
            //series1.getData().add(new XYChart.Data(listePoints.get(i)[0], listePoints.get(i)[1]));
        }

        sc.getData().add(series1);
        vboxPrincipale.getChildren().add(sc);

        HBox hBoxButton = new HBox();
        Button sauv = new Button("Sauvegarder");
        Button nouvGraphe = new Button("Créer un nouveau graphe");
        hBoxButton.getChildren().addAll(sauv, nouvGraphe);
        hBoxButton.setSpacing(10);

        vboxPrincipale.getChildren().add(hBoxButton);
        vboxPrincipale.setSpacing(10);
        Scene scene = new Scene(vboxPrincipale, 500, 400);
        stage.setTitle("Graph"); //voir pour modifier le nom de la page avec des paramètres
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
