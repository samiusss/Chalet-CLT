package Utilitaires;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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








    }

