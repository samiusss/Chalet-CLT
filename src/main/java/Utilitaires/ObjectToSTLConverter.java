package Utilitaires;
import java.util.*;

public class ObjectToSTLConverter {

    //TODO: convert list in list to list of hashmaps pour les murs, pour avoir la bonne data structure et revoir la logique
    public static List<Triangle> convertWallsToTriangles(List<List<Point3D>> walls) {
        List<Triangle> triangles = new ArrayList<>();

        // Convert each wall into triangles (assuming walls are represented as a list of points)
        for (List<Point3D> wall : walls) {
            for (int i = 0; i < wall.size() - 2; i++) {
                triangles.add(new Triangle(wall.get(i), wall.get(i + 1), wall.get(i + 2)));
            }
        }
        return triangles;
    }
}
