package domain;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;

import java.awt.*;
import java.util.List;
import java.util.*;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnInt;
import static java.util.Arrays.asList;

public class Chalet {

    public static double largeurChalet = 400;
    public static double longueurChalet = 300;
    public static double hauteurMurs=2*80;
    public static double epaisseurChalet = 2*20;
    public static double angleToit;
    public static List<Mur> listeMurs; //ex: listeMurs  = [Mur n, Mur w, Mur e, Mur s]
    public static String orientationToit;

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
    }



    public void initialiserMurFacade(){

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


    public void ResetinitialiserMurFacade(){

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




    public void initialiserMurArriere(){

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

    public void initialiserMurGauche(){

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

    public void initialiserMurDroite(){

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
                    mur.createSommet(mur, new PointDouble(0,(getEpaisseurChalet()/2) - distanceUsinage)); // creer Point(0, 1.3) rainure exterieur gauche
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2) + distanceUsinage, (getEpaisseurChalet()/2) - distanceUsinage)); // creer Point(1.7, 1.3) rainure interieur gauche
                    mur.getSommetsMur().get(1).setLocation((getEpaisseurChalet()/2) + distanceUsinage, getEpaisseurChalet()); //B: SupGauche // Point(0, 3.0) devient Point(1.7, 3.0)

                    mur.getSommetsMur().get(2).setLocation(longueurChalet-(epaisseurChalet/2) - distanceUsinage , getEpaisseurChalet()); //C: SupDroite // Point(10.0, 3.0) devient Point(9.8, 3.0)
                    mur.createSommet(mur, new PointDouble(longueurChalet-(epaisseurChalet/2) - distanceUsinage, (getEpaisseurChalet()/2) - distanceUsinage)); // creer Point(8.3, 1.7) rainure interieur droite
                    mur.createSommet(mur, new PointDouble(longueurChalet, (epaisseurChalet/2) - distanceUsinage)); // creer Point(10.0, 1.7) rainure exterieur droite ne bouge pas
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
                    mur.getSommetsMur().get(0).setLocation((getEpaisseurChalet()/2) + distanceUsinage, getLargeurChalet() - (getEpaisseurChalet())); //A: SupGauche // Point(0, 10.0) reste Point(0, 10.0)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2) + distanceUsinage, (getLargeurChalet() - (getEpaisseurChalet()/2) + distanceUsinage))); // creer Point(0, 8.7)
                    mur.createSommet(mur, new PointDouble(0, (getLargeurChalet()-(getEpaisseurChalet()/2) + distanceUsinage))); // creer Point(1.7, 8.7)
                    mur.getSommetsMur().get(1).setLocation(0, getLargeurChalet()); //B: (0, 10) reste (0, 10)

                    mur.getSommetsMur().get(2).setLocation(getLongueurChalet(), getLargeurChalet()); //C: InfDroite // Point(10.0, 7.0) devient Point(8,3, 7.0)
                    mur.createSommet(mur, new PointDouble(getLongueurChalet(), getLargeurChalet() - (getEpaisseurChalet()/2) + distanceUsinage)); // creer Point(8.3, 8.7)
                    mur.createSommet(mur, new PointDouble(getLongueurChalet() - (getEpaisseurChalet()/2) - distanceUsinage, getLargeurChalet() - (getEpaisseurChalet()/2) + distanceUsinage)); // creer Point(10.0, 8.7)
                    mur.getSommetsMur().get(3).setLocation(getLongueurChalet() - (getEpaisseurChalet()/2) - distanceUsinage, getLargeurChalet() - getEpaisseurChalet()); //D: SupDroite // Point(10.0, 10.0) reste Point(10.0, 10.0)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation(0, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation(0, getHauteurMurs()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(getLongueurChalet(), getHauteurMurs()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(getLongueurChalet(), getHauteurMurs()); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Gauche")) {
                    // final points (0, 1.7), (1.3, 1.7), (1.3, 3.2), (3.0, 3.2), (0, 8.3), (1.3, 8.3), (1.3, 7.6), (3.0, 7.6)
                    //initial points:  0:(0, 0)  1:(0, 10)  2: (3, 10)  3: (3, 0)
                    mur.getSommetsMur().get(0).setLocation(0, (getEpaisseurChalet()/2)); //A: InfGauche // Point(0, 0) devient Point(0, 1.5)
                    mur.getSommetsMur().get(1).setLocation(0, (getLargeurChalet() - (getEpaisseurChalet()/2))); //B: SupGauche // Point(0, 10) devient Point(0,9)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), (getLargeurChalet() - (getEpaisseurChalet()/2)))); // creer Point(1.5, 9)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), (getLargeurChalet() - getEpaisseurChalet()))); // creer Point(1.5, 8)

                    mur.getSommetsMur().get(2).setLocation(getEpaisseurChalet(), (getLargeurChalet() - getEpaisseurChalet())); //C: SupDroite // Point(2, 10) devient Point(2, 8)
                    mur.getSommetsMur().get(3).setLocation(getEpaisseurChalet(), getEpaisseurChalet()); //D: InfDroite // Point(2, 0) devient Point(2, 2)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), getEpaisseurChalet())); // creer Point(1.5, 2)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), (getEpaisseurChalet()/2))); // creer Point(1.5, 1.5)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation((getEpaisseurChalet()/2) + distanceUsinage, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation((getEpaisseurChalet()/2) + distanceUsinage, getHauteurMurs()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(getLargeurChalet() - (getEpaisseurChalet()/2) - distanceUsinage, getHauteurMurs()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(getLargeurChalet() - (getEpaisseurChalet()/2) - distanceUsinage, 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if(Objects.equals(mur.getNomMur(), "Droite")){
                    //initial points: A  0:(7.0, 0)  B  1:(7.0, 10)  C  2:(10.0, 10.0)  D  3:(10.0, 0)
                    //final points: A:(7.0, 3.2)  B:(7.0, 6.8)  E:(8.7, 6.8)  F:(8.7, 8.3)   C:(10, 8.3)  D:(10.0, 1.7)  G:(8.7, 1.7)  H:(8.7, 3.2)
                    mur.getSommetsMur().get(0).setLocation((getLongueurChalet() - getEpaisseurChalet()), getEpaisseurChalet()); //A: InfGauche // Point(7.0, 0) devient Point(8,2)
                    mur.getSommetsMur().get(1).setLocation((getLongueurChalet() - getEpaisseurChalet()), (getLargeurChalet() - getEpaisseurChalet())); //B: SupGauche // Point(7.0, 10.0) devient Point(8, 8)
                    mur.createSommet(mur, new PointDouble((getLongueurChalet() - (getEpaisseurChalet()/2)), (getLargeurChalet() - getEpaisseurChalet()))); // E creer Point(9, 8)
                    mur.createSommet(mur, new PointDouble((getLongueurChalet() - (getEpaisseurChalet()/2)), ((getLargeurChalet() - (getEpaisseurChalet()/2))))); // F creer Point(9, 9)

                    mur.getSommetsMur().get(2).setLocation(getLongueurChalet(), (getLargeurChalet() - (getEpaisseurChalet()/2))); //C: SupDroite // Point(10.0, 10.0) devient Point(10.0, 9)
                    mur.getSommetsMur().get(3).setLocation(getLongueurChalet(), (getEpaisseurChalet()/2)); //D: InfDroite // Point(10.0, 0) devient Point(10.0, 1)
                    mur.createSommet(mur, new PointDouble((getLongueurChalet() - (getEpaisseurChalet()/2)), (getEpaisseurChalet()/2))); // G creer Point(9, 1)
                    mur.createSommet(mur, new PointDouble((getLongueurChalet() - (getEpaisseurChalet()/2)), getEpaisseurChalet())); // H creer Point(9, 2)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation(getEpaisseurChalet()/2, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation(getEpaisseurChalet()/2, getHauteurMurs()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(getLargeurChalet() - (getEpaisseurChalet()/2), getHauteurMurs()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(getLargeurChalet() - (getEpaisseurChalet()/2), 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)
                    mursDecoupes.add(mur);
                }
            }
            System.out.println("Liste des murs avec rainures: " + mursDecoupes);
        }
        if(Objects.equals(orientationToit, "Ouest") || Objects.equals(orientationToit, "Est")){
            System.out.println("Orientation toit est Est OU Ouest, dans ce cas: " + orientationToit);
            /*for (Mur mur : listeDeMursARainurer){// it's gonna be the same as North or South, but with the Mur Gauche and Droite are the ones that are longer than Facade and Arriere
                if(mur.getNomMur() == "Gauche"){

                    mur.getSommetsMur().get(0).setLocation(mur.getSommetsMur().get(0).getX(), mur.getSommetsMur().get(0).getY()); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(1).setLocation(mur.getSommetsMur().get(1).getX(), mur.getSommetsMur().get(1).getY()); //B: SupGauche // Point(0, 10) reste Point(0, 10)
                    mur.getSommetsMur().get(2);
                    mur.getSommetsMur().get(3);

                }
            }*/

        }
    }

    public List<Mur> getMursUsines(double distanceUsinage, String orientationToit) {
        retirerRainures(listeMurs, distanceUsinage, orientationToit);
        return listeMurs;
    }


    /*public int DeterminerPointMurLargeur(int numMur,int w,int h) {

        //Largeur & Hauteur PORTE
        Pouces largeur = Porte.PORTE_LARGEUR_STANDARD;
        Pouces hauteur = Porte.PORTE_HAUTEUR_STANDARD;

        Mur mur = listeMurs.get(numMur);

        //J'ai la largeur et la hauteur du panel. Pour determiner les coordonnées du mur de base je dois me baser sur cela.
        //MUR POUCES
        Pouces largeurMur = convertirDoubleEnPouces(largeurChalet);
        Pouces hauteurMur = convertirDoubleEnPouces(hauteurMurs);

        //MUR DIVISER 2 POUCES
        int w1 = convertirPoucesEnPixels(largeurMur);
        int h1 = convertirPoucesEnPixels(hauteurMur);

        //MUR DIVISER 2
        int w2 = w1/2;
        int h2 = h1/2;

        //LARGEUR PANEL DIVISER 2 - POINT MILIEU - ENTIER
        int PointMurMilieuX = w/2;
        int PointMurMilieuY = h/2;

        //Pouces pointMurX = PointMurMilieuX.substractPouces(w1);
        //Pouces pointMurY = PointMurMilieuY.substractPouces(h1);

        //POINT MUR
        int pointMurX = PointMurMilieuX - w2;
        int pointMurY = PointMurMilieuY - h2;

        Pouces pointMurPouceX = convertirPixelsEnPouces(pointMurX);
        Pouces pointMurPouceY = convertirPixelsEnPouces(pointMurY);


        return(pointMurY);

    } */
    public static int determinerMur(String nomMur){

        int numMur = 0;

        if (nomMur == "FACADE") {
            numMur = 0;
        }
        if (nomMur == "ARRIERE") {
            numMur = 1;

        }
        if(nomMur == "DROITE" ) {
            numMur = 3;

        }
        if (nomMur == "GAUCHE") {
            numMur = 2;

        }
        return numMur;

    }


    public static List<Point> DeterminerCollisionSommetsMur(Mur mur, Dimension initialDimension){

        //List<PointDouble> sommetsMur = mur.getSommetsMur();


        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        PointDouble pointSupDroitfc = mur.getSommetsMur().get(4);
        PointDouble pointSupGauchefc = mur.getSommetsMur().get(5);
        PointDouble pointInfDroitfc = mur.getSommetsMur().get(6);
        PointDouble pointInfGauchefc = mur.getSommetsMur().get(7);

        double positionX = width/2 - pointInfDroitfc.getX()/2;
        double positionY = height/2- pointInfDroitfc.getY()/2;

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
        /* System.out.println(x1fc+" En Haut a Gauche "+y1fc);
        System.out.println(x2fc+" En Bas a Gauche "+y2fc);
        System.out.println(x3fc+" En Bas a Droite "+y3fc);
        System.out.println(x4fc+" En Haut a Droite "+y4fc); */

        Point SupGauche = new Point(x1fc,y1fc);
        Point SupDroite = new Point(x4fc,y4fc);
        Point InfGauche = new Point(x2fc,y2fc);
        Point InfDroite = new Point(x3fc,y3fc);


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
        boolean conditionDeux = x >= x2 ;
        boolean conditionTrois = y >= y1;
        boolean conditionQuatre = y <= y3;
        System.out.println(conditionUn +""+  conditionDeux +""+  conditionTrois +""+  conditionQuatre +"Les conditions" );


        // Vérifie si le point se trouve à l'intérieur du rectangle
        boolean estDansRectangle = (conditionUn &&  conditionDeux && conditionTrois && conditionQuatre);

        System.out.println(estDansRectangle + "(estDansRectangle) " + point);

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
        System.out.println(SupGaucheMur+"(DeterminerCollisionSommetsMur) Mur En Haut a Gauche ");
        System.out.println(InfGaucheMur+"(DeterminerCollisionSommetsMur) Mur En Bas a Gauche ");
        System.out.println(InfDroiteMur+"(DeterminerCollisionSommetsMur) Mur En Bas a Droite ");
        System.out.println(SupDroiteMur+"(DeterminerCollisionSommetsMur) Mur En Haut a Droite ");



        //On determine les sommets de la fenetres
        Point SupGaucheFenetre = mousePoint;
        Point SupDroitFenetre = new Point(SupGaucheFenetre.x + largeur, SupGaucheFenetre.y);
        Point InfGaucheFenetre = new Point(SupGaucheFenetre.x, SupGaucheFenetre.y + hauteur);
        Point InfDroitFenetre = new Point(SupGaucheFenetre.x + largeur, SupGaucheFenetre.y + hauteur);
        System.out.println(SupGaucheFenetre+"(AntiCollisionFenetreMur) Fenetre En Haut a Gauche ");
        System.out.println(InfGaucheFenetre+"(AntiCollisionFenetreMur) Fenetre En Bas a Gauche ");
        System.out.println(InfDroitFenetre+"(AntiCollisionFenetreMur) Fenetre En Bas a Droite ");
        System.out.println(SupDroitFenetre+"(AntiCollisionFenetreMur) Fenetre En Haut a Droite ");

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

            if(Anticollision && listePorte != null) {
                int lenghtlistePorte = listePorte.size();
                if (lenghtlistePorte > 0) {
                    mur.clearListePorte();
                }
            }

            Porte porte = new Porte(mousepoint,largeur, hauteur );
            boolean success = mur.ajouterPorte(porte);
            System.out.println(porte+"(ajouterPorte) Porte ajoute");
            return success;

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

                boolean success = porte.setLargeurPorte(nouvelleLargeur);
                System.out.println(porte + "Largeur de la Porte Modifie ");
                return success;

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

        if(AntiCollisionAccessoireMur(mur,mousepoint,largeur,hauteur,initialDimension))
        {
            Fenetre Fenetre = new Fenetre(mousepoint,largeur,hauteur);
            boolean success = mur.ajouterFenetre(Fenetre);
            System.out.println(Fenetre+"(ajouterFenetre) Fenetre ajoute");
            return success;

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
    public static boolean modifierXporte (Point mousePointClicked, int nouveauXporteint, String nomMur, List <Mur> listeMursDrawer,Dimension initialDimension ) {
        boolean modificationXreussieporte = false;
        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Porte> listePorte = mur.getListePorte();

        for (Porte porte : listePorte) {
            mousePointClicked.setLocation(nouveauXporteint, porte.mousePoint.getY());
            //Point mousesPointClicked = new Point(nouveauXporteint, (int) porte.mousePoint.getY());
            if (AntiCollisionAccessoireMur(mur, mousePointClicked, porte.largeur, porte.hauteur, initialDimension)) {
                modificationXreussieporte = porte.setPoint(mousePointClicked);
                System.out.println(porte + "X de la Porte Modifie ");
                return modificationXreussieporte;
            }
        }
        return false;

    }
    // Rajout de la methode modifierXfenetre qui considere le cas echeant on selectionne une fenetre
    public static boolean modifierXfenetre (Point mousePointClicked, int nouveauXfenetreint, String nomMur, List <Mur> listeMursDrawer,Dimension initialDimension ) {
        boolean modificationXreussiefenetre = false;
        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {
            boolean fenetreTrouve = selectionFenetre(fenetre,mousePointClicked);
            if(fenetreTrouve == true){
            mousePointClicked.setLocation(nouveauXfenetreint,fenetre.mousePoint.getY());
            //Point mousesPointClicked = new Point(nouveauXporteint, (int) porte.mousePoint.getY());
            if (AntiCollisionAccessoireMur(mur, mousePointClicked, fenetre.largeur, fenetre.hauteur, initialDimension)) {
                modificationXreussiefenetre = fenetre.setPoint(mousePointClicked);
                System.out.println(fenetre + "Y de la Fenetre Modifie ");
                return modificationXreussiefenetre;
            }
        }
        }
        return false;

    }

    public static boolean modifierYfenetre (Point mousePointClicked,int nouveauYfenetreint,String nomMur,List <Mur> listeMursDrawer,Dimension initialDimension ) {
        boolean modificationYreussiefenetre = false;
        int numMur = determinerMur(nomMur);
        Mur mur = listeMursDrawer.get(numMur);
        //Une porte par mur
        List<Fenetre> listeFenetre = mur.getListeFenetre();

        for (Fenetre fenetre : listeFenetre) {
            boolean fenetreTrouve = selectionFenetre(fenetre,mousePointClicked);
            if(fenetreTrouve == true){
            mousePointClicked.setLocation(fenetre.mousePoint.getX(),nouveauYfenetreint);
            //Point mousesPointClicked = new Point(nouveauXporteint, (int) porte.mousePoint.getY());
            if (AntiCollisionAccessoireMur(mur, mousePointClicked, fenetre.largeur, fenetre.hauteur, initialDimension)) {
                modificationYreussiefenetre = fenetre.setPoint(mousePointClicked);
                System.out.println(fenetre + "Y de la Fenetre Modifie ");
                return modificationYreussiefenetre;
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

                    boolean success = fenetre.setHauteurFenetre(nouvelleHauteur);
                    System.out.println(fenetre + "Hauteur de la Fenetre Modifie ");
                    return success;

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

                    boolean success = fenetre.setLargeurFenetre(nouvelleLargeur);
                    System.out.println(fenetre + "Largeur de la Fenetre Modifie ");
                    return success;

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

                boolean success = porte.setHauteurPorte(nouvelleHauteur);
                System.out.println(porte + "Hauteur de la Porte Modifie ");
                return success;

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

    public String getOrientationToit() {
        return this.orientationToit;
    }

    public static void setLargeurChalet(double largeurChaletMN) {
        largeurChalet = largeurChaletMN;
        System.out.println(largeurChaletMN); //test
        System.out.println(largeurChalet+" is the new value of largeur in Chalet.java"); //test



    }

    public static void setHauteurMurs(double hauteurMursMN) {
        hauteurMurs = hauteurMursMN;
    }

    public static void setLongueurChalet(double longueurChaletMN) {
        longueurChalet = longueurChaletMN;
        System.out.println(longueurChaletMN); //test
        System.out.println(longueurChalet+" is the new value in Chalet.java"); //test


    }

    public static void setEpaisseurChalet(double epaisseurChaletMN) {
        epaisseurChalet = epaisseurChaletMN;
    }

    public void setAngleToit(double angleToit) {
        angleToit = angleToit;
    }

    public void setListeMurs(List<Mur> listerMurs) {
        this.listeMurs = listeMurs;
    }

    public void setOrientationToit(String orientationToit) {
        this.orientationToit = orientationToit;
    }
}
