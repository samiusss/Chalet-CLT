package domain;


import Utilitaires.PointDouble;

import java.util.ArrayList;
import java.util.*;

public class Mur {
    private String nomMur;
    private List<PointDouble> sommetsMur;
    private static List<String> accessoiresMur;
    //private List<Porte> porteMur;
    //private List<Fenetre> fenetreMur;
    List<Porte> porteMur= new LinkedList();
    List<Fenetre> fenetreMur = new LinkedList();


    // on peut creer un mur simple
    public Mur() {
    }

    // deuxieme constructeur pour creer un mur avec 4 points et des accessoires et un nom
    // ce meme constructeur est appelle a la ligne
    public Mur(String nomMur, List<PointDouble> sommetsMur, List<String> accessoiresMur) {
        this.nomMur = nomMur;
        this.sommetsMur = new ArrayList<>(sommetsMur);  // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
        this.accessoiresMur = accessoiresMur;           // ceci est comme ci: [Liste de accessoires]

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
        return this.sommetsMur;
    }

    public void setSommetsMur(List<PointDouble> sommetsMur) {
        this.sommetsMur = sommetsMur;
    }

    public void createSommet(Mur mur, PointDouble point){
        mur.sommetsMur.add(point);
    }

    public static List<String> getAccessoiresMur() {
        return accessoiresMur;
    }

    public void setAccessoiresMur(List<String> accessoiresMur) {
        this.accessoiresMur = accessoiresMur;
    }

    public List<Porte> getListePorte(){
        return porteMur;
    }

    public boolean clearListePorte() {
        porteMur.clear();
        return true;
    }

    public boolean ajouterPorte(Porte Porte){
        return porteMur.add(Porte);
    }

    public boolean ajouterFenetre(Fenetre Fenetre)
    {
        return fenetreMur.add(Fenetre);
    }

    public List<Fenetre> getListeFenetre()
    {
        return fenetreMur;
    }

    public boolean clearListeFenetre() {
        fenetreMur.clear();
        return true;
    }


}
