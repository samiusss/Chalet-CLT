package Utilitaires;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class STLWriter {
    public static void writeSTLFile(List<Triangle> triangles, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("solid ASCII_STL");

            for (Triangle triangle : triangles) {
                writer.println("  facet normal 0 0 0");
                writer.println("    outer loop");
                writeVertex(writer, triangle.vertex1);
                writeVertex(writer, triangle.vertex2);
                writeVertex(writer, triangle.vertex3);
                writer.println("    endloop");
                writer.println("  endfacet");
            }

            writer.println("endsolid");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: a revoir cette partie, car il faut definir les data structure avant
    private static void writeVertex(PrintWriter writer, Point3D vertex) {
        writer.printf("      vertex %f %f %f%n", vertex.getLongueurMur(), vertex.getEpaisseurMur(), vertex.getHauteurMur());
    }
}
