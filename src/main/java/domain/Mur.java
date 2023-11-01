package domain;


import java.awt.Point;
import java.util.HashMap;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        private Map<String, List<Point>> sommetsMurs;
        private Map<String, List<String>> accessoiresMurs;

        public Mur() {
            sommetsMurs = new HashMap<>();
            accessoiresMurs = new HashMap<>();
        }

        // Méthode pour créer un mur rectangulaire 3D avec des coordonnées et des accessoires
        public void creerMur(String nomMur, List<Point> coordonnees, List<String> accessoires) {
            sommetsMurs.put(nomMur, coordonnees);
            accessoiresMurs.put(nomMur, accessoires);
        }

        // Méthode pour obtenir les coordonnées d'un mur spécifique
        public List<Point> getSommetsMur(String nom) {
            return sommetsMurs.get(nom);
        }

        // Méthode pour obtenir les accessoires d'un mur spécifique
        public List<String> getAccessoiresMur(String nom) {
            return accessoiresMurs.get(nom);
        }

    }

//}