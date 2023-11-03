/*package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainWindow extends javax.swing.JFrame {

    private JPanel PanelGauche;
    private JPanel PanelHaut;
    private JPanel PannelDroit;
    private JPanel FenetrePrincipale;
    private JButton UndoButton;
    private JButton RedoButton;
    private JComboBox VueComboBox;
    private JLabel ChalCLTPanel;
    private JLabel MurPanelLabel;
    private JPanel MurLabel;
    private JPanel MurPanel;
    private JLabel ToitPanelLabel;
    private JPanel ToitPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JPanel PannelAffichage;
    private JTabbedPane ToitPaneltabbedPane;
    private JPanel ToitPaneltabbedPaneDevantPanel;
    private JPanel ToitPaneltabbedPaneDerrierePanel;
    private JPanel ToitPaneltabbedPaneDroitPanel;
    private JPanel ToitPaneltabbedPaneGauchePanel;
    private JLabel NomProjetLabel;
    private JLabel VueLabel;

    public MainWindow() {
        setContentPane(FenetrePrincipale);
        setSize(500, 500);
        setLocationRelativeTo(null);

        UndoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Undo();
            }
        });
        RedoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Redo();
            }
        });
        VueComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    // The user has selected an item from the JComboBox
//                    String selectedItem = (String) comboBox.getSelectedItem();
//
//                    // Check if the selected item is the one you want to trigger the "yo()" method
//                    if ("OptionToTriggerYo".equals(selectedItem)) {
//                        yo();
//                    }
//                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
*/

package ui;

import domain.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainWindow extends javax.swing.JFrame {
    private Controleur controleur;
    private ApplicationMode actualMode;

    public enum ApplicationMode {
        SELECT, ADD
    }

    private JPanel PanelGauche;
    private JPanel PanelHaut;
    private JPanel PannelDroit;
    private JPanel FenetrePrincipale;
    private JButton UndoButton;
    private JButton RedoButton;
    private JComboBox VueComboBox;
    private JLabel ChalCLTPanel;
    private JLabel MurPanelLabel;
    private JPanel MurPanel;
    private JLabel ToitPanelLabel;
    private JPanel ToitPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    //private DrawingPanel PannelAffichage; // Utilisez DrawingPanel au lieu de JPanel
    private JTabbedPane ToitPaneltabbedPane;
    private JPanel ToitPaneltabbedPaneDevantPanel;
    private JPanel ToitPaneltabbedPaneDerrierePanel;
    private JPanel ToitPaneltabbedPaneDroitPanel;
    private JPanel ToitPaneltabbedPaneGauchePanel;
    private JLabel NomProjetLabel;
    private JLabel VueLabel;
    private JPanel drawingPanel;
    //private ui.DrawingPanel drawingPanel;

    public MainWindow() {
        controleur = new Controleur();
        initComponents();
        setContentPane(FenetrePrincipale);
        setSize(500, 500);
        setLocationRelativeTo(null);

        UndoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Undo();
            }
        });
        RedoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redo();
            }
        });
        VueComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Handle item state change
            }
        });
    }

    private void initComponents() {
        // Create an instance of DrawingPanel and add it to FenetrePrincipale
        drawingPanel = new DrawingPanel(this); // Pass 'this' to DrawingPanel if needed

        //Configure the layout for FenetrePrincipale n'affiche plus quand je add drawingPanel donc je le mets en commentaires jusqua que ca change
        //FenetrePrincipale.setLayout(new BorderLayout());
        //FenetrePrincipale.add(drawingPanel, BorderLayout.EAST);
    }
}

