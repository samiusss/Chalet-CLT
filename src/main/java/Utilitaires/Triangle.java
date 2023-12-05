package Utilitaires;
import java.util.ArrayList;
import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class Triangle {
    public float[] vertex1;
    public float[]  vertex2;
    public float[]  vertex3;

    public float[] normal;

    public String Type;

    //constructeur

    public Triangle(float[]  vertex1, float[]  vertex2, float[]  vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;

        this.normal = calculateNormal();
    }

    public Triangle(float[]  vertex1, float[]  vertex2, float[]  vertex3, String Type) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        this.Type = Type;

        this.normal = calculateNormal();
    }

    public Triangle(float[] vertices) {
        this.vertex1 = vertices;
        this.vertex2 = vertices;
        this.vertex3 = vertices;
        this.normal = calculateNormal();
    }

    public void setNormal(float[] normal) {
        this.normal = calculateNormal();
    }

    public float[] getVertices() {
        return new float[]{vertex1[0], vertex1[1], vertex1[2], vertex2[0], vertex2[1], vertex2[2], vertex3[0], vertex3[1], vertex3[2]};
    }
    private float[] calculateNormal() {
        float[] vector1 = {vertex2[0] - vertex1[0], vertex2[1] - vertex1[1], vertex2[2] - vertex1[2]};
        float[] vector2 = {vertex3[0] - vertex1[0], vertex3[1] - vertex1[1], vertex3[2] - vertex1[2]};

        float[] normal = {
                vector1[1] * vector2[2] - vector1[2] * vector2[1],
                vector1[2] * vector2[0] - vector1[0] * vector2[2],
                vector1[0] * vector2[1] - vector1[1] * vector2[0]
        };

        float length = (float) Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);

        normal[0] /= length;
        normal[1] /= length;
        normal[2] /= length;

        return normal;
    }
    private Object[] toObjectArray(float[] floatArray){
        Object[] objectArray = new Object[floatArray.length];
        for(int i = 0; i < floatArray.length; i++){
            objectArray[i] = floatArray[i];
        }
        return objectArray;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Triangle other = (Triangle) obj;
        return Arrays.deepEquals(toObjectArray(this.getVertices()), toObjectArray(other.getVertices()));
    }
}
