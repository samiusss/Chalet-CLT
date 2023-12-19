package domain;
import Utilitaires.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;

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

    public boolean setPoint(Point mousePoint) {
        this.mousePoint = mousePoint;
        return true;
    }

    public Pouces getLargeur() {
        return largeur;
    }

    public Pouces getHauteur() {
        return hauteur;
    }
    public boolean modifierLargeurStandard(Pouces valeur) {
        largeur = valeur;
        FENETRE_LARGEUR_STANDARD = valeur;

        return false;
    }

    public boolean modifierHauteurStandard(Pouces valeur) {
        hauteur = valeur;
        FENETRE_HAUTEUR_STANDARD = valeur;
        return true;
    }

    public boolean setLargeurFenetre(Pouces valeur) {
        largeur = valeur;
        return true;
    }

    public boolean setHauteurFenetre(Pouces valeur) {
        hauteur = valeur;
        return true;
    }
    public Point getCoinSuperieurGauche() {
        return new Point(mousePoint.x - convertirPoucesEnPixels(largeur) / 2, mousePoint.y - convertirPoucesEnPixels(hauteur) / 2);
    }

    public Point getCoinSuperieurDroit() {
        return new Point(mousePoint.x + convertirPoucesEnPixels(largeur) / 2, mousePoint.y - convertirPoucesEnPixels(hauteur) / 2);
    }

    public Point getCoinInferieurGauche() {
        return new Point(mousePoint.x - convertirPoucesEnPixels(largeur) / 2, mousePoint.y + convertirPoucesEnPixels(hauteur) / 2);
    }

    public Point getCoinInferieurDroit() {
        return new Point(mousePoint.x + convertirPoucesEnPixels(largeur) / 2, mousePoint.y + convertirPoucesEnPixels(hauteur) / 2);
    }
    public boolean estDansMousePoint(Point targetMousePoint) {
        // Obtenez les coins de la fenêtre
        Point coinSuperieurGauche = getCoinSuperieurGauche();
        Point coinSuperieurDroit = getCoinSuperieurDroit();
        Point coinInferieurGauche = getCoinInferieurGauche();
        Point coinInferieurDroit = getCoinInferieurDroit();

        // Vérifiez si le targetMousePoint est à l'intérieur des limites de la fenêtre
        return targetMousePoint.x >= Math.min(coinSuperieurGauche.x, coinSuperieurDroit.x) &&
                targetMousePoint.x <= Math.max(coinSuperieurGauche.x, coinSuperieurDroit.x) &&
                targetMousePoint.y >= Math.min(coinSuperieurGauche.y, coinInferieurGauche.y) &&
                targetMousePoint.y <= Math.max(coinSuperieurGauche.y, coinInferieurGauche.y);
    }




    public List<pointPouces> getSommetsFenetre(){
        return this.sommetsFenetre;
    }

}
