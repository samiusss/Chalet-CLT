package domain;

import Utilitaires.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chalet {
    //allo
    protected double largeurChalet;
    protected double longueurChalet;
    protected double hauteurMurs;
    protected double epaisseurChalet;
    private double angleToit;
    protected List<Mur> listeMurs; //ex: listeMurs  = [Mur n, Mur w, Mur e, Mur s]
    private String orientationToit;

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

        PointDouble pointInfGauche = new PointDouble(0, 0);
        PointDouble pointSupGauche = new PointDouble(0, getEpaisseurChalet());
        PointDouble pointSupDroit = new PointDouble(getLongueurChalet(), getEpaisseurChalet());
        PointDouble pointInfDroit = new PointDouble(getLongueurChalet(), 0);
        Mur facade = new Mur("Facade", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit), new ArrayList<String>());

        listeMurs.add(facade);
    }

    public void initialiserMurArriere(){

        PointDouble pointInfGauche = new PointDouble(0, getLargeurChalet() - getEpaisseurChalet());
        PointDouble pointSupGauche = new PointDouble(0, getLargeurChalet());
        PointDouble pointSupDroit = new PointDouble(getLongueurChalet(), getLargeurChalet());
        PointDouble pointInfDroit = new PointDouble(getLongueurChalet(), getLargeurChalet() - getEpaisseurChalet());
        Mur arriere = new Mur("Arriere", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit), new ArrayList<String>());

        listeMurs.add(arriere);
    }

    public void initialiserMurGauche(){

        PointDouble pointInfGauche = new PointDouble(0, 0);
        PointDouble pointSupGauche = new PointDouble(0, getLargeurChalet());
        PointDouble pointSupDroit = new PointDouble(getEpaisseurChalet(), getLargeurChalet());
        PointDouble pointInfDroit = new PointDouble(getEpaisseurChalet(), 0);
        Mur gauche = new Mur("Gauche", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit), new ArrayList<String>());

        listeMurs.add(gauche);
    }

    public void initialiserMurDroite(){

        PointDouble pointInfGauche = new PointDouble(getLongueurChalet() - getEpaisseurChalet(), 0);
        PointDouble pointSupGauche = new PointDouble(getLongueurChalet() - getEpaisseurChalet(), getLargeurChalet());
        PointDouble pointSupDroit = new PointDouble(getLongueurChalet(), getLargeurChalet());
        PointDouble pointInfDroit = new PointDouble(getLongueurChalet(), 0);
        Mur droite = new Mur("Droite", Arrays.asList(pointInfGauche, pointSupGauche, pointSupDroit, pointInfDroit), new ArrayList<String>());

        listeMurs.add(droite);
    }

    /*public void retirerRainures(double distanceUsinage){
        for (Mur mur : listeMurs) {

        }
    }*/

    // function pour test
    public void afficherListeMurs(){
        for (Mur mur : listeMurs) {
            System.out.println(mur);
        }
    }


    public void ajouterPorte(){}
    public double getLargeurChalet() {
        return this.largeurChalet;
    }

    public double getLongueurChalet() {
        return(this.longueurChalet);
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
