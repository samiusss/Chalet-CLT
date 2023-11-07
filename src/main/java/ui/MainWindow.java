package ui;

import domain.Controleur;
import domain.Mur;

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
    private JTextField MurPannelTabbedPaneDerriereLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneDerriereLabelLargeurTextField;
    private JLabel MurPannelTabbedPaneFaçadeLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneFaçadeLabelLargeurLabel;
    private JTextField MurPannelTabbedPaneDroitLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneDroitLabelLargeurTextField;
    private JTextField MurPannelTabbedPaneGaucheLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneGaucheLabelLargeurTextField;
    private JLabel MurPannelTabbedPaneDerriereLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneDerriereLabelLargeurLabel;
    private JLabel MurPannelTabbedPaneDroitLabelLongeurlabel;
    private JLabel MurPannelTabbedPaneDroitLabelLargeurLabel;
    private JLabel MurPannelTabbedPaneGaucheLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneGaucheLabelLargeurLabel;
    private JTextField AccessoirePanelLongeurTextField;
    private JTextField AccessoirePanelLargeurTextField;
    private JLabel AccessoireLabel;
    private JLabel AccessoirePanelLongeurPanel;
    private JLabel AccessoirePanelIDPanel;
    private JLabel AccessoireID;
    private JLabel AccessoirePanelLargeurPanel;

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
        MurPannelTabbedPaneFaçadeLabelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneFaçadeLabelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneFaçadeLabelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneDerriereLabelLargeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDerriereLabelLargeurTextField.getText();
            }
        });
        MurPannelTabbedPaneDroitLabelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneDroitLabelLargeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelLargeurTextField.getText();
            }
        });
        MurPannelTabbedPaneGaucheLabelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneGaucheLabelLargeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelLargeurTextField.getText();
            }
        });
        AccessoirePanelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = AccessoirePanelLongeurTextField.getText();
            }
        });
        AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = AccessoirePanelLargeurTextField.getText();
            }
        });
//        public void setAccessoireIDText(String Accessoires.UUID) {
//            AccessoireID.setText(Accessoires.UUID);
//        }
    }

    private void drawingPanelMousePressed(java.awt.event.MouseEvent evt) {
        if (isAddingPorte) {
            System.out.println("ajoutPortereussi");



            Point mousePoint = evt.getPoint();
            String nomMur = "Droite";
            java.util.List<Mur> listeMursDrawer = null ;

            boolean ajoutReussi = controleur.ajouterPorte(mousePoint,nomMur,listeMursDrawer);



            if (ajoutReussi) {
            } else {
            }
            isAddingPorte = false;
        }

        if (isAddingFenetre){


            String nomMurr = "Droite";
            java.util.List<Mur> listeMursDrawer = null ;
            Point mousePoint = evt.getPoint();

            boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mousePoint,nomMurr,listeMursDrawer);
            System.out.println(ajoutFenetrereussi);
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
        setSize(1200, 700);
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

