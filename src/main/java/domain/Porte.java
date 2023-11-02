package domain;

import Utilitaires.PointDouble;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Porte extends Accessoires implements Serializable {

    private List<PointDouble> sommetsPorte;

    public Porte(String AID,Point point, List<PointDouble> sommetsPorte, double largeur, double hauteur) {
        super(AID,point, largeur, hauteur);
        this.sommetsPorte = sommetsPorte;
    }
    public void CreersommetPorte() {
        PointDouble pointPorteSupDroit = new PointDouble(getPoint().getX() + getLargeur() / 2, getPoint().getY() + getHauteur() / 2);
        PointDouble pointPorteSupGauche = new PointDouble(getPoint().getX() - getLargeur() / 2, getPoint().getY() + getHauteur() / 2);
        PointDouble pointPorteInfGauche = new PointDouble(getPoint().getX() - getLargeur() / 2, 0); // On suppose que la base du mur est a y=0
        PointDouble pointPorteInfDroit = new PointDouble(getPoint().getX() + getLargeur() / 2, 0);
        // Ajouter l'objet porte dans une liste qui est lié au mur.

        sommetsPorte.add(pointPorteSupDroit);
        sommetsPorte.add(pointPorteSupGauche);
        sommetsPorte.add(pointPorteInfGauche);
        sommetsPorte.add(pointPorteInfDroit);
    }
}
