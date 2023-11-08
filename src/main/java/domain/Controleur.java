package domain;

import Utilitaires.Pouces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//import static domain.Mur.accessoiresMur;

public class Controleur {
    private ChaletDTO chalet;
    private float zoom;
    private float offset;


    public static Mur facade ; // mur facade deja codé en bas
    public static Mur arriere; // mur arriere deja codé en bas
    public static Mur gauche ; // mur gauche deja codé en bas
    public static Mur droite; // mur droite deja codé en bas


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
        double epaisseurMur = Chalet.epaisseurChalet;     // Épaisseur du mur
        double hauteurMurs = Chalet.hauteurMurs;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = Chalet.largeurChalet;      // Largeur des murs venant
        double longueurMur = Chalet.longueurChalet;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
        return chalet ;
    }

    public static boolean initialiserChalet(Chalet chalet) {

        chalet.initialiserMurFacade();
        chalet.initialiserMurArriere();
        chalet.initialiserMurGauche();
        chalet.initialiserMurDroite();

        facade = chalet.getMursUsines(0.2, "Nord").get(0); // mur facade deja codé en bas
        arriere = chalet.getMursUsines(0.2, "Nord").get(1); // mur arriere deja codé en bas
        gauche = chalet.getMursUsines(0.2, "Nord").get(2); // mur gauche deja codé en bas
        droite = chalet.getMursUsines(0.2, "Nord").get(3); // mur droite deja codé en bas

        return true;
    }

    /*public static boolean reinitialiserChalet(Chalet chalet)
    {
        chalet.reinitialiserMurFacade();
        chalet.reinitialiserMurArriere();
        chalet.reinitialiserMurGauche();
        chalet.reinitialiserMurDroite();

        facade = chalet.getMursUsines(0.2, "Nord").get(0); // mur facade deja codé en bas
        arriere = chalet.getMursUsines(0.2, "Nord").get(1); // mur arriere deja codé en bas
        gauche = chalet.getMursUsines(0.2, "Nord").get(2); // mur gauche deja codé en bas
        droite = chalet.getMursUsines(0.2, "Nord").get(3); // mur droite deja codé en bas

        return true;
    }*/

        public static void setEpaisseurChalet(double epaisseurChalet)
    {
        Chalet.setEpaisseurChalet(epaisseurChalet);
        initialiserChalet(chaletProduction);
    }

    public static void setLongueurChalet(double longueurChalet)
    {
        Chalet.setLongueurChalet(longueurChalet);
        //Chalet.setLargeurChalet(longueurChalet);

        System.out.println(longueurChalet+" Réinitialisation en cours"); //test
        initialiserChalet(chaletProduction);
    }

    public static void setLargeurChalet(double largeurChalet)
    {
        Chalet.setLargeurChalet(largeurChalet);
        System.out.println(largeurChalet+" Réinitialisation en cours"); //test
        initialiserChalet(chaletProduction);
    }

    public static void setHauteurMurs(double hauteurMurs) {
        Chalet.setHauteurMurs(hauteurMurs);
        System.out.println(hauteurMurs+" Réinitialisation en cours"); //test
        initialiserChalet(chaletProduction);
    }

    ///////////////////////////////////////PORTE//////////////////////////////////

    public void setLargeurPorte(Pouces nouvellelargeur)
    {
        Porte.PORTE_LARGEUR_STANDARD = nouvellelargeur;
        System.out.println(Porte.PORTE_LARGEUR_STANDARD);
    }

    public void setHauteurPorte(Pouces nouvellehauteur)
    {
        Porte.PORTE_HAUTEUR_STANDARD = nouvellehauteur;
    }

    static Chalet chaletProduction = createChalet();

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
