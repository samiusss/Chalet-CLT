package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;

public class DroitDrawer {

    private Controleur controleur;

    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;
    public Mur droite ; // mur facade deja codé en bas

    public DroitDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;

        Chalet chalet = controleur.getChaletProduction();
        this.droite = controleur.droite; // mur facade deja codé en bas
    }

    public void draw(Graphics g)
    {
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

                // Appeler dimensions Fenetre
                Pouces largeurFenetre = Fenetre.FENETRE_LARGEUR_STANDARD;
                Pouces hauteurFenetre = Fenetre.FENETRE_HAUTEUR_STANDARD;
                //if bouton.listener add.fenetre activated:
                Point mousePoint = fenetre.mousePoint;

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

                Pouces largeurPorte = Porte.PORTE_LARGEUR_STANDARD;
                Pouces hauteurPorte = Porte.PORTE_HAUTEUR_STANDARD;

                Point mousePoint = porte.mousePoint;

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

            }
        }


    }

    private void drawDroit(Graphics g)
    {
        //Chalet chalet = controleur.getChaletProduction();
        g.setColor(new Color(1, 1, 166));

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
        PointDouble pointSupDroitdc = droite.getSommetsMur().get(4);
        PointDouble pointSupGauchedc = droite.getSommetsMur().get(5);
        PointDouble pointInfDroitdc = droite.getSommetsMur().get(6);
        PointDouble pointInfGauchedc = droite.getSommetsMur().get(7);

        // Centrer au milieu du drawingPanel
        double positionX = width/2 - pointInfDroitdc.getX()/2;
        double positionY = height/2 - pointInfDroitdc.getY()/2;

        int x1dc = (int) (pointInfGauchedc.getX() + positionX);
        int zebii = (int) (pointInfGauchedc.getX());
        int y1dc = (int) (pointInfGauchedc.getY() + positionY);

        int x2dc = (int) (pointInfDroitdc.getX() + positionX);
        int y2dc = (int) (pointInfDroitdc.getY() + positionY);

        int x3dc = (int) (pointSupGauchedc.getX() + positionX);
        int y3dc = (int) (pointSupGauchedc.getY() + positionY);

        int x4dc = (int) (pointSupDroitdc.getX() + positionX);
        int y4dc = (int) (pointSupDroitdc.getY() + positionY);


// Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsDroiteCote = {x1dc, x2dc, x3dc, x4dc};
        int[] yPointsDroiteCote = {y1dc, y2dc, y3dc, y4dc};

// Dessiner le polygone pour le mur facade de coté (dc)

        g.fillPolygon(xPointsDroiteCote, yPointsDroiteCote, 4);

    }
}
