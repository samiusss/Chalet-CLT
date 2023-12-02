package domain;

import Utilitaires.PointDouble;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.tan;

public class Toit {

    public static double hauteurPignon;


    public static String nomToit;
    public static LinkedList<PointDouble> sommetsToit;

    public Toit(String nomToit, List<PointDouble> sommetsToit) {
        this.nomToit = nomToit;
        this.sommetsToit = new LinkedList<>(sommetsToit);  // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
    }



    public void createSommetToit(Toit toit, PointDouble point){
        sommetsToit.add(point);
    }
    @Override
    public String toString() {
        return "Toit: " + nomToit + ", Coordinates: " + sommetsToit ;
    }

    public void initialiserPignonDroit()
        {
            hauteurPignon= Chalet.largeurChalet * tan(Chalet.angleToit); //largeurChalet est la largeur du pignon

            if (Objects.equals(Chalet.orientationToit, "Nord"))
            {
            }
        }
    }
