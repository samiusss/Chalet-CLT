package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import domain.*;

import java.awt.*;
import java.util.List;
import java.util.Objects;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnInt;
import static domain.Chalet.*;
import static java.lang.Math.tan;

public class GaucheDrawer
{
    private Controleur controleur;
    public ChaletDTO chaletdto;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;
    private double zoomFactor;

    public Mur gauche ; // mur arriere deja codé en bas
    public Toit pignongauche;
    public PointDouble DroiteRainureInfGauche, DroiteRainureSupGauche, DroiteRainureInfDroit, DroiteRainureSupDroit;
    public PointDouble GaucheRainureInfGauche, GaucheRainureSupGauche, GaucheRainureInfDroit, GaucheRainureSupDroit;

    public GaucheDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;

        Chalet chalet = controleur.getChaletProduction();
        this.gauche = chaletdto.gauche; // mur facade deja codé en bas
        this.zoomFactor = controleur.getZoom();
    }

    public void draw(Graphics g)
    {
        drawGauche(g);
        drawPorte(g);
        drawFenetre(g);
        drawToitGauche(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {

        g.setColor(Color.lightGray);

        double grilleP = Chalet.grilleP*zoomFactor;

        // Lignes verticales
        for (int x = -500; x < 1500; x += grilleP) {
            g.drawLine(x, 1500, x, -1500);
        }
        // Lignes horizontales
        for (int y = -500; y < 1500; y += grilleP) {
            g.drawLine(1500, y, -1500, y);
        }
    }


    private void drawFenetre(Graphics g) {


        System.out.println("fenetreGAUCHE");

        g.setColor(new Color(204, 0, 102));

        List<Fenetre> listeFenetre = gauche.getListeFenetre();
        int lenghtlisteFenetre = listeFenetre.size();
        System.out.println(lenghtlisteFenetre);


        for (Fenetre fenetre : listeFenetre) {
            System.out.println("fenetreGAUCHE2");

            System.out.println(fenetre);

            Fenetre fenetreActuel = (Fenetre) fenetre;
            if (fenetreActuel != null) {
                Point mousePoint = fenetre.mousePoint;

                Pouces largeur = fenetre.getLargeur();
                Pouces hauteur = fenetre.getHauteur();

                int x1ac = (int) (((mousePoint.x  ) * zoomFactor ) ) ;
                int y1ac = (int) (((mousePoint.y  ) * zoomFactor ) ) ;

                int largeurFenetreInt = (int)(convertirPoucesEnInt(largeur) * zoomFactor);
                int hauteurFenetreInt = (int)(convertirPoucesEnInt(hauteur) * zoomFactor);

                g.fillRect(x1ac, y1ac, largeurFenetreInt, hauteurFenetreInt);
            }
        }


    }

    private void drawPorte(Graphics g) {


        System.out.println("porteDROITE");

        g.setColor(new Color(255, 255, 0));
        List<Porte> listePorte = gauche.getListePorte();
        int lenghtlistePorte = listePorte.size();
        System.out.println(lenghtlistePorte);

        for (Porte porte : listePorte) {

            Point mousePoint = porte.mousePoint;

            Pouces largeur = porte.getLargeur();
            Pouces hauteur = porte.getHauteur();

            int largeurPorteInt = (int) (convertirPoucesEnInt(largeur) * zoomFactor);
            int hauteurPorteInt = (int) (convertirPoucesEnInt(hauteur) * zoomFactor);
            PointDouble pointInfGaucheac = gauche.getSommetsMur().get(7);

            int xPorte = (int) (((mousePoint.x) * zoomFactor) ) ;

            double positionY = 0 ;
            int yPorte = (int) (((((pointInfGaucheac.getY() * zoomFactor + positionY))  - hauteurPorteInt  + hauteurMurs* zoomFactor) ) );

            g.fillRect(xPorte , yPorte, largeurPorteInt, hauteurPorteInt);
            porte.setLaportefutdessinee(true);
        }


    }


    private void drawGauche(Graphics g)
    {
        g.setColor(new Color(166, 1, 1));

        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        PointDouble pointSupDroitgc = gauche.getSommetsMur().get(4);
        PointDouble pointSupGauchegc = gauche.getSommetsMur().get(5);
        PointDouble pointInfDroitgc = gauche.getSommetsMur().get(6);
        PointDouble pointInfGauchegc = gauche.getSommetsMur().get(7);

        if(pointSupGauchegc.getX() != 0 || pointInfGauchegc.getX() != 0)
        {
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(epaisseurChalet/2, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(epaisseurChalet/2, 0);

            DroiteRainureInfGauche = new PointDouble(pointInfDroitgc.getX(), 0);
            DroiteRainureSupGauche = new PointDouble(pointSupDroitgc.getX(), hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(largeurChalet, 0);
        } else{
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(0, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(0, 0);

            DroiteRainureInfGauche = new PointDouble(largeurChalet, 0);
            DroiteRainureSupGauche = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(largeurChalet, 0);
        }

        int positionX = 0;
        int positionY = 0;

        int x1gc = (int) (pointInfGauchegc.getX()* zoomFactor) + positionX;
        int y1gc = (int) (pointInfGauchegc.getY()* zoomFactor) + positionY;

        int x2gc = (int) (pointInfDroitgc.getX()* zoomFactor)+ positionX;
        int y2gc = (int) (pointInfDroitgc.getY()* zoomFactor) + positionY;

        int x3gc = (int) (pointSupGauchegc.getX()* zoomFactor)+ positionX;
        int y3gc = (int) (pointSupGauchegc.getY()* zoomFactor)+ positionY;

        int x4gc = (int) (pointSupDroitgc.getX()* zoomFactor)+ positionX;
        int y4gc = (int) (pointSupDroitgc.getY()* zoomFactor)+ positionY;

        int intx1RainureGauche = (int) (GaucheRainureInfGauche.getX()* zoomFactor)+ positionX;
        int inty1RainureGauche = (int) (GaucheRainureInfGauche.getY()* zoomFactor)+ positionY;

        int intx2RainureGauche = (int) (GaucheRainureSupGauche.getX()* zoomFactor)+ positionX;
        int inty2RainureGauche = (int) (GaucheRainureSupGauche.getY()* zoomFactor)+ positionY;

        int intx3RainureGauche = (int) (GaucheRainureSupDroit.getX()* zoomFactor)+ positionX;
        int inty3RainureGauche = (int) (GaucheRainureSupDroit.getY()* zoomFactor)+ positionY;

        int intx4RainureGauche = (int) (GaucheRainureInfDroit.getX()* zoomFactor)+ positionX;
        int inty4RainureGauche = (int) (GaucheRainureInfDroit.getY()* zoomFactor)+ positionY;

        int intx1RainureDroite = (int) (DroiteRainureInfGauche.getX()* zoomFactor)+ positionX;
        int inty1RainureDroite = (int) (DroiteRainureInfGauche.getY()* zoomFactor)+ positionY;

        int intx2RainureDroite = (int) (DroiteRainureSupGauche.getX()* zoomFactor)+ positionX;
        int inty2RainureDroite = (int) (DroiteRainureSupGauche.getY()* zoomFactor)+ positionY;

        int intx3RainureDroite = (int) (DroiteRainureSupDroit.getX()* zoomFactor)+ positionX;
        int inty3RainureDroite = (int) (DroiteRainureSupDroit.getY()* zoomFactor)+ positionY;

        int intx4RainureDroite = (int) (DroiteRainureInfDroit.getX()* zoomFactor)+ positionX;
        int inty4RainureDroite = (int) (DroiteRainureInfDroit.getY()* zoomFactor)+ positionY ;

        int[] xPointsGaucheRainure = {intx1RainureGauche, intx2RainureGauche, intx3RainureGauche, intx4RainureGauche};
        int[] yPointsGaucheRainure = {inty1RainureGauche, inty2RainureGauche, inty3RainureGauche, inty4RainureGauche};
        g.setColor(new Color(104, 190, 83));
        g.fillPolygon(xPointsGaucheRainure, yPointsGaucheRainure, 4);


        int[] xPointsDroiteRainure = {intx1RainureDroite, intx2RainureDroite, intx3RainureDroite, intx4RainureDroite};
        int[] yPointsDroiteRainure = {inty1RainureDroite, inty2RainureDroite, inty3RainureDroite, inty4RainureDroite};
        g.setColor(new Color(225, 118, 118));
        g.fillPolygon(xPointsDroiteRainure, yPointsDroiteRainure, 4);

        int[] xPointsGauche = {x1gc, x2gc, x3gc, x4gc};
        int[] yPointsGauche = {y1gc, y2gc, y3gc, y4gc};
        g.setColor(new Color(239, 167, 139));
        g.fillPolygon(xPointsGauche, yPointsGauche, 4);

        //pignon gauche



    }
    private void drawToitGauche(Graphics g)
    {
        hauteurPignon= Chalet.largeurChalet * tan((Chalet.angleToit)* (Math.PI / 180)); //largeurChalet est la largeur du pignon
        hauteurRallonge = hauteurPignon + epaisseurChalet/2 * tan((Chalet.angleToit)* (Math.PI / 180));

        if (Objects.equals(orientationToit, "Sud"))
        {
            double positionX = 0;
            double positionY = 0;

            ////PIGNONGAUCHE////


            //Points de pignon à gauche de la pente
            PointDouble pointSupGauchePignon = new PointDouble(epaisseurChalet/2, (0-hauteurPignon));
            PointDouble pointInfGauchePignon = new PointDouble(epaisseurChalet/2, 0);
            PointDouble pointInfDroitePignon = new PointDouble((largeurChalet-epaisseurChalet/2), 0);


            int pointSupGauchePignonX = (int) (pointSupGauchePignon.getX()* zoomFactor+positionX);
            int pointSupGauchePignonY = (int) (pointSupGauchePignon.getY()* zoomFactor+positionY);

            int pointInfGauchePignonX = (int) (pointInfGauchePignon.getX()* zoomFactor+positionX);
            int pointInfGauchePignonY = (int) (pointInfGauchePignon.getY()* zoomFactor+positionY);

            int pointInfDroitePignonX = (int) (pointInfDroitePignon.getX()* zoomFactor+positionX);
            int pointInfDroitePignonY = (int) (pointInfDroitePignon.getY()* zoomFactor+positionY);

            int[] xPointsPignon = {pointSupGauchePignonX, pointInfGauchePignonX, pointInfDroitePignonX};
            int[] yPointsPignon = {pointSupGauchePignonY, pointInfGauchePignonY, pointInfDroitePignonY};

            g.setColor(new Color(2, 125, 0));
            g.fillPolygon(xPointsPignon, yPointsPignon, 3);
            System.out.println("Le drawer détecte l'orientation "+ orientationToit +" dans le mur de facade...");

            ///RALLONGE///


            PointDouble pointSupGaucheRallonge = new PointDouble(0, (0-hauteurRallonge));
            PointDouble pointInfGaucheRallonge = new PointDouble(0, 0);
            PointDouble pointInfDroiteRallonge = new PointDouble(epaisseurChalet/2, 0);
            PointDouble pointSupDroiteRallonge = new PointDouble(epaisseurChalet/2, (0-hauteurPignon));



            int pointSupGaucheRallongeX = (int) (pointSupGaucheRallonge.getX()*zoomFactor+positionX);
            int pointSupGaucheRallongeY = (int) (pointSupGaucheRallonge.getY()*zoomFactor+positionY);

            int pointInfGaucheRallongeX = (int) (pointInfGaucheRallonge.getX()*zoomFactor+positionX);
            int pointInfGaucheRallongeY = (int) (pointInfGaucheRallonge.getY()*zoomFactor+positionY);

            int pointInfDroiteRallongeX = (int) (pointInfDroiteRallonge.getX()*zoomFactor+positionX);
            int pointInfDroiteRallongeY = (int) (pointInfDroiteRallonge.getY()*zoomFactor+positionY);

            int pointSupDroiteRallongeX = (int) (pointSupDroiteRallonge.getX()*zoomFactor+positionX);
            int pointSupDroiteRallongeY = (int) (pointSupDroiteRallonge.getY()*zoomFactor+positionY);

            int[] xPointsRallonge = {pointSupGaucheRallongeX, pointInfGaucheRallongeX, pointInfDroiteRallongeX, pointSupDroiteRallongeX};
            int[] yPointsRallonge = {pointSupGaucheRallongeY, pointInfGaucheRallongeY, pointInfDroiteRallongeY, pointSupDroiteRallongeY};

            g.setColor(new Color(200, 200, 200));
            g.fillPolygon(xPointsRallonge, yPointsRallonge, 4);

            /// TOIT ///
            PointDouble pointSupGaucheToit = new PointDouble(0, (0-hauteurRallonge-epaisseurChalet/2));
            PointDouble pointInfGaucheToit = new PointDouble(0, (0-hauteurRallonge));
            PointDouble pointInfDroiteProcheToit = new PointDouble((largeurChalet-epaisseurChalet/2), 0);
            PointDouble pointInfDroiteLoinToit = new PointDouble((largeurChalet), 0);
            PointDouble pointSupDroiteToit = new PointDouble((largeurChalet), -epaisseurChalet/2);

            int pointSupGaucheToitX = (int) (pointSupGaucheToit.getX()*zoomFactor+positionX);
            int pointSupGaucheToitY = (int) (pointSupGaucheToit.getY()*zoomFactor+positionY);

            int pointInfGaucheToitX = (int) (pointInfGaucheToit.getX()*zoomFactor+positionX);
            int pointInfGaucheToitY = (int) (pointInfGaucheToit.getY()*zoomFactor+positionY);

            int pointInfDroiteProcheToitX = (int) (pointInfDroiteProcheToit.getX()*zoomFactor+positionX);
            int pointInfDroiteProcheToitY = (int) (pointInfDroiteProcheToit.getY()*zoomFactor+positionY);

            int pointInfDroiteLoinToitX = (int) (pointInfDroiteLoinToit.getX()*zoomFactor+positionX);
            int pointInfDroiteLoinToitY = (int) (pointInfDroiteLoinToit.getY()*zoomFactor+positionY);

            int pointSupDroiteToitX = (int) (pointSupDroiteToit.getX()*zoomFactor+positionX);
            int pointSupDroiteToitY = (int) (pointSupDroiteToit.getY()*zoomFactor+positionY);

            int[] xPointsToit = {pointSupGaucheToitX, pointInfGaucheToitX, pointInfDroiteProcheToitX, pointInfDroiteLoinToitX, pointSupDroiteToitX};
            int[] yPointsToit = {pointSupGaucheToitY, pointInfGaucheToitY, pointInfDroiteProcheToitY, pointInfDroiteLoinToitY, pointSupDroiteToitY};

            g.setColor(new Color(0, 0, 50));
            g.fillPolygon(xPointsToit, yPointsToit, 5);
        }
        if (Objects.equals(orientationToit, "Nord"))
        {
            double positionX = 0;
            double positionY = 0;

            ////PIGNONGAUCHE////


            //Points de pignon à gauche de la pente
            PointDouble pointSupDroitePignon = new PointDouble((largeurChalet-epaisseurChalet/2), (0-hauteurPignon));
            PointDouble pointInfGauchePignon = new PointDouble(epaisseurChalet/2, 0);
            PointDouble pointInfDroitePignon = new PointDouble((largeurChalet-epaisseurChalet/2), 0);


            int pointSupDroitePignonX = (int) (pointSupDroitePignon.getX()* zoomFactor+positionX);
            int pointSupDroitePignonY = (int) (pointSupDroitePignon.getY()* zoomFactor+positionY);

            int pointInfGauchePignonX = (int) (pointInfGauchePignon.getX()* zoomFactor+positionX);
            int pointInfGauchePignonY = (int) (pointInfGauchePignon.getY()* zoomFactor+positionY);

            int pointInfDroitePignonX = (int) (pointInfDroitePignon.getX()* zoomFactor+positionX);
            int pointInfDroitePignonY = (int) (pointInfDroitePignon.getY()* zoomFactor+positionY);

            int[] xPointsPignon = {pointSupDroitePignonX, pointInfDroitePignonX, pointInfGauchePignonX};
            int[] yPointsPignon = {pointSupDroitePignonY, pointInfDroitePignonY, pointInfGauchePignonY};

            g.setColor(new Color(2, 125, 0));
            g.fillPolygon(xPointsPignon, yPointsPignon, 3);
            System.out.println("Le drawer détecte l'orientation "+ orientationToit +" dans le mur de facade...");

            ///RALLONGE///


            PointDouble pointSupGaucheRallonge = new PointDouble(largeurChalet-epaisseurChalet/2, (0-hauteurPignon));
            PointDouble pointInfGaucheRallonge = new PointDouble(largeurChalet-epaisseurChalet/2, 0);
            PointDouble pointInfDroiteRallonge = new PointDouble(largeurChalet, 0);
            PointDouble pointSupDroiteRallonge = new PointDouble(largeurChalet, (0-hauteurRallonge));



            int pointSupGaucheRallongeX = (int) (pointSupGaucheRallonge.getX()*zoomFactor+positionX);
            int pointSupGaucheRallongeY = (int) (pointSupGaucheRallonge.getY()*zoomFactor+positionY);

            int pointInfGaucheRallongeX = (int) (pointInfGaucheRallonge.getX()*zoomFactor+positionX);
            int pointInfGaucheRallongeY = (int) (pointInfGaucheRallonge.getY()*zoomFactor+positionY);

            int pointInfDroiteRallongeX = (int) (pointInfDroiteRallonge.getX()*zoomFactor+positionX);
            int pointInfDroiteRallongeY = (int) (pointInfDroiteRallonge.getY()*zoomFactor+positionY);

            int pointSupDroiteRallongeX = (int) (pointSupDroiteRallonge.getX()*zoomFactor+positionX);
            int pointSupDroiteRallongeY = (int) (pointSupDroiteRallonge.getY()*zoomFactor+positionY);

            int[] xPointsRallonge = {pointSupGaucheRallongeX, pointInfGaucheRallongeX, pointInfDroiteRallongeX, pointSupDroiteRallongeX};
            int[] yPointsRallonge = {pointSupGaucheRallongeY, pointInfGaucheRallongeY, pointInfDroiteRallongeY, pointSupDroiteRallongeY};

            g.setColor(new Color(200, 200, 200));
            g.fillPolygon(xPointsRallonge, yPointsRallonge, 4);

            /// TOIT ///
            PointDouble pointSupGaucheToit = new PointDouble(largeurChalet, (0-hauteurRallonge-epaisseurChalet/2));
            PointDouble pointInfGaucheToit = new PointDouble(largeurChalet, (0-hauteurRallonge));
            PointDouble pointInfDroiteProcheToit = new PointDouble((0+epaisseurChalet/2), 0);
            PointDouble pointInfDroiteLoinToit = new PointDouble((0), 0);
            PointDouble pointSupDroiteToit = new PointDouble((0), -epaisseurChalet/2);

            int pointSupGaucheToitX = (int) (pointSupGaucheToit.getX()*zoomFactor+positionX);
            int pointSupGaucheToitY = (int) (pointSupGaucheToit.getY()*zoomFactor+positionY);

            int pointInfGaucheToitX = (int) (pointInfGaucheToit.getX()*zoomFactor+positionX);
            int pointInfGaucheToitY = (int) (pointInfGaucheToit.getY()*zoomFactor+positionY);

            int pointInfDroiteProcheToitX = (int) (pointInfDroiteProcheToit.getX()*zoomFactor+positionX);
            int pointInfDroiteProcheToitY = (int) (pointInfDroiteProcheToit.getY()*zoomFactor+positionY);

            int pointInfDroiteLoinToitX = (int) (pointInfDroiteLoinToit.getX()*zoomFactor+positionX);
            int pointInfDroiteLoinToitY = (int) (pointInfDroiteLoinToit.getY()*zoomFactor+positionY);

            int pointSupDroiteToitX = (int) (pointSupDroiteToit.getX()*zoomFactor+positionX);
            int pointSupDroiteToitY = (int) (pointSupDroiteToit.getY()*zoomFactor+positionY);

            int[] xPointsToit = {pointSupGaucheToitX, pointInfGaucheToitX, pointInfDroiteProcheToitX, pointInfDroiteLoinToitX, pointSupDroiteToitX};
            int[] yPointsToit = {pointSupGaucheToitY, pointInfGaucheToitY, pointInfDroiteProcheToitY, pointInfDroiteLoinToitY, pointSupDroiteToitY};

            g.setColor(new Color(0, 0, 50));
            g.fillPolygon(xPointsToit, yPointsToit, 5);
            System.out.println("Le drawer détecte l'orientation "+ orientationToit +" dans le mur de facade...");

        }
        if (Objects.equals(orientationToit, "Ouest"))
        {
            double positionX = 0;
            double positionY = 0;

            //// TOIT CAR FACADE ORIENTÉ NORD DONNE LA PENTE (OU LE TOIT) ////

            PointDouble pSupGaucheToit = new PointDouble(0, (0-hauteurPignon-epaisseurChalet/2));
            PointDouble pInfGaucheToit = new PointDouble(0, 0);
            PointDouble pInfDroiteLoinToit = new PointDouble((largeurChalet), 0);
            PointDouble pSupDroiteToit = new PointDouble((largeurChalet), (0-hauteurPignon-epaisseurChalet/2));

            int pSupGaucheToitX = (int) (pSupGaucheToit.getX()*zoomFactor+positionX);
            int pSupGaucheToitY = (int) (pSupGaucheToit.getY()*zoomFactor+positionY);

            int pInfGaucheToitX = (int) (pInfGaucheToit.getX()*zoomFactor+positionX);
            int pInfGaucheToitY = (int) (pInfGaucheToit.getY()*zoomFactor+positionY);

            int pInfDroiteLoinToitX = (int) (pInfDroiteLoinToit.getX()*zoomFactor+positionX);
            int pInfDroiteLoinToitY = (int) (pInfDroiteLoinToit.getY()*zoomFactor+positionY);

            int pSupDroiteToitX = (int) (pSupDroiteToit.getX()*zoomFactor+positionX);
            int pSupDroiteToitY = (int) (pSupDroiteToit.getY()*zoomFactor+positionY);

            int[] xPointsToit = {pSupGaucheToitX, pInfGaucheToitX , pInfDroiteLoinToitX, pSupDroiteToitX};
            int[] yPointsToit = {pSupGaucheToitY, pInfGaucheToitY , pInfDroiteLoinToitY, pSupDroiteToitY};

            g.setColor(new Color(0, 0, 50));
            g.fillPolygon(xPointsToit, yPointsToit, 4);
            System.out.println("Je détecte l'orientation "+ orientationToit +" dans le mur de facade...");


        }
        if (Objects.equals(orientationToit, "Est"))
        {
            double positionX = 0;
            double positionY = 0;

            //// TOIT CAR FACADE ORIENTÉ SUD DONNE LE DOS DU TOIT (AVEC RALLONGE) ////

            PointDouble pSupGaucheToit = new PointDouble(0, (0-hauteurRallonge-epaisseurChalet/2));
            PointDouble pInfGaucheToit = new PointDouble(0, -hauteurPignon);
            PointDouble pInfDroiteLoinToit = new PointDouble((largeurChalet), -hauteurPignon);
            PointDouble pSupDroiteToit = new PointDouble((largeurChalet), (0-hauteurRallonge-epaisseurChalet/2));

            int pSupGaucheToitX = (int) (pSupGaucheToit.getX()*zoomFactor+positionX);
            int pSupGaucheToitY = (int) (pSupGaucheToit.getY()*zoomFactor+positionY);

            int pInfGaucheToitX = (int) (pInfGaucheToit.getX()*zoomFactor+positionX);
            int pInfGaucheToitY = (int) (pInfGaucheToit.getY()*zoomFactor+positionY);

            int pInfDroiteLoinToitX = (int) (pInfDroiteLoinToit.getX()*zoomFactor+positionX);
            int pInfDroiteLoinToitY = (int) (pInfDroiteLoinToit.getY()*zoomFactor+positionY);

            int pSupDroiteToitX = (int) (pSupDroiteToit.getX()*zoomFactor+positionX);
            int pSupDroiteToitY = (int) (pSupDroiteToit.getY()*zoomFactor+positionY);

            int[] xPointsToit = {pSupGaucheToitX, pInfGaucheToitX , pInfDroiteLoinToitX, pSupDroiteToitX};
            int[] yPointsToit = {pSupGaucheToitY, pInfGaucheToitY , pInfDroiteLoinToitY, pSupDroiteToitY};

            g.setColor(new Color(0, 0, 50));
            g.fillPolygon(xPointsToit, yPointsToit, 4);
            System.out.println("Je détecte l'orientation "+ orientationToit +" dans le mur de facade...");

            ///RALLONGE DE DOS///


            PointDouble pointSupGaucheRallonge = new PointDouble(0, (0-hauteurRallonge));
            PointDouble pointInfGaucheRallonge = new PointDouble(0, 0);
            PointDouble pointInfDroiteRallonge = new PointDouble(largeurChalet, 0);
            PointDouble pointSupDroiteRallonge = new PointDouble(largeurChalet, 0-hauteurRallonge);


            int pointSupGaucheRallongeX = (int) (pointSupGaucheRallonge.getX()*zoomFactor+positionX);
            int pointSupGaucheRallongeY = (int) (pointSupGaucheRallonge.getY()*zoomFactor+positionY);

            int pointInfGaucheRallongeX = (int) (pointInfGaucheRallonge.getX()*zoomFactor+positionX);
            int pointInfGaucheRallongeY = (int) (pointInfGaucheRallonge.getY()*zoomFactor+positionY);

            int pointInfDroiteRallongeX = (int) (pointInfDroiteRallonge.getX()*zoomFactor+positionX);
            int pointInfDroiteRallongeY = (int) (pointInfDroiteRallonge.getY()*zoomFactor+positionY);

            int pointSupDroiteRallongeX = (int) (pointSupDroiteRallonge.getX()*zoomFactor+positionX);
            int pointSupDroiteRallongeY = (int) (pointSupDroiteRallonge.getY()*zoomFactor+positionY);

            int[] xPointsRallonge = {pointSupGaucheRallongeX, pointInfGaucheRallongeX, pointInfDroiteRallongeX, pointSupDroiteRallongeX};
            int[] yPointsRallonge = {pointSupGaucheRallongeY, pointInfGaucheRallongeY, pointInfDroiteRallongeY, pointSupDroiteRallongeY};

            g.setColor(new Color(200, 200, 200));
            g.fillPolygon(xPointsRallonge, yPointsRallonge, 4);


        }
    }
}
