package ui;

import domain.Controleur;

import java.awt.*;

public class Chaletdrawer {
    private final Controleur controleur;
    private Dimension initialDimension;

    public Chaletdrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;

    }

    public void draw(Graphics g)
    {
        drawChalet(g);
    }

    private void drawChalet(Graphics g){
    //if controleur.vueChoisie == "Dessus"

//        int largeur1 = (int) initialDimension.getWidth();
//        int longueur1 = (int) initialDimension.getHeight();
//        g.setColor(new Color(140,98,57));
//        g.fillRect(largeur1/1.75, (int)(longueur1/4), largeur1/2, longueur1/1.75);
//
//        int largeur1 = (int) initialDimension.getWidth();
//        int longueur1 = (int) initialDimension.getHeight();
//        g.setColor(new Color(140,98,57));
//        g.fillRect(largeur1/1.75, (int)(longueur1/4), largeur1/2, longueur1/1.75;
//
//        int largeur2 = (int) initialDimension.getWidth();
//        int longueur2 = (int) initialDimension.getHeight();
//        g.setColor(new Color(140,98,57));
//        g.fillRect(longueur2/1.75, (int)(largeur2/4), largeur2/2, longueur2/1.75);
//
//        int largeur2 = (int) initialDimension.getWidth();
//        int longueur2 = (int) initialDimension.getHeight();
//        g.setColor(new Color(140,98,57));
//        g.fillRect(longueur2/1.75, (int)(largeur2/4), longueur2/2, largeur2/1.75);

    }


}