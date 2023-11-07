package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;


public class Chaletdrawer {
    private Controleur controleur;
    public  Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;

    public Mur facade ; // mur facade deja codé en bas
    public Mur arriere; // mur arriere deja codé en bas
    public Mur gauche ; // mur gauche deja codé en bas
    public Mur droite; // mur droite deja codé en bas
    public static Controleur.AffichageVue VueAccessoire ;

    public Chaletdrawer(Controleur controleur ,Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
        Chalet chalet = controleur.getChaletProduction();
        this.facade = controleur.facade; // mur facade deja codé en bas
        this.arriere = controleur.arriere; // mur facade deja codé en bas
        this.droite = controleur.droite; // mur facade deja codé en bas
        this.gauche = controleur.gauche; // mur facade deja codé en bas


    }

    public static boolean changerVue(Controleur.AffichageVue selectedVue) {
         VueAccessoire = selectedVue;
         return true;
    }

    public void draw(Graphics g)
    {
        drawFenetre(g);
        drawPorte(g);
    }


    private void drawPorte(Graphics g) {


        System.out.println("porteSURPLOMP");

        g.setColor(new Color(1, 1, 0));


        if (VueAccessoire == Controleur.AffichageVue.SURPLOMB) {
            System.out.println("lenghtlistePorte1");


        } else {

            List<Porte>listePorte = facade.getListePorte();

            if (VueAccessoire == Controleur.AffichageVue.FACADE) {
                listePorte = facade.getListePorte();
                System.out.println("lenghtlistePorte");


            }
            if (VueAccessoire == Controleur.AffichageVue.ARRIERE) {
                listePorte = arriere.getListePorte();
                System.out.println("lenghtlistePorte3");

            }
            if (VueAccessoire == Controleur.AffichageVue.DROITE) {
                listePorte = droite.getListePorte();

            }
            if (VueAccessoire == Controleur.AffichageVue.GAUCHE) {
                listePorte = gauche.getListePorte();

            }




            int lenghtlistePorte = listePorte.size();
                System.out.println(lenghtlistePorte);



                for (Porte porte : listePorte) {
                    System.out.println("porteSURPLOMB2");

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




    }



    private void drawFenetre(Graphics g)
    {

        //ArrayList<pointPouces> sommetsFenetre = new ArrayList<>() ;
        g.setColor(new Color(1, 100, 166));


        java.util.List<Fenetre> listeFenetre = facade.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {

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
