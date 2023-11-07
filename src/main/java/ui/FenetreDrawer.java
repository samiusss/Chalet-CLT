package ui;


import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.Fenetre;

import java.awt.*;
import java.util.ArrayList;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;

public class FenetreDrawer {
    private Point centre;
    private boolean isAddingFenetre = false;
    public void draw(Graphics g)
    {
        drawFenetre(g);
    }

    private void drawFenetre(Graphics g)
    {
        ArrayList<pointPouces> sommetsFenetre = new ArrayList<>() ;
        g.setColor(new Color(1, 100, 166));
        if (isAddingFenetre) {
            Point mousepoint = new Point(1, 1);
            Fenetre fenetre = new Fenetre(mousepoint, Fenetre.FENETRE_LARGEUR_STANDARD, Fenetre.FENETRE_HAUTEUR_STANDARD);
            centre = fenetre.mousePoint;
            pointPouces pointFenetreSupDroit = new pointPouces(fenetre.getPointPouces(centre).getX().addPouces(fenetre.getLargeur().diviserPouces(2)), fenetre.getPointPouces(centre).getY().addPouces(fenetre.getHauteur().diviserPouces(2)));
            pointPouces pointFenetreSupGauche = new pointPouces(fenetre.getPointPouces(centre).getX().substractPouces(fenetre.getLargeur().diviserPouces(2)), fenetre.getPointPouces(centre).getY().addPouces(fenetre.getHauteur().diviserPouces(2)));
            pointPouces pointFenetreInfGauche = new pointPouces(fenetre.getPointPouces(centre).getX().substractPouces(fenetre.getLargeur().diviserPouces(2)), fenetre.getPointPouces(centre).getY().substractPouces(fenetre.getHauteur().diviserPouces(2)));
            pointPouces pointFenetreInfDroit = new pointPouces(fenetre.getPointPouces(centre).getX().addPouces(fenetre.getLargeur().diviserPouces(2)), fenetre.getPointPouces(centre).getY().substractPouces(fenetre.getHauteur().diviserPouces(2)));


            int x1 = convertirPoucesEnPixels(pointFenetreSupDroit.getX());
            int y1 = convertirPoucesEnPixels(pointFenetreSupDroit.getY());
            int x2 = convertirPoucesEnPixels(pointFenetreSupGauche.getX());
            int y2 = convertirPoucesEnPixels(pointFenetreSupGauche.getY());
            int x3 = convertirPoucesEnPixels(pointFenetreInfGauche.getX());
            int y3 = convertirPoucesEnPixels(pointFenetreInfGauche.getY());

            int x4 = convertirPoucesEnPixels(pointFenetreInfDroit.getX());
            int y4 = convertirPoucesEnPixels(pointFenetreInfDroit.getY());
            int[] xPoints = {x1, x2, x3, x4};
            int[] yPoints = {y1, y2, y3, y4};

            g.fillPolygon(xPoints, yPoints, 4);
        }
    }
}
