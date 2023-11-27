package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnInt;
import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;
import static domain.Chalet.*;

public class FacadeDrawer {

    private Controleur controleur;
    public ChaletDTO chaletdto;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;

    public Mur facade ; // mur facade deja codé en bas

    public PointDouble GaucheRainureInfGauche, GaucheRainureInfDroit, GaucheRainureSupGauche, GaucheRainureSupDroit;
    public PointDouble DroiteRainureInfGauche, DroiteRainureInfDroit, DroiteRainureSupGauche, DroiteRainureSupDroit;
    private double zoomFactor;
    private float offsetX;
    private float offsetY;
    private boolean existeDEJA;

    public FacadeDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
        Chalet chalet = controleur.getChaletProduction();
        this.facade = chaletdto.facade;
        this.zoomFactor = controleur.getZoom();
        this.offsetX = controleur.getOffsetX();
        this.offsetY = controleur.getOffsetY();
    }

    public void draw(Graphics g)
    {
        drawFacade(g);
        drawPorte(g);
        drawFenetre(g);
    }


    private void drawFenetre(Graphics g) {

        g.setColor(new Color(1, 1, 0));
        this.zoomFactor = controleur.getZoom();
        this.offsetX = controleur.getOffsetX();
        this.offsetY = controleur.getOffsetY();

        List<Fenetre> listeFenetre = facade.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {
            Fenetre fenetreActuel = fenetre;
            if (fenetreActuel != null) {
                Point mousePoint = fenetre.mousePoint;

                Pouces largeur = fenetre.getLargeur();
                Pouces hauteur = fenetre.getHauteur();
                int hauteurPorteInt = (int) (convertirPoucesEnInt(hauteur) * zoomFactor);

                PointDouble pointInfDroitac = facade.getSommetsMur().get(6);
                PointDouble pointInfGaucheac = facade.getSommetsMur().get(7);

                offsetX = 1;
                //double offsetX = initialDimension.getWidth()/2 - pointInfDroitac.getX()/2;
                //double offsetY = initialDimension.getHeight()/2 - facade.getSommetsMur().get(5).getY()/2;

                int x1ac = (int) (((mousePoint.x - offsetX ) * zoomFactor ) + offsetX);
                int y1ac = (int) (((mousePoint.y - offsetY ) * zoomFactor ) + offsetY);

                int largeurFenetreInt = (int)(convertirPoucesEnInt(largeur) * zoomFactor);
                int hauteurFenetreInt = (int)(convertirPoucesEnInt(hauteur) * zoomFactor);

                g.fillRect(x1ac, y1ac, largeurFenetreInt, hauteurFenetreInt);
            }
        }
    }



    private void drawPorte(Graphics g) {

        g.setColor(new Color(1, 1, 0));
        this.zoomFactor = controleur.getZoom();
        this.offsetX = controleur.getOffsetX();
        this.offsetY = controleur.getOffsetY();

        List<Porte> listePorte = facade.getListePorte();
        int lenghtlistePorte = listePorte.size();


        for (Porte porte : listePorte) {

            Porte porteActuel = porte;
            if (porteActuel != null) {

                Point mousePoint = porte.mousePoint;

                Pouces largeur = porte.getLargeur();
                Pouces hauteur = porte.getHauteur();

                int largeurPorteInt = (int) (convertirPoucesEnInt(largeur) * zoomFactor);
                int hauteurPorteInt = (int) (convertirPoucesEnInt(hauteur) * zoomFactor);

                double height = initialDimension.getHeight() ;

                PointDouble pointInfDroitac = facade.getSommetsMur().get(6);
                PointDouble pointInfGaucheac = facade.getSommetsMur().get(7);

                int x1ac = (int) ((mousePoint.x - (facade.getSommetsMur().get(5).getX() + offsetX)) * zoomFactor + offsetX);
                // La x ou le gars a clicker - (le coins gauches + offset) = le x du click dans le mur
                // Le x du mur est zoomer
                //On y ajoute le offset
                int y1ac = (int) (((facade.getSommetsMur().get(5).getY() + offsetY)  - hauteurPorteInt) * zoomFactor);
                // Le Y en bas a gauche + offset - hauter = le point en haut a gauche de la porte (car la h de la porte
                // est égale à hauteurPorteInt
                //le dit point * zoomFactor pour zoomer

                g.fillRect(x1ac, y1ac, largeurPorteInt, hauteurPorteInt);

//                if (!existeDEJA)
//                {
                    //g.fillRect(mousePoint.x , y1ac, largeurPorteInt, hauteurPorteInt);
//                }
//                if (existeDEJA)
//                {g.fillRect(x1ac , y1ac, largeurPorteInt, hauteurPorteInt);}
                boolean existeDEJA = true;
            }
        }
    }

    private void drawFacade(Graphics g)
    {
        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        PointDouble pointSupDroitfc = facade.getSommetsMur().get(4);
        PointDouble pointSupGauchefc = facade.getSommetsMur().get(5);
        PointDouble pointInfDroitfc = facade.getSommetsMur().get(6);
        PointDouble pointInfGauchefc = facade.getSommetsMur().get(7);

        if(pointInfGauchefc.getX() != 0 || pointSupGauchefc.getX() != 0){
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(epaisseurChalet/2, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(epaisseurChalet/2, 0);

            DroiteRainureInfGauche = new PointDouble(pointInfDroitfc.getX(), 0);
            DroiteRainureSupGauche = new PointDouble(pointSupDroitfc.getX(), hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(longueurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(longueurChalet, 0);
        }else{
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(0, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(0, 0);

            DroiteRainureInfGauche = new PointDouble(longueurChalet, 0);
            DroiteRainureSupGauche = new PointDouble(longueurChalet, hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(longueurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(longueurChalet, 0);
        }

        int x1fc = (int) (pointInfGauchefc.getX() * zoomFactor + offsetX);
        int y1fc = (int) (pointInfGauchefc.getY() * zoomFactor + offsetY);

        int x2fc = (int) (pointInfDroitfc.getX() * zoomFactor + offsetX);
        int y2fc = (int) (pointInfDroitfc.getY() * zoomFactor + offsetY);

        int x3fc = (int) (pointSupGauchefc.getX() * zoomFactor + offsetX);
        int y3fc = (int) (pointSupGauchefc.getY() * zoomFactor + offsetY);

        int x4fc = (int) (pointSupDroitfc.getX() * zoomFactor + offsetX);
        int y4fc = (int) (pointSupDroitfc.getY() * zoomFactor + offsetY);

        int RainureGaucheInfGaucheX = (int) (GaucheRainureInfGauche.getX() * zoomFactor + offsetX);
        int RainureGaucheInfGaucheY = (int) (GaucheRainureInfGauche.getY() * zoomFactor + offsetY);

        int RainureGaucheSupGaucheX = (int) (GaucheRainureSupGauche.getX() * zoomFactor + offsetX);
        int RainureGaucheSupGaucheY = (int) (GaucheRainureSupGauche.getY() * zoomFactor + offsetY);

        int RainureGaucheSupDroitX = (int) (GaucheRainureSupDroit.getX() * zoomFactor + offsetX);
        int RainureGaucheSupDroitY = (int) (GaucheRainureSupDroit.getY() * zoomFactor + offsetY);

        int RainureGaucheInfDroitX = (int) (GaucheRainureInfDroit.getX() * zoomFactor + offsetX);
        int RainureGaucheInfDroitY = (int) (GaucheRainureInfDroit.getY() * zoomFactor + offsetY);

        int RainureDroiteInfGaucheX = (int) (DroiteRainureInfGauche.getX() * zoomFactor + offsetX);
        int RainureDroiteInfGaucheY = (int) (DroiteRainureInfGauche.getY() * zoomFactor + offsetY);

        int RainureDroiteSupGaucheX = (int) (DroiteRainureSupGauche.getX() * zoomFactor + offsetX);
        int RainureDroiteSupGaucheY = (int) (DroiteRainureSupGauche.getY() * zoomFactor + offsetY);

        int RainureDroiteSupDroitX = (int) (DroiteRainureSupDroit.getX() * zoomFactor + offsetX);
        int RainureDroiteSupDroitY = (int) (DroiteRainureSupDroit.getY() * zoomFactor + offsetY);

        int RainureDroiteInfDroitX = (int) (DroiteRainureInfDroit.getX() * zoomFactor + offsetX);
        int RainureDroiteInfDroitY = (int) (DroiteRainureInfDroit.getY() * zoomFactor + offsetY);


        int[] xPointsRainureGauche = {RainureGaucheInfGaucheX, RainureGaucheSupGaucheX, RainureGaucheSupDroitX, RainureGaucheInfDroitX};
        int[] yPointsRainureGauche = {RainureGaucheInfGaucheY, RainureGaucheSupGaucheY, RainureGaucheSupDroitY, RainureGaucheInfDroitY};
        g.setColor(new Color(96, 96, 238));
        g.fillPolygon(xPointsRainureGauche, yPointsRainureGauche, 4);

        int[] xPointsRainureDroite = {RainureDroiteInfGaucheX, RainureDroiteSupGaucheX, RainureDroiteSupDroitX, RainureDroiteInfDroitX};
        int[] yPointsRainureDroite = {RainureDroiteInfGaucheY, RainureDroiteSupGaucheY, RainureDroiteSupDroitY, RainureDroiteInfDroitY};
        g.setColor(new Color(239, 167, 139));
        g.fillPolygon(xPointsRainureDroite, yPointsRainureDroite, 4);

        int[] xPointsFacadeCote = {x1fc, x2fc, x3fc, x4fc};
        int[] yPointsFacadeCote = {y1fc, y2fc, y3fc, y4fc};
        g.setColor(new Color(225, 118, 118));
        g.fillPolygon(xPointsFacadeCote, yPointsFacadeCote, 4);
    }
}
