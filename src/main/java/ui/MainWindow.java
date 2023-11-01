package ui;

import javax.swing.*;
import java.awt.BorderLayout;

public class MainWindow extends javax.swing.JFrame {

    private JPanel PanelGauche;
    private JPanel PanelAffichage;
    private JPanel PanelDroit;
    private JPanel FenetrePrincipale;
    private JButton button1;
    private JButton button2;

    public MainWindow() {
        setContentPane(FenetrePrincipale);
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

}
