package ui;

import domain.*;
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

    private void drawChalet(Graphics g){  // dessine les 4 murs
            int largeur = (int) initialDimension.getWidth();
            int longueur = (int) initialDimension.getHeight();

            // Couleur du mur
            Color wallColor = new Color(40, 98, 57);

            int perspective3d = 40;
            // Dessine le mur de facade
            g.setColor(wallColor);
            g.fillRect(largeur, longueur, largeur, longueur);

            // bordures sombres pour donner un aspect en relief
            g.setColor(Color.BLACK);
            g.drawRect(largeur / 4, longueur / 4, largeur / 2, longueur / 2);
            g.drawRect(largeur / 4 + 10, longueur / 4 + 10, largeur / 2, longueur / 2);
        }
    }
