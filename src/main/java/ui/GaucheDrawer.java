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

public class GaucheDrawer
{
    private Controleur controleur;
    public ChaletDTO chaletdto;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;
    private double zoomFactor;

    public Mur gauche ; // mur arriere deja codé en bas

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

                //if bouton.listener add.fenetre activated:
                Point mousePoint = fenetre.mousePoint;



                Pouces largeur = fenetre.getLargeur();

                Pouces hauteur = fenetre.getHauteur();



                int largeurFenetreInt = convertirPoucesEnInt(largeur);
                int hauteurFenetreInt = convertirPoucesEnInt(hauteur);

                g.fillRect(mousePoint.x, mousePoint.y, largeurFenetreInt, hauteurFenetreInt);



                /*

                pointPouces pointFenetreSupDroit = new pointPouces(fenetre.getPointPouces(mousePoint).getX().addPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().addPouces(fenetre.getHauteur().diviserPouces(2)));
                pointPouces pointFenetreSupGauche=new pointPouces(fenetre.getPointPouces(mousePoint).getX().substractPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().addPouces(fenetre.getHauteur().diviserPouces(2)));
                pointPouces pointFenetreInfGauche = new pointPouces(fenetre.getPointPouces(mousePoint).getX().substractPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().substractPouces(fenetre.getHauteur().diviserPouces(2)));
                pointPouces pointFenetreInfDroit = new pointPouces(fenetre.getPointPouces(mousePoint).getX().addPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().substractPouces(fenetre.getHauteur().diviserPouces(2)));


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

                 */

            }
        }


    }

    private void drawPorte(Graphics g) {


        System.out.println("porteGAUHE");

        g.setColor(new Color(255, 255, 255));

        List<Porte> listePorte = gauche.getListePorte();
        int lenghtlistePorte = listePorte.size();
        System.out.println(lenghtlistePorte);


        for (Porte porte : listePorte) {
            System.out.println("porteGAUCHE2");

            System.out.println(porte);

            Porte porteActuel = (Porte) porte;
            if (porteActuel != null) {


                Point mousePoint = porte.mousePoint;


                Pouces largeur = porte.getLargeur();
                Pouces hauteur = porte.getHauteur();

                int largeurPorteInt = convertirPoucesEnInt(largeur);
                int hauteurPorteInt = convertirPoucesEnInt(hauteur);

                double height = initialDimension.getHeight();
                PointDouble pointInfDroitac = gauche.getSommetsMur().get(6);
                PointDouble pointInfGaucheac = gauche.getSommetsMur().get(7);
                double positionY = height/2 - pointInfDroitac.getY()/2;
                int y1ac = (int) (((pointInfGaucheac.getY() + positionY) - hauteurPorteInt) + hauteurMurs);


                g.fillRect(mousePoint.x, y1ac, largeurPorteInt, hauteurPorteInt);

                /*

                pointPouces pointPorteSupDroit = new pointPouces(porte.getPointPouces(mousePoint).getX().addPouces(porte.getLargeur().diviserPouces(2)), porte.getPointPouces(mousePoint).getY().addPouces(porte.getHauteur().diviserPouces(2)));
                pointPouces pointPorteSupGauche = new pointPouces(porte.getPointPouces(mousePoint).getX().substractPouces(porte.getLargeur().diviserPouces(2)), porte.getPointPouces(mousePoint).getY().addPouces(porte.getHauteur().diviserPouces(2)));
                pointPouces pointPorteInfGauche = new pointPouces(porte.getPointPouces(mousePoint).getX().substractPouces(porte.getLargeur().diviserPouces(2)), new Pouces(0, 0, 1));
                pointPouces pointPorteInfDroit = new pointPouces(porte.getPointPouces(mousePoint).getX().addPouces(porte.getLargeur().diviserPouces(2)), new Pouces(0, 0, 1));


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

                 */

            }
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

        int positionX = (int) (width/2 - pointInfDroitgc.getX()/2);
        int positionY = (int) (height/2- pointInfDroitgc.getY()/2);

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
    }

}
