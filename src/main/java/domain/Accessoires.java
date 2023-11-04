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


    public void setLargeur(Pouces largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(Pouces hauteur) {
        this.hauteur = hauteur;
    }

    public pointPouces getPointPouces(Point lepoint) {
        Pouces xPouces = new Pouces(lepoint.x,1,1);
        Pouces yPouces = new Pouces(lepoint.y,1,1);
        return new pointPouces (xPouces,yPouces);
    }


}

