package domain;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;

import java.awt.*;
import java.util.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnInt;
import static java.util.Arrays.asList;

public class Chalet {

    public static double largeurChalet = 300;
    public static double longueurChalet = 300;
    public static double hauteurMurs = 2 * 80;
    public static double epaisseurChalet = 2 * 15; //15
    public static double angleToit = 15.0;
    public static double retraitChalet;
    public static List<Mur> listeMurs; //ex: listeMurs  = [Mur n, Mur w, Mur e, Mur s]
    public static String orientationToit;
    public static double zoom;
    public static float offsetX = 100;
    public static float offsetY = 170;
    public Chalet(double largeurChalet, double longueurChalet,
                  double epaisseurChalet, double angleToit,
                  double hauteurMurs, List<Mur> listeMurs, String orientationToit) {
        this.largeurChalet = largeurChalet;
        this.longueurChalet = longueurChalet;
        this.hauteurMurs = hauteurMurs;
        this.epaisseurChalet = epaisseurChalet;
        this.angleToit = angleToit;
        this.listeMurs = listeMurs;
        this.orientationToit = orientationToit;
        this.zoom = 1;
    }


    public void initialiserMurFacade() {

        //points du haut
        PointDouble pointInfGauche = new PointDouble(0, 0);
        PointDouble pointSupGauche = new PointDouble(0, epaisseurChalet);
        PointDouble pointSupDroit = new PointDouble(longueurChalet, epaisseurChalet);
        PointDouble pointInfDroit = new PointDouble(longueurChalet, 0);

        //Points de face
        PointDouble pointInfGaucheFace = new PointDouble(0, 0);
        PointDouble pointSupGaucheFace = new PointDouble(0, hauteurMurs);
        PointDouble pointSupDroitFace = new PointDouble(longueurChalet, hauteurMurs);
        PointDouble pointInfDroitFace = new PointDouble(longueurChalet, 0);

        Mur facade = new Mur("Facade", asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new LinkedList<String>());

        listeMurs.add(facade);
    }


    public void initialiserMurArriere() {

        //points de haut
        PointDouble pointInfGauche = new PointDouble(0, largeurChalet - epaisseurChalet);
        PointDouble pointSupGauche = new PointDouble(0, largeurChalet);
        PointDouble pointSupDroit = new PointDouble(longueurChalet, largeurChalet);
        PointDouble pointInfDroit = new PointDouble(longueurChalet, largeurChalet - epaisseurChalet);

        //points de face
        PointDouble pointInfGaucheArriere = new PointDouble(0, 0);
        PointDouble pointSupGaucheArriere = new PointDouble(0, hauteurMurs);
        PointDouble pointSupDroitArriere = new PointDouble(longueurChalet, hauteurMurs);
        PointDouble pointInfDroitArriere = new PointDouble(longueurChalet, 0);

        Mur arriere = new Mur("Arriere", asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheArriere, pointSupGaucheArriere, pointSupDroitArriere, pointInfDroitArriere), new LinkedList<String>());

