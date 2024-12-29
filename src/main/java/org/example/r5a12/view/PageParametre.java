package org.example.r5a12.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.r5a12.model.Generator;
import org.example.r5a12.model.Point;

import java.util.ArrayList;
import java.util.List;

import static org.example.r5a12.model.Generator.generatePoints;

public class PageParametre extends Application {
    @Override
    public void start(Stage stage) throws Exception {
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
        RadioButton fichierSource = new RadioButton("Fichier source+\t format supporté: JSON");
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
        generer.setOnAction(e->{
            List<Point> point = new ArrayList<>();
            if(fichierSource.isSelected()){
                //bla
            } else{
                int nomPoint = 5;
                int vMin = -10;
                int vMax = 10;
                if(!nbrPoint.getCharacters().isEmpty()){ //un truc dans le edti text
                    nomPoint = Integer.parseInt(nbrPoint.getCharacters().toString());
                }

                if(!minValeur.getCharacters().isEmpty()){
                    vMin = Integer.parseInt(nbrPoint.getCharacters().toString());
                }

                if(!maxValeur.getCharacters().isEmpty()){
                    vMax = Integer.parseInt(nbrPoint.getCharacters().toString());
                }

                point = generatePoints(vMin,vMax,nomPoint);
            }
        });
        vBoxPrincipale.getChildren().add(generer);

        vBoxPrincipale.setAlignment(Pos.TOP_CENTER);
        vBoxPrincipale.setSpacing(10);

        Scene scene = new Scene(vBoxPrincipale, 600, 400);
        stage.setTitle("Radio Button Example");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
