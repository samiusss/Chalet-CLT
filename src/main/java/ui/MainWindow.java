package ui;

import javax.swing.*;

public class MainWindow extends javax.swing.JFrame {

    private JPanel PanelGauche;
    private JPanel PanelAffichage;
    private JPanel PanelDroit;
    private JPanel FenetrePrincipale;

    public MainWindow() {
        setContentPane(FenetrePrincipale);
        setSize(500, 500);
        setLocationRelativeTo(null);

    }

}
