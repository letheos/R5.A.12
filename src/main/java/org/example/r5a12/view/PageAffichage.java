package org.example.r5a12.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageAffichage extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        VBox vboxPrincipale = new VBox(); //vertical box
        Text titrePage = new Text("Graphe Crée");
        vboxPrincipale.getChildren().add(titrePage);


        //graph wip
        //ajoute le graphe ici

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
