package domain;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import Utilitaires.*;


public class Porte extends Accessoires implements Serializable {

    private List<pointPouces> sommetsPorte;
    public Point mousePoint;
    public static Pouces PORTE_LARGEUR_STANDARD = new Pouces(2, 0, 1);
    public static Pouces PORTE_HAUTEUR_STANDARD = new Pouces(5, 0, 1);

    public Porte(Point mousepoint,Pouces largeur, Pouces hauteur) {
        //super(largeur, hauteur);
        this.mousePoint = mousepoint;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Point getPoint() {
        return mousePoint;
    }


    public boolean setHauteurPorte(Pouces valeur) {
        hauteur = valeur;
        return true;
    }

    public boolean modifierMousePoint(Point newMousePoint) {
        mousePoint = newMousePoint;
        return true;
    }

    public boolean setLargeurPorte(Pouces valeur) {
        largeur = valeur;
        return true;
    }


    public Pouces getLargeur() {
        return largeur;
    }


    public Pouces getHauteur() {
        return hauteur;
    }
    public boolean modifierHauteur(Pouces valeur) {
        PORTE_HAUTEUR_STANDARD = valeur;
        return true;
    }

    public List<pointPouces> getSommetsPorte(){
        return this.sommetsPorte;
    }

    }

