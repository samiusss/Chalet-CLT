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
        //chalet.initialiserMurDroite();
        //chalet.initialiserMurGauche();
        //chalet.initialiserMurArriere();

        // Accédez aux coordonnées du pointSupDroit de Mur: Facade
        Mur facade = chalet.getListeMurs().get(0); // Je veux le premier mur de la liste (Mur: Facade)
        PointDouble pointSupDroit = facade.getSommetsMur().get(2); // Je veux le troisième sommet (index 2) qui a les coordonnées (10.0, 2.0)
        PointDouble pointSupGauche = facade.getSommetsMur().get(1); // Je veux le deuxieme sommet (index 1) qui a les coordonnées (10.0, 2.0)
        PointDouble pointInfDroit = facade.getSommetsMur().get(3); // Je veux le quatrième sommet (index 3) qui a les coordonnées (10.0, 2.0)
        PointDouble pointInfGauche = facade.getSommetsMur().get(0); // Je veux le premier sommet (index 0) qui a les coordonnées (10.0, 2.0)


        //double facadeX = largeurMur / 4;
        //double facadeY = largeurMur / 1.3 - epaisseurMur;

        // Dessiner la partie du mur de façade avec les points choisit du controleur
/*      int x1 = (int) facadeX;
        int y1 = (int) facadeY;
        int x2 = (int) (facadeX + largeurMur);
        int y2 = (int) (facadeY + epaisseurMur);*/

        double positionZero = 400;
        int x1 = (int) (pointInfDroit.getX()+positionZero);
        int y1 = (int) (pointInfDroit.getY()+positionZero);
        //
        int x2 = (int) (pointSupGauche.getX()+positionZero);
        int y2 = (int) (pointSupGauche.getY()+positionZero);
        //test d'un point, pointSupDroit
        int x3 = (int) (pointSupDroit.getX()+positionZero);
        int y3 = (int) (pointSupDroit.getY()+positionZero);
        //
        int x4 = (int) (pointInfGauche.getX()+positionZero);//(positionZero+largeurMur);
        int y4 = (int) (pointInfGauche.getY()+positionZero);//positionZero;

        int[] xPoints = {x1, x3, x2, x4};
        int[] yPoints = {y1, y3, y2, y4};

        g.fillPolygon(xPoints, yPoints, 4);


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
