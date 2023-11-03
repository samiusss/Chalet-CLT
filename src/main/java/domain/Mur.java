package domain;


import Utilitaires.PointDouble;
import java.util.List;
import java.util.Objects;

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

    public void createSommet(PointDouble point){
        this.sommetsMur.add(point);
    }

    public List<String> getAccessoiresMur() {
        return accessoiresMur;
    }

    public void setAccessoiresMur(List<String> accessoiresMur) {
        this.accessoiresMur = accessoiresMur;
    }
}