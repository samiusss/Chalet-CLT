package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnInt;
import static domain.Chalet.*;

public class DroitDrawer {

    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;
    public Mur droite; // mur facade deja codé en bas
    public PointDouble GaucheRainureInfGauche, GaucheRainureSupGauche, GaucheRainureSupDroit, GaucheRainureInfDroit;
    public PointDouble DroiteRainureInfGauche, DroiteRainureSupGauche, DroiteRainureSupDroit, DroiteRainureInfDroit;

    public DroitDrawer(Controleur controleur, Dimension initialDimension) {
        this.controleur = controleur;
        this.initialDimension = initialDimension;

        Chalet chalet = controleur.getChaletProduction();
        this.droite = controleur.droite; // mur facade deja codé en bas
    }

    public void draw(Graphics g) {
        drawDroit(g);
        drawPorte(g);
        drawFenetre(g);
    }


    private void drawFenetre(Graphics g) {
        System.out.println("fenetreDROIT");

        g.setColor(new Color(255, 0, 0));

        List<Fenetre> listeFenetre = droite.getListeFenetre();
        int lenghtlisteFenetre = listeFenetre.size();
        System.out.println(lenghtlisteFenetre);


        for (Fenetre fenetre : listeFenetre) {
            System.out.println("fenetreDROIT2");

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


        System.out.println("porteDROITE");

        g.setColor(new Color(255, 255, 0));

        List<Porte> listePorte = droite.getListePorte();
        int lenghtlistePorte = listePorte.size();
        System.out.println(lenghtlistePorte);


        for (Porte porte : listePorte) {
            System.out.println("porteDROITE2");

            System.out.println(porte);

            Porte porteActuel = (Porte) porte;
            if (porteActuel != null) {


                Point mousePoint = porte.mousePoint;


                Pouces largeur = porte.getLargeur();
                Pouces hauteur = porte.getHauteur();

                int largeurPorteInt = convertirPoucesEnInt(largeur);
                int hauteurPorteInt = convertirPoucesEnInt(hauteur);

                double height = initialDimension.getHeight();
                PointDouble pointInfDroitac = droite.getSommetsMur().get(6);
                PointDouble pointInfGaucheac = droite.getSommetsMur().get(7);
                double positionY = height / 2 - pointInfDroitac.getY() / 2;
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


    private void drawDroit(Graphics g) {
        //Chalet chalet = controleur.getChaletProduction();


        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();
/*
        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs

        //Dimensions du mur en 3D
        double epaisseurMur = Chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = Chalet.hauteurMurs;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = Chalet.largeurChalet;     // Largeur des murs venant de chalet
        double longueurMur = Chalet.longueurChalet;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
        */
        //chalet.initialiserMurDroite();
        //Mur droite = chalet.getMursUsines(0.2, "Nord").get(0); // mur droite deja codé en bas

        // Accéder coord de Mur droite de face (dc)


        //ceux-ci sont les points reels du mur de droite
        PointDouble pointSupDroitdc = droite.getSommetsMur().get(4); //E ou W = (10, 8) & N ou S = (8.5, 10)
        PointDouble pointSupGauchedc = droite.getSommetsMur().get(5);//E ou W = (0, 8) & N ou S = (1.5, 8)
        PointDouble pointInfDroitdc = droite.getSommetsMur().get(6);//E ou W = (10, 0) & N ou S = (8.5, 0)
        PointDouble pointInfGauchedc = droite.getSommetsMur().get(7);//E ou W = (0, 0) & N ou S = (1.5, 0)

        if(pointSupGauchedc.getX() != 0 || pointInfGauchedc.getX() != 0){
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(epaisseurChalet/2, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(epaisseurChalet/2, 0);

            DroiteRainureInfGauche = new PointDouble(pointInfDroitdc.getX(), 0);
            DroiteRainureSupGauche = new PointDouble(pointInfDroitdc.getX(), hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(largeurChalet, 0);
        }else{
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(0, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(0, 0);

            DroiteRainureInfGauche = new PointDouble(largeurChalet, 0);
            DroiteRainureSupGauche = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(largeurChalet, 0);
        }


        /*if(chalet.getOrientationToit().equals("Nord")|| chalet.getOrientationToit().equals("Sud")){
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(epaisseurChalet/2, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(epaisseurChalet/2, 0);

            DroiteRainureInfGauche = new PointDouble(largeurChalet - epaisseurChalet/2, 0);
            DroiteRainureSupGauche = new PointDouble(largeurChalet - epaisseurChalet/2, hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(largeurChalet, 0);
        }
        else if(chalet.getOrientationToit().equals("Est") || chalet.getOrientationToit().equals("Ouest")){
            GaucheRainureInfGauche = new PointDouble(0, 0);
            GaucheRainureSupGauche = new PointDouble(0, hauteurMurs);
            GaucheRainureSupDroit = new PointDouble(0, hauteurMurs);
            GaucheRainureInfDroit = new PointDouble(0, 0);

            DroiteRainureInfGauche = new PointDouble(largeurChalet, 0);
            DroiteRainureSupGauche = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureSupDroit = new PointDouble(largeurChalet, hauteurMurs);
            DroiteRainureInfDroit = new PointDouble(largeurChalet, 0);
        }*/


        // Centrer au milieu du drawingPanel
        double positionX = width / 2 - pointInfDroitdc.getX() / 2;
        double positionY = height / 2 - pointInfDroitdc.getY() / 2;

        int x1dc = (int) (pointInfGauchedc.getX() + positionX);
        int y1dc = (int) (pointInfGauchedc.getY() + positionY);

        int x2dc = (int) (pointInfDroitdc.getX() + positionX);
        int y2dc = (int) (pointInfDroitdc.getY() + positionY);

        int x3dc = (int) (pointSupGauchedc.getX() + positionX);
        int y3dc = (int) (pointSupGauchedc.getY() + positionY);

        int x4dc = (int) (pointSupDroitdc.getX() + positionX);
        int y4dc = (int) (pointSupDroitdc.getY() + positionY);

        int x1RainureGauche = (int) (GaucheRainureInfGauche.getX() + positionX);
        int y1RainureGauche = (int) (GaucheRainureInfGauche.getY() + positionY);

        int x2RainureGauche = (int) (GaucheRainureSupGauche.getX() + positionX);
        int y2RainureGauche = (int) (GaucheRainureSupGauche.getY() + positionY);

        int x3RainureGauche = (int) (GaucheRainureSupDroit.getX() + positionX);
        int y3RainureGauche = (int) (GaucheRainureSupDroit.getY() + positionY);

        int x4RainureGauche = (int) (GaucheRainureInfDroit.getX() + positionX);
        int y4RainureGauche = (int) (GaucheRainureInfDroit.getY() + positionY);

        int x1RainureDroite = (int) (DroiteRainureInfGauche.getX() + positionX);
        int y1RainureDroite = (int) (DroiteRainureInfGauche.getY() + positionY);

        int x2RainureDroite = (int) (DroiteRainureSupGauche.getX() + positionX);
        int y2RainureDroite = (int) (DroiteRainureSupGauche.getY() + positionY);

        int x3RainureDroite = (int) (DroiteRainureSupDroit.getX() + positionX);
        int y3RainureDroite = (int) (DroiteRainureSupDroit.getY() + positionY);

        int x4RainureDroite = (int) (DroiteRainureInfDroit.getX() + positionX);
        int y4RainureDroite = (int) (DroiteRainureInfDroit.getY() + positionY);


// Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsRainureGauche = {x1RainureGauche, x2RainureGauche, x3RainureGauche, x4RainureGauche};
        int[] yPointsRainureGauche = {y1RainureGauche, y2RainureGauche, y3RainureGauche, y4RainureGauche};
        g.setColor(new Color(225, 118, 118));
        g.fillPolygon(xPointsRainureGauche, yPointsRainureGauche, 4);

        int[] xPointsRainureDroite = {x1RainureDroite, x2RainureDroite, x3RainureDroite, x4RainureDroite};
        int[] yPointsRainureDroite = {y1RainureDroite, y2RainureDroite, y3RainureDroite, y4RainureDroite};
        g.setColor(new Color(104, 190, 83));
        g.fillPolygon(xPointsRainureDroite, yPointsRainureDroite, 4);

        int[] xPointsDroiteCote = {x1dc, x2dc, x3dc, x4dc};
        int[] yPointsDroiteCote = {y1dc, y2dc, y3dc, y4dc};
        g.setColor(new Color(96, 96, 238));
        g.fillPolygon(xPointsDroiteCote, yPointsDroiteCote, 4);

    }
}
