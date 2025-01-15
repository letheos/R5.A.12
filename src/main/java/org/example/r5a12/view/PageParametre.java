package org.example.r5a12.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.r5a12.controller.FileChooserJson;
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
        fichierSource.setToggleGroup(toggleGroup);
        Button importFichier = new Button("Importer fichier");
        typeGauche.getChildren().addAll(fichierSource,importFichier);


        VBox typeDroite = new VBox();
        typeDroite.setSpacing(10);
        RadioButton genererPoint = new RadioButton("Générer points aléatoires");
        genererPoint.setSelected(true);
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
                if(Objects.equals(fileChooserJson.getFileChooser().getSelectedExtensionFilter(), filtreJson)) {
                    final ArrayList<Point> bla = (ArrayList<Point>) readJsonFile(selectedFile.get());
                    System.out.println(bla);
                } else{
                    final ArrayList<Point> bla = (ArrayList<Point>) readTextFile(selectedFile.get());
                    System.out.println(bla);
                }


            } else {



                int nomPoint = 5;
                int vMin = -10;
                int vMax = 10;
                if (!nbrPoint.getCharacters().isEmpty()) { //un truc dans le edti text
                    nomPoint = Integer.parseInt(nbrPoint.getCharacters().toString());
                }

                if (!minValeur.getCharacters().isEmpty()) {
                    vMin = Integer.parseInt(nbrPoint.getCharacters().toString());
                }

                if (!maxValeur.getCharacters().isEmpty()) {
                    vMax = Integer.parseInt(nbrPoint.getCharacters().toString());
                }

                points = generatePoints(vMin, vMax, nomPoint);

            }
            if (nextScene != null) {
                stage.setScene(nextScene);
            }
        }

        );
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


}
