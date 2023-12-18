package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//import static domain.Chalet.distanceUsinage;

public class ChaletDTO {
    public static Mur facade ; // mur facade deja codé en bas
    public static Mur arriere; // mur arriere deja codé en bas
    public static Mur gauche ; // mur gauche deja codé en bas
    public static Mur droite; // mur droite deja codé en bas
    public static Toit pignongauche; // pignon à gauche de la pente



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
    public static double distanceUsinage=1;
    public static List<Mur> listeMurs;
    public static String orientationToit;
    public static double ZoomFactor;
    public static double grilleP = 10.0;



    public static Chalet createChalet() {

        ArrayList<Mur> listeMurs = new ArrayList<>();

        //Dimensions du mur en 3D
        double epaisseurMur = Chalet.epaisseurChalet;     // Épaisseur du mur
        double hauteurMurs = Chalet.hauteurMurs;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = Chalet.largeurChalet;      // Largeur des murs venant
        double longueurMur = Chalet.longueurChalet;
        double angleToit = Chalet.angleToit;
        String orientationToit = "Est";
        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs,  listeMurs, orientationToit);
        //System.out.println("Voici la VRAI copie, compare.... "+chalet.getMursUsines(distanceUsinage, orientationToit));

        return chalet ;
    }


    static List<Chalet> listeCopiesChalets = new LinkedList<>();
    static List<Mur> listeCopiesMurs = new LinkedList<>();
    public static int compteurCopie=0;


    public static boolean initialiserChalet(Chalet chalet) {
        //get les accessoires deja existant dans une variable externe
        System.out.println("Le chalet vient de changer");
        List<Porte> listePorteDAVANTf = new LinkedList<>();
        List<Porte> listePorteDAVANTa = new LinkedList<>();
        List<Porte> listePorteDAVANTg = new LinkedList<>();
        List<Porte> listePorteDAVANTd = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTf = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTa = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTg = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTd = new LinkedList<>();


        //On cherche tous les accessoires
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

        facade = chalet.getMursUsines(distanceUsinage, orientationToit).get(0); // mur facade deja codé en bas
        arriere = chalet.getMursUsines(distanceUsinage, orientationToit).get(1); // mur arriere deja codé en bas
        gauche = chalet.getMursUsines(distanceUsinage, orientationToit).get(2); // mur gauche deja codé en bas
        droite = chalet.getMursUsines(distanceUsinage, orientationToit).get(3); // mur droite deja codé en bas

        System.out.println("Observons les dimensions de chalet normale.... "+chalet.getLongueurChalet());

        //aide a copier les murs de surplomb si les objets sont toujours pareil
        listeCopiesMurs.add(facade);
        listeCopiesMurs.add(arriere);
        listeCopiesMurs.add(gauche);
        listeCopiesMurs.add(droite);

        Chalet copiedChalet = chalet.clone();  // Assuming Chalet implements Cloneable
        listeCopiesChalets.add(copiedChalet);

        compteurCopie += 1;

        System.out.println("Voici la copie d'un chalet: "+listeCopiesChalets.get(0).getMursUsines(distanceUsinage, orientationToit).get(0));
        System.out.println("==================== Voila ce que contient listeCopies "+ listeCopiesChalets);

        return true;
    }
    public static boolean initialiserChaletSansCopier(Chalet chalet) {
        //Éviter que le code détecte le undo comme une modification, et qu'il ajoute une autre copie par accident
        System.out.println("Le chalet vient de changer sans copie pour ton UNDO");


        List<Porte> listePorteDAVANTf = new LinkedList<>();
        List<Porte> listePorteDAVANTa = new LinkedList<>();
        List<Porte> listePorteDAVANTg = new LinkedList<>();
        List<Porte> listePorteDAVANTd = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTf = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTa = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTg = new LinkedList<>();
        List<Fenetre> listeFenetreDAVANTd = new LinkedList<>();

        //On cherche tous les accessoires
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


        // on copie les murs
        facade = listeCopiesMurs.get(0+4*compteurCopie); // mur facade
        arriere = listeCopiesMurs.get(1+4*compteurCopie); // mur arriere
        //gauche = chalet.getMursUsines(distanceUsinage, orientationToit).get(2); // mur gauche
        gauche = listeCopiesMurs.get(2+4*compteurCopie);
        //droite = chalet.getMursUsines(distanceUsinage, orientationToit).get(3); // mur droite
        droite = listeCopiesMurs.get(3+4*compteurCopie);
/*
        System.out.println("==================== Voila ce que contient listeCopies(1) et (0)  "+ listeCopiesChalets.get(1).getLongueurChalet()+ listeCopiesChalets.get(0).getLongueurChalet());
        Controleur.setLongueurChalet(droite.getSommetsMur().get(3).getX());
        Controleur.setHauteurMurs(facade.getSommetsMur().get(5).getY());
        Controleur.setLargeurChalet(droite.getSommetsMur().get(3).getX());
        Controleur.setEpaisseurChalet(gauche.getSommetsMur().get(2).getX());
//        Controleur.setLongueurChalet(listeCopiesChalets.get(0).getLongueurChalet());
//        Controleur.setHauteurMurs(listeCopiesChalets.get(1).getHauteurMurs());
//        Controleur.setLargeurChalet(listeCopiesChalets.get(1).getLargeurChalet());
//        Controleur.setEpaisseurChalet(listeCopiesChalets.get(1).getEpaisseurChalet());
        Controleur.setAngleToit(chalet.getAngleToit());
        //Controleur.setRetraitChalet(listeCopiesChalets.get(1).());
        Controleur.setOrientation(listeCopiesChalets.get(1).getOrientationToit());
*/



        return true;
    }
}
