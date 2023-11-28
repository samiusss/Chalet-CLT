package Utilitaires;
import domain.*;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.convertirPoucesEnFloat;
import static domain.Chalet.determinerSommetsAccessoires;


public class STLWriterSecondaire {
    public ChaletDTO chaletdto;
    public static Chalet chalet;
    public static Mur facade ; // mur facade deja codé en bas
    public Mur gauche ; // mur arriere deja codé en bas
    private Dimension initialDimension;
    public Mur arriere ; // mur arriere deja codé en bas
    public Mur droite; // mur facade deja codé en bas


    public STLWriterSecondaire() {
        this.initialDimension = initialDimension;

        this.droite = ChaletDTO.droite; // mur facade deja codé en bas
        this.gauche = ChaletDTO.gauche; // mur gauche deja codé en bas
        this.arriere = ChaletDTO.arriere; // mur gauche deja codé en bas
        this.facade = chaletdto.facade;

    }

    // Méthode pour le traitement automatique des vertices
    private static String processVertex(float originalValue) {
        BigDecimal bd = new BigDecimal(Float.toString(originalValue));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String nombreAvecVirgule = bd.toPlainString();  // Utilisez toPlainString() pour éviter la notation scientifique

        String nombreAvecPoint = nombreAvecVirgule.replace(",", ".");
        return nombreAvecPoint;
    }



    public static List<float[]> determinerPointsPrismes(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {
        List<float[]> listeVertex = new LinkedList<>();
        int numCellsLength = 35;
        int numCellsWidth = 1;
        int numCellsHeight = 25;

        /*int numCellsLength = 100;
        int numCellsWidth = 1;
        int numCellsHeight = 50;*/

        /*int numCellsLength = 60;
        int numCellsWidth = 1;
        int numCellsHeight = 40;*/

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


    public static List<Triangle> generateRectangularPrism(float length, float width, float height, float xSupGauche, float ySupGauche, float zSupGauche) {

        List<Triangle> triangles = new ArrayList<>();
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);
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

    public static float[] calculerNormale(float[] pointA, float[] pointB, float[] pointC) {
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


    public static List<Triangle> decomposerRectangleTriangle(Point coinSupGauche, Point coinSupDroit, Point coinInfGauche, Point coinInfDroit, Double EpaisseurChalet){
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


        List<Triangle> listeTriangle = new LinkedList<>();
        listeTriangle.add(triangle1);
        listeTriangle.add(triangle2);


        return listeTriangle;

    }

    public static void generateSTL(List<Triangle> triangles, String fileName) {
        try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
            // Écrire l'en-tête du fichier STL
            fileWriter.write("solid generated\n");

            // Écrire les triangles
            for (Triangle triangle : triangles) {


                float[] normal = calculerNormale(triangle.vertex1, triangle.vertex2,triangle.vertex3);

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



    public static void ExporterPanneauxBrut(String fileName) {
        Point point = new Point(0,0);
        //determinerSommetsAccessoires fait le meme traitement dont on a besoin d'ou son utlisation ici.
        List<Point> listPointPanneaux = determinerSommetsAccessoires(point,100, 100);

        Point SupGauche = listPointPanneaux.get(0);
        Point SupDroit = listPointPanneaux.get(1);
        Point InfGauche = listPointPanneaux.get(2);
        Point InfDroit = listPointPanneaux.get(3);

        double epaisseurChalet = Chalet.epaisseurChalet;
        //double epaisseur = 15.0;

        List<Triangle> listeTriangles = generateRectangularPrism(500,(float) epaisseurChalet, 200,0,0,0);
        generateSTL(listeTriangles,fileName);

    }



    public static List<Triangle> ExporterPanneauxFinisDroite(float[] SupGauche, float length, float width, float height){
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
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Générer le prisme principal
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal);

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

        // Ajouter les prismes secondaires à la liste des triangles
        listeTriangles.addAll(listeTrianglesGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        //Générer l'espace de la porte
        Chalet chalet = Controleur.getChaletProductionStatic();
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(3).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(3).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal);


        return listeTriangles;



    }


    public static List<Triangle> ExporterPanneauxRetraitDroite(float[] SupGauche, float length, float width, float height){
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
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

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
        List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(3).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(3).porteMur ;

        List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal);
        listeTrianglesSecondaire.addAll(listeTrianglesPortes);


        return listeTrianglesSecondaire;

    }



    public static List<Triangle> ExporterPanneauxFinisGauche(float[] SupGauche, float length, float width, float height){
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
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Générer le prisme principal
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal);

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

        // Ajouter les prismes secondaires à la liste des triangles
        listeTriangles.addAll(listeTrianglesGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        //Générer l'espace de la porte
        Chalet chalet = Controleur.getChaletProductionStatic();
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(2).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(2).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal);


        return listeTriangles;


    }



    public static List<Triangle> ExporterPanneauxRetraitGauche(float[] SupGauche, float length, float width, float height){
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
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

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
        List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(2).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(2).porteMur ;

        List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal);
        listeTrianglesSecondaire.addAll(listeTrianglesPortes);


        return listeTrianglesSecondaire;

    }


    public static List<Triangle> ExporterPanneauxFinisArriere(float[] SupGauche, float length, float width, float height) {
        //Dimension du prisme de base
        double epaisseurChalet = Chalet.epaisseurChalet;
        width = (float) epaisseurChalet;


        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = (float) (Chalet.epaisseurChalet);

        // Générer le prisme principal
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal);

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

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
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(1).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(1).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal);




