package Utilitaires;

import domain.Chalet;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class STLretrait {
    protected static void RectangleToSTL(FileWriter writer, double startX, double endX, double startY, double endY, double z, String normalVector) throws IOException {
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + startX + " " + startY + " " + z + "\n");
        writer.write("vertex " + endX + " " + startY + " " + z + "\n");
        writer.write("vertex " + endX + " " + endY + " " + z + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + startX + " " + startY + " " + z + "\n");
        writer.write("vertex " + endX + " " + endY + " " + z + "\n");
        writer.write("vertex " + startX + " " + endY + " " + z + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
    }








}
