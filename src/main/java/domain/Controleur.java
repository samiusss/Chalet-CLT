package domain;

import Utilitaires.Pouces;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
   public Controleur()
   {
       this.zoom = 1;
   }

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
        //get les accessoires deja existant dans une variable externe
        //System.out.println("Here la liste"+chalet.getListeMurs());
        List<Porte> listePorteDAVANTf = new LinkedList<>();
        List<Porte> listePorteDAVANTa = new LinkedList<>();
        List<Porte> listePorteDAVANTg = new LinkedList<>();
        List<Porte> listePorteDAVANTd = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTf = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTa = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTg = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTd = new LinkedList<>();

        List<Mur> listeMur = chalet.getListeMurs();
        if (!listeMur.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                Mur mur = listeMur.get(i);
                List<Porte> listePorte = mur.getListePorte();
                List<Fenetre> listeFenetre = mur.getListeFenetre();

                if (i == 0) {
                    listePorteDAVANTf = listePorte;
                    listeFenetreDAVANTf = listeFenetre;
                } else if (i == 1) {
                    listePorteDAVANTa = listePorte;
                    listeFenetreDAVANTa = listeFenetre;
                } else if (i == 2) {
                    listePorteDAVANTg = listePorte;
                    listeFenetreDAVANTg = listeFenetre;
                } else if (i == 3) {
                    listePorteDAVANTd = listePorte;
                    listeFenetreDAVANTd = listeFenetre;
                }
            }
        }



        chalet.getListeMurs().clear();

        chalet.initialiserMurFacade();
        chalet.initialiserMurArriere();
        chalet.initialiserMurGauche();
        chalet.initialiserMurDroite();
        chalet.getListeMurs().get(0).porteMur = listePorteDAVANTf;
        chalet.getListeMurs().get(1).porteMur = listePorteDAVANTa;
        chalet.getListeMurs().get(2).porteMur = listePorteDAVANTg;
        chalet.getListeMurs().get(3).porteMur = listePorteDAVANTd;
        chalet.getListeMurs().get(0).fenetreMur = listeFenetreDAVANTf;
        chalet.getListeMurs().get(1).fenetreMur = listeFenetreDAVANTa;
        chalet.getListeMurs().get(2).fenetreMur = listeFenetreDAVANTg;
        chalet.getListeMurs().get(3).fenetreMur = listeFenetreDAVANTd;

        // set les accessoires dans les nouveaux murs
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

    public boolean setLargeurPorte(Pouces nouvellelargeur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setLargeurPorte(nouvellelargeur, nomMur, listeMursDrawer, initialDimension);
        return success;
    }
    public boolean setHauteurPorte(Pouces nouvelleHauteur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setHauteurPorte(nouvelleHauteur, nomMur, listeMursDrawer,initialDimension);
        return success;
    }

    public boolean setHauteurFenetre(Point mousePointClicked, Pouces nouvelleLongueur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setHauteurFenetre(mousePointClicked,nouvelleLongueur, nomMur, listeMursDrawer, initialDimension);
        return success;
    }

    public boolean setLargeurFenetre(Point mousePointClicked,Pouces nouvelleLongueur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setLargeurFenetre(mousePointClicked,nouvelleLongueur, nomMur, listeMursDrawer,initialDimension);
        return success;
    }
    public boolean supprimerPorte(String nomMur, List<Mur> listeMursDrawer)
    {
        boolean success = Chalet.supprimerPorte(nomMur, listeMursDrawer);
        return success;
    }
    public boolean modifierXPorte(Point mousePointClicked,int nouveauXporteint, String nomMur, List<Mur> listeMursDrawer,Dimension initialDimension)
    {
        boolean success = Chalet.modifierXporte(mousePointClicked,nouveauXporteint, nomMur, listeMursDrawer,initialDimension );
        return success;}
    // J'ai un bugg ici
    public boolean modifierXFenetre(Point mousePointClicked, int nouveauXfenetreint, String nomMur, List<Mur> listeMursDrawer,Dimension initialDimension)
    {
        boolean success = Chalet.modifierXfenetre(mousePointClicked,nouveauXfenetreint, nomMur, listeMursDrawer,initialDimension );
        return success;
    }

    public boolean modifierYFenetre(Point mousePointClicked, int nouveauYfenetreint, String nomMur, List<Mur> listeMursDrawer,Dimension initialDimension)
    {
        boolean success = Chalet.modifierYfenetre(mousePointClicked,nouveauYfenetreint, nomMur, listeMursDrawer,initialDimension );
        return success;
    }

    public static boolean supprimerFenetre(Point mousePointClicked,String nomMur, List<Mur> listeMursDrawer)
    {
        boolean success = Chalet.supprimerFenetre(mousePointClicked,nomMur, listeMursDrawer);
        return success;
    }

    public static boolean supprimerToutesFenetre(String nomMur, List<Mur> listeMursDrawer)
    {
        boolean success = Chalet.supprimerToutesFenetre(nomMur, listeMursDrawer);
        return success;
    }



    static Chalet chaletProduction = createChalet();

    boolean rep = initialiserChalet(chaletProduction);

    public Chalet getChaletProduction() {
        return chaletProduction;
    }




    public static boolean ajouterFenetre(Point mousepoint, String nomMur, List<Mur> listeMursDrawer, Dimension intitalDimension){

        if(Chalet.ajouterFenetre(mousepoint, nomMur,listeMursDrawer,intitalDimension))
        {
            return true;
        }

        return false;
        //Fenetre newFenetre = new Accessoires("AID", mousepoint, double largeur, double hauteur);
        //accessoiresmur.add(newFenetre);

    }

    public static boolean ajouterPorte(Point mousepoint, String nomMur, List<Mur> listeMursDrawer,Dimension intitalDimension){

        if(Chalet.ajouterPorte(mousepoint, nomMur,listeMursDrawer,intitalDimension))
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

    public void setZoom(float zbi)
    {
        this.zoom = zbi;
    }

    public float getOffset() {
        return offset;
    }
}