        listeMurs.add(arriere);
        System.out.println("Initialisation");

    }

    public void initialiserMurGauche() {

        //points de haut
        PointDouble pointInfGauche = new PointDouble(0, 0);
        PointDouble pointSupGauche = new PointDouble(0, largeurChalet);
        PointDouble pointSupDroit = new PointDouble(epaisseurChalet, largeurChalet);
        PointDouble pointInfDroit = new PointDouble(epaisseurChalet, 0);

        //points de face
        PointDouble pointInfGaucheFace = new PointDouble(0, 0);
        PointDouble pointSupGaucheFace = new PointDouble(0, hauteurMurs);
        PointDouble pointSupDroitFace = new PointDouble(largeurChalet, hauteurMurs);
        PointDouble pointInfDroitFace = new PointDouble(largeurChalet, 0);

        Mur gauche = new Mur("Gauche", asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new LinkedList<String>());

        listeMurs.add(gauche);
    }

    public void initialiserMurDroite() {

        //points de haut
        PointDouble pointInfGauche = new PointDouble(longueurChalet - epaisseurChalet, 0);
        PointDouble pointSupGauche = new PointDouble(longueurChalet - epaisseurChalet, largeurChalet);
        PointDouble pointSupDroit = new PointDouble(longueurChalet, largeurChalet);
        PointDouble pointInfDroit = new PointDouble(longueurChalet, 0);

        //points de face
        PointDouble pointInfGaucheFace = new PointDouble(0, 0);
        PointDouble pointSupGaucheFace = new PointDouble(0, hauteurMurs);
        PointDouble pointSupDroitFace = new PointDouble(largeurChalet, hauteurMurs);
        PointDouble pointInfDroitFace = new PointDouble(largeurChalet, 0);

        Mur droite = new Mur("Droite", asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new LinkedList<String>());

        listeMurs.add(droite);
    }

    public void retirerRainures(List<Mur> listeDeMursARainurer, double distanceUsinage, String orientationToit) {
        List<Mur> mursDecoupes = new LinkedList<>();
        if (Objects.equals(orientationToit, "Nord") || Objects.equals(orientationToit, "Sud")) {
            for (Mur mur : listeDeMursARainurer) {
                if (Objects.equals(mur.getNomMur(), "Facade")) {
                    mur.getSommetsMur().get(0).setLocation(0, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.createSommet(mur, new PointDouble(0, epaisseurChalet / 2 - distanceUsinage)); // creer Point(0, 1.3) rainure exterieur gauche
                    mur.createSommet(mur, new PointDouble(epaisseurChalet / 2 + distanceUsinage, epaisseurChalet / 2 - distanceUsinage)); // creer Point(1.7, 1.3) rainure interieur gauche
                    mur.getSommetsMur().get(1).setLocation(epaisseurChalet / 2 + distanceUsinage, epaisseurChalet - distanceUsinage); //B: SupGauche // Point(0, 3.0) devient Point(1.7, 3.0)

                    mur.getSommetsMur().get(2).setLocation(longueurChalet - (epaisseurChalet / 2) - distanceUsinage, epaisseurChalet - distanceUsinage); //C: SupDroite // Point(10.0, 3.0) devient Point(9.8, 3.0)
                    mur.createSommet(mur, new PointDouble(longueurChalet - (epaisseurChalet / 2) - distanceUsinage, (epaisseurChalet / 2) - distanceUsinage)); // creer Point(8.3, 1.7) rainure interieur droite
                    mur.createSommet(mur, new PointDouble(longueurChalet, (epaisseurChalet / 2) - distanceUsinage)); // creer Point(10.0, 1.7) rainure exterieur droite ne bouge pas
                    mur.getSommetsMur().get(3).setLocation(longueurChalet, 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    //Vue coté
                    mur.getSommetsMur().get(4).setLocation(0, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation(0, getHauteurMurs()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(getLongueurChalet(), getHauteurMurs()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(getLongueurChalet(), 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Arriere")) {
                    //initial: A=(0, 10.0), B=(0, 7.0), C=(10.0, 7.0), D=(10.0, 10.0)
                    mur.getSommetsMur().get(0).setLocation((epaisseurChalet / 2) + distanceUsinage, largeurChalet - (epaisseurChalet) + distanceUsinage); //A: SupGauche // Point(0, 10.0) reste Point(0, 10.0)
                    mur.createSommet(mur, new PointDouble((epaisseurChalet / 2) + distanceUsinage, (largeurChalet - (epaisseurChalet / 2) + distanceUsinage))); // creer Point(0, 8.7)
                    mur.createSommet(mur, new PointDouble(0, (largeurChalet - (epaisseurChalet / 2) + distanceUsinage))); // creer Point(1.7, 8.7)
                    mur.getSommetsMur().get(1).setLocation(0, largeurChalet); //B: (0, 10) reste (0, 10)

                    mur.getSommetsMur().get(2).setLocation(longueurChalet, largeurChalet); //C: InfDroite // Point(10.0, 7.0) devient Point(8,3, 7.0)
                    mur.createSommet(mur, new PointDouble(longueurChalet, largeurChalet - (epaisseurChalet / 2) + distanceUsinage)); // creer Point(8.3, 8.7)
                    mur.createSommet(mur, new PointDouble(longueurChalet - (epaisseurChalet / 2) - distanceUsinage, largeurChalet - (epaisseurChalet / 2) + distanceUsinage)); // creer Point(10.0, 8.7)
                    mur.getSommetsMur().get(3).setLocation(longueurChalet - (epaisseurChalet / 2) - distanceUsinage, largeurChalet - epaisseurChalet + distanceUsinage); //D: SupDroite // Point(10.0, 10.0) reste Point(10.0, 10.0)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation(0, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation(0, hauteurMurs); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(longueurChalet, hauteurMurs); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(longueurChalet, hauteurMurs); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Gauche")) {
                    // final points (0, 1.7), (1.3, 1.7), (1.3, 3.2), (3.0, 3.2), (0, 8.3), (1.3, 8.3), (1.3, 7.6), (3.0, 7.6)
                    //initial points:  0:(0, 0)  1:(0, 10)  2: (3, 10)  3: (3, 0)
                    mur.getSommetsMur().get(0).setLocation(0, (epaisseurChalet / 2) + distanceUsinage); //A: InfGauche // Point(0, 0) devient Point(0, 1.5)
                    mur.getSommetsMur().get(1).setLocation(0, (largeurChalet - (epaisseurChalet / 2)) - distanceUsinage); //B: SupGauche // Point(0, 10) devient Point(0,9)
                    mur.createSommet(mur, new PointDouble((epaisseurChalet / 2) - distanceUsinage, (largeurChalet - (epaisseurChalet / 2)) - distanceUsinage)); // creer Point(1.5, 9)
                    mur.createSommet(mur, new PointDouble((epaisseurChalet / 2) - distanceUsinage, (largeurChalet - epaisseurChalet) - distanceUsinage)); // creer Point(1.5, 8)

                    mur.getSommetsMur().get(2).setLocation(epaisseurChalet, (largeurChalet - epaisseurChalet) - distanceUsinage); //C: SupDroite // Point(2, 10) devient Point(2, 8)
                    mur.getSommetsMur().get(3).setLocation(epaisseurChalet, epaisseurChalet + distanceUsinage); //D: InfDroite // Point(2, 0) devient Point(2, 2)
                    mur.createSommet(mur, new PointDouble((epaisseurChalet / 2) - distanceUsinage, epaisseurChalet + distanceUsinage)); // creer Point(1.5, 2)
                    mur.createSommet(mur, new PointDouble((epaisseurChalet / 2) - distanceUsinage, (epaisseurChalet / 2) + distanceUsinage)); // creer Point(1.5, 1.5)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation((epaisseurChalet / 2) + distanceUsinage, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation((epaisseurChalet / 2) + distanceUsinage, hauteurMurs); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(largeurChalet - (epaisseurChalet / 2) - distanceUsinage, hauteurMurs); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(largeurChalet - (epaisseurChalet / 2) - distanceUsinage, 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Droite")) {
                    //initial points: A  0:(7.0, 0)  B  1:(7.0, 10)  C  2:(10.0, 10.0)  D  3:(10.0, 0)
                    //final points: A:(7.0, 3.2)  B:(7.0, 6.8)  E:(8.7, 6.8)  F:(8.7, 8.3)   C:(10, 8.3)  D:(10.0, 1.7)  G:(8.7, 1.7)  H:(8.7, 3.2)
                    mur.getSommetsMur().get(0).setLocation((longueurChalet - epaisseurChalet), epaisseurChalet + distanceUsinage); // A: InfGauche // Point(7.0, 0) devient Point(8, 2)
                    mur.getSommetsMur().get(1).setLocation((longueurChalet - epaisseurChalet), (largeurChalet - epaisseurChalet) - distanceUsinage); // B: SupGauche // Point(7.0, 10.0) devient Point(8, 8)
                    mur.createSommet(mur, new PointDouble((longueurChalet - (epaisseurChalet / 2)), (largeurChalet - epaisseurChalet) - distanceUsinage)); // E creer Point(9, 8)
                    mur.createSommet(mur, new PointDouble((longueurChalet - (epaisseurChalet / 2)), ((largeurChalet - (epaisseurChalet / 2))) - distanceUsinage)); // F creer Point(9, 9)

                    mur.getSommetsMur().get(2).setLocation(longueurChalet, (largeurChalet - (epaisseurChalet / 2)) - distanceUsinage); // C: SupDroite // Point(10.0, 10.0) devient Point(10.0, 9)
                    mur.getSommetsMur().get(3).setLocation(longueurChalet, (epaisseurChalet / 2) + distanceUsinage); // D: InfDroite // Point(10.0, 0) devient Point(10.0, 1)
                    mur.createSommet(mur, new PointDouble((longueurChalet - (epaisseurChalet / 2)), (epaisseurChalet / 2) + distanceUsinage)); // G creer Point(9, 1)
                    mur.createSommet(mur, new PointDouble((longueurChalet - (epaisseurChalet / 2)), epaisseurChalet + distanceUsinage)); // H creer Point(9, 2)
                    //Vue de coté
                    mur.getSommetsMur().get(4).setLocation(epaisseurChalet / 2, 0); //A: InfGauche
                    mur.getSommetsMur().get(5).setLocation(epaisseurChalet / 2, hauteurMurs); //B: SupGauche
                    mur.getSommetsMur().get(6).setLocation(largeurChalet - (epaisseurChalet / 2), hauteurMurs); //C: SupDroite
                    mur.getSommetsMur().get(7).setLocation(largeurChalet - (epaisseurChalet / 2), 0); //D: InfDroite
                    mursDecoupes.add(mur);
                }
            }
            //System.out.println("Liste des murs avec rainures: " + mursDecoupes);
        }
        if (Objects.equals(orientationToit, "Ouest") || Objects.equals(orientationToit, "Est")) {
            System.out.println("Orientation toit est Est OU Ouest, dans ce cas: " + orientationToit);
            for (Mur mur : listeDeMursARainurer) {
                if (Objects.equals(mur.getNomMur(), "Facade")) {
                    mur.getSommetsMur().get(0).setLocation(epaisseurChalet / 2 + distanceUsinage, 0); //sommet0 dessin
                    mur.createSommet(mur, new PointDouble(epaisseurChalet / 2 + distanceUsinage, epaisseurChalet / 2 - distanceUsinage)); //sommet1 dessin
                    mur.createSommet(mur, new PointDouble(epaisseurChalet + distanceUsinage, epaisseurChalet / 2 - distanceUsinage)); //sommet2 dessin
                    mur.getSommetsMur().get(1).setLocation(epaisseurChalet + distanceUsinage, epaisseurChalet); //B: SupGauche // Point(0, 10) reste Point(0, 10)

                    mur.getSommetsMur().get(2).setLocation(longueurChalet - epaisseurChalet - distanceUsinage, epaisseurChalet); //C: SupDroite // Point(10.0, 3.0) devient Point(9.8, 3.0)
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet - distanceUsinage, epaisseurChalet / 2 - distanceUsinage)); // creer Point(8.3, 1.7) rainure interieur droite
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet / 2 - distanceUsinage, epaisseurChalet / 2 - distanceUsinage)); // creer Point(8.3, 1.7) rainure exterieur droite ne bouge pas
                    mur.getSommetsMur().get(3).setLocation(longueurChalet - epaisseurChalet / 2 - distanceUsinage, 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    //Vue de coté
                    mur.getSommetsMur().get(4).setLocation(epaisseurChalet/2 - distanceUsinage , 0);
                    mur.getSommetsMur().get(5).setLocation(epaisseurChalet/2 - distanceUsinage, hauteurMurs);
                    mur.getSommetsMur().get(6).setLocation(longueurChalet - epaisseurChalet/2 + distanceUsinage, hauteurMurs);
                    mur.getSommetsMur().get(7).setLocation(longueurChalet - epaisseurChalet/2 + distanceUsinage, 0);


                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Arriere")) {
                    mur.getSommetsMur().get(0).setLocation(epaisseurChalet + distanceUsinage, largeurChalet - epaisseurChalet); // creer Point(2.0, 8.3) rainure interieur gauche
                    mur.createSommet(mur, new PointDouble(epaisseurChalet + distanceUsinage, largeurChalet - epaisseurChalet / 2 + distanceUsinage)); // creer Point(2.0, 8.3) rainure exterieur gauche
                    mur.createSommet(mur, new PointDouble(epaisseurChalet / 2 + distanceUsinage, largeurChalet - epaisseurChalet / 2 + distanceUsinage)); // creer Point(2.0, 8.3) rainure exterieur gauche
                    mur.getSommetsMur().get(1).setLocation(epaisseurChalet / 2 + distanceUsinage, largeurChalet);

                    mur.getSommetsMur().get(2).setLocation(longueurChalet - epaisseurChalet / 2 - distanceUsinage, largeurChalet); // creer Point(2.0, 8.3) rainure exterieur gauche
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet / 2 - distanceUsinage, largeurChalet - epaisseurChalet / 2 + distanceUsinage)); // creer Point(2.0, 8.3) rainure exterieur gauche
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet - distanceUsinage, largeurChalet - epaisseurChalet / 2 + distanceUsinage)); // creer Point(2.0, 8.3) rainure exterieur gauche
                    mur.getSommetsMur().get(3).setLocation(longueurChalet - epaisseurChalet - distanceUsinage, largeurChalet - epaisseurChalet);

                    //Vue de face

                    mur.getSommetsMur().get(4).setLocation(epaisseurChalet/2 - distanceUsinage , 0);
                    mur.getSommetsMur().get(5).setLocation(epaisseurChalet/2 - distanceUsinage, hauteurMurs);
                    mur.getSommetsMur().get(6).setLocation(longueurChalet - epaisseurChalet/2 + distanceUsinage, hauteurMurs);
                    mur.getSommetsMur().get(7).setLocation(longueurChalet - epaisseurChalet/2 + distanceUsinage, 0);

                    mursDecoupes.add(mur);
                }

                if (Objects.equals(mur.getNomMur(), "Gauche")) {
                    mur.getSommetsMur().get(0).setLocation(0, 0); //sommet0 dessin
                    mur.getSommetsMur().get(1).setLocation(0, largeurChalet); //sommet1 dessin
                    mur.createSommet(mur, new PointDouble(epaisseurChalet / 2 - distanceUsinage, largeurChalet)); //sommet2 dessin
                    mur.createSommet(mur, new PointDouble(epaisseurChalet / 2 - distanceUsinage, largeurChalet - epaisseurChalet / 2 - distanceUsinage));

                    mur.getSommetsMur().get(2).setLocation(epaisseurChalet - distanceUsinage, largeurChalet - epaisseurChalet / 2 - distanceUsinage); //sommet4 dessin
                    mur.getSommetsMur().get(3).setLocation(epaisseurChalet - distanceUsinage, epaisseurChalet / 2 + distanceUsinage); //sommet5 dessin
                    mur.createSommet(mur, new PointDouble(epaisseurChalet / 2 - distanceUsinage, epaisseurChalet / 2 + distanceUsinage)); //sommet6 dessin
                    mur.createSommet(mur, new PointDouble(epaisseurChalet / 2 - distanceUsinage, 0)); //sommet7 dessin

                    //Vue face
                    mur.getSommetsMur().get(4).setLocation(0, 0);
                    mur.getSommetsMur().get(5).setLocation(0, hauteurMurs);
                    mur.getSommetsMur().get(6).setLocation(largeurChalet, hauteurMurs);
                    mur.getSommetsMur().get(7).setLocation(largeurChalet, 0);

                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Droite")) {
                    mur.getSommetsMur().get(0).setLocation(longueurChalet - epaisseurChalet + distanceUsinage, epaisseurChalet / 2 + distanceUsinage); //sommet0 dessin
                    mur.getSommetsMur().get(1).setLocation(longueurChalet - epaisseurChalet + distanceUsinage, largeurChalet - epaisseurChalet / 2 - distanceUsinage); //sommet1 dessin
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet / 2 + distanceUsinage, largeurChalet - epaisseurChalet / 2 - distanceUsinage)); //sommet2 dessin
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet / 2 + distanceUsinage, largeurChalet)); //sommet3 dessin

                    mur.getSommetsMur().get(2).setLocation(longueurChalet, largeurChalet); //sommet4 dessin
                    mur.getSommetsMur().get(3).setLocation(longueurChalet, 0); //sommet5 dessin
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet / 2 + distanceUsinage, 0)); //sommet6 dessin
                    mur.createSommet(mur, new PointDouble(longueurChalet - epaisseurChalet / 2 + distanceUsinage, epaisseurChalet / 2 + distanceUsinage)); //sommet7 dessin

                    //Vue face

                    mur.getSommetsMur().get(4).setLocation(0, 0);
                    mur.getSommetsMur().get(5).setLocation(0, hauteurMurs);
                    mur.getSommetsMur().get(6).setLocation(largeurChalet, hauteurMurs);
                    mur.getSommetsMur().get(7).setLocation(largeurChalet, 0);
                    mursDecoupes.add(mur);
                }
            }
        }
    }

    public List<Mur> getMursUsines(double distanceUsinage, String orientationToit) {
        retirerRainures(listeMurs, retraitChalet, Chalet.orientationToit);
        return listeMurs;
    }


    public static int determinerMur(String nomMur) {

        int numMur = 0;

        if (nomMur == "FACADE") {
            numMur = 0;
        }
        if (nomMur == "ARRIERE") {
            numMur = 1;

        }
        if (nomMur == "DROITE") {
            numMur = 3;

        }
        if (nomMur == "GAUCHE") {
            numMur = 2;

        }
        return numMur;

    }


    public static List<Point> DeterminerCollisionSommetsMur(Mur mur, Dimension initialDimension) {

        //List<PointDouble> sommetsMur = mur.getSommetsMur();

        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        PointDouble pointSupDroitfc = mur.getSommetsMur().get(4);
        PointDouble pointSupGauchefc = mur.getSommetsMur().get(5);
        PointDouble pointInfDroitfc = mur.getSommetsMur().get(6);
        PointDouble pointInfGauchefc = mur.getSommetsMur().get(7);

        double positionX = width / 2 - pointInfDroitfc.getX() / 2;
        double positionY = height / 2 - pointInfDroitfc.getY() / 2;

        int x1fc = (int) (pointInfGauchefc.getX() + 0);
        int y1fc = (int) (pointInfGauchefc.getY() + 0);

        int x2fc = (int) (pointInfDroitfc.getX() + 0);
        int y2fc = (int) (pointInfDroitfc.getY() + 0);

        int x3fc = (int) (pointSupGauchefc.getX() + 0);
        int y3fc = (int) (pointSupGauchefc.getY() + 0);

        int x4fc = (int) (pointSupDroitfc.getX() + 0);
        int y4fc = (int) (pointSupDroitfc.getY() + 0);

        // Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsFacadeCote = {x1fc, x2fc, x3fc, x4fc};
        int[] yPointsFacadeCote = {y1fc, y2fc, y3fc, y4fc};
        System.out.println(x1fc + " " + y1fc + " En Haut a Gauche ");
        System.out.println(x2fc + " " + y2fc + " En Bas a Gauche ");
        System.out.println(x3fc + " " + y3fc + " En Bas a Droite ");
        System.out.println(x4fc + " " + y4fc + " En Haut a Droite ");


        Point SupGauche = new Point(x1fc, y1fc);
        Point SupDroite = new Point(x4fc, y4fc);
        Point InfGauche = new Point(x2fc, y2fc);
        Point InfDroite = new Point(x3fc, y3fc);


        List<Point> PointsMur = new LinkedList<>();
        PointsMur.add(SupGauche);
        PointsMur.add(SupDroite);
        PointsMur.add(InfGauche);
        PointsMur.add(InfDroite);

        return PointsMur;

    }



    public static boolean estDansRectangle(Point point, Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit) {
        int x = point.x;
        int y = point.y;

        int x1 = coinSupGauche.x;
        int y1 = coinSupGauche.y;

        int x2 = coinSupDroit.x;
        int y2 = coinSupDroit.y;

        int x3 = coinInfGauche.x;
        int y3 = coinInfGauche.y;

        int x4 = coinInfDroit.x;
        int y4 = coinInfDroit.y;

        //Probleme dans la méthode de generations des sommets des murs. Le points superieur droit est plus petit que le coin superieur gauche.
        //boolean conditionUn = x >= x1;
        //boolean conditionDeux = x <= x2 ;

        boolean conditionUn = x <= x1;
        boolean conditionDeux = x >= x2;
        boolean conditionTrois = y >= y1;
        boolean conditionQuatre = y <= y3;
        System.out.println(conditionUn +""+  conditionDeux +""+  conditionTrois +""+  conditionQuatre +"Les conditions" );

        // Vérifie si le point se trouve à l'intérieur du rectangle
        boolean estDansRectangle = (conditionUn && conditionDeux && conditionTrois && conditionQuatre);

        //System.out.println(estDansRectangle + "(estDansRectangle) " + point);
        return estDansRectangle;

    /*private static boolean estDansRectangle2(Point pointVerification, Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit) {
        boolean Rect = pointVerification.x >= coinSupGauche.x && pointVerification.x <= coinSupDroit.x
                && pointVerification.y >= coinSupGauche.y && pointVerification.y <= coinInfGauche.y ;
        System.out.println(Rect + "(estDansRectangle) "+pointVerification);

    return (pointVerification.x >= coinSupGauche.x && pointVerification.x <= coinSupDroit.x
                && pointVerification.y >= coinSupGauche.y && pointVerification.y <= coinInfGauche.y);
    } */

    }
    public static boolean estDansRectangleAcc(Point point, Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit) {
        int x = point.x;
        int y = point.y;

        int x1 = coinSupGauche.x;
        int y1 = coinSupGauche.y;

        int x2 = coinSupDroit.x;
        int y2 = coinSupDroit.y;

        int x3 = coinInfGauche.x;
        int y3 = coinInfGauche.y;

        int x4 = coinInfDroit.x;
        int y4 = coinInfDroit.y;

        //Probleme dans la méthode de generations des sommets des murs. Le points superieur droit est plus petit que le coin superieur gauche.
        //boolean conditionUn = x >= x1;
        //boolean conditionDeux = x <= x2 ;

        boolean conditionUn = x >= x1;
        boolean conditionDeux = x <= x2 ;
        boolean conditionTrois = y >= y1;
        boolean conditionQuatre = y <= y3;
        //System.out.println(conditionUn +""+  conditionDeux +""+  conditionTrois +""+  conditionQuatre +"Les conditions" );

        // Vérifie si le point se trouve à l'intérieur du rectangle
        boolean estDansRectangle = (conditionUn &&  conditionDeux && conditionTrois && conditionQuatre);

        //System.out.println(estDansRectangle + "(estDansRectangle) " + point);
        return estDansRectangle;


    }







    public static boolean estDansRectangleMurArriere(Point point, Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit) {
        int x = point.x;
        int y = point.y;

        int x2 = coinSupGauche.x;
        int y2 = coinSupGauche.y;

        int x1 = coinSupDroit.x;
        int y1 = coinSupDroit.y;

        int x3 = coinInfGauche.x;
        int y3 = coinInfGauche.y;

        int x4 = coinInfDroit.x;
        int y4 = coinInfDroit.y;

        //Probleme dans la méthode de generations des sommets des murs. Le points superieur droit est plus petit que le coin superieur gauche.
        //boolean conditionUn = x >= x1;
        //boolean conditionDeux = x <= x2 ;

        boolean conditionUn = x >= x1;
        boolean conditionDeux = x <= x2;
        boolean conditionTrois = y >= y1;
        boolean conditionQuatre = y <= y3;
        //System.out.println(conditionUn +""+  conditionDeux +""+  conditionTrois +""+  conditionQuatre +"Les conditions" );

        // Vérifie si le point se trouve à l'intérieur du rectangle
        boolean estDansRectangle = (conditionUn && conditionDeux && conditionTrois && conditionQuatre);

        //System.out.println(estDansRectangle + "(estDansRectangle) " + point);
        return estDansRectangle;

    /*private static boolean estDansRectangle2(Point pointVerification, Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit) {
        boolean Rect = pointVerification.x >= coinSupGauche.x && pointVerification.x <= coinSupDroit.x
                && pointVerification.y >= coinSupGauche.y && pointVerification.y <= coinInfGauche.y ;
        System.out.println(Rect + "(estDansRectangle) "+pointVerification);

    return (pointVerification.x >= coinSupGauche.x && pointVerification.x <= coinSupDroit.x
                && pointVerification.y >= coinSupGauche.y && pointVerification.y <= coinInfGauche.y);
    } */

    }

    public static boolean AntiCollisionAccessoireMur(Mur mur, Point mousePoint, Pouces largeurPouces, Pouces hauteurPouces, Dimension initialDimension){
        //On récupere les mesures de la fenetres
        int largeur = convertirPoucesEnInt(largeurPouces);
        int hauteur = convertirPoucesEnInt(hauteurPouces);

        //On determine les sommets du mur
        List<Point> PointsMur = DeterminerCollisionSommetsMur(mur,initialDimension);

        Point SupGaucheMur = PointsMur.get(0);
        Point SupDroiteMur = PointsMur.get(1);
        Point InfGaucheMur = PointsMur.get(2);
        Point InfDroiteMur = PointsMur.get(3);
        /*System.out.println(SupGaucheMur+"(DeterminerCollisionSommetsMur) Mur En Haut a Gauche ");
        System.out.println(InfGaucheMur+"(DeterminerCollisionSommetsMur) Mur En Bas a Gauche ");
        System.out.println(InfDroiteMur+"(DeterminerCollisionSommetsMur) Mur En Bas a Droite ");
        System.out.println(SupDroiteMur+"(DeterminerCollisionSommetsMur) Mur En Haut a Droite "); */



        //On determine les sommets de la fenetres
        Point SupGaucheFenetre = mousePoint;
        Point SupDroitFenetre = new Point(SupGaucheFenetre.x + largeur, SupGaucheFenetre.y);
        Point InfGaucheFenetre = new Point(SupGaucheFenetre.x, SupGaucheFenetre.y + hauteur);
        Point InfDroitFenetre = new Point(SupGaucheFenetre.x + largeur, SupGaucheFenetre.y + hauteur);
        /*System.out.println(SupGaucheFenetre+"(AntiCollisionFenetreMur) Fenetre En Haut a Gauche ");
        System.out.println(InfGaucheFenetre+"(AntiCollisionFenetreMur) Fenetre En Bas a Gauche ");
        System.out.println(InfDroitFenetre+"(AntiCollisionFenetreMur) Fenetre En Bas a Droite ");
        System.out.println(SupDroitFenetre+"(AntiCollisionFenetreMur) Fenetre En Haut a Droite "); */

        boolean PointUnRect = estDansRectangle(SupGaucheFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur);
        boolean PointDeuxRect = estDansRectangle(SupDroitFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur) ;
        boolean PointTroisRect = estDansRectangle(InfGaucheFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur) ;
        boolean PointQuatreRect = estDansRectangle(InfDroitFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur);

        return (PointUnRect && PointDeuxRect & PointTroisRect && PointQuatreRect);


        /* return (estDansRectangle(SupGaucheFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur)
                && estDansRectangle(SupDroitFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur)
                && estDansRectangle(InfGaucheFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur)
                && estDansRectangle(InfDroitFenetre, SupGaucheMur, SupDroiteMur, InfGaucheMur, InfDroiteMur)); */
    }


    public static List<Point> determinerSommetsAccessoires(Point mousePoint, int largeur, int hauteur){

        //On determine les sommets de la Accessoires
        Point SupGaucheAccessoires = mousePoint;
        Point SupDroitAccessoires = new Point(SupGaucheAccessoires.x + largeur, SupGaucheAccessoires.y);
        Point InfGaucheAccessoires = new Point(SupGaucheAccessoires.x, SupGaucheAccessoires.y + hauteur);
        Point InfDroitAccessoires = new Point(SupGaucheAccessoires.x + largeur, SupGaucheAccessoires.y + hauteur);
        /*System.out.println(SupGaucheAccessoires+"(AntiCollisionAccessoires) Accessoires En Haut a Gauche ");
        System.out.println(InfGaucheAccessoires+"(AntiCollisionAccessoires) Accessoires En Bas a Gauche ");
        System.out.println(InfDroitAccessoires+ "(AntiCollisionAccessoires) Accessoires En Bas a Droite ");
        System.out.println(SupDroitAccessoires+ "(AntiCollisionAccessoires) Accessoires En Haut a Droite "); */

        List<Point> PointsMur = new ArrayList<>();
        PointsMur.add(SupGaucheAccessoires);
        PointsMur.add(SupDroitAccessoires);
        PointsMur.add(InfGaucheAccessoires);
        PointsMur.add(InfDroitAccessoires);

        return PointsMur;
    }


    public static boolean AntiCollisionFenetrePorte(Mur mur, Point mousePoint, Pouces largeurPouces, Pouces hauteurPouces) {
        //On récupere les mesures de la fenetres
        int largeur = convertirPoucesEnInt(largeurPouces);
        int hauteur = convertirPoucesEnInt(hauteurPouces);

        List<Point> listePoints = determinerSommetsAccessoires(mousePoint,largeur,hauteur);

        Point SupGaucheFenetre = listePoints.get(0);
        Point SupDroitFenetre = listePoints.get(1);
        Point InfGaucheFenetre = listePoints.get(2);
        Point InfDroitFenetre = listePoints.get(3);


        /*System.out.println(SupGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Haut a Gauche ");
        System.out.println(SupDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Bas a Gauche ");
        System.out.println(InfGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Bas a Droite ");
        System.out.println(InfDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Haut a Droite "); */




        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {

            Point mousePointFenetre = fenetre.getPoint();
            Pouces ValidationTroisPouces = new Pouces(30,0,1);

            //Largeur
            Pouces largeurListe = fenetre.getLargeur();
            largeurListe = largeurListe.addPouces(ValidationTroisPouces);
            int largeurFenetre = convertirPoucesEnInt(largeurListe);

            //Hauteur
            Pouces hauteurListe = fenetre.getHauteur();
            hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
            int hauteurFenetre = convertirPoucesEnInt(hauteurListe);
            System.out.println(fenetre + "FenetreDeListeFenetre ");

            List<Point> listePointsFenetre = determinerSommetsAccessoires(mousePointFenetre,largeurFenetre,hauteurFenetre);

            Point SupGaucheListeFenetre = listePointsFenetre.get(0);
            Point SupDroitListeFenetre = listePointsFenetre.get(1);
            Point InfGaucheListeFenetre = listePointsFenetre.get(2);
            Point InfDroitListeFenetre = listePointsFenetre.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Haut a Droite "); */

            boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /* System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

            if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
            {
                System.out.println(true+"(AntiCollisionFenetre) ");

                return true;

            }

        }

        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {

            Point mousePointFenetre = porte.getPoint();
            Pouces ValidationTroisPouces = new Pouces(3,0,1);



            //Largeur
            Pouces largeurListe = porte.getLargeur();
            largeurListe = largeurListe.addPouces(ValidationTroisPouces);
            int largeurPorte = convertirPoucesEnInt(largeurListe);

            //Hauteur
            Pouces hauteurListe = porte.getHauteur();
            hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
            int hauteurPorte = convertirPoucesEnInt(hauteurListe);
            System.out.println(porte + "PorteDeListePorte");

            List<Point> listePointsPorte = determinerSommetsAccessoires(mousePointFenetre,largeurPorte,hauteurPorte);

            Point SupGaucheListePorte = listePointsPorte.get(0);
            Point SupDroitListePorte = listePointsPorte.get(1);
            Point InfGaucheListePorte = listePointsPorte.get(2);
            Point InfDroitListePorte = listePointsPorte.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Haut a Droite "); */

            boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
            boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte) ;
            boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte) ;
            boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
            //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /* System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

            if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
            {
                System.out.println(true+"(AntiCollisionPorte) ");

                return true;

            }

        }



        System.out.println(false+"(AntiCollisionFenetre) ");
        return false;


    }

    public static boolean AntiCollisionFenetreModification(Mur mur, Fenetre fenetreExistante, Point mousePoint, Pouces largeurPouces, Pouces hauteurPouces) {
        //On récupere les mesures de la fenetres
        int largeur = convertirPoucesEnInt(largeurPouces);
        int hauteur = convertirPoucesEnInt(hauteurPouces);

        List<Point> listePoints = determinerSommetsAccessoires(mousePoint,largeur,hauteur);

        Point SupGaucheFenetre = listePoints.get(0);
        Point SupDroitFenetre = listePoints.get(1);
        Point InfGaucheFenetre = listePoints.get(2);
        Point InfDroitFenetre = listePoints.get(3);


        /*System.out.println(SupGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Haut a Gauche ");
        System.out.println(SupDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Bas a Gauche ");
        System.out.println(InfGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Bas a Droite ");
        System.out.println(InfDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Haut a Droite "); */




        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {

            if(fenetre != fenetreExistante ) {

                Point mousePointFenetre = fenetre.getPoint();
                Pouces ValidationTroisPouces = new Pouces(3,0,1);

                //Largeur
                Pouces largeurListe = fenetre.getLargeur();
                largeurListe = largeurListe.addPouces(ValidationTroisPouces);
                int largeurFenetre = convertirPoucesEnInt(largeurListe);

                //Hauteur
                Pouces hauteurListe = fenetre.getHauteur();
                hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
                int hauteurFenetre = convertirPoucesEnInt(hauteurListe);
                System.out.println(fenetre + "FenetreDeListeFenetre ");

                List<Point> listePointsFenetre = determinerSommetsAccessoires(mousePointFenetre,largeurFenetre,hauteurFenetre);

                Point SupGaucheListeFenetre = listePointsFenetre.get(0);
                Point SupDroitListeFenetre = listePointsFenetre.get(1);
                Point InfGaucheListeFenetre = listePointsFenetre.get(2);
                Point InfDroitListeFenetre = listePointsFenetre.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Haut a Droite "); */

                boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
                boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
                boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
                boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
                //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /* System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

                if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
                {
                    System.out.println(true+"(AntiCollisionFenetre) ");

                    return true;

                }

            }

        }

        List<Porte> listePorte = mur.getListePorte();
        int nbPorte = listePorte.size();
        if(nbPorte != 0) {

            for (Porte porte : listePorte) {

                Point mousePointFenetre = porte.getPoint();
                Pouces ValidationTroisPouces = new Pouces(3, 0, 1);


                //Largeur
                Pouces largeurListe = porte.getLargeur();
                largeurListe = largeurListe.addPouces(ValidationTroisPouces);
                int largeurPorte = convertirPoucesEnInt(largeurListe);

                //Hauteur
                Pouces hauteurListe = porte.getHauteur();
                hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
                int hauteurPorte = convertirPoucesEnInt(hauteurListe);
                System.out.println(porte + "PorteDeListePorte");

                List<Point> listePointsPorte = determinerSommetsAccessoires(mousePointFenetre, largeurPorte, hauteurPorte);

                Point SupGaucheListePorte = listePointsPorte.get(0);
                Point SupDroitListePorte = listePointsPorte.get(1);
                Point InfGaucheListePorte = listePointsPorte.get(2);
                Point InfDroitListePorte = listePointsPorte.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Haut a Droite "); */

                boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /* System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

                if (PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect) {
                    System.out.println(true + "(AntiCollisionPorte) ");

                    return true;

                }

            }
        }


        System.out.println(false+"(AntiCollisionFenetre) ");
        return false;


    }


    public static boolean AntiCollisionPorteModification(Mur mur, Porte porteExistante, Point mousePoint, Pouces largeurPouces, Pouces hauteurPouces) {
        //On récupere les mesures de la fenetres
        int largeur = convertirPoucesEnInt(largeurPouces);
        int hauteur = convertirPoucesEnInt(hauteurPouces);

        List<Point> listePoints = determinerSommetsAccessoires(mousePoint,largeur,hauteur);

        Point SupGaucheFenetre = listePoints.get(0);
        Point SupDroitFenetre = listePoints.get(1);
        Point InfGaucheFenetre = listePoints.get(2);
        Point InfDroitFenetre = listePoints.get(3);


        /*System.out.println(SupGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Haut a Gauche ");
        System.out.println(SupDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Bas a Gauche ");
        System.out.println(InfGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Bas a Droite ");
        System.out.println(InfDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Haut a Droite "); */




        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {


            Point mousePointFenetre = fenetre.getPoint();
            Pouces ValidationTroisPouces = new Pouces(3,0,1);

            //Largeur
            Pouces largeurListe = fenetre.getLargeur();
            largeurListe = largeurListe.addPouces(ValidationTroisPouces);
            int largeurFenetre = convertirPoucesEnInt(largeurListe);

            //Hauteur
            Pouces hauteurListe = fenetre.getHauteur();
            hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
            int hauteurFenetre = convertirPoucesEnInt(hauteurListe);
            System.out.println(fenetre + "FenetreDeListeFenetre ");

            List<Point> listePointsFenetre = determinerSommetsAccessoires(mousePointFenetre,largeurFenetre,hauteurFenetre);

            Point SupGaucheListeFenetre = listePointsFenetre.get(0);
            Point SupDroitListeFenetre = listePointsFenetre.get(1);
            Point InfGaucheListeFenetre = listePointsFenetre.get(2);
            Point InfDroitListeFenetre = listePointsFenetre.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Haut a Droite "); */

            boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /* System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

            if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
            {
                System.out.println(true+"(AntiCollisionFenetre) ");

                return true;

            }



        }

        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {

            if(porte != porteExistante ) {


                Point mousePointFenetre = porte.getPoint();
                Pouces ValidationTroisPouces = new Pouces(3,0,1);



                //Largeur
                Pouces largeurListe = porte.getLargeur();
                largeurListe = largeurListe.addPouces(ValidationTroisPouces);
                int largeurPorte = convertirPoucesEnInt(largeurListe);

                //Hauteur
                Pouces hauteurListe = porte.getHauteur();
                hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
                int hauteurPorte = convertirPoucesEnInt(hauteurListe);
                System.out.println(porte + "PorteDeListePorte");

                List<Point> listePointsPorte = determinerSommetsAccessoires(mousePointFenetre,largeurPorte,hauteurPorte);

                Point SupGaucheListePorte = listePointsPorte.get(0);
                Point SupDroitListePorte = listePointsPorte.get(1);
                Point InfGaucheListePorte = listePointsPorte.get(2);
                Point InfDroitListePorte = listePointsPorte.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Haut a Droite "); */

                boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte) ;
                boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte) ;
                boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /* System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

                if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
                {
                    System.out.println(true+"(AntiCollisionPorte) ");

                    return true;

                }

            }

        }



        System.out.println(false+"(AntiCollisionFenetre) ");
        return false;


    }


    public static boolean AntiCollisionFenetreModification2(Mur mur,Fenetre fenetreExistante, Point mousePoint, Pouces largeurPouces, Pouces hauteurPouces) {
        //On récupere les mesures de la fenetres
        int largeur = convertirPoucesEnInt(largeurPouces);
        int hauteur = convertirPoucesEnInt(hauteurPouces);

        List<Point> listePoints = determinerSommetsAccessoires(mousePoint,largeur,hauteur);

        Point SupGaucheFenetre = listePoints.get(0);
        Point SupDroitFenetre = listePoints.get(1);
        Point InfGaucheFenetre = listePoints.get(2);
        Point InfDroitFenetre = listePoints.get(3);


        /*System.out.println(SupGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Haut a Gauche ");
        System.out.println(SupDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Bas a Gauche ");
        System.out.println(InfGaucheFenetre+"(determinerSommetsAccessoires) NouvelleFenetre En Bas a Droite ");
        System.out.println(InfDroitFenetre+"(determinerSommetsAccessoires)  NouvelleFenetre En Haut a Droite "); */


        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {

            if(fenetre != fenetreExistante) {


                Point mousePointFenetre = fenetre.getPoint();
                Pouces ValidationTroisPouces = new Pouces(3,0,1);

                //Largeur
                Pouces largeurListe = fenetre.getLargeur();
                largeurListe = largeurListe.addPouces(ValidationTroisPouces);
                int largeurFenetre = convertirPoucesEnInt(largeurListe);

                //Hauteur
                Pouces hauteurListe = fenetre.getHauteur();
                hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
                int hauteurFenetre = convertirPoucesEnInt(hauteurListe);



                System.out.println(fenetre + "FenetreDeListeFenetre ");

                List<Point> listePointsFenetre = determinerSommetsAccessoires(mousePointFenetre,largeurFenetre,hauteurFenetre);

                Point SupGaucheListeFenetre = listePointsFenetre.get(0);
                Point SupDroitListeFenetre = listePointsFenetre.get(1);
                Point InfGaucheListeFenetre = listePointsFenetre.get(2);
                Point InfDroitListeFenetre = listePointsFenetre.get(3);

                /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Haut a Gauche ");
                System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Bas a Gauche ");
                System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Bas a Droite ");
                System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Haut a Droite "); */

                boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
                boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
                boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
                boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
                //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

                System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
                System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
                System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
                System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) ");

                if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
                {
                    System.out.println(true+"(AntiCollisionFenetre) ");

                    return true;

                }

            }

        }


        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {

            Point mousePointFenetre = porte.getPoint();
            Pouces ValidationTroisPouces = new Pouces(3,0,1);

            //Largeur
            Pouces largeurListe = porte.getLargeur();
            largeurListe = largeurListe.addPouces(ValidationTroisPouces);
            int largeurFenetre = convertirPoucesEnInt(largeurListe);

            //Hauteur
            Pouces hauteurListe = porte.getHauteur();
            hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
            int hauteurFenetre = convertirPoucesEnInt(hauteurListe);
            System.out.println(porte + "PorteDeListePorte");

            List<Point> listePointsPorte = determinerSommetsAccessoires(mousePointFenetre,largeurFenetre,hauteurFenetre);

            Point SupGaucheListeFenetre = listePointsPorte.get(0);
            Point SupDroitListeFenetre = listePointsPorte.get(1);
            Point InfGaucheListeFenetre = listePointsPorte.get(2);
            Point InfDroitListeFenetre = listePointsPorte.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Haut a Droite "); */

            boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /*System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

            if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
            {
                System.out.println(true+"(AntiCollisionPorte) ");

                return true;

            }

        }


        System.out.println(false+"(AntiCollisionFenetre) ");
        return false;


    }


    public static boolean AntiCollisionPorteModification2(Mur mur, Porte porteExistante,Point mousePoint, Pouces largeurPouces, Pouces hauteurPouces) {
        //On récupere les mesures de la fenetres
        int largeur = convertirPoucesEnInt(largeurPouces);
        int hauteur = convertirPoucesEnInt(hauteurPouces);

        List<Point> listePoints = determinerSommetsAccessoires(mousePoint,largeur,hauteur);

        Point SupGaucheFenetre = listePoints.get(0);
        Point SupDroitFenetre = listePoints.get(1);
        Point InfGaucheFenetre = listePoints.get(2);
        Point InfDroitFenetre = listePoints.get(3);


        /*System.out.println(SupGaucheFenetre+"(determinerSommetsAccessoires) NouvellePorte En Haut a Gauche ");
        System.out.println(SupDroitFenetre+"(determinerSommetsAccessoires)  NouvellePorte En Bas a Gauche ");
        System.out.println(InfGaucheFenetre+"(determinerSommetsAccessoires) NouvellePorte En Bas a Droite ");
        System.out.println(InfDroitFenetre+"(determinerSommetsAccessoires)  NouvellePorte En Haut a Droite "); */



        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {


            Point mousePointFenetre = fenetre.getPoint();
            Pouces ValidationTroisPouces = new Pouces(3,0,1);

            //Largeur
            Pouces largeurListe = fenetre.getLargeur();
            largeurListe = largeurListe.addPouces(ValidationTroisPouces);
            int largeurFenetre = convertirPoucesEnInt(largeurListe);

            //Hauteur
            Pouces hauteurListe = fenetre.getHauteur();
            hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
            int hauteurFenetre = convertirPoucesEnInt(hauteurListe);

            System.out.println(fenetre + "FenetreDeListeFenetre ");

            List<Point> listePointsFenetre = determinerSommetsAccessoires(mousePointFenetre,largeurFenetre,hauteurFenetre);

            Point SupGaucheListeFenetre = listePointsFenetre.get(0);
            Point SupDroitListeFenetre = listePointsFenetre.get(1);
            Point InfGaucheListeFenetre = listePointsFenetre.get(2);
            Point InfDroitListeFenetre = listePointsFenetre.get(3);

            System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listeFenetre En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listeFenetre En Haut a Droite ");

            boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre) ;
            boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListeFenetre, SupDroitListeFenetre, InfGaucheListeFenetre, InfDroitListeFenetre);
            //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

                /*System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
                System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
                System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
                System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

            if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
            {
                System.out.println(true+"(AntiCollisionFenetre) ");

                return true;

            }

        }


        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {

            if(porte != porteExistante) {

                Point mousePointFenetre = porte.getPoint();
                Pouces ValidationTroisPouces = new Pouces(3,0,1);

                //Largeur
                Pouces largeurListe = porte.getLargeur();
                largeurListe = largeurListe.addPouces(ValidationTroisPouces);
                int largeurPorte = convertirPoucesEnInt(largeurListe);

                //Hauteur
                Pouces hauteurListe = porte.getHauteur();
                hauteurListe = hauteurListe.addPouces(ValidationTroisPouces);
                int hauteurPorte = convertirPoucesEnInt(hauteurListe);
                System.out.println(porte + "PorteDeListePorte");

                List<Point> listePointsPorte = determinerSommetsAccessoires(mousePointFenetre,largeurPorte,hauteurPorte);

                Point SupGaucheListePorte = listePointsPorte.get(0);
                Point SupDroitListePorte = listePointsPorte.get(1);
                Point InfGaucheListePorte = listePointsPorte.get(2);
                Point InfDroitListePorte = listePointsPorte.get(3);

            /*System.out.println(SupGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Haut a Gauche ");
            System.out.println(InfGaucheListeFenetre+"(determinerSommetsAccessoires) listePorte En Bas a Gauche ");
            System.out.println(InfDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Bas a Droite ");
            System.out.println(SupDroitListeFenetre+"(determinerSommetsAccessoires)  listePorte En Haut a Droite "); */

                boolean PointUnRect = estDansRectangleAcc(SupGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                boolean PointDeuxRect = estDansRectangleAcc(SupDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte) ;
                boolean PointTroisRect = estDansRectangleAcc(InfGaucheFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte) ;
                boolean PointQuatreRect = estDansRectangleAcc(InfDroitFenetre, SupGaucheListePorte, SupDroitListePorte, InfGaucheListePorte, InfDroitListePorte);
                //boolean collision = PointUnRect && PointDeuxRect && PointTroisRect && PointQuatreRect;

            /* System.out.println(PointUnRect+"(SupGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointDeuxRect+"(SupDroitFenetre est dans rectangle ?) ");
            System.out.println(PointTroisRect+"(InfGaucheFenetre est dans rectangle ?) ");
            System.out.println(PointQuatreRect+"(InfDroitFenetre est dans rectangle ?) "); */

                if( PointUnRect || PointDeuxRect || PointTroisRect || PointQuatreRect )
                {
                    System.out.println(true+"(AntiCollisionPorte) ");

                    return true;

                }

            }

        }

        System.out.println(false+"(AntiCollisionPorte) ");
        return false;


    }


    public static boolean ajouterPorte(Point mousepoint, String nomMur, List<Mur> listeMursDrawer,Dimension initialDimension){
        Pouces largeur = new Pouces(35, 0, 1);
        Pouces hauteur = new Pouces(60, 0, 1);

        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);

        //Une porte par mur
        List<Porte> listePorte = mur.getListePorte();
        boolean Anticollision = AntiCollisionAccessoireMur(mur,mousepoint,largeur,hauteur,initialDimension);

        if(Anticollision)
        {


            if(AntiCollisionFenetrePorte(mur,mousepoint,largeur,largeur) == false){

                if(Anticollision && listePorte != null) {
                    int lenghtlistePorte = listePorte.size();
                    if (lenghtlistePorte > 0) {
                        mur.clearListePorte();
                    }
                }

                Porte porte = new Porte(mousepoint,largeur, hauteur );
                boolean success = mur.ajouterPorte(porte);
                //System.out.println(porte+"(ajouterPorte) Porte ajoute");
                return success;
            }


        }

        /*listePorte = mur.getListePorte();
        for (Porte porte1 : listePorte) {
            System.out.println(porte1);
            return success;
        } */

        return false;


    }

    public static boolean supprimerPorte(String nomMur, List<Mur> listeMursDrawer){


        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Porte> listePorte = mur.getListePorte();

        if(listePorte != null) {

            int lenghtlistePorte = listePorte.size();

            if (lenghtlistePorte > 0) {
                mur.clearListePorte();
            }
        }

        return false;

    }

    public static boolean setLargeurPorte(Pouces nouvelleLargeur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension){

        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {

            if(AntiCollisionAccessoireMur(mur,porte.mousePoint,nouvelleLargeur,porte.hauteur,initialDimension))
            {
                if(AntiCollisionPorteModification(mur,porte,porte.mousePoint,nouvelleLargeur,porte.hauteur) == false){

                    boolean success = porte.setLargeurPorte(nouvelleLargeur);
                    System.out.println(porte + "Largeur de la Porte Modifie ");
                    return success;

                }


            }


        }


        return false;

    }


    public static boolean ajouterFenetre(Point mousepoint, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension){

        Pouces largeur = new Pouces(25, 0, 1);
        Pouces hauteur = new Pouces(25, 0, 1);

        int numMur = determinerMur(nomMur);

        Mur mur = listeMursDrawer.get(numMur);
        List<Fenetre> listeFenetre = mur.getListeFenetre();

        if(listeFenetre != null) {
            int lenghtlisteFenetre = listeFenetre.size();

            if(lenghtlisteFenetre > 5){
                mur.clearListeFenetre();
            }

        }

        if(AntiCollisionAccessoireMur(mur,mousepoint,largeur,largeur,initialDimension))
        {
            if(!AntiCollisionFenetrePorte(mur, mousepoint, largeur, largeur)){

                Fenetre Fenetre = new Fenetre(mousepoint,largeur,hauteur);
                boolean success = mur.ajouterFenetre(Fenetre);
                //System.out.println(Fenetre+"(ajouterFenetre) Fenetre ajoute");
                return success;

            }

        }

        return false;
    }

    // Fonction qui sera utile pour le Drag
    public static boolean selectionPorte(Porte porte, Point mousePointClicked){
        int largeurPorte = convertirPoucesEnInt(porte.getLargeur());
        int hauteurPorte = convertirPoucesEnInt(porte.getHauteur());

        // On determine les sommets de la porte.. Pas sur verifier demain.
        Point coinSuperieurGauchePorte = porte.mousePoint;
        Point coinSuperieurDroitPorte = new Point(coinSuperieurGauchePorte.x + largeurPorte, coinSuperieurGauchePorte.y);
        Point coinInferieurGauchePorte = new Point(coinSuperieurGauchePorte.x, coinSuperieurGauchePorte.y + hauteurPorte);
        Point coinInferieurDroitPorte = new Point(coinSuperieurGauchePorte.x + largeurPorte, coinSuperieurGauchePorte.y + hauteurPorte);

        // Verifier si le mousePointClicked se trouve entre les 4 sommets de la porte
        if (mousePointClicked.getX() >= coinSuperieurGauchePorte.getX() && mousePointClicked.getX() <= coinSuperieurDroitPorte.getX() &&
                mousePointClicked.getY() >= coinSuperieurGauchePorte.getY() && mousePointClicked.getY() <= coinInferieurGauchePorte.getY() ) {
            return true;
        }
        return false;

    }


    public static boolean selectionFenetre(Fenetre fenetre, Point mousePointClicked){
        //On récupere les mesures de la fenetres
        int largeur = convertirPoucesEnInt(fenetre.largeur);
        int hauteur = convertirPoucesEnInt(fenetre.hauteur);

        //On determine les sommets de la fenetres
        Point coinSuperieurGauche = fenetre.mousePoint;
        Point coinSuperieurDroit = new Point(coinSuperieurGauche.x + largeur, coinSuperieurGauche.y);
        Point coinInferieurGauche = new Point(coinSuperieurGauche.x, coinSuperieurGauche.y + hauteur);
        Point coinInferieurDroit = new Point(coinSuperieurGauche.x + largeur, coinSuperieurGauche.y + hauteur);

        //On verifie si le mousePointClicked se trouvemt entres les 4 sommets
        if( mousePointClicked.getX() >= coinSuperieurGauche.getX() && mousePointClicked.getX() <= coinSuperieurDroit.getX() &&
                mousePointClicked.getY() >= coinSuperieurGauche.getY() && mousePointClicked.getY() <= coinInferieurGauche.getY() ) {
            return true;
        }
        return false;
    }

    public static boolean modifierXporte(Point mousePointClicked, int nouveauXporteint, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension) {
        boolean modificationXreussieporte = false;
        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {
            mousePointClicked.setLocation(nouveauXporteint, porte.mousePoint.getY());
            //Point mousesPointClicked = new Point(nouveauXporteint, (int) porte.mousePoint.getY());
            if (AntiCollisionAccessoireMur(mur, mousePointClicked, porte.largeur, porte.hauteur, initialDimension)) {
                if(AntiCollisionPorteModification(mur, porte,mousePointClicked, porte.largeur, porte.hauteur) == false) {

                    modificationXreussieporte = porte.setPoint(mousePointClicked);
                    System.out.println(porte + "X de la Porte Modifie ");
                    return modificationXreussieporte;
                }
            }
        }
        return false;

    }

    public static boolean modifierXfenetre(Point mousePointClicked, int nouveauXfenetreint, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension) {
        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Fenetre> listeFenetre = mur.getListeFenetre();
        System.out.println("modifierXfenetre Chalet");

        for (Fenetre fenetre : listeFenetre) {
            System.out.println("fenetreExistante"+fenetre);

            boolean fenetreTrouve = selectionFenetre(fenetre, mousePointClicked);
            System.out.println("fenetreTrouve"+fenetreTrouve);

            if (fenetreTrouve == true) {
                Point nouveauMousePoint = new Point(nouveauXfenetreint, (int) fenetre.mousePoint.y);
                System.out.println("nouveauMousePoint"+nouveauMousePoint);
                //mousePointClicked.setLocation(nouveauXfenetreint, fenetre.mousePoint.getY());
                if (AntiCollisionAccessoireMur(mur, nouveauMousePoint, fenetre.largeur, fenetre.hauteur, initialDimension)) {
                    System.out.println("AntiCollisionAccessoireMur Passé");
                    if(AntiCollisionFenetreModification(mur, fenetre,nouveauMousePoint, fenetre.largeur, fenetre.hauteur) == false) {
                        System.out.println("AntiCollisionFenetreModification Passé");
                        boolean modificationXreussiefenetre = fenetre.setPoint(nouveauMousePoint);
                        System.out.println(fenetre + "X de la Fenetre Modifie ");
                        return modificationXreussiefenetre;
                    }
                }
            }
        }
        return false;

    }

    public static boolean modifierYfenetre(Point mousePointClicked, int nouveauYfenetreint, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension) {
        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);

        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {
            boolean fenetreTrouve = selectionFenetre(fenetre, mousePointClicked);
            if (fenetreTrouve == true) {
                //mousePointClicked.setLocation(fenetre.mousePoint.getX(), nouveauYfenetreint);
                Point nouveauMousesPointClicked = new Point(nouveauYfenetreint, (int) fenetre.mousePoint.y);
                if (AntiCollisionAccessoireMur(mur, nouveauMousesPointClicked, fenetre.largeur, fenetre.hauteur, initialDimension)) {
                    if(AntiCollisionFenetreModification(mur, fenetre,nouveauMousesPointClicked, fenetre.largeur, fenetre.hauteur) == false) {
                        boolean modificationYreussiefenetre = fenetre.setPoint(nouveauMousesPointClicked);
                        System.out.println(fenetre + "Y de la Fenetre Modifie ");
                        return modificationYreussiefenetre;
                    }
                }
            }
        }
        return false;

    }

    public static boolean supprimerFenetre(Point mousePointClicked,String nomMur, List<Mur> listeMursDrawer){


        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Fenetre> listeFenetre = mur.getListeFenetre();
        int i = 0;
        for (Fenetre fenetre : listeFenetre) {

            boolean fenetreTrouve = selectionFenetre(fenetre,mousePointClicked);
            if(fenetreTrouve == true){

                listeFenetre.remove(i);
                System.out.println(fenetre + "(supprimerFenetre) La fenetre supprimer en question");
                return true;

            }
            i= i + 1;

        }

        if(listeFenetre != null) {

            int lenghtlisteFenetre = listeFenetre.size();

            if (lenghtlisteFenetre > 0) {
                mur.clearListeFenetre();
            }
        }

        return false;

    }

    public static boolean supprimerToutesFenetre(String nomMur, List<Mur> listeMursDrawer){


        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Fenetre> listeFenetre = mur.getListeFenetre();
        listeFenetre.clear();


        return true;

    }

    public static boolean setHauteurFenetre(Point mousePointClicked, Pouces nouvelleHauteur, String nomMur, List<Mur> listeMursDrawer,Dimension initialDimension){

        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Fenetre> listeFenetre = mur.getListeFenetre();


        for (Fenetre fenetre : listeFenetre) {

            boolean fenetreTrouve = selectionFenetre(fenetre,mousePointClicked);
            if(fenetreTrouve == true){

                if(AntiCollisionAccessoireMur(mur,fenetre.mousePoint,fenetre.largeur,nouvelleHauteur,initialDimension))
                {

                    if(AntiCollisionFenetreModification(mur,fenetre,fenetre.mousePoint,fenetre.largeur,nouvelleHauteur) == false){

                        boolean success = fenetre.setHauteurFenetre(nouvelleHauteur);
                        System.out.println(fenetre + "Hauteur de la Fenetre Modifie ");
                        return success;

                    }

                }



            }


        }

        return false;

    }


    public static boolean setLargeurFenetre(Point mousePointClicked,Pouces nouvelleLargeur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension){

        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {

            boolean fenetreTrouve = selectionFenetre(fenetre,mousePointClicked);
            if(fenetreTrouve == true){
                if(AntiCollisionAccessoireMur(mur,fenetre.mousePoint,nouvelleLargeur,fenetre.hauteur,initialDimension))
                {
                    if(AntiCollisionFenetreModification(mur,fenetre,fenetre.mousePoint,nouvelleLargeur,fenetre.hauteur) == false){

                        boolean success = fenetre.setLargeurFenetre(nouvelleLargeur);
                        System.out.println(fenetre + "Largeur de la Fenetre Modifie ");
                        return success;

                    }

                }


            }

        }

        return false;

    }
    public static boolean setHauteurPorte(Pouces nouvelleHauteur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension){

        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {
            if(AntiCollisionAccessoireMur(mur,porte.mousePoint,porte.largeur,nouvelleHauteur,initialDimension))
            {

                if(AntiCollisionPorteModification(mur,porte,porte.mousePoint,porte.largeur,nouvelleHauteur) == false){

                    boolean success = porte.setHauteurPorte(nouvelleHauteur);
                    System.out.println(porte + "Hauteur de la Porte Modifie ");
                    return success;


                }


            }

        }

        return false;

    }



    public double getLargeurChalet() {
        return largeurChalet;
    }

    public double getHauteurMurs() {
        return hauteurMurs;
    }

    public double getLongueurChalet() {
        return longueurChalet;
    }

    public double getEpaisseurChalet() {
        return epaisseurChalet;
    }

    public double getAngleToit() {
        return this.angleToit;
    }

    public List<Mur> getListeMurs() {
        return this.listeMurs;
    }


    public static void setLargeurChalet(double largeurChaletMN) {
        largeurChalet = largeurChaletMN;
        System.out.println(largeurChaletMN); //test
        System.out.println(largeurChalet + " is the new value of largeur in Chalet.java"); //test
    }

    public static void setHauteurMurs(double hauteurMursMN) {
        hauteurMurs = hauteurMursMN;
    }

    public static void setLongueurChalet(double longueurChaletMN) {
        longueurChalet = longueurChaletMN;
        System.out.println(longueurChaletMN); //test
        System.out.println(longueurChalet + " is the new value in Chalet.java"); //test


    }

    public static void setEpaisseurChalet(double epaisseurChaletMN) {
        epaisseurChalet = epaisseurChaletMN;
    }
    public static void setRetraitChalet(double retraitChaletMN) {
        retraitChalet = retraitChaletMN;
        System.out.println(retraitChalet + " is the new value of retrait in Chalet.java"); //test

    }

    public void setAngleToit(double angleToit) {
        angleToit = angleToit;
    }

    public void setListeMurs(List<Mur> listerMurs) {
        this.listeMurs = listeMurs;
    }

    public static void setOrientation(String orientation)
    {
        Chalet.orientationToit = orientation ;
        System.out.println(orientation + " is the new value of orientation in TOIT.java"); //test

    }
    public static double getZoom() {
        return zoom;
    }
    public static float getOffsetX() {
        return offsetX;
    }

    public static float getOffsetY() {
        return offsetY;
    }
    public static void setZoom(double leZoom)
    {
        zoom = leZoom;
    }
    public static void setOffsetX(float leOffsetX)
    {
        offsetX = leOffsetX;
    }
    public static void setOffsetY(float leOffsetY)
    {
        offsetY = leOffsetY;
    }
}
