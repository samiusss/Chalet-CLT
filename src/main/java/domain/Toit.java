package domain;

import Utilitaires.PointDouble;

import java.util.LinkedList;
import java.util.List;

public class Toit {

    public static double hauteurPignon;
    public static List<PointDouble> sommetsToit;

    private String nomToit;
    //public LinkedList<PointDouble> sommetsToit;

    public Toit(String nomToit, List<PointDouble> sommetsToit) {
        this.nomToit = nomToit;
        this.sommetsToit = new LinkedList<>(sommetsToit);  // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
    }
    public List<PointDouble> getSommetsToit() {
        return this.sommetsToit;
    }



    public void createSommetToit(Toit toit, PointDouble point){
        sommetsToit.add(point);
    }
    @Override
    public String toString() {
        return "Toit: " + nomToit + ", Coordinates: " + sommetsToit ;
    }
}
