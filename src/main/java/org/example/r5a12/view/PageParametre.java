package org.example.r5a12.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.r5a12.controller.FileChooserJson;
import org.example.r5a12.controller.Hermite;
import org.example.r5a12.controller.Lagrange;
import org.example.r5a12.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.r5a12.model.Generator.generatePoints;
import static org.example.r5a12.model.TrouveFile.readJsonFile;
import static org.example.r5a12.model.TrouveFile.readTextFile;

public class PageParametre {
    private final Scene scene;
    private Scene nextScene;

    public PageParametre(Stage stage) {
        List<Point> point = new ArrayList<>();
        VBox vBoxPrincipale = new VBox();
        Text titrePage = new Text(10,50,"Paramètre de création");
        titrePage.setFont(new Font(15));
        vBoxPrincipale.getChildren().add(titrePage);

        ToggleGroup toggleGroup = new ToggleGroup();

        CheckBox rbLagrangienne = new CheckBox("interpolation Lagrangienne");
        CheckBox rbHermite = new CheckBox("interpolation d'Hermite");
        CheckBox rbBezier = new CheckBox("interpolation paramètrique de Bézier");
        CheckBox rbLineaire = new CheckBox("interpolation linéaire");



        VBox vBoxRadio = new VBox();
        vBoxRadio.setSpacing(2);
        vBoxRadio.getChildren().addAll(rbLagrangienne, rbHermite, rbBezier, rbLineaire);

        vBoxPrincipale.getChildren().add(vBoxRadio); //2

        Text typeFichier = new Text("Type fichier");
        vBoxPrincipale.getChildren().add(typeFichier);

        HBox hBoxType = new HBox();

        VBox typeGauche = new VBox();
        RadioButton fichierSource = new RadioButton(" Fichier source\n format supporté: JSON");
        fichierSource.setSelected(true);
        fichierSource.setToggleGroup(toggleGroup);
        Button importFichier = new Button("Importer fichier");
        typeGauche.getChildren().addAll(fichierSource,importFichier);

        VBox typeDroite = new VBox();
        typeDroite.setSpacing(10);
        RadioButton genererPoint = new RadioButton("Générer points aléatoires");
        genererPoint.setSelected(false);
        genererPoint.setToggleGroup(toggleGroup);
        TextField nbrPoint = new TextField();
        nbrPoint.setPromptText("Par défault 5 points");
        typeDroite.getChildren().addAll(genererPoint, nbrPoint);



        HBox minMaxinput = new HBox();
        hBoxType.setSpacing(10);
        TextField minValeur = new TextField();
        minValeur.setPromptText("min: par défault -10");


        TextField maxValeur = new TextField();
        maxValeur.setPromptText("max: par défault 10");
        minMaxinput.getChildren().addAll(minValeur,maxValeur);
        typeDroite.getChildren().add(minMaxinput);

        hBoxType.getChildren().addAll(typeGauche, typeDroite);
        vBoxPrincipale.getChildren().add(hBoxType);
        hBoxType.setAlignment(Pos.CENTER);
        hBoxType.setSpacing(3);
        Button generer = new Button("Générer");
        generer.setDisable(true);

        FileChooserJson fileChooserJson = new FileChooserJson();

        AtomicReference<String> selectedFile = null;
        importFichier.setOnAction(e -> {
            String filePath = fileChooserJson.getFileChooser().showOpenDialog(this.getScene().getWindow()).getPath();
            if(selectedFile != null){
                selectedFile.set(filePath);
                generer.setDisable(false);
            }
        });

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == fichierSource) {
                generer.setDisable(true);
                importFichier.setDisable(false);
                nbrPoint.setDisable(true);
            } else if (newValue == genererPoint) {
                generer.setDisable(false);
                importFichier.setDisable(true);
                nbrPoint.setDisable(false);
            }
        });


        generer.setOnAction(e -> {
            ArrayList<Point> points = new ArrayList<>();
            if (fichierSource.isSelected()) {
                FileChooser.ExtensionFilter filtreJson = new FileChooser.ExtensionFilter("JSON Files", "*.json");
                if (Objects.equals(fileChooserJson.getFileChooser().getSelectedExtensionFilter(), filtreJson)) {
                    points = (ArrayList<Point>) readJsonFile(selectedFile.get());
                } else {
                    points = (ArrayList<Point>) readTextFile(selectedFile.get());
                }
            } else {
                int nomPoint = 5;
                int vMin = -10;
                int vMax = 10;
                if (!nbrPoint.getText().isEmpty()) {
                    nomPoint = Integer.parseInt(nbrPoint.getText());
                }
                if (!minValeur.getText().isEmpty()) {
                    vMin = Integer.parseInt(minValeur.getText());
                }
                if (!maxValeur.getText().isEmpty()) {
                    vMax = Integer.parseInt(maxValeur.getText());
                }
                points = generatePoints(vMin, vMax, nomPoint);
            }

            // Création de la page d'affichage avec plusieurs graphiques
            VBox graphContainer = new VBox(10);
            graphContainer.setAlignment(Pos.CENTER);

            if (rbLagrangienne.isSelected()) {
                graphContainer.getChildren().add(createGraphWithCurve(Lagrange.getInterpolation(points,1), "Interpolation Lagrangienne"));
            }
            if (rbHermite.isSelected()) {
                graphContainer.getChildren().add(createGraphWithCurve((Hermite.interpolatePoints(points)), "Interpolation Hermite"));
            }
            if (rbBezier.isSelected()) {
                graphContainer.getChildren().add(createGraphWithCurve(points, "Interpolation Bézier"));
            }
            if (rbLineaire.isSelected()) {
                graphContainer.getChildren().add(createGraphWithCurve(points, "Interpolation Linéaire"));
            }

            // Création de la scène avec tous les graphiques
            ScrollPane scrollPane = new ScrollPane(graphContainer);
            scrollPane.setFitToWidth(true);
            Scene graphScene = new Scene(scrollPane, 800, 600);

            stage.setScene(graphScene);
        });


        vBoxPrincipale.getChildren().add(generer);

        vBoxPrincipale.setSpacing(10);
        scene = new Scene(vBoxPrincipale, 600, 400);
    }

    public Scene getScene() {
        return scene;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }


    private LineChart<Number, Number> createGraphWithCurve(List<Point> points, String title) {
        double minx = points.get(0).getX();
        double maxx = points.get(0).getX();
        double miny = points.get(0).getY();
        double maxy = points.get(0).getY();
        for (int x = 0; x < points.size(); x++) {
            if(points.get(x).getX() < minx){
                minx = points.get(x).getX();
            }
            if(points.get(x).getX() > maxx){
                maxx = points.get(x).getX();
            }
            if(points.get(x).getY() < miny){
                miny = points.get(x).getY();
            }
            if(points.get(x).getY() > maxy){
                maxy = points.get(x).getY();
            }
        }
        final NumberAxis xAxis = new NumberAxis(minx, maxx, 1);
        final NumberAxis yAxis = new NumberAxis(miny, maxy, 1);
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(title);
        lineChart.setCreateSymbols(true); // Affiche les points



        XYChart.Series<Number, Number> seriesCurve = new XYChart.Series<>();
        for (Point point : points) {
            seriesCurve.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
        }

        lineChart.getData().add(seriesCurve);
        return lineChart;
    }



}
