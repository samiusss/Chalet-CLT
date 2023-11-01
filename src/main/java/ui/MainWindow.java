package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends javax.swing.JFrame {

    private JPanel PanelGauche;
    private JPanel PanelAffichage;
    private JPanel PanelDroit;
    private JPanel FenetrePrincipale;
    private JButton weshButton;
    private JButton button2;

    public MainWindow() {
        setContentPane(FenetrePrincipale);
        setSize(500, 500);
        setLocationRelativeTo(null);
        weshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "vive l<algerie, isma t nul");
            }
        });
    }

}
