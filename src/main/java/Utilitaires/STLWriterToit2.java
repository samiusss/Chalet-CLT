package Utilitaires;

import domain.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnFloat;
import static domain.Chalet.*;

public class STLWriterToit2 implements java.io.Serializable {
    public ChaletDTO chaletdto;
    public static Chalet chalet;
    public static Mur facade; // mur facade deja codé en bas
    public Mur gauche; // mur arriere deja codé en bas
    private Dimension initialDimension;
    public Mur arriere; // mur arriere deja codé en bas
    public Mur droite; // mur facade deja codé en bas

    public STLWriterToit2() {
        this.initialDimension = initialDimension;

        this.droite = ChaletDTO.droite;
        this.gauche = ChaletDTO.gauche;
        this.arriere = ChaletDTO.arriere;
        facade = ChaletDTO.facade;
    }

    // Méthode pour le traitement automatique des vertices
    private static String processVertex(float originalValue) {
        BigDecimal bd = new BigDecimal(Float.toString(originalValue));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String nombreAvecVirgule = bd.toPlainString();  // Utilisez toPlainString() pour éviter la notation scientifique

        String nombreAvecPoint = nombreAvecVirgule.replace(",", ".");
        return nombreAvecPoint;
    }


    public static List<float[]> determinerPointsPignon(float angle, float length, float width, float height, float space) {
        List<float[]> listeVertex = new LinkedList<>();


        float bigHeight = (float) Math.tan(angle) * (width - (float) epaisseurChalet*2);

        listeVertex.add(new float[]{0, 0, 0}); //v0
        listeVertex.add(new float[]{(float) epaisseurChalet, 0, 0}); //v1

        listeVertex.add(new float[]{0, 0, width - 2*(float)epaisseurChalet}); //v2
        listeVertex.add(new float[]{(float) epaisseurChalet, 0, width - 2*(float)epaisseurChalet}); //v3

        listeVertex.add(new float[]{0, bigHeight, width - 2*(float)epaisseurChalet}); //v4
        listeVertex.add(new float[]{(float) epaisseurChalet, bigHeight, width - 2*(float)epaisseurChalet}); //v5

        return listeVertex;
    }



    public static java.util.List<float[]> determinerPointsPrismes(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
        java.util.List<float[]> listeVertex = new LinkedList<>();
        int numCellsLength = 10;
        int numCellsWidth = 1;
        int numCellsHeight = 10;

        float cellLength = length / numCellsLength;
        float cellWidth = width / numCellsWidth;
        //float cellHeight = height / numCellsHeight;
        float cellHeight = height / numCellsHeight;

        for (int i = 0; i <= numCellsLength; i++) {
            for (int j = 0; j <= numCellsWidth; j++) {
                for (int k = 0; k <= numCellsHeight; k++) {
                    float x = xSupGauche + i * cellLength;
                    float y = ySupGauche + j * cellWidth;
                    float z = zSupGauche + k * cellHeight;
                    listeVertex.add(new float[]{x, y, z});
                }
            }
        }

        return listeVertex;
    }