        return listeTriangles;
    }


    public static List<Triangle> ExporterPanneauxRetraitArriere(float[] SupGauche, float length, float width, float height) {
        //Dimension du prisme de base
        //Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];

        // Déterminer les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

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
        List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(1).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(1).porteMur ;

        List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal);
        listeTrianglesSecondaire.addAll(listeTrianglesPortes);


        return listeTrianglesSecondaire;
    }

    public static List<Triangle> ExporterPanneauxFinisAvant(float[] SupGauche, float length, float width, float height) {


        //Dimension du prisme de base
        float[] supGauche = {0,0,0};

        // Coordonnées du coin inferieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];


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

         Coordonnées du coin supérieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2]; */


        // Déterminer les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

        // Utiliser les coordonnées du coin supérieur gauche du prisme principal (xSupGauche, ySupGauche, zSupGauche)
        float xSupGauchePrincipal = xSupGauche;
        float ySupGauchePrincipal = ySupGauche;
        float zSupGauchePrincipal = zSupGauche;

        // Utiliser les dimensions du prisme principal
        float lengthPrincipal = length - length / 10;
        float thicknessPrincipal = width;

        // Générer le prisme principal
        List<Triangle> listeTriangles = generateRectangularPrism(lengthPrincipal, thicknessPrincipal, height, xSupGauchePrincipal, ySupGauchePrincipal, zSupGauchePrincipal);

        // Utiliser les dimensions du prisme secondaire
        float lengthSecondaire = length / 10;
        float heightSecondaire = height;
        float thicknessSecondaire = thicknessPrincipal / 2;

        // Utiliser les coordonnées ajustées pour les prismes secondaires
        float xSupGaucheGauche = xSupGauchePrincipal - lengthSecondaire;
        float xSupGaucheDroite = xSupGauchePrincipal + lengthPrincipal;

        // Générer les prismes secondaires à gauche et à droite
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

        // Ajouter les prismes secondaires à la liste des triangles
        listeTriangles.addAll(listeTrianglesGauche);
        listeTriangles.addAll(listeTrianglesDroite);

        //Générer l'espace de la porte
        Chalet chalet = Controleur.getChaletProductionStatic();
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(0).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(0).porteMur ;
        retirerAccessoires(listeFenetre, listePorte,listeTriangles, length, height,thicknessPrincipal);


        int i = 0;
        for(Triangle triangles : listeTriangles){
            System.out.println(triangles);
            i++;
        }
        System.out.println(i);


        return listeTriangles;
    }




    public static List<Triangle> ExporterPanneauxRetraitAvant(float[] SupGauche, float length, float width, float height) {

        //Dimension du prisme de base

        // Mur (Arriere/Avant => longueur = longueur Chalet) (Droite/Gauche => longueur = largeur Chalet)
        float[] supGauche = {0,0,0};


        // Coordonnées du coin inferieur gauche du prisme principal
        float xSupGauche = SupGauche[0];
        float ySupGauche = SupGauche[1];
        float zSupGauche = SupGauche[2];


        // Déterminer les points du prisme de base
        List<float[]> listeVertex = determinerPointsPrismes(length, width, height, xSupGauche, ySupGauche, zSupGauche);

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

        List<Triangle> listeTrianglesSecondaire = new LinkedList<>();

        // Générer les prismes secondaires à gauche et à droite (Vue d'avant sur la visionneuse , le point 0,0 est en bas a gauche -> +  )
        List<Triangle> listeTrianglesGauche = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheGauche, ySupGauchePrincipal, zSupGauchePrincipal);
        List<Triangle> listeTrianglesDroite = generateRectangularPrism(lengthSecondaire, thicknessSecondaire, heightSecondaire, xSupGaucheDroite, ySupGauchePrincipal, zSupGauchePrincipal);

        // Ajouter les prismes secondaires à la liste des triangles
        listeTrianglesSecondaire.addAll(listeTrianglesGauche);
        listeTrianglesSecondaire.addAll(listeTrianglesDroite);

        //Ajouter les prismes secondaires des accessoires
        Chalet chalet = Controleur.getChaletProductionStatic();
        List<Fenetre> listeFenetre = chalet.getListeMurs().get(0).fenetreMur ;
        List<Porte> listePorte =  chalet.getListeMurs().get(0).porteMur ;

        List<Triangle> listeTrianglesPortes =  GenererRetraitAccessoires(listeFenetre, listePorte, length, height, thicknessPrincipal);
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
        List<float[]> listeVertex = determinerPointsPrismes(lengthGaucheDroite,width,height,xSupGauche,ySupGauche,zSupGauche);

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

        // MUR AVANT
        List<Triangle> trianglesAvant = ExporterPanneauxFinisAvant(supGauche,lengthFacadeArriere,width,height);
        List<Triangle> trianglesRetraitAvant = ExporterPanneauxRetraitAvant(supGauche,lengthFacadeArriere,width,height);

        // MUR DROITE
        List<Triangle> trianglesDroites = ExporterPanneauxFinisDroite(supGauche,lengthGaucheDroite,width,height);
        List<Triangle> trianglesRetraitDroites = ExporterPanneauxRetraitDroite(v3,lengthGaucheDroite,width,height);


        //MUR GAUCHE
        List<Triangle> trianglesGauche = ExporterPanneauxFinisGauche(supGauche,lengthGaucheDroite,width,height);
        List<Triangle> trianglesRetraitGauche = ExporterPanneauxRetraitGauche(v2,lengthGaucheDroite,width,height);

        //MUR ARRIERE
        List<Triangle> trianglesArriere  = ExporterPanneauxFinisArriere(supGauche,lengthFacadeArriere,width,height);
        List<Triangle> trianglesRetraitArriere = ExporterPanneauxRetraitArriere(supGauche,lengthFacadeArriere,width,height);


        //CHALET
        List<Triangle> triangleChalet = new LinkedList<>();
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




    }


    public static void ExporterPanneauxRetrait(String fileNameAvant, String fileNameDroite, String fileNameChalet, String fileNameGauches, String fileNameArriere,String fileNameRetraitAvant,String fileNameRetraitArriere, String fileNameRetraitDroite,String fileNameRetraitGauches) throws IOException {

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
        List<float[]> listeVertex = determinerPointsPrismes(lengthGaucheDroite,width,height,xSupGauche,ySupGauche,zSupGauche);

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

        // MUR AVANT
        List<Triangle> trianglesAvant = ExporterPanneauxFinisAvant(supGauche,lengthFacadeArriere,width,height);
        List<Triangle> trianglesRetraitAvant = ExporterPanneauxRetraitAvant(supGauche,lengthFacadeArriere,width,height);

        // MUR DROITE
        List<Triangle> trianglesDroites = ExporterPanneauxFinisDroite(supGauche,lengthGaucheDroite,width,height);
        List<Triangle> trianglesRetraitDroites = ExporterPanneauxRetraitDroite(v3,lengthGaucheDroite,width,height);


        //MUR GAUCHE
        List<Triangle> trianglesGauche = ExporterPanneauxFinisGauche(supGauche,lengthGaucheDroite,width,height);
        List<Triangle> trianglesRetraitGauche = ExporterPanneauxRetraitGauche(v2,lengthGaucheDroite,width,height);

        //MUR ARRIERE
        List<Triangle> trianglesArriere  = ExporterPanneauxFinisArriere(supGauche,lengthFacadeArriere,width,height);
        List<Triangle> trianglesRetraitArriere = ExporterPanneauxRetraitArriere(supGauche,lengthFacadeArriere,width,height);


        //CHALET
        List<Triangle> triangleChalet = new LinkedList<>();
        triangleChalet.addAll(trianglesAvant);
        triangleChalet.addAll(trianglesDroites);
        triangleChalet.addAll(trianglesGauche);
        triangleChalet.addAll(trianglesArriere);

/*

        //Chalet
        generateSTL(triangleChalet,fileNameChalet);


        //Finis
        generateSTL(trianglesAvant,fileNameAvant);
        generateSTL(trianglesDroites,fileNameDroite);
        generateSTL(trianglesGauche,fileNameGauches);
        generateSTL(trianglesArriere,fileNameArriere);
*/


        //Retrait
        generateSTL(trianglesRetraitAvant,fileNameRetraitAvant);
        generateSTL(trianglesRetraitDroites,fileNameRetraitDroite);
        generateSTL(trianglesRetraitGauche,fileNameRetraitGauches);
        generateSTL(trianglesRetraitArriere,fileNameRetraitArriere);



    }




    public static List<Triangle> GenererRetraitAccessoires(List<Fenetre> listeFenetre, List<Porte> listePorte , float length, float height, float thicknessPrincipal) {

        double epaisseurChalet = Chalet.epaisseurChalet;
        List<Triangle> listeTriangleAccessoire = new LinkedList<>();

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


            List<Triangle> listeTrianglesPortes = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte);
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

            List<Triangle> listeTrianglesFenetre = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte);
            listeTriangleAccessoire.addAll(listeTrianglesFenetre);

        }
        return listeTriangleAccessoire;
    }



    public static void retirerAccessoires(List<Fenetre> listeFenetre, List<Porte> listePorte ,List<Triangle> listeTriangles, float lenght,float heigth,float thicknessPrincipal) {

        double epaisseurChalet = Chalet.epaisseurChalet;

        for(Porte porte : listePorte) {

            // Coordonnées et dimensions de la porte
            Pouces lengthPortePouce = porte.largeur;
            float lengthPorte =  convertirPoucesEnFloat(lengthPortePouce); // Remplacez par la valeur spécifique
            Pouces heightPortePouce = porte.hauteur;
            float heightPorte =  convertirPoucesEnFloat(heightPortePouce) ; // Remplacez par la valeur spécifique
            //int xSupGauchePorteMousePoint = porte.mousePoint.x;

            // Calcul du point inférieur gauche
            float xSupGauchePorteMousePoint = porte.mousePoint.x + lengthPorte;
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


            List<Triangle> listeTrianglesPortes = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte);
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


            List<Triangle> listeTrianglesPortes = generateRectangularPrism(lengthPorte, thicknessPrincipal, heightPorte, xSupGauchePorte, ySupGauchePorte, zSupGauchePorte);
            retirerTrianglesCommuns(listeTriangles,listeTrianglesPortes);

        }
    }


    public static void retirerTrianglesCommuns(List<Triangle> listeTrianglesMur, List<Triangle> listeTrianglesAccessoires) {
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
        float tolerance = 4f;
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

