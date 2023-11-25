package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import domain.Chalet;

public class ChaletDTO {
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

    public static double largeurChalet = 10.0;
    public static double longueurChalet = 10.0;
    public static double hauteurMurs=8.0;
    public static double epaisseurChalet = 2.0;
    public static double angleToit;
    public static List<Mur> listeMurs;
    public static String orientationToit;
    public static double ZoomFactor;

    public ChaletDTO(Chalet bi){
        largeurChalet = bi.getLargeurChalet();
        longueurChalet = bi.getLongueurChalet();
        hauteurMurs = bi.getHauteurMurs();
        epaisseurChalet = bi.getEpaisseurChalet();
        angleToit = bi.getAngleToit();
        listeMurs = bi.getListeMurs();
        orientationToit = bi.getOrientationToit();
        ZoomFactor = bi.getZoom();
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

    public static void creerNouveauChalet(Chalet chalet){
        chalet.getListeMurs().clear();
        chalet.initialiserMurArriere();
        chalet.initialiserMurDroite();
        chalet.initialiserMurFacade();
        chalet.initialiserMurGauche();

        //return true;

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
    public double getZoomFactor() {
        return this.ZoomFactor;
    }

    public void setZoom(double newZoom)
    {
        ZoomFactor = newZoom;

    }

}
