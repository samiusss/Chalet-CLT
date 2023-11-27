package Utilitaires;
import domain.Chalet;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static domain.Chalet.determinerSommetsAccessoires;


public class STLWriterPrincipal {

    public static void main(String[] args) {

    }





    // Méthode pour le traitement automatique des vertices
    private static String processVertex(float originalValue) {
        BigDecimal bd = new BigDecimal(Float.toString(originalValue));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String nombreAvecVirgule = bd.toPlainString();  // Utilisez toPlainString() pour éviter la notation scientifique

        String nombreAvecPoint = nombreAvecVirgule.replace(",", ".");
        return nombreAvecPoint;
    }


    public static List<float[]> determinerPointsPrismes(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
            /*Pour construire le panneau en 3D , on doit construire un prisme ,
            le prisme est constitue de 6 faces qui s'emboitent donc partangent
            les meme points en fonction d'une certaine épaisseur. Chacun de ces faces est constitue de deux triangles */

        float[] v0 = {xSupGauche, ySupGauche, zSupGauche};           // PointSupGaucheFacade (Top Left Front)
        float[] v1 = {xSupGauche + length, ySupGauche, zSupGauche};   // PointSupDroiteFacade (Top Right Front)
        float[] v2 = {xSupGauche + length, ySupGauche + width, zSupGauche};   // PointSupDroiteArriere (Top Right Back)
        float[] v3 = {xSupGauche, ySupGauche + width, zSupGauche};    // PointSupGaucheArriere (Top Left Back)
        float[] v4 = {xSupGauche, ySupGauche, zSupGauche + height};   // PointInfGaucheFacade (Bottom Left Front)
        float[] v5 = {xSupGauche + length, ySupGauche, zSupGauche + height};   // PointInfDroiteFacade (Bottom Right Front)
        float[] v6 = {xSupGauche + length, ySupGauche + width, zSupGauche + height};   // PointInfDroiteArriere (Bottom Right Back)
        float[] v7 = {xSupGauche, ySupGauche + width, zSupGauche + height};    // PointInfGaucheArriere (Bottom Left Back)

        // Correspondance pour les autres bases :
        // PointSupGaucheFacade correspond à PointInfGaucheFacade sur la base inférieure.
        // PointSupDroiteFacade correspond à PointInfDroiteFacade sur la base inférieure.
        // PointSupDroiteArriere correspond à PointInfDroiteArriere sur la base inférieure.
        // PointSupGaucheArriere correspond à PointInfGaucheArriere sur la base inférieure.

        // PointSupGaucheFacade correspond à PointSupDroiteFacade sur la base droite.
        // PointSupDroiteFacade correspond à PointSupGaucheFacade sur la base gauche.
        // PointSupDroiteArriere correspond à PointSupGaucheArriere sur la base gauche.
        // PointSupGaucheArriere correspond à PointSupDroiteArriere sur la base droite.

        // PointSupGaucheFacade correspond à PointSupGaucheArriere sur la base arrière.
        // PointSupDroiteFacade correspond à PointSupDroiteArriere sur la base arrière.
        // PointInfDroiteFacade correspond à PointInfDroiteArriere sur la base arrière.
        // PointInfGaucheFacade correspond à PointInfGaucheArriere sur la base arrière.


        List<float[]> listeVertex = new LinkedList<>();
        listeVertex.add(v0);
        listeVertex.add(v1);
        listeVertex.add(v2);
        listeVertex.add(v3);
        listeVertex.add(v4);
        listeVertex.add(v5);
        listeVertex.add(v6);
        listeVertex.add(v7);

        return listeVertex;


    }


    public static List<Triangle> generateRectangularPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
            /*Pour construire le panneau en 3D , on doit construire un prisme ,
            le prisme est constitue de 6 faces qui s'emboitent donc partangent
            les meme points en fonction d'une certaine épaisseur. Chacun de ces faces est constitue de deux triangles */
        List<Triangle> triangles = new ArrayList<>();

