package ui;

import Utilitaires.PointDouble;
import domain.Accessoires;
import domain.Chalet;
import domain.Controleur;
import domain.Mur;

import java.awt.*;
import java.util.ArrayList;

public class ArriereDrawer {
    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;

    public ArriereDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g)
    {
        drawArriere(g);
    }

    private void drawArriere(Graphics g)
    {
        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        double positionX = width/2;
        double positionY = height/2;

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
        chalet.initialiserMurArriere();
        Mur arriere = chalet.getMursUsines(0.2, "Nord").get(0); // mur arriere deja codé en bas

        // Accéder coord de Mur arriere de face (ac)
        PointDouble pointSupDroitac = arriere.getSommetsMur().get(4);
        PointDouble pointSupGaucheac = arriere.getSommetsMur().get(5);
        PointDouble pointInfDroitac = arriere.getSommetsMur().get(6);
        PointDouble pointInfGaucheac = arriere.getSommetsMur().get(7);

        int x2ac = (int) pointInfGaucheac.getX();
        int y2ac = (int) pointInfGaucheac.getY();

        int x1ac = (int) pointInfDroitac.getX();
        int y1ac = (int) pointInfDroitac.getY();

        int x3ac = (int) pointSupGaucheac.getX();
        int y3ac = (int) pointSupGaucheac.getY();

        int x4ac = (int) pointSupDroitac.getX();
        int y4ac = (int) pointSupDroitac.getY();

        // Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsArriereCote = {x1ac, x2ac, x3ac, x4ac};
        int[] yPointsArriereCote = {y1ac, y2ac, y3ac, y4ac};

        // Dessiner le polygone pour le mur facade de coté (fc)
        g.fillPolygon(xPointsArriereCote, yPointsArriereCote, 4);

    }
}
