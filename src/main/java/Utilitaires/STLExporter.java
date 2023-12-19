package Utilitaires;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class STLExporter {

    public static class Point {
        float x, y, z;

        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Triangle implements java.io.Serializable {
        Point vertex1, vertex2, vertex3;

        public Triangle(Point vertex1, Point vertex2, Point vertex3) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.vertex3 = vertex3;
        }
    }

    public static List<Triangle> generateWall() {
        // Example: generate simple walls for demonstration
        List<Triangle> wallTriangles = new ArrayList<>();
        wallTriangles.add(new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0)));
        wallTriangles.add(new Triangle(new Point(1, 0, 0), new Point(1, 1, 0), new Point(0, 1, 0)));
        return wallTriangles;
    }

    public static List<Triangle> generateAccessories() {
        // Example: generate accessories for demonstration
        List<Triangle> accessoryTriangles = new ArrayList<>();
        accessoryTriangles.add(new Triangle(new Point(0.2f, 0.2f, 0), new Point(0.8f, 0.2f, 0), new Point(0.2f, 0.8f, 0)));
        accessoryTriangles.add(new Triangle(new Point(0.8f, 0.2f, 0), new Point(0.8f, 0.8f, 0), new Point(0.2f, 0.8f, 0)));
        return accessoryTriangles;
    }

    public static List<Point> findIntersectionPoints(List<Triangle> wallTriangles, List<Triangle> accessoryTriangles) {
        // Example: find intersection points between walls and accessories
        Set<Point> intersectionPoints = new HashSet<>();

        // Implementation to be filled based on your needs

        return new ArrayList<>(intersectionPoints);
    }

    public static List<Point> removeDuplicates(List<Point> points) {
        // Remove duplicates
        return new ArrayList<>(new HashSet<>(points));
    }

    public static List<Triangle> createRectangles(List<Point> intersectionPoints) {
        // Example: create rectangles from intersection points
        List<Triangle> rectangles = new ArrayList<>();

        // Sort intersection points by X coordinate
        Collections.sort(intersectionPoints, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Float.compare(p1.x, p2.x);
            }
        });

        // Create rectangles
        for (int i = 0; i < intersectionPoints.size() - 1; i++) {
            Point p1 = intersectionPoints.get(i);
            Point p2 = intersectionPoints.get(i + 1);
            rectangles.add(new Triangle(p1, new Point(p2.x, p1.y, p1.z), p2));
            rectangles.add(new Triangle(new Point(p2.x, p1.y, p1.z), new Point(p2.x, p2.y, p2.z), p2));
        }

        return rectangles;
    }

    private static String formatFloat(float value) {
        return String.format("%.6f", value);
    }

    public static void generateSTL(List<Triangle> wallTriangles, List<Triangle> rectangles, String fileName) {
        List<Point> intersectionPoints = findIntersectionPoints(wallTriangles, rectangles);

        // Sort intersection points by X coordinate
        Collections.sort(intersectionPoints, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Float.compare(p1.x, p2.x);
            }
        });

        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            // Write the STL file header
            fileWriter.write("solid generated\n");

            // Write the rectangles
            for (Triangle rectangle : rectangles) {
                writeFacet(fileWriter, rectangle);
            }

            // Write the triangles
            for (Triangle triangle : wallTriangles) {
                writeFacet(fileWriter, triangle);
            }

            // Write the STL file footer
            fileWriter.write("endsolid generated\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("STL file generated successfully: " + fileName);
    }

    private static void writeFacet(FileWriter fileWriter, Triangle triangle) throws IOException {
        float[] normal = calculateNormal(triangle.vertex1, triangle.vertex2, triangle.vertex3);

        // Replacement for vertex 1
        String normalA = formatFloat(normal[0]);
        String normalB = formatFloat(normal[1]);
        String normalC = formatFloat(normal[2]);

        // Write facet normal
        fileWriter.write("  facet normal " + normalA + " " + normalB + " " + normalC + System.lineSeparator());
        fileWriter.write("    outer loop\n");

        // Write vertex coordinates
        writeVertex(fileWriter, triangle.vertex1);
        writeVertex(fileWriter, triangle.vertex2);
        writeVertex(fileWriter, triangle.vertex3);

        // Write the STL file footer
        fileWriter.write("    endloop\n");
        fileWriter.write("  endfacet\n");
    }




    private static void writeVertex(FileWriter fileWriter, Point vertex) throws IOException {
        // Write vertex coordinates
        fileWriter.write(String.format("      vertex %.6f %.6f %.6f\n", vertex.x, vertex.y, vertex.z));
    }

    private static float[] calculateNormal(Point vertex1, Point vertex2, Point vertex3) {
        float[] defaultNormal = {0.0f, 0.0f, 1.0f}; // Default normal if calculation fails

        // Replace this with your actual logic to calculate the normal
        return defaultNormal;
    }
}
