package ui;

import domain.Controleur;
import domain.Mur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


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
    private JTextField MurPannelTabbedPaneDerriereLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneDerriereLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneFaçadeLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneFaçadeLabelLargeurLabel;
    private JTextField MurPannelTabbedPaneDroitLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneDroitLabelHauturTextField;
    private JTextField MurPannelTabbedPaneGaucheLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneGaucheLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneDerriereLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneDerriereLabelHauteurLabel;
    private JLabel MurPannelTabbedPaneDroitLabelLongeurlabel;
    private JLabel MurPannelTabbedPaneDroitLabelHauteurLabel;
    private JLabel MurPannelTabbedPaneGaucheLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneGaucheLabelHauteurLabel;
    private JTextField AccessoirePanelLongeurTextField;
    private JTextField AccessoirePanelLargeurTextField;
    private JLabel AccessoireLabel;
    private JLabel AccessoirePanelLongeurPanel;
    private JLabel AccessoirePanelIDPanel;
    private JLabel AccessoireID;
    private JLabel AccessoirePanelLargeurPanel;
    private JLabel ToitPaneltabbedPaneDroitPanelAngleLabel;
    private JLabel ToitPaneltabbedPaneDroitPanelHauteurLabel;
    private JTextField ToitPaneltabbedPaneDroitPanelAngleTextField;
    private JTextField ToitPaneltabbedPaneDroitPanelHauteurTextField;
    private JTextField ToitPaneltabbedPaneGauchePanelAngleTextField;
    private JTextField ToitPaneltabbedPaneGauchePanelHauteurTextField;
    private JLabel ToitPaneltabbedPaneGauchePanelAngleLabel;
    private JLabel ToitPaneltabbedPaneGauchePanelHauteurLabel;
    private JLabel AjoutPorteLabel;
    private JLabel AjoutFenetreLabel;
    private JTextField ToitPaneltabbedPaneDevantPanelAngleTextField;
    private JTextField ToitPaneltabbedPaneDevantPanelHauteurTextField;
    private JLabel ToitPaneltabbedPaneDevantPanelAngleLabel;
    private JLabel ToitPaneltabbedPaneDevantPanelHauteurLabel;
    private JTextField ToitPaneltabbedPaneDerrierePanelAngleTextField;
    private JTextField ToitPaneltabbedPaneDerrierePanelHauteurTextField;
    private JLabel ToitPaneltabbedPaneDerrierePanelAngleLabel;
    private JLabel ToitPaneltabbedPaneDerrierePanelHauteurLabel;
    private JComboBox AccessoireComboBox;

    private Controleur.AffichageVue selectedVue;


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

            private Controleur.AffichageVue selectedVue;

            private String getSelectedVueOption() {
                return (String) VueComboBox.getSelectedItem();
            }
            @Override

            
            public void itemStateChanged(ItemEvent e) {
                        int vueSelecteur;
                        String selectedOption = (String) VueComboBox.getSelectedItem();

                        if (selectedOption != null) {
                            switch (selectedOption) {
                                case "Facade":
                                    //drawFacade();
                                    this.setVue(Controleur.AffichageVue.FACADE);
                                    ui.DrawingPanel.changerVue(selectedVue);
                                    DrawingPanel.repaint();
                                    System.out.println("Facade");
                                    break;
                                case "Arriere":
                                    this.setVue(Controleur.AffichageVue.ARRIERE);
                                    ui.DrawingPanel.changerVue(selectedVue);
                                    DrawingPanel.repaint();
                                    System.out.println("Arriere");
                                    break;
                                case "Droit":
                                    this.setVue(Controleur.AffichageVue.DROITE);
                                    ui.DrawingPanel.changerVue(selectedVue);
                                    DrawingPanel.repaint();
                                    System.out.println("Droit");
                                    break;
                                case "Gauche":
                                    this.setVue(Controleur.AffichageVue.GAUCHE);

                                    ui.DrawingPanel.changerVue(selectedVue);
                                    DrawingPanel.repaint();
                                    System.out.println("Gauche");
                                    break;
                                case "Surplomb":
                                    this.setVue(Controleur.AffichageVue.SURPLOMB);

                                    ui.DrawingPanel.changerVue(selectedVue);
                                    DrawingPanel.repaint();
                                    System.out.println("Surplomb");
                                    break;
                                default:
                                    // Handle any other cases or do nothing
                                    break;
                            }
                        }
                    }

            private void setVue(Controleur.AffichageVue facade) {
                this.selectedVue = facade;

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
        MurPannelTabbedPaneDerriereLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDerriereLabelHauteurTextField.getText();
            }
        });
        MurPannelTabbedPaneDroitLabelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneDroitLabelHauturTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelHauturTextField.getText();
            }
        });
        MurPannelTabbedPaneGaucheLabelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneGaucheLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelHauteurTextField.getText();
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
        ToitPaneltabbedPaneDroitPanelAngleTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneDroitPanelAngleTextField.getText();
            }
        });
        ToitPaneltabbedPaneDroitPanelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneDroitPanelHauteurTextField.getText();
            }
        });

        ToitPaneltabbedPaneGauchePanelAngleTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneGauchePanelAngleTextField.getText();
            }
        });

        ToitPaneltabbedPaneGauchePanelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneGauchePanelHauteurTextField.getText();
            }
        });

        ToitPaneltabbedPaneDevantPanelAngleTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneDevantPanelAngleTextField.getText();
            }
        });
        ToitPaneltabbedPaneDevantPanelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneDevantPanelHauteurTextField.getText();

            }
        });
        ToitPaneltabbedPaneDerrierePanelAngleTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneDerrierePanelAngleTextField.getText();
            }
        });

        ToitPaneltabbedPaneDerrierePanelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = ToitPaneltabbedPaneDerrierePanelHauteurTextField.getText();
            }
        });

        PannelAffichage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isAddingPorte)
                {
                    //addPorte();

                    Point mousePoint = e.getPoint();
                    String nomMur = "Droite";
                    //java.util.List<Mur> listeMursDrawer = chalet.getMursUsines() ;
                    java.util.List<Mur> listeMursDrawer = null ;

                    boolean ajoutReussi = controleur.ajouterPorte(mousePoint,nomMur,listeMursDrawer);
                    System.out.println("ajoutPortereussi");
                    isAddingPorte = false;
                }
                if (isAddingFenetre)
                {
                    //addFenetre();

                    String nomMurr = "Droite";
                    //java.util.List<Mur> listeMursDrawer = chalet.getMursUsines() ;
                    java.util.List<Mur> listeMursDrawer = null ;

                    Point mousePoint = e.getPoint();

                    boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mousePoint,nomMurr,listeMursDrawer);
                    System.out.println(ajoutFenetrereussi);
                    System.out.println("ajoutFenetrereussi");

                    isAddingFenetre = false;

                }
            }
        });

    }

    /*private void drawingPanelMousePressed(java.awt.event.MouseEvent evt) {
        if (isAddingPorte) {

            Point mousePoint = evt.getPoint();
            String nomMur = "Droite";
            java.util.List<Mur> listeMursDrawer = chalet.getMursUsines() ;

            boolean ajoutReussi = controleur.ajouterPorte(mousePoint,nomMur,listeMursDrawer);
            System.out.println("ajoutPortereussi");


            if (ajoutReussi) {
            } else {
            }
            isAddingPorte = false;
        }

        if (isAddingFenetre){


            String nomMurr = "Droite";
            java.util.List<Mur> listeMursDrawer = chalet.getMursUsines() ;
            Point mousePoint = evt.getPoint();

            boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mousePoint,nomMurr,listeMursDrawer);
            System.out.println(ajoutFenetrereussi);
            System.out.println("ajoutFenetrereussi");


            if (ajoutFenetrereussi){
            }else {
            }
            isAddingFenetre = false;
        }
    }*/

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

