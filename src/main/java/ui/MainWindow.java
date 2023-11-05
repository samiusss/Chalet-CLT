package ui;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainWindow extends javax.swing.JFrame {
    private Controleur controleur;
    private AccessoiresModes actualMode;
    // Ces attributs servent à la gestion du déplacement.
    public Point actualMousePoint = new Point();
    public Point delta = new Point();


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
    private JPanel DrawingPanel;
    private JLabel DrawingPanelCoordonéesLabel;
    private JButton PannelDroitAjoutPorteButton;
    private JButton PannelDroitAjoutFenetreButton;
    //private ui.DrawingPanel DrawingPanel;


    public AccessoiresModes selectedAccessoiresModes;

    public enum AccessoiresModes {
        PORTE, FENETRE
    }



    public MainWindow() {
        controleur = new Controleur();
        initComponents();



       /* PannelDroitAjoutPorteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }


        });*/

        /* PannelDroitAjoutFenetreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        }); */



    }

    private void initComponents() {
        DrawingPanel = new DrawingPanel(this);
        setContentPane(DrawingPanel);
        setSize(1000, 1000);
        setLocationRelativeTo(null);





        /* UndoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Undo();
            }
        }); */
        /* RedoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redo();
            }
        });
        VueComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            }
        }); */

        //Configure the layout for FenetrePrincipale n'affiche plus quand je add drawingPanel donc je le mets en commentaires jusqua que ca change
        DrawingPanel.setLayout(new FlowLayout());
        DrawingPanel.add(FenetrePrincipale, BorderLayout.PAGE_END);
    }


    public void setMode(AccessoiresModes newMode)
    {
        this.selectedAccessoiresModes = newMode;
    }

    private void FENETREButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setMode(AccessoiresModes.FENETRE);
    }
    private void PORTEBUTTONACTIONPERFORMED(java.awt.event.ActionEvent evt){
        this.setMode(AccessoiresModes.PORTE);
    }
    private void drawingPanelMousePressed(java.awt.event.MouseEvent evt){
        Point mousePoint = evt.getPoint();
        this.actualMousePoint = mousePoint;
        if (this.actualMode == AccessoiresModes.PORTE){

        }
    }



}

