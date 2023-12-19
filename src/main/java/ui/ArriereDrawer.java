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

public class ArriereDrawer implements java.io.Serializable {
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
        drawToitArriere(g);
        if(grilleActive){      drawGrid(g);}

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

                int x1ac = (int) (((mousePoint.x  ) * zoomFactor ) ) ;
                int y1ac = (int) (((mousePoint.y  ) * zoomFactor ) ) ;

                int largeurFenetreInt = (int)(convertirPoucesEnInt(largeur) * zoomFactor);
                int hauteurFenetreInt = (int)(convertirPoucesEnInt(hauteur) * zoomFactor);

                g.fillRect(x1ac, y1ac, largeurFenetreInt, hauteurFenetreInt);
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

                int largeurPorteInt = (int) (convertirPoucesEnInt(largeur) * zoomFactor);
                int hauteurPorteInt = (int) (convertirPoucesEnInt(hauteur) * zoomFactor);

                PointDouble pointInfDroitac = arriere.getSommetsMur().get(6);
                PointDouble pointInfGaucheac = arriere.getSommetsMur().get(7);

                int xPorte = (int) (((mousePoint.x) * zoomFactor) ) ;

                double positionY = 0 ;
                int yPorte = (int) (((((pointInfGaucheac.getY() * zoomFactor + positionY))  - hauteurPorteInt  + hauteurMurs* zoomFactor) ) );

                g.fillRect(xPorte , yPorte, largeurPorteInt, hauteurPorteInt);
                porte.setLaportefutdessinee(true);
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

        int positionX = 0;
        int positionY = 0;

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
    private void drawToitArriere(Graphics g)
    {
        hauteurPignon= Chalet.longueurChalet * tan((Chalet.angleToit)* (Math.PI / 180)); //largeurChalet est la largeur du pignon
        hauteurRallonge = hauteurPignon + epaisseurChalet/2 * tan((Chalet.angleToit)* (Math.PI / 180));

        if (Objects.equals(orientationToit, "Est"))
        {
            double positionX = 0;
            double positionY = 0;

            ////PIGNONGAUCHE////

            hauteurPignon= Chalet.longueurChalet * tan((Chalet.angleToit)* (Math.PI / 180)); //largeurChalet est la largeur du pignon

            //Points de pignon à gauche de la pente
            PointDouble pointSupGauchePignon = new PointDouble(epaisseurChalet/2, (0-hauteurPignon));
            PointDouble pointInfGauchePignon = new PointDouble(epaisseurChalet/2, 0);
            PointDouble pointInfDroitePignon = new PointDouble((longueurChalet-epaisseurChalet/2), 0);


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

            ///RALLONGE///

            hauteurRallonge = hauteurPignon + epaisseurChalet/2 * tan((Chalet.angleToit)* (Math.PI / 180));

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
            PointDouble pointInfDroiteProcheToit = new PointDouble((longueurChalet-epaisseurChalet/2), 0);
            PointDouble pointInfDroiteLoinToit = new PointDouble((longueurChalet), 0);
            PointDouble pointSupDroiteToit = new PointDouble((longueurChalet), -epaisseurChalet/2);

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
        if (Objects.equals(orientationToit, "Ouest"))
        {
            double positionX = 0;
            double positionY = 0;

            ////PIGNONGAUCHE////

            hauteurPignon= Chalet.longueurChalet * tan((Chalet.angleToit)* (Math.PI / 180)); //largeurChalet est la largeur du pignon

            //Points de pignon à gauche de la pente
            PointDouble pointSupDroitePignon = new PointDouble((longueurChalet-epaisseurChalet/2), (0-hauteurPignon));
            PointDouble pointInfGauchePignon = new PointDouble(epaisseurChalet/2, 0);
            PointDouble pointInfDroitePignon = new PointDouble((longueurChalet-epaisseurChalet/2), 0);


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

            ///RALLONGE///

            hauteurRallonge = hauteurPignon + epaisseurChalet/2 * tan((Chalet.angleToit)* (Math.PI / 180));

            PointDouble pointSupGaucheRallonge = new PointDouble(longueurChalet-epaisseurChalet/2, (0-hauteurPignon));
            PointDouble pointInfGaucheRallonge = new PointDouble(longueurChalet-epaisseurChalet/2, 0);
            PointDouble pointInfDroiteRallonge = new PointDouble(longueurChalet, 0);
            PointDouble pointSupDroiteRallonge = new PointDouble(longueurChalet, (0-hauteurRallonge));



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
            PointDouble pointSupGaucheToit = new PointDouble(longueurChalet, (0-hauteurRallonge-epaisseurChalet/2));
            PointDouble pointInfGaucheToit = new PointDouble(longueurChalet, (0-hauteurRallonge));
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

        }
        if (Objects.equals(orientationToit, "Sud"))
        {
            double positionX = 0;
            double positionY = 0;

            //// TOIT CAR FACADE ORIENTÉ NORD DONNE LA PENTE (OU LE TOIT) ////

            PointDouble pSupGaucheToit = new PointDouble(0, (0-hauteurRallonge-epaisseurChalet/2));
            PointDouble pInfGaucheToit = new PointDouble(0, 0);
            PointDouble pInfDroiteLoinToit = new PointDouble((longueurChalet), 0);
            PointDouble pSupDroiteToit = new PointDouble((longueurChalet), (0-hauteurRallonge-epaisseurChalet/2));

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


        }
        if (Objects.equals(orientationToit, "Nord"))
        {
            double positionX = 0;
            double positionY = 0;

            //// TOIT CAR FACADE ORIENTÉ SUD DONNE LE DOS DU TOIT (AVEC RALLONGE) ////

            PointDouble pSupGaucheToit = new PointDouble(0, (0-hauteurRallonge-epaisseurChalet/2));
            PointDouble pInfGaucheToit = new PointDouble(0, -hauteurPignon);
            PointDouble pInfDroiteLoinToit = new PointDouble((longueurChalet), -hauteurPignon);
            PointDouble pSupDroiteToit = new PointDouble((longueurChalet), (0-hauteurRallonge-epaisseurChalet/2));

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

            ///RALLONGE DE DOS///

            hauteurRallonge = hauteurPignon + epaisseurChalet/2 * tan((Chalet.angleToit)* (Math.PI / 180));

            PointDouble pointSupGaucheRallonge = new PointDouble(0, (0-hauteurRallonge));
            PointDouble pointInfGaucheRallonge = new PointDouble(0, 0);
            PointDouble pointInfDroiteRallonge = new PointDouble(longueurChalet, 0);
            PointDouble pointSupDroiteRallonge = new PointDouble(longueurChalet, 0-hauteurRallonge);


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
