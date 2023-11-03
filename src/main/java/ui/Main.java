/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;
import domain.Chalet;
import domain.Mur;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author ismaelsdiri
 */
public class Main {

    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

        double largeurChalet = 10.0;
        double longueurChalet = 10.0;
        double hauteurMurs = 8.0;
        double epaisseurChalet = 2.0;
        double angleToit = 0.0;
        ArrayList<Mur> listeMurs = new ArrayList<>();
        String orientationToit = "Nord";

        Chalet chalet = new Chalet(largeurChalet, longueurChalet, epaisseurChalet, angleToit, hauteurMurs, listeMurs, orientationToit);
        chalet.initialiserMurFacade();

        // Accédez aux coordonnées du point "10.0" de Mur: Facade
        Mur facade = chalet.getListeMurs().get(0); // Obtenez le premier mur de la liste (Mur: Facade)
        Point point10_0 = facade.getSommetsMur().get(2); // Obtenez le troisième sommet (index 2) qui a les coordonnées (10.0, 2.0)

        // Affichez les coordonnées
        System.out.println("exemple pour get un point spécifique, soit l'absice du 3e point du mur facade:   "+point10_0.getX());

        chalet.afficherListeMurs();

    }

}
