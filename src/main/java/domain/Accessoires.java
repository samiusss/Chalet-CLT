package domain;
import java.awt.Point;
import java.io.Serializable;

public abstract class Accessoires implements Serializable {
    private String AID;
    private Point mousepoint;
    private double largeur;
    private double hauteur;

    public Accessoires(String AID,Point mousepoint, double largeur, double hauteur) {
        this.mousepoint = mousepoint;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.AID=AID;
    }

    public void setPoint(Point point) {
        this.mousepoint = point;
    }
    public Point getPoint() {
        return this.mousepoint;
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

