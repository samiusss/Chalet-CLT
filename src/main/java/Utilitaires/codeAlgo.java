package Utilitaires;
import domain.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnFloat;
import static domain.Chalet.determinerSommetsAccessoires;
public class codeAlgo {
    protected static void writeStlForRectangle(FileWriter writer, double debutRectangleX, double endRectangleX, double debutRectangleY, double endRectangleY, double z, String normalVector) throws IOException{
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + debutRectangleX + " " + debutRectangleY + " " + z + "\n");
        writer.write("vertex " + endRectangleX + " " + debutRectangleY + " " + z + "\n");
        writer.write("vertex " + endRectangleX + " " + endRectangleY + " " + z + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + debutRectangleX + " " + debutRectangleY + " " + z + "\n");
        writer.write("vertex " + endRectangleX + " " + endRectangleY + " " + z + "\n");
        writer.write("vertex " + debutRectangleX + " " + endRectangleY + " " + z + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
    }

    protected ArrayList<Point> createAccessoryIntersectionPoints(Mur mur){


        PointDouble pointInfGauchef = mur.getSommetsMur().get(0); // Je veux le premier sommet (index 0) // pointInfGauchef = pointInfGauche facade
        PointDouble pointSupGauchef = mur.getSommetsMur().get(1); // Je veux le deuxieme sommet (index 1) // pointSupGauchef = pointSupGauche facade
        PointDouble pointSupDroitf = mur.getSommetsMur().get(2); // Je veux le troisième sommet (index 2) // pointSupDroitf = pointSupDroit facade
        PointDouble pointInfDroitf = mur.getSommetsMur().get(3); // Je veux le quatrième sommet (index 3) // pointInfDroitf = pointInfDroit facade

        PointDouble rainureGauche1 = mur.getSommetsMur().get(8); // Je veux le neuvième sommet (index 8) // rainureGauche1 = rainureGauche1 facade
        PointDouble rainureGauche2 = mur.getSommetsMur().get(9); // Je veux le dixième sommet (index 9) // rainureGauche2 = rainureGauche2 facade
        PointDouble rainureDroite1 = mur.getSommetsMur().get(10); // Je veux le onzième sommet (index 10) // rainureDroite1 = rainureDroite1 facade
        PointDouble rainureDroite2 = mur.getSommetsMur().get(11); // Je veux le douzième sommet (index 11) // rainureDroite2 = rainureDroite2 facade


        double positionX = 0 ;
        double positionY = 0;

        int x1f = (int) (pointInfDroitf.getX() + positionX);
        int y1f = (int) (pointInfDroitf.getY() + positionY);
        int x1r1f = (int) (rainureDroite1.getX() + positionX);
        int y1r1f = (int) (rainureDroite1.getY() + positionY);
        int x1r2f = (int) (rainureDroite2.getX() + positionX);
        int y1r2f = (int) (rainureDroite2.getY() + positionY);

        int x2f = (int) (pointSupDroitf.getX() + positionX);
        int y2f = (int) (pointSupDroitf.getY() + positionY);

        int x3f = (int) (pointSupGauchef.getX() + positionX);
        int y3f = (int) (pointSupGauchef.getY() + positionY);
        int x3r1f = (int) (rainureGauche1.getX() + positionX);
        int y3r1f = (int) (rainureGauche1.getY() + positionY);
        int x3r2f = (int) (rainureGauche2.getX() + positionX);
        int y3r2f = (int) (rainureGauche2.getY() + positionY);

        int x4f = (int) (pointInfGauchef.getX() + positionX);
        int y4f = (int) (pointInfGauchef.getY() + positionY);


        int[] xPointsMur = {x1f, x1r2f, x1r1f, x2f, x3f, x3r2f, x3r1f, x4f};
        int[] yPointsMur = {y1f, y1r2f, y1r1f, y2f, y3f, y3r2f, y3r1f, y4f};

                /*
        Deuxieme Algo (plus simple):
        1. Ajouter les points des murs dans une liste
        2. Ajouter les points des accessoires dans une liste
        3. Enlever les doublons
        4. Ajouter les points d'intersection des lignes horizontales et verticales dans une liste
        5. Tri des points d'intersection par le X
        6. Creer les rectangles
        */


        // Initialisation des variables
        List<Integer> listHorizontalLines = new ArrayList<Integer>(); // lines
        List<Integer>  listVerticalLines = new ArrayList<Integer>(); // columns
        List<Point> listIntersectionPoints = new ArrayList<Point>(); //intersections Point



        // Ajout des points des murs
        for(int i=0; i<mur.getSommetsMur().size(); i++){
            listVerticalLines.add(xPointsMur[i]);
            listHorizontalLines.add(yPointsMur[i]);
        }
        // Ajout des points des accessoires
        List<Porte> listePorte = mur.getListePorte();
        for (Porte porte : listePorte) {
                listVerticalLines.add(porte.mousePoint.y);
                listHorizontalLines.add(porte.mousePoint.x);

        }
        // Enlever les doublons
        HashSet<Integer> set = new HashSet<>(listHorizontalLines);
        listHorizontalLines.clear();
        listHorizontalLines.addAll(set);

        set= new HashSet<>(listVerticalLines);
        listVerticalLines.clear();
        listVerticalLines.addAll(set);

        // Ajout des points d'intesection des lignes horizontales et verticales
        for (int i=0; i<listHorizontalLines.size(); i++) {
            for (int j=0; j<listVerticalLines.size(); j++) {
                listIntersectionPoints.add(new Point(listHorizontalLines.get(i), listVerticalLines.get(j)));
            }
        }

        // Tri des points d'intersection par le X
        listIntersectionPoints.sort(Comparator.comparingDouble(o -> o.y ));
        listIntersectionPoints.sort(Comparator.comparingDouble(o -> o.x));
        return (ArrayList<Point>) listIntersectionPoints;
    }

