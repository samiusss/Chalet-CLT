/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;

import domain.Chalet;
import domain.Mur;

import java.util.ArrayList;

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
        chalet.initialiserMurArriere();
        chalet.initialiserMurDroite();
        chalet.initialiserMurGauche();
        chalet.getMursUsines();
        chalet.afficherListeMurs();

    }

}
