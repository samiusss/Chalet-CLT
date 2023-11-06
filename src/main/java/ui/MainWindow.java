package ui;

import domain.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class MainWindow extends javax.swing.JFrame {
    private Controleur controleur;
    /*private AccessoiresModes actualMode;
    // Ces attributs servent à la gestion du déplacement.
    public Point actualMousePoint = new Point();
    public Point delta = new Point();*/

    private boolean isAddingPorte = false; // État pour indiquer si l'utilisateur est en mode d'ajout de porte
    private boolean isAddingFenetre = false; // Moyen de valider si l'utilisateur ajoute une fenetre
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
    //private DrawingPanel PannelAffichage; // Utilisez DrawingPanel au lieu de JPanel
    private JTabbedPane ToitPaneltabbedPane;
    private JPanel ToitPaneltabbedPaneDevantPanel;
    private JPanel ToitPaneltabbedPaneDerrierePanel;
    private JPanel ToitPaneltabbedPaneDroitPanel;
    private JPanel ToitPaneltabbedPaneGauchePanel;
    private JLabel NomProjetLabel;
    private JLabel VueLabel;
    private JPanel PannelAffichage;

    private JPanel DrawingPanel;
    private JLabel DrawingPanelCoordonéesLabel;
    private JButton PannelDroitAjoutPorteButton;
    private JButton PannelDroitAjoutFenetreButton;
    private JTabbedPane MurPannelTabbedPane;
    private JTextField MurPannelTabbedPaneFaçadeLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneFaçadeLabelLargeurTextField;
    private JPanel MurPannelTabbedPaneFaçadeLabel;
    private JPanel MurPannelTabbedPaneDerrièreLabel;
    private JPanel MurPannelTabbedPaneGaucheLabel;
    private JPanel MurPannelTabbedPaneDroitLabel;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel MurPannelTabbedPaneFaçadeLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneFaçadeLabelLargeurLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField5;
    private JTextField textField6;

    // private JMenuBar menuBar1;
    //private ui.DrawingPanel DrawingPanel;


    public MainWindow() {
        controleur = new Controleur();
        initComponents();

        PannelDroitAjoutPorteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAddingPorte = true;
            }
        });
        PannelDroitAjoutFenetreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                isAddingFenetre = true;

            }

        });

        VueComboBox.addItemListener(new ItemListener() {
            private String getSelectedVueOption() {
                return (String) VueComboBox.getSelectedItem();
            }
            @Override
            public void itemStateChanged(ItemEvent e) {
                        // Check which option is selected and call the corresponding drawing function
//                        String selectedOption = (String) VueComboBox.getSelectedItem();
//                        if (selectedOption != null) {
//                            switch (selectedOption) {
//                                case "Facade":
//                                    drawFacade();
//                                    break;
//                                case "Arriere":
//                                    drawArriere();
//                                    break;
//                                case "Droit":
//                                    drawDroit();
//                                    break;
//                                case "Gauche":
//                                    drawGauche();
//                                    break;
//                                default:
//                                    // Handle any other cases or do nothing
//                                    break;
                            //}
                        //}
                    }

        });
    }


    private void drawingPanelMousePressed(java.awt.event.MouseEvent evt) {
        if (isAddingPorte) {
            System.out.println("ajoutFenetrereussi");

            Point mousePoint = evt.getPoint();
            String nomMur = "Droite";
            boolean ajoutReussi = controleur.ajouterPorte(mousePoint,nomMur);


            if (ajoutReussi) {
            } else {
            }
            isAddingPorte = false;
        }
        if (isAddingFenetre){
            Point mousePoint = evt.getPoint();
            String nomMur = "Droite";
            boolean ajoutFenetrereussi = controleur.ajouterFenetre(mousePoint,nomMur);
            System.out.println("ajoutFenetrereussi");


            if (ajoutFenetrereussi){
            }else {
            }
            isAddingFenetre = false;
        }
    }

    private void initComponents() {

        DrawingPanel = new DrawingPanel(this);

        PannelAffichage.setLayout(new BorderLayout());
        PannelAffichage.add(DrawingPanel, BorderLayout.CENTER);
        DrawingPanel.setPreferredSize(new java.awt.Dimension(555, 424));
        DrawingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setContentPane(FenetrePrincipale);
        setSize(500, 500);
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

    }


/*
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
*/
    }

