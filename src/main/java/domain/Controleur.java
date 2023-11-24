package domain;

import Utilitaires.Pouces;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static domain.ChaletDTO.createChalet;
import static domain.ChaletDTO.initialiserChalet;

//import static domain.Mur.accessoiresMur;

public class Controleur {
    private ChaletDTO chaletdto;
    private double zoom;
    private float offset;


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
        return chaletdto;
    }
    public double getZoom () {
        double leZoom = Chalet.getZoom();
        return leZoom;
    }

    public void setZoom(double newZoom)
    {
        Chalet.setZoom(newZoom);
    }

    public float getOffset() {
        return offset;
    }
}
