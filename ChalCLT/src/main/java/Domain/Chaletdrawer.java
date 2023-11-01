package Domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class Chaletdrawer {
    private final ChaletControleur controleur;
    private Dimension initialDimension;

    public Chaletdrawer(ChaletControleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;

    }

    public void draw(Graphics g){
        drawChalet(g);
    }

    private void drawChalet(Graphics g){
    //if controleur.vueChoisie == "Dessus"

        int largeur1 = (int) initialDimension.getWidth();
        int largeur1 = (int) initialDimension.getHeight();
        g.setColor(new Color(140,98,57));
        g.fillRect(largeur1/1.75, (int)(largeur1/4), largeur1/2, largeur1/1.75);

        int largeur1 = (int) initialDimension.getWidth();
        int longueur1 = (int) initialDimension.getHeight();
        g.setColor(new Color(140,98,57));
        g.fillRect(largeur1/1.75, (int)(longueur1/4), largeur1/2, longueur1/1.75;

        int largeur2 = (int) initialDimension.getWidth();
        int largeur2 = (int) initialDimension.getHeight();
        g.setColor(new Color(140,98,57));
        g.fillRect(largeur2/1.75, (int)(largeur2/4), largeur2/2, largeur2/1.75);

        int largeur2 = (int) initialDimension.getWidth();
        int largeur2 = (int) initialDimension.getHeight();
        g.setColor(new Color(140,98,57));
        g.fillRect(width/1.75, (int)(largeur2/4), width/2, largeur2/1.75);

    }


}