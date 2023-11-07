package domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controleur {
    private ChaletDTO chalet;
    private float zoom;
    private float offset;

    public enum AffichageVue
    {
        FACADE,

        TEST,
        GAUCHE,
        DROITE,
        ARRIERE,
        SURPLOMB
    }


   /* public Controleur (Chalet chalet){
        this.chalet = chalet;
    }*/
    /*public Controleur() {
        chalet = new Chalet();
    }
*/


    public static Chalet createChalet() {

        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        //Dimensions du mur en 3D
        double epaisseurMur = 2*20;   // Épaisseur du mur
        double hauteurMurs = 2*80;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 2*100;     // Largeur des murs venant
        double longueurMur = 2*100;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);


        return chalet ;

    }
    Chalet chaletProduction = createChalet();


    public Chalet getChaletProduction() {
        return chaletProduction;
    }




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
