package ui;

import Utilitaires.PointDouble;
import domain.Accessoires;
import domain.Chalet;
import domain.Controleur;
import domain.Mur;

import java.awt.*;

public class GaucheDrawer
{
    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;

    public GaucheDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g)
    {
        drawGauche(g);
    }

    private void drawGauche(Graphics g)
    {
        Chalet chalet = controleur.getChaletProduction();
        g.setColor(new Color(166, 1, 1));

        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();

        /*ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs

        //Dimensions du mur en 3D
        double epaisseurMur = Chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = Chalet.hauteurMurs;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = Chalet.largeurChalet;     // Largeur des murs venant de chalet
        double longueurMur = Chalet.longueurChalet;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);
*/
        chalet.initialiserMurGauche();
        Mur gauche = chalet.getMursUsines(0.2, "Nord").get(0); // mur gauche deja codé en bas

        // Accéder coord de Mur gauche de face (gc)
        PointDouble pointSupDroitgc = gauche.getSommetsMur().get(4);
        PointDouble pointSupGauchegc = gauche.getSommetsMur().get(5);
        PointDouble pointInfDroitgc = gauche.getSommetsMur().get(6);
        PointDouble pointInfGauchegc = gauche.getSommetsMur().get(7);

        double positionX = width/2 - pointInfDroitgc.getX()/2;
        double positionY = height/2 - pointInfDroitgc.getY()/2;

        int x1gc = (int) (pointInfGauchegc.getX() + positionX);
        int y1gc = (int) (pointInfGauchegc.getY() + positionY);

        int x2gc = (int) (pointInfDroitgc.getX() + positionX);
        int y2gc = (int) (pointInfDroitgc.getY() + positionY);

        int x3gc = (int) (pointSupGauchegc.getX() + positionX);
        int y3gc = (int) (pointSupGauchegc.getY() + positionY);

        int x4gc = (int) (pointSupDroitgc.getX() + positionX);
        int y4gc = (int) (pointSupDroitgc.getY() + positionY);


// Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsGaucheCote = {x1gc, x2gc, x3gc, x4gc};
        int[] yPointsGaucheCote = {y1gc, y2gc, y3gc, y4gc};

// Dessiner le polygone pour le mur facade de coté (gc)

        g.fillPolygon(xPointsGaucheCote, yPointsGaucheCote, 4);

    }
}
