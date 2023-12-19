package Utilitaires;

import domain.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnFloat;
import static domain.Chalet.*;

public class STLWriterToit implements java.io.Serializable {
    public ChaletDTO chaletdto;
    public static Chalet chalet;
    public static Mur facade; // mur facade deja codé en bas
    public Mur gauche; // mur arriere deja codé en bas
    private Dimension initialDimension;
    public Mur arriere; // mur arriere deja codé en bas
    public Mur droite; // mur facade deja codé en bas

    public STLWriterToit() {
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

    public static List<float[]> determinerPointsPignonFiniDroite(float angle, float length, float width, float height, float space) {
        List<float[]> listeVertex = new LinkedList();

        float x = 0;
        float y = 0;
        float z = 0;

        int numCellsLength = 10;
        int numCellsWidth = 1;
        int numCellsHeight = 10;

        float smallLength = width - (float) epaisseurChalet*2 - (float) epaisseurChalet;

        float smallHeight = (float) Math.tan(angle) * smallLength;
        float bigHeight = (float) Math.tan(angle) * (width - (float) epaisseurChalet*2);

        listeVertex.add(new float[]{0, 0, 0}); //v0
        listeVertex.add(new float[]{(float) epaisseurChalet / 2, 0, 0}); //v1

        listeVertex.add(new float[]{0, 0, width - 2*(float)epaisseurChalet}); //v2
        listeVertex.add(new float[]{(float) epaisseurChalet/2, 0, width - 2*(float)epaisseurChalet}); //v3

        listeVertex.add(new float[]{0, bigHeight, width - 2*(float)epaisseurChalet}); //v4
        listeVertex.add(new float[]{(float) epaisseurChalet/2, bigHeight, width - 2*(float)epaisseurChalet}); //v5

        listeVertex.add(new float[]{(float) epaisseurChalet/2, 0, (float) epaisseurChalet/2}); //v6
        listeVertex.add(new float[]{(float) epaisseurChalet, 0, (float) epaisseurChalet/2}); //v7

        listeVertex.add(new float[]{(float) epaisseurChalet/2, 0, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v8
        listeVertex.add(new float[]{(float) epaisseurChalet, 0, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v9

        listeVertex.add(new float[]{(float) epaisseurChalet/2, smallHeight, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v10
        listeVertex.add(new float[]{(float) epaisseurChalet, smallHeight, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v11


        return listeVertex;
    }

    public static List<float[]> determinerPointsPignonFiniGauche(float angle, float length, float width, float height, float space) {
        List<float[]> listeVertex = new LinkedList();

        float x = 0;
        float y = 0;
        float z = 0;

        int numCellsLength = 10;
        int numCellsWidth = 1;
        int numCellsHeight = 10;

        float smallLength = width - (float) epaisseurChalet*2 - (float) epaisseurChalet;

        float smallHeight = (float) Math.tan(angle) * smallLength;
        float bigHeight = (float) Math.tan(angle) * (width - (float) epaisseurChalet*2);

        listeVertex.add(new float[]{0, 0, 0}); //v0
        listeVertex.add(new float[]{(float) epaisseurChalet / 2, 0, 0}); //v1

        listeVertex.add(new float[]{0, 0, width - 2*(float)epaisseurChalet}); //v2
        listeVertex.add(new float[]{(float) epaisseurChalet/2, 0, width - 2*(float)epaisseurChalet}); //v3

        listeVertex.add(new float[]{0, bigHeight, width - 2*(float)epaisseurChalet}); //v4
        listeVertex.add(new float[]{(float) epaisseurChalet/2, bigHeight, width - 2*(float)epaisseurChalet}); //v5

        listeVertex.add(new float[]{(float) 0, 0, (float) epaisseurChalet/2}); //v6
        listeVertex.add(new float[]{(float) -epaisseurChalet/2, 0, (float) epaisseurChalet/2}); //v7

        listeVertex.add(new float[]{(float) 0, 0, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v8
        listeVertex.add(new float[]{(float) -epaisseurChalet/2, 0, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v9

        listeVertex.add(new float[]{(float) 0, smallHeight, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v10
        listeVertex.add(new float[]{(float) -epaisseurChalet/2, smallHeight, width - 2*(float) epaisseurChalet - (float) epaisseurChalet/2}); //v11


        return listeVertex;
    }

    public static List<float[]> determinerPointsParDessus(double anglee, double lengthh, double widthh, double epaisseurr, double heightt, String type){

        float angle = (float) anglee;
        float length = (float) lengthh;
        float width = (float) widthh;
        float height = (float) heightt;
        float epaisseur = (float) epaisseurr;

        List<float[]> listeVertex = new LinkedList<>();

        float floatParDessusBigWidth = (float) Math.cos(angle) / (width + 2*epaisseur);
        float floatParDessusSmallWidth = ((float) Math.tan(angle) * (width)) + (float) Math.cos(angle) / (epaisseur/2);

        float hauteurP = length * (float) (Math.tan(angle * Math.PI / 180));
        float hauteurR = hauteurP + (float) ((epaisseur/2)*(Math.tan(angle * Math.PI / 180)));

        listeVertex.add(new float[]{0, 0, 0}); // v0
        listeVertex.add(new float[]{0, 0, width}); // v1
        listeVertex.add(new float[]{0, epaisseur, width}); // v2
        listeVertex.add(new float[]{0, epaisseur, 0}); // v3
        listeVertex.add(new float[]{epaisseur, 0, 0}); // v4
        listeVertex.add(new float[]{epaisseur, 0, width}); // v5
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur / 2), hauteurP, 0}); // v6
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur / 2), hauteurP, width}); // v7
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur / 2), (hauteurP + epaisseur / 2), 0}); // v8
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur / 2), (hauteurP + epaisseur / 2), width}); // v9
        listeVertex.add(new float[]{length, hauteurR + epaisseur / 2, 0}); // v10
        listeVertex.add(new float[]{length, hauteurR + epaisseur / 2, width}); // v11
        listeVertex.add(new float[]{length, hauteurR + epaisseur, 0}); // v12
        listeVertex.add(new float[]{length, hauteurR + epaisseur, width}); // v13

        listeVertex.add(new float[]{epaisseur, ((float) Math.tan(angle) * epaisseur) + epaisseur, 0}); // v14
        listeVertex.add(new float[]{epaisseur, ((float) Math.tan(angle) * epaisseur), width}); // v15
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur / 2), hauteurP + epaisseur, width + epaisseur}); // v16
        listeVertex.add(new float[]{length, hauteurR + epaisseur, width + epaisseur}); // v17
        listeVertex.add(new float[]{length, hauteurR + epaisseur, width}); // v18
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur / 2), hauteurP + epaisseur, width}); // v19
        listeVertex.add(new float[]{epaisseur, 0, width}); // v20
        listeVertex.add(new float[]{0, 0, width}); // v21


        return listeVertex;

    }

    public static List<float[]> determinerPointsParDessusBrut(double anglee, double lengthh, double widthh, double epaisseurr, double heightt, String type){

        float angle = (float) anglee;
        float length = (float) lengthh;
        float width = (float) widthh;
        float height = (float) heightt;
        float epaisseur = (float) epaisseurr;

        List<float[]> listeVertex = new LinkedList<>();


        float hauteurP = (length * (float) (Math.tan(angle * Math.PI / 180))) - (epaisseur);
        float hauteurR = hauteurP + (float) ((epaisseur/2)*(Math.tan(angle * Math.PI / 180)));

        listeVertex.add(new float[]{0, 0, 0}); // v0
        listeVertex.add(new float[]{epaisseur, 0, 0}); // v1
        listeVertex.add(new float[]{0, epaisseur, 0}); // v2
        listeVertex.add(new float[]{length, hauteurP, 0}); // v3
        listeVertex.add(new float[]{length, hauteurR, 0}); // v4

        listeVertex.add(new float[]{0, 0, width}); // v5
        listeVertex.add(new float[]{epaisseur, 0, width}); // v6
        listeVertex.add(new float[]{0, epaisseur, width}); // v7
        listeVertex.add(new float[]{length, hauteurP, width}); // v8
        listeVertex.add(new float[]{length, hauteurR, width}); // v9


        return listeVertex;

    }
    public static List<float[]> determinerPointsRetraitParDessus(double anglee, double lengthh, double widthh, double epaisseurr, double heightt, String type){

        float angle = (float) anglee;
        float length = (float) lengthh;
        float width = (float) widthh;
        float height = (float) heightt;
        float epaisseur = (float) epaisseurr;

        List<float[]> listeVertex = new LinkedList<>();

        float floatParDessusBigWidth = (float) Math.cos(angle) / (width + 2*epaisseur);
        float floatParDessusSmallWidth = ((float) Math.tan(angle) * (width)) + (float) Math.cos(angle) / (epaisseur/2);

        float hauteurP = length * (float) (Math.tan(angle * Math.PI / 180));
        float hauteurR = hauteurP + (float) ((epaisseur/2)*(Math.tan(angle * Math.PI / 180)));

        listeVertex.add(new float[]{0, 0, 0}); //v0
        listeVertex.add(new float[]{epaisseur/2, (float)Math.tan(angle)*epaisseur/2, 0}); //v1
        listeVertex.add(new float[]{0, (float)Math.tan(angle)*epaisseur/2, 0}); //v2
        listeVertex.add(new float[]{0, epaisseur/2, 0}); //v3
        listeVertex.add(new float[]{epaisseur/2, epaisseur/2, 0}); //v4
        listeVertex.add(new float[]{epaisseur/2, (float)(Math.tan(angle)*epaisseur/2)+epaisseur/2, 0}); //v5

        listeVertex.add(new float[]{0, 0, width}); //v6
        listeVertex.add(new float[]{epaisseur/2, (float)Math.tan(angle)*epaisseur/2, width}); //v7
        listeVertex.add(new float[]{0, (float)Math.tan(angle)*epaisseur/2, width}); //v8
        listeVertex.add(new float[]{0, epaisseur/2, width}); //v9
        listeVertex.add(new float[]{epaisseur/2, epaisseur/2, width}); //v10
        listeVertex.add(new float[]{epaisseur/2, (float)(Math.tan(angle)*epaisseur/2)+epaisseur/2, width}); //v11

        return listeVertex;
    }

    public static List<Triangle> generateParDessus(double anglee, double lengthh, double widthh, double heightt, double epaisseurr, String type){
        float angle = (float) anglee;
        float length = (float) lengthh;
        float width = (float) widthh;
        float height = (float) heightt;
        float epaisseur = (float) epaisseurr;

        List<Triangle> trianglesParDessus = new ArrayList<>();

        List<float[]> listeVertexParDessus = determinerPointsParDessus(angle, length, width, epaisseur, height, "AVANT");

        float[] v0 = listeVertexParDessus.get(0);
        float[] v1 = listeVertexParDessus.get(1);
        float[] v2 = listeVertexParDessus.get(2);
        float[] v3 = listeVertexParDessus.get(3);
        float[] v4 = listeVertexParDessus.get(4);
        float[] v5 = listeVertexParDessus.get(5);
        float[] v6 = listeVertexParDessus.get(6);
        float[] v7 = listeVertexParDessus.get(7);
        float[] v8 = listeVertexParDessus.get(8);
        float[] v9 = listeVertexParDessus.get(9);
        float[] v10 = listeVertexParDessus.get(10);
        float[] v11 = listeVertexParDessus.get(11);
        float[] v12 = listeVertexParDessus.get(12);
        float[] v13 = listeVertexParDessus.get(13);

        trianglesParDessus.add(new Triangle(v0, v1, v2, type));
        trianglesParDessus.add(new Triangle(v0, v2, v3, type));

        trianglesParDessus.add(new Triangle(v0, v1, v4, type));
        trianglesParDessus.add(new Triangle(v1, v4, v5, type));

        trianglesParDessus.add(new Triangle(v4, v5, v7, type));
        trianglesParDessus.add(new Triangle(v4, v7, v6, type));

        trianglesParDessus.add(new Triangle(v6, v7, v8, type));
        trianglesParDessus.add(new Triangle(v7, v8, v9, type));

        trianglesParDessus.add(new Triangle(v8, v9, v10, type));
        trianglesParDessus.add(new Triangle(v9, v10, v11, type));

        trianglesParDessus.add(new Triangle(v10, v11, v12, type));
        trianglesParDessus.add(new Triangle(v11, v12, v13, type));

        trianglesParDessus.add(new Triangle(v12, v13, v2, type));
        trianglesParDessus.add(new Triangle(v2, v3, v12, type));

        return trianglesParDessus;
    }

    public static List<Triangle> generateParDessusBrut(double anglee, double lengthh, double widthh, double heightt, double epaisseurr, String type){
        float angle = (float) anglee;
        float length = (float) lengthh;
        float width = (float) widthh;
        float height = (float) heightt;
        float epaisseur = (float) epaisseurr;

        List<Triangle> trianglesParDessus = new ArrayList<>();

        List<float[]> listeVertexParDessus = determinerPointsParDessusBrut(angle, length, width, epaisseur, height, "AVANT");

        float[] v0 = listeVertexParDessus.get(0);
        float[] v1 = listeVertexParDessus.get(1);
        float[] v2 = listeVertexParDessus.get(2);
        float[] v3 = listeVertexParDessus.get(3);
        float[] v4 = listeVertexParDessus.get(4);
        float[] v5 = listeVertexParDessus.get(5);
        float[] v6 = listeVertexParDessus.get(6);
        float[] v7 = listeVertexParDessus.get(7);
        float[] v8 = listeVertexParDessus.get(8);
        float[] v9 = listeVertexParDessus.get(9);

        trianglesParDessus.add(new Triangle(v0, v2, v5, type));
        trianglesParDessus.add(new Triangle(v7, v2, v5, type));

        trianglesParDessus.add(new Triangle(v0, v1, v5, type));
        trianglesParDessus.add(new Triangle(v6, v1, v5, type));

        trianglesParDessus.add(new Triangle(v1, v3, v8, type));
        trianglesParDessus.add(new Triangle(v1, v6, v8, type));

        trianglesParDessus.add(new Triangle(v3, v4, v9, type));
        trianglesParDessus.add(new Triangle(v9, v4, v8, type));

        trianglesParDessus.add(new Triangle(v2, v4, v7, type));
        trianglesParDessus.add(new Triangle(v9, v4, v7, type));

        trianglesParDessus.add(new Triangle(v2, v4, v3, type));
        trianglesParDessus.add(new Triangle(v7, v9, v8, type));

        trianglesParDessus.add(new Triangle(v2, v4, v1, type));
        trianglesParDessus.add(new Triangle(v7, v9, v6, type));

        trianglesParDessus.add(new Triangle(v2, v0, v1, type));
        trianglesParDessus.add(new Triangle(v7, v5, v6, type));

        return trianglesParDessus;
    }

    public static List<Triangle> generateRetraitParDessus(double anglee, double lengthh, double widthh, double heightt, double epaisseurr, String type){
        float angle = (float) anglee;
        float length = (float) lengthh;
        float width = (float) widthh;
        float height = (float) heightt;
        float epaisseur = (float) epaisseurr;

        List<Triangle> trianglesRetraitParDessus = new ArrayList<>();

        List<float[]> listeVertexRetraitParDessus = determinerPointsRetraitParDessus(angle, length, width, epaisseur, height, "AVANT");

        float[] v0 = listeVertexRetraitParDessus.get(0);
        float[] v1 = listeVertexRetraitParDessus.get(1);
        float[] v2 = listeVertexRetraitParDessus.get(2);
        float[] v3 = listeVertexRetraitParDessus.get(3);
        float[] v4 = listeVertexRetraitParDessus.get(4);
        float[] v5 = listeVertexRetraitParDessus.get(5);
        float[] v6 = listeVertexRetraitParDessus.get(6);
        float[] v7 = listeVertexRetraitParDessus.get(7);
        float[] v8 = listeVertexRetraitParDessus.get(8);
        float[] v9 = listeVertexRetraitParDessus.get(9);
        float[] v10 = listeVertexRetraitParDessus.get(10);
        float[] v11 = listeVertexRetraitParDessus.get(11);

        trianglesRetraitParDessus.add(new Triangle(v0, v1, v3, type));
        trianglesRetraitParDessus.add(new Triangle(v6, v7, v9, type));

        trianglesRetraitParDessus.add(new Triangle(v1, v3, v5, type));
        trianglesRetraitParDessus.add(new Triangle(v7, v9, v11, type));


        trianglesRetraitParDessus.add(new Triangle(v0, v1, v7, type));
        trianglesRetraitParDessus.add(new Triangle(v0, v6, v7, type));

        trianglesRetraitParDessus.add(new Triangle(v0, v3, v9, type));
        trianglesRetraitParDessus.add(new Triangle(v0, v6, v9, type));

        trianglesRetraitParDessus.add(new Triangle(v3, v5, v11, type));
        trianglesRetraitParDessus.add(new Triangle(v3, v9, v11, type));

        trianglesRetraitParDessus.add(new Triangle(v1, v5, v11, type));
        trianglesRetraitParDessus.add(new Triangle(v1, v7, v11, type));

        return trianglesRetraitParDessus;
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
        float hauteurV10V11 = (float) (Math.tan(angle) * (width));
        float hauteurV6V7 = (float) (float) (Math.tan(angle) * (width + (float) epaisseurChalet/2));

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

    public static List<Triangle> generatePignonFiniGauche (double anglee, double lenghtt, double widthh, double heightt, String type) {

        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;


        List<Triangle> trianglesPignon = new ArrayList<>();
        List<float[]> listeVertexPignonGauche = determinerPointsPignonFiniGauche(angle, length, width, height, width);

        //pignon 1 half depth
        float[] v0 = listeVertexPignonGauche.get(0);
        float[] v1 = listeVertexPignonGauche.get(1);
        float[] v2 = listeVertexPignonGauche.get(2);
        float[] v3 = listeVertexPignonGauche.get(3);
        float[] v4 = listeVertexPignonGauche.get(4);
        float[] v5 = listeVertexPignonGauche.get(5);

        //pignon 1 retrait
        float[] v6 = listeVertexPignonGauche.get(6);
        float[] v7 = listeVertexPignonGauche.get(7);
        float[] v8 = listeVertexPignonGauche.get(8);
        float[] v9 = listeVertexPignonGauche.get(9);
        float[] v10 = listeVertexPignonGauche.get(10);
        float[] v11 = listeVertexPignonGauche.get(11);

        trianglesPignon.add(new Triangle(v0, v1, v2, type));
        trianglesPignon.add(new Triangle(v2, v3, v1, type));

        trianglesPignon.add(new Triangle(v0, v1, v4, type));
        trianglesPignon.add(new Triangle(v1, v4, v5, type));

        trianglesPignon.add(new Triangle(v4, v5, v2, type));
        trianglesPignon.add(new Triangle(v2, v3, v5, type));

        trianglesPignon.add(new Triangle(v0, v2, v4, type));
        trianglesPignon.add(new Triangle(v1, v3, v5, type));

        trianglesPignon.add(new Triangle(v6, v10, v8, type));

        trianglesPignon.add(new Triangle(v8, v10, v9, type));
        trianglesPignon.add(new Triangle(v9, v11, v10, type));

        trianglesPignon.add(new Triangle(v11, v9, v7, type));
        trianglesPignon.add(new Triangle(v6, v7, v8, type));
        trianglesPignon.add(new Triangle(v8, v7, v9, type));

        trianglesPignon.add(new Triangle(v6, v7, v10, type));
        trianglesPignon.add(new Triangle(v10, v11, v7, type));

        return trianglesPignon;
    }

    public static List<Triangle> generatePignonFini(double anglee, double lenghtt, double widthh, double heightt, String type) {

        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;


        List<Triangle> trianglesPignon = new ArrayList<>();
        List<float[]> listeVertexPignonGauche = determinerPointsPignonFiniDroite(angle, length, width, height, width);

        //pignon 1 half depth
        float[] v0 = listeVertexPignonGauche.get(0);
        float[] v1 = listeVertexPignonGauche.get(1);
        float[] v2 = listeVertexPignonGauche.get(2);
        float[] v3 = listeVertexPignonGauche.get(3);
        float[] v4 = listeVertexPignonGauche.get(4);
        float[] v5 = listeVertexPignonGauche.get(5);

        //pignon 1 retrait
        float[] v6 = listeVertexPignonGauche.get(6);
        float[] v7 = listeVertexPignonGauche.get(7);
        float[] v8 = listeVertexPignonGauche.get(8);
        float[] v9 = listeVertexPignonGauche.get(9);
        float[] v10 = listeVertexPignonGauche.get(10);
        float[] v11 = listeVertexPignonGauche.get(11);

        trianglesPignon.add(new Triangle(v0, v1, v2, type));
        trianglesPignon.add(new Triangle(v2, v3, v1, type));

        trianglesPignon.add(new Triangle(v0, v1, v4, type));
        trianglesPignon.add(new Triangle(v1, v4, v5, type));

        trianglesPignon.add(new Triangle(v4, v5, v2, type));
        trianglesPignon.add(new Triangle(v2, v3, v5, type));

        trianglesPignon.add(new Triangle(v0, v2, v4, type));
        trianglesPignon.add(new Triangle(v1, v3, v5, type));

        trianglesPignon.add(new Triangle(v6, v10, v8, type));

        trianglesPignon.add(new Triangle(v8, v10, v9, type));
        trianglesPignon.add(new Triangle(v9, v11, v10, type));

        trianglesPignon.add(new Triangle(v11, v9, v7, type));
        trianglesPignon.add(new Triangle(v6, v7, v8, type));
        trianglesPignon.add(new Triangle(v8, v7, v9, type));

        trianglesPignon.add(new Triangle(v6, v7, v10, type));
        trianglesPignon.add(new Triangle(v10, v11, v7, type));

        return trianglesPignon;
    }

    public static List<Triangle> generateRallongeVerticaleRetrait(double anglee, double lenghtt, double widthh, double heightt, String type){
        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;

        List<Triangle> trianglesRallongeRetrait = new ArrayList<>();

        List<float[]> listeVertexRallonge = determinerPointRallongeVerticale(angle, length, height, width);

        float[] v0 = listeVertexRallonge.get(0);
        float[] v1 = listeVertexRallonge.get(1);
        float[] v2 = listeVertexRallonge.get(2);
        float[] v3 = listeVertexRallonge.get(3);
        float[] v4 = listeVertexRallonge.get(4);
        float[] v5 = listeVertexRallonge.get(5);
        float[] v6 = listeVertexRallonge.get(6);
        float[] v7 = listeVertexRallonge.get(7);
        float[] v8 = listeVertexRallonge.get(8);
        float[] v9 = listeVertexRallonge.get(9);
        float[] v10 = listeVertexRallonge.get(10);
        float[] v11 = listeVertexRallonge.get(11);
        float[] v12 = listeVertexRallonge.get(12);
        float[] v13 = listeVertexRallonge.get(13);
        float[] v14 = listeVertexRallonge.get(14);
        float[] v15 = listeVertexRallonge.get(15);
        float[] v16 = listeVertexRallonge.get(16);
        float[] v17 = listeVertexRallonge.get(17);
        float[] v18 = listeVertexRallonge.get(18);
        float[] v19 = listeVertexRallonge.get(19);
        float[] v20 = listeVertexRallonge.get(20);
        float[] v21 = listeVertexRallonge.get(21);
        float[] v22 = listeVertexRallonge.get(22);
        float[] v23 = listeVertexRallonge.get(23);
        float[] v24 = listeVertexRallonge.get(24);
        float[] v25 = listeVertexRallonge.get(25);

        trianglesRallongeRetrait.add(new Triangle(v6, v7, v20, type));
        trianglesRallongeRetrait.add(new Triangle(v6, v20, v21, type));

        trianglesRallongeRetrait.add(new Triangle(v3, v2, v20, type));
        trianglesRallongeRetrait.add(new Triangle(v2, v20, v21, type));

        trianglesRallongeRetrait.add(new Triangle(v2, v8, v3, type));
        trianglesRallongeRetrait.add(new Triangle(v2, v9, v8, type));

        trianglesRallongeRetrait.add(new Triangle(v11, v8, v10, type));
        trianglesRallongeRetrait.add(new Triangle(v8, v10, v9, type));

        trianglesRallongeRetrait.add(new Triangle(v10, v7, v11, type));
        trianglesRallongeRetrait.add(new Triangle(v10, v6, v7, type));

        trianglesRallongeRetrait.add(new Triangle(v6, v23, v10, type));
        trianglesRallongeRetrait.add(new Triangle(v7, v22, v11, type));

        trianglesRallongeRetrait.add(new Triangle(v9, v25, v2, type));
        trianglesRallongeRetrait.add(new Triangle(v8, v24, v3, type));

        trianglesRallongeRetrait.add(new Triangle(v25, v23, v9, type));
        trianglesRallongeRetrait.add(new Triangle(v25, v21, v23, type));

        trianglesRallongeRetrait.add(new Triangle(v8, v22, v20, type));
        trianglesRallongeRetrait.add(new Triangle(v8, v20, v24, type));

        return trianglesRallongeRetrait;
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

    public static List<Triangle> generateRallongeVerticaleFini(double anglee, double lenghtt, double widthh, double heightt, String type) {

        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;

        List<Triangle> trianglesRallonge = new ArrayList<>();

        List<float[]> listeVertexRallonge = determinerPointRallongeVerticale(angle, length, height, width);

        float[] v0 = listeVertexRallonge.get(0);
        float[] v1 = listeVertexRallonge.get(1);
        float[] v2 = listeVertexRallonge.get(2);
        float[] v3 = listeVertexRallonge.get(3);
        float[] v4 = listeVertexRallonge.get(4);
        float[] v5 = listeVertexRallonge.get(5);
        float[] v6 = listeVertexRallonge.get(6);
        float[] v7 = listeVertexRallonge.get(7);
        float[] v8 = listeVertexRallonge.get(8);
        float[] v9 = listeVertexRallonge.get(9);
        float[] v10 = listeVertexRallonge.get(10);
        float[] v11 = listeVertexRallonge.get(11);
        float[] v12 = listeVertexRallonge.get(12);
        float[] v13 = listeVertexRallonge.get(13);
        float[] v14 = listeVertexRallonge.get(14);
        float[] v15 = listeVertexRallonge.get(15);
        float[] v16 = listeVertexRallonge.get(16);
        float[] v17 = listeVertexRallonge.get(17);
        float[] v18 = listeVertexRallonge.get(18);
        float[] v19 = listeVertexRallonge.get(19);

        //front face
        trianglesRallonge.add(new Triangle(v0, v1, v2, type));
        trianglesRallonge.add(new Triangle(v2, v3, v0, type));

        //back face
        trianglesRallonge.add(new Triangle(v4, v7, v5, type));
        trianglesRallonge.add(new Triangle(v5, v6, v7, type));

        trianglesRallonge.add(new Triangle(v0, v17, v3, type));
        trianglesRallonge.add(new Triangle(v0, v4, v17, type));

        trianglesRallonge.add(new Triangle(v1, v5, v18, type));
        trianglesRallonge.add(new Triangle(v18, v2, v1, type));

        trianglesRallonge.add(new Triangle(v0, v1, v5, type));
        trianglesRallonge.add(new Triangle(v0, v4, v5, type));

        trianglesRallonge.add(new Triangle(v8, v12, v14, type));
        trianglesRallonge.add(new Triangle(v14, v11, v8, type));

        trianglesRallonge.add(new Triangle(v15, v8, v3, type)); //good
        trianglesRallonge.add(new Triangle(v17, v15, v8, type)); //
        trianglesRallonge.add(new Triangle(v17, v12, v8, type));

        //idk up here

        trianglesRallonge.add(new Triangle(v2, v16, v9, type));
        trianglesRallonge.add(new Triangle(v16, v19, v18, type));
        trianglesRallonge.add(new Triangle(v9, v16, v19, type));
        //up here too

        trianglesRallonge.add(new Triangle(v8, v12, v11, type));
        trianglesRallonge.add(new Triangle(v12, v11, v14, type));

        trianglesRallonge.add(new Triangle(v9, v19, v13, type));//
        trianglesRallonge.add(new Triangle(v9, v10, v13, type));

        trianglesRallonge.add(new Triangle(v14, v11, v7, type));
        trianglesRallonge.add(new Triangle(v6, v13, v10, type));

        trianglesRallonge.add(new Triangle(v8, v11, v10, type));
        trianglesRallonge.add(new Triangle(v8, v9, v10, type));

        trianglesRallonge.add(new Triangle(v7, v6, v11, type));
        trianglesRallonge.add(new Triangle(v11, v10, v6, type));

        trianglesRallonge.add(new Triangle(v8, v9, v3, type));
        trianglesRallonge.add(new Triangle(v3, v2, v9, type));

        return trianglesRallonge;
    }

    public static List<Triangle> generatePignonRetraitGauche(double anglee, double lenghtt, double widthh, double heightt, String type){

        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;

        List<Triangle> trianglesPignon = new ArrayList<>();
        List<float[]> listeVertexPignonGauche = determinerPignonRetraitGauche(angle, length, width, height, "AVANT");

        float[] v0 = listeVertexPignonGauche.get(0);
        float[] v1 = listeVertexPignonGauche.get(1);
        float[] v2 = listeVertexPignonGauche.get(2);
        float[] v3 = listeVertexPignonGauche.get(3);
        float[] v4 = listeVertexPignonGauche.get(4);
        float[] v5 = listeVertexPignonGauche.get(5);
        float[] v6 = listeVertexPignonGauche.get(6);
        float[] v7 = listeVertexPignonGauche.get(7);
        float[] v8 = listeVertexPignonGauche.get(8);
        float[] v9 = listeVertexPignonGauche.get(9);
        float[] v10 = listeVertexPignonGauche.get(10);
        float[] v11 = listeVertexPignonGauche.get(11);


        trianglesPignon.add(new Triangle(v0, v1, v2, type));
        trianglesPignon.add(new Triangle(v3, v4, v5, type));
        trianglesPignon.add(new Triangle(v6, v7, v8, type));
        return null;
    }

    public static List<float[]> determinerPignonRetraitGauche(float angle, float length, float width, float height, String type){

        List<float[]> listeVertex = new LinkedList<>();

        float hauteurBig = (float) (Math.tan(angle) * (width - 2*(float)epaisseurChalet));
        float hauteurSmall = (float) (Math.tan(angle) * (width - 2*(float)epaisseurChalet));

        //big triangle
        listeVertex.add(new float[]{0, 0, 0}); // v0
        listeVertex.add(new float[]{0, 0, (width - 2*(float)epaisseurChalet)}); // v1
        listeVertex.add(new float[]{0, hauteurBig, (width - 2*(float)epaisseurChalet)}); // v2

        listeVertex.add(new float[]{(float) epaisseurChalet/2, 0, 0}); // v6
        listeVertex.add(new float[]{(float) epaisseurChalet/2, 0, (width - 2*(float)epaisseurChalet)}); // v9
        listeVertex.add(new float[]{(float) epaisseurChalet/2, hauteurBig, (width - 2*(float)epaisseurChalet)}); // v11

        //small triangle
        listeVertex.add(new float[]{0, 0, (float) epaisseurChalet/2}); // v1
        listeVertex.add(new float[]{0, hauteurBig, (width - 2*(float)epaisseurChalet) + (float) epaisseurChalet/2}); // v2 //revoir + or -
        listeVertex.add(new float[]{0, hauteurSmall, (width - 2*(float)epaisseurChalet) + (float) epaisseurChalet/2}); // v4 //revoir + or -

        listeVertex.add(new float[]{(float) epaisseurChalet/2, 0, (float) epaisseurChalet/2}); // v7
        listeVertex.add(new float[]{(float) epaisseurChalet/2, hauteurBig, (width - 2*(float)epaisseurChalet) + (float) epaisseurChalet/2}); // v8 //revoir + or -
        listeVertex.add(new float[]{(float) epaisseurChalet/2, hauteurSmall, (width - 2*(float)epaisseurChalet) + (float) epaisseurChalet/2}); // v10 //revoir + or -


        return listeVertex;

    }

    public static void ExporterPignonRetrait(String fileName){

        java.util.List<Triangle> listeTriangles = generatePignonRetraitGauche(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
        generateSTL(listeTriangles,fileName);
    }

    public static void ExporterRallongeVerticaleBrut(String fileName){

            java.util.List<Triangle> listeTriangles = generateRallongeVerticaleBrut(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
            generateSTL(listeTriangles,fileName);
    }

    public static void ExporterRallongeVerticaleRetrait(String fileName){

            java.util.List<Triangle> listeTriangles = generateRallongeVerticaleRetrait(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
            generateSTL(listeTriangles,fileName);
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
    
    public static void ExporterRallongeVerticaleFini(String fileName){

        java.util.List<Triangle> listeTriangles = generateRallongeVerticaleFini(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
        generateSTL(listeTriangles,fileName);
    }

    public static void ExporterParDessusFini(String fileName){

            java.util.List<Triangle> listeTriangles = generateParDessus(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,Chalet.epaisseurChalet, "ARRIERE");
            generateSTL(listeTriangles,fileName);
    }

    public static void ExporterRetraitParDessus(String fileName){

        java.util.List<Triangle> listeTriangles = generateRetraitParDessus(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,Chalet.epaisseurChalet, "ARRIERE");
        generateSTL(listeTriangles,fileName);
    }
    public static void ExporterParDessusBrut(String fileName){

        java.util.List<Triangle> listeTriangles = generateParDessusBrut(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,Chalet.epaisseurChalet, "ARRIERE");
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

    public static void ExporterPignonFiniDroite(String fileName) {
        
        List<Triangle> listeTrianglesPignon = generatePignonFini(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs, "ARRIERE");
        
        generateSTL(listeTrianglesPignon, fileName);
    }

    public static void ExporterPignonFiniGauche(String fileName) {

        List<Triangle> listeTrianglesPignon = generatePignonFiniGauche(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs, "ARRIERE");

        generateSTL(listeTrianglesPignon, fileName);
    }



}