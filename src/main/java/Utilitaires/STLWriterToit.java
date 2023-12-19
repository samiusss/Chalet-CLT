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

    /*public static List<float[]> determinerPointsPignon(float length, float width, float height, float space) {
        List<float[]> listeVertex = new LinkedList<>();

        int numCellsLength = 10;
        int numCellsWidth = 1;
        int numCellsHeight = 10;

        float cellLength = length / numCellsLength;
        float cellWidth = width / numCellsWidth;
        float cellHeight = height / numCellsHeight;

        listeVertex.add(new float[]{0, 0, 0});
        listeVertex.add(new float[]{length, 0, 0});
        listeVertex.add(new float[]{0, height, 0});

        listeVertex.add(new float[]{0, 0, width});
        listeVertex.add(new float[]{length, 0, width});
        listeVertex.add(new float[]{0, height, width});
        //////
        listeVertex.add(new float[]{0, 0, 0 + space});
        listeVertex.add(new float[]{length, 0, 0 + space});
        listeVertex.add(new float[]{0, height, 0 + space});

        listeVertex.add(new float[]{0, 0, width + space});
        listeVertex.add(new float[]{length, 0, width + space});
        listeVertex.add(new float[]{0, height, width + space});

        return listeVertex;
    }*/

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

    public static List<float[]> determinerPointsPignonRetrait(float angle, float length, float width, float height, float space) {
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

        ///////
        /*listeVertex.add(new float[]{0, bigHeight, 0});

        listeVertex.add(new float[]{0, 0, length / 2});
        listeVertex.add(new float[]{length, 0, length / 2});
        listeVertex.add(new float[]{0, bigHeight, length / 2});


        listeVertex.add(new float[]{0 + (float) epaisseurChalet / 2, 0, length / 2});
        listeVertex.add(new float[]{width - (float)epaisseurChalet / 2, 0, length / 2});
        listeVertex.add(new float[]{0 + (float) epaisseurChalet/ 2, smallHeight, length / 2});

        listeVertex.add(new float[]{0 +  (float) epaisseurChalet/ 2, 0, width});
        listeVertex.add(new float[]{width - (float) epaisseurChalet / 2, 0, width});
        listeVertex.add(new float[]{0 + (float) epaisseurChalet/ 2, smallHeight, width});*/
        /////////////////////////////////////////////////////////////
        /*listeVertex.add(new float[]{0, 0, width + length});
        listeVertex.add(new float[]{length, 0, width + length});
        listeVertex.add(new float[]{0, bigHeight, width + length});

        listeVertex.add(new float[]{0, 0, width + length + (float) epaisseurChalet/2});
        listeVertex.add(new float[]{length, 0, width + length + (float) epaisseurChalet/2});
        listeVertex.add(new float[]{0, bigHeight, width + length + (float) epaisseurChalet/2});

        listeVertex.add(new float[]{0 + width / 2, 0, width + length});
        listeVertex.add(new float[]{length - width / 2, 0, width + length});
        listeVertex.add(new float[]{0 + width / 2, smallHeight, width + length});

        listeVertex.add(new float[]{0 + width / 2, 0, length});
        listeVertex.add(new float[]{length - width / 2, 0, length});
        listeVertex.add(new float[]{0 + width / 2, smallHeight, length});*/


        return listeVertex;
    }

    public static List<float[]> determinerPointsPignonRetraitGauche(float angle, float length, float width, float height, float space) {
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

        /*listeVertex.add(new float[]{0, 0, 0}); //v0
        listeVertex.add(new float[]{0, 0, width}); //v1
        listeVertex.add(new float[]{0, epaisseur, width}); //v2
        listeVertex.add(new float[]{0, epaisseur, 0}); //v3

        listeVertex.add(new float[]{epaisseur, 0, 0}); //v4
        listeVertex.add(new float[]{epaisseur, 0, width}); //v5

        listeVertex.add(new float[]{((float) longueurChalet - epaisseur /2),  hauteurP, 0}); //v6
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur /2),  hauteurP, width}); //v7

        listeVertex.add(new float[]{((float) longueurChalet - epaisseur /2), (hauteurP + epaisseur/2), 0}); //v8
        listeVertex.add(new float[]{((float) longueurChalet - epaisseur /2), (hauteurP + epaisseur/2), width}); //v9

        listeVertex.add(new float[]{length, hauteurR + epaisseur / 2, 0}); //v10
        listeVertex.add(new float[]{length, hauteurR + epaisseur / 2, width}); //v11

        listeVertex.add(new float[]{length, hauteurR + epaisseur, 0}); //v12
        listeVertex.add(new float[]{length, hauteurR + epaisseur, width}); //v13*/

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

    public static List<float[]> determinerPointRallongeVerticale(double angle, double length, double height, double width) {
        List<float[]> listeVertex = new LinkedList<>();

        if (orientationToit == "North" || orientationToit == "South") {
            // Swap length and width
            double temp = length;
            length = width;
            width = temp;
        }

        float firstBaseWidth = (float) ((float) Math.tan(angle) * (epaisseurChalet / 2));
        listeVertex.add(new float[]{0, 0, 0});//v0
        listeVertex.add(new float[]{(float) length, 0, 0}); //v1

        listeVertex.add(new float[]{(float) length, (float) (Math.tan(angle) * width + epaisseurChalet), 0});//v2
        listeVertex.add(new float[]{0, (float) (Math.tan(angle) * width + epaisseurChalet), 0}); //v3

        listeVertex.add(new float[]{0, 0, (float) epaisseurChalet}); //v4
        listeVertex.add(new float[]{(float) length, 0, (float) epaisseurChalet}); //v5

        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width), (float) epaisseurChalet}); //v6
        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width), (float) epaisseurChalet}); //v7

        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2), (float) epaisseurChalet / 2}); //v8
        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2), (float) epaisseurChalet / 2});//v9

        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2) - (float)epaisseurChalet/2, (float) epaisseurChalet / 2});//v10
        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2) - (float)epaisseurChalet/2, (float) epaisseurChalet / 2});//v11

        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2), (float) epaisseurChalet});//v12
        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2) + (float)epaisseurChalet/2, (float) epaisseurChalet});//v13

        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2) + (float)epaisseurChalet/2, (float) epaisseurChalet});//v14
        listeVertex.add(new float[]{0, (float) (Math.tan(angle) * width + epaisseurChalet), (float) epaisseurChalet / 2}); //v15

        listeVertex.add(new float[]{(float) length, (float) (Math.tan(angle) * width + epaisseurChalet), (float) epaisseurChalet / 2}); //v16
        listeVertex.add(new float[]{0, (float) (Math.tan(angle) * width + epaisseurChalet), (float) epaisseurChalet});//v17

        listeVertex.add(new float[]{(float) length, (float) (Math.tan(angle) * width + epaisseurChalet), (float) epaisseurChalet});//v18
        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2), (float) epaisseurChalet});//v19

        //points pour brut
        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width), 0}); //v20
        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width), 0}); //v21

        //et retrait
        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width), (float)epaisseurChalet/2}); //v22
        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width), (float)epaisseurChalet/2}); //v23
        listeVertex.add(new float[]{0, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2), 0}); //v24
        listeVertex.add(new float[]{(float) length, (float) ((float) Math.tan(angle) * width + epaisseurChalet/2), 0}); //v25

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
        List<float[]> listeVertexPignonGauche = determinerPointsPignonRetraitGauche(angle, length, width, height, width);

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
        List<float[]> listeVertexPignonGauche = determinerPointsPignonRetrait(angle, length, width, height, width);

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

    public static List<float[]> determinerPignonRetraitGauche(float angle, float length, float width, float height, String type){

        List<float[]> listeVertex = new LinkedList<>();


        listeVertex.add(new float[]{0, 0, width - 2*(float)epaisseurChalet/2}); //v0
        listeVertex.add(new float[]{0, 0, 0}); //v1
        listeVertex.add(new float[]{(float) epaisseurChalet / 2, 0, 0}); //v2

        listeVertex.add(new float[]{0, 0, width - 2*(float)epaisseurChalet}); //v3
        return null;

    }

    public static List<Triangle> generatePignonRetraitGauche(double anglee, double lenghtt, double widthh, double heightt, String type){


        float angle = (float) anglee;
        float length = (float) lenghtt;
        float width = (float) widthh;
        float height = (float) heightt;

        return null;
    }

    public static List<Triangle> findUnsharedTriangles(List<Triangle> list1, List<Triangle> list2) {
        List<Triangle> result = new ArrayList<>(list1);
        result.removeAll(list2);  // Remove common elements from list1

        List<Triangle> commonTriangles = new ArrayList<>(list1);
        commonTriangles.retainAll(list2);  // Keep only common elements

        result.addAll(list2);
        result.removeAll(commonTriangles);  // Remove common elements from list2

        return result;
    }

    public static void ExporterPignonRetrait(String fileName){

        java.util.List<Triangle> listeTriangles = generatePignonRetraitGauche(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
        generateSTL(listeTriangles,fileName);
    }

    public static void ExporterRallongeVerticaleBrut(String fileName){

            java.util.List<Triangle> listeTriangles = generateRallongeVerticaleBrut(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
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

    public static List<Triangle> generatePignonPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, String type) {
        java.util.List<Triangle> triangles = new ArrayList<>();
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        int numCellsLength = 10;
        int numCellsWidth = 1;
        int numCellsHeight = 10;

        for (int i = 0; i < numCellsLength - 9; i++) {
            for (int j = 0; j < numCellsWidth; j++) {
                for (int k = 0; k < numCellsHeight - 9; k++) {
                    int index = i * (numCellsWidth + 1) * (numCellsHeight + 1) + j * (numCellsHeight + 1) + k;

                    float[] v0 = listeVertex.get(index);
                    float[] v1 = listeVertex.get(index + 1);
                    float[] v2 = listeVertex.get(index + (numCellsHeight + 1));
                    float[] v3 = listeVertex.get(index + (numCellsHeight + 1) + 1);
                    float[] v4 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1));
                    float[] v5 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + 1);
                    float[] v6 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1));
                    float[] v7 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1) + 1);


                    //front face
                    triangles.add(new Triangle(v0, v1, v2, type));
                    triangles.add(new Triangle(v1, v3, v2, type));
                    // top face
                    triangles.add(new Triangle(v2, v3, v6, type));
                    // back face
                    triangles.add(new Triangle(v0, v4, v1, type));
                    // Left face
                    triangles.add(new Triangle(v0, v2, v4, type));
                    triangles.add(new Triangle(v2, v6, v4, type));
                    //right face
                    triangles.add(new Triangle(v6, v3, v1, type));
                    triangles.add(new Triangle(v1, v4, v6, type));

        /*for (int rect = numCellsLength - 1; rect >= 0; rect--){
            for(int hau = 0; hau < numCellsHeight - 1; hau ++) {
                for (int kr = 0; kr < numCellsWidth; kr++) {
                    int index = rect * (numCellsWidth + 1) * (numCellsHeight + 1) + kr * (numCellsHeight + 1) + hau;
                    float[] v0 = listeVertex.get(index);
                    float[] v1 = listeVertex.get(index + 1);
                    float[] v2 = listeVertex.get(index + (numCellsHeight + 1));
                    float[] v3 = listeVertex.get(index + (numCellsHeight + 1) + 1);
                    float[] v4 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1));
                    float[] v6 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1));

                    //front face
                    triangles.add(new Triangle(v0, v1, v2, type));
                    triangles.add(new Triangle(v1, v3, v2, type));
                    // top face
                    triangles.add(new Triangle(v2, v3, v6, type));
                    // back face
                    triangles.add(new Triangle(v0, v4, v1, type));
                    // Left face
                    triangles.add(new Triangle(v0, v2, v4, type));
                    triangles.add(new Triangle(v2, v6, v4, type));
                    //right face
                    triangles.add(new Triangle(v1, v6, v4, type));
                    triangles.add(new Triangle(v1, v4, v6, type));
                }
            }
        }*/
                }
            }
        }
        return triangles;
    }

    /*public static java.util.List<Triangle> generateRectangularPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, String type) {
        java.util.List<Triangle> triangles = new ArrayList<>();
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

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
                }
            }
        }

        return triangles;
    }*/

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


        /*for (int rect = numCellsLength - 1; rect >= 0; rect--){
            for(int hau = 0; hau < numCellsHeight - 1; hau ++) {
                for (int kr = 0; kr < numCellsWidth; kr++) {
                    int index = rect * (numCellsWidth + 1) * (numCellsHeight + 1) + kr * (numCellsHeight + 1) + hau;
                    float[] v0 = listeVertex.get(index);
                    float[] v1 = listeVertex.get(index + 1);
                    float[] v2 = listeVertex.get(index + (numCellsHeight + 1));
                    float[] v3 = listeVertex.get(index + (numCellsHeight + 1) + 1);
                    float[] v4 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1));
                    float[] v6 = listeVertex.get(index + (numCellsWidth + 1) * (numCellsHeight + 1) + (numCellsHeight + 1));

                    //front face
                    triangles.add(new Triangle(v0, v1, v2, type));
                    triangles.add(new Triangle(v1, v3, v2, type));
                    // top face
                    triangles.add(new Triangle(v2, v3, v6, type));
                    // back face
                    triangles.add(new Triangle(v0, v4, v1, type));
                    // Left face
                    triangles.add(new Triangle(v0, v2, v4, type));
                    triangles.add(new Triangle(v2, v6, v4, type));
                    //right face
                    triangles.add(new Triangle(v1, v6, v4, type));
                    triangles.add(new Triangle(v1, v4, v6, type));
                }
            }
        }*/
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

    /*public static float[] calculerNormaleAvecTransformations(float[] pointA, float[] pointB, float[] pointC, String Type) {
*//*
        float[] translation = {0.0f, 0.0f, 10.0f};
        float rotationAngle = 0.0f ;
        float[] scale = {1.0f, 1.0f, 1.0f};

        if(Type == "ARRIERE") {
            translation = new float[]{0.0f, 0.0f, -10.0f};
            rotationAngle = 0.0f ;
            scale = new float[]{1.0f, 1.0f, 1.0f};
        }
        if(Type == "DROITE") {
            translation = new float[]{-10.0f, 0.0f, 0.0f};
            rotationAngle =  90.0f ;
            scale = new float[]{1.0f, 1.0f, 1.0f};
        }
        if(Type == "GAUCHE") {
            translation = new float[]{10.0f, 0.0f, 0.0f};
            rotationAngle = 90.0f ;
            scale = new float[]{1.0f, 1.0f, 1.0f};
        }

        // Appliquer la translation, rotation et mise à l'échelle aux points A, B et C
        appliquerTranslation(pointA, translation);
        appliquerTranslation(pointB, translation);
        appliquerTranslation(pointC, translation);

        appliquerRotation(pointA, rotationAngle);
        appliquerRotation(pointB, rotationAngle);
        appliquerRotation(pointC, rotationAngle);

        appliquerMiseAEchelle(pointA, scale);
        appliquerMiseAEchelle(pointB, scale);
        appliquerMiseAEchelle(pointC, scale); *//*

        // Calculez les vecteurs AB et AC après les transformations
        float[] vecteurAB = {pointB[0] - pointA[0], pointB[1] - pointA[1], pointB[2] - pointA[2]};
        float[] vecteurAC = {pointC[0] - pointA[0], pointC[1] - pointA[1], pointC[2] - pointA[2]};

        // Calculez le produit vectoriel des vecteurs AB et AC pour obtenir la normale
        float[] normal = {
                vecteurAB[1] * vecteurAC[2] - vecteurAB[2] * vecteurAC[1],
                vecteurAB[2] * vecteurAC[0] - vecteurAB[0] * vecteurAC[2],
                vecteurAB[0] * vecteurAC[1] - vecteurAB[1] * vecteurAC[0]
        };

        // Normalisez le vecteur résultant (mettez-le à l'échelle pour qu'il ait une longueur de 1)
        float longueur = (float) Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);
        normal[0] /= longueur;
        normal[1] /= longueur;
        normal[2] /= longueur;

        return normal;
    }*/

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

    public static java.util.List<Triangle> generateRectangularPrismPrism2(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche, String type) {
        java.util.List<Triangle> triangles = new ArrayList<>();
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

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
    
    public static void ExporterRallongeVerticaleFini(String fileName){

        java.util.List<Triangle> listeTriangles = generateRallongeVerticaleFini(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,"AVANT");
        generateSTL(listeTriangles,fileName);
    }

    public static void ExporterParDessusFini(String fileName){

            java.util.List<Triangle> listeTriangles = generateParDessus(Chalet.angleToit, Chalet.longueurChalet, Chalet.largeurChalet, Chalet.hauteurMurs,Chalet.epaisseurChalet, "ARRIERE");
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

    private static List<Triangle> generateTriangle(float[] v0, float[] v1, float[] v2, String type) {
        List<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(v0, v1, v2, type));
        return triangles;
    }

    public static void ExporterPanneauxBrut(String fileName) {
        Point point = new Point(0,0);
        //determinerSommetsAccessoires fait le meme traitement dont on a besoin d'ou son utlisation ici.
        java.util.List<Point> listPointPanneaux = determinerSommetsAccessoires(point,100, 100);

        Point SupGauche = listPointPanneaux.get(0);
        Point SupDroit = listPointPanneaux.get(1);
        Point InfGauche = listPointPanneaux.get(2);
        Point InfDroit = listPointPanneaux.get(3);

        double epaisseurChalet = Chalet.epaisseurChalet;
        //double epaisseur = 15.0;

        java.util.List<Triangle> listeTriangles = generateRectangularPrism(500,(float) epaisseurChalet, 400,0,0,0,"AVANT");
        generateSTL(listeTriangles,fileName);
    }

    public static java.util.List<Triangle> ExporterPanneauxFinisDroite(float[] SupGauche, float length, float width, float height){
        /* MUR DROITE */
        // Le point SupGauche du mur droite correspond au point superieur gauche de la base arriere du mur de facade v3

        //Dimension du prisme de base
        double epaisseurChalet = Chalet.epaisseurChalet;
        width = (float) epaisseurChalet;


        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Générer le prisme principal
        java.util.List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal,"DROITE");

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"DROITE");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"DROITE");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTriangles.addAll(listeTrianglesGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        //Générer l'espace de la porte
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(3).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(3).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal, "DROITE");


        return listeTriangles;


    }

    public static java.util.List<Triangle> ExporterPanneauxRetraitDroite(float[] SupGauche, float length, float width, float height){
        /* MUR DROITE */
        // Le point SupGauche du mur droite correspond au point superieur gauche de la base arriere du mur de facade v3. vision salle.

        //Dimension du prisme de base
        double epaisseurChalet = Chalet.epaisseurChalet;
        width = (float) epaisseurChalet;


        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;
        java.util.List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"DROITE");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"DROITE");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(3).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(3).porteMur ;

        java.util.List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal,"DROITE");
        listeTrianglesSecondaire.addAll(listeTrianglesPortes);


        return listeTrianglesSecondaire;

    }

    public static java.util.List<Triangle> ExporterPanneauxFinisGauche(float[] SupGauche, float length, float width, float height){
        /* MUR GAUUCHE */
        // Le point SupGauche du mur droite correspond au point superieur droite de la base arriere du mur de facade v2, pour la vision chalet

        //Dimension du prisme de base
        double epaisseurChalet = Chalet.epaisseurChalet;
        width = (float) epaisseurChalet;


        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Générer le prisme principal
        java.util.List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal,"GAUCHE");

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"GAUCHE");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"GAUCHE");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTriangles.addAll(listeTrianglesGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        //Générer l'espace de la porte
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(2).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(2).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal,"GAUCHE");


        return listeTriangles;

    }

    public static java.util.List<Triangle> ExporterPanneauxRetraitGauche(float[] SupGauche, float length, float width, float height){
        /* MUR GAUUCHE */
        // Le point SupGauche du mur droite correspond au point superieur droite de la base arriere du mur de facade v2. vision salle.

        //Dimension du prisme de base
        double epaisseurChalet = Chalet.epaisseurChalet;
        width = (float) epaisseurChalet;


        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);
        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;
        java.util.List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"GAUCHE");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"GAUCHE");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(2).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(2).porteMur ;

        java.util.List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal,"GAUCHE");
        listeTrianglesSecondaire.addAll(listeTrianglesPortes);


        return listeTrianglesSecondaire;

    }

    public static java.util.List<Triangle> ExporterPanneauxFinisArriere(float[] SupGauche, float length, float width, float height) {
        //Dimension du prisme de base
        double epaisseurChalet = Chalet.epaisseurChalet;
        width = (float) epaisseurChalet;


        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Générer le prisme principal
        java.util.List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal,"ARRIERE");

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"ARRIERE");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"ARRIERE");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTriangles.addAll(listeTrianglesGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        // Coordonnées et dimensions de la porte
        float lengthPorte =  50; // Remplacez par la valeur spécifique
        float heightPorte =  70 ; // Remplacez par la valeur spécifique
        float xSupGauchePorte = 20f ; // Remplacez par la valeur spécifique
        float ySupGauchePorte = 0f; // Remplacez par la valeur spécifique
        float zSupGauchePorte = 0f; // Remplacez par la valeur spécifique


        // Ajuster les coordonnées de l'accessoire en fonction du mur
        float xSupGaucheAccessoire = xSupGauche ;//+ xSupGauchePorte;
        float ySupGaucheAccessoire = -20; //+ ySupGauchePorte;
        float zSupGaucheAccessoire = 0f ;//+ zSupGauchePorte;


        //Générer l'espace de la porte
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(1).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(1).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal, "ARRIERE");


        return listeTriangles;
    }

    public static java.util.List<Triangle> ExporterPanneauxRetraitArriere(float[] SupGauche, float length, float width, float height) {
        //Dimension du prisme de base
        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = width;

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;
        java.util.List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"ARRIERE");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"ARRIERE");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(1).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(1).porteMur ;

        java.util.List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal,"ARRIERE");
        listeTrianglesSecondaire.addAll(listeTrianglesPortes);


        return listeTrianglesSecondaire;
    }

    public static java.util.List<Triangle> ExporterPanneauxFinisAvant(float[] SupGauche, float length, float width, float height, float [] SupDroitePrincipal) {

        /*
        //Dimension du prisme de base
        float[] supGauche = {0,0,0};

        // Coordonnées du coin inferieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];
        */

        /* // Dimensions du prisme de base
        float[] supGauche = {0,0,0};
        length = 300;
        height = 160;
        // Épaisseur du prisme emboîté (moitié de l'épaisseur du prisme principal)
        width = 30; // thickness

        // Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = 0f;
        float ySupGauche = 0f;
        float zSupGauche = 0f;
*/
        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];


        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        float xSupDroitePrincipal = SupGauche[0];
        float ySupDroitePrincipal = SupGauche[1];
        float zSupDroitePrincipal = SupGauche[2];

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = width;

        // Générer le prisme principal
        java.util.List<Triangle> listeTriangles = generateRectangularPrismeSupDroite(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal,xSupDroitePrincipal, ySupDroitePrincipal, zSupDroitePrincipal, "AVANT");

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"AVANT");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"AVANT");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTriangles.addAll(listeTrianglesGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        //Générer l'espace de la porte
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(0).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(0).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal, "AVANT");


        int i = 0;
        for(Triangle triangles : listeTriangles){
            System.out.println(triangles);
            i++;
        }
        System.out.println(i);


        return listeTriangles;
    }

    public static java.util.List<Triangle> ExporterPanneauxRetraitAvant(float[] SupGauche, float length, float width, float height) {

        //Dimension du prisme de base

        // Mur (Arriere/Avant => longueur = longueur Chalet) (Droite/Gauche => longueur = largeur Chalet)
        //float[] supGauche = {0,0,0};


        // Coordonnées du coin inferieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];


        // Déterminer les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = width;

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        java.util.List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite (Vue d'avant sur la visionneuse , le point 0,0 est en bas a gauche -> +  )
        java.util.List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal,"AVANT");
        java.util.List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal,"AVANT");

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        java.util.List<Fenetre> listeFenetre = chalet.getListeMurs().get(0).fenetreMur ;
        java.util.List<Porte> listePorte =  chalet.getListeMurs().get(0).porteMur ;

        java.util.List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal,"AVANT");
        listeTrianglesSecondaire.addAll(listeTrianglesPortes);


        return listeTrianglesSecondaire;

    }

    public static void ExporterPanneauxFinis(String fileNameAvant, String fileNameDroite, String fileNameChalet, String fileNameGauches, String fileNameArriere,String fileNameRetraitAvant,String fileNameRetraitArriere, String fileNameRetraitDroite,String fileNameRetraitGauches) throws IOException {

        //Dimension du prisme de base
        /*float[] supGauche = {Chalet.OffsetX,Chalet.OffsetY,0};
        double epaisseurChalet = Chalet.epaisseurChalet;
        double lengthChalet = Chalet.longueurChalet;
        double heightChalet = Chalet.longueurChalet; */


        // Dimensions du prisme de base
        // Mur (Arriere/Avant => longueur = longueur Chalet) (Droite/Gauche => longueur = largeur Chalet)
        float[] supGauche = {0,0,0};
        double epaisseurChalet = Chalet.epaisseurChalet;
        double lengthChalet = Chalet.longueurChalet;
        double widthChalet = Chalet.largeurChalet;

        double heightChalet = Chalet.longueurChalet;

        float lengthFacadeArriere = (float) lengthChalet;
        float lengthGaucheDroite = (float) widthChalet;
        float width = (float) epaisseurChalet; // thickness
        float height = (float) heightChalet;


        // Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = 0f;
        float ySupGauche = 0f;
        float zSupGauche = 0f;

        // Determiner les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismeSupDroite(lengthGaucheDroite,width,height,xSupGauche,ySupGauche,zSupGauche,xSupGauche + lengthFacadeArriere,ySupGauche, zSupGauche);

        // Determiner les points de prismes qui seront d'une longueur determine et
        // d'épaisseur correspondant a la moitie de l'épaisseur du prisme de base afin de construire les rainures gauches et droites
        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);

        // Mur Gauche
        float[] supGaucheMurGauche = {supGauche[0], supGauche[1] - lengthGaucheDroite, supGauche[2]};
        float[] supGaucheMurGaucheAvant = {supGauche[0] + lengthGaucheDroite, supGauche[1] , supGauche[2]};


        // Mur Droite
        float[] supGaucheMurDroites = {supGauche[0], supGauche[1] + lengthGaucheDroite, supGauche[2]};


        // Mur Avant
        float[] supGaucheMurAvant = {supGauche[0] + lengthFacadeArriere, supGauche[1], supGauche[2]};

        java.util.List<Triangle> trianglesPrisme = generateRectangularPrismPrism(lengthFacadeArriere, 80, height, supGauche[0],supGauche[1],supGauche[2], "AVANT");

        // MUR AVANT
        java.util.List<Triangle> trianglesAvant = ExporterPanneauxFinisAvant(supGaucheMurGauche, lengthFacadeArriere, width, height, supGaucheMurGaucheAvant);
        java.util.List<Triangle> trianglesRetraitAvant = ExporterPanneauxRetraitAvant(supGaucheMurAvant, lengthFacadeArriere, width, height);

        // MUR DROITE
        java.util.List<Triangle> trianglesDroites = ExporterPanneauxFinisDroite(supGaucheMurDroites, lengthGaucheDroite, width, height);
        java.util.List<Triangle> trianglesRetraitDroites = ExporterPanneauxRetraitDroite(supGaucheMurDroites, lengthGaucheDroite, width, height);

        // MUR GAUCHE
        java.util.List<Triangle> trianglesGauche = ExporterPanneauxFinisGauche(supGaucheMurGauche, lengthGaucheDroite, width, height);
        java.util.List<Triangle> trianglesRetraitGauche = ExporterPanneauxRetraitGauche(supGaucheMurGauche, lengthGaucheDroite, width, height);

        // MUR ARRIERE
        java.util.List<Triangle> trianglesArriere = ExporterPanneauxFinisArriere(supGauche, lengthFacadeArriere, width, height);
        java.util.List<Triangle> trianglesRetraitArriere = ExporterPanneauxRetraitArriere(supGauche, lengthFacadeArriere, width, height);

        //CHALET
        java.util.List<Triangle> triangleChalet = new LinkedList<>();
        triangleChalet.addAll(trianglesAvant);
        triangleChalet.addAll(trianglesDroites);
        triangleChalet.addAll(trianglesGauche);
        triangleChalet.addAll(trianglesArriere);

