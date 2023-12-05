package ui;

import domain.Chalet;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        Chalet.initialiserPignonDroit();
        System.out.println("liste de toit est: "+Chalet.listeToit);
        //System.out.println(Toit.);

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);

    }
}
