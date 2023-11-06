package domain;
import java.awt.Point;

public class Controleur {
    private ChaletDTO chalet;
    private float zoom;
    private float offset;


   /* public Controleur (Chalet chalet){
        this.chalet = chalet;
    }*/
    /*public Controleur() {
        chalet = new Chalet();
    }
*/


    public boolean ajouterFenetre(Point mousepoint, String nomMur){

        if(Chalet.ajouterFenetre(mousepoint, nomMur))
        {
            return true;
        }

        return false;
        //Fenetre newFenetre = new Accessoires("AID", mousepoint, double largeur, double hauteur);
        //accessoiresmur.add(newFenetre);

    }

    public boolean ajouterPorte(Point mousepoint, String nomMur){

        if(Chalet.ajouterPorte(mousepoint, nomMur))
        {
            return true;
        }

        return false;
        //Porte newPorte = newAccessoires("AID",mousepoint, double largeur,double hauteur);
        //accessoiresmur.add(newPorte);
    }
//test
    public ChaletDTO getChalet() {
        return chalet;
    }
    public float getZoom () {
        return zoom;
    }

    public float getOffset() {
        return offset;
    }



}
