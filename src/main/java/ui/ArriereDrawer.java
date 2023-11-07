package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.*;

public class ArriereDrawer {
    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;
    public Mur arriere ; // mur arriere deja codé en bas

    public ArriereDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;

        Chalet chalet = controleur.getChaletProduction();
        this.arriere = controleur.arriere; // mur facade deja codé en bas
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
        System.out.println(lenghtlisteFenetre);


        for (Fenetre fenetre : listeFenetre) {
            System.out.println("porteARRIERE2");

            System.out.println(fenetre);

            Fenetre fenetreActuel = (Fenetre) fenetre;
            if (fenetreActuel != null) {

                // Appeler dimensions Fenetre
                //Pouces largeurFenetre = Fenetre.FENETRE_LARGEUR_STANDARD;
                //Pouces hauteurFenetre = Fenetre.FENETRE_HAUTEUR_STANDARD;
                //if bouton.listener add.fenetre activated:
                Point mousePoint = fenetre.mousePoint;


                Pouces largeur = fenetre.getLargeur();

                Pouces hauteur = fenetre.getHauteur();



                int largeurFenetreInt = convertirPoucesEnInt(largeur);
                int hauteurFenetreInt = convertirPoucesEnInt(hauteur);

                g.fillRect(mousePoint.x, mousePoint.y, largeurFenetreInt, hauteurFenetreInt);


                /*

                pointPouces xyPouces = fenetre.getPointPouces(mousePoint);
                Pouces xPouces = xyPouces.getX();
                Pouces yPouces = xyPouces.getY();

                Pouces largeurParDeux = largeur.diviserPouces(2);
                Pouces hauteurParDeux = hauteur.diviserPouces(2);


                pointPouces pointFenetreSupDroit = new pointPouces(xPouces.addPouces(largeurParDeux),yPouces.addPouces(hauteurParDeux));
                pointPouces pointFenetreSupGauche=new pointPouces(xPouces.substractPouces(largeurParDeux),yPouces.addPouces(hauteurParDeux));
                pointPouces pointFenetreInfGauche = new pointPouces(xPouces.substractPouces(largeurParDeux),yPouces.substractPouces(hauteurParDeux));
                pointPouces pointFenetreInfDroit = new pointPouces(xPouces.addPouces(largeurParDeux),yPouces.substractPouces(hauteurParDeux));


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

                g.fillPolygon(xPoints, yPoints, 4); */

            }
        }


    }


    private void drawPorte(Graphics g) {


        System.out.println("porteARRIRERE");

        g.setColor(new Color(204, 255, 204));

        List<Porte> listePorte = arriere.getListePorte();
        int lenghtlistePorte = listePorte.size();
        System.out.println(lenghtlistePorte);


        for (Porte porte : listePorte) {
            System.out.println("porteARRIRERE2");

            System.out.println(porte);

            Porte porteActuel = (Porte) porte;
            if (porteActuel != null) {

                /* Pouces largeurPorte = Porte.PORTE_LARGEUR_STANDARD;
                Pouces hauteurPorte = Porte.PORTE_HAUTEUR_STANDARD;

                 */

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


    private void drawArriere(Graphics g)
    {
        g.setColor(new Color(1, 166, 1));

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
        //chalet.initialiserMurArriere();

        // Accéder coord de Mur arriere de face (ac)
        /*PointDouble pointSupDroitac = arriere.getSommetsMur().get(4);
        PointDouble pointSupGaucheac = arriere.getSommetsMur().get(5);
        PointDouble pointInfDroitac = arriere.getSommetsMur().get(6);
        PointDouble pointInfGaucheac = arriere.getSommetsMur().get(7);

        double positionX = width/2 - pointInfDroitac.getX()/2;
        double positionY = height/2 - pointInfDroitac.getY()/2;

        int x1ac = (int) (pointInfGaucheac.getX() + positionX);
        int y1ac = (int) (pointInfGaucheac.getY() + positionY);

        int x2ac = (int) (pointInfDroitac.getX() + positionX);
        int y2ac = (int) (pointInfDroitac.getY() + positionY);

        int x3ac = (int) (pointSupGaucheac.getX() + positionX);
        int y3ac = (int) (pointSupGaucheac.getY() + positionY);

        int x4ac = (int) (pointSupDroitac.getX() + positionX);
        int y4ac = (int) (pointSupDroitac.getY() + positionY);


        // Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsArriereCote = {x1ac, x2ac, x3ac, x4ac};
        int[] yPointsArriereCote = {y1ac, y2ac, y3ac, y4ac};

        // Dessiner le polygone pour le mur facade de coté (fc)
        g.fillPolygon(xPointsArriereCote, yPointsArriereCote, 4);
*/
        PointDouble point1 = arriere.getSommetsMur().get(4);
        PointDouble point2 = arriere.getSommetsMur().get(5);
        PointDouble point3 = arriere.getSommetsMur().get(6);
        PointDouble point4 = arriere.getSommetsMur().get(7);

        double positionX = width/2 - point3.getX()/2;
        double positionY = height/2 - point3.getY()/2;

        int x1 = (int) (point1.getX() + positionX);
        int y1 = (int) (point1.getY() + positionY);

        int x2 = (int) (point2.getX() + positionX);
        int y2 = (int) (point2.getY() + positionY);

        int x3 = (int) (point3.getX() + positionX);
        int y3 = (int) (point3.getY() + positionY);

        int x4 = (int) (point3.getX() + positionX);
        int y4 = (int) (point1.getY() + positionY);

        int[] xPoints = {x1, x2, x3, x4};
        int[] yPoints = {y1, y2, y3, y4};

        g.fillPolygon(xPoints, yPoints, 4);

    }
}
