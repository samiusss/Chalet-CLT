package domain;

import Utilitaires.PointDouble;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;

public class Fenetre extends Accessoires implements Serializable {

    private List<PointDouble> sommetsFenetre;
    public Fenetre(Point point, List<PointDouble> sommetsFenetre, double largeur, double hauteur) {
        super(point, largeur, hauteur);
        this.sommetsFenetre = sommetsFenetre;
    }
    public void initialiserFenetre(){
        PointDouble pointFenetreSupDroit = new PointDouble(getPoint().getX() + getLargeur()/2, getPoint().getY()+getHauteur()/2);
        PointDouble pointFenetreSupGauche = new PointDouble(getPoint().getX()-getLargeur()/2,getPoint().getY()+getHauteur()/2);
        PointDouble pointFenetreInfGauche = new PointDouble(getPoint().getX()-getLargeur()/2,getPoint().getY()-getHauteur()/2);
        PointDouble pointFenetreInfDroit = new PointDouble(getPoint().getX()+getLargeur()/2,getPoint().getY()-getHauteur()/2);
    }
}
