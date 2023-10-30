import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Mur {
    private Color couleur;
    private String MID;
    private List<Accessoires> listeAccessoire;
    private List<Point> listeSommets;

    public Mur(String MID, Color couleur, List<Accessoires> listeAccessoire, List<Point> listeSommets) {
        this.MID = MID;
        this.couleur = couleur;
        this.listeAccessoire = listeAccessoire;
        this.listeSommets = listeSommets;
    }

    public Color getCouleur() {
        return couleur;
    }

    public String getMID() {
        return MID;
    }

    public List<Accessoires> getListeAccessoire() {
        return listeAccessoire;
    }

    public List<Point> getListeSommets() {
        return listeSommets;
    }

    public void ajouterAccessoire(Accessoires accessoire) {
        /*
        if type_accessoire == porte
            accessoire newPorte = new accessoire(type, couleur)
         */

        listeAccessoire.add(accessoire);
    }

    public void supprimerAccessoire(Accessoires accessoire) {
        listeAccessoire.remove(accessoire);
    }
}

