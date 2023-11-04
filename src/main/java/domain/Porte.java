package domain;


import Utilitaires.PointDouble;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import Utilitaires.Pouces;


public class Porte extends Accessoires implements Serializable {

    private List<PointDouble> sommetsPorte;
    public static Pouces PORTE_LARGEUR_STANDARD = new Pouces(15, 0, 1);
    public static Pouces PORTE_HAUTEUR_STANDARD = new Pouces(35, 0, 1);

    public Point mousePoint;

    public Porte(UUID AID, Point mousepoint, List<PointDouble> sommetsPorte, Pouces largeur, Pouces hauteur) {
        super(AID,mousepoint, largeur, hauteur);
        this.sommetsPorte = sommetsPorte;
    }

    public Point getPoint() {
        return mousePoint;
    }
    public boolean modifierMousePoint(Point newMousePoint) {
        mousePoint = newMousePoint;
        return true;
    }

    public boolean modifierLargeur(Pouces valeur) {
        PORTE_LARGEUR_STANDARD = valeur;
        return true;
    }

    public boolean modifierHauteur(Pouces valeur) {
        PORTE_HAUTEUR_STANDARD = valeur;
        return true;
    }

    /* public void CreersommetPorte() {
        PointDouble pointPorteSupDroit = new PointDouble(getPoint().getX() + getLargeur() / 2, getPoint().getY() + getHauteur() / 2);
        PointDouble pointPorteSupGauche = new PointDouble(getPoint().getX() - getLargeur() / 2, getPoint().getY() + getHauteur() / 2);
        PointDouble pointPorteInfGauche = new PointDouble(getPoint().getX() - getLargeur() / 2, 0); // On suppose que la base du mur est a y=0
        PointDouble pointPorteInfDroit = new PointDouble(getPoint().getX() + getLargeur() / 2, 0);
        // Ajouter l'objet porte dans une liste qui est lié au mur.

        sommetsPorte.add(pointPorteSupDroit);
        sommetsPorte.add(pointPorteSupGauche);
        sommetsPorte.add(pointPorteInfGauche);
        sommetsPorte.add(pointPorteInfDroit);
    } */
}