    protected List<ArrayList<Point>> createRectangles(ArrayList<Point> listIntersectionPoints) {

        List<ArrayList<Point>> rectangles = new ArrayList<>();
        int p1= 0; //pointer 1
        int p2= 1; //pointer 2

        while (listIntersectionPoints.get(p2).x != listIntersectionPoints.get(listIntersectionPoints.size() - 1).x ||
                listIntersectionPoints.get(p2).y != listIntersectionPoints.get(listIntersectionPoints.size() - 1).x) {
            if (listIntersectionPoints.get(p1).x == listIntersectionPoints.get(p2).x){
                p2++;
            }
            else if (listIntersectionPoints.get(p1+1).x== listIntersectionPoints.get(p2).x && listIntersectionPoints.size()!=4){
                p1++;
                p2++;
            }
            else{
                Point debut = listIntersectionPoints.get(p1); // Top left
                Point endRectangleX = listIntersectionPoints.get(p2); // Top right
                Point maxRectangle = listIntersectionPoints.get(p2 + 1); // Bottom right
                Point endRectangleY = listIntersectionPoints.get(p1 + 1);// Bottom left
                rectangles.add(new ArrayList<Point>(Arrays.asList(debut, endRectangleX, endRectangleY, maxRectangle)));
                p1++;
                p2++;
            }
        }
        return rectangles;
    }

    protected void removeRectanglesInAccessories(List<ArrayList<Point>> rectangles, Mur mur) {
        if (mur.getListePorte().isEmpty()) {
            return;
        }
        for (int i = 0; i < rectangles.size(); i++) {
            List<Porte> listePorte = mur.getListePorte();
            for (int j = 0; j < listePorte.size(); j++) {
                List<Point> accessoryPoints = Collections.singletonList(listePorte.get(j).mousePoint);
                boolean condition1 = PointUtils.greaterOrEqualsX(rectangles.get(i).get(0), accessoryPoints.get(0).x);
                boolean condition2 = PointUtils.lessOrEqualsX(rectangles.get(i).get(1), accessoryPoints.get(1).x);
                boolean condition3 = PointUtils.lessOrEqualsY(rectangles.get(i).get(2), accessoryPoints.get(2).y);
                boolean condition4 = PointUtils.greaterOrEqualsY(rectangles.get(i).get(0), accessoryPoints.get(0).y);
                if (condition1 && condition2 && condition3 && condition4) {
                    rectangles.remove(i);
                }
            }
        }
    }

    protected List<ArrayList<Point>> prepareRectangleForStl(Mur mur){
        ArrayList<Point> intersectionPoints = createAccessoryIntersectionPoints(mur);
        List <ArrayList<Point>> rectangles = createRectangles(intersectionPoints);
        removeRectanglesInAccessories(rectangles,mur);
        return rectangles;
    }
}
