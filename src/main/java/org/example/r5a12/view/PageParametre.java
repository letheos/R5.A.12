package org.example.r5a12.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageParametre extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBoxPrincipale = new VBox();
        Text titrePage = new Text(10,50,"Paramètre de création");
        titrePage.setFont(new Font(15));
        vBoxPrincipale.getChildren().add(titrePage);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton rbLagrangienne = new RadioButton("interpolation Lagrangienne");
        RadioButton rbHermite = new RadioButton("interpolation d'Hermite");
        RadioButton rbBezier = new RadioButton("interpolation paramètrique de Bézier");
        RadioButton rbLineaire = new RadioButton("interpolation linéaire");

        rbLagrangienne.setToggleGroup(toggleGroup);
        rbHermite.setToggleGroup(toggleGroup);
        rbBezier.setToggleGroup(toggleGroup);
        rbLineaire.setToggleGroup(toggleGroup);

        rbLagrangienne.setSelected(true);

        VBox vBoxRadio = new VBox();
        vBoxRadio.setSpacing(2);
        vBoxRadio.getChildren().addAll(rbLagrangienne, rbHermite, rbBezier, rbLineaire);

        vBoxPrincipale.getChildren().add(vBoxRadio); //2

        Text typeFichier = new Text("Type fichier");
        vBoxPrincipale.getChildren().add(typeFichier);

        HBox hBoxType = new HBox();

        VBox typeGauche = new VBox();
        RadioButton fichierSource = new RadioButton("Fichier source+\t format supporté: JSON");
        Button importFichier = new Button("Importer fichier");
        typeGauche.getChildren().addAll(fichierSource,importFichier);


        VBox typeDroite = new VBox();
        RadioButton genererPoint = new RadioButton("Generer points aléatoires");
        TextField nbrPoint = new TextField();
        nbrPoint.setPromptText("Par défault 5 points");
        typeDroite.getChildren().addAll(genererPoint, nbrPoint);

        hBoxType.getChildren().addAll(typeGauche, typeDroite);
        vBoxPrincipale.getChildren().add(hBoxType);
        hBoxType.setAlignment(Pos.CENTER);

        Button generer = new Button("Generer");
        vBoxPrincipale.getChildren().add(generer);

        vBoxPrincipale.setAlignment(Pos.TOP_CENTER);
        vBoxPrincipale.setSpacing(10);
        Scene scene = new Scene(vBoxPrincipale, 500, 400);
        stage.setTitle("Radio Button Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
