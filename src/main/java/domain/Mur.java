package domain;


import Utilitaires.PointDouble;
import java.util.List;

public class Mur {
    private String nomMur;
    private List<PointDouble> sommetsMur;
    private List<String> accessoiresMur;

    // on peut creer un mur simple
    public Mur() {
    }

    // deuxieme constructeur pour creer un mur avec 4 points et des accessoires et un nom
    // ce meme constructeur est appelle a la ligne
    public Mur(String nomMur, List<PointDouble> sommetsMur, List<String> accessoiresMur) {
        this.nomMur = nomMur;
        this.sommetsMur = sommetsMur; // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
        this.accessoiresMur = accessoiresMur; // ceci est comme ci: [Liste de accessoires]
    }

    /*public void retirerRainures(List<Mur> listeDeMursARainurer, double distanceUsinage, String orientationToit){
        for(Mur mur : listeDeMursARainurer){
            if()
            if (mur.nomMur == "Facade"){

            }
            for(PointDouble sommet : mur.getSommetsMur()){
                sommet.setLocation(sommet.getX() - distanceUsinage, sommet.getY() - distanceUsinage);
            }
        }
        for (int i = 0; i < mur.getSommetsMur().size(); i++) {

            point.setLocation(point.getX() + distanceUsinage, point.getY() + distanceUsinage);
        }
    }*/
    @Override
    public String toString() {
        return "Mur: " + nomMur + ", Coordinates: " + sommetsMur + ", Accessories: " + accessoiresMur;
    }

    public String getNomMur() {
        return nomMur;
    }

    public void setNomMur(String nomMur) {
        this.nomMur = nomMur;
    }

    public List<PointDouble> getSommetsMur() {
        return sommetsMur;
    }

    public void setSommetsMur(List<PointDouble> sommetsMur) {
        this.sommetsMur = sommetsMur;
    }

    public List<String> getAccessoiresMur() {
        return accessoiresMur;
    }

    public void setAccessoiresMur(List<String> accessoiresMur) {
        this.accessoiresMur = accessoiresMur;
    }
}