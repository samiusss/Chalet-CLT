package domain;

import Utilitaires.PointDouble;

import java.util.LinkedList;
import java.util.List;

public class Toit implements java.io.Serializable {

    public static List<PointDouble> sommetsToit;

    private final String nomToit;

    public Toit(String nomToit, List<PointDouble> sommetsToit) {
        this.nomToit = nomToit;
        Toit.sommetsToit = new LinkedList<>(sommetsToit);  // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
    }
    public List<PointDouble> getSommetsToit() {
        return sommetsToit;
    }

    public String getNomToit() {
        return nomToit;
    }


    public void createSommetToit(Toit toit, PointDouble point){
        sommetsToit.add(point);
    }
    @Override
    public String toString() {
        return "Toit: " + nomToit + ", Coordinates: " + sommetsToit ;
    }
}
