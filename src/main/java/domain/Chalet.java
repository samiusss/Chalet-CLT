package domain;

import Utilitaires.PointDouble;
import domain.Mur;
import java.util.*;
import java.util.List;

public class Chalet {

    protected double largeurChalet;
    protected double longueurChalet;
    protected double hauteurMurs;
    protected double epaisseurChalet;
    private double angleToit;
    protected List<Mur> listeMurs; //ex: listeMurs  = [Mur n, Mur w, Mur e, Mur s]
    private String orientationToit;

    public Chalet(){
        this.largeurChalet = 0.0;
        this.longueurChalet = 0.0;
        this.hauteurMurs = 0.0;
        this.epaisseurChalet = 0.0;
        this.angleToit = 0.0;
        this.listeMurs = new ArrayList<>();
        this.orientationToit = "";
    }
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
    Chalet chalet = new Chalet(); // TODO: je peux faire ca? --> a confirmer

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

/*public retirerRainures(double distanceSupplementaire=0.2){

    for(Mur mur in listeMurs){
        if()
    }
    Chalet chalet;
    Point pointInfGauche = new Point3D(0, 0, 0);
    Point pointInfDroit = new Point(chalet.longueurChalet, 0, 0);
    Point pointSupGauche = new Point(0);

    if(chalet.longueurChalet < chalet.largeurChalet){
        //pas encore fini
    }
    if (chalet.orientationToit == "Nord" || chalet.orientationToit == "Sud"){
        if(mur.MID == "facade"){
            listeMurs[0].get(2).setX()
        }if(mur.MID == ""){

        }
    } else {

    }
}*/
  public void ajouterFenetre(){}

  public void ajouterPorte(){}
  public double getLargeurChalet() {
      return this.largeurChalet;
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
        //this.largeurChalet = 1000;

    }

    public void setLongueurChalet(double longueurChalet) {
        this.longueurChalet = longueurChalet;
        //this.longueurChalet = 1000;
    }

    public void setEpaisseurChalet(double epaisseurChalet) {
        this.epaisseurChalet = epaisseurChalet;
        //this.epaisseurChalet = 50;
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
