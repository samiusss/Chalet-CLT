package Utilitaires;

import domain.Chalet;
import domain.Controleur;
import domain.Mur;
import domain.Toit;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChaletCopie implements Serializable {
    public double largeurChalet;
    public double longueurChalet;
    public double hauteurMurs;
    public double epaisseurChalet;
    public double angleToit;
    public double retraitChalet;
    public List<Mur> listeMurs;
    public List<Toit> listeToit;
    public double hauteurPignon;
    public double hauteurRallonge;
    public String orientationToit;
    public double zoom;
    public float offsetX;
    public float offsetY;
    public double grilleP;

    private static final Map<String, Integer> MURS = new HashMap<>();

    static {
        MURS.put("FACADE", 0);
        MURS.put("ARRIERE", 1);
        MURS.put("DROITE", 3);
        MURS.put("GAUCHE", 2);
    }

    public ChaletCopie(double largeurChalet, double longueurChalet,
                       double epaisseurChalet, double angleToit,
                       double hauteurMurs, List<Mur> listeMurs,  String orientationToit) {
        this.largeurChalet = largeurChalet;
        this.longueurChalet = longueurChalet;
        this.hauteurMurs = hauteurMurs;
       // this.hauteurPignon = hauteurPignon;
        this.epaisseurChalet = epaisseurChalet;
        this.angleToit = angleToit;
        this.listeMurs = listeMurs;
        this.orientationToit = orientationToit;
        this.zoom = 1;
    }

    public void serialiserChalet(String cheminFichier) {
        CopieChalet copieChalet = new CopieChalet();
        copieChalet.largeurChalet = Chalet.largeurChalet;
        copieChalet.longueurChalet = Chalet.longueurChalet;
        copieChalet.hauteurMurs = Chalet.hauteurMurs;
        copieChalet.epaisseurChalet = Chalet.epaisseurChalet;
        copieChalet.angleToit = Chalet.angleToit;
        copieChalet.retraitChalet = Chalet.retraitChalet;
        copieChalet.listeMurs = Chalet.listeMurs;
        copieChalet.orientationToit = Chalet.orientationToit;
        copieChalet.zoom = Chalet.zoom;
        copieChalet.offsetX = Chalet.offsetX;
        copieChalet.offsetY = Chalet.offsetY;
        copieChalet.grilleP = Chalet.grilleP;

        try {
            FileOutputStream fileOut = new FileOutputStream(cheminFichier);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(copieChalet);
            System.out.println("Avant la sérialisation - Largeur du Chalet : " + copieChalet.largeurChalet);
            System.out.println("Avant la sérialisation - Longueur du Chalet : " + copieChalet.longueurChalet);

            out.close();
            fileOut.close();
            System.out.printf("Les données sérialisées sont enregistrées dans %s\n", cheminFichier);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static class CopieChalet implements Serializable {
        public double largeurChalet;
        public double longueurChalet;
        public double hauteurMurs;
        public double epaisseurChalet;
        public double angleToit;
        public double retraitChalet;
        public List<Mur> listeMurs;
        public List<Toit> listeToit;
        public double hauteurPignon;
        public double hauteurRallonge;
        public String orientationToit;
        public double zoom;
        public float offsetX;
        public float offsetY;
        public double grilleP;
    }

    public static void deserialiserChalet(String cheminFichier) {
        try {
            FileInputStream fileIn = new FileInputStream(cheminFichier);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            CopieChalet copieChalet = (CopieChalet) in.readObject();
            in.close();
            fileIn.close();

            // Copier les valeurs de copieChalet à la classe Chalet
            Chalet.largeurChalet = copieChalet.largeurChalet;
            Chalet.longueurChalet = copieChalet.longueurChalet;
            Chalet.hauteurMurs = copieChalet.hauteurMurs;
            Chalet.epaisseurChalet = copieChalet.epaisseurChalet;
            Chalet.angleToit = copieChalet.angleToit;
            Chalet.retraitChalet = copieChalet.retraitChalet;
            Chalet.listeMurs = copieChalet.listeMurs;
            //Chalet.listeToit = copieChalet.listeToit;
            //Chalet.hauteurPignon = copieChalet.hauteurPignon;
            //Chalet.hauteurRallonge = copieChalet.hauteurRallonge;
            Chalet.orientationToit = copieChalet.orientationToit;
            Controleur.setLongueurChalet(copieChalet.longueurChalet);
            Chalet.zoom = copieChalet.zoom;
            Chalet.offsetX = copieChalet.offsetX;
            Chalet.offsetY = copieChalet.offsetY;
            Chalet.grilleP = copieChalet.grilleP;

            System.out.println("Après la désérialisation :");
            System.out.println("Largeur du Chalet : " + Chalet.largeurChalet);
            System.out.println("Longueur du Chalet : " + Chalet.longueurChalet);
            // Ajouter d'autres champs si nécessaire
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
