package domain;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static Utilitaires.ConvertisseurMesures.*;

public class Chalet {

    public static double largeurChalet = 10.0;
    public static double longueurChalet = 10.0;
    public static double hauteurMurs=8.0;
    public static double epaisseurChalet = 2.0;
    public static double angleToit;
    public static List<Mur> listeMurs; //ex: listeMurs  = [Mur n, Mur w, Mur e, Mur s]
    public static List<Mur> listeMursPourAcc;
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
        PointDouble pointSupGauche = new PointDouble(0, getEpaisseurChalet());
        PointDouble pointSupDroit = new PointDouble(getLongueurChalet(), getEpaisseurChalet());
        PointDouble pointInfDroit = new PointDouble(getLongueurChalet(), 0);

        //Points de face
        PointDouble pointInfGaucheFace = new PointDouble(0, 0);
        PointDouble pointSupGaucheFace = new PointDouble(0, getHauteurMurs());
        PointDouble pointSupDroitFace = new PointDouble(getLongueurChalet(), getHauteurMurs());
        PointDouble pointInfDroitFace = new PointDouble(getLongueurChalet(), 0);

        Mur facade = new Mur("Facade", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new ArrayList<String>());

        listeMurs.add(facade);
    }

    public void initialiserMurArriere(){

        //points de haut
        PointDouble pointInfGauche = new PointDouble(0, getLargeurChalet() - getEpaisseurChalet());
        PointDouble pointSupGauche = new PointDouble(0, getLargeurChalet());
        PointDouble pointSupDroit = new PointDouble(getLongueurChalet(), getLargeurChalet());
        PointDouble pointInfDroit = new PointDouble(getLongueurChalet(), getLargeurChalet() - getEpaisseurChalet());

        //points de face
        //Ici il y a une twist: on a besoin de creer les points de l'arriere, mais on a besoin de les creer dans le sens inverse
        PointDouble pointInfGaucheArriere = new PointDouble(getLongueurChalet(), 0); // Meme chose que mur de face mais en points inverses!
        PointDouble pointSupGaucheArriere = new PointDouble(getLongueurChalet(), getHauteurMurs());
        PointDouble pointSupDroitArriere = new PointDouble(0, getHauteurMurs());
        PointDouble pointInfDroitArriere = new PointDouble(0, 0);

        Mur arriere = new Mur("Arriere", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheArriere, pointSupGaucheArriere, pointSupDroitArriere, pointInfDroitArriere), new ArrayList<String>());

        listeMurs.add(arriere);
    }

    public void initialiserMurGauche(){

        //points de haut
        PointDouble pointInfGauche = new PointDouble(0, 0);
        PointDouble pointSupGauche = new PointDouble(0, getLargeurChalet());
        PointDouble pointSupDroit = new PointDouble(getEpaisseurChalet(), getLargeurChalet());
        PointDouble pointInfDroit = new PointDouble(getEpaisseurChalet(), 0);

        //points de face
        PointDouble pointInfGaucheFace = new PointDouble(0, 0);
        PointDouble pointSupGaucheFace = new PointDouble(0, getHauteurMurs());
        PointDouble pointSupDroitFace = new PointDouble(getLargeurChalet(), getHauteurMurs());
        PointDouble pointInfDroitFace = new PointDouble(getLargeurChalet(), 0);

        Mur gauche = new Mur("Gauche", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new ArrayList<String>());

        listeMurs.add(gauche);
    }


    public void initialiserMurDroite(){

        //points de haut
        PointDouble pointInfGauche = new PointDouble(getLongueurChalet() - getEpaisseurChalet(), 0);
        PointDouble pointSupGauche = new PointDouble(getLongueurChalet() - getEpaisseurChalet(), getLargeurChalet());
        PointDouble pointSupDroit = new PointDouble(getLongueurChalet(), getLargeurChalet());
        PointDouble pointInfDroit = new PointDouble(getLongueurChalet(), 0);

        //points de face
        PointDouble pointInfGaucheFace = new PointDouble(0, 0);
        PointDouble pointSupGaucheFace = new PointDouble(0, getHauteurMurs());
        PointDouble pointSupDroitFace = new PointDouble(getLargeurChalet(), getHauteurMurs());
        PointDouble pointInfDroitFace = new PointDouble(getLargeurChalet(), 0);

        Mur droite = new Mur("Droite", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new ArrayList<String>());

        listeMurs.add(droite);
    }

    public List<Mur> retirerRainures(List<Mur> listeDeMursARainurer, double distanceUsinage, String orientationToit) {
        List<Mur> mursDecoupes = new ArrayList<>();
        if (Objects.equals(orientationToit, "Nord") || Objects.equals(orientationToit, "Sud")) {
            for (Mur mur : listeDeMursARainurer) {
                System.out.println("Mur: " + mur);
                System.out.println("sommets:" + mur.getSommetsMur());
                if (Objects.equals(mur.getNomMur(), "Facade")) {
                    mur.getSommetsMur().get(0).setLocation(mur.getSommetsMur().get(0).getX(), mur.getSommetsMur().get(0).getY()); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.createSommet(mur, new PointDouble((mur.getSommetsMur().get(0).getX()), (mur.getSommetsMur().get(1).getY()) / 2 - distanceUsinage)); // creer Point(0, 1.3)
                    mur.createSommet(mur, new PointDouble((mur.getSommetsMur().get(1).getY()) / 2 + distanceUsinage, (mur.getSommetsMur().get(1).getY()) / 2 - distanceUsinage)); // creer Point(1.7, 1.3)
                    mur.getSommetsMur().get(1).setLocation((mur.getSommetsMur().get(1).getY()) / 2 + distanceUsinage, mur.getSommetsMur().get(1).getY()); //B: SupGauche // Point(0, 3.0) devient Point(1.7, 3.0)

                    mur.getSommetsMur().get(2).setLocation(mur.getSommetsMur().get(2).getX() - ((mur.getSommetsMur().get(1).getX()) / 2) + distanceUsinage, mur.getSommetsMur().get(2).getY()); //C: SupDroite // Point(10.0, 3.0) devient Point(9.8, 3.0)
                    mur.createSommet(mur, new PointDouble(mur.getSommetsMur().get(2).getX() - ((mur.getSommetsMur().get(1).getX()) / 2) - distanceUsinage, (mur.getSommetsMur().get(1).getY()) / 2 - distanceUsinage)); // creer Point(8.3, 1.7)
                    mur.createSommet(mur, new PointDouble(mur.getSommetsMur().get(3).getX(), ((mur.getSommetsMur().get(1).getY()) / 2) - distanceUsinage)); // creer Point(10.0, 1.7)
                    mur.getSommetsMur().get(3).setLocation(mur.getSommetsMur().get(3).getX() , mur.getSommetsMur().get(3).getY()); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    //Vue face
                    mur.getSommetsMur().get(4).setLocation(mur.getSommetsMur().get(4).getX(), mur.getSommetsMur().get(4).getY()); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation(mur.getSommetsMur().get(5).getX(), mur.getSommetsMur().get(5).getY()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(mur.getSommetsMur().get(6).getX(), mur.getSommetsMur().get(6).getY()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(mur.getSommetsMur().get(7).getX(), mur.getSommetsMur().get(7).getY()); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Arriere")) {
                    //initial: A=(0, 10.0), B=(0, 7.0), C=(10.0, 7.0), D=(10.0, 10.0)
                    mur.getSommetsMur().get(0).setLocation(mur.getSommetsMur().get(0).getX(), mur.getSommetsMur().get(0).getY()); //A: SupGauche // Point(0, 10.0) reste Point(0, 10.0)
                    mur.createSommet(mur, new PointDouble(mur.getSommetsMur().get(0).getX(), (mur.getSommetsMur().get(1).getY()) / 2 + distanceUsinage)); // creer Point(0, 8.7)
                    mur.createSommet(mur, new PointDouble((mur.getSommetsMur().get(1).getX()) / 2 + distanceUsinage, (mur.getSommetsMur().get(1).getY()) / 2 + distanceUsinage)); // creer Point(1.7, 8.7)
                    mur.getSommetsMur().get(1).setLocation((mur.getSommetsMur().get(1).getX()) / 2 + distanceUsinage, mur.getSommetsMur().get(1).getY()); //B: InfGauche // Point(0, 7.0) devient Point(1.7, 7.0)

                    mur.getSommetsMur().get(2).setLocation(mur.getSommetsMur().get(2).getX() - ((mur.getSommetsMur().get(1).getY()) / 2) + distanceUsinage, mur.getSommetsMur().get(2).getY()); //C: InfDroite // Point(10.0, 7.0) devient Point(8,3, 7.0)
                    mur.createSommet(mur, new PointDouble(mur.getSommetsMur().get(2).getX() - ((mur.getSommetsMur().get(1).getY()) / 2) + distanceUsinage, (mur.getSommetsMur().get(1).getY()) / 2 + distanceUsinage)); // creer Point(8.3, 8.7)
                    mur.createSommet(mur, new PointDouble(mur.getSommetsMur().get(3).getX(), (mur.getSommetsMur().get(1).getY()) / 2 + distanceUsinage)); // creer Point(10.0, 8.7)
                    mur.getSommetsMur().get(3).setLocation(mur.getSommetsMur().get(3).getX(), mur.getSommetsMur().get(3).getY()); //D: SupDroite // Point(10.0, 10.0) reste Point(10.0, 10.0)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation(mur.getSommetsMur().get(4).getX(), mur.getSommetsMur().get(4).getY()); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation(mur.getSommetsMur().get(5).getX(), mur.getSommetsMur().get(5).getY()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(mur.getSommetsMur().get(6).getX(), mur.getSommetsMur().get(6).getY()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(mur.getSommetsMur().get(7).getX(), mur.getSommetsMur().get(7).getY()); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if (Objects.equals(mur.getNomMur(), "Gauche")) {
                    // final points (0, 1.7), (1.3, 1.7), (1.3, 3.2), (3.0, 3.2), (0, 8.3), (1.3, 8.3), (1.3, 7.6), (3.0, 7.6)
                    //initial points:  0:(0, 0)  1:(0, 10)  2: (3, 10)  3: (3, 0)
                    mur.getSommetsMur().get(0).setLocation(mur.getSommetsMur().get(0).getX(), (mur.getSommetsMur().get(2).getY() / 2) + distanceUsinage); //A: InfGauche // Point(0, 0) devient Point(0, 1.7)
                    mur.createSommet(mur, new PointDouble((mur.getSommetsMur().get(2).getX()) / 2 - distanceUsinage, (mur.getSommetsMur().get(2).getX()) / 2 + distanceUsinage)); // creer Point(1.3, 1.7)
                    mur.createSommet(mur, new PointDouble((mur.getSommetsMur().get(2).getX()) / 2 - distanceUsinage, (mur.getSommetsMur().get(2).getX()) + distanceUsinage)); // creer Point(1.3, 3.2)
                    mur.getSommetsMur().get(1).setLocation(mur.getSommetsMur().get(1).getX(), mur.getSommetsMur().get(1).getY() - (mur.getSommetsMur().get(2).getX()) / 2 - distanceUsinage); //B: SupGauche // Point(0, 10) devient Point(0, 8.3)

                    mur.createSommet(mur, new PointDouble((mur.getSommetsMur().get(2).getX()) / 2 - distanceUsinage, mur.getSommetsMur().get(1).getY() - (mur.getSommetsMur().get(2).getX()) / 2 - distanceUsinage)); // creer Point(1.3, 8.3)
                    mur.createSommet(mur, new PointDouble((mur.getSommetsMur().get(2).getX()) / 2 - distanceUsinage, mur.getSommetsMur().get(2).getY() - (mur.getSommetsMur().get(2).getX()) - distanceUsinage)); // creer Point(1.3, 6.8)
                    mur.getSommetsMur().get(2).setLocation(mur.getSommetsMur().get(2).getX(), mur.getSommetsMur().get(2).getY() - (mur.getSommetsMur().get(2).getX()) - distanceUsinage); //C: SupDroite // Point(3, 10) devient Point(3, 6.8)
                    mur.getSommetsMur().get(3).setLocation(mur.getSommetsMur().get(3).getX(), mur.getSommetsMur().get(3).getX() + distanceUsinage); //D: InfDroite // Point(3, 0) devient Point(3, 3.2)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation((mur.getSommetsMur().get(4).getX() + (epaisseurChalet/2)), mur.getSommetsMur().get(4).getY()); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation((mur.getSommetsMur().get(5).getX()  + (epaisseurChalet/2)), mur.getSommetsMur().get(5).getY()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation((mur.getSommetsMur().get(6).getX() - (epaisseurChalet/2)), mur.getSommetsMur().get(6).getY()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation((mur.getSommetsMur().get(7).getX() - (epaisseurChalet/2)), mur.getSommetsMur().get(7).getY()); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)


                    mursDecoupes.add(mur);
                }
                if(Objects.equals(mur.getNomMur(), "Droite")){
                    //initial points: A  0:(7.0, 0)  B  1:(7.0, 10)  C  2:(10.0, 10.0)  D  3:(10.0, 0)
                    //final points: A:(7.0, 3.2)  B:(7.0, 6.8)  E:(8.7, 6.8)  F:(8.7, 8.3)   C:(10, 8.3)  D:(10.0, 1.7)  G:(8.7, 1.7)  H:(8.7, 3.2)
                    mur.getSommetsMur().get(0).setLocation(mur.getSommetsMur().get(0).getX(), mur.getSommetsMur().get(0).getY() + (mur.getSommetsMur().get(2).getX()) / 2 + distanceUsinage); //A: InfGauche // Point(7.0, 0) devient Point(7.0, 3.2)
                    mur.getSommetsMur().get(1).setLocation(mur.getSommetsMur().get(1).getX(), mur.getSommetsMur().get(1).getX() - distanceUsinage); //B: SupGauche // Point(7.0, 10.0) devient Point(7.0, 6.8)
                    mur.createSommet(mur, new PointDouble(((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage, mur.getSommetsMur().get(1).getX() - distanceUsinage)); // E creer Point(8.7, 6.8)
                    mur.createSommet(mur, new PointDouble(((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage,  ((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage)); // F creer Point(8.7, 8.3)

                    mur.getSommetsMur().get(2).setLocation(mur.getSommetsMur().get(2).getX(), ((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage); //C: SupDroite // Point(10.0, 10.0) devient Point(10.0, 8.3)
                    mur.getSommetsMur().get(3).setLocation(mur.getSommetsMur().get(3).getX(), ((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage); //D: InfDroite // Point(10.0, 0) devient Point(10.0, 1.7)
                    mur.createSommet(mur, new PointDouble(((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage, ((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage)); // G creer Point(8.7, 1.7)
                    mur.createSommet(mur, new PointDouble(((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX()))/2 + distanceUsinage, ((mur.getSommetsMur().get(2).getX()) - (mur.getSommetsMur().get(0).getX())) + distanceUsinage)); // H creer Point(8.7, 3.2)
                    mursDecoupes.add(mur);
                }
            }
        }
        if(Objects.equals(orientationToit, "Ouest") || Objects.equals(orientationToit, "Est")){
            for (Mur mur : listeDeMursARainurer){// it's gonna be the same as North or South, but with the Mur Gauche and Droite are the ones that are longer than Facade and Arriere
                if(mur.getNomMur() == "Gauche"){

                    mur.getSommetsMur().get(0).setLocation(mur.getSommetsMur().get(0).getX(), mur.getSommetsMur().get(0).getY()); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(1).setLocation(mur.getSommetsMur().get(1).getX(), mur.getSommetsMur().get(1).getY()); //B: SupGauche // Point(0, 10) reste Point(0, 10)
                    mur.getSommetsMur().get(2);
                    mur.getSommetsMur().get(3);

                }
            }

        }
        System.out.println("Liste finale: " + mursDecoupes);
        return mursDecoupes;
    }

    public List<Mur> getMursUsines(){
        retirerRainures(listeMurs, 0.3, "Nord");
        System.out.println("Liste de murs usines: " + listeMurs);
        listeMursPourAcc = listeMurs;
        return listeMurs;
    }


    public int DeterminerPointMurLargeur(int numMur,int w,int h) {

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

    }

    public static int determinerMur(String nomMur){

        int numMur = 0;
        if (nomMur == "Facade") {
            numMur = 0;
        }
        if (nomMur == "Arriere") {
            numMur = 1;

        }
        if(nomMur == "Droite" ) {
            numMur = 2;

        }
        if (nomMur == "Arriere") {
            numMur = 3;

        }
        return numMur;

    }


    public static boolean ajouterPorte(Point mousepoint, String nomMur, List<Mur> listeMursDrawer){

        int numMur = determinerMur(nomMur);

         //List<Mur> listeMurs1 = Chalet.getMursUsines();

            Mur mur = listeMursDrawer.get(numMur);

        //Une porte par mur
        List<Porte> listePorte = mur.getListePorte();
        int lenghtlistePorte = listePorte.size();

        if(lenghtlistePorte > 0){
            mur.clearListePorte();
        }


        int x = (int) mousepoint.getX();
        Pouces hauteurMur = convertirDoubleEnPouces(hauteurMurs);
        int hauteurMurInt = convertirPoucesEnPixels(hauteurMur);
        Pouces hauteurPorte = Porte.PORTE_HAUTEUR_STANDARD;
        int hauteurPorteInt = convertirPoucesEnPixels(hauteurPorte);

        /*
        int y = DeterminerPointMurLargeur(,w,h, mousePoint);
        y = (y + hauteurMurInt) - hauteurPorteInt;
        Point murPoint = new Point(x,y);
        Porte Porte = new Porte(positionPorte, murPoint);

        */
        Pouces largeur = new Pouces(10, 0, 1);
        Pouces hauteur = new Pouces(10, 0, 1);
        Porte Porte = new Porte(mousepoint,largeur, hauteur );

        boolean success = mur.ajouterPorte(Porte);

        return success;

    }


    public static boolean ajouterFenetre(Point mousepoint, String nomMur,  List<Mur> listeMursDrawer){

        Pouces largeur = new Pouces(10, 0, 1);
        Pouces hauteur = new Pouces(10, 0, 1);
        Fenetre Fenetre = new Fenetre(mousepoint,largeur,hauteur);

        //List<Mur> listeMurs1 = Chalet.getMursUsines();


        int numMur = determinerMur(nomMur);

        Mur mur = listeMursDrawer.get(numMur);
            List<Fenetre> listeFenetre = mur.getListeFenetre();
            int lenghtlisteFenetre = listeFenetre.size();

            if(lenghtlisteFenetre > 0){
                mur.clearListeFenetre();
            }

        boolean success = mur.ajouterFenetre(Fenetre);

        return success;
    }


    public double getLargeurChalet() {
        //return this.largeurChalet = 8.0;
        return this.largeurChalet;
    }

    public double getHauteurMurs() {
        return this.hauteurMurs;
    }

    public double getLongueurChalet() {
        return this.longueurChalet;
    }

    public double getEpaisseurChalet() {
        return this.epaisseurChalet;
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

    public void setLargeurChalet(double largeurChalet) {
        this.largeurChalet = largeurChalet;

    }

    public void setHauteurMurs(double hauteurMurs) {
        this.hauteurMurs = hauteurMurs;
    }

    public void setLongueurChalet(double longueurChalet) {
        this.longueurChalet = longueurChalet;
    }

    public void setEpaisseurChalet(double epaisseurChalet) {
        this.epaisseurChalet = epaisseurChalet;
    }

    public void setAngleToit(double angleToit) {
        this.angleToit = angleToit;
    }

    public void setListeMurs(List<Mur> listerMurs) {
        this.listeMurs = listeMurs;
    }

    public void setOrientationToit(String orientationToit) {
        this.orientationToit = orientationToit;
    }
}
