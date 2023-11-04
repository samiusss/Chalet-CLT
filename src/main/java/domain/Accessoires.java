package domain;
import Utilitaires.*;
import java.awt.Point;
import java.io.Serializable;
import java.util.UUID;


public abstract class Accessoires implements Serializable {
    private UUID AID;
    private Point mousepoint;
    public Pouces largeur;
    public Pouces hauteur;

    public Accessoires(UUID AID,Point mousepoint, Pouces largeur, Pouces hauteur) {
        this.mousepoint = mousepoint;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.AID= UUID.randomUUID();;
    }

    public UUID getIdAccessoire() {
        return AID;
    }
    public void setPoint(Point point) {
        this.mousepoint = point;
    }
    public Point getPoint() {
        return this.mousepoint;
    }

    public Pouces getLargeur() {
        return largeur;
    }

    public Pouces getHauteur() {
        return hauteur;
    }


    // set la valeur de largeur lorsque l'on doit utiliser une valeur par default
    public void setLargeur(Pouces largeur) {
        this.largeur = largeur;
    }
    // set la valeur de hauteur lorsque l'on doit utiliser une valeur par default

    public void setHauteur(Pouces hauteur) {
        this.hauteur = hauteur;
    }

   /* public pointPouces getPointPouces() {
        Pouces xPouces = new Pouces(getPoint().x);
        Pouces yPouces = new Pouces(getPoint().y);
        return new pointPouces(xPouces, yPouces);
    }
*/
}

