package Utilitaires;
import domain.*;
import java.util.*;

public class ObjectToSTLConverter {

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

    public static List<Triangle> convertWallsToTriangles(List<Mur> murs, double hauteurMurs) {
        List<Triangle> triangles = new ArrayList<>();

        // Convert each wall into triangles (assuming walls are represented as a list of points)
        /*for (Mur mur : murs) {
            for (int i = 0; i < mur.getSommetsMur().size() - 2; i++) {
                triangles.add(new Triangle(mur.getSommetsMur().get(i).getX(), mur.getSommetsMur().get(i).getY(), findPerspectivePoint(mur.getSommetsMur().get(i).getZ())));
            }
        }*/
        return triangles;
    }

    /*public static PointDouble findPerspectivePoint(Mur wall) {
        //for each wall
        //  for each point in sommetsMur, find the perspective point
        //  add the perspective point to the list of perspective points
        /////// is equivalent to:
        //for each wall
        //  if mur = facade
        //   go to vue surplomb, and find the y point
        //  if mur = cote

    }*/
}

// Path: src/main/java/Utilitaires/STLWriter.java