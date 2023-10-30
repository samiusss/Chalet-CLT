package A23-Equipe9.domaine;


import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.LinkedList;


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

    public Mur(String MID, Color couleur, List<Accessoires> listeAccessoire, List<Point> listeSommets)
    {
        this.MID = MID;
        this.couleur = couleur;
        this.listeAccessoire = listeAccessoire;
        this.listeSommets = listeSommets;
    }

    public static void initialiserMurs(Mur murFacade, Mur murDroite, Mur murGauche, Mur murArriere)
    {
        mur[0] = murFacade;
        mur[1] = murDroite;
        mur[2] = murGauche;
        mur[3] = murArriere;
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
