package ui;

import Utilitaires.PointDouble;
import domain.Accessoires;
import domain.Chalet;
import domain.Controleur;
import domain.Mur;

import java.awt.*;

public class FacadeDrawer {

    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;

    public FacadeDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g)
    {
        drawFacade(g);
    }

    private void drawFacade(Graphics g)

    {
        Chalet chalet = controleur.getChaletProduction();

        g.setColor(new Color(110, 166, 166));
        double width = initialDimension.getWidth();
        double height = initialDimension.getHeight();
        // Centrer au milieu du drawingPanel
        double positionX = width/2;
        double positionY = height/2;
        /*ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";
        // Définir la couleur des murs

        //Dimensions du mur en 3D
        double epaisseurMur = Chalet.epaisseurChalet; // Épaisseur du mur test local
        double hauteurMurs = Chalet.hauteurMurs;      // Hauteur des murs, sera utilisée pour les vues de côté
        double largeurMur = Chalet.largeurChalet;     // Largeur des murs venant de chalet
        double longueurMur = Chalet.longueurChalet;
        double angleToit = 0.0;

        Chalet chalet = new Chalet(largeurMur, longueurMur, epaisseurMur, angleToit, hauteurMurs, listeMurs, orientationToit);*/
        chalet.initialiserMurFacade();
        // Accéder aux coordonnées de Mur: Facade
        Mur facade = chalet.getMursUsines(0.2, "Nord").get(0); // mur facade deja codé en bas
        // Accéder coord de Mur facade de face (fc)
        PointDouble pointSupDroitfc = facade.getSommetsMur().get(4);
        PointDouble pointSupGauchefc = facade.getSommetsMur().get(5);
        PointDouble pointInfDroitfc = facade.getSommetsMur().get(6);
        PointDouble pointInfGauchefc = facade.getSommetsMur().get(7);

        int x1fc = (int) (pointInfGauchefc.getX() + positionX);
        int y1fc = (int) (pointInfGauchefc.getY() + positionY);

        int x2fc = (int) (pointInfDroitfc.getX() + positionX);
        int y2fc = (int) (pointInfDroitfc.getY() + positionY);

        int x3fc = (int) (pointSupGauchefc.getX() + positionX);
        int y3fc = (int) (pointSupGauchefc.getY() + positionY);

        int x4fc = (int) (pointSupDroitfc.getX() + positionX);
        int y4fc = (int) (pointSupDroitfc.getY() + positionY);


        // Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsFacadeCote = {x1fc, x2fc, x3fc, x4fc};
        int[] yPointsFacadeCote = {y1fc, y2fc, y3fc, y4fc};

        // Dessiner le polygone pour le mur facade de coté (fc)
        g.fillPolygon(xPointsFacadeCote, yPointsFacadeCote, 4);
    }
}
