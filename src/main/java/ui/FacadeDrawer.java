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


    public FacadeDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
        Chalet chalet = controleur.getChaletProduction();
        this.facade = chaletdto.facade;
        this.zoomFactor = controleur.getZoom();
    }

    public void draw(Graphics g)
    {
        drawFacade(g);
        drawPorte(g);
        drawFenetre(g);
        drawToitFacade(g);
    }


    private void drawFenetre(Graphics g) {

        g.setColor(new Color(1, 1, 0));
        this.zoomFactor = controleur.getZoom();
        List<Fenetre> listeFenetre = facade.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {
            Fenetre fenetreActuel = fenetre;
            if (fenetreActuel != null) {
                Point mousePoint = fenetre.mousePoint;

                Pouces largeur = fenetre.getLargeur();
                Pouces hauteur = fenetre.getHauteur();

                int x1ac = (int) (((mousePoint.x ) * zoomFactor ) ) ;
                int y1ac = (int) (((mousePoint.y ) * zoomFactor ) ) ;

                int largeurFenetreInt = (int)(convertirPoucesEnInt(largeur) * zoomFactor);
                int hauteurFenetreInt = (int)(convertirPoucesEnInt(hauteur) * zoomFactor);

                g.fillRect(x1ac, y1ac, largeurFenetreInt, hauteurFenetreInt);
            }
        }


    }



    private void drawPorte(Graphics g) {

        g.setColor(new Color(1, 1, 0));
        this.zoomFactor = controleur.getZoom();

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


                PointDouble pointInfDroitac = facade.getSommetsMur().get(6);
                PointDouble pointInfGaucheac = facade.getSommetsMur().get(7);


                int xPorte = (int) (((mousePoint.x) * zoomFactor) ) ;

                double positionY = 0 ;
                int yPorte = (int) (((((pointInfGaucheac.getY() * zoomFactor + positionY))  - hauteurPorteInt  + hauteurMurs* zoomFactor) ) );

                g.fillRect(xPorte , yPorte, largeurPorteInt, hauteurPorteInt);
                porte.setLaportefutdessinee(true);
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

        double positionX = 0;
        double positionY = 0;

        int x1fc = (int) (pointInfGauchefc.getX() * zoomFactor + positionX);
        int y1fc = (int) (pointInfGauchefc.getY() * zoomFactor + positionY);

        int x2fc = (int) (pointInfDroitfc.getX() * zoomFactor + positionX);
        int y2fc = (int) (pointInfDroitfc.getY() * zoomFactor + positionY);

        int x3fc = (int) (pointSupGauchefc.getX() * zoomFactor + positionX);
        int y3fc = (int) (pointSupGauchefc.getY() * zoomFactor + positionY);

        int x4fc = (int) (pointSupDroitfc.getX() * zoomFactor + positionX);
        int y4fc = (int) (pointSupDroitfc.getY() * zoomFactor + positionY);

        int RainureGaucheInfGaucheX = (int) (GaucheRainureInfGauche.getX() * zoomFactor + positionX);
        int RainureGaucheInfGaucheY = (int) (GaucheRainureInfGauche.getY() * zoomFactor + positionY);
        int RainureGaucheSupGaucheX = (int) (GaucheRainureSupGauche.getX() * zoomFactor + positionX);
        int RainureGaucheSupGaucheY = (int) (GaucheRainureSupGauche.getY() * zoomFactor + positionY);
        int RainureGaucheSupDroitX = (int) (GaucheRainureSupDroit.getX() * zoomFactor + positionX);
        int RainureGaucheSupDroitY = (int) (GaucheRainureSupDroit.getY() * zoomFactor + positionY);
        int RainureGaucheInfDroitX = (int) (GaucheRainureInfDroit.getX() * zoomFactor + positionX);
        int RainureGaucheInfDroitY = (int) (GaucheRainureInfDroit.getY() * zoomFactor + positionY);
        int RainureDroiteInfGaucheX = (int) (DroiteRainureInfGauche.getX() * zoomFactor + positionX);
        int RainureDroiteInfGaucheY = (int) (DroiteRainureInfGauche.getY() * zoomFactor + positionY);
        int RainureDroiteSupGaucheX = (int) (DroiteRainureSupGauche.getX() * zoomFactor + positionX);
        int RainureDroiteSupGaucheY = (int) (DroiteRainureSupGauche.getY() * zoomFactor + positionY);
        int RainureDroiteSupDroitX = (int) (DroiteRainureSupDroit.getX() * zoomFactor + positionX);
        int RainureDroiteSupDroitY = (int) (DroiteRainureSupDroit.getY() * zoomFactor + positionY);
        int RainureDroiteInfDroitX = (int) (DroiteRainureInfDroit.getX() * zoomFactor + positionX);
        int RainureDroiteInfDroitY = (int) (DroiteRainureInfDroit.getY() * zoomFactor + positionY);


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
    private void drawToitFacade(Graphics g)
    {
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
            System.out.println("Le drawer détecte l'orientation "+ orientationToit +" dans le mur de facade...");

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

            g.setColor(new Color(0, 255, 0));
            g.fillPolygon(xPointsToit, yPointsToit, 5);
        }
    }

    }
