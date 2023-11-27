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


    /*

    public static List<Triangle> generateRectangularPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
     /*Pour construire le panneau en 3D , on doit construire un prisme ,
            le prisme est constitue de 6 faces qui s'emboitent donc partangent
            les meme points en fonction d'une certaine épaisseur. Chacun de ces faces est constitue de deux triangles
        //List<Triangle> triangles = new ArrayList<>();

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
        for(Triangle triangle: void createGridTriangles(length, width, height, 20, 20)){
            triangles.add(triangle);
        }

        // Back face
        for(Triangle triangle: createGridTriangles(length, width, height, 20, 20)){
            triangles.add(triangle);
        }
        /*triangles.add(new Triangle(v4, v6, v5));
        triangles.add(new Triangle(v4, v7, v6));*//*

        // Left face
        triangles.add(new Triangle(v0, v4, v3));
        triangles.add(new Triangle(v4, v7, v3));
        /*A   B
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
    } */

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
        List<float[]> vertices = new ArrayList<>();

        float xInfGauche = xSupGauche + length;
        float yInfGauche = ySupGauche + width;
        float zInfGauche = zSupGauche + height;

        // Sommets du prisme
        float[] v0 = {xSupGauche, ySupGauche, zSupGauche};
        float[] v1 = {xInfGauche, ySupGauche, zSupGauche};
        float[] v2 = {xInfGauche, yInfGauche, zSupGauche};
        float[] v3 = {xSupGauche, yInfGauche, zSupGauche};
        float[] v4 = {xSupGauche, ySupGauche, zInfGauche};
        float[] v5 = {xInfGauche, ySupGauche, zInfGauche};
        float[] v6 = {xInfGauche, yInfGauche, zInfGauche};
        float[] v7 = {xSupGauche, yInfGauche, zInfGauche};

        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);

        return vertices;
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

    public static List<Triangle> generateRectangularPrismGrid(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, float smallPrismLength, float smallPrismWidth) {
        List<Triangle> triangles = new ArrayList<>();

        int numPrismsLength = (int) Math.ceil(length / smallPrismLength);
        int numPrismsWidth = (int) Math.ceil(width / smallPrismWidth);

        float smallPrismHeight = height / numPrismsLength;
        float smallPrismDepth = width / numPrismsWidth;

        for (int i = 0; i < numPrismsLength; i++) {
            for (int j = 0; j < numPrismsWidth; j++) {
                float startX = xSupGauche + i * smallPrismLength;
                float startY = ySupGauche + j * smallPrismWidth;
                float startZ = zSupGauche + i * smallPrismHeight;

                List<float[]> prismVertices = determinerPointsPrismes(smallPrismLength, smallPrismWidth, smallPrismHeight, startX, startY, startZ);

                float[] v0 = prismVertices.get(0);
                float[] v1 = prismVertices.get(1);
                float[] v2 = prismVertices.get(2);
                float[] v3 = prismVertices.get(3);
                float[] v4 = prismVertices.get(4);
                float[] v5 = prismVertices.get(5);
                float[] v6 = prismVertices.get(6);
                float[] v7 = prismVertices.get(7);

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
            }
        }

        return triangles;
    }


    public static List<Triangle> generateRectangularPrismGrid3(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, float smallPrismLength, float smallPrismWidth) {
        List<Triangle> triangles = new ArrayList<>();

        int numPrismsLength = (int) Math.ceil(length / smallPrismLength);
        int numPrismsWidth = (int) Math.ceil(width / smallPrismWidth);

        float smallPrismHeight = height / Math.max(numPrismsLength, numPrismsWidth);

        for (int i = 0; i < numPrismsLength; i++) {
            for (int j = 0; j < numPrismsWidth; j++) {
                float startX = xSupGauche + i * smallPrismLength;
                float startY = ySupGauche + j * smallPrismWidth;
                float startZ = zSupGauche + i * smallPrismHeight; // Ajuster la hauteur en fonction du nombre de petits prismes

                List<float[]> prismVertices = determinerPointsPrismes(smallPrismLength, smallPrismWidth, smallPrismHeight, startX, startY, startZ);

                float[] v0 = prismVertices.get(0);
                float[] v1 = prismVertices.get(1);
                float[] v2 = prismVertices.get(2);
                float[] v3 = prismVertices.get(3);
                float[] v4 = prismVertices.get(4);
                float[] v5 = prismVertices.get(5);
                float[] v6 = prismVertices.get(6);
                float[] v7 = prismVertices.get(7);

                // Front face
                triangles.add(new Triangle(v0, v1, v2));
                triangles.add(new Triangle(v0, v2, v3));

                // Back face
                triangles.add(new Triangle(v4, v5, v6));
                triangles.add(new Triangle(v4, v6, v7));

                // Left face
                if (i == 0) {
                    triangles.add(new Triangle(v0, v3, v7));
                    triangles.add(new Triangle(v0, v7, v4));
                }

                // Right face
                if (i == numPrismsLength - 1) {
                    triangles.add(new Triangle(v1, v5, v6));
                    triangles.add(new Triangle(v1, v6, v2));
                }

                // Top face
                if (j == numPrismsWidth - 1) {
                    triangles.add(new Triangle(v2, v6, v7));
                    triangles.add(new Triangle(v2, v7, v3));
                }

                // Bottom face
                if (j == 0) {
                    triangles.add(new Triangle(v0, v1, v5));
                    triangles.add(new Triangle(v0, v5, v4));
                }
            }
        }

        return triangles;
    }




    public static List<Triangle> generateRectangularPrismGrid2(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, float smallPrismLength, float smallPrismWidth) {
        List<Triangle> triangles = new ArrayList<>();

        int numPrismsLength = (int) Math.ceil(length / smallPrismLength);
        int numPrismsWidth = (int) Math.ceil(width / smallPrismWidth);

        float smallPrismHeight = height / Math.max(numPrismsLength, numPrismsWidth);

        for (int i = 0; i < numPrismsLength; i++) {
            for (int j = 0; j < numPrismsWidth; j++) {
                float startX = xSupGauche + i * smallPrismLength;
                float startY = ySupGauche + j * smallPrismWidth;
                float startZ = zSupGauche;

                List<float[]> prismVertices = determinerPointsPrismes(smallPrismLength, smallPrismWidth, smallPrismHeight, startX, startY, startZ);

                float[] v0 = prismVertices.get(0);
                float[] v1 = prismVertices.get(1);
                float[] v2 = prismVertices.get(2);
                float[] v3 = prismVertices.get(3);
                float[] v4 = prismVertices.get(4);
                float[] v5 = prismVertices.get(5);
                float[] v6 = prismVertices.get(6);
                float[] v7 = prismVertices.get(7);

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
            }
        }

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
            Point point = new Point(0,0);
            //determinerSommetsAccessoires fait le meme traitement dont on a besoin d'ou son utlisation ici.
            List<Point> listPointPanneaux = Chalet.determinerSommetsAccessoires(point,100, 100);

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

    /*public static List<Rectangle> generateRectangles(Rectangle mainRectangle, List<Rectangle> existingRectangles) {
        List<Rectangle> resultRectangles = new ArrayList<>(existingRectangles);

        double mainRectX = mainRectangle.mousePoint.x;
        double mainRectY = mainRectangle.mousePoint.y;
        double mainRectWidth = mainRectangle.length;
        double mainRectHeight = mainRectangle.width;

        // Vérifier le côté supérieur
        addMissingRectangles(resultRectangles, mainRectX, mainRectY, mainRectWidth, 0, existingRectangles);

        // Vérifier le côté inférieur
        addMissingRectangles(resultRectangles, mainRectX, mainRectY, mainRectWidth, mainRectHeight, existingRectangles);

        // Vérifier le côté gauche
        addMissingRectangles(resultRectangles, mainRectX, mainRectY, 0, mainRectHeight, existingRectangles);

        // Vérifier le côté droit
        addMissingRectangles(resultRectangles, mainRectX, mainRectY, mainRectWidth, mainRectHeight, existingRectangles);

        return resultRectangles;
    } */


    /*private static void addMissingRectangles(List<Rectangle> resultRectangles, double x, double y, double width, double height, List<Rectangle> existingRectangles) {
        // Check if width or height is zero to avoid division by zero
        if (width <= 0 || height <= 0) {
            return;
        }

        for (Rectangle existingRectangle : existingRectangles) {
            double existingX = existingRectangle.mousePoint.x;
            double existingY = existingRectangle.mousePoint.y;
            double existingWidth = existingRectangle.width;
            double existingHeight = existingRectangle.length;

            // Check for overlap
            if (x < existingX + existingWidth && x + width > existingX &&
                    y < existingY + existingHeight && y + height > existingY) {
                // Overlapping with an existing rectangle, do not add
                return;
            }
        }

        // No overlap with any existing rectangle, add the new rectangle
        resultRectangles.add(new Rectangle(new Point((int) x, (int) y), (int) width, (int) height));
    } */



    /*public static List<Rectangle> genererMurX(Point mousePointMur, float longueurMur, float hauteurMur,
                                             float epaisseurMur, Point mousePointAccessoire, float longueurAccessoire,
                                             float hauteurAccessoire, float epaisseurAccessoire) {

        // Calcul des coordonnées du coin supérieur gauche du mur
        float murX = mousePointMur.x - longueurMur / 2;
        float murY = mousePointMur.y - hauteurMur / 2;

        // Calcul des coordonnées du coin supérieur gauche de l'accessoire
        float accessoireX = mousePointAccessoire.x - longueurAccessoire / 2;
        float accessoireY = mousePointAccessoire.y - hauteurAccessoire / 2;

        // Calcul du nombre d'accessoires nécessaires pour former le mur
        int nombreAccessoires = (int) (longueurMur / longueurAccessoire);

        // Liste pour stocker les rectangles représentant le mur formé par les accessoires
        List<Rectangle> rectanglesMur = new ArrayList<>();

        // Génération des rectangles autour des accessoires pour former le mur
        for (int i = 0; i < nombreAccessoires; i++) {
            float x = murX + i * longueurAccessoire;
            float y = murY;
            Rectangle rectangle = new Rectangle(new Point((int) x, (int) y), (int) longueurAccessoire, (int) hauteurMur);
            rectanglesMur.add(rectangle);
        }

        return rectanglesMur;
    } */

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


    // Méthode pour diviser le rectangle en petits rectangles avec une distance de grille donnée
    public static List<Rectangle> diviserEnRectangles3D(float distanceDeGrille, Point pointSuperieurGauche, float length, float height) {
        // Vérifier que la distance de grille est valide
        if (distanceDeGrille <= 0) {
            throw new IllegalArgumentException("La distance de grille doit être supérieure à zéro.");
        }

        // Calculer le nombre de petits rectangles dans la longueur
        int nombreDeRectanglesLongueur = (int) Math.ceil(length / distanceDeGrille);

        // Calculer la nouvelle longueur des petits rectangles
        float nouvelleLongueur = length / nombreDeRectanglesLongueur;

        // Créer et stocker les petits rectangles dans une liste
        List<Rectangle> petitsRectangles = new ArrayList<>();

        for (int i = 0; i < nombreDeRectanglesLongueur; i++) {
            // Calculer le coin supérieur gauche de chaque petit rectangle
            float nouveauX = pointSuperieurGauche.x + i * nouvelleLongueur;

            // Créer le petit rectangle avec la hauteur du rectangle de base et l'ajouter à la liste
            petitsRectangles.add(new Rectangle(new Point((int) nouveauX, pointSuperieurGauche.y), nouvelleLongueur, height));
        }

        return petitsRectangles;
    }

        public static List<Rectangle> createGrid(Point pointSuperieurGauche, float length, float height, float distanceDeGrille) {
            // Calcul du nombre de lignes et de colonnes
            int nombreDeLignes = (int) Math.ceil(height / distanceDeGrille);
            int nombreDeColonnes = (int) Math.ceil(length / distanceDeGrille);

            // Calcul de la longueur et de la hauteur de chaque cellule
            float longueurCellule = length / nombreDeColonnes;
            float hauteurCellule = height / nombreDeLignes;

            // Liste pour stocker les rectangles
            List<Rectangle> listeRectangles = new ArrayList<>();

            // Création de la grille de rectangles
            for (int i = 0; i < nombreDeLignes; i++) {
                for (int j = 0; j < nombreDeColonnes; j++) {
                    // Calcul du point supérieur gauche de chaque nouveau rectangle
                    float nouveauX = pointSuperieurGauche.x + j * longueurCellule;
                    float nouveauY = pointSuperieurGauche.y + i * hauteurCellule;

                    // Création du nouveau rectangle
                    Rectangle nouveauRectangle = new Rectangle(new Point((int) nouveauX, (int) nouveauY), longueurCellule, hauteurCellule);

                    // Ajout du rectangle à la liste
                    listeRectangles.add(nouveauRectangle);
                }
            }

            // Retour de la liste de rectangles
            return listeRectangles;
        }


    // Méthode pour diviser le rectangle en une liste de rectangles
    public static List<Rectangle> diviserEnGrille(Point pointSuperieurGauche, float length, float height, float distanceDeGrille) {
        List<Rectangle> rectangles = new ArrayList<>();

        // Calculer le nombre de lignes et de colonnes dans la grille
        int nombreDeColonnes = (int) Math.ceil(length / distanceDeGrille);
        int nombreDeLignes = (int) Math.ceil(height / distanceDeGrille);

        // Calculer la longueur et la hauteur de chaque sous-rectangle dans la grille
        float sousRectangleLength = length / nombreDeColonnes;
        float sousRectangleHeight = height / nombreDeLignes;

        // Remplir la liste avec les rectangles
        for (int i = 0; i < nombreDeLignes; i++) {
            for (int j = 0; j < nombreDeColonnes; j++) {
                // Calculer le coin supérieur gauche de chaque sous-rectangle
                Point nouveauPointSuperieurGauche = new Point(
                        (int) (pointSuperieurGauche.x + j * sousRectangleLength),
                        (int) (pointSuperieurGauche.y + i * sousRectangleHeight)
                );

                Rectangle rect = new Rectangle(nouveauPointSuperieurGauche, sousRectangleLength, sousRectangleHeight);

                // Créer le sous-rectangle
                rectangles.add(rect);
            }
        }

        return rectangles;
    }
    public static List<Triangle> ExporterPanneauxRetraitArriere(float[] SupGauche, float length, float width, float height) {

            // Set lengthPrincipal to the desired length of the main prism
            float lengthPrincipal = length - length / 10;

            // Set the desired thickness of the main prism
            double epaisseurChalet = Chalet.epaisseurChalet;
            float thicknessPrismePrincipal = (float) epaisseurChalet;

            // Assuming diviserEnGrille returns rectangles covering the entire base rectangle
            List<Rectangle> rectanglesMur = diviserEnGrille(new Point((int) SupGauche[0], (int) SupGauche[1]), lengthPrincipal, height, 4);

            List<Triangle> listeTriangles = new LinkedList<>();

            for (Rectangle rectangle : rectanglesMur) {
                Point mousePoint = rectangle.pointSuperieurGauche;

                // Set thicknessRect to the thickness of the groove
                float thicknessRect = (float) epaisseurChalet;

                // Check if length and width are correctly assigned to generateRectangularPrism
                List<Triangle> listeTrianglesRect = generateRectangularPrism(rectangle.length, rectangle.height, thicknessRect, mousePoint.x, mousePoint.y, 0);
                listeTriangles.addAll(listeTrianglesRect);
            }

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


    public static List<Triangle> ExporterPanneauxRetraitArriere2(float[] SupGauche, float length, float width, float height){

        /* MUR GAUUCHE */
        // Le point SupGauche du mur droite correspond au point superieur droite de la base arriere du mur de facade v2

        float lengthPrincipal = length - length/10;

        double epaisseurChalet = Chalet.epaisseurChalet;
        float thickness = (float) (epaisseurChalet);


        //List<Triangle> listesTriangles2D = decomposerRectangleTriangle(new Point(2,3),new Point(4,3),new Point(2,6),new Point(4,6), (double) thickness);

        Point pointA = new Point(0, 0);
        Point pointB = new Point(2, 2);
        Point pointC = new Point(5, 5);



        List<Rectangle> rectanglesMur = diviserEnGrille(pointA, lengthPrincipal,height,4);


        List<Triangle> listeTriangles = new LinkedList<>();
        for(Rectangle rectangle : rectanglesMur) {

            Point mousePoint = rectangle.pointSuperieurGauche;
            float lengthRect = rectangle.height;
            float heightRect = rectangle.length;


            List<Triangle>  listeTrianglesRect = generateRectangularPrism(heightRect, width,lengthRect, mousePoint.x,mousePoint.y,0);
            listeTriangles.addAll(listeTrianglesRect);
            System.out.println(listeTriangles);


        }

        //List<Triangle>  listeTrianglesRect = generateRectangularPrism(lengthPrincipal, width,height, 0,0,0);
        //listeTriangles.addAll(listeTrianglesRect);


        // Exemple d'utilisation
        /*Rectangle mainRectangle = new Rectangle(pointA, (int) lengthPrincipal, (int) height);

        List<Rectangle> rectangleList = new ArrayList<>();
        rectangleList.add(new Rectangle(pointB, 3, 2));
        rectangleList.add(new Rectangle(pointC, 2, 1));
        // Ajoutez d'autres rectangles à votre liste si nécessaire

        List<Rectangle> resultRectangles = generateRectangles(mainRectangle, rectangleList);

        List<Triangle> listeTriangles = new LinkedList<>();
        for(Rectangle rectangle : resultRectangles) {

            Point mousePoint = rectangle.mousePoint;
            float lengthRect = rectangle.length;
            float heightRect = rectangle.width;


            List<Triangle>  listeTrianglesRect = generateRectangularPrism(lengthRect, width,heightRect, mousePoint.x,mousePoint.y,0);
            listeTriangles.addAll(listeTrianglesRect);
            System.out.println(listeTriangles);


        } */

        // Exemple d'utilisation de la méthode
        /*Point mousePointMur = new Point((int) SupGauche[0], (int) SupGauche[1]);
        float longueurMur = lengthPrincipal;
        float hauteurMur = height;

        Point mousePointAccessoire = new Point(5, 0);
        float longueurAccessoire = 5;
        float hauteurAccessoire = 10;
        //float epaisseurAccessoire = 10;

        List<Rectangle> rectanglesMur = genererMur(mousePointMur, longueurMur, hauteurMur,
                (float) epaisseurChalet, mousePointAccessoire, longueurAccessoire,hauteurAccessoire, (float) epaisseurChalet);

        // Affichage des rectangles générés
        for (Rectangle rectangle : rectanglesMur) {
            System.out.println(rectangle);
        }

        List<Triangle> listeTriangles = new LinkedList<>();
        for(Rectangle rectangle : rectanglesMur) {

            Point mousePoint = rectangle.mousePoint;
            float lengthRect = rectangle.length;
            float heightRect = rectangle.height;


            List<Triangle>  listeTrianglesRect = generateRectangularPrism(lengthRect, (float) epaisseurChalet,heightRect, mousePoint.x,mousePoint.y,0);
            listeTriangles.addAll(listeTrianglesRect);
            System.out.println(listeTriangles);


        } */


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

        generateSTL(listeTriangles, fileName);
        generateSTL(trianglesDroites, fileNameDroite);
        generateSTL(triangleChalet, fileNameChalet);
    }




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

        //addAccessoryIntoPrism(triangles, defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3), 5, 5);

        return addAccessoryIntoPrism(triangles, defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3));
    }



    // methode pour generer un prisme 3D sans rainures, avec les deux grosses faces etant une mosaique de triangles
    // nb de triangles etant rows * cols * 2  par face
    public static List<Triangle> generateRectangularPrismWithGrid(float length, float width, float height, int rows, int cols) {
        float deltaY = height / rows;
        float deltaX = width / cols;
        List<Triangle> updated = new ArrayList<>();

        List<Triangle> prismTriangles = generateRectangularPrism(length, width, height, 0, 0, 0);

        List<Triangle> accessoryTriangles = defineGridCellsBeingAccessories(length, height, 5, 5, 3, 8, 3, 8);

        /*List<Triangle> gridTriangles1 = createGridTriangles(length, width, height, rows, cols);
        List<Triangle> gridTriangles2 = createGridTriangles(length, width, height, rows, cols);

        prismTriangles.addAll(gridTriangles1);
        prismTriangles.addAll(gridTriangles2);*/

        /*defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3);
        addAccessoryIntoPrism(prismTriangles, defineGridCellsBeingAccessories(length, height, 5, 5, 3, 3, 3, 3), 5, 5);
*/
        return addAccessoryIntoPrism(prismTriangles, accessoryTriangles);
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
    public static List<Triangle> addAccessoryIntoPrism(List<Triangle> prismTriangles, List<Triangle> trianglesOfAccessories) {
        List<Triangle> updatedPrismTriangles = new ArrayList<>();

        for (Triangle prismTriangle : prismTriangles) {
            boolean isAccessoryTriangle = false;
            for(Triangle accessoryTriangle: trianglesOfAccessories){
                if(Arrays.equals(prismTriangle.getVertices(), accessoryTriangle.getVertices())){
                    isAccessoryTriangle = true;
                    break;
                }
            }
            prismTriangle.setNormal(calculateInwardNormal(prismTriangle.getVertices()));
            updatedPrismTriangles.add(prismTriangle);
        }
        for(Triangle prismTriangle : prismTriangles){
            if(trianglesOfAccessories.contains(prismTriangle)){
                continue;
            }
            else{
                prismTriangle.setNormal(calculateInwardNormal(prismTriangle.getVertices()));
                updatedPrismTriangles.add(prismTriangle);
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