    public static List<float[]> determinerPointRallongeVerticale(double angle, double length, double height, double width) {
        List<float[]> listeVertex = new LinkedList<>();

        if (orientationToit == "North" || orientationToit == "South") {
            // Swap length and width
            double temp = length;
            length = width;
            width = temp;
        }

        float firstBaseWidth = (float) ((float) Math.tan(angle) * (epaisseurChalet / 2));

        float hauteurV2V3 = (float) (Math.tan(angle) * (width - (float) epaisseurChalet));
        float hauteurV8V9 = (float) (Math.tan(angle) * (width - (float) epaisseurChalet/2));
        float hauteurV10V11 = (float) (Math.tan(angle) * (width - (float) epaisseurChalet/4));
        float hauteurV6V7 = (float) (float) (Math.tan(angle) * (width));

        System.out.println("hauteurV2V3: " + hauteurV2V3);
        System.out.println("hauteurV8V9: " + hauteurV8V9);
        System.out.println("hauteurV10V11: " + hauteurV10V11);
        System.out.println("hauteurV6V7: " + hauteurV6V7);


        listeVertex.add(new float[]{0, 0, 0});//v0
        listeVertex.add(new float[]{(float) length, 0, 0}); //v1

        listeVertex.add(new float[]{(float) length, hauteurV2V3, 0});//v2
        listeVertex.add(new float[]{0, hauteurV2V3, 0}); //v3

        listeVertex.add(new float[]{0, 0, (float) epaisseurChalet}); //v4
        listeVertex.add(new float[]{(float) length, 0, (float) epaisseurChalet}); //v5

        listeVertex.add(new float[]{(float) length, hauteurV6V7, (float) epaisseurChalet}); //v6
        listeVertex.add(new float[]{0, hauteurV6V7, (float) epaisseurChalet}); //v7

        listeVertex.add(new float[]{0, hauteurV8V9, (float) epaisseurChalet / 2}); //v8
        listeVertex.add(new float[]{(float) length, hauteurV8V9, (float) epaisseurChalet / 2});//v9

        listeVertex.add(new float[]{(float) length, hauteurV10V11, (float) epaisseurChalet / 2});//v10
        listeVertex.add(new float[]{0,hauteurV10V11, (float) epaisseurChalet / 2});//v11

        listeVertex.add(new float[]{0, hauteurV8V9, (float) epaisseurChalet});//v12
        listeVertex.add(new float[]{(float) length, hauteurV10V11, (float) epaisseurChalet});//v13

        listeVertex.add(new float[]{0, hauteurV10V11, (float) epaisseurChalet});//v14
        listeVertex.add(new float[]{0, hauteurV2V3, (float) epaisseurChalet / 2}); //v15

        listeVertex.add(new float[]{(float) length, hauteurV2V3, (float) epaisseurChalet / 2}); //v16
        listeVertex.add(new float[]{0, hauteurV2V3, (float) epaisseurChalet});//v17

        listeVertex.add(new float[]{(float) length, hauteurV2V3, (float) epaisseurChalet});//v18
        listeVertex.add(new float[]{(float) length, hauteurV8V9, (float) epaisseurChalet});//v19

        //points pour brut
        listeVertex.add(new float[]{0, hauteurV6V7, 0}); //v20
        listeVertex.add(new float[]{(float) length, hauteurV6V7, 0}); //v21

        //et retrait
        listeVertex.add(new float[]{0, hauteurV6V7, (float)epaisseurChalet/2}); //v22
        listeVertex.add(new float[]{(float) length, hauteurV6V7, (float)epaisseurChalet/2}); //v23
        listeVertex.add(new float[]{0, hauteurV8V9, 0}); //v24
        listeVertex.add(new float[]{(float) length, hauteurV8V9, 0}); //v25

        System.out.println("epaisseurChalet/2: " + (float) Math.tan(angle) * epaisseurChalet/2);
        System.out.println("epaisseurChalet: " + (float) Math.tan(angle) * epaisseurChalet);
        System.out.println("width: " + (float) Math.tan(angle) * width);
        System.out.println("width - epaisseurChalet: " + (float) (Math.tan(angle) * width - (float) epaisseurChalet));
        System.out.println("width - epaisseurChalet/2: " + (float) (Math.tan(angle) * width - (float) epaisseurChalet/2));

        return listeVertex;
    }


    public static List<Triangle> generateRallongeVerticaleBrut(double anglee, double lenghtt, double widthh, double heightt, String type){
        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;

        List<Triangle> trianglesRallongeBrut = new ArrayList<>();

        List<float[]> listeVertexRallonge = determinerPointRallongeVerticale(angle, length, height, width);

        float[] v0 = listeVertexRallonge.get(0);
        float[] v1 = listeVertexRallonge.get(1);
        float[] v4 = listeVertexRallonge.get(4);
        float[] v5 = listeVertexRallonge.get(5);
        float[] v6 = listeVertexRallonge.get(6);
        float[] v7 = listeVertexRallonge.get(7);
        float[] v20 = listeVertexRallonge.get(20);
        float[] v21 = listeVertexRallonge.get(21);

        trianglesRallongeBrut.add(new Triangle(v0, v1, v21, type));
        trianglesRallongeBrut.add(new Triangle(v0, v20, v21, type));

        trianglesRallongeBrut.add(new Triangle(v21, v20, v7, type));
        trianglesRallongeBrut.add(new Triangle(v21, v7, v6, type));

        trianglesRallongeBrut.add(new Triangle(v7, v5, v6, type));
        trianglesRallongeBrut.add(new Triangle(v7, v5, v4, type));

        trianglesRallongeBrut.add(new Triangle(v4, v5, v1, type));
        trianglesRallongeBrut.add(new Triangle(v4, v1, v0, type));

        trianglesRallongeBrut.add(new Triangle(v4, v7, v20, type));
        trianglesRallongeBrut.add(new Triangle(v4, v20, v0, type));

        trianglesRallongeBrut.add(new Triangle(v5, v6, v21, type));
        trianglesRallongeBrut.add(new Triangle(v5, v21, v1, type));

        return trianglesRallongeBrut;

    }



