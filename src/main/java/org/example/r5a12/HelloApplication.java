package org.example.r5a12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.r5a12.controller.Lagrange;
import org.example.r5a12.model.Generator;
import org.example.r5a12.model.Point;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Point> points = Generator.generatePoints(-100,100,10);
        ArrayList<Point> resultats = Lagrange.getInterpolation(points,0.1f);
        System.out.println(resultats);
        for (Point p : resultats) {
            System.out.println("point :"+p.getX() + " " + p.getY());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}