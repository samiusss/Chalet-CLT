/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;

import domain.Chalet;

/**
 *
 * @author ismaelsdiri
 */
public class Main {

    public static void main(String[] args) {
        
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

        Chalet chalet = new Chalet();
        chalet.initialiserMurFacade();
        chalet.initialiserMurArriere();
        chalet.initialiserMurDroite();
        chalet.initialiserMurGauche();
        chalet.afficherListeMurs();

    }
    
}
