package domain;
import java.awt.Point;

public class Controleur {
    private Chalet chalet;
    private float zoom;
    private float offset;


    /*public Controleur (Chalet chalet){
        this.chalet = chalet;
    }
    public BasketController() {
        basket = new Basket();
    }*/

    public void ajouterFenetre(Point mousepoint){
        //Fenetre newFenetre = new Accessoires("AID", mousepoint, double largeur, double hauteur);
        //accessoiresmur.add(newFenetre);

    }

    public void ajouterPorte(Point mousepoint){
        //Porte newPorte = newAccessoires("AID",mousepoint, double largeur,double hauteur);
        //accessoiresmur.add(newPorte);
    }
//test
    public Chalet getChalet() {
        return chalet;
    }

    public float getZoom () {
        return zoom;
    }

    public float getOffset() {
        return offset;
    }



}