    public static List<Triangle> generatePignonBrut(double anglee, double lenghtt, double widthh, double heightt, String type) {

        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;

        List<Triangle> trianglesPignon = new ArrayList<>();
        List<float[]> listeVertexPignonGauche = determinerPointsPignon(angle, length, width, height, length);
        //List<float[]> listeVertexPignonDroit = determinerPointsPignon(angle, length, width, height, length);


        float[] v0 = listeVertexPignonGauche.get(0);
        float[] v1 = listeVertexPignonGauche.get(1);
        float[] v2 = listeVertexPignonGauche.get(2);
        float[] v3 = listeVertexPignonGauche.get(3);
        float[] v4 = listeVertexPignonGauche.get(4);
        float[] v5 = listeVertexPignonGauche.get(5);

        trianglesPignon.add(new Triangle(v0, v1, v2, type));
        trianglesPignon.add(new Triangle(v2, v3, v1, type));

        trianglesPignon.add(new Triangle(v0, v1, v4, type));
        trianglesPignon.add(new Triangle(v1, v4, v5, type));

        trianglesPignon.add(new Triangle(v4, v5, v2, type));
        trianglesPignon.add(new Triangle(v2, v3, v5, type));

        trianglesPignon.add(new Triangle(v0, v2, v4, type));
        trianglesPignon.add(new Triangle(v1, v3, v5, type));


        return trianglesPignon;
    }


