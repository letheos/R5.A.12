package org.example.r5a12.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class GestionPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        PageParametre pageParametre = new PageParametre(stage);
        PageAffichage pageAffichage = new PageAffichage(stage);

        pageParametre.setNextScene(pageAffichage.getScene());
        pageAffichage.setPreviousScene(pageParametre.getScene());

        stage.setScene(pageParametre.getScene());
        stage.setTitle("Application");
        stage.show();

    }
}
