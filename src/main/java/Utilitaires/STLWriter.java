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


    private static double[] calculateNormal(double[] v1, double[] v2, double[] v3) {
        double[] normal = new double[3];
        double[] vector1 = {v2[0] - v1[0], v2[1] - v1[1], v2[2] - v1[2]};
        double[] vector2 = {v3[0] - v1[0], v3[1] - v1[1], v3[2] - v1[2]};
        normal[0] = vector1[1] * vector2[2] - vector1[2] * vector2[1];
        normal[1] = vector1[2] * vector2[0] - vector1[0] * vector2[2];
        normal[2] = vector1[0] * vector2[1] - vector1[1] * vector2[0];
        return normal;
    }

    // Méthode pour le traitement automatique des vertices
    private static String processVertex(float originalValue) {
        BigDecimal bd = new BigDecimal(Float.toString(originalValue));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String nombreAvecVirgule = bd.toPlainString();  // Utilisez toPlainString() pour éviter la notation scientifique

        String nombreAvecPoint = nombreAvecVirgule.replace(",", ".");
        return nombreAvecPoint;
    }

    public static List<Triangle> generateRectangularPrism(float length, float width, float height) {
        List<Triangle> triangles = new ArrayList<>();

        float[] v0 = {0.0f, 0.0f, 0.0f};
        float[] v1 = {length, 0.0f, 0.0f};
        float[] v2 = {length, width, 0.0f};
        float[] v3 = {0.0f, width, 0.0f};
        float[] v4 = {0.0f, 0.0f, height};
        float[] v5 = {length, 0.0f, height};
        float[] v6 = {length, width, height};
        float[] v7 = {0.0f, width, height};

        // Front face
        triangles.add(new Triangle(v0, v1, v2));
        triangles.add(new Triangle(v0, v2, v3));

        // Back face
        triangles.add(new Triangle(v4, v6, v5));
        triangles.add(new Triangle(v4, v7, v6));

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

        return triangles;
    }

    public static List<Triangle> generateRectangularPrism2(List<Float> vertices, float thickness) {
        List<Triangle> triangles = new ArrayList<>();

        // Assuming vertices are provided in the order (x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4)
        float[] bottomLeft = {vertices.get(0), vertices.get(1), vertices.get(2)};
        float[] bottomRight = {vertices.get(3), vertices.get(4), vertices.get(5)};
        float[] topRight = {vertices.get(6), vertices.get(7), vertices.get(8)};
        float[] topLeft = {vertices.get(9), vertices.get(10), vertices.get(11)};

        // Bottom face
        triangles.add(new Triangle(bottomLeft, bottomRight, topRight));
        triangles.add(new Triangle(bottomLeft, topRight, topLeft));

        // Top face
        float[] newTopBottomLeft = {bottomLeft[0], bottomLeft[1], bottomLeft[2] + thickness};
        float[] newTopBottomRight = {bottomRight[0], bottomRight[1], bottomRight[2] + thickness};
        float[] newTopTopRight = {topRight[0], topRight[1], topRight[2] + thickness};
        float[] newTopTopLeft = {topLeft[0], topLeft[1], topLeft[2] + thickness};

        triangles.add(new Triangle(newTopBottomRight, newTopBottomLeft, newTopTopRight));
        triangles.add(new Triangle(newTopTopLeft, newTopTopRight, newTopBottomLeft));

        // Side faces
        triangles.add(new Triangle(bottomLeft, bottomRight, newTopBottomRight));
        triangles.add(new Triangle(bottomLeft, newTopBottomRight, newTopBottomLeft));

        triangles.add(new Triangle(newTopTopRight, newTopTopLeft, topLeft));
        triangles.add(new Triangle(newTopTopRight, topLeft, newTopBottomLeft));

        triangles.add(new Triangle(newTopTopLeft, newTopTopRight, newTopBottomRight));
        triangles.add(new Triangle(newTopTopLeft, newTopBottomRight, newTopBottomLeft));

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

                    String vertex2c = processVertex(triangle.vertex3[2]);

                    // Remplacement pour le vertex 3
                    String vertex3a = processVertex(triangle.vertex3[0]);

                    String vertex3b = processVertex(triangle.vertex3[1]);

                    String vertex3c = processVertex(triangle.vertex3[2]);
                    //+normalA+" "+normalB+" "+ normalC+ System.getProperty("line.separator")

                    fileWriter.write("  facet normal 0.0 0.0 0.0\n");
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
            List<Point> listPointPanneaux = Chalet.determinerSommetsAccessoires(point,100, 100);


            Point SupGauche = listPointPanneaux.get(0);
            Point SupDroit = listPointPanneaux.get(1);
            Point InfGauche = listPointPanneaux.get(2);
            Point InfDroit = listPointPanneaux.get(3);

            double epaisseur = Chalet.epaisseurChalet;

            float[] vertex1 = {(float) SupGauche.getX(), (float) SupGauche.getY(), (float) epaisseur};
            float[] vertex2 = {(float) SupDroit.getX(), (float) SupDroit.getY(), (float) epaisseur};
            float[] vertex3 = {(float) InfGauche.getX(), (float) InfGauche.getY(), (float) epaisseur};
            float[] vertex4 = {(float) InfDroit.getX(), (float) InfDroit.getY(), (float) epaisseur};

            List<Float> verticesList = new LinkedList<>();
            verticesList.add((float) SupGauche.getX());
            verticesList.add((float) SupGauche.getY());
            verticesList.add( (float) epaisseur);

            verticesList.add((float) SupDroit.getX());
            verticesList.add((float) SupDroit.getY());
            verticesList.add( (float) epaisseur);

            verticesList.add((float) InfGauche.getX());
            verticesList.add((float) InfGauche.getY());
            verticesList.add( (float) epaisseur);

            verticesList.add((float) InfDroit.getX());
            verticesList.add((float) InfDroit.getY());
            verticesList.add( (float) epaisseur);

            List<Triangle> listeTriangles = generateRectangularPrism(100,100, (float) epaisseur);
                generateSTL(listeTriangles,fileName);


            /*

            Point[] pointPrisme = genererPrisme(SupGauche,SupDroit,InfGauche,InfDroit,epaisseur);


            //Liste triangle Base Avant
            List<Triangle> listeTrianglesBaseAvant = decomposerRectangleTriangle(SupGauche,SupDroit,InfGauche,InfDroit,epaisseur) ;

            // Base Arrière
            List<Triangle> listeTrianglesBaseArriere = decomposerRectangleTriangle(
                    pointPrisme[0], pointPrisme[1], pointPrisme[5], pointPrisme[4], epaisseur);

            // Base Supérieure
            List<Triangle> listeTrianglesBaseSuperieure = decomposerRectangleTriangle(
                    pointPrisme[4], pointPrisme[5], pointPrisme[6], pointPrisme[7], epaisseur);

            // Base Inférieure
            List<Triangle> listeTrianglesBaseInferieure = decomposerRectangleTriangle(
                    pointPrisme[0], pointPrisme[1], pointPrisme[2], pointPrisme[3], epaisseur);


            // Base Gauche (vue de face)
            List<Triangle> listeTrianglesBaseGauche = decomposerRectangleTriangle(
                    pointPrisme[0], pointPrisme[4], pointPrisme[7], pointPrisme[3], (double) 0);

            // Base Droite (vue de face)
            List<Triangle> listeTrianglesBaseDroite = decomposerRectangleTriangle(
                    pointPrisme[1], pointPrisme[5], pointPrisme[6], pointPrisme[2], (double) 0);


            List<Triangle> listeTriangles= new LinkedList<>();
            listeTriangles.addAll(listeTrianglesBaseAvant);
            listeTriangles.addAll(listeTrianglesBaseArriere);
            //listeTriangles.addAll(listeTrianglesBaseInferieure);
            //listeTriangles.addAll(listeTrianglesBaseSuperieure);
            //listeTriangles.addAll(listeTrianglesBaseGauche);
            //listeTriangles.addAll(listeTrianglesBaseDroite);  */




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




        }








    }

