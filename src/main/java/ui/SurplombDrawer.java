package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;

public class SurplombDrawer
{
    private Controleur controleur;

    public Chalet chalet;

    private Accessoires accessoires;
    private Dimension initialDimension;


    public Mur facade ; // mur facade deja codé en bas
    public Mur arriere; // mur arriere deja codé en bas
    public Mur gauche ; // mur gauche deja codé en bas
    public Mur droite; // mur droite deja codé en bas


    public SurplombDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
        Chalet chalet = controleur.getChaletProduction();
        this.facade = controleur.facade; // mur facade deja codé en bas
        this.arriere = controleur.arriere; // mur facade deja codé en bas
        this.droite = controleur.droite; // mur facade deja codé en bas
        this.gauche = controleur.gauche; // mur facade deja codé en bas


    }

    public void draw(Graphics g)
    {
        drawSurplomb(g);
    }


    private void drawSurplomb(Graphics g)
    {


        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        /*ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs
        g.setColor(new Color(166, 66, 66));

        //Dimensions du mur en 3D
        double epaisseurMur = Chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = Chalet.hauteurMurs;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = Chalet.largeurChalet;     // Largeur des murs venant de chalet
        double longueurMur = Chalet.longueurChalet;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
        */




        //Vue haut
        PointDouble pointInfGauchef = facade.getSommetsMur().get(0); // Je veux le premier sommet (index 0) // pointInfGauchef = pointInfGauche facade
        PointDouble pointSupGauchef = facade.getSommetsMur().get(1); // Je veux le deuxieme sommet (index 1) // pointSupGauchef = pointSupGauche facade
        PointDouble pointSupDroitf = facade.getSommetsMur().get(2); // Je veux le troisième sommet (index 2) // pointSupDroitf = pointSupDroit facade
        PointDouble pointInfDroitf = facade.getSommetsMur().get(3); // Je veux le quatrième sommet (index 3) // pointInfDroitf = pointInfDroit facade

        PointDouble rainureGauche1 = facade.getSommetsMur().get(8); // Je veux le neuvième sommet (index 8) // rainureGauche1 = rainureGauche1 facade
        PointDouble rainureGauche2 = facade.getSommetsMur().get(9); // Je veux le dixième sommet (index 9) // rainureGauche2 = rainureGauche2 facade
        PointDouble rainureDroite1 = facade.getSommetsMur().get(10); // Je veux le onzième sommet (index 10) // rainureDroite1 = rainureDroite1 facade
        PointDouble rainureDroite2 = facade.getSommetsMur().get(11); // Je veux le douzième sommet (index 11) // rainureDroite2 = rainureDroite2 facade


        // Accéder aux coordonnées de Mur: Arrière
        PointDouble pointInfGauchea = arriere.getSommetsMur().get(0);
        PointDouble pointSupGauchea = arriere.getSommetsMur().get(1);
        PointDouble pointSupDroita = arriere.getSommetsMur().get(2);
        PointDouble pointInfDroita = arriere.getSommetsMur().get(3);

        PointDouble rainureGauche1a = arriere.getSommetsMur().get(8);
        PointDouble rainureGauche2a = arriere.getSommetsMur().get(9);
        PointDouble rainureDroite1a = arriere.getSommetsMur().get(10);
        PointDouble rainureDroite2a = arriere.getSommetsMur().get(11);

        // Accéder aux coordonnées de Mur: Gauche
        PointDouble pointInfGaucheg = gauche.getSommetsMur().get(0);
        PointDouble pointSupGaucheg = gauche.getSommetsMur().get(1);
        PointDouble pointSupDroitg = gauche.getSommetsMur().get(2);
        PointDouble pointInfDroitg = gauche.getSommetsMur().get(3);

        PointDouble rainureGauche1g = gauche.getSommetsMur().get(8);
        PointDouble rainureGauche2g = gauche.getSommetsMur().get(9);
        PointDouble rainureDroite1g = gauche.getSommetsMur().get(10);
        PointDouble rainureDroite2g = gauche.getSommetsMur().get(11);

        // Accéder aux coordonnées de Mur: Droite
        PointDouble pointInfDroitd = droite.getSommetsMur().get(0);
        PointDouble pointSupDroitd = droite.getSommetsMur().get(1);
        PointDouble pointSupGauched = droite.getSommetsMur().get(2);
        PointDouble pointInfGauched = droite.getSommetsMur().get(3);

        PointDouble rainureGauche1d = droite.getSommetsMur().get(8);
        PointDouble rainureGauche2d = droite.getSommetsMur().get(9);
        PointDouble rainureDroite1d = droite.getSommetsMur().get(10);
        PointDouble rainureDroite2d = droite.getSommetsMur().get(11);

        double positionX = width/2 - pointSupDroita.getX()/2;
        double positionY = height/2 - pointSupDroita.getY()/2;

        int x1f = (int) (pointInfDroitf.getX()+positionX);
        int y1f = (int) (pointInfDroitf.getY()+positionY);
        int x1r1f = (int) (rainureDroite1.getX()+positionX);
        int y1r1f = (int) (rainureDroite1.getY()+positionY);
        int x1r2f = (int) (rainureDroite2.getX()+positionX);
        int y1r2f = (int) (rainureDroite2.getY()+positionY);
        //
        int x2f = (int) (pointSupDroitf.getX()+positionX);
        int y2f = (int) (pointSupDroitf.getY()+positionY);
        //
        int x3f = (int) (pointSupGauchef.getX()+positionX);
        int y3f = (int) (pointSupGauchef.getY()+positionY);
        int x3r1f = (int) (rainureGauche1.getX()+positionX);
        int y3r1f = (int) (rainureGauche1.getY()+positionY);
        int x3r2f = (int) (rainureGauche2.getX()+positionX);
        int y3r2f = (int) (rainureGauche2.getY()+positionY);
        //
        int x4f = (int) (pointInfGauchef.getX()+positionX);
        int y4f = (int) (pointInfGauchef.getY()+positionY);
        //
        int[] xPointsFacade = {x1f, x1r2f, x1r1f, x2f, x3f, x3r2f, x3r1f, x4f};
        int[] yPointsFacade = {y1f, y1r2f, y1r1f, y2f, y3f, y3r2f, y3r1f, y4f};
        g.setColor(new Color(12, 238, 162));
        g.fillPolygon(xPointsFacade, yPointsFacade, 8);

        //Dessiner le polygone pour le mur arriere
        int x1a = (int) (pointInfDroita.getX()+positionX);
        int y1a = (int) (pointInfDroita.getY()+positionY);
        int x1r1a = (int) (rainureDroite1a.getX()+positionX);
        int y1r1a = (int) (rainureDroite1a.getY()+positionY);
        int x1r2a = (int) (rainureDroite2a.getX()+positionX);
        int y1r2a = (int) (rainureDroite2a.getY()+positionY);
        //
        int x2a = (int) (pointSupDroita.getX()+positionX);
        int y2a = (int) (pointSupDroita.getY()+positionY);
        //
        int x3a = (int) (pointSupGauchea.getX()+positionX);
        int y3a = (int) (pointSupGauchea.getY()+positionY);
        int x3r1a = (int) (rainureGauche1a.getX()+positionX);
        int y3r1a = (int) (rainureGauche1a.getY()+positionY);
        int x3r2a = (int) (rainureGauche2a.getX()+positionX);
        int y3r2a = (int) (rainureGauche2a.getY()+positionY);
        //
        int x4a = (int) (pointInfGauchea.getX()+positionX);
        int y4a = (int) (pointInfGauchea.getY()+positionY);
        //
        int[] xPointsArriere = {x1a, x1r2a, x1r1a, x2a, x3a, x3r2a, x3r1a, x4a};
        int[] yPointsArriere = {y1a, y1r2a, y1r1a, y2a, y3a, y3r2a, y3r1a, y4a};
        g.setColor(new Color(66, 66, 166));
        g.fillPolygon(xPointsArriere, yPointsArriere, 8);

        //Dessiner le polygone pour le mur gauche
        int x1g = (int) (pointInfDroitg.getX()+positionX);
        int y1g = (int) (pointInfDroitg.getY()+positionY);
        int x1r1g = (int) (rainureDroite1g.getX()+positionX);
        int y1r1g = (int) (rainureDroite1g.getY()+positionY);
        int x1r2g = (int) (rainureDroite2g.getX()+positionX);
        int y1r2g = (int) (rainureDroite2g.getY()+positionY);
        //
        int x2g = (int) (pointSupDroitg.getX()+positionX);
        int y2g = (int) (pointSupDroitg.getY()+positionY);
        //
        int x3g = (int) (pointSupGaucheg.getX()+positionX);
        int y3g = (int) (pointSupGaucheg.getY()+positionY);
        int x3r1g = (int) (rainureGauche1g.getX()+positionX);
        int y3r1g = (int) (rainureGauche1g.getY()+positionY);
        int x3r2g = (int) (rainureGauche2g.getX()+positionX);
        int y3r2g = (int) (rainureGauche2g.getY()+positionY);
        //
        int x4g = (int) (pointInfGaucheg.getX()+positionX);
        int y4g = (int) (pointInfGaucheg.getY()+positionY);
        //
        int[] xPointsGauche = {x1r2g, x1r1g,  x1g,  x2g, x3r2g, x3r1g, x3g, x4g};
        int[] yPointsGauche = {y1r2g, y1r1g,  y1g,  y2g, y3r2g, y3r1g, y3g, y4g};
        g.setColor(new Color(198, 216, 211));
        g.fillPolygon(xPointsGauche, yPointsGauche, 8);

        //Dessiner le polygone pour le mur droit
        int x1d = (int) (pointInfDroitd.getX()+positionX);
        int y1d = (int) (pointInfDroitd.getY()+positionY);
        int x1r1d = (int) (rainureDroite1d.getX()+positionX);
        int y1r1d = (int) (rainureDroite1d.getY()+positionY);
        int x1r2d = (int) (rainureDroite2d.getX()+positionX);
        int y1r2d = (int) (rainureDroite2d.getY()+positionY);
        //
        int x2d = (int) (pointSupDroitd.getX()+positionX);
        int y2d = (int) (pointSupDroitd.getY()+positionY);
        //
        int x3d = (int) (pointSupGauched.getX()+positionX);
        int y3d = (int) (pointSupGauched.getY()+positionY);
        int x3r1d = (int) (rainureGauche1d.getX()+positionX);
        int y3r1d = (int) (rainureGauche1d.getY()+positionY);
        int x3r2d = (int) (rainureGauche2d.getX()+positionX);
        int y3r2d = (int) (rainureGauche2d.getY()+positionY);
        //
        int x4d = (int) (pointInfGauched.getX()+positionX);
        int y4d = (int) (pointInfGauched.getY()+positionY);
        //
        int[] xPointsDroit = {x1r1d, x1r2d, x1d, x2d, x3r1d, x3r2d, x3d, x4d};
        int[] yPointsDroit = {y1r1d, y1r2d, y1d, y2d, y3r1d, y3r2d, y3d, y4d};
        g.setColor(new Color(210, 68, 1));
        g.fillPolygon(xPointsDroit, yPointsDroit, 8);
    }

}
