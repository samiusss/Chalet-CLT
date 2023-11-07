package ui;

import Utilitaires.PointDouble;
import Utilitaires.Pouces;
import Utilitaires.pointPouces;
import domain.*;

import java.awt.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnPixels;

public class GaucheDrawer
{
    private Controleur controleur;
    public static Chalet chalet;
    private Accessoires accessoires;
    private Dimension initialDimension;
    public Mur gauche ; // mur arriere deja codé en bas

    public GaucheDrawer(Controleur controleur, Dimension initialDimension){
        this.controleur = controleur;
        this.initialDimension = initialDimension;

        Chalet chalet = controleur.getChaletProduction();
        this.gauche = controleur.gauche; // mur facade deja codé en bas
    }

    public void draw(Graphics g)
    {
        drawGauche(g);
        drawPorte(g);
        drawFenetre(g);
    }


    private void drawFenetre(Graphics g) {


        System.out.println("fenetreGAUCHE");

        g.setColor(new Color(204, 0, 102));

        List<Fenetre> listeFenetre = gauche.getListeFenetre();
        int lenghtlisteFenetre = listeFenetre.size();
        System.out.println(lenghtlisteFenetre);


        for (Fenetre fenetre : listeFenetre) {
            System.out.println("fenetreGAUCHE2");

            System.out.println(fenetre);

            Fenetre fenetreActuel = (Fenetre) fenetre;
            if (fenetreActuel != null) {

                // Appeler dimensions Fenetre
                Pouces largeurFenetre = Fenetre.FENETRE_LARGEUR_STANDARD;
                Pouces hauteurFenetre = Fenetre.FENETRE_HAUTEUR_STANDARD;
                //if bouton.listener add.fenetre activated:
                Point mousePoint = fenetre.mousePoint;

                pointPouces pointFenetreSupDroit = new pointPouces(fenetre.getPointPouces(mousePoint).getX().addPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().addPouces(fenetre.getHauteur().diviserPouces(2)));
                pointPouces pointFenetreSupGauche=new pointPouces(fenetre.getPointPouces(mousePoint).getX().substractPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().addPouces(fenetre.getHauteur().diviserPouces(2)));
                pointPouces pointFenetreInfGauche = new pointPouces(fenetre.getPointPouces(mousePoint).getX().substractPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().substractPouces(fenetre.getHauteur().diviserPouces(2)));
                pointPouces pointFenetreInfDroit = new pointPouces(fenetre.getPointPouces(mousePoint).getX().addPouces(fenetre.getLargeur().diviserPouces(2)),fenetre.getPointPouces(mousePoint).getY().substractPouces(fenetre.getHauteur().diviserPouces(2)));


                int x1 = convertirPoucesEnPixels(pointFenetreSupDroit.getX());
                int y1 = convertirPoucesEnPixels(pointFenetreSupDroit.getY());

                int x2 = convertirPoucesEnPixels(pointFenetreSupGauche.getX());
                int y2 = convertirPoucesEnPixels(pointFenetreSupGauche.getY());

                int x3 = convertirPoucesEnPixels(pointFenetreInfGauche.getX());
                int y3 = convertirPoucesEnPixels(pointFenetreInfGauche.getY());


                int x4 = convertirPoucesEnPixels(pointFenetreInfDroit.getX());
                int y4 = convertirPoucesEnPixels(pointFenetreInfDroit.getY());

                int[] xPoints = {x1, x2, x3, x4};
                int[] yPoints = {y1, y2, y3, y4};

                g.fillPolygon(xPoints, yPoints, 4);

            }
        }


    }

