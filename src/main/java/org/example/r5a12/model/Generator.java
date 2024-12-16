package org.example.r5a12.model;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    public Generator() {

    }
    public static ArrayList<Point> generatePoints(int lowlimit, int highlimit,int nbpoints) {
        ArrayList<Point> points= new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<nbpoints; i++){
            Point p = new Point(random.nextFloat(lowlimit,highlimit),random.nextFloat(lowlimit,highlimit));
            points.add(p);
            System.out.println("Point de base :"+i+":"+p.getX()+","+p.getY());
        }

        return points;
    }
}