        List<float[]> listeVertex = determinerPointsPrismes(length,width,height,xSupGauche,ySupGauche,zSupGauche);

        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);

        // Front face
        triangles.add(new Triangle(v0, v1, v2));
        triangles.add(new Triangle(v0, v2, v3));

        // Back face
        triangles.add(new Triangle(v4, v5, v6));
        triangles.add(new Triangle(v4, v6, v7));

        // Left face
        triangles.add(new Triangle(v0, v3, v7));
        triangles.add(new Triangle(v0, v7, v4));

        // Right face
        triangles.add(new Triangle(v1, v5, v6));
        triangles.add(new Triangle(v1, v6, v2));

        // Top face
        triangles.add(new Triangle(v2, v6, v7));
        triangles.add(new Triangle(v2, v7, v3));

        // Bottom face
        triangles.add(new Triangle(v0, v1, v5));
        triangles.add(new Triangle(v0, v5, v4));


        // Front face
       /* triangles.add(new Triangle(v0, v1, v2));
        triangles.add(new Triangle(v0, v2, v3));

        // Back face
        triangles.add(new Triangle(v4, v6, v5));
        triangles.add(new Triangle(v4, v7, v6));

        // Left face
        triangles.add(new Triangle(v0, v4, v3));
        triangles.add(new Triangle(v4, v7, v3));
        /*A   B
          C
               B
          C    E

        // Right face
        triangles.add(new Triangle(v1, v5, v2));
        triangles.add(new Triangle(v2, v5, v6));

        // Top face
        triangles.add(new Triangle(v3, v7, v2));
        triangles.add(new Triangle(v2, v7, v6));

        // Bottom face
        triangles.add(new Triangle(v0, v1, v5));
        triangles.add(new Triangle(v0, v5, v4)); */

        return triangles;
    }


    public static float[] calculerNormale(float[] pointA, float[] pointB, float[] pointC) {
        // Calculez les vecteurs AB et AC
        float[] vecteurAB = {pointB[0] - pointA[0], pointB[1] - pointA[1], pointB[2] - pointA[2]};
        float[] vecteurAC = {pointC[0] - pointA[0], pointC[1] - pointA[1], pointC[2] - pointA[2]};

        // Calculez le produit vectoriel des vecteurs AB et AC pour obtenir la normale
        float[] normal = {
                vecteurAB[1] * vecteurAC[2] - vecteurAB[2] * vecteurAC[1],
                vecteurAB[2] * vecteurAC[0] - vecteurAB[0] * vecteurAC[2],
                vecteurAB[0] * vecteurAC[1] - vecteurAB[1] * vecteurAC[0]
        };

        // Normalisez le vecteur résultant (mettez-le à l'échelle pour qu'il ait une longueur de 1)
        float longueur = (float) Math.sqrt(normal[0]*normal[0] + normal[1]*normal[1] + normal[2]*normal[2]);
        normal[0] /= longueur;
        normal[1] /= longueur;
        normal[2] /= longueur;

        return normal;
    }



    // Méthode pour générer les points du prisme à partir des sommets du rectangle et de l'épaisseur
    public static Point[] genererPrisme(Point pointSuperieurGauche, Point pointSuperieurDroit,
                                        Point pointInferieurDroit, Point pointInferieurGauche,
                                        double epaisseur) {
        // Initialisation des points du prisme
        Point[] pointsPrisme = new Point[8];

        // Points de la base inférieure du prisme (rectangle)
        pointsPrisme[0] = pointSuperieurGauche;
        pointsPrisme[1] = pointSuperieurDroit;
        pointsPrisme[2] = pointInferieurDroit;
        pointsPrisme[3] = pointInferieurGauche;

        // Points de la base supérieure du prisme (rectangle décalé selon l'épaisseur)
        pointsPrisme[4] = new Point((int) (pointSuperieurGauche.getX() + epaisseur),
                (int) (pointSuperieurGauche.getY() + epaisseur));
        pointsPrisme[5] = new Point((int) (pointSuperieurDroit.getX() - epaisseur),
                (int) (pointSuperieurDroit.getY() + epaisseur));
        pointsPrisme[6] = new Point((int) (pointInferieurDroit.getX() - epaisseur),
                (int) (pointInferieurDroit.getY() - epaisseur));
        pointsPrisme[7] = new Point((int) (pointInferieurGauche.getX() + epaisseur),
                (int) (pointInferieurGauche.getY() - epaisseur));

            /* Base Supérieure:

            Point 1 (Supérieur Gauche): pointsPrisme[4]
            Point 2 (Supérieur Droit): pointsPrisme[5]
            Point 3 (Inférieur Droit): pointsPrisme[6]
            Point 4 (Inférieur Gauche): pointsPrisme[7]
            Base Inférieure:

            Point 1 (Supérieur Gauche): pointsPrisme[0]
            Point 2 (Supérieur Droit): pointsPrisme[1]
            Point 3 (Inférieur Droit): pointsPrisme[2]
            Point 4 (Inférieur Gauche): pointsPrisme[3]
            Base Gauche (vue de face):

            Point 1 (Supérieur Gauche): pointsPrisme[0]
            Point 2 (Supérieur Droit): pointsPrisme[4]
            Point 3 (Inférieur Droit): pointsPrisme[7]
            Point 4 (Inférieur Gauche): pointsPrisme[3]
            Base Droite (vue de face):

            Point 1 (Supérieur Gauche): pointsPrisme[1]
            Point 2 (Supérieur Droit): pointsPrisme[5]
            Point 3 (Inférieur Droit): pointsPrisme[6]
            Point 4 (Inférieur Gauche): pointsPrisme[2]
            Base Arrière (vue de face):

            Point 1 (Supérieur Gauche): pointsPrisme[4]
            Point 2 (Supérieur Droit): pointsPrisme[5]
            Point 3 (Inférieur Droit): pointsPrisme[6]
            Point 4 (Inférieur Gauche): pointsPrisme[7]

            * */

        return pointsPrisme;
    }


    public static List<Triangle> decomposerRectangleTriangle(Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit, Double EpaisseurChalet){
        /* Un rectangle est composer de deux triangles */

        Double Epaisseur = 8.0;

        float[] vertexTriangle1_a = {(float) coinSupGauche.getX(), (float) coinSupGauche.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle1_b= {(float) coinSupDroit.getX(), (float) coinSupDroit.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle1_c = {(float) coinInfGauche.getX(), (float) coinInfGauche.getY(), Epaisseur.floatValue()};

        float[] vertexTriangle2_a = {(float) coinInfDroit.getX(), (float) coinInfDroit.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle2_b = {(float) coinInfGauche.getX(), (float) coinInfGauche.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle2_c = {(float) coinSupDroit.getX(), (float) coinSupDroit.getY(), Epaisseur.floatValue()};

        Triangle triangle1 = new Triangle(vertexTriangle1_a,vertexTriangle1_b,vertexTriangle1_c);
        Triangle triangle2 = new Triangle(vertexTriangle2_a,vertexTriangle2_b,vertexTriangle2_c);


        List<Triangle> listeTriangle = new LinkedList<>();
        listeTriangle.add(triangle1);
        listeTriangle.add(triangle2);


        return listeTriangle;

    }

    public static void generateSTL(List<Triangle> triangles, String fileName) {
        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            // Écrire l'en-tête du fichier STL
            fileWriter.write("solid generated\n");

            // Écrire les triangles
            for (Triangle triangle : triangles) {


                float[] normal = calculerNormale(triangle.vertex1, triangle.vertex2,triangle.vertex3);

                // Remplacement pour le vertex 1
                String normalA = processVertex(normal[0]);

                String normalB = processVertex(normal[1]);

                String normalC = processVertex(normal[2]);

                // Remplacement pour le vertex 1
                String vertex1a = processVertex(triangle.vertex1[0]);
                //float vertex1a = Float.parseFloat(vertex1String);

                String vertex1b = processVertex(triangle.vertex1[1]);

                String vertex1c = processVertex(triangle.vertex1[2]);

                // Remplacement pour le vertex 2
                String vertex2a = processVertex(triangle.vertex2[0]);

                String vertex2b = processVertex(triangle.vertex2[1]);

                String vertex2c = processVertex(triangle.vertex2[2]);

                // Remplacement pour le vertex 3
                String vertex3a = processVertex(triangle.vertex3[0]);

                String vertex3b = processVertex(triangle.vertex3[1]);

                String vertex3c = processVertex(triangle.vertex3[2]);

                fileWriter.write("  facet normal " +normalA+" "+normalB+" "+ normalC+ System.getProperty("line.separator")
                );
                fileWriter.write("    outer loop\n");


                fileWriter.write(String.format("     vertex "+vertex1a+" "+vertex1b+" "+vertex1c+ System.getProperty("line.separator")));
                fileWriter.write(String.format("     vertex "+vertex2a+" "+vertex2b+" "+vertex2c+ System.getProperty("line.separator")));
                fileWriter.write(String.format("     vertex "+vertex3a+" "+vertex3b+" "+vertex3c+ System.getProperty("line.separator")));

                fileWriter.write("    endloop\n");
                fileWriter.write("  endfacet\n");
            }

            // Écrire le pied de page du fichier STL
            fileWriter.write("endsolid generated\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Fichier STL généré avec succès : " + fileName);
    }



    public static void ExporterPanneauxBrut(String fileName) {
        Point point = new Point(0,0);
        //determinerSommetsAccessoires fait le meme traitement dont on a besoin d'ou son utlisation ici.
        List<Point> listPointPanneaux = determinerSommetsAccessoires(point,100, 100);

        Point SupGauche = listPointPanneaux.get(0);
        Point SupDroit = listPointPanneaux.get(1);
        Point InfGauche = listPointPanneaux.get(2);
        Point InfDroit = listPointPanneaux.get(3);

        double epaisseurChalet = Chalet.epaisseurChalet;
        //double epaisseur = 15.0;

        List<Triangle> listeTriangles = generateRectangularPrism(100,(float) epaisseurChalet, 50,0,0,0);
        generateSTL(listeTriangles,fileName);

    }



    public static List<Triangle> ExporterPanneauxRetraitDroite(float[] SupGauche, float length, float width, float height){

        /* MUR DROITE */
        // Le point SupGauche du mur droite correspond au point superieur gauche de la base arriere du mur de facade v3

        float lengthPrincipal = length - length/10;

        double epaisseurChalet = Chalet.epaisseurChalet;
        float thickness = (float) (epaisseurChalet);


        // Generer le prisme de base
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, width,height, SupGauche[0],SupGauche[1],SupGauche[2]);

        // Determiner les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length,width,height,SupGauche[0],SupGauche[1],SupGauche[2]);

        // Determiner les points de prismes qui seront d'une longueur determine et
        // d'épaisseur correspondant a la moitie de l'épaisseur du prisme de base afin de construire les rainures gauches et droites
        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);


        // v0 // PointSupGaucheFacade (Top Left Front)
        // v1 // PointSupDroiteFacade (Top Right Front)
        // v2 // PointSupDroiteArriere (Top Right Back)
        // v3 // PointSupGaucheArriere (Top Left Back)
        // v4 // PointInfGaucheFacade (Bottom Left Front)
        // v5 // PointInfDroiteFacade (Bottom Right Front)
        // v6 // PointInfDroiteArriere (Bottom Right Back)
        // v7 // PointInfGaucheArriere (Bottom Left Back)

        // Correspondance pour les autres bases :
        // PointSupGaucheFacade correspond à PointInfGaucheFacade sur la base inférieure.
        // PointSupDroiteFacade correspond à PointInfDroiteFacade sur la base inférieure.
        // PointSupDroiteArriere correspond à PointInfDroiteArriere sur la base inférieure.
        // PointSupGaucheArriere correspond à PointInfGaucheArriere sur la base inférieure.

        // PointSupGaucheFacade correspond à PointSupDroiteFacade sur la base droite.
        // PointSupDroiteFacade correspond à PointSupGaucheFacade sur la base gauche.
        // PointSupDroiteArriere correspond à PointSupGaucheArriere sur la base gauche.
        // PointSupGaucheArriere correspond à PointSupDroiteArriere sur la base droite.

        // PointSupGaucheFacade correspond à PointSupGaucheArriere sur la base arrière.
        // PointSupDroiteFacade correspond à PointSupDroiteArriere sur la base arrière.
        // PointInfDroiteFacade correspond à PointInfDroiteArriere sur la base arrière.
        // PointInfGaucheFacade correspond à PointInfGaucheArriere sur la base arrière.

        // Dimensions du prisme secondaire
        float lengthSecondaire = length/10;
        float widthSecondaire = (float) (width / 2);
        float heightSecondaire = height;

        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        float thicknessSecondaire = (float) (width / 2);


        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, heightSecondaire,SupGauche[0]-lengthSecondaire,SupGauche[1],SupGauche[2]);
        listeTriangles.addAll(listeTrianglesGauche);

        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, heightSecondaire,v1[0] - lengthSecondaire ,SupGauche[1],SupGauche[2]);
        listeTriangles.addAll(listeTrianglesDroite);

        return listeTriangles;


    }


    public static List<Triangle> ExporterPanneauxRetraitGauche(float[] SupGauche, float length, float width, float height){

        /* MUR GAUUCHE */
        // Le point SupGauche du mur droite correspond au point superieur droite de la base arriere du mur de facade v2

        float lengthPrincipal = length - length/10;

        double epaisseurChalet = Chalet.epaisseurChalet;
        float thickness = (float) (epaisseurChalet);


        // Generer le prisme de base
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, width,height, SupGauche[0],SupGauche[1],SupGauche[2]);

        // Determiner les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length,width,height,SupGauche[0],SupGauche[1],SupGauche[2]);

        // Determiner les points de prismes qui seront d'une longueur determine et
        // d'épaisseur correspondant a la moitie de l'épaisseur du prisme de base afin de construire les rainures gauches et droites
        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);


        // v0 // PointSupGaucheFacade (Top Left Front)
        // v1 // PointSupDroiteFacade (Top Right Front)
        // v2 // PointSupDroiteArriere (Top Right Back)
        // v3 // PointSupGaucheArriere (Top Left Back)
        // v4 // PointInfGaucheFacade (Bottom Left Front)
        // v5 // PointInfDroiteFacade (Bottom Right Front)
        // v6 // PointInfDroiteArriere (Bottom Right Back)
        // v7 // PointInfGaucheArriere (Bottom Left Back)

        // Correspondance pour les autres bases :
        // PointSupGaucheFacade correspond à PointInfGaucheFacade sur la base inférieure.
        // PointSupDroiteFacade correspond à PointInfDroiteFacade sur la base inférieure.
        // PointSupDroiteArriere correspond à PointInfDroiteArriere sur la base inférieure.
        // PointSupGaucheArriere correspond à PointInfGaucheArriere sur la base inférieure.

        // PointSupGaucheFacade correspond à PointSupDroiteFacade sur la base droite.
        // PointSupDroiteFacade correspond à PointSupGaucheFacade sur la base gauche.
        // PointSupDroiteArriere correspond à PointSupGaucheArriere sur la base gauche.
        // PointSupGaucheArriere correspond à PointSupDroiteArriere sur la base droite.

        // PointSupGaucheFacade correspond à PointSupGaucheArriere sur la base arrière.
        // PointSupDroiteFacade correspond à PointSupDroiteArriere sur la base arrière.
        // PointInfDroiteFacade correspond à PointInfDroiteArriere sur la base arrière.
        // PointInfGaucheFacade correspond à PointInfGaucheArriere sur la base arrière.

        // Dimensions du prisme secondaire
        float lengthSecondaire = length/10;
        float widthSecondaire = (float) (width / 2);
        float heightSecondaire = height;

        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        float thicknessSecondaire = (float) (width / 2);


        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, heightSecondaire,SupGauche[0]-lengthSecondaire,SupGauche[1],SupGauche[2]);
        listeTriangles.addAll(listeTrianglesGauche);

        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, heightSecondaire,v1[0] - lengthSecondaire ,SupGauche[1],SupGauche[2]);
        listeTriangles.addAll(listeTrianglesDroite);

        return listeTriangles;


    }


    public static List<Rectangle> genererMur(Point mousePointMur, float longueurMur, float hauteurMur, float epaisseurMur,
                                             Point mousePointAccessoire, float longueurAccessoire, float hauteurAccessoire, float epaisseurAccessoire) {

        List<Rectangle> rectangles = new ArrayList<>();

        Rectangle mur = new Rectangle(mousePointMur, longueurMur, hauteurMur);
        Rectangle accessoire = new Rectangle(mousePointAccessoire, longueurAccessoire, hauteurAccessoire);

        // Calcul des positions des rectangles
        Rectangle murGauche = new Rectangle(new Point((int) (mur.mousePoint.x - epaisseurMur), mur.mousePoint.y), epaisseurMur, mur.height);
        Rectangle murDroite = new Rectangle(new Point((int) (mur.mousePoint.x + mur.length), mur.mousePoint.y), epaisseurMur, mur.height);
        Rectangle murHaut = new Rectangle(new Point(murGauche.mousePoint.x, murGauche.mousePoint.y - (int) epaisseurMur), mur.length + murDroite.length + (int) epaisseurMur, epaisseurMur);

        rectangles.add(murGauche);
        rectangles.add(murDroite);
        rectangles.add(murHaut);

        return rectangles;
    }
    // Méthode pour diviser le rectangle en petits rectangles avec une distance de grille donnée
    public static List<Rectangle> diviserEnRectangles(float distanceDeGrille, Point pointSuperieurGauche, float length, float height) {
        // Vérifier que la distance de grille est valide
        if (distanceDeGrille <= 0) {
            throw new IllegalArgumentException("La distance de grille doit être supérieure à zéro.");
        }

        // Calculer le nombre de petits rectangles dans la longueur et la largeur
        int nombreDeRectanglesLongueur = (int) Math.ceil(length / distanceDeGrille);
        int nombreDeRectanglesLargeur = (int) Math.ceil(height / distanceDeGrille);

        // Calculer la nouvelle longueur et largeur des petits rectangles
        float nouvelleLongueur = length / nombreDeRectanglesLongueur;
        float nouvelleLargeur = height / nombreDeRectanglesLargeur;

        // Créer et stocker les petits rectangles dans une liste
        List<Rectangle> petitsRectangles = new ArrayList<>();

        for (int i = 0; i < nombreDeRectanglesLongueur; i++) {
            for (int j = 0; j < nombreDeRectanglesLargeur; j++) {
                // Calculer le coin supérieur gauche de chaque petit rectangle
                float nouveauX = pointSuperieurGauche.x + i * nouvelleLongueur;
                float nouveauY = pointSuperieurGauche.y + j * nouvelleLargeur;

                // Créer le petit rectangle et l'ajouter à la liste
                petitsRectangles.add(new Rectangle(new Point((int) nouveauX, (int) nouveauY), nouvelleLongueur, nouvelleLargeur));
            }
        }

        return petitsRectangles;
    }


    public static List<Triangle> ExporterPanneauxRetraitArriere(float[] SupGauche, float length, float width, float height) {

        // Set lengthPrincipal to the desired length of the main prism
        float lengthPrincipal = length - length / 10;

        // Set the desired thickness of the main prism
        double epaisseurChalet = Chalet.epaisseurChalet;
        float thicknessPrismePrincipal = (float) epaisseurChalet;

        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal,height, (float) epaisseurChalet, SupGauche[0], SupGauche[1], 0);


        // Generer le prisme de base

        // Determiner les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length,width,height,SupGauche[0],SupGauche[1],SupGauche[2]);

        // Determiner les points de prismes qui seront d'une longueur determine et
        // d'épaisseur correspondant a la moitie de l'épaisseur du prisme de base afin de construire les rainures gauches et droites
        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);


        // Dimensions du prisme secondaire
        float lengthSecondaire = length/10;
        float widthSecondaire = (float) (width / 2);
        float heightSecondaire = height;

        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        float thicknessSecondaire = (float) (width / 2);


        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, heightSecondaire,SupGauche[0]-lengthSecondaire,SupGauche[1],SupGauche[2]);
        listeTriangles.addAll(listeTrianglesGauche);

        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, heightSecondaire,v1[0] - lengthSecondaire ,SupGauche[1],SupGauche[2]);
        listeTriangles.addAll(listeTrianglesDroite);


        return listeTriangles;
    }


    public static void ExporterPanneauxRetrait(String fileName, String fileNameDroite, String fileNameChalet, String fileNameGauches, String fileNameArriere) throws IOException {
        //determinerSommetsAccessoires fait le meme traitement dont on a besoin d'ou son utlisation ici.
        //List<Point> listPointPanneaux = Chalet.determinerSommetsAccessoires(point,100, 100);

        //Point SupGauche = listPointPanneaux.get(0);
        //Point SupDroit = listPointPanneaux.get(1);
        //Point InfGauche = listPointPanneaux.get(2);
        //Point InfDroit = listPointPanneaux.get(3);
        float[] supGauche = {0,0,0};

        double epaisseurChalet = Chalet.epaisseurChalet;
        //double epaisseur = 15.0;

        // Dimensions du prisme principal
        float length = 100;
        //On prend en compte les rainures qui correpondent a 1/10 de la longeur du prisme. Ainsi on
        float lengthPrincipal = length - length/10;
        float width = 25; // thickness
        float height = 50;

        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        float thickness = (float) (epaisseurChalet);

        // Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = 0f;
        float ySupGauche = 0f;
        float zSupGauche = 0f;

        /* MUR FACADE */
        // Generer le prisme de base
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thickness, 50,xSupGauche,ySupGauche,xSupGauche);

        // Determiner les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length,width,height,xSupGauche,ySupGauche,zSupGauche);

        // Determiner les points de prismes qui seront d'une longueur determine et
        // d'épaisseur correspondant a la moitie de l'épaisseur du prisme de base afin de construire les rainures gauches et droites
        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);


        // v0 // PointSupGaucheFacade (Top Left Front)
        // v1 // PointSupDroiteFacade (Top Right Front)
        // v2 // PointSupDroiteArriere (Top Right Back)
        // v3 // PointSupGaucheArriere (Top Left Back)
        // v4 // PointInfGaucheFacade (Bottom Left Front)
        // v5 // PointInfDroiteFacade (Bottom Right Front)
        // v6 // PointInfDroiteArriere (Bottom Right Back)
        // v7 // PointInfGaucheArriere (Bottom Left Back)

        // Correspondance pour les autres bases :
        // PointSupGaucheFacade correspond à PointInfGaucheFacade sur la base inférieure.
        // PointSupDroiteFacade correspond à PointInfDroiteFacade sur la base inférieure.
        // PointSupDroiteArriere correspond à PointInfDroiteArriere sur la base inférieure.
        // PointSupGaucheArriere correspond à PointInfGaucheArriere sur la base inférieure.

        // PointSupGaucheFacade correspond à PointSupDroiteFacade sur la base droite.
        // PointSupDroiteFacade correspond à PointSupGaucheFacade sur la base gauche.
        // PointSupDroiteArriere correspond à PointSupGaucheArriere sur la base gauche.
        // PointSupGaucheArriere correspond à PointSupDroiteArriere sur la base droite.

        // PointSupGaucheFacade correspond à PointSupGaucheArriere sur la base arrière.
        // PointSupDroiteFacade correspond à PointSupDroiteArriere sur la base arrière.
        // PointInfDroiteFacade correspond à PointInfDroiteArriere sur la base arrière.
        // PointInfGaucheFacade correspond à PointInfGaucheArriere sur la base arrière.

        // Dimensions du prisme secondaire
        float lengthSecondaire = length/10;
        float widthSecondaire = width;
        float heightSecondaire = height;

        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        float thicknessSecondaire = (float) (thickness / 2);


        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, height,xSupGauche-lengthSecondaire,ySupGauche,zSupGauche);
        listeTriangles.addAll(listeTrianglesGauche);

        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire,thicknessSecondaire, height,v1[0] - lengthSecondaire ,ySupGauche,zSupGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        // MUR DROITE
        List<Triangle> trianglesDroites = ExporterPanneauxRetraitDroite(v3,length,width,height);

        //MUR GAUCHE
        List<Triangle> trianglesGauche = ExporterPanneauxRetraitGauche(v2,length,width,height);

        //MUR ARRIERE
        List<Triangle> trianglesArriere  = ExporterPanneauxRetraitArriere(supGauche,length,width,height);


        //CHALET
        List<Triangle> triangleChalet = new LinkedList<>();
        triangleChalet.addAll(listeTriangles);
        triangleChalet.addAll(trianglesDroites);
        triangleChalet.addAll(trianglesGauche);
        triangleChalet.addAll(trianglesArriere);



        generateSTL(listeTriangles,fileName);
        generateSTL(trianglesDroites,fileNameDroite);
        generateSTL(trianglesGauche,fileNameGauches);
        generateSTL(trianglesArriere,fileNameArriere);

        generateSTL(triangleChalet,fileNameChalet);



    }



}
