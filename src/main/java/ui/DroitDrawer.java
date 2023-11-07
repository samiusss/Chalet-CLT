package ui;

import Utilitaires.PointDouble;
import domain.Accessoires;
import domain.Chalet;
import domain.Controleur;
import domain.Mur;

import java.awt.*;
import java.util.ArrayList;

public class DroitDrawer {

    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;

    public DroitDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g)
    {
        drawDroit(g);
    }

    private void drawDroit(Graphics g)
    {
        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs
        g.setColor(new Color(166, 66, 66));

        //Dimensions du mur en 3D
        double epaisseurMur = 2*Chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = 2*Chalet.hauteurMurs;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 2*Chalet.largeurChalet;     // Largeur des murs venant de chalet
        double longueurMur = 2*Chalet.longueurChalet;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
        chalet.initialiserMurDroite();
        Mur droite = chalet.getMursUsines(0.2, "Nord").get(0); // mur droite deja codé en bas

        // Accéder coord de Mur droite de face (dc)
        PointDouble pointSupDroitdc = droite.getSommetsMur().get(4);
        PointDouble pointSupGauchedc = droite.getSommetsMur().get(5);
        PointDouble pointInfDroitdc = droite.getSommetsMur().get(6);
        PointDouble pointInfGauchedc = droite.getSommetsMur().get(7);

        // Centrer au milieu du drawingPanel
        double positionX = width/2;
        double positionY = height/2;

        int x1dc = (int) pointInfGauchedc.getX();
        int y1dc = (int) pointInfGauchedc.getY();

        int x2dc = (int) pointInfDroitdc.getX();
        int y2dc = (int) pointInfDroitdc.getY();

        int x3dc = (int) pointSupGauchedc.getX();
        int y3dc = (int) pointSupGauchedc.getY();

        int x4dc = (int) pointSupDroitdc.getX();
        int y4dc = (int) pointSupDroitdc.getY();

// Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsDroiteCote = {x1dc, x2dc, x3dc, x4dc};
        int[] yPointsDroiteCote = {y1dc, y2dc, y3dc, y4dc};

// Dessiner le polygone pour le mur facade de coté (dc)

        g.fillPolygon(xPointsDroiteCote, yPointsDroiteCote, 4);

    }
}
