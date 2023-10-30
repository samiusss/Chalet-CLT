
package A23-Equipe9.domain;
import java.awt.Point;

public class Fenetre extends Accessoires
{
    private Pouces largeur;
    private Pouces longueur;

    public Fenetre(Point point, String AID, Pouces largeur, Pouces longueur)
    {
        super(point, AID);
        this.largeur = largeur;
        this.longueur = longueur;
    }

    public Pouces getLargeur() {
        return largeur;
    }

    public Pouces getLongueur() {
        return longueur;
    }
}