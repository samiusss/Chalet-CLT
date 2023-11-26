package Utilitaires;

import domain.Chalet;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;


public class STLWriter {

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

    private static List<Triangle> createPrism(double length, double width, double height, int rows, int cols) {
        List<Triangle> triangles = new ArrayList<>();

        double deltaX = length / cols;
        double deltaY = width / rows;
        double deltaZ = height;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Calculate vertices of the current small rectangle on the base facade
                double x1 = j * deltaX;
                double y1 = i * deltaY;
                double z1 = 0.0;

                double x2 = (j + 1) * deltaX;
                double y2 = i * deltaY;
                double z2 = 0.0;

                double x3 = j * deltaX;
                double y3 = (i + 1) * deltaY;
                double z3 = 0.0;

                double x4 = (j + 1) * deltaX;
                double y4 = (i + 1) * deltaY;
                double z4 = 0.0;

                // Create a Triangle object for the current small rectangle on the base facade
                float[] verticesBase1 = {(float) x1, (float) y1, (float) z1};
                float[] verticesBase2 = {(float) x2, (float) y2, (float) z2};
                float[] verticesBase3 = {(float) x3, (float) y3, (float) z3};

                Triangle triangleBase1 = new Triangle(verticesBase1, verticesBase2, verticesBase3);

                // Create another Triangle object for the other diagonal of the rectangle on the base facade
                float[] verticesBase4 = {(float) x2, (float) y2, (float) z2};
                float[] verticesBase5 = {(float) x4, (float) y4, (float) z4};
                float[] verticesBase6 = {(float) x3, (float) y3, (float) z3};

                Triangle triangleBase2 = new Triangle(verticesBase4, verticesBase5, verticesBase6);

                // Add both triangles to the list
                triangles.add(triangleBase1);
                triangles.add(triangleBase2);

                // Create triangles for the corresponding rectangles on the top facade
                float[] verticesTop1 = {(float) x1, (float) y1, (float) (z1 + deltaZ)};
                float[] verticesTop2 = {(float) x2, (float) y2, (float) (z2 + deltaZ)};
                float[] verticesTop3 = {(float) x3, (float) y3, (float) (z3 + deltaZ)};

                Triangle triangleTop1 = new Triangle(verticesTop1, verticesTop2, verticesTop3);

                float[] verticesTop4 = {(float) x2, (float) y2, (float) (z2 + deltaZ)};
                float[] verticesTop5 = {(float) x4, (float) y4, (float) (z4 + deltaZ)};
                float[] verticesTop6 = {(float) x3, (float) y3, (float) (z3 + deltaZ)};

                Triangle triangleTop2 = new Triangle(verticesTop4, verticesTop5, verticesTop6);

                // Add triangles for the top facade to the list
                triangles.add(triangleTop1);
                triangles.add(triangleTop2);
            }
        }

        return triangles;
    }



    public static List<float[]> determinerPointsPrismes(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
        //length correspond a longueur du mur
        //width correspond a epaisseur du mur
        //height correspond a hauteur du mur

        /*Pour construire le panneau en 3D , on doit construire un prisme ,
        le prisme est constitue de 6 faces qui s'emboitent donc partangent
        les meme points en fonction d'une certaine épaisseur. Chacun de ces faces est constitue de deux triangles */
        //vue de face:
        //float[] v0 = {xSupGauche, ySupGauche, zSupGauche};           // PointSupGaucheFacade (Top Left Front)

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

    public static List<float[]> determinerPointsPorte(float lengthporte, float widthporte, float heightporte, float xPorteSupGauche, float yPorteSupGauche, float zPorteSupGauche) {

        float[] v8 = {xPorteSupGauche, yPorteSupGauche, zPorteSupGauche};           // PointSupGaucheFacade (Top Left Front)
        float[] v9 = {xPorteSupGauche + lengthporte, yPorteSupGauche, zPorteSupGauche};   // PointSupDroiteFacade (Top Right Front)
        float[] v10 = {xPorteSupGauche + lengthporte, yPorteSupGauche + widthporte, zPorteSupGauche};   // PointSupDroiteArriere (Top Right Back)
        float[] v11 = {xPorteSupGauche, yPorteSupGauche + widthporte, zPorteSupGauche};    // PointSupGaucheArriere (Top Left Back)
        float[] v12 = {xPorteSupGauche, yPorteSupGauche, zPorteSupGauche + heightporte};   // PointInfGaucheFacade (Bottom Left Front)
        float[] v13 = {xPorteSupGauche + lengthporte, yPorteSupGauche, zPorteSupGauche + heightporte};   // PointInfDroiteFacade (Bottom Right Front)
        float[] v14 = {xPorteSupGauche + lengthporte, yPorteSupGauche + widthporte, zPorteSupGauche + heightporte};   // PointInfDroiteArriere (Bottom Right Back)
        float[] v15 = {xPorteSupGauche, yPorteSupGauche + widthporte, zPorteSupGauche + heightporte};    // PointInfGaucheArriere (Bottom Left Back)

        List<float[]> listeVertex = new LinkedList<>();
        listeVertex.add(v8);
        listeVertex.add(v9);
        listeVertex.add(v10);
        listeVertex.add(v11);
        listeVertex.add(v12);
        listeVertex.add(v13);
        listeVertex.add(v14);
        listeVertex.add(v15);

        return listeVertex;
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
        float longueur = (float) Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);
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


    public static List<Triangle> decomposerRectangleTriangle(Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit, Double EpaisseurChalet) {
        /* Un rectangle est composer de deux triangles */

        Double Epaisseur = 8.0;

        float[] vertexTriangle1_a = {(float) coinSupGauche.getX(), (float) coinSupGauche.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle1_b = {(float) coinSupDroit.getX(), (float) coinSupDroit.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle1_c = {(float) coinInfGauche.getX(), (float) coinInfGauche.getY(), Epaisseur.floatValue()};

        float[] vertexTriangle2_a = {(float) coinInfDroit.getX(), (float) coinInfDroit.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle2_b = {(float) coinInfGauche.getX(), (float) coinInfGauche.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle2_c = {(float) coinSupDroit.getX(), (float) coinSupDroit.getY(), Epaisseur.floatValue()};

        Triangle triangle1 = new Triangle(vertexTriangle1_a, vertexTriangle1_b, vertexTriangle1_c);
        Triangle triangle2 = new Triangle(vertexTriangle2_a, vertexTriangle2_b, vertexTriangle2_c);

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

                float[] normal = calculerNormale(triangle.vertex1, triangle.vertex2, triangle.vertex3);
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

                fileWriter.write("  facet normal " + normalA + " " + normalB + " " + normalC + System.getProperty("line.separator")
                );
                fileWriter.write("    outer loop\n");


                fileWriter.write(String.format("     vertex " + vertex1a + " " + vertex1b + " " + vertex1c + System.getProperty("line.separator")));
                fileWriter.write(String.format("     vertex " + vertex2a + " " + vertex2b + " " + vertex2c + System.getProperty("line.separator")));
                fileWriter.write(String.format("     vertex " + vertex3a + " " + vertex3b + " " + vertex3c + System.getProperty("line.separator")));

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

    /*public static void ExporterGrid(String fileName){
        Point point = new Point(0, 0);

        List<Triangle> trianglesDeGrid = createGridTriangles(100, 100, 0, 50, 50);
        for (Triangle triangle : trianglesDeGrid) {
            float[] v1 = triangle.vertex1;
            float[] v2 = triangle.vertex2;
            float[] v3 = triangle.vertex3;

            System.out.println("Triangle vertices: " +
                    v1[0] + ", " + v1[1] + ", " + v1[2] + ", " +
                    v2[0] + ", " + v2[1] + ", " + v2[2] + ", " +
                    v3[0] + ", " + v3[1] + ", " + v3[2]);
        }
        generateSTL(trianglesDeGrid, fileName);
    }*/
    /*public static void ExporterGrid(String fileName) {
        Point point = new Point(0, 0);

        List<Triangle> trianglesDeGrid = createPrism(100, 100, 10, 50, 50);

        for (Triangle triangle : trianglesDeGrid) {
            float[] v1 = triangle.vertex1;
            float[] v2 = triangle.vertex2;
            float[] v3 = triangle.vertex3;

            System.out.println("Triangle vertices: " +
                    v1[0] + ", " + v1[1] + ", " + v1[2] + ", " +
                    v2[0] + ", " + v2[1] + ", " + v2[2] + ", " +
                    v3[0] + ", " + v3[1] + ", " + v3[2]);
        }

        generateSTL(trianglesDeGrid, fileName);
    }*/


    public static void ExporterPanneauxBrut(String fileName) {
        Point point = new Point(0, 0);
        //determinerSommetsAccessoires fait le meme traitement dont on a besoin d'ou son utlisation ici.
        List<Point> listPointPanneaux = Chalet.determinerSommetsAccessoires(point, 100, 100);

        Point SupGauche = listPointPanneaux.get(0);
        Point SupDroit = listPointPanneaux.get(1);
        Point InfGauche = listPointPanneaux.get(2);
        Point InfDroit = listPointPanneaux.get(3);

        double epaisseurChalet = Chalet.epaisseurChalet;
        //double epaisseur = 15.0;

        List<Triangle> listeTriangles = generateRectangularPrism(100, (float) epaisseurChalet, 50, 0, 0, 0);
        generateSTL(listeTriangles, fileName);

    }


    public static List<Triangle> ExporterPanneauxRetraitDroite(float[] SupGauche, float length, float width, float height) {

        /* MUR DROITE */
        // Le point SupGauche du mur droite correspond au point superieur gauche de la base arriere du mur de facade v3

        float lengthPrincipal = length - length / 10;

        double epaisseurChalet = Chalet.epaisseurChalet;
        float thickness = (float) (epaisseurChalet);


        // Generer le prisme de base
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, width, height, SupGauche[0], SupGauche[1], SupGauche[2]);

        // Determiner les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, SupGauche[0], SupGauche[1], SupGauche[2]);

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
        float lengthSecondaire = length / 10;
        float widthSecondaire = (float) (width / 2);
        float heightSecondaire = height;

        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        float thicknessSecondaire = (float) (width / 2);


        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, SupGauche[0] - lengthSecondaire, SupGauche[1], SupGauche[2]);
        listeTriangles.addAll(listeTrianglesGauche);

        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, v1[0] - lengthSecondaire, SupGauche[1], SupGauche[2]);
        listeTriangles.addAll(listeTrianglesDroite);

        return listeTriangles;
    }

    public static void ExporterPanneauxRetrait(String fileName, String fileNameDroite, String fileNameChalet) {
        Point point = new Point(0, 0);
        //determinerSommetsAccessoires fait le meme traitement dont on a besoin d'ou son utlisation ici.
        List<Point> listPointPanneaux = Chalet.determinerSommetsAccessoires(point, 100, 100);

        Point SupGauche = listPointPanneaux.get(0);
        Point SupDroit = listPointPanneaux.get(1);
        Point InfGauche = listPointPanneaux.get(2);
        Point InfDroit = listPointPanneaux.get(3);

        double epaisseurChalet = Chalet.epaisseurChalet;
        //double epaisseur = 15.0;

        // Dimensions du prisme principal
        float length = 100;
        //On prend en compte les rainures qui correpondent a 1/10 de la longeur du prisme. Ainsi on
        float lengthPrincipal = length - length / 10;
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
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thickness, 50, 0, 0, 0);

        // Determiner les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

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
        float lengthSecondaire = length / 10;
        float widthSecondaire = width;
        float heightSecondaire = height;

        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        float thicknessSecondaire = (float) (thickness / 2);


        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, height, xSupGauche - lengthSecondaire, 0, 0);
        listeTriangles.addAll(listeTrianglesGauche);

        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, height, v1[0] - lengthSecondaire, 0, 0);
        listeTriangles.addAll(listeTrianglesDroite);

        // MUR DROITE
        List<Triangle> trianglesDroites = ExporterPanneauxRetraitDroite(v3, length, width, height);

        //CHALET
        List<Triangle> triangleChalet = new LinkedList<>();
        triangleChalet.addAll(listeTriangles);
        triangleChalet.addAll(trianglesDroites);

        generateSTL(listeTriangles, fileName);
        generateSTL(trianglesDroites, fileNameDroite);
        generateSTL(triangleChalet, fileNameChalet);
    }
    /*public static List<Triangle> generateRectangularPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
            *//*Pour construire le panneau en 3D , on doit construire un prisme ,
            le prisme est constitue de 6 faces qui s'emboitent donc partangent
            les meme points en fonction d'une certaine épaisseur. Chacun de ces faces est constitue de deux triangles *//*
        List<Triangle> triangles = new ArrayList<>();

        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);


        // Front face
        for(Triangle triangle: createGridTriangles(length, width, height, 20, 20)){
            triangles.add(triangle);
        }

        // Back face
        for(Triangle triangle: createGridTriangles(length, width, height, 20, 20)){
            triangles.add(triangle);
        }
        *//*triangles.add(new Triangle(v4, v6, v5));
        triangles.add(new Triangle(v4, v7, v6));*//*

        // Left face
        triangles.add(new Triangle(v0, v4, v3));
        triangles.add(new Triangle(v4, v7, v3));
        *//*A   B
          C
               B
          C    E  *//*

        // Right face
        triangles.add(new Triangle(v1, v5, v2));
        triangles.add(new Triangle(v2, v5, v6));

        // Top face
        triangles.add(new Triangle(v3, v7, v2));
        triangles.add(new Triangle(v2, v7, v6));

        // Bottom face
        triangles.add(new Triangle(v0, v1, v5));
        triangles.add(new Triangle(v0, v5, v4));

        return triangles;
    }*/

    public static List<Triangle> generateRectangularPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
        List<Triangle> triangles = new ArrayList<>();

        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);

        // Front face
        for (Triangle triangle : createGridTriangles(length, width, height, 20, 20)) {
            triangles.add(triangle);
        }

        // Back face
        for (Triangle triangle : createGridTriangles(length, width, height, 20, 20)) {
            // Adjust the depth of the back face grid
            float[] vertices1 = {triangle.vertex1[0], triangle.vertex1[1], triangle.vertex1[2] + height};
            float[] vertices2 = {triangle.vertex2[0], triangle.vertex2[1], triangle.vertex2[2] + height};
            float[] vertices3 = {triangle.vertex3[0], triangle.vertex3[1], triangle.vertex3[2] + height};
            triangles.add(new Triangle(vertices1, vertices2, vertices3));
        }

        // Left face
        triangles.add(new Triangle(v0, v4, v3));
        triangles.add(new Triangle(v4, v7, v3));

        // Right face
        triangles.add(new Triangle(v1, v5, v2));
        triangles.add(new Triangle(v2, v5, v6));

        // Top face
        triangles.add(new Triangle(v3, v7, v2));
        triangles.add(new Triangle(v2, v7, v6));

        // Bottom face
        triangles.add(new Triangle(v0, v1, v5));
        triangles.add(new Triangle(v0, v5, v4));

        defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3);
        //addAccessoryIntoPrism(triangles, defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3), 5, 5);

        return addAccessoryIntoPrism(triangles, defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3), 5, 5);
    }



    // methode pour generer un prisme 3D sans rainures, avec les deux grosses faces etant une mosaique de triangles
    // nb de triangles etant rows * cols * 2  par face
    public static List<Triangle> generateRectangularPrismWithGrid(float length, float width, float height, int rows, int cols) {
        float deltaY = height / rows;
        float deltaX = width / cols;

        List<Triangle> prismTriangles = generateRectangularPrism(length, width, height, 0, 0, 0);

        List<Triangle> gridTriangles1 = createGridTriangles(length, width, height, rows, cols);
        List<Triangle> gridTriangles2 = createGridTriangles(length, width, height, rows, cols);

        prismTriangles.addAll(gridTriangles1);
        prismTriangles.addAll(gridTriangles2);

        /*defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3);
        addAccessoryIntoPrism(prismTriangles, defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3), 5, 5);
*/
        return prismTriangles;
    }

    public static void ExporterPrismeWithGrids(String fileName) {
        List<Triangle> gridTriangles = generateRectangularPrismWithGrid(200, 100, 10, 20, 20);

        for (Triangle triangle : gridTriangles) {
            float[] v1 = triangle.vertex1;
            float[] v2 = triangle.vertex2;
            float[] v3 = triangle.vertex3;

            System.out.println("Triangle vertices: " +
                    v1[0] + ", " + v1[1] + ", " + v1[2] + ", " +
                    v2[0] + ", " + v2[1] + ", " + v2[2] + ", " +
                    v3[0] + ", " + v3[1] + ", " + v3[2]);
        }
        generateSTL(gridTriangles, fileName);
    }

    // methode permettant de creer une grille de triangles composant une face rectangulaire
    private static List<Triangle> createGridTriangles(float length, float height, float width, int rows, int columns) {
        List<Triangle> trianglesList = new ArrayList<>();

        double deltaX = length / columns; // distance inter colonnes
        double deltaY = height / rows; //distance inter lignes

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                float x1 = (float) (j * deltaX);
                float y1 = (float) (i * deltaY);

                float x2 = (float) ((j + 1) * deltaX);
                float y2 = (float) (i * deltaY);

                float x3 = (float) (j * deltaX);
                float y3 = (float) ((i + 1) * deltaY);

                float x4 = (float) ((j + 1) * deltaX);
                float y4 = (float) ((i + 1) * deltaY);

                float[] vertices1 = {x1, y1, 0.0f};
                float[] vertices2 = {x2, y2, 0.0f};
                float[] vertices3 = {x3, y3, 0.0f};

                Triangle triangleUpper = new Triangle(vertices1, vertices2, vertices3);

                float[] vertices4 = {x2, y2, 0.0f};
                float[] vertices5 = {x4, y4, 0.0f};
                float[] vertices6 = {x3, y3, 0.0f};

                Triangle triangleLower = new Triangle(vertices4, vertices5, vertices6);
                trianglesList.add(triangleUpper);
                trianglesList.add(triangleLower);
            }
        }
        return trianglesList;
    }

    // methode permettant de definir quelles cellules de la grille seront des accessoires
    // on prend en compte ici le positionnement de l'objet en plus de ses distances
    public static List<Triangle> defineGridCellsBeingAccessories(float length, float height, int accessoryRows, int accessoryCols, float xSmallAccessory, float xLargeAccessory, float yTopAccessory, float yBotAccessory){
        List<Triangle> trianglesOfAccessories = new ArrayList<>();
        float deltaX = length / accessoryCols;
        float deltaY = height / accessoryRows;

        float xSmall = xSmallAccessory * deltaX;
        float yTop = yTopAccessory * deltaY;

        float xLarge = xLargeAccessory * deltaX;
        float yBot = yBotAccessory * deltaY;

        float[] verticeTopLeft = {xSmall, yTop, 0.0f};
        float[] verticeTopRight = {xLarge, yTop, 0.0f};
        float[] verticeBotLeft = {xSmall, yBot, 0.0f};
        float[] verticeBotRight = {xLarge, yBot, 0.0f};

        Triangle triangleUpper = new Triangle(verticeTopLeft, verticeTopRight, verticeBotLeft);
        Triangle triangleLower = new Triangle(verticeTopRight, verticeBotRight, verticeBotLeft);

        triangleUpper.setNormal(calculateInwardNormal(triangleUpper.getVertices()));
        triangleLower.setNormal(calculateInwardNormal(triangleLower.getVertices()));

        trianglesOfAccessories.add(triangleUpper);
        trianglesOfAccessories.add(triangleLower);

        return trianglesOfAccessories;
    }

    // methode permettant d'ajouter les accessoires dans le prisme, et si les triangles sont pareils dans accesoires et dans le prisme, ne pas mettre
    // ces triangles dans la liste updated de triangles du prisme
    public static List<Triangle> addAccessoryIntoPrism(List<Triangle> prismTriangles, List<Triangle> trianglesOfAccessories, int accessoryRows, int accessoryCols) {
        List<Triangle> updatedPrismTriangles = new ArrayList<>();

        if (accessoryRows > prismTriangles.size() || accessoryCols > prismTriangles.size()) {
            System.out.println("Accessory dimensions are larger than prism dimensions");
            return updatedPrismTriangles;
        }

        if (!trianglesOfAccessories.isEmpty()) {
            for (Triangle prismTriangle : prismTriangles) {
                boolean isAccessoryTriangle = false;
                for(Triangle accessoryTriangle: trianglesOfAccessories){
                    if(Arrays.equals(prismTriangle.getVertices(), accessoryTriangle.getVertices())){
                        isAccessoryTriangle = true;
                        break;
                    }
                }
                if(!isAccessoryTriangle){
                    prismTriangle.setNormal(calculateInwardNormal(prismTriangle.getVertices()));
                    updatedPrismTriangles.add(prismTriangle);
                }
            }
        }

        return updatedPrismTriangles;
    }

    //methode permettant de calculer la normale d'une triangle pour la faire pointer vers l'interieur du prisme
    private static float[] calculateInwardNormal(float[] vertices) {

        float[] vector1 = {vertices[3] - vertices[0], vertices[4] - vertices[1], vertices[5] - vertices[2]};
        float[] vector2 = {vertices[6] - vertices[0], vertices[7] - vertices[1], vertices[8] - vertices[2]};

        float[] normal = {
                vector1[1] * vector2[2] - vector1[2] * vector2[1],
                vector1[2] * vector2[0] - vector1[0] * vector2[2],
                vector1[0] * vector2[1] - vector1[1] * vector2[0]
        };

        float length = (float) Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);
        normal[0] /= length;
        normal[1] /= length;
        normal[2] /= length;

        return normal;
    }
}

