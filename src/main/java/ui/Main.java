package ui;
import Utilitaires.Point3D;
import Utilitaires.STLWriterPrincipal;
import Utilitaires.Triangle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {



        // Spécifiez le nom du fichier STL de sortie
        //String fileName = "chemin/vers/votre/repertoire/fichier.stl";

        // Créez un objet Path représentant le chemin du répertoire
        Path directory = Paths.get("C:\\STL");

        // Spécifiez le chemin complet du répertoire et le nom du fichier
        String directoryPath = "C:\\STL";
        String fileName = "fichier.stl";

        // Concaténez le chemin du répertoire et le nom du fichier pour obtenir le chemin complet
        String filePath = directoryPath + File.separator + "MurFacade" + fileName;
        String filePathDroite = directoryPath + File.separator + "MurDroite" + fileName;
        String filePathChalet = directoryPath + File.separator + "ToutChalet" + fileName;
        String filePathGauche = directoryPath + File.separator + "MurGauche" + fileName;
        String filePathArriere = directoryPath + File.separator + "MurArriere" + fileName;

        String filePathRetraitAvant = directoryPath + File.separator + "MurFacadeRetrait" + fileName;
        String filePathRetraitDroite = directoryPath + File.separator + "MurDroiteRetrait" + fileName;
        String filePathRetraitGauche = directoryPath + File.separator + "MurGaucheRetrait" + fileName;
        String filePathRetraitArriere = directoryPath + File.separator + "MurArriereRetrait" + fileName;


        // Vérifiez si le répertoire existe, sinon, créez-le
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
                System.out.println("Le répertoire a été créé avec succès : " + directory);
            } catch (Exception e) {
                System.out.println("Erreur : Le répertoire n'a pas pu être créé.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Le répertoire existe déjà : " + directory);
        }

        //STLWriter.ExporterPanneauxBrut(filePath);
        STLWriterPrincipal.ExporterPanneaux(filePath,filePathDroite,filePathChalet,filePathGauche,filePathArriere,filePathRetraitAvant,filePathRetraitArriere,filePathRetraitDroite,filePathRetraitGauche);



        /* // Définir la géométrie de l'objet (triangles)
        List<Triangle> listeTriangles =  new LinkedList<>();

        float[] vertex1 = {0, 0, 0};
        float[] vertex2 = {1, 0, 0};
        float[] vertex3 = {0, 1, 0};

        Triangle triangle1 = new Triangle(vertex1,vertex2,vertex3);
        listeTriangles.add(triangle1);

        // Nom du fichier STL de sortie
        String stlFileName = "output.stl";

        // Générer le fichier STL
        STLWriter.generateSTL(listeTriangles, filePath); */


        /*List<Triangle> triangles = new ArrayList<>();

        triangles.add(new Triangle(new Point3D(0, 0, 0), new Point3D(10, 0, 0), new Point3D(10, 10, 0)));
        triangles.add(new Triangle(new Point3D(0, 10, 0), new Point3D(10, 0, 0), new Point3D(10, 10, 0)));
        System.out.println(triangles.size());
        System.out.println(triangles);
        // Spécifiez le nom du fichier STL de sortie
        String fileName = "chemin/vers/votre/repertoire/fichier.stl";

        // Créez un objet Path représentant le chemin du répertoire
        Path directory = Paths.get("chemin/vers/votre/repertoire");

        // Vérifiez si le répertoire existe, sinon, créez-le
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
                System.out.println("Le répertoire a été créé avec succès : " + directory);
            } catch (Exception e) {
                System.out.println("Erreur : Le répertoire n'a pas pu être créé.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Le répertoire existe déjà : " + directory);
        }

        // Appelez la méthode writeSTLFile pour écrire le fichier STL
        STLWriter.File(triangles, fileName);

        */

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

    }
}
