import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Mur {
    private Color couleur;
    private String MID;
    private List<Accessoire> listeAccessoire;
    private List<Point> listeSommets;  // rainures
    private static Mur[] murs = new Mur[4];

    public Mur()
    {
        listeAccessoire = new LinkedList<Accessoire>();
    }
    public void add(Accessoires accesoires)  // ajouter les accessoires dans une liste
    {
        listeAccessoire.add(accesoires);
    }

    public Mur(String MID, Color couleur, List<Accessoires> listeAccessoire, List<Point> listeSommets) {
        this.MID = MID;
        this.couleur = couleur;
        this.listeAccessoire = listeAccessoire;
        this.listeSommets = listeSommets;
    }

    public static void initialiserMurs(Mur murFacade, Mur murDroite, Mur murGauche, Mur murArriere) {
        murListe[0] = murFacade;
        murListe[1] = murDroite;
        murListe[2] = murGauche;
        murListe[3] = murArriere;
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

}
