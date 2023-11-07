package domain;
import ui.Chaletdrawer;

import java.awt.Point;
import java.util.List;

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



    public static boolean ajouterFenetre(Point mousepoint, String nomMur,List<Mur> listeMursDrawer){

        //java.util.List<Mur> listeMursDrawer2 = Chaletdrawer.chalet.getMursUsines(0,"NORD") ;

        if(Chalet.ajouterFenetre(mousepoint, nomMur,listeMursDrawer))
        {
            return true;
        }

        return false;
        //Fenetre newFenetre = new Accessoires("AID", mousepoint, double largeur, double hauteur);
        //accessoiresmur.add(newFenetre);

    }

    public static boolean ajouterPorte(Point mousepoint, String nomMur, List<Mur> listeMursDrawer){
        //java.util.List<Mur> listeMursDrawer2 = Chaletdrawer.chalet.getMursUsines(0,"NORD") ;


        if(Chalet.ajouterPorte(mousepoint, nomMur,listeMursDrawer))
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
