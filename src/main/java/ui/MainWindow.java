package ui;

import domain.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainWindow extends javax.swing.JFrame {
    public Controleur controleur;
    private ApplicationMode actualMode;
    //public Point actualMousePoint = new Point();
    //public Point delta = new Point();
    public enum ApplicationMode {
        SELECT,ADD
    }

    private JPanel PanelGauche;
    private JPanel PanelHaut;
    private JPanel PannelDroit;
    private JPanel FenetrePrincipale;
    private JButton UndoButton;
    private JButton RedoButton;
    private JLabel NomProjetLabel;
    private JLabel VueLabel;
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
    private javax.swing.JPanel drawingPanel;
    private JTabbedPane ToitPaneltabbedPane;
    private JPanel ToitPaneltabbedPaneDevantPanel;
    private JPanel ToitPaneltabbedPaneDerrierePanel;
    private JPanel ToitPaneltabbedPaneDroitPanel;
    private JPanel ToitPaneltabbedPaneGauchePanel;

    public MainWindow() {
        controleur = new Controleur();
        initComponents();
        setContentPane(drawingPanel);
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

    @SuppressWarnings("unchecked")
    private void initComponents() {
        drawingPanel = new ui.DrawingPanel(this);

    }
}
