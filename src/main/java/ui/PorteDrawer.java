package ui;


import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.Porte;

import java.awt.*;
import java.util.ArrayList;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;

public class PorteDrawer
{
    private double zoomFactor;
    public void draw(Graphics g)
    {
        drawPorte(g);
    }
    private void drawPorte (Graphics g)
{
    ArrayList<pointPouces> sommetsPorte = new ArrayList<>();
    g.setColor(new Color(1,1,1));

    Pouces largeurPorte = Porte.PORTE_LARGEUR_STANDARD;
    Pouces hauteurPorte = Porte.PORTE_HAUTEUR_STANDARD;
    Point mousepoint = new Point(1,1);
    Porte porte = new Porte(mousepoint, largeurPorte, hauteurPorte);
    Point mousePoint = porte.mousePoint;
    pointPouces pointPorteSupDroit = new pointPouces(porte.getPointPouces(mousePoint).getX().addPouces(porte.getLargeur().diviserPouces(2)),porte.getPointPouces(mousePoint).getY().addPouces(porte.getHauteur().diviserPouces(2)));
    pointPouces pointPorteSupGauche=new pointPouces(porte.getPointPouces(mousePoint).getX().substractPouces(porte.getLargeur().diviserPouces(2)),porte.getPointPouces(mousePoint).getY().addPouces(porte.getHauteur().diviserPouces(2)));
    pointPouces pointPorteInfGauche = new pointPouces(porte.getPointPouces(mousePoint).getX().substractPouces(porte.getLargeur().diviserPouces(2)),new Pouces(0, 0, 1));
    pointPouces pointPorteInfDroit = new pointPouces(porte.getPointPouces(mousePoint).getX().addPouces(porte.getLargeur().diviserPouces(2)),new Pouces(0,0,1));

    int x1 = convertirPoucesEnPixels(pointPorteSupDroit.getX());
    int y1 = convertirPoucesEnPixels(pointPorteSupDroit.getY());
    int x2 = convertirPoucesEnPixels(pointPorteSupGauche.getX());
    int y2 = convertirPoucesEnPixels(pointPorteSupGauche.getY());
    int x3 = convertirPoucesEnPixels(pointPorteInfGauche.getX());
    int y3 = convertirPoucesEnPixels(pointPorteInfGauche.getY());

    int x4 = convertirPoucesEnPixels(pointPorteInfDroit.getX());
    int y4 = convertirPoucesEnPixels(pointPorteInfDroit.getY());
    int[] xPoints = {x1, x2, x3, x4};
    int[] yPoints = {y1, y2, y3, y4};

    g.fillPolygon(xPoints, yPoints, 4);

}
}
