package ui;

import domain.Chalet;
import domain.Controleur;
import domain.Mur;

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
        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        //Il faut que le controleur ou Chalet choisit les points, pas Main, puis j'appelle le point en bas
        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs
        g.setColor(new Color(166, 66, 66));

        //Dimensions du mur en 3D
        //double epaisseurMur = 70; // Épaisseur du mur test local
        double epaisseurMur = 100*Chalet.epaisseurChalet; // Épaisseur du mur test local
        double wallHeight = 1000; // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 100*Chalet.largeurChalet; // Largeur des murs venant de chalet
//////////////////////////////////////////////////
   /// Vue par dessus, if controleur.vue==dessus////
     //////////////////////////////////////////////////
        /// Coordonnées du Mur de Facade (en 3D)
        double facadeX = width / 4;
        double facadeY = height / 2 - epaisseurMur;

        // Dessiner la partie du mur de façade avec les points choisit du controleur
        int x1 = (int) facadeX;
        int y1 = (int) facadeY;
        int x2 = (int) (facadeX + largeurMur);
        int y2 = (int) (facadeY + epaisseurMur);

        int[] xPoints = {x1, x2, x2, x1};
        int[] yPoints = {y1, y1, y2, y2};

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
