package domain;
import Utilitaires.Pouces;
import Utilitaires.PointDouble;
import java.util.UUID;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Fenetre extends Accessoires implements Serializable {

    private List<PointDouble> sommetsFenetre;

    public Point mousePoint;
    public static Pouces FENETRE_LARGEUR_STANDARD = new Pouces(10, 0, 1) ;
    public static Pouces FENETRE_HAUTEUR_STANDARD = new Pouces(10, 0, 1) ;

    public Fenetre(UUID AID,Point mousepoint, List<PointDouble> sommetsFenetre, Pouces largeur, Pouces hauteur) {
        super(AID,mousepoint, largeur, hauteur);
        this.sommetsFenetre = sommetsFenetre;
    }

    public Point getPoint() {
        return mousePoint;
    }

    public boolean modifierMousePoint(Point newMousePoint) {
        mousePoint = newMousePoint;
        return true;
    }

    public boolean modifierLargeur(Pouces valeur) {
        largeur = valeur;
        this.FENETRE_LARGEUR_STANDARD = valeur;

        return true;
    }
    public boolean modifierHauteur(Pouces valeur) {
        hauteur = valeur;
        this.FENETRE_HAUTEUR_STANDARD = valeur;
        return true;
    }


    /*public void CreersommetFenetre(){
        PointDouble pointFenetreSupDroit = new PointDouble(getPoint().getX() + getLargeur()/2, getPoint().getY()+getHauteur()/2);
        PointDouble pointFenetreSupGauche = new PointDouble(getPoint().getX()-getLargeur()/2,getPoint().getY()+getHauteur()/2);
        PointDouble pointFenetreInfGauche = new PointDouble(getPoint().getX()-getLargeur()/2,getPoint().getY()-getHauteur()/2);
        PointDouble pointFenetreInfDroit = new PointDouble(getPoint().getX()+getLargeur()/2,getPoint().getY()-getHauteur()/2);
        // Ajouter l'objet fenetre dans une liste qui est lié au mur et les points dans une liste de points
        sommetsFenetre.add(pointFenetreSupDroit);
        sommetsFenetre.add(pointFenetreSupGauche);
        sommetsFenetre.add(pointFenetreInfGauche);
        sommetsFenetre.add(pointFenetreInfDroit);
    } */
}