    private void drawPorte(Graphics g) {


        System.out.println("porteGAUHE");

        g.setColor(new Color(255, 255, 255));

        List<Porte> listePorte = gauche.getListePorte();
        int lenghtlistePorte = listePorte.size();
        System.out.println(lenghtlistePorte);


        for (Porte porte : listePorte) {
            System.out.println("porteGAUCHE2");

            System.out.println(porte);

            Porte porteActuel = (Porte) porte;
            if (porteActuel != null) {

                Pouces largeurPorte = Porte.PORTE_LARGEUR_STANDARD;
                Pouces hauteurPorte = Porte.PORTE_HAUTEUR_STANDARD;

                Point mousePoint = porte.mousePoint;

                pointPouces pointPorteSupDroit = new pointPouces(porte.getPointPouces(mousePoint).getX().addPouces(porte.getLargeur().diviserPouces(2)), porte.getPointPouces(mousePoint).getY().addPouces(porte.getHauteur().diviserPouces(2)));
                pointPouces pointPorteSupGauche = new pointPouces(porte.getPointPouces(mousePoint).getX().substractPouces(porte.getLargeur().diviserPouces(2)), porte.getPointPouces(mousePoint).getY().addPouces(porte.getHauteur().diviserPouces(2)));
                pointPouces pointPorteInfGauche = new pointPouces(porte.getPointPouces(mousePoint).getX().substractPouces(porte.getLargeur().diviserPouces(2)), new Pouces(0, 0, 1));
                pointPouces pointPorteInfDroit = new pointPouces(porte.getPointPouces(mousePoint).getX().addPouces(porte.getLargeur().diviserPouces(2)), new Pouces(0, 0, 1));


                int x1 = convertirPoucesEnPixels(pointPorteSupDroit.getX());
                int y1 = convertirPoucesEnPixels(pointPorteSupDroit.getY());

                int x2 = convertirPoucesEnPixels(pointPorteSupGauche.getX());
                int y2 = convertirPoucesEnPixels(pointPorteSupGauche.getY());

                int x3 = convertirPoucesEnPixels(pointPorteInfGauche.getX());
                int y3 = convertirPoucesEnPixels(pointPorteInfGauche.getY());


                int x4 = convertirPoucesEnPixels(pointPorteInfDroit.getX());
                int y4 = convertirPoucesEnPixels(pointPorteInfDroit.getY());

                int[] xPoints = {x1, x2, x3, x4};
                int[] yPoints = {y1, y2, y3, y4};

                g.fillPolygon(xPoints, yPoints, 4);

            }
        }


    }


    private void drawGauche(Graphics g)
    {
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
        //chalet.initialiserMurGauche();
        //Mur gauche = chalet.getMursUsines(0.2, "Nord").get(0); // mur gauche deja codé en bas

        // Accéder coord de Mur gauche de face (gc)
        PointDouble pointSupDroitgc = gauche.getSommetsMur().get(4);
        PointDouble pointSupGauchegc = gauche.getSommetsMur().get(5);
        PointDouble pointInfDroitgc = gauche.getSommetsMur().get(6);
        PointDouble pointInfGauchegc = gauche.getSommetsMur().get(7);


        PointDouble pointInfGaucheCadre = new PointDouble(0,0);
        PointDouble pointSupGaucheCadre = new PointDouble(0, gauche.getSommetsMur().get(5).getY());
        PointDouble pointSupDroitCadre = new PointDouble(gauche.getSommetsMur().get(5).getX() + gauche.getSommetsMur().get(4).getX(), (gauche.getSommetsMur().get(5).getY()));
        PointDouble pointInfDroitCadre = new PointDouble(gauche.getSommetsMur().get(5).getX() + gauche.getSommetsMur().get(4).getX(), 0);

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
        /////////////////////////////////
        int x1Cadre = (int) (pointInfGaucheCadre.getX() + positionX);
        int y1Cadre = (int) (pointInfGaucheCadre.getY() + positionY);

        int x2Cadre = (int) (pointSupGaucheCadre.getX() + positionX);
        int y2Cadre = (int) (pointSupGaucheCadre.getY() + positionY);

        int x3Cadre = (int) (pointSupDroitCadre.getX() + positionX);
        int y3Cadre = (int) (pointSupDroitCadre.getY() + positionY);

        int x4Cadre = (int) (pointInfDroitCadre.getX() + positionX);
        int y4Cadre = (int) (pointInfDroitCadre.getY() + positionY);

        int[] xPointsCadre = {x1Cadre, x2Cadre, x3Cadre, x4Cadre};
        int[] yPointsCadre = {y1Cadre, y2Cadre, y3Cadre, y4Cadre};

// Construire tableaux de coordonnées pour le mur facade de coté
        int[] xPointsGaucheCote = {x1gc, x2gc, x3gc, x4gc};
        int[] yPointsGaucheCote = {y1gc, y2gc, y3gc, y4gc};

// Dessiner le polygone pour le mur facade de coté (gc)
        //premiere rainure
        int[] PremiereRainurex = {x1Cadre, x2Cadre, x3gc, x1gc};
        int[] PremiereRainurey = {y1Cadre, y2Cadre, y3gc, y1gc};
        g.setColor(new Color(66, 66, 166));
        g.fillPolygon(PremiereRainurex, PremiereRainurey, 4);

        //mur gauche
        int[] murGauchex = {x1gc, x2gc, x3gc, x4gc};
        int[] murGauchey = {y1gc, y2gc, y3gc, y4gc};
        g.setColor(new Color(210, 68, 1));
        g.fillPolygon(murGauchex, murGauchey, 4);

        //troisieme rainure
        int[] rainureTroisiemex = {x4gc + x1gc, x4gc, x3gc + x2gc, x2gc};
        int[] rainureTroisiemey = {y4gc, y4gc, y2gc, y2gc};
        g.setColor(new Color(66, 66, 166));
        //g.fillPolygon(rainureTroisiemex, rainureTroisiemey, 4);

    }
}
