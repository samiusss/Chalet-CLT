package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;

public class FacadeDrawer {

    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;
    public Mur facade ; // mur facade deja codé en bas

    public FacadeDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
        Chalet chalet = controleur.getChaletProduction();
        this.facade = controleur.facade; // mur facade deja codé en bas
    }

    public void draw(Graphics g)
    {
        drawFacade(g);
        drawPorte(g);
        drawFenetre(g);
    }



    private void drawFenetre(Graphics g) {


        System.out.println("fenetreFACADE");

        g.setColor(new Color(1, 1, 0));

        List<Fenetre> listeFenetre = facade.getListeFenetre();
        int lenghtlisteFenetre = listeFenetre.size();
        System.out.println(lenghtlisteFenetre);


        for (Fenetre fenetre : listeFenetre) {
            System.out.println("porteFACADE2");

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


        System.out.println("porteFACADE");

        g.setColor(new Color(1, 1, 0));

        List<Porte> listePorte = facade.getListePorte();
        int lenghtlistePorte = listePorte.size();
        System.out.println(lenghtlistePorte);


        for (Porte porte : listePorte) {
            System.out.println("porteFACADE2");

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

    private void drawFacade(Graphics g)

    {
        //Chalet chalet = controleur.getChaletProduction();

        g.setColor(new Color(110, 166, 166));
        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();
        // Centrer au milieu du drawingPanel
        double positionX = width/2;
        double positionY = height/2;

        //chalet.initialiserMurFacade();

        // Accéder aux coordonnées de Mur: Facade
        //Mur facade = chalet.getMursUsines(0.2, "Nord").get(0); // mur facade deja codé en bas
        // Accéder coord de Mur facade de face (fc)
        PointDouble pointSupDroitfc = facade.getSommetsMur().get(4);
        PointDouble pointSupGauchefc = facade.getSommetsMur().get(5);
        PointDouble pointInfDroitfc = facade.getSommetsMur().get(6);
        PointDouble pointInfGauchefc = facade.getSommetsMur().get(7);

        int x1fc = (int) (pointInfGauchefc.getX() + positionX);
        int y1fc = (int) (pointInfGauchefc.getY() + positionY);

        int x2fc = (int) (pointInfDroitfc.getX() + positionX);
        int y2fc = (int) (pointInfDroitfc.getY() + positionY);

        int x3fc = (int) (pointSupGauchefc.getX() + positionX);
        int y3fc = (int) (pointSupGauchefc.getY() + positionY);

        int x4fc = (int) (pointSupDroitfc.getX() + positionX);
        int y4fc = (int) (pointSupDroitfc.getY() + positionY);


        // Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsFacadeCote = {x1fc, x2fc, x3fc, x4fc};
        int[] yPointsFacadeCote = {y1fc, y2fc, y3fc, y4fc};

        // Dessiner le polygone pour le mur facade de coté (fc)
        g.fillPolygon(xPointsFacadeCote, yPointsFacadeCote, 4);
    }
}
