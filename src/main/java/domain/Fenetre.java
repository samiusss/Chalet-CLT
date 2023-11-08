package domain;
import Utilitaires.*;
import java.util.UUID;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Fenetre extends Accessoires implements Serializable {

    public List<pointPouces> sommetsFenetre;

    public Point mousePoint;
    public static Pouces FENETRE_LARGEUR_STANDARD = new Pouces(4, 0, 1) ;
    public static Pouces FENETRE_HAUTEUR_STANDARD = new Pouces(4, 0, 1) ;

    public Fenetre(Point mousepoint, Pouces largeur, Pouces hauteur) {
        //super(largeur, hauteur);
        this.mousePoint = mousepoint;
        this.largeur = largeur;
        this.hauteur = hauteur;


    }

    public Point getPoint() {
        return mousePoint;
    }

    public boolean modifierMousePoint(Point newMousePoint) {
        mousePoint = newMousePoint;
        return false;
    }

    public Pouces setLargeur(Pouces largeur){
        this.largeur = largeur;
        return largeur;
    }

    public Pouces getLargeur() {
        return largeur;
    }

    public Pouces getHauteur() {
        return hauteur;
    }
    public boolean modifierLargeur(Pouces valeur) {
        largeur = valeur;
        this.FENETRE_LARGEUR_STANDARD = valeur;

        return false;
    }
    public boolean modifierHauteur(Pouces valeur) {
        hauteur = valeur;
        this.FENETRE_HAUTEUR_STANDARD = valeur;
        return true;
    }

    public List<pointPouces> getSommetsFenetre(){
        return this.sommetsFenetre;
    }

}
