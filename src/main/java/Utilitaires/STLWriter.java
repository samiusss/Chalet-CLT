package Utilitaires;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class STLWriter {

        public static void main(String[] args) {

        }

    // Méthode pour le traitement automatique des vertices
    private static String processVertex(float originalValue) {
        String nombreAvecVirgule = String.valueOf(originalValue);
        String nombreAvecPoint = nombreAvecVirgule.replace(",", ".");
        return nombreAvecPoint;
    }

        public static void generateSTL(List<Triangle> triangles, String fileName) {
            try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
                // Écrire l'en-tête du fichier STL
                fileWriter.write("solid generated\n");

                // Écrire les triangles
                for (Triangle triangle : triangles) {
                    fileWriter.write("  facet normal 0 0 1\n");
                    fileWriter.write("    outer loop\n");

                    //TRAITEMENT1
                    String nombreAvecVirgule = String.valueOf(triangle.vertex1[0]);   // Remplacer la virgule par un point
                    String nombreAvecPoint = nombreAvecVirgule.replace(",", ".");  // Convertir la chaîne résultante en float
                    float vertex1 = Float.parseFloat(nombreAvecPoint);


                    fileWriter.write(String.format("vertex %f %f %f\n", vertex1, triangle.vertex1[1], triangle.vertex1[2]));
                    fileWriter.write(String.format("vertex %f %f %f\n", triangle.vertex2[0], triangle.vertex2[1], triangle.vertex2[2]));
                    fileWriter.write(String.format("vertex %f %f %f\n", triangle.vertex3[0], triangle.vertex3[1], triangle.vertex3[2]));


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

