package ui;

/*import Utilitaires.STLWriter3;
import Utilitaires.STLWriterSecondaire;*/
import Utilitaires.STLWriterToit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main implements java.io.Serializable {

    public static void main(String[] args) throws IOException {



        // Spécifiez le nom du fichier STL de sortiee
        //String fileName = "chemin/vers/votre/repertoire/fichier.stl";

        // Créez un objet Path représentant le chemin du répertoire
        Path directory = Paths.get("C:\\STL");

        // Spécifiez le chemin complet du répertoire et le nom du fichier
        String directoryPath = "C:\\STL";
        String fileName = "fichier.stl";

        // Concaténez le chemin du répertoire et le nom du fichier pour obtenir le chemin complet
        String filePathBrut = directoryPath + File.separator + "MurBrutFacade" + fileName;

        String filePath = directoryPath + File.separator + "MurFinisFacade" + fileName;
        String filePathDroite = directoryPath + File.separator + "MurFinisDroite" + fileName;
        String filePathChalet = directoryPath + File.separator + "ToutFinisChalet" + fileName;
        String filePathGauche = directoryPath + File.separator + "MurFinisGauche" + fileName;
        String filePathArriere = directoryPath + File.separator + "MurFinisArriere" + fileName;

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

        STLWriterToit.ExporterParDessusFini("ParDessusFiniFileeee.stl");

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

    }
}
