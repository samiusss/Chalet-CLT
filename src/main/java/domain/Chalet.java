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

        Mur facade = new Mur("Facade", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new ArrayList<String>());

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

        Mur arriere = new Mur("Arriere", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheArriere, pointSupGaucheArriere, pointSupDroitArriere, pointInfDroitArriere), new ArrayList<String>());

        listeMurs.add(arriere);
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

        Mur gauche = new Mur("Gauche", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new ArrayList<String>());

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

        Mur droite = new Mur("Droite", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit,
                pointInfGaucheFace, pointSupGaucheFace, pointSupDroitFace, pointInfDroitFace), new ArrayList<String>());

        listeMurs.add(droite);
    }

    public void retirerRainures(List<Mur> listeDeMursARainurer, double distanceUsinage, String orientationToit) {
        List<Mur> mursDecoupes = new ArrayList<>();
        if (Objects.equals(orientationToit, "Nord") || Objects.equals(orientationToit, "Sud")) {
            for (Mur mur : listeDeMursARainurer) {
                if (Objects.equals(mur.getNomMur(), "Facade")) {
                    mur.getSommetsMur().get(0).setLocation(0, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.createSommet(mur, new PointDouble(0,(getEpaisseurChalet()/2) - distanceUsinage)); // creer Point(0, 1.3)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2) + distanceUsinage, (getEpaisseurChalet()/2) - distanceUsinage)); // creer Point(1.7, 1.3)
                    mur.getSommetsMur().get(1).setLocation((getEpaisseurChalet()/2) + distanceUsinage, (getEpaisseurChalet())); //B: SupGauche // Point(0, 3.0) devient Point(1.7, 3.0)

                    mur.getSommetsMur().get(2).setLocation(getLongueurChalet()-(getEpaisseurChalet()/2) - distanceUsinage , getEpaisseurChalet()); //C: SupDroite // Point(10.0, 3.0) devient Point(9.8, 3.0)
                    mur.createSommet(mur, new PointDouble(getLongueurChalet()-(getEpaisseurChalet()/2) - distanceUsinage, (getEpaisseurChalet()/2) - distanceUsinage)); // creer Point(8.3, 1.7)
                    mur.createSommet(mur, new PointDouble(getLongueurChalet(), (getEpaisseurChalet()/2) - distanceUsinage)); // creer Point(10.0, 1.7)
                    mur.getSommetsMur().get(3).setLocation(getLongueurChalet() , 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    //Vue face
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
                    mur.getSommetsMur().get(1).setLocation(0, (getLongueurChalet() - (getEpaisseurChalet()/2))); //B: SupGauche // Point(0, 10) devient Point(0,9)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), (getLongueurChalet() - (getEpaisseurChalet()/2)))); // creer Point(1.5, 9)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), (getLargeurChalet() - getEpaisseurChalet()))); // creer Point(1.5, 8)

                    mur.getSommetsMur().get(2).setLocation(getEpaisseurChalet(), (getLargeurChalet() - getEpaisseurChalet())); //C: SupDroite // Point(2, 10) devient Point(2, 8)
                    mur.getSommetsMur().get(3).setLocation(getEpaisseurChalet(), getEpaisseurChalet()); //D: InfDroite // Point(2, 0) devient Point(2, 2)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), getEpaisseurChalet())); // creer Point(1.5, 2)
                    mur.createSommet(mur, new PointDouble((getEpaisseurChalet()/2), (getEpaisseurChalet()/2))); // creer Point(1.5, 1.5)

                    //Vue de face
                    mur.getSommetsMur().get(4).setLocation(getEpaisseurChalet()/2, 0); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.getSommetsMur().get(5).setLocation(getEpaisseurChalet()/2, getHauteurMurs()); //B: SupGauche // Point(0, 8.0) reste Point(0, 8.0)
                    mur.getSommetsMur().get(6).setLocation(getLargeurChalet() - (getEpaisseurChalet()/2), getHauteurMurs()); //C: SupDroite // Point(10.0, 8.0) reste Point(10.0, 8.0)
                    mur.getSommetsMur().get(7).setLocation(getLargeurChalet() - (getEpaisseurChalet()/2), 0); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)

                    mursDecoupes.add(mur);
                }
                if(Objects.equals(mur.getNomMur(), "Droite")){
                    //initial points: A  0:(7.0, 0)  B  1:(7.0, 10)  C  2:(10.0, 10.0)  D  3:(10.0, 0)
                    //final points: A:(7.0, 3.2)  B:(7.0, 6.8)  E:(8.7, 6.8)  F:(8.7, 8.3)   C:(10, 8.3)  D:(10.0, 1.7)  G:(8.7, 1.7)  H:(8.7, 3.2)
                    mur.getSommetsMur().get(0).setLocation(getLongueurChalet() - getEpaisseurChalet(), getEpaisseurChalet()); //A: InfGauche // Point(7.0, 0) devient Point(8,2)
                    mur.getSommetsMur().get(1).setLocation(getLongueurChalet() - getEpaisseurChalet(), getLongueurChalet() - getEpaisseurChalet()); //B: SupGauche // Point(7.0, 10.0) devient Point(8, 8)
                    mur.createSommet(mur, new PointDouble((getLongueurChalet() - (getEpaisseurChalet()/2)), getLongueurChalet() - getEpaisseurChalet())); // E creer Point(9, 8)
                    mur.createSommet(mur, new PointDouble((getLongueurChalet() - (getEpaisseurChalet()/2)),  (getLongueurChalet() - (getEpaisseurChalet()/2)))); // F creer Point(9, 9)

                    mur.getSommetsMur().get(2).setLocation(getLongueurChalet(), (getLongueurChalet() - (getEpaisseurChalet()/2))); //C: SupDroite // Point(10.0, 10.0) devient Point(10.0, 9)
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
        System.out.println("Liste de murs usinés: " + listeMurs);
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

        //java.util.List<Mur> listeMursDrawer2 = Chaletdrawer.chalet.getMursUsines(0,"NORD") ;
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


    public static boolean ajouterFenetre(Point mousepoint, String nomMur, List<Mur> listeMursDrawer){

        Pouces largeur = new Pouces(10, 0, 1);
        Pouces hauteur = new Pouces(10, 0, 1);
        Fenetre Fenetre = new Fenetre(mousepoint,largeur,hauteur);

        int numMur = determinerMur(nomMur);

        Mur mur = listeMurs.get(numMur);
            List<Fenetre> listeFenetre = mur.getListeFenetre();
            int lenghtlisteFenetre = listeFenetre.size();

            if(lenghtlisteFenetre > 0){
                mur.clearListeFenetre();
            }

        boolean success = mur.ajouterFenetre(Fenetre);

        return success;
    }


    public double getLargeurChalet() {
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
