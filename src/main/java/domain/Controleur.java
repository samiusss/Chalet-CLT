package domain;

import Utilitaires.Pouces;
import Utilitaires.STLWriterToit;
import Utilitaires.STLWriterSecondaire;
import Utilitaires.UndoRedoManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static domain.ChaletDTO.*;
//import static domain.ChaletDTO.creerNouveauChalet;
//import static domain.Mur.accessoiresMur;

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

    public static void ExporterRallongeVerticale() throws IOException{
       Path directory = Paths.get("C:\\STL");

        String directoryPath = "C:\\STL";
        String fileName = "RallongeVerticaleFile.stl";


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

        STLWriterToit.ExporterRallongeVerticaleFini(fileName);
    }

    public static void ExporterPignonBrut() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String directoryPath = "C:\\STL";
        String fileName = "PignonBrutFile.stl";

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

        STLWriterToit.ExporterPignonBrut(fileName);
    }

    public static void ExporterPignonFini() throws IOException{
        Path directory = Paths.get("C:\\STL");

        String directoryPath = "C:\\STL";
        String fileName = "PignonFiniFile.stl";

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

        STLWriterToit.ExporterPignonsFinis(fileName);
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
        String filePathBrutFacade = directoryPath + File.separator + "MurBrutFacade" + fileName;
        String filePathBrutArriere = directoryPath + File.separator + "MurBrutArriere" + fileName;
        String filePathBrutGauche = directoryPath + File.separator + "MurBrutGauche" + fileName;
        String filePathBrutDroite = directoryPath + File.separator + "MurBrutDroite" + fileName;


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

        STLWriterToit.ExporterPignonBrut("toitPignon.,stl");
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

        initialiserChalet(chaletProduction);
        System.out.println("======== Voici la valeur suite au UNDO "+copieDuChalet.angleToit); //test

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
        //Chalet.supprimerPorte(nomMur, copieDuChalet.listeMurs);

        initialiserChalet(chaletProduction);

        return copieDuChalet;
    }

    public static UndoRedoManager.CopieChaletUR setUndoGeneral()
    {
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.undo();


        Chalet.setAngleToit(copieDuChalet.angleToit);
        Chalet.setEpaisseurChalet(copieDuChalet.epaisseurChalet);
        Chalet.setLongueurChalet(copieDuChalet.longueurChalet);
        Chalet.setLargeurChalet(copieDuChalet.largeurChalet);
        Chalet.setHauteurMurs(copieDuChalet.hauteurMurs);
        Chalet.setRetraitChalet(copieDuChalet.retraitChalet);
        Chalet.setOrientation(copieDuChalet.orientationToit);

        initialiserChalet(chaletProduction);

        return copieDuChalet;
    }

    public static void setAngleToit(double angleToit)
    {
        Chalet.setAngleToit(angleToit);
        UndoRedoManager.CopieChaletUR copieDuChalet = UndoRedoManager.versionURCHALET();
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
    // J'ai un bugg ici
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

        initialiserChalet(chaletProduction);
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
            return true;
        }

        return false;

    }

    public static boolean ajouterPorte(Point mousepoint, String nomMur, List<Mur> listeMursDrawer,Dimension intitalDimension){

        if(Chalet.ajouterPorte(mousepoint, nomMur,listeMursDrawer,intitalDimension))
        {
            return true;
        }

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










