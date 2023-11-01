package Domain;


import Utilitaires.Point3D;
import java.awt.Color;
import java.awt.Point;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.util.LinkedList;


public class Mur {
    private Color couleur; //On va pas creer un objet color non?
    private String MID;
    private List<Accessoire> listeAccessoire;
    private Map<String, List<Point>> sommetsMur; // rainures
    private static Mur[] murs = new Mur[4]; //pk ce truc existe ici? la classe ici est juste censee
    //produire un mur et c'est chalet qui va les lier et les mettre dans une liste

    public Mur()
    {
        listeAccessoire = new LinkedList<Accessoire>();
    }

    public void add(Accessoires accesoires)  // ajouter les accessoires dans une liste
    {
        listeAccessoire.add(accesoires);
    }

    public Mur(String MID, Color couleur, List<Accessoires> listeAccessoire, Map<String, List<Point>> sommetsMur)
    {
        this.MID = MID;
        this.couleur = couleur;
        this.listeAccessoire = listeAccessoire;
        this.sommetsMur = sommetsMur;
        sommetsMur.add()
        sommetsMur.add(new Point3D(longueurMur, largeurMur, epaisseurMur));

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
