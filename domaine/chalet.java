import java.awt.*;

public class Chalet {

    private double largeurChalet;
    private double longueurChalet;
    private double epaisseurChalet;
    private double angleToit;
    private List<Mur> listerMurs;
    private String orientationToit;

    public Chalet(double largeurChalet, double longueurChalet,
                  double epaisseurChalet, double angleToit,
                  List<Mur> listerMurs, String orientationToit) {
        this.largeurChalet = largeurChalet;
        this.longueurChalet = longueurChalet;
        this.epaisseurChalet = epaisseurChalet;
        this.angleToit = angleToit;
        this.listerMurs = listerMurs;
        this.orientationToit = orientationToit;
    }

    public retirerRainures(double distanceSupplementaire=0.2){
        Chalet chalet;
        Mur facade = new Point(
                chalet.longueurChalet,
                chalet.largeurChalet,
                chalet.epaisseurChalet);

        if(chalet.longueurChalet < chalet.largeurChalet){
            //pas encore fini
        }
    }
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

    public List<Mur> getListerMurs() {
        return this.listerMurs;
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

    public void setListerMurs(List<Mur> listerMurs) {
        this.listerMurs = listerMurs;
    }

    public void setOrientationToit(String orientationToit) {
        this.orientationToit = orientationToit;
    }


}
