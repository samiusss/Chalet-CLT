package domain;

import Utilitaires.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static domain.ChaletDTO.*;

public class Controleur implements java.io.Serializable {
    private ChaletDTO chaletdto;
    private final double zoom;
    private float OffsetX;

    private float OffsetY;


   /* public Controleur (Chalet chalet){
        this.chalet = chalet;
    }*/
    /*public Controleur() {
        chalet = new Chalet();
    }
*/
   public Controleur()
   {
       this.zoom = 1;
   }

    public static void ExporterPanneauxFinis() throws IOException {
        // Spécifiez le nom du fichier STL de sortie
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


        //String fileName = "ChalCLT_Brut_PG.stl";


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

        STLWriterSecondaire.ExporterPanneauxFinis(filePath,filePathDroite,filePathChalet,filePathGauche,filePathArriere,filePathRetraitAvant,filePathRetraitArriere,filePathRetraitDroite,filePathRetraitGauche);

    }


    public static void ExporterToitPignonFinis() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String directoryPath = "C:\\STL";
        String fileName = "ChalCLT_PIGNON_RV.stl";
        String filePathDroite = directoryPath + File.separator + fileName;
        String filePathGauche = directoryPath + File.separator + fileName;


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

        STLWriterToit2.ExporterToitPignonFinis(filePathDroite,filePathGauche);
    }

    public static void ExporterRallongeVerticale() throws IOException{
       Path directory = Paths.get("C:\\STL");

        String directoryPath = "C:\\STL";

        String filePathRallongeV = directoryPath + File.separator + "ChalCLT_Fini_RV.stl";


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

        STLWriterToit.ExporterRallongeVerticaleFini(filePathRallongeV);
    }

    public static void ExporterPignonBrutDroite() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String directoryPath = "C:\\STL";
        String fileName = "File.stl";


        String filePathPignonBrutDroite = directoryPath + File.separator + "ChalCLT_Brut_PD.stl";

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

        STLWriterToit.ExporterPignonBrutDroite(filePathPignonBrutDroite);
    }

    public static void ExporterPignonRetraitGauche() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "ChalCLT_Retrait_PG.stl";
        String filePathPignonRetraitGauche = directory + File.separator + "ChalCLT_Retrait_PG.stl";

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

        STLWriterToit.ExporterPignonRetrait(filePathPignonRetraitGauche);
    }

    public static void ExporterPignonRetraitDroit() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String directoryPath = "C:\\STL";
        String fileName = "ChalCLT_Retrait_PD.stl";

        String filePathPignonRetraitGauche = directory + File.separator + "ChalCLT_Retrait_PD.stl";


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

        STLWriterToit.ExporterPignonRetrait(filePathPignonRetraitGauche);
    }

    public static void ExporterRallongeVerticaleBrut() throws IOException{
        Path directory = Paths.get("C:\\STL");


        String filePathRallongeVerticaleBrut = directory + File.separator + "ChalCLT_Brut_RV.stl";


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

        STLWriterToit.ExporterRallongeVerticaleBrut(filePathRallongeVerticaleBrut);
    }

    public static void ExporterRallongeVerticaleRetrait() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = ".stl";
        String filePathChalCLT_Retrait_RV  = directory + File.separator + "ChalCLT_Retrait_RV" + fileName;


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

        STLWriterToit.ExporterRallongeVerticaleRetrait(filePathChalCLT_Retrait_RV);
    }

    public static void ExporterPignonBrutGauche() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "ChalCLT_Brut_PG.stl";

        String filePathChalCLT_Brut_PG  = directory + File.separator + fileName;


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

        STLWriterToit.ExporterPignonBrutGauche(filePathChalCLT_Brut_PG);
    }



    public static void ExporterParDessusFini()throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "ChalCLT_Fini_ParDessus.stl";

        String fileParDessusFini  = directory + File.separator + fileName;

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

        STLWriterToit.ExporterParDessusFini(fileParDessusFini);
    }
    public static void ExporterRetraitParDessus()throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "ChalCLT_Retrait_ParDessus.stl";
        String fileParDessusRetrait  = directory + File.separator + fileName;

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

        STLWriterToit.ExporterRetraitParDessus(fileParDessusRetrait);
    }
    public static void ExporterToit()throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "Chalclt_Fini_Toit.stl";
        String fileParDessusRetrait  = directory + File.separator + fileName;

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

        STLWriterToit.ExporterToit(fileParDessusRetrait);
    }

    public static void ExporterParDessusBrut()throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "ChalCLT_Brut_ParDessus.stl";
        String fileParDessusBrut  = directory + File.separator + fileName;

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

        STLWriterToit.ExporterParDessusBrut(fileParDessusBrut);
    }


    public static void ExporterPignonFiniDroite() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "ChalCLT_Fini_PD.stl";

        String filePignonFiniDroite  = directory + File.separator + fileName;


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

        STLWriterToit.ExporterPignonFiniDroite(filePignonFiniDroite);
    }

    public static void ExporterPignonFiniGauche() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String fileName = "ChalCLT_Fini_PG.stl";
        String filePignonFiniGauche  = directory + File.separator + fileName;


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

        STLWriterToit.ExporterPignonFiniGauche(filePignonFiniGauche);
    }

    public static void ExporterPanneauxBrut() throws IOException {
        // Spécifiez le nom du fichier STL de sortie
        //String fileName = "chemin/vers/votre/repertoire/fichier.stl";

        // Créez un objet Path représentant le chemin du répertoire
        Path directory = Paths.get("C:\\STL");

        // Spécifiez le chemin complet du répertoire et le nom du fichier
        String directoryPath = "C:\\STL";
        String fileName = "fichier.stl";



        // Concaténez le chemin du répertoire et le nom du fichier pour obtenir le chemin complet
        String filePathBrutFacade = directory + File.separator + "MurBrutFacade" + fileName;
        String filePathBrutArriere = directory + File.separator + "MurBrutArriere" + fileName;
        String filePathBrutGauche = directory + File.separator + "MurBrutGauche" + fileName;
        String filePathBrutDroite = directory + File.separator + "MurBrutDroite" + fileName;


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

        STLWriterSecondaire.ExporterPanneauxBrut(filePathBrutDroite);
    }


    public static void ExporterPanneauxRetrait() throws IOException {
        // Spécifiez le nom du fichier STL de sortie
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

        STLWriterSecondaire.ExporterPanneauxRetrait(filePath,filePathDroite,filePathChalet,filePathGauche,filePathArriere,filePathRetraitAvant,filePathRetraitArriere,filePathRetraitDroite,filePathRetraitGauche);

    }
    public static UndoRedoManager.CopieChaletUR setRedo()
    {
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.redo();

        Chalet.setAngleToit(copieDuChalet.angleToit);
        Chalet.setEpaisseurChalet(copieDuChalet.epaisseurChalet);
        Chalet.setLongueurChalet(copieDuChalet.longueurChalet);
        Chalet.setLargeurChalet(copieDuChalet.largeurChalet);
        Chalet.setHauteurMurs(copieDuChalet.hauteurMurs);
        Chalet.setRetraitChalet(copieDuChalet.retraitChalet);
        Chalet.setOrientation(copieDuChalet.orientationToit);
        Chalet.setGrille(copieDuChalet.grilleP);
        //Chalet.setListeMurs(copieDuChalet.listeMurs);

//        chaletProduction.getListeMurs().get(0).porteMur = copieDuChalet.listeMurs.get(0).porteMur;
//        chaletProduction.getListeMurs().get(1).porteMur = copieDuChalet.listeMurs.get(1).porteMur;
//        chaletProduction.getListeMurs().get(2).porteMur = copieDuChalet.listeMurs.get(2).porteMur;
//        chaletProduction.getListeMurs().get(3).porteMur = copieDuChalet.listeMurs.get(3).porteMur;
//        chaletProduction.getListeMurs().get(0).fenetreMur = copieDuChalet.listeMurs.get(0).fenetreMur;
//        chaletProduction.getListeMurs().get(1).fenetreMur = copieDuChalet.listeMurs.get(1).fenetreMur;
//        chaletProduction.getListeMurs().get(2).fenetreMur = copieDuChalet.listeMurs.get(2).fenetreMur;
//        chaletProduction.getListeMurs().get(3).fenetreMur = copieDuChalet.listeMurs.get(3).fenetreMur;


        //if longueur largeur tataat
        initialiserChalet(chaletProduction);
        // if listeMur donc accessoire change

        initialiserChaletUndoRedo(chaletProduction,copieDuChalet); //undo accessoire seuelemnt marche ici

        return copieDuChalet;
    }
    public static UndoRedoManager.CopieChaletUR setUndo()
    {
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.undo();

        Chalet.setAngleToit(copieDuChalet.angleToit);
        Chalet.setEpaisseurChalet(copieDuChalet.epaisseurChalet);
        Chalet.setLongueurChalet(copieDuChalet.longueurChalet);
        Chalet.setLargeurChalet(copieDuChalet.largeurChalet);
        Chalet.setHauteurMurs(copieDuChalet.hauteurMurs);
        Chalet.setRetraitChalet(copieDuChalet.retraitChalet);
        Chalet.setOrientation(copieDuChalet.orientationToit);
        Chalet.setGrille(copieDuChalet.grilleP);
        //Chalet.setListeMurs(copieDuChalet.listeMurs);
        //Chalet.setListePorteFacade(copieDuChalet.listeMurs.get(0).porteMur,copieDuChalet.listeMurs.get(1).porteMur,copieDuChalet.listeMurs.get(2).porteMur,copieDuChalet.listeMurs.get(3).porteMur,copieDuChalet.listeMurs.get(0).fenetreMur,copieDuChalet.listeMurs.get(1).fenetreMur,copieDuChalet.listeMurs.get(2).fenetreMur,copieDuChalet.listeMurs.get(3).fenetreMur);


//        chaletProduction.getListeMurs().get(0).porteMur = copieDuChalet.listeMurs.get(0).porteMur;
//        chaletProduction.getListeMurs().get(1).porteMur = copieDuChalet.listeMurs.get(1).porteMur;
//        chaletProduction.getListeMurs().get(2).porteMur = copieDuChalet.listeMurs.get(2).porteMur;
//        chaletProduction.getListeMurs().get(3).porteMur = copieDuChalet.listeMurs.get(3).porteMur;
//        chaletProduction.getListeMurs().get(0).fenetreMur = copieDuChalet.listeMurs.get(0).fenetreMur;
//        chaletProduction.getListeMurs().get(1).fenetreMur = copieDuChalet.listeMurs.get(1).fenetreMur;
//        chaletProduction.getListeMurs().get(2).fenetreMur = copieDuChalet.listeMurs.get(2).fenetreMur;
//        chaletProduction.getListeMurs().get(3).fenetreMur = copieDuChalet.listeMurs.get(3).fenetreMur;


        //if longueur largeur tataat
        initialiserChalet(chaletProduction);
        // if listeMur donc accessoire change

        initialiserChaletUndoRedo(chaletProduction,copieDuChalet);

        return copieDuChalet;
    }

    public static void setListeMurs(List<Mur> listeMurs)
    {
        Chalet.setListeMurs(listeMurs);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);
    }

        public static void setAngleToit(double angleToit)
    {
        Chalet.setAngleToit(angleToit);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);
    }




    public static void setEpaisseurNouveauChalet(double epaisseurChalet)
    {
        Chalet.setEpaisseurChalet(epaisseurChalet);
        //UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);
    }

    public static void setLongueurNouveauChalet(double longueurChalet)
    {
        Chalet.setLongueurChalet(longueurChalet);
        //UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }

    public static void setLargeurNouveauChalet(double largeurChalet)
    {
        Chalet.setLargeurChalet(largeurChalet);
        //UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }

    public static void setHauteurNouveauMurs(double hauteurMurs) {
        Chalet.setHauteurMurs(hauteurMurs);
        //UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }


    public static void setEpaisseurChalet(double epaisseurChalet)
    {
        Chalet.setEpaisseurChalet(epaisseurChalet);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);
    }

    public static void setLongueurChalet(double longueurChalet)
    {
        Chalet.setLongueurChalet(longueurChalet);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }

    public static void setLargeurChalet(double largeurChalet)
    {
        Chalet.setLargeurChalet(largeurChalet);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }

    public static void setHauteurMurs(double hauteurMurs) {
        Chalet.setHauteurMurs(hauteurMurs);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }
    public static void setRetraitChalet(double distanceUsinage) {
        Chalet.setRetraitChalet(distanceUsinage);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }
    public static void setOrientation(String orientation) {
        Chalet.setOrientation(orientation);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        initialiserChalet(chaletProduction);

    }
    public boolean setLargeurPorte(Pouces nouvellelargeur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setLargeurPorte(nouvellelargeur, nomMur, listeMursDrawer, initialDimension);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;

    }
    public boolean setHauteurPorte(Pouces nouvelleHauteur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setHauteurPorte(nouvelleHauteur, nomMur, listeMursDrawer,initialDimension);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }

    public boolean setHauteurFenetre(Point mousePointClicked, Pouces nouvelleLongueur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setHauteurFenetre(mousePointClicked,nouvelleLongueur, nomMur, listeMursDrawer, initialDimension);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }

    public boolean setLargeurFenetre(Point mousePointClicked,Pouces nouvelleLongueur, String nomMur, List<Mur> listeMursDrawer, Dimension initialDimension)
    {
        boolean success = Chalet.setLargeurFenetre(mousePointClicked,nouvelleLongueur, nomMur, listeMursDrawer,initialDimension);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }
    public boolean supprimerPorte(String nomMur, List<Mur> listeMursDrawer)
    {
        boolean success = Chalet.supprimerPorte(nomMur, listeMursDrawer);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }
    public boolean modifierXPorte(int nouveauXporteint, String nomMur, Dimension initialDimension)
    {
        boolean success = chaletProduction.modifierXporte(nouveauXporteint, nomMur,initialDimension );
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;}

    public boolean modifierXFenetre(int nouveauXfenetreint, String nomMur,Dimension initialDimension)
    {
        boolean success = chaletProduction.modifierXfenetre(nouveauXfenetreint,nomMur,initialDimension );
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }

    public boolean modifierYFenetre( int nouveauYfenetreint, String nomMur,Dimension initialDimension)
    {
        boolean success = chaletProduction.modifierYfenetre(nouveauYfenetreint, nomMur,initialDimension );
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }

    public static boolean supprimerFenetre(Point mousePointClicked,String nomMur, List<Mur> listeMursDrawer)
    {
        boolean success = Chalet.supprimerFenetre(mousePointClicked,nomMur, listeMursDrawer);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }

    public static boolean supprimerToutesFenetre(String nomMur, List<Mur> listeMursDrawer)
    {
        boolean success = Chalet.supprimerToutesFenetre(nomMur, listeMursDrawer);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return success;
    }

    public boolean MethodeTest(String nomMur,List<Mur> listeMursDrawer, Point mousePoint)
    {
        boolean succes = Chalet.MethodeTest(nomMur,listeMursDrawer,mousePoint);
        //UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        initialiserChalet(chaletProduction);
        return succes;
    }

    public boolean MethodeTestFenetre(String nomMur,List<Mur> listeMursDrawer, Point mousePoint)
    {
        boolean succes = Chalet.MethodeTestFenetre(nomMur,listeMursDrawer,mousePoint);
        //UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

//        initialiserChalet(chaletProduction);
        return succes;
    }

    public static void setGrille(double grille) {
        Chalet.setGrille(grille);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
        System.out.println(grille +" comme grille a été updated in Controleur"); //test

    }





    public static boolean ajouterFenetre(Point mousepoint, String nomMur, List<Mur> listeMursDrawer, Dimension intitalDimension){

        if(Chalet.ajouterFenetre(mousepoint, nomMur,listeMursDrawer,intitalDimension))
        {
            UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
            return true;

        }
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        return false;

    }

    public static boolean ajouterPorte(Point mousepoint, String nomMur, List<Mur> listeMursDrawer,Dimension intitalDimension){

        if(Chalet.ajouterPorte(mousepoint, nomMur,listeMursDrawer,intitalDimension))
        {
            UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
            return true;
        }
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();

        return false;
        //Porte newPorte = newAccessoires("AID",mousepoint, double largeur,double hauteur);
        //accessoiresmur.add(newPorte);
    }

    public ChaletDTO getChalet() {
        return chaletdto;
    }
    public double getZoom () {
        double leZoom = Chalet.getZoom();
        return leZoom;
    }

    public float getOffsetX () {
        float leOffX = Chalet.getOffsetX();
        return leOffX;
    }
    public float getOffsetY () {
        float leOffY = Chalet.getOffsetY();
        return leOffY;
    }
    public void setZoom(double newZoom)
    {
        Chalet.setZoom(newZoom);
    }
    public void setOffsetX(float newOffX)
    {
        Chalet.setOffsetX(newOffX);
    }
    public void setOffsetY(float newOffY)
    {
        Chalet.setOffsetY(newOffY);
    }

    static Chalet chaletProduction = createChalet();

    boolean rep = initialiserChalet(chaletProduction);

    public Chalet getChaletProduction() {
        return chaletProduction;
    }
    public static Chalet getChaletProductionStatic() {
        return chaletProduction;
    }}










