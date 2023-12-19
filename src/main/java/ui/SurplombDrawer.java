package ui;

import Utilitaires.PointDouble;
import domain.*;

import javax.swing.*;
import java.awt.*;

import static domain.Chalet.grilleActive;

public class SurplombDrawer extends JFrame implements java.io.Serializable
{
    private final Controleur controleur;

    public Chalet chalet;
    public ChaletDTO chaletdto;

    private Accessoires accessoires;
    private final Dimension initialDimension;

    private final double zoomFactor;


    public Mur facade ; // mur facade deja codé en bas
    public Mur arriere; // mur arriere deja codé en bas
    public Mur gauche ; // mur gauche deja codé en bas
    public Mur droite; // mur droite deja codé en bas


    public SurplombDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
        Chalet chalet = controleur.getChaletProduction();
        this.facade = ChaletDTO.facade; // mur facade deja codé en bas
        this.arriere = ChaletDTO.arriere; // mur facade deja codé en bas
        this.droite = ChaletDTO.droite; // mur facade deja codé en bas
        this.gauche = ChaletDTO.gauche; // mur facade deja codé en bas
        this.zoomFactor = controleur.getZoom();


    }

    public void draw(Graphics g)
    {
        drawSurplomb(g);
        if (grilleActive==true)
        {drawGrid(g);}
    }

    private void drawGrid(Graphics g) {

        g.setColor(Color.lightGray);

        double grilleP = Chalet.grilleP*zoomFactor;

        // Lignes verticales
        for (int x = -500; x < 1500; x += grilleP) {
            g.drawLine(x, 1500, x, -1500);
        }
        // Lignes horizontales
        for (int y = -500; y < 1500; y += grilleP) {
            g.drawLine(1500, y, -1500, y);
        }
    }

    private void drawSurplomb(Graphics g)
    {
        super.paint(g);
        //this.zoomFactor = controleur.getZoom();
        double width = initialDimension.getWidth() ;
        double height = initialDimension.getHeight();

        PointDouble pointInfGauchef = facade.getSommetsMur().get(0); // Je veux le premier sommet (index 0) // pointInfGauchef = pointInfGauche facade
        PointDouble pointSupGauchef = facade.getSommetsMur().get(1); // Je veux le deuxieme sommet (index 1) // pointSupGauchef = pointSupGauche facade
        PointDouble pointSupDroitf = facade.getSommetsMur().get(2); // Je veux le troisième sommet (index 2) // pointSupDroitf = pointSupDroit facade
        PointDouble pointInfDroitf = facade.getSommetsMur().get(3); // Je veux le quatrième sommet (index 3) // pointInfDroitf = pointInfDroit facade


        PointDouble rainureGauche1 = facade.getSommetsMur().get(8); // Je veux le neuvième sommet (index 8) // rainureGauche1 = rainureGauche1 facade
        PointDouble rainureGauche2 = facade.getSommetsMur().get(9); // Je veux le dixième sommet (index 9) // rainureGauche2 = rainureGauche2 facade
        PointDouble rainureDroite1 = facade.getSommetsMur().get(10); // Je veux le onzième sommet (index 10) // rainureDroite1 = rainureDroite1 facade
        PointDouble rainureDroite2 = facade.getSommetsMur().get(11); // Je veux le douzième sommet (index 11) // rainureDroite2 = rainureDroite2 facade


        PointDouble pointInfGauchea = arriere.getSommetsMur().get(0);
        PointDouble pointSupGauchea = arriere.getSommetsMur().get(1);
        PointDouble pointSupDroita = arriere.getSommetsMur().get(2);
        PointDouble pointInfDroita = arriere.getSommetsMur().get(3);

        PointDouble rainureGauche1a = arriere.getSommetsMur().get(8);
        PointDouble rainureGauche2a = arriere.getSommetsMur().get(9);
        PointDouble rainureDroite1a = arriere.getSommetsMur().get(10);
        PointDouble rainureDroite2a = arriere.getSommetsMur().get(11);

        PointDouble pointInfGaucheg = gauche.getSommetsMur().get(0);
        PointDouble pointSupGaucheg = gauche.getSommetsMur().get(1);
        PointDouble pointSupDroitg = gauche.getSommetsMur().get(2);
        PointDouble pointInfDroitg = gauche.getSommetsMur().get(3);

        PointDouble rainureGauche1g = gauche.getSommetsMur().get(8);
        PointDouble rainureGauche2g = gauche.getSommetsMur().get(9);
        PointDouble rainureDroite1g = gauche.getSommetsMur().get(10);
        PointDouble rainureDroite2g = gauche.getSommetsMur().get(11);

        PointDouble pointInfDroitd = droite.getSommetsMur().get(0);
        PointDouble pointSupDroitd = droite.getSommetsMur().get(1);
        PointDouble pointSupGauched = droite.getSommetsMur().get(2);
        PointDouble pointInfGauched = droite.getSommetsMur().get(3);

        PointDouble rainureGauche1d = droite.getSommetsMur().get(8);
        PointDouble rainureGauche2d = droite.getSommetsMur().get(9);
        PointDouble rainureDroite1d = droite.getSommetsMur().get(10);
        PointDouble rainureDroite2d = droite.getSommetsMur().get(11);

        double positionX = 0 ;
        double positionY = 0;

        int x1f = (int) (pointInfDroitf.getX() * zoomFactor +positionX);
        int y1f = (int) (pointInfDroitf.getY()* zoomFactor+positionY);
        int x1r1f = (int) (rainureDroite1.getX()* zoomFactor+positionX);
        int y1r1f = (int) (rainureDroite1.getY()* zoomFactor+positionY);
        int x1r2f = (int) (rainureDroite2.getX()* zoomFactor+positionX);
        int y1r2f = (int) (rainureDroite2.getY()* zoomFactor+positionY);

        int x2f = (int) (pointSupDroitf.getX()* zoomFactor+positionX);
        int y2f = (int) (pointSupDroitf.getY()* zoomFactor+positionY);

        int x3f = (int) (pointSupGauchef.getX()* zoomFactor+positionX);
        int y3f = (int) (pointSupGauchef.getY()* zoomFactor+positionY);
        int x3r1f = (int) (rainureGauche1.getX()* zoomFactor+positionX);
        int y3r1f = (int) (rainureGauche1.getY()* zoomFactor+positionY);
        int x3r2f = (int) (rainureGauche2.getX()* zoomFactor+positionX);
        int y3r2f = (int) (rainureGauche2.getY()* zoomFactor+positionY);

        int x4f = (int) (pointInfGauchef.getX()* zoomFactor+positionX);
        int y4f = (int) (pointInfGauchef.getY()* zoomFactor+positionY);

        int[] xPointsFacade = {x1f, x1r2f, x1r1f, x2f, x3f, x3r2f, x3r1f, x4f};
        int[] yPointsFacade = {y1f, y1r2f, y1r1f, y2f, y3f, y3r2f, y3r1f, y4f};
        g.setColor(new Color(104, 190, 83));
        g.fillPolygon(xPointsFacade, yPointsFacade, 8);

        int x1a = (int) (pointInfDroita.getX()* zoomFactor+positionX);
        int y1a = (int) (pointInfDroita.getY()* zoomFactor+positionY);
        int x1r1a = (int) (rainureDroite1a.getX()* zoomFactor+positionX);
        int y1r1a = (int) (rainureDroite1a.getY()* zoomFactor+positionY);
        int x1r2a = (int) (rainureDroite2a.getX()* zoomFactor+positionX);
        int y1r2a = (int) (rainureDroite2a.getY()* zoomFactor+positionY);

        int x2a = (int) (pointSupDroita.getX()* zoomFactor+positionX);
        int y2a = (int) (pointSupDroita.getY()* zoomFactor+positionY);

        int x3a = (int) (pointSupGauchea.getX()* zoomFactor+positionX);
        int y3a = (int) (pointSupGauchea.getY()* zoomFactor+positionY);
        int x3r1a = (int) (rainureGauche1a.getX()* zoomFactor+positionX);
        int y3r1a = (int) (rainureGauche1a.getY()* zoomFactor+positionY);
        int x3r2a = (int) (rainureGauche2a.getX()* zoomFactor+positionX);
        int y3r2a = (int) (rainureGauche2a.getY()* zoomFactor+positionY);

        int x4a = (int) (pointInfGauchea.getX()* zoomFactor+positionX);
        int y4a = (int) (pointInfGauchea.getY()* zoomFactor+positionY);
        //
        int[] xPointsArriere = {x1a, x1r2a, x1r1a, x2a, x3a, x3r2a, x3r1a, x4a};
        int[] yPointsArriere = {y1a, y1r2a, y1r1a, y2a, y3a, y3r2a, y3r1a, y4a};
        g.setColor(new Color(225, 118, 118));
        g.fillPolygon(xPointsArriere, yPointsArriere, 8);


        int x1g = (int) (pointInfDroitg.getX()* zoomFactor+positionX);
        int y1g = (int) (pointInfDroitg.getY()* zoomFactor+positionY);
        int x1r1g = (int) (rainureDroite1g.getX()* zoomFactor+positionX);
        int y1r1g = (int) (rainureDroite1g.getY()* zoomFactor+positionY);
        int x1r2g = (int) (rainureDroite2g.getX()* zoomFactor+positionX);
        int y1r2g = (int) (rainureDroite2g.getY()* zoomFactor+positionY);

        int x2g = (int) (pointSupDroitg.getX()* zoomFactor+positionX);
        int y2g = (int) (pointSupDroitg.getY()* zoomFactor+positionY);

        int x3g = (int) (pointSupGaucheg.getX()* zoomFactor+positionX);
        int y3g = (int) (pointSupGaucheg.getY()* zoomFactor+positionY);
        int x3r1g = (int) (rainureGauche1g.getX()* zoomFactor+positionX);
        int y3r1g = (int) (rainureGauche1g.getY()* zoomFactor+positionY);
        int x3r2g = (int) (rainureGauche2g.getX()* zoomFactor+positionX);
        int y3r2g = (int) (rainureGauche2g.getY() * zoomFactor+positionY);

        int x4g = (int) (pointInfGaucheg.getX()* zoomFactor+positionX);
        int y4g = (int) (pointInfGaucheg.getY()* zoomFactor+positionY);

        int[] xPointsGauche = {x1r2g, x1r1g,  x1g,  x2g, x3r2g, x3r1g, x3g, x4g};
        int[] yPointsGauche = {y1r2g, y1r1g,  y1g,  y2g, y3r2g, y3r1g, y3g, y4g};
        g.setColor(new Color(239, 167, 139));
        g.fillPolygon(xPointsGauche, yPointsGauche, 8);

        int x1d = (int) (pointInfDroitd.getX()* zoomFactor+positionX);
        int y1d = (int) (pointInfDroitd.getY()* zoomFactor+positionY);
        int x1r1d = (int) (rainureDroite1d.getX()* zoomFactor+positionX);
        int y1r1d = (int) (rainureDroite1d.getY()* zoomFactor+positionY);
        int x1r2d = (int) (rainureDroite2d.getX()* zoomFactor+positionX);
        int y1r2d = (int) (rainureDroite2d.getY()* zoomFactor+positionY);

        int x2d = (int) (pointSupDroitd.getX()* zoomFactor+positionX);
        int y2d = (int) (pointSupDroitd.getY()* zoomFactor+positionY);

        int x3d = (int) (pointSupGauched.getX()* zoomFactor+positionX);
        int y3d = (int) (pointSupGauched.getY()* zoomFactor+positionY);
        int x3r1d = (int) (rainureGauche1d.getX()* zoomFactor+positionX);
        int y3r1d = (int) (rainureGauche1d.getY()* zoomFactor+positionY);
        int x3r2d = (int) (rainureGauche2d.getX()* zoomFactor+positionX);
        int y3r2d = (int) (rainureGauche2d.getY()* zoomFactor+positionY);

        int x4d = (int) (pointInfGauched.getX()* zoomFactor+positionX);
        int y4d = (int) (pointInfGauched.getY()* zoomFactor+positionY);

        int[] xPointsDroit = {x1r1d, x1r2d, x1d, x2d, x3r1d, x3r2d, x3d, x4d};
        int[] yPointsDroit = {y1r1d, y1r2d, y1d, y2d, y3r1d, y3r2d, y3d, y4d};
        g.setColor(new Color(96, 96, 238));
        g.fillPolygon(xPointsDroit, yPointsDroit, 8);


    }
}
