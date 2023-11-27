package Utilitaires;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Triangle {
    public float[] vertex1;
    public float[]  vertex2;
    public float[]  vertex3;

    boolean accessoires;

    //constructeur
    public Triangle(float[]  vertex1, float[]  vertex2, float[]  vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }
}
