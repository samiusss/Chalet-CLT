package ui;

import Utilitaires.ConvertisseurMesures;
import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.ArrayList;


public class Chaletdrawer {
    private Controleur controleur;
    private Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;

    public Chaletdrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g)
    {
        drawFenetre(g);
        drawChalet(g);
    }

    private void drawFenetre(Graphics g)
    {
        ArrayList<pointPouces> sommetsFenetre = new ArrayList<>() ;
        g.setColor(new Color(1, 100, 166));

        // Appeler dimensions Fenetre
        Pouces largeurFenetre = Fenetre.FENETRE_LARGEUR_STANDARD;
        Pouces hauteurFenetre = Fenetre.FENETRE_HAUTEUR_STANDARD;

        //Fenetre.CreersommetFenetre(); Rafiou donne moi des nouvelles, cela ne marche pas

        double x_fenetre = ConvertisseurMesures.convertirPoucesEnPixels(largeurFenetre);
        double y_fenetre = ConvertisseurMesures.convertirPoucesEnPixels(hauteurFenetre);

        // largeur et les hauteurs, il les transforme en sommets

        // Coordonnée
     /* double PointSupDroitFenetre = ;
        double PointSupGaucheFenetre = ;
        double PointInfGaucheFenetre = ;
        double PointInfDroitFenetre = */

    }


    private void drawChalet(Graphics g) {
        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs
        g.setColor(new Color(166, 66, 66));

        //Dimensions du mur en 3D
        double epaisseurMur = 2*chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = 2*chalet.hauteurMurs; // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 2*chalet.largeurChalet; // Largeur des murs venant de chalet
        double longueurMur = 2*chalet.longueurChalet;
        double angleToit = 0.0;

        //////////////////////////////////////////////////
         /// Vue par dessus, if controleur.vue.listener==dessus////
          /////////////////////////////////////////////////

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
        chalet.initialiserMurFacade();
        chalet.initialiserMurArriere();


        // Accéder aux coordonnées de Mur: Facade
        Mur facade = chalet.getMursUsines().get(0); // mur facade deja codé en bas

        PointDouble pointSupDroitf = facade.getSommetsMur().get(2); // Je veux le troisième sommet (index 2) // pointSupDroitf = pointSupDroit facade
        PointDouble pointSupGauchef = facade.getSommetsMur().get(1); // Je veux le deuxieme sommet (index 1) // pointSupGauchef = pointSupGauche facade
        PointDouble pointInfDroitf = facade.getSommetsMur().get(3); // Je veux le quatrième sommet (index 3) // pointInfDroitf = pointInfDroit facade
        PointDouble pointInfGauchef = facade.getSommetsMur().get(0); // Je veux le premier sommet (index 0) // pointInfGauchef = pointInfGauche facade
        PointDouble rainureGauche1 = facade.getSommetsMur().get(8); // Je veux le neuvième sommet (index 8) // rainureGauche1 = rainureGauche1 facade
        PointDouble rainureGauche2 = facade.getSommetsMur().get(9); // Je veux le dixième sommet (index 9) // rainureGauche2 = rainureGauche2 facade
        PointDouble rainureDroite1 = facade.getSommetsMur().get(10); // Je veux le onzième sommet (index 10) // rainureDroite1 = rainureDroite1 facade
        PointDouble rainureDroite2 = facade.getSommetsMur().get(11); // Je veux le douzième sommet (index 11) // rainureDroite2 = rainureDroite2 facade

        // Accéder coord de Mur facade de coté (fc)
        PointDouble pointSupDroitfc = facade.getSommetsMur().get(4);
        PointDouble pointSupGauchefc = facade.getSommetsMur().get(5);
        PointDouble pointInfDroitfc = facade.getSommetsMur().get(6);
        PointDouble pointInfGauchefc = facade.getSommetsMur().get(7);

        // Centrer au milieu du drawingPanel
        double positionX = width/2;
        double positionY = height/2;

        int x1 = (int) (pointInfDroitf.getX()+positionX);
        int y1 = (int) (pointInfDroitf.getY()+positionY);
        int x1r1 = (int) (rainureDroite1.getX()+positionX);
        int y1r1 = (int) (rainureDroite1.getY()+positionY);
        int x1r2 = (int) (rainureDroite2.getX()+positionX);
        int y1r2 = (int) (rainureDroite2.getY()+positionY);
        //
        int x2 = (int) (pointSupDroitf.getX()+positionX);
        int y2 = (int) (pointSupDroitf.getY()+positionY);
        //
        int x3 = (int) (pointSupGauchef.getX()+positionX);
        int y3 = (int) (pointSupGauchef.getY()+positionY);
        int x3r1 = (int) (rainureGauche1.getX()+positionX);
        int y3r1 = (int) (rainureGauche1.getY()+positionY);
        int x3r2 = (int) (rainureGauche2.getX()+positionX);
        int y3r2 = (int) (rainureGauche2.getY()+positionY);
        //
        int x4 = (int) (pointInfGauchef.getX()+positionX);
        int y4 = (int) (pointInfGauchef.getY()+positionY);
        //
        int[] xPoints = {x1, x1r2, x1r1, x2, x3, x3r2, x3r1, x4};
        int[] yPoints = {y1, y1r2, y1r1, y2, y3, y3r2, y3r1, y4};

        g.fillPolygon(xPoints, yPoints, 8);

    // Accéder aux coordonnées de Mur: Arrière
        Mur arriere = chalet.getListeMurs().get(1);

    // Prendre les sommets du mur arrière
        PointDouble pointSupDroita = arriere.getSommetsMur().get(2);
        PointDouble pointSupGauchea = arriere.getSommetsMur().get(1);
        PointDouble pointInfDroita = arriere.getSommetsMur().get(3);
        PointDouble pointInfGauchea = arriere.getSommetsMur().get(0);
        PointDouble rainureGauche1a = arriere.getSommetsMur().get(8);
        PointDouble rainureGauche2a = arriere.getSommetsMur().get(9);
        PointDouble rainureDroite1a = arriere.getSommetsMur().get(10);
        PointDouble rainureDroite2a = arriere.getSommetsMur().get(11);
        PointDouble pointSupDroitac = arriere.getSommetsMur().get(6);
        PointDouble pointSupGaucheac = arriere.getSommetsMur().get(5);
        PointDouble pointInfDroitac = arriere.getSommetsMur().get(7);
        PointDouble pointInfGaucheac = arriere.getSommetsMur().get(4);

    // Convertir les coordonnées en entiers
        int x1a = (int) (pointInfDroita.getX() + positionX);
        int y1a = (int) (pointInfDroita.getY() + positionX);
        int x1r1a = (int) (rainureDroite1a.getX() + positionX);
        int y1r1a = (int) (rainureDroite1a.getY() + positionX);
        int x1r2a = (int) (rainureDroite2a.getX() + positionX);
        int y1r2a = (int) (rainureDroite2a.getY() + positionX);

        int x2a = (int) (pointSupDroita.getX() + positionX);
        int y2a = (int) (pointSupDroita.getY() + positionX);

        int x3a = (int) (pointSupGauchea.getX() + positionX);
        int y3a = (int) (pointSupGauchea.getY() + positionX);
        int x3r1a = (int) (rainureGauche1a.getX() + positionX);
        int y3r1a = (int) (rainureGauche1a.getY() + positionX);
        int x3r2a = (int) (rainureGauche2a.getX() + positionX);
        int y3r2a = (int) (rainureGauche2a.getY() + positionX);

        int x4a = (int) (pointInfGauchea.getX() + positionX);
        int y4a = (int) (pointInfGauchea.getY() + positionX);

        // Construire tableaux de coordonnées pour le mur arrière
        int[] xPointsArriere = {x1a, x1r2a, x1r1a, x2a, x3a, x3r2a, x3r1a, x4a};
        int[] yPointsArriere = {y1a, y1r2a, y1r1a, y2a, y3a, y3r2a, y3r1a, y4a};
        g.setColor(new Color(66, 66, 166));

        // Dessiner le polygone pour le mur arrière
        g.fillPolygon(xPointsArriere, yPointsArriere, 8);

        chalet.initialiserMurDroite();
        chalet.initialiserMurGauche();

    ///////////////////////////////////////////////////////
    //////////Vue de coté facade, if controle.vue.listener == facade;
            /////////////////////////////////////////

        int x1fc = (int) pointInfGauchefc.getX();
        int y1fc = (int) pointInfGauchefc.getY();

        int x2fc = (int) pointInfDroitfc.getX();
        int y2fc = (int) pointInfDroitfc.getY();

        int x3fc = (int) pointSupGauchefc.getX();
        int y3fc = (int) pointSupGauchefc.getY();

        int x4fc = (int) pointSupDroitfc.getX();
        int y4fc = (int) pointSupDroitfc.getY();

        // Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsFacadeCote = {x1fc, x2fc, x3fc, x4fc};
        int[] yPointsFacadeCote = {y1fc, y2fc, y3fc, y4fc};

        // Dessiner le polygone pour le mur facade de coté (fc)
        g.fillPolygon(xPointsFacadeCote, yPointsFacadeCote, 4);

        ///////////////////////////////////////////////////////
        //////////Vue de coté arrière, if controle.vue.listener == arriere;
        ////////////////////////////////
        int x2ac = (int) pointInfGaucheac.getX();
        int y2ac = (int) pointInfGaucheac.getY();

        int x1ac = (int) pointInfDroitac.getX();
        int y1ac = (int) pointInfDroitac.getY();

        int x3ac = (int) pointSupGaucheac.getX();
        int y3ac = (int) pointSupGaucheac.getY();

        int x4ac = (int) pointSupDroitac.getX();
        int y4ac = (int) pointSupDroitac.getY();

        // Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsArriereCote = {x1ac, x2ac, x3ac, x4ac};
        int[] yPointsArriereCote = {y1ac, y2ac, y3ac, y4ac};

        // Dessiner le polygone pour le mur facade de coté (fc)
        g.fillPolygon(xPointsArriereCote, yPointsArriereCote, 4);

        // Dessiner le mur de façade en profondeur, pour ressemble à celui de l'énoncé, laisser en commentaires
        /*double pointDeVue3D = 120; // Hauteur de la perspective
        g.setColor(new Color(1, 1, 150));
        int[] xPointsP = {
                (int) facadeX,
                (int) (facadeX + 40),
                (int) (facadeX + largeurMur - 40),
                (int) (facadeX + largeurMur)
        };
        int[] yPointsP = {
                (int) facadeY,
                (int) (facadeY - pointDeVue3D),
                (int) (facadeY - pointDeVue3D),
                (int) facadeY
        };
        g.fillPolygon(xPointsP, yPointsP, 4);*/
    }
}