/*
        //Chalet
        generateSTL(triangleChalet,fileNameChalet);


        //Retrait
        generateSTL(trianglesRetraitAvant,fileNameRetraitAvant);
        generateSTL(trianglesRetraitDroites,fileNameRetraitDroite);
        generateSTL(trianglesRetraitGauche,fileNameRetraitGauches);
        generateSTL(trianglesRetraitArriere,fileNameRetraitArriere);
*/

        //Finis
        generateSTL(trianglesAvant,fileNameAvant);
        generateSTL(trianglesDroites,fileNameDroite);
        generateSTL(trianglesGauche,fileNameGauches);
        generateSTL(trianglesArriere,fileNameArriere);
        generateSTL(trianglesPrisme,fileNameArriere);





    }


    public static void ExporterPanneauxRetrait(String fileNameAvant, String fileNameDroite, String fileNameChalet, String fileNameGauches, String fileNameArriere,String fileNameRetraitAvant,String fileNameRetraitArriere, String fileNameRetraitDroite,String fileNameRetraitGauches) throws IOException {

        // Définir les dimensions du chalet
        float chaletLength = (float) Chalet.longueurChalet;
        float chaletWidth = (float) Chalet.largeurChalet;
        float chaletHeight = (float) Chalet.hauteurMurs;

        // Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = 0f;
        float ySupGauche = 0f;
        float zSupGauche = 0f;


        // Determiner les points du prisme de base
        java.util.List<float[]> listeVertex = determinerPointsPrismes(chaletLength, chaletWidth, chaletHeight, xSupGauche, ySupGauche, zSupGauche);


        // Determiner les points de prismes qui seront d'une longueur determine et
        // d'épaisseur correspondant a la moitie de l'épaisseur du prisme de base afin de construire les rainures gauches et droites
        float[] v0 = listeVertex.get(0);
        float[] v1 = listeVertex.get(1);
        float[] v2 = listeVertex.get(2);
        float[] v3 = listeVertex.get(3);
        float[] v4 = listeVertex.get(4);
        float[] v5 = listeVertex.get(5);
        float[] v6 = listeVertex.get(6);
        float[] v7 = listeVertex.get(7);

        // Définir les coordonnées du coin supérieur gauche initial
        float[] supGauche = {0, 0, 0};

        // Calculer les coordonnées des coins supérieurs gauches pour chaque mur
        float[] supGaucheAvant = {supGauche[0], supGauche[1], supGauche[2] + chaletHeight};
        float[] supGaucheDroite = {supGauche[0] + chaletLength, supGauche[1], supGauche[2]};
        float[] supGaucheGauche = {supGauche[0], supGauche[1], supGauche[2]};
        float[] supGaucheArriere = {supGauche[0], supGauche[1] + chaletWidth, supGauche[2]};

        // Maintenant, vous pouvez utiliser ces valeurs pour créer vos murs
        java.util.List<Triangle> trianglesAvant = ExporterPanneauxFinisAvant(supGaucheAvant, chaletLength, chaletWidth, chaletHeight,supGaucheDroite);
        java.util.List<Triangle> trianglesRetraitAvant = ExporterPanneauxRetraitAvant(supGaucheAvant, chaletLength, chaletWidth, chaletHeight);

        java.util.List<Triangle> trianglesDroites = ExporterPanneauxFinisDroite(supGaucheDroite, chaletWidth, chaletLength, chaletHeight);
        java.util.List<Triangle> trianglesRetraitDroites = ExporterPanneauxRetraitDroite(supGaucheDroite, chaletWidth, chaletLength, chaletHeight);

        java.util.List<Triangle> trianglesGauche = ExporterPanneauxFinisGauche(supGaucheGauche, chaletWidth, chaletLength, chaletHeight);
        java.util.List<Triangle> trianglesRetraitGauche = ExporterPanneauxRetraitGauche(supGaucheGauche, chaletWidth, chaletLength, chaletHeight);

        java.util.List<Triangle> trianglesArriere = ExporterPanneauxFinisArriere(supGaucheArriere, chaletLength, chaletWidth, chaletHeight);
        java.util.List<Triangle> trianglesRetraitArriere = ExporterPanneauxRetraitArriere(supGaucheArriere, chaletLength, chaletWidth, chaletHeight);


        // CHALET
        java.util.List<Triangle> triangleChalet = new LinkedList<>();
        triangleChalet.addAll(trianglesAvant);
        triangleChalet.addAll(trianglesDroites);
        triangleChalet.addAll(trianglesGauche);
        triangleChalet.addAll(trianglesArriere);


        //Retrait
        generateSTL(trianglesRetraitAvant,fileNameRetraitAvant);
        generateSTL(trianglesRetraitDroites,fileNameRetraitDroite);
        generateSTL(trianglesRetraitGauche,fileNameRetraitGauches);
        generateSTL(trianglesRetraitArriere,fileNameRetraitArriere);

    }

    public static java.util.List<Triangle> GenererRetraitAccessoires(java.util.List<Fenetre> listeFenetre, java.util.List<Porte> listePorte , float length, float height, float thicknessPrincipal, String Type) {

        double epaisseurChalet = Chalet.epaisseurChalet;
        java.util.List<Triangle> listeTriangleAccessoire = new LinkedList<>();

        for(Porte porte : listePorte) {

            // Coordonnées et dimensions de la porte
            Pouces lengthPortePouce = porte.largeur;
            float lengthPorte =  convertirPoucesEnFloat(lengthPortePouce); // Remplacez par la valeur spécifique
            Pouces heightPortePouce = porte.hauteur;
            float heightPorte =  convertirPoucesEnFloat(heightPortePouce) ; // Remplacez par la valeur spécifique
            int xSupGauchePorteMousePoint = porte.mousePoint.x;
            float xSupGauchePorte = xSupGauchePorteMousePoint ; // Remplacez par la valeur spécifique
            float ySupGauchePorte = 0f; // Remplacez par la valeur spécifique
            float zSupGauchePorte = 0f; // Remplacez par la valeur spécifique
/*            System.out.println("Porte X"+xSupGauchePorte);
            System.out.println("Porte Y"+ySupGauchePorte);*/

            // Ajuster les coordonnées de l'accessoire en fonction du mur
            float xSupGaucheAccessoire = 0 ;//+ xSupGauchePorte;
            float ySupGaucheAccessoire = -20; //+ ySupGauchePorte;
            float zSupGaucheAccessoire = 0f ;//+ zSupGauchePorte;


            java.util.List<Triangle> listeTrianglesPortes = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte, "Type");
            listeTriangleAccessoire.addAll(listeTrianglesPortes);
        }

        for(Fenetre fenetre : listeFenetre) {


            // Coordonnées et dimensions de la porte
            Pouces lengthPortePouce = fenetre.largeur;
            float lengthPorte =  convertirPoucesEnFloat(lengthPortePouce); // Remplacez par la valeur spécifique
            Pouces heightPortePouce = fenetre.hauteur;
            float heightPorte =  convertirPoucesEnFloat(heightPortePouce) ; // Remplacez par la valeur spécifique
            System.out.println("Fenetre Base X"+fenetre.mousePoint);

            Point SupGaucheFenetre = scalePoint(new Point(0,0),length,height,fenetre.mousePoint,500,500);
            int xSupGauchePorteMousePoint = SupGaucheFenetre.x;
            int ySupGauchePorteMousePoint = SupGaucheFenetre.y;

            float xSupGauchePorte = xSupGauchePorteMousePoint ; // Remplacez par la valeur spécifique
            float ySupGauchePorte = ySupGauchePorteMousePoint ; // Remplacez par la valeur spécifique
            System.out.println("Fenetre Ajuste X"+xSupGauchePorte);
            System.out.println("Fenetre Ajuste Y"+ySupGauchePorte);

            float zSupGauchePorte = 0f; // Remplacez par la valeur spécifique

            // Ajuster les coordonnées de l'accessoire en fonction du mur
            float xSupGaucheAccessoire = 0 ;//+ xSupGauchePorte;
            float ySupGaucheAccessoire = ySupGauchePorte; //+ ySupGauchePorte;
            float zSupGaucheAccessoire = 0f ;//+ zSupGauchePorte;

            java.util.List<Triangle> listeTrianglesFenetre = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte, "Type");
            listeTriangleAccessoire.addAll(listeTrianglesFenetre);

        }
        return listeTriangleAccessoire;
    }

    public static void retirerAccessoires(java.util.List<Fenetre> listeFenetre, java.util.List<Porte> listePorte , java.util.List<Triangle> listeTriangles, float lenght, float heigth, float thicknessPrincipal, String Type) {

        double epaisseurChalet = Chalet.epaisseurChalet;

        for(Porte porte : listePorte) {

            // Coordonnées et dimensions de la porte
            Pouces lengthPortePouce = porte.largeur;
            float lengthPorte =  convertirPoucesEnFloat(lengthPortePouce); // Remplacez par la valeur spécifique
            Pouces heightPortePouce = porte.hauteur;
            float heightPorte =  convertirPoucesEnFloat(heightPortePouce) ; // Remplacez par la valeur spécifique
            //int xSupGauchePorteMousePoint = porte.mousePoint.x;

            // Calcul du point inférieur gauche
            float xSupGauchePorteMousePoint = porte.mousePoint.x - lengthPorte;
            float yInferieurGauche = porte.mousePoint.y + heightPorte;


            float xSupGauchePorte = xSupGauchePorteMousePoint ; // Remplacez par la valeur spécifique
            float ySupGauchePorte = 0f; // Remplacez par la valeur spécifique
            float zSupGauchePorte = 0f; // Remplacez par la valeur spécifique
            System.out.println("Porte X"+xSupGauchePorte);
            System.out.println("Porte Y"+ySupGauchePorte);

            // Ajuster les coordonnées de l'accessoire en fonction du mur
            float xSupGaucheAccessoire = 0 ;//+ xSupGauchePorte;
            float ySupGaucheAccessoire = -20; //+ ySupGauchePorte;
            float zSupGaucheAccessoire = 0f ;//+ zSupGauchePorte;


            java.util.List<Triangle> listeTrianglesPortes = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte, "Type");
            retirerTrianglesCommuns(listeTriangles,listeTrianglesPortes);

        }

        for(Fenetre fenetre : listeFenetre) {


            // Coordonnées et dimensions de la porte
            Pouces lengthPortePouce = fenetre.largeur;
            float lengthPorte =  convertirPoucesEnFloat(lengthPortePouce); // Remplacez par la valeur spécifique
            Pouces heightPortePouce = fenetre.hauteur;
            float heightPorte =  convertirPoucesEnFloat(heightPortePouce) ; // Remplacez par la valeur spécifique
            System.out.println("Fenetre Base X"+fenetre.mousePoint);

            // Calcul du point inférieur gauche
            float xInferieurGauche = fenetre.mousePoint.x + lengthPorte;
            float yInferieurGauche = fenetre.mousePoint.y + heightPorte;



            /*Point SupGaucheFenetre = scalePoint(new Point(0,0),lenght,heigth,fenetre.mousePoint,500,500);
            int xSupGauchePorteMousePoint = SupGaucheFenetre.x;
            int ySupGauchePorteMousePoint = SupGaucheFenetre.y;

            float xSupGauchePorte = xSupGauchePorteMousePoint ; // Remplacez par la valeur spécifique
            float ySupGauchePorte = ySupGauchePorteMousePoint ; // Remplacez par la valeur spécifique
            System.out.println("Fenetre Ajuste X"+xSupGauchePorte);
            System.out.println("Fenetre Ajuste Y"+ySupGauchePorte); */


            Point SupGaucheFenetre = scalePoint(new Point(0,0),lenght,heigth,new Point((int) xInferieurGauche, (int) yInferieurGauche),500,500);
            xInferieurGauche = SupGaucheFenetre.x;
            yInferieurGauche = SupGaucheFenetre.y;


            float xSupGauchePorte = xInferieurGauche ; // Remplacez par la valeur spécifique
            float ySupGauchePorte = yInferieurGauche ; // Remplacez par la valeur spécifique
            float zSupGauchePorte = 0f; // Remplacez par la valeur spécifique


            // Ajuster les coordonnées de l'accessoire en fonction du mur
            float xSupGaucheAccessoire = xSupGauchePorte + lenght / 2;
            float ySupGaucheAccessoire = ySupGauchePorte + heigth / 2;
            float zSupGaucheAccessoire = zSupGauchePorte + heigth / 2;


            java.util.List<Triangle> listeTrianglesPortes = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte, "Type");
            retirerTrianglesCommuns(listeTriangles,listeTrianglesPortes);

        }
    }

    public static void retirerTrianglesCommuns(java.util.List<Triangle> listeTrianglesMur, List<Triangle> listeTrianglesAccessoires) {
        // Supprimer les triangles communs des listes en utilisant une méthode plus souple de comparaison
        listeTrianglesMur.removeIf(triangleMur -> listeTrianglesAccessoires.stream().anyMatch(triangleAccessoire -> trianglesSeChevauchent(triangleMur, triangleAccessoire)));
    }

    private static boolean trianglesSeChevauchent(Triangle triangle1, Triangle triangle2) {
        // Vérifier si les triangles se chevauchent ou se touchent en comparant les coordonnées de leurs sommets
        return (
                pointsSeChevauchent(triangle1.vertex1, triangle2.vertex1) ||
                        pointsSeChevauchent(triangle1.vertex2, triangle2.vertex2) ||
                        pointsSeChevauchent(triangle1.vertex3, triangle2.vertex3)
        ) || (
                pointsSeChevauchent(triangle1.vertex1, triangle2.vertex2) ||
                        pointsSeChevauchent(triangle1.vertex2, triangle2.vertex3) ||
                        pointsSeChevauchent(triangle1.vertex3, triangle2.vertex1)
        ) || (
                pointsSeChevauchent(triangle1.vertex1, triangle2.vertex3) ||
                        pointsSeChevauchent(triangle1.vertex2, triangle2.vertex1) ||
                        pointsSeChevauchent(triangle1.vertex3, triangle2.vertex2)
        );
    }

    private static boolean pointsSeChevauchent(float[] point1, float[] point2) {
        // Vérifier si les coordonnées des points se chevauchent (tolérance à ajuster selon la précision requise)
        float tolerance = 20f;
        return Math.abs(point1[0] - point2[0]) < tolerance &&
                Math.abs(point1[1] - point2[1]) < tolerance &&
                Math.abs(point1[2] - point2[2]) < tolerance;
    }


    public static Point scalePoint(Point lowerLeft, float rectangleWidth, float rectangleHeight, Point point, int screenWidth, int screenHeight) {
        // Calculer les rapports d'échelle pour les axes X et Y
        double scaleX = rectangleWidth / screenWidth;
        double scaleY = rectangleHeight / screenHeight;

        // Mettre à l'échelle les coordonnées X et Y du point
        int scaledX = (int) (lowerLeft.getX() + point.getX() * scaleX);
        int scaledY = (int) (lowerLeft.getY() + point.getY() * scaleY);

        // Créer et retourner le nouveau point mis à l'échelle
        return new Point(scaledX, scaledY);
    }


}