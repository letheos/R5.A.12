package org.example.r5a12.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TrouveFile {
    // Method to read JSON file and parse it into a list of Points
    public static List<Point> readJsonFile(String filePath) {
        List<Point> points = new ArrayList<>();
        Gson gson = new Gson();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            Type listType = new TypeToken<List<Point>>() {}.getType();
            points = gson.fromJson(br, listType);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return points;
    }

    /**
     * Méthode lisant un fichier text et renvoyant une liste de point
     * @param filePath Text
     * @return List<Point>
     */
    public static List<Point> readTextFile(String filePath) {
        List<Point> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("x=") && line.contains(", y=")) {
                    String[] parts = line.split(", y=");
                    double x = Double.parseDouble(parts[0].substring(2).trim());
                    double y = Double.parseDouble(parts[1].trim());
                    points.add(new Point((float)x, (float)y));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading text file: " + e.getMessage());
        }

        return points;
    }

}
