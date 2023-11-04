package ui;

import Utilitaires.PointDouble;
import domain.Accessoires;
import domain.Chalet;
import domain.Controleur;
import domain.Mur;

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
        drawAccessoire(g);
        drawChalet(g);
    }

    private void drawAccessoire(Graphics g)
    {        // Prototype

        //List accessoires = (List) Mur.getAccessoiresMur(); // Remplacez Accessoire par le type d'objet approprié
        /*for (Accessoires: accessoires) {
            Point accessoirePoint = Point.getPoint();
            int width = accessoires.getWidth(); // Largeur de la fenêtre ou de la porte
            int height = accessoires.getHeight(); // Hauteur de la fenêtre ou de la porte

            if (accessoires.isSelected) {
                int offsetWidth = width + 1;
                int offsetHeight = height + 1;
                g.fillRect((int) accessoirePoint.getX() - offsetWidth / 2, (int) accessoirePoint.getY() - offsetHeight / 2, offsetWidth, offsetHeight);
            }

            g.setColor(new Color(166, 66, 66));
            g.fillRect((int) accessoirePoint.getX() - width / 2, (int) accessoirePoint.getY() - height / 2, width, height);
        }*/
    }

    private void drawChalet(Graphics g) {
        //double width = initialDimension.getWidth();
        //double height = initialDimension.getHeight();

        //Il faut que le controleur ou Chalet choisit les points, pas Main, puis j'appelle le point en bas
        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs
        g.setColor(new Color(166, 66, 66));

        //Dimensions du mur en 3D
        double epaisseurMur = 2*Chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = 2*Chalet.hauteurMurs; // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 2*Chalet.largeurChalet; // Largeur des murs venant de chalet
        double longueurMur = 2*Chalet.longueurChalet;
        double angleToit = 0.0;


 //////////////////////////////////////////////////
   /// Vue par dessus, if controleur.vue==dessus////
     //////////////////////////////////////////////////

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
        chalet.initialiserMurFacade();
        //chalet.initialiserMurDroite();
        //chalet.initialiserMurGauche();
        chalet.initialiserMurArriere();

        // Accéder aux coordonnées de Mur: Facade
        Mur facade = chalet.getMursUsines().get(0); // mure facade deja codé en bas

        PointDouble pointSupDroitf = facade.getSommetsMur().get(2); // Je veux le troisième sommet (index 2)
        PointDouble pointSupGauchef = facade.getSommetsMur().get(1); // Je veux le deuxieme sommet (index 1)
        PointDouble pointInfDroitf = facade.getSommetsMur().get(3); // Je veux le quatrième sommet (index 3)
        PointDouble pointInfGauchef = facade.getSommetsMur().get(0); // Je veux le premier sommet (index 0)
        PointDouble rainureGauche1 = facade.getSommetsMur().get(8);
        PointDouble rainureGauche2 = facade.getSommetsMur().get(9);
        PointDouble rainureDroite1 = facade.getSommetsMur().get(10);
        PointDouble rainureDroite2 = facade.getSommetsMur().get(11);

        double positionZero = 400;
        int x1 = (int) (pointInfDroitf.getX()+positionZero);
        int y1 = (int) (pointInfDroitf.getY()+positionZero);
        int x1r1 = (int) (rainureDroite1.getX()+positionZero);
        int y1r1 = (int) (rainureDroite1.getY()+positionZero);
        int x1r2 = (int) (rainureDroite2.getX()+positionZero);
        int y1r2 = (int) (rainureDroite2.getY()+positionZero);
        //
        int x2 = (int) (pointSupDroitf.getX()+positionZero);
        int y2 = (int) (pointSupDroitf.getY()+positionZero);
        //
        int x3 = (int) (pointSupGauchef.getX()+positionZero);
        int y3 = (int) (pointSupGauchef.getY()+positionZero);
        int x3r1 = (int) (rainureGauche1.getX()+positionZero);
        int y3r1 = (int) (rainureGauche1.getY()+positionZero);
        int x3r2 = (int) (rainureGauche2.getX()+positionZero);
        int y3r2 = (int) (rainureGauche2.getY()+positionZero);
        //
        int x4 = (int) (pointInfGauchef.getX()+positionZero);
        int y4 = (int) (pointInfGauchef.getY()+positionZero);
        //
        int[] xPoints = {x1, x1r2, x1r1, x2, x3, x3r2, x3r1, x4};
        int[] yPoints = {y1, y1r2, y1r1, y2, y3, y3r2, y3r1, y4};

        g.drawPolygon(xPoints, yPoints, 8);

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

    // Ajouter un décalage pour séparer le mur
        double positionZeroArriere = 420; // Utilisez la position appropriée

    // Convertissez les coordonnées en entiers
        int x1a = (int) (pointInfDroita.getX() + positionZero);
        int y1a = (int) (pointInfDroita.getY() + positionZeroArriere);
        int x1r1a = (int) (rainureDroite1a.getX() + positionZero);
        int y1r1a = (int) (rainureDroite1a.getY() + positionZeroArriere);
        int x1r2a = (int) (rainureDroite2a.getX() + positionZero);
        int y1r2a = (int) (rainureDroite2a.getY() + positionZeroArriere);

        int x2a = (int) (pointSupDroita.getX() + positionZero);
        int y2a = (int) (pointSupDroita.getY() + positionZeroArriere);

        int x3a = (int) (pointSupGauchea.getX() + positionZero);
        int y3a = (int) (pointSupGauchea.getY() + positionZeroArriere);
        int x3r1a = (int) (rainureGauche1a.getX() + positionZero);
        int y3r1a = (int) (rainureGauche1a.getY() + positionZeroArriere);
        int x3r2a = (int) (rainureGauche2a.getX() + positionZero);
        int y3r2a = (int) (rainureGauche2a.getY() + positionZeroArriere);

        int x4a = (int) (pointInfGauchea.getX() + positionZero);
        int y4a = (int) (pointInfGauchea.getY() + positionZeroArriere);

    // Construire tableaux de coordonnées pour le mur arrière
        int[] xPointsArriere = {x1a, x1r2a, x1r1a, x2a, x3a, x3r2a, x3r1a, x4a};
        int[] yPointsArriere = {y1a, y1r2a, y1r1a, y2a, y3a, y3r2a, y3r1a, y4a};

    // Dessiner le polygone pour le mur arrière
        g.drawPolygon(xPointsArriere, yPointsArriere, 8);



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
