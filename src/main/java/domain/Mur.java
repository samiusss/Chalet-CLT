package domain;


import Utilitaires.PointDouble;

import java.awt.Point;
import java.util.*;
//import java.util.LinkedList;


//public class Mur {
//    private Chalet chalet;
//    private String MID;
//    private List<Accessoires> listeAccessoire;
//    private Map<String, List<Point>> sommetsMur; // rainures
//
//    //public Map<String, List<Point>> getListeSommets() {
//    //    return this.sommetsMur = sommetsMur;
//    //}
//
//    Chalet chalet = new Chalet(10.0, 15.0, epaisseurChalet, 30.0, new List<>(), "Nord");
//
//    public void add(Accessoires accesoires)  // ajouter les accessoires dans une liste
//    {
//        listeAccessoire.add(accesoires);
//    }
//
//    public Mur(String MID, List<Accessoires> listeAccessoire, Map<String, List<Point>> sommetsMur)
//    {
//        this.MID = MID;
//        this.listeAccessoire = listeAccessoire;
//        this.sommetsMur = sommetsMur;
//        //sommetsMur.add();
//        sommetsMur(new Point3D(Chalet.longueurChalet, Chalet.largeurMur, epaisseurMur));
//
//    }
//
//
//    public String getMID() {
//        return MID;
//    }
//
//    public List<Accessoires> getListeAccessoire() {
//        return listeAccessoire;
//    }

    public class Mur {
        private String nomMur;
        private List<PointDouble> sommetsMur;
        private List<String> accessoiresMur;

        // on peut creer un mur simple
        public Mur() {
            this.nomMur = nomMur;
            this.sommetsMur = sommetsMur; // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
            this.accessoiresMur = accessoiresMur; // ceci est comme ci: [Liste de accessoires]
        }

        // deuxieme constructeur pour creer un mur avec 4 points et des accessoires et un nom
        // ce meme constructeur est appelle a la ligne
        public Mur(String nomMur, List<PointDouble> coordonnees, List<String> accessoiresMur) {
            this.nomMur = nomMur;
            this.sommetsMur = sommetsMur; // ceci est comme ci: [Point(0,0), Point(10,0), Point(10,5), Point(0,5)]
            this.accessoiresMur = accessoiresMur; // ceci est comme ci: [Liste de accessoires]
        }
        public void creerMur(String nomMur, PointDouble pointInfGauche, PointDouble pointSupGauche, PointDouble pointSupDroit, PointDouble pointInfDroit, List<String> accessoiresMur) {
            List<PointDouble> coordonnees = new ArrayList<>();
            coordonnees.add(pointInfGauche);
            coordonnees.add(pointSupGauche);
            coordonnees.add(pointSupDroit);
            coordonnees.add(pointInfDroit);

            Mur mur = new Mur(nomMur, coordonnees, accessoiresMur);
        }
        // Méthode pour créer un mur rectangulaire 3D avec des coordonnées et des accessoires
/*        public void creerMur(String nomMur, List<PointDouble> coordonnees, List<String> accessoires) {
            sommetsMur.put(nomMur, coordonnees);
            accessoiresMurs.put(nomMur, accessoires);
        }*/


        // Méthode pour obtenir les coordonnées d'un mur spécifique
        /*public List<Point> getSommetsMur(String nom) {
            return sommetsMurs.get(nom);
        }*/

        // Méthode pour obtenir les accessoires d'un mur spécifique
        /*public List<String> getAccessoiresMur(String nom) {
            return accessoiresMurs.get(nom);
        }*/


    }

//}