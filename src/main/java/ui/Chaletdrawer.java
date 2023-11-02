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
            Color wallColor = new Color(18, 18, 229);

            int perspective3d = 40;
            // Dessine le mur de facade
            g.setColor(wallColor);
            g.fillRect(400, 200, 500, 100);
        }
    }
