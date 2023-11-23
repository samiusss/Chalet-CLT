package Utilitaires;
import domain.Chalet;
import domain.Controleur;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
                    String normalA = String.valueOf(normal[0]);

                    String normalB = String.valueOf(normal[1]);

                    String normalC = String.valueOf(normal[2]);

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


                    fileWriter.write("  facet normal "+normalA+" "+normalB+" "+ normalC+ System.getProperty("line.separator"));
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

            Point[] pointPrisme = genererPrisme(SupGauche,SupDroit,InfGauche,InfDroit,epaisseur);


            //Liste triangle Base Avant
            List<Triangle> listeTrianglesBaseAvant = decomposerRectangleTriangle(SupGauche,SupDroit,InfGauche,InfDroit,epaisseur) ;

            // Base Supérieure
            List<Triangle> listeTrianglesBaseSuperieure = decomposerRectangleTriangle(
                    pointPrisme[4], pointPrisme[5], pointPrisme[6], pointPrisme[7], epaisseur);

            // Base Inférieure
            List<Triangle> listeTrianglesBaseInferieure = decomposerRectangleTriangle(
                    pointPrisme[0], pointPrisme[1], pointPrisme[2], pointPrisme[3], epaisseur);

            // Base Arrière
            List<Triangle> listeTrianglesBaseArriere = decomposerRectangleTriangle(
                    pointPrisme[0], pointPrisme[1], pointPrisme[5], pointPrisme[4], epaisseur);

            // Base Gauche (vue de face)
            List<Triangle> listeTrianglesBaseGauche = decomposerRectangleTriangle(
                    pointPrisme[0], pointPrisme[4], pointPrisme[7], pointPrisme[3], epaisseur);

            // Base Droite (vue de face)
            List<Triangle> listeTrianglesBaseDroite = decomposerRectangleTriangle(
                    pointPrisme[1], pointPrisme[5], pointPrisme[6], pointPrisme[2], epaisseur);


            List<Triangle> listeTriangles= new LinkedList<>();
            listeTriangles.addAll(listeTrianglesBaseAvant);
            listeTriangles.addAll(listeTrianglesBaseArriere);
            listeTriangles.addAll(listeTrianglesBaseInferieure);
            listeTriangles.addAll(listeTrianglesBaseSuperieure);
            listeTriangles.addAll(listeTrianglesBaseGauche);
            listeTriangles.addAll(listeTrianglesBaseAvant);


            generateSTL(listeTriangles,fileName);


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

