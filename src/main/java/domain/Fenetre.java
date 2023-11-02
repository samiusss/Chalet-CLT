package domain;

import Utilitaires.PointDouble;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Fenetre extends Accessoires implements Serializable {

    private List<PointDouble> sommetsFenetre;
    public Fenetre(String AID,Point point, List<PointDouble> sommetsFenetre, double largeur, double hauteur) {
        super(AID,point, largeur, hauteur);
        this.sommetsFenetre = sommetsFenetre;
    }
    public void initialiserFenetre(){
        PointDouble pointFenetreSupDroit = new PointDouble(getPoint().getX() + getLargeur()/2, getPoint().getY()+getHauteur()/2);
        PointDouble pointFenetreSupGauche = new PointDouble(getPoint().getX()-getLargeur()/2,getPoint().getY()+getHauteur()/2);
        PointDouble pointFenetreInfGauche = new PointDouble(getPoint().getX()-getLargeur()/2,getPoint().getY()-getHauteur()/2);
        PointDouble pointFenetreInfDroit = new PointDouble(getPoint().getX()+getLargeur()/2,getPoint().getY()-getHauteur()/2);
        // Ajouter l'objet fenetre dans une liste qui est lié au mur.
    }
}
