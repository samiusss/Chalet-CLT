package ui;

import Utilitaires.PointDouble;
import domain.Accessoires;
import domain.Chalet;
import domain.Controleur;
import domain.Mur;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

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
        Chalet chalet = controleur.getChaletProduction();
        g.setColor(new Color(1, 1, 166));

        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();


        chalet.initialiserMurDroite();
        Mur droite = chalet.getMursUsines(0.2, "Nord").get(0); // mur droite deja codé en bas

        // Accéder coord de Mur droite de face (dc) // Afficher en new Color(210, 68, 1)
        PointDouble pointInfGauchedc = droite.getSommetsMur().get(7); //(1.5, 0)
        PointDouble pointSupGauchedc = droite.getSommetsMur().get(5); //(1.5, 8)
        PointDouble pointSupDroitdc = droite.getSommetsMur().get(4); // (8.5, 8)
        PointDouble pointInfDroitdc = droite.getSommetsMur().get(6); // (8.5, 0)

        //Cadre du mur droit de base complet // Afficher en new Color(66, 66, 166)
        PointDouble pointInfGaucheCadre = new PointDouble(0,0);
        PointDouble pointSupGaucheCadre = new PointDouble(0, droite.getSommetsMur().get(5).getY());
        PointDouble pointSupDroitCadre = new PointDouble(droite.getSommetsMur().get(5).getX() + droite.getSommetsMur().get(6).getX(), (droite.getSommetsMur().get(5).getY()));
        PointDouble pointInfDroitCadre = new PointDouble(droite.getSommetsMur().get(5).getX() + droite.getSommetsMur().get(6).getX(), 0);

        // Centrer au milieu du drawingPanel
        double positionX = width/2;
        double positionY = height/2;
        //point mur droite en new Color(210, 68, 1)
        int x1dc = (int) (pointInfGauchedc.getX() + positionX);
        int y1dc = (int) (pointInfGauchedc.getY() + positionY);

        int x2dc = (int) (pointInfDroitdc.getX() + positionX);
        int y2dc = (int) (pointInfDroitdc.getY() + positionY);

        int x3dc = (int) (pointSupGauchedc.getX() + positionX);
        int y3dc = (int) (pointSupGauchedc.getY() + positionY);

        int x4dc = (int) (pointSupDroitdc.getX() + positionX);
        int y4dc = (int) (pointSupDroitdc.getY() + positionY);

        // points du cadre new Color(66, 66, 166)
        int x1Cadre = (int) (pointInfGaucheCadre.getX() + positionX);
        int y1Cadre = (int) (pointInfGaucheCadre.getY() + positionY);

        int x2Cadre = (int) (pointInfDroitCadre.getX() + positionX);
        int y2Cadre = (int) (pointInfDroitCadre.getY() + positionY);

        int x3Cadre = (int) (pointSupGaucheCadre.getX() + positionX);
        int y3Cadre = (int) (pointSupGaucheCadre.getY() + positionY);

        int x4Cadre = (int) (pointSupDroitCadre.getX() + positionX);
        int y4Cadre = (int) (pointSupDroitCadre.getY() + positionY);

        /*System.out.println("DroiteInfGauche" + x1dc + "," + y1dc);
        System.out.println("DroiteSupGauche" + x3dc + "," + y3dc);
        System.out.println("DroiteSupDroit" + x4dc + "," + y4dc);
        System.out.println("DroiteInfDroit" + x2dc + "," + y2dc);

        System.out.println("CadreInfGauche" + x1Cadre + "," + y1Cadre);
        System.out.println("CadreSupGauche" + x3Cadre + "," + y3Cadre);
        System.out.println("CadreSupDroit" + x4Cadre + "," + y4Cadre);
        System.out.println("CadreInfDroit" + x2Cadre + "," + y2Cadre);
        */


        /////
        int[] xPremierRect = {x1Cadre, x1dc, x3Cadre, x3dc};
        int[] yPremierRest = {y1Cadre, y1dc, y3Cadre, y3dc};
        g.setColor(new Color(66, 66, 166));
        g.fillPolygon(xPremierRect, yPremierRest, 4);

        int[] xPointsDroiteCote = {x1dc, x2dc, x3dc, x4dc};
        int[] yPointsDroiteCote = {y1dc, y2dc, y3dc, y4dc};
        g.setColor(new Color(210, 68, 1));
        //g.fillPolygon(xPointsDroiteCote, yPointsDroiteCote, 4);

        int[] xDernierRect = {x2Cadre, x2dc, x4Cadre, x4dc};
        int[] yDernierRect = {y2Cadre, y2dc, y4Cadre, y4dc};
        g.setColor(new Color(66, 66, 166));
        //g.fillPolygon(xDernierRect, yDernierRect, 4);
        ////

    }
}
