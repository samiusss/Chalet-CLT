package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.*;
import static domain.Chalet.*;

public class ArriereDrawer {
    private Controleur controleur;
    public static Chalet chalet;
    public ChaletDTO chaletdto;
    private Accessoires accessoires;
    private Dimension initialDimension;
    public Mur arriere ; // mur arriere deja codé en bas

    public PointDouble RainureGaucheInfGauche, RainureGaucheSupGauche, RainureGaucheSupDroit, RainureGaucheInfDroit;
    public PointDouble RainureDroiteInfGauche, RainureDroiteSupGauche, RainureDroiteSupDroit, RainureDroiteInfDroite;
    private double zoomFactor;

    public ArriereDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;

        Chalet chalet = controleur.getChaletProduction();
        this.arriere = chaletdto.arriere; // mur facade deja codé en bas
        this.zoomFactor = controleur.getZoom();
    }

    public void draw(Graphics g)
    {
        drawArriere(g);
        drawPorte(g);
        drawFenetre(g);
    }



    private void drawFenetre(Graphics g) {

        System.out.println("porteARRIERE");

        g.setColor(new Color(102, 102, 0));

        List<Fenetre> listeFenetre = arriere.getListeFenetre();
        int lenghtlisteFenetre = listeFenetre.size();

        for (Fenetre fenetre : listeFenetre) {

            Fenetre fenetreActuel = (Fenetre) fenetre;
            if (fenetreActuel != null) {

                Point mousePoint = fenetre.mousePoint;

                Pouces largeur = fenetre.getLargeur();
                Pouces hauteur = fenetre.getHauteur();

                int largeurFenetreInt = convertirPoucesEnInt(largeur);
                int hauteurFenetreInt = convertirPoucesEnInt(hauteur);

                g.fillRect(mousePoint.x, mousePoint.y, largeurFenetreInt, hauteurFenetreInt);
            }
        }
    }


    private void drawPorte(Graphics g) {

        g.setColor(new Color(204, 255, 204));
        List<Porte> listePorte = arriere.getListePorte();
        int lenghtlistePorte = listePorte.size();

        for (Porte porte : listePorte) {
            Porte porteActuel = (Porte) porte;
            if (porteActuel != null) {

                Point mousePoint = porte.mousePoint;
                Pouces largeur = porte.getLargeur();
                Pouces hauteur = porte.getHauteur();

                int largeurPorteInt = convertirPoucesEnInt(largeur);
                int hauteurPorteInt = convertirPoucesEnInt(hauteur);


                double height = initialDimension.getHeight();
                PointDouble pointInfDroitac = arriere.getSommetsMur().get(6);
                PointDouble pointInfGaucheac = arriere.getSommetsMur().get(7);
                double positionY = height/2 - pointInfDroitac.getY()/2;
                int y1ac = (int) ((pointInfGaucheac.getY() + positionY) - hauteurPorteInt);


                g.fillRect(mousePoint.x, y1ac, largeurPorteInt, hauteurPorteInt);


            }
        }


    }


    private void drawArriere(Graphics g)
    {


        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        PointDouble point1 = arriere.getSommetsMur().get(4);
        PointDouble point2 = arriere.getSommetsMur().get(5);
        PointDouble point3 = arriere.getSommetsMur().get(6);
        PointDouble point4 = arriere.getSommetsMur().get(7);

        if(point2.getX() != 0 || point4.getX() != 0){
            RainureGaucheInfGauche = new PointDouble(0, 0);
            RainureGaucheSupGauche = new PointDouble(0, hauteurMurs);
            RainureGaucheSupDroit = new PointDouble(epaisseurChalet/2, hauteurMurs);
            RainureGaucheInfDroit = new PointDouble(epaisseurChalet/2, 0);

            RainureDroiteInfGauche = new PointDouble(point3.getX(), 0);
            RainureDroiteSupGauche = new PointDouble(point3.getX(), hauteurMurs);
            RainureDroiteSupDroit = new PointDouble(longueurChalet, hauteurMurs);
            RainureDroiteInfDroite = new PointDouble(longueurChalet, 0);
        }else{
            RainureGaucheInfGauche = new PointDouble(0, 0);
            RainureGaucheSupGauche = new PointDouble(0, hauteurMurs);
            RainureGaucheSupDroit = new PointDouble(0, hauteurMurs);
            RainureGaucheInfDroit = new PointDouble(0, 0);

            RainureDroiteInfGauche = new PointDouble(longueurChalet, 0);
            RainureDroiteSupGauche = new PointDouble(longueurChalet, hauteurMurs);
            RainureDroiteSupDroit = new PointDouble(longueurChalet, hauteurMurs);
            RainureDroiteInfDroite = new PointDouble(longueurChalet, 0);
        }
        int positionX = (int) (width/2 - point3.getX()/2);
        int positionY = (int) (height/2- point3.getY()/2);

        int x1 = (int) (point1.getX() * zoomFactor) + positionX;
        int y1 = (int) (point1.getY() * zoomFactor) + positionY;

        int x2 = (int) (point2.getX() * zoomFactor) + positionX;
        int y2 = (int) (point2.getY() * zoomFactor)+ positionY;

        int x3 = (int) (point3.getX() * zoomFactor)+ positionX;
        int y3 = (int) (point3.getY() * zoomFactor)+ positionY;

        int x4 = (int) (point3.getX() * zoomFactor)+ positionX;
        int y4 = (int) (point1.getY() * zoomFactor)+ positionY;

        int x1RainureGauche = (int) (RainureGaucheInfGauche.getX() * zoomFactor)+ positionX;
        int y1RainureGauche = (int) (RainureGaucheInfGauche.getY() * zoomFactor)+ positionY;

        int x2RainureGauche = (int) (RainureGaucheSupGauche.getX() * zoomFactor)+ positionX;
        int y2RainureGauche = (int) (RainureGaucheSupGauche.getY() * zoomFactor)+ positionY;

        int x3RainureGauche = (int) (RainureGaucheSupDroit.getX() * zoomFactor)+ positionX;
        int y3RainureGauche = (int) (RainureGaucheSupDroit.getY() * zoomFactor)+ positionY;

        int x4RainureGauche = (int) (RainureGaucheInfDroit.getX() * zoomFactor)+ positionX;
        int y4RainureGauche = (int) (RainureGaucheInfDroit.getY() * zoomFactor)+ positionY;

        int x1RainureDroite = (int) (RainureDroiteInfGauche.getX() * zoomFactor)+ positionX;
        int y1RainureDroite = (int) (RainureDroiteInfGauche.getY() * zoomFactor)+ positionY;

        int x2RainureDroite = (int) (RainureDroiteSupGauche.getX() * zoomFactor)+ positionX;
        int y2RainureDroite = (int) (RainureDroiteSupGauche.getY() * zoomFactor)+ positionY;

        int x3RainureDroite = (int) (RainureDroiteSupDroit.getX() * zoomFactor)+ positionX;
        int y3RainureDroite = (int) (RainureDroiteSupDroit.getY() * zoomFactor)+ positionY;

        int x4RainureDroite = (int) (RainureDroiteInfDroite.getX() * zoomFactor)+ positionX;
        int y4RainureDroite = (int) (RainureDroiteInfDroite.getY() * zoomFactor)+ positionY;

        int[] xPointsRainureGauche = {x1RainureGauche, x2RainureGauche, x3RainureGauche, x4RainureGauche};
        int[] yPointsRainureGauche = {y1RainureGauche, y2RainureGauche, y3RainureGauche, y4RainureGauche};
        g.setColor(new Color(239, 167, 139));
        g.fillPolygon(xPointsRainureGauche, yPointsRainureGauche, 4);

        int[] xPointsRainureDroite = {x1RainureDroite, x2RainureDroite, x3RainureDroite, x4RainureDroite};
        int[] yPointsRainureDroite = {y1RainureDroite, y2RainureDroite, y3RainureDroite, y4RainureDroite};
        g.setColor(new Color(96, 96, 238));
        g.fillPolygon(xPointsRainureDroite, yPointsRainureDroite, 4);

        int[] xPoints = {x1, x2, x3, x4};
        int[] yPoints = {y1, y2, y3, y4};
        g.setColor(new Color(104, 190, 83));
        g.fillPolygon(xPoints, yPoints, 4);

    }
}
