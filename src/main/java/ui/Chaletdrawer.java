package ui;

import domain.Controleur;

import java.awt.*;

public class Chaletdrawer {
    private Controleur controleur;
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

        // Définir la couleur des murs
        g.setColor(new Color(66, 66, 66));

        // Dimensions du mur en 3D
        double epaisseurMur = 70; // Épaisseur du mur
        double wallHeight = 1000; // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 800; // Largeur des murs

        // Coordonnées du Mur de Facade (en 3D)
        double facadeX = width / 4;
        double facadeY = height / 2 - epaisseurMur;

        // Dessiner la partie inférieure du mur de façade
        int x1 = (int) facadeX;
        int y1 = (int) facadeY;
        int x2 = (int) (facadeX + largeurMur);
        int y2 = (int) (facadeY + epaisseurMur);
        g.fillRect(x1, y1, x2 - x1, y2 - y1);

        // Dessiner le mur de façade en perspective
        double pointDeVue3D = 120; // Hauteur de la perspective
        /*g.setColor(new Color(1, 1, 150));
        int[] xPoints = {
                (int) facadeX,
                (int) (facadeX + 40),
                (int) (facadeX + largeurMur - 40),
                (int) (facadeX + largeurMur)
        };
        int[] yPoints = {
                (int) facadeY,
                (int) (facadeY - pointDeVue3D),
                (int) (facadeY - pointDeVue3D),
                (int) facadeY
        };
        g.fillPolygon(xPoints, yPoints, 4);*/
    }


    }
