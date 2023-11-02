package main.java.domain;
import java.awt.Point;
import java.io.Serializable;

public abstract class Accessoires implements Serializable {
    private Point point;
    private double largeur;
    private double hauteur;

    public Accessoires(Point point, double largeur, double hauteur) {
        this.point = point;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    public Point getPoint() {
        return this.point;
    }

    public double getLargeur() {
        return largeur;
    }

    public double getHauteur() {
        return hauteur;
    }


    // set la valeur de largeur lorsque l'on doit utiliser une valeur par default
    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }
    // set la valeur de hauteur lorsque l'on doit utiliser une valeur par default

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

}

