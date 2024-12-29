package org.example.r5a12.controller;

import org.example.r5a12.model.Point;
import org.example.r5a12.model.TrouveFile;

import java.util.List;

public class ControllerTrouveFile {
    /**
     * Renvoie une liste de point à partir d'un path
     * @param path String
     * @return List<Point>
     */
    public static List<Point> RechechJSON(String path){
        return TrouveFile.readJsonFile(path);
    }

    /**
     * Renvoie une liste de point à partir d'un path
     * @param path String
     * @return List<Point>
     */
    public static List<Point> RecerchTXT(String path){
        return TrouveFile.readTextFile(path);
    }

}
