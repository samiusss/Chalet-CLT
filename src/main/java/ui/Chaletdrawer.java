package ui;

import domain.Chalet;
import domain.Controleur;
import domain.Mur;
import Utilitaires.*;

import java.awt.*;
import java.util.ArrayList;

public class Chaletdrawer {
    private Controleur controleur;
    private Chalet chalet;

    private Dimension initialDimension;

    public Chaletdrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g)
    {
        drawChalet(g);
    }

    private void drawAccessoire(Graphics g)
    {
        //
    }

    private void drawChalet(Graphics g) {
        //double width = initialDimension.getWidth();
        //double height = initialDimension.getHeight();

        //Il faut que le controleur ou Chalet choisit les points, pas Main, puis j'appelle le point en bas
        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs
        g.setColor(new Color(166, 66, 66));

        //Dimensions du mur en 3D
        double epaisseurMur = 2*Chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = 2*Chalet.hauteurMurs; // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 2*Chalet.largeurChalet; // Largeur des murs venant de chalet
        double longueurMur = 2*Chalet.longueurChalet;
        double angleToit = 0.0;


 //////////////////////////////////////////////////
   /// Vue par dessus, if controleur.vue==dessus////
     //////////////////////////////////////////////////
        /// Coordonnées du Mur de Facade (en 3D)

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
        chalet.initialiserMurFacade();
        chalet.initialiserMurDroite();
        chalet.initialiserMurGauche();
        chalet.initialiserMurArriere();

        // Accédez aux coordonnées de Mur: Facade
        Mur facade = chalet.getMursUsines().get(0); // Je veux le premier mur de la liste (Mur: Facade)
        PointDouble pointSupDroitf = facade.getSommetsMur().get(2); // Je veux le troisième sommet (index 2)
        PointDouble pointSupGauchef = facade.getSommetsMur().get(1); // Je veux le deuxieme sommet (index 1)
        PointDouble pointInfDroitf = facade.getSommetsMur().get(3); // Je veux le quatrième sommet (index 3)
        PointDouble pointInfGauchef = facade.getSommetsMur().get(0); // Je veux le premier sommet (index 0)
        PointDouble rainureGauche1 = facade.getSommetsMur().get(8);
        PointDouble rainureGauche2 = facade.getSommetsMur().get(9);
        PointDouble rainureDroite1 = facade.getSommetsMur().get(10);
        PointDouble rainureDroite2 = facade.getSommetsMur().get(11);


        //double facadeX = largeurMur / 4;
        //double facadeY = largeurMur / 1.3 - epaisseurMur;

        // Dessiner la partie du mur de façade avec les points choisit du controleur
/*      int x1 = (int) facadeX;
        int y1 = (int) facadeY;
        int x2 = (int) (facadeX + largeurMur);
        int y2 = (int) (facadeY + epaisseurMur);*/

        double positionZero = 400;
        int x1 = (int) (pointInfDroitf.getX()+positionZero);
        int y1 = (int) (pointInfDroitf.getY()+positionZero);

        int x3r1 = (int) (rainureGauche1.getX()+positionZero);
        int y3r1 = (int) (rainureGauche1.getY()+positionZero);
        int x3r2 = (int) (rainureGauche2.getX()+positionZero);
        int y3r2 = (int) (rainureGauche2.getY()+positionZero);
        //
        int x2 = (int) (pointSupDroitf.getX()+positionZero);
        int y2 = (int) (pointSupDroitf.getY()+positionZero);
        //
        int x3 = (int) (pointSupGauchef.getX()+positionZero);
        int y3 = (int) (pointSupGauchef.getY()+positionZero);

        int x1r1 = (int) (rainureDroite1.getX()+positionZero);
        int y1r1 = (int) (rainureDroite1.getY()+positionZero);
        int x1r2 = (int) (rainureDroite2.getX()+positionZero);
        int y1r2 = (int) (rainureDroite2.getY()+positionZero);
        //
        int x4 = (int) (pointInfGauchef.getX()+positionZero);
        int y4 = (int) (pointInfGauchef.getY()+positionZero);
        //
        int[] xPoints = {x1,  x1r2, x1r1, x2, x3, x3r2, x3r1, x4};
        int[] yPoints = {y1,  y1r2, y1r1, y2, y3, y3r2, y3r1, y4};

        g.fillPolygon(xPoints, yPoints, 8);


        // Dessiner le mur de façade en profondeur, pour ressemble à celui de l'énoncé
        /*double pointDeVue3D = 120; // Hauteur de la perspective
        g.setColor(new Color(1, 1, 150));
        int[] xPointsP = {
                (int) facadeX,
                (int) (facadeX + 40),
                (int) (facadeX + largeurMur - 40),
                (int) (facadeX + largeurMur)
        };
        int[] yPointsP = {
                (int) facadeY,
                (int) (facadeY - pointDeVue3D),
                (int) (facadeY - pointDeVue3D),
                (int) facadeY
        };
        g.fillPolygon(xPointsP, yPointsP, 4);*/
    }
    }
