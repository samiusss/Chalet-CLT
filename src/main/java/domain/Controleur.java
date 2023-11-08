package domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//import static domain.Mur.accessoiresMur;

public class Controleur {
    private ChaletDTO chalet;
    private float zoom;
    private float offset;


    public Mur facade ; // mur facade deja codé en bas
    public Mur arriere; // mur arriere deja codé en bas
    public Mur gauche ; // mur gauche deja codé en bas
    public Mur droite; // mur droite deja codé en bas


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
        double epaisseurMur = 2*20;     // Épaisseur du mur
        double hauteurMurs = 2*80;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = 2*100;      // Largeur des murs venant
        double longueurMur = 2*100;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);


        return chalet ;

    }


    public boolean initialiserChalet(Chalet chalet) {
        chalet.initialiserMurFacade();
        chalet.initialiserMurArriere();
        chalet.initialiserMurGauche();
        chalet.initialiserMurDroite();

        this.facade = chalet.getMursUsines(0.2, "Nord").get(0); // mur facade deja codé en bas
        this.arriere = chalet.getMursUsines(0.2, "Nord").get(1); // mur arriere deja codé en bas
        this.gauche = chalet.getMursUsines(0.2, "Nord").get(2); // mur gauche deja codé en bas
        this.droite = chalet.getMursUsines(0.2, "Nord").get(3); // mur droite deja codé en bas

        return true;
    }


    Chalet chaletProduction = createChalet();

    boolean rep = initialiserChalet(chaletProduction);

    public Chalet getChaletProduction() {
        return chaletProduction;
    }




    public static boolean ajouterFenetre(Point mousepoint, String nomMur, List<Mur> listeMursDrawer){

        if(Chalet.ajouterFenetre(mousepoint, nomMur,listeMursDrawer))
        {
            return true;
        }

        return false;
        //Fenetre newFenetre = new Accessoires("AID", mousepoint, double largeur, double hauteur);
        //accessoiresmur.add(newFenetre);

    }

    public static boolean ajouterPorte(Point mousepoint, String nomMur, List<Mur> listeMursDrawer){

        if(Chalet.ajouterPorte(mousepoint, nomMur,listeMursDrawer))
        {
            return true;
        }

        return false;
        //Porte newPorte = newAccessoires("AID",mousepoint, double largeur,double hauteur);
        //accessoiresmur.add(newPorte);
    }


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