    public static void ExporterRallongeVerticaleBrut(String fileName){

        java.util.List<Triangle> listeTriangles = generateRallongeVerticaleBrut(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
        generateSTL(listeTriangles,fileName);
    }

    public static void ExporterPignonBrutDroite(String fileName) {

        List<Triangle> listeTrianglesPignon = generatePignonBrut(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs, "ARRIERE");

        // Generate STL file
        generateSTL(listeTrianglesPignon, fileName);
    }

    public static void ExporterPignonBrutGauche(String fileName) {

        List<Triangle> listeTrianglesPignon = generatePignonBrut(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs, "ARRIERE");

        generateSTL(listeTrianglesPignon, fileName);
    }








    public static java.util.List<Triangle> generateRectangularPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, String type)
    {
        java.util.List<Triangle> triangles = new ArrayList<>();
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        int numCellsLength = 10;
        int numCellsWidth = 1;
        int numCellsHeight = 10;

        for (int i = 0; i < numCellsLength; i++) {
            for (int j = 0; j < numCellsWidth; j++) {
                for (int k = 0; k < numCellsHeight; k++) {
                    int index = i * (numCellsWidth + 1) * (numCellsHeight + 1) + j * (numCellsHeight + 1) + k;

                    float[] v0 = listeVertex.get(index);
                    float[] v1 = listeVertex.get(index + 1);
                    float[] v2 = listeVertex.get(index + (numCellsHeight + 1));
                    float[] v3 = listeVertex.get(index + (numCellsHeight + 1) + 1);
                    float[] v4 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1));
                    float[] v5 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + 1);
                    float[] v6 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1));
                    float[] v7 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1) + 1);

                    // Front face
                    triangles.add(new Triangle(v0, v1, v2, type));
                    triangles.add(new Triangle(v1, v3, v2, type));

                    // Back face
                    triangles.add(new Triangle(v4, v6, v5, type));
                    triangles.add(new Triangle(v5, v6, v7, type));

                    // Left face
                    triangles.add(new Triangle(v0, v2, v4, type));
                    triangles.add(new Triangle(v2, v6, v4, type));

                    // Right face
                    triangles.add(new Triangle(v1, v5, v3, type));
                    triangles.add(new Triangle(v3, v5, v7, type));

                    // Top face
                    triangles.add(new Triangle(v2, v3, v6, type));
                    triangles.add(new Triangle(v3, v7, v6, type));

                    // Bottom face
                    triangles.add(new Triangle(v0, v4, v1, type));
                    triangles.add(new Triangle(v1, v4, v5, type));


                    for (int n = numCellsLength - 1; n >= 0; n--) {
                        for (int m = 0; m < numCellsWidth - 1; m++) {
                            for (int l = 0; l < numCellsHeight; l++) {

                                /*triangles.remove(Triangle(v3, v7, v6, type));

                                triangles.remove(Triangle(v1, v5, v4, type));

                                triangles.remove(Triangle(v4, v6, v5, type));
                                triangles.remove(Triangle(v5, v6, v7, type));

                                triangles.remove(Triangle(v1, v5, v3, type));
                                triangles.remove(Triangle(v3, v5, v7, type));*/
                                triangles.removeIf(triangle ->
                                        Arrays.equals(triangle.getV0(), v3) &&
                                                Arrays.equals(triangle.getV1(), v1) &&
                                                Arrays.equals(triangle.getV2(), v4)
                                );
                                triangles.removeIf(triangle ->
                                        Arrays.equals(triangle.getV0(), v1) &&
                                                Arrays.equals(triangle.getV1(), v5) &&
                                                Arrays.equals(triangle.getV2(), v4)
                                );

                                triangles.removeIf(triangle ->
                                        Arrays.equals(triangle.getV0(), v4) &&
                                                Arrays.equals(triangle.getV1(), v6) &&
                                                Arrays.equals(triangle.getV2(), v5)
                                );
                                triangles.removeIf(triangle ->
                                        Arrays.equals(triangle.getV0(), v5) &&
                                                Arrays.equals(triangle.getV1(), v6) &&
                                                Arrays.equals(triangle.getV2(), v7)
                                );

                                triangles.removeIf(triangle ->
                                        Arrays.equals(triangle.getV0(), v1) &&
                                                Arrays.equals(triangle.getV1(), v5) &&
                                                Arrays.equals(triangle.getV2(), v3)
                                );
                                triangles.removeIf(triangle ->
                                        Arrays.equals(triangle.getV0(), v3) &&
                                                Arrays.equals(triangle.getV1(), v5) &&
                                                Arrays.equals(triangle.getV2(), v7)
                                );

                                triangles.add(new Triangle(v3, v1, v4, type));
                                triangles.add(new Triangle(v4, v6, v3, type));

                            }
                        }
                    }
                }
            }
        }

        return triangles;
    }


    public static java.util.List<float[]> determinerPointsPrismeSupDroite(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, float xSupDroit, float ySupDroit, float zSupDroit) {

        java.util.List<float[]> listeVertex = new LinkedList<>();

        int numCellsLength = 35;
        int numCellsWidth = 1;
        int numCellsHeight = 25;

        float cellLength = length / numCellsLength;
        float cellWidth = width / numCellsWidth;
        float cellHeight = height / numCellsHeight;

        for (int i = 0; i <= numCellsLength; i++) {
            for (int j = 0; j <= numCellsWidth; j++) {
                for (int k = 0; k <= numCellsHeight; k++) {
                    float x = xSupGauche + i * cellLength + j * (xSupDroit - xSupGauche) / numCellsWidth;
                    float y = ySupGauche + j * cellWidth;
                    float z = zSupGauche + k * cellHeight;
                    listeVertex.add(new float[]{x, y, z});
                }
            }
        }

        return listeVertex;
    }

    public static java.util.List<Triangle> generateRectangularPrismeSupDroite(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, float xSupDroit, float ySupDroit, float zSupDroit, String type) {
        java.util.List<Triangle> triangles = new ArrayList<>();
        java.util.List<float[]> listeVertex = determinerPointsPrismeSupDroite(length, width, height, xSupGauche, ySupGauche, zSupGauche, xSupDroit, ySupDroit, zSupDroit);

        int numCellsLength = 35;
        int numCellsWidth = 1;
        int numCellsHeight = 25;

        for (int i = 0; i < numCellsLength; i++) {
            for (int j = 0; j < numCellsWidth; j++) {
                for (int k = 0; k < numCellsHeight; k++) {
                    int index = i * (numCellsWidth + 1) * (numCellsHeight + 1) + j * (numCellsHeight + 1) + k;
                    float[] v0 = listeVertex.get(index);
                    float[] v1 = listeVertex.get(index + 1);
                    float[] v2 = listeVertex.get(index + (numCellsHeight + 1));
                    float[] v3 = listeVertex.get(index + (numCellsHeight + 1) + 1);
                    float[] v4 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1));
                    float[] v5 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + 1);
                    float[] v6 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1));
                    float[] v7 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1) + 1);

                    // Reste du code inchangé...
                }
            }
        }

        return triangles;
    }

    public static java.util.List<Triangle> generateRectangularPrismWithoutType(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {

        java.util.List<Triangle> triangles = new ArrayList<>();
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);
        //Mettre a l'echelle avec InitialDimension
        int numCellsLength = 35;
        int numCellsWidth = 1;
        int numCellsHeight = 25;

        /* int numCellsLength = 60;
        int numCellsWidth = 1;
        int numCellsHeight = 40; */

        for (int i = 0; i < numCellsLength; i++) {
            for (int j = 0; j < numCellsWidth; j++) {
                for (int k = 0; k < numCellsHeight; k++) {
                    int index = i * (numCellsWidth + 1) * (numCellsHeight + 1) + j * (numCellsHeight + 1) + k;
                    float[] v0 = listeVertex.get(index);
                    float[] v1 = listeVertex.get(index + 1);
                    float[] v2 = listeVertex.get(index + (numCellsHeight + 1));
                    float[] v3 = listeVertex.get(index + (numCellsHeight + 1) + 1);
                    float[] v4 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1));
                    float[] v5 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + 1);
                    float[] v6 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1));
                    float[] v7 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1) + 1);

                    // Front face
                    triangles.add(new Triangle(v0, v1, v2));
                    triangles.add(new Triangle(v1, v3, v2));

                    // Back face
                    triangles.add(new Triangle(v4, v6, v5));
                    triangles.add(new Triangle(v5, v6, v7));

                    // Left face
                    triangles.add(new Triangle(v0, v2, v4));
                    triangles.add(new Triangle(v2, v6, v4));

                    // Right face
                    triangles.add(new Triangle(v1, v5, v3));
                    triangles.add(new Triangle(v3, v5, v7));

                    // Top face
                    triangles.add(new Triangle(v2, v3, v6));
                    triangles.add(new Triangle(v3, v7, v6));

                    // Bottom face
                    triangles.add(new Triangle(v0, v4, v1));
                    triangles.add(new Triangle(v1, v4, v5));
                }
            }
        }

        return triangles;
    }

    public static float[] calculerNormale2(float[] pointA, float[] pointB, float[] pointC) {
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

    public static float[] calculerNormaleAvecTransformations(float[] vertex1, float[] vertex2, float[] vertex3, String type) {
        float[] normal = new float[3];

        // Calculate the cross product of two edges of the triangle
        float[] edge1 = new float[3];
        float[] edge2 = new float[3];
        for (int i = 0; i < 3; i++) {
            edge1[i] = vertex2[i] - vertex1[i];
            edge2[i] = vertex3[i] - vertex1[i];
        }

        normal[0] = edge1[1] * edge2[2] - edge1[2] * edge2[1];
        normal[1] = edge1[2] * edge2[0] - edge1[0] * edge2[2];
        normal[2] = edge1[0] * edge2[1] - edge1[1] * edge2[0];

        // Normalize the normal vector
        float length = (float) Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);

        if (Float.isNaN(length) || length == 0) {
            // Handle the case where the length is NaN or zero
            // You can set a default normal or perform some other appropriate action
            normal[0] = 1.0f;
            normal[1] = 0.0f;
            normal[2] = 0.0f;
        } else {
            normal[0] /= length;
            normal[1] /= length;
            normal[2] /= length;
        }

        return normal;
    }

    private static void appliquerTranslation(float[] point, float[] translation) {
        point[0] += translation[0];
        point[1] += translation[1];
        point[2] += translation[2];
    }

    private static void appliquerRotation(float[] point, float angle) {
        // Appliquer la rotation autour de l'axe y
        float x = point[0];
        float z = point[2];
        point[0] = (float) (x * Math.cos(angle) - z * Math.sin(angle));
        point[2] = (float) (x * Math.sin(angle) + z * Math.cos(angle));
    }

    private static void appliquerMiseAEchelle(float[] point, float[] scale) {
        point[0] *= scale[0];
        point[1] *= scale[1];
        point[2] *= scale[2];
    }

    public static java.util.List<Triangle> decomposerRectangleTriangle(Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit, Double EpaisseurChalet){
        /* Un rectangle est composer de deux triangles */

        Double Epaisseur = 8.0;

        float[] vertexTriangle1_a = {(float) coinSupGauche.getX(), (float) coinSupGauche.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle1_b= {(float) coinSupDroit.getX(), (float) coinSupDroit.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle1_c = {(float) coinInfGauche.getX(), (float) coinInfGauche.getY(), Epaisseur.floatValue()};

        float[] vertexTriangle2_a = {(float) coinInfDroit.getX(), (float) coinInfDroit.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle2_b = {(float) coinInfGauche.getX(), (float) coinInfGauche.getY(), Epaisseur.floatValue()};
        float[] vertexTriangle2_c = {(float) coinSupDroit.getX(), (float) coinSupDroit.getY(), Epaisseur.floatValue()};

        Triangle triangle1 = new Triangle(vertexTriangle1_a,vertexTriangle1_b,vertexTriangle1_c);
        Triangle triangle2 = new Triangle(vertexTriangle2_a,vertexTriangle2_b,vertexTriangle2_c);


        java.util.List<Triangle> listeTriangle = new LinkedList<>();
        listeTriangle.add(triangle1);
        listeTriangle.add(triangle2);


        return listeTriangle;

    }

    //Dans ce code, les boucles for à travers la hauteur (k) excluent la première et la dernière itération, ce qui correspond aux faces supérieure et inférieure du prisme. Cela devrait générer le prisme sans ces deux faces. Assurez-vous de bien tester le code pour vous assurer qu'il fonctionne comme prévu dans votre application.
    public static java.util.List<Triangle> generateRectangularPrismPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, String type) {
        java.util.List<Triangle> triangles = new ArrayList<>();
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        int numCellsLength = 35;
        int numCellsWidth = 1;
        int numCellsHeight = 25;

        int startHeight = 1; // Exclure la face inférieure
        int endHeight = numCellsHeight - 1; // Exclure la face supérieure

        for (int i = 0; i < numCellsLength; i++) {
            for (int j = 0; j < numCellsWidth; j++) {
                for (int k = startHeight; k < endHeight; k++) {

                    int index = i * (numCellsWidth + 1) * (numCellsHeight + 1) + j * (numCellsHeight + 1) + k;
                    float[] v0 = listeVertex.get(index);
                    float[] v1 = listeVertex.get(index + 1);
                    float[] v2 = listeVertex.get(index + (numCellsHeight + 1));
                    float[] v3 = listeVertex.get(index + (numCellsHeight + 1) + 1);
                    float[] v4 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1));
                    float[] v5 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + 1);
                    float[] v6 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1));
                    float[] v7 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1) + 1);

                    // Subdivide each face further
                    int subCellsLength = 2;  // Number of subdivisions in the length direction
                    int subCellsWidth = 2;   // Number of subdivisions in the width direction
                    int subCellsHeight = 2;  // Number of subdivisions in the height direction

                    float subCellLength = 1.0f / subCellsLength;
                    float subCellWidth = 1.0f / subCellsWidth;
                    float subCellHeight = 1.0f / subCellsHeight;

                    for (int l = 0; l < subCellsLength; l++) {
                        for (int w = 0; w < subCellsWidth; w++) {
                            for (int h = 0; h < subCellsHeight; h++) {
                                float[] v0Sub = interpolateVertices(v0, v1, v2, v3, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l, w, h);
                                float[] v1Sub = interpolateVertices(v0, v1, v2, v3, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l + 1, w, h);
                                float[] v2Sub = interpolateVertices(v0, v1, v2, v3, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l, w + 1, h);
                                float[] v3Sub = interpolateVertices(v0, v1, v2, v3, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l + 1, w + 1, h);
                                float[] v4Sub = interpolateVertices(v4, v5, v6, v7, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l, w, h);
                                float[] v5Sub = interpolateVertices(v4, v5, v6, v7, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l + 1, w, h);
                                float[] v6Sub = interpolateVertices(v4, v5, v6, v7, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l, w + 1, h);
                                float[] v7Sub = interpolateVertices(v4, v5, v6, v7, v4, v5, v6, v7, subCellLength, subCellWidth, subCellHeight, l + 1, w + 1, h);

                                // Generate triangles for the subdivided face
                                triangles.add(new Triangle(v0Sub, v1Sub, v2Sub, type));
                                triangles.add(new Triangle(v1Sub, v3Sub, v2Sub, type));
                                triangles.add(new Triangle(v4Sub, v6Sub, v5Sub, type));
                                triangles.add(new Triangle(v5Sub, v6Sub, v7Sub, type));
                                triangles.add(new Triangle(v0Sub, v2Sub, v4Sub, type));
                                triangles.add(new Triangle(v2Sub, v6Sub, v4Sub, type));
                                triangles.add(new Triangle(v1Sub, v5Sub, v3Sub, type));
                                triangles.add(new Triangle(v3Sub, v5Sub, v7Sub, type));
                                triangles.add(new Triangle(v2Sub, v3Sub, v6Sub, type));
                                triangles.add(new Triangle(v3Sub, v7Sub, v6Sub, type));
                                triangles.add(new Triangle(v0Sub, v4Sub, v1Sub, type));
                                triangles.add(new Triangle(v1Sub, v4Sub, v5Sub, type));
                            }
                        }
                    }

                }
            }
        }

        return triangles;
    }

    private static float[] interpolateVertices(float[] v0, float[] v1, float[] v2, float[] v3, float[] v4, float[] v5, float[] v6, float[] v7, float subCellLength, float subCellWidth, float subCellHeight, int l, int w, int h) {
        float u = l * subCellLength;
        float v = w * subCellWidth;
        float t = h * subCellHeight;

        float u1 = 1.0f - u;
        float v1Value = 1.0f - v;  // Rename variable from v1 to v1Value
        float t1 = 1.0f - t;

        float[] interpolatedVertex = new float[3];
        interpolatedVertex[0] = (u1 * v1Value * t1 * v0[0] + u * v1Value * t1 * v1[0] + u1 * v * t1 * v2[0] + u * v * t1 * v3[0] +
                u1 * v1Value * t * v4[0] + u * v1Value * t * v5[0] + u1 * v * t * v6[0] + u * v * t * v7[0]);

        interpolatedVertex[1] = (u1 * v1Value * t1 * v0[1] + u * v1Value * t1 * v1[1] + u1 * v * t1 * v2[1] + u * v * t1 * v3[1] +
                u1 * v1Value * t * v4[1] + u * v1Value * t * v5[1] + u1 * v * t * v6[1] + u * v * t * v7[1]);

        interpolatedVertex[2] = (u1 * v1Value * t1 * v0[2] + u * v1Value * t1 * v1[2] + u1 * v * t1 * v2[2] + u * v * t1 * v3[2] +
                u1 * v1Value * t * v4[2] + u * v1Value * t * v5[2] + u1 * v * t * v6[2] + u * v * t * v7[2]);

        return interpolatedVertex;
    }


    public static void generateSTL(java.util.List<Triangle> triangles, String fileName) {
        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            // Écrire l'en-tête du fichier STL
            fileWriter.write("solid generated\n");

            // Écrire les triangles
            for (Triangle triangle : triangles) {


                float[] normal = calculerNormaleAvecTransformations(triangle.vertex1, triangle.vertex2,triangle.vertex3, triangle.Type);

                // Remplacement pour le vertex 1
                String normalA = processVertex(normal[0]);

                String normalB = processVertex(normal[1]);

                String normalC = processVertex(normal[2]);

                // Remplacement pour le vertex 1
                String vertex1a = processVertex(triangle.vertex1[0]);
                //float vertex1a = Float.parseFloat(vertex1String);

                String vertex1b = processVertex(triangle.vertex1[1]);

                String vertex1c = processVertex(triangle.vertex1[2]);

                // Remplacement pour le vertex 2
                String vertex2a = processVertex(triangle.vertex2[0]);

                String vertex2b = processVertex(triangle.vertex2[1]);

                String vertex2c = processVertex(triangle.vertex2[2]);

                // Remplacement pour le vertex 3
                String vertex3a = processVertex(triangle.vertex3[0]);

                String vertex3b = processVertex(triangle.vertex3[1]);

                String vertex3c = processVertex(triangle.vertex3[2]);

                fileWriter.write("  facet normal " +normalA+" "+normalB+" "+ normalC+ System.getProperty("line.separator")
                );
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




    private static List<Triangle> generateTriangle(float[] v0, float[] v1, float[] v2, String type) {
        List<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(v0, v1, v2, type));
        return triangles;
    }



}