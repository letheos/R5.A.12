package org.example.r5a12.view;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageAffichage extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        VBox vboxPrincipale = new VBox();
        Text titrePage = new Text("Graphe Crée");

        //graph wip

        HBox hBoxButton = new HBox();
        Button sauv = new Button("Sauvegarder");
        Button nouvGraphe = new Button("Créer un nouveau graphe");
        hBoxButton.getChildren().addAll(sauv, nouvGraphe);
        vboxPrincipale.getChildren().addAll(titrePage, hBoxButton);

    }
}
