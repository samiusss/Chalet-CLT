package domain;


import Utilitaires.PointDouble;
import java.util.List;
import java.util.Objects;

public class Mur {
    private String nomMur;
    private List<PointDouble> sommetsMur;
    private List<String> accessoiresMur;

    // on peut creer un mur simple
    public Mur() {
    }

    // deuxieme constructeur pour creer un mur avec 4 points et des accessoires et un nom
    // ce meme constructeur est appelle a la ligne
    public Mur(String nomMur, List<PointDouble> sommetsMur, List<String> accessoiresMur) {
        this.nomMur = nomMur;
        this.sommetsMur = sommetsMur; // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
        this.accessoiresMur = accessoiresMur; // ceci est comme ci: [Liste de accessoires]
    }

    /*public void retirerRainures(List<Mur> listeDeMursARainurer, double distanceUsinage, String orientationToit){
        if (Objects.equals(orientationToit, "Nord") || Objects.equals(orientationToit, "Sud")){
            for(Mur mur : listeDeMursARainurer) {
                if (Objects.equals(mur.getNomMur(), "Facade")){
                    mur.getSommetsMur().get(0).setLocation(mur.getSommetsMur().get(0).getX(), mur.getSommetsMur().get(0).getY()); //A: InfGauche // Point(0, 0) reste Point(0, 0)
                    mur.createSommet(new PointDouble(mur.getSommetsMur().get(0).getX(), (mur.getSommetsMur().get(1).getY())/2 - distanceUsinage)); // creer Point(0, 1.3)
                    mur.createSommet(new PointDouble((mur.getSommetsMur().get(1).getY())/2 + distanceUsinage, (mur.getSommetsMur().get(1).getY())/2 - distanceUsinage)); // creer Point(1.7, 1.3)
                    mur.getSommetsMur().get(1).setLocation((mur.getSommetsMur().get(1).getY())/2 + distanceUsinage, mur.getSommetsMur().get(1).getY()); //B: SupGauche // Point(0, 3.0) devient Point(1.7, 3.0)

                    mur.getSommetsMur().get(2).setLocation(mur.getSommetsMur().get(2).getX() - ((mur.getSommetsMur().get(1).getY())/2) - distanceUsinage, mur.getSommetsMur().get(2).getY()); //C: SupDroite // Point(10.0, 3.0) devient Point(9.8, 3.0)
                    mur.createSommet(new PointDouble(mur.getSommetsMur().get(2).getX() - ((mur.getSommetsMur().get(1).getY())/2) - distanceUsinage, (mur.getSommetsMur().get(1).getY())/2 - distanceUsinage)); // creer Point(8.3, 1.7)
                    mur.createSommet(new PointDouble(mur.getSommetsMur().get(3).getX(), (mur.getSommetsMur().get(1).getY())/2 - distanceUsinage)); // creer Point(10.0, 1.7)
                    mur.getSommetsMur().get(3).setLocation(mur.getSommetsMur().get(3).getX(), mur.getSommetsMur().get(3).getY()); //D: InfDroite // Point(10.0, 0) reste Point(10.0, 0)
                }
                else{
                    System.out.println("Le mur n'est pas une facade");
                }
            }
        }
    }*/

    @Override
    public String toString() {
        return "Mur: " + nomMur + ", Coordinates: " + sommetsMur + ", Accessories: " + accessoiresMur;
    }

    public String getNomMur() {
        return nomMur;
    }

    public void setNomMur(String nomMur) {
        this.nomMur = nomMur;
    }

    public List<PointDouble> getSommetsMur() {
        return sommetsMur;
    }

    public void setSommetsMur(List<PointDouble> sommetsMur) {
        this.sommetsMur = sommetsMur;
    }

    public void createSommet(PointDouble point){
        this.sommetsMur.add(point);
    }

    public List<String> getAccessoiresMur() {
        return accessoiresMur;
    }

    public void setAccessoiresMur(List<String> accessoiresMur) {
        this.accessoiresMur = accessoiresMur;
    }
}