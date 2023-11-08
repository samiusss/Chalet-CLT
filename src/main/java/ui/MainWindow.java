package ui;

import domain.Chalet;
import domain.Controleur;
import domain.Mur;
import Utilitaires.Pouces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


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
    private JTextField MurPannelTabbedPaneFaçadeLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneFaçadeLabelHauteurTextField;
    private JPanel MurPannelTabbedPaneFaçadeLabel;
    private JPanel MurPannelTabbedPaneDerrièreLabel;
    private JPanel MurPannelTabbedPaneGaucheLabel;
    private JPanel MurPannelTabbedPaneDroitLabel;
    private JTextField MurPannelTabbedPaneDerriereLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneDerriereLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneFaçadeLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneFaçadeLabelLargeurLabel;
    private JTextField MurPannelTabbedPaneDroitLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneDroitLabelHauteurTextField;
    private JTextField MurPannelTabbedPaneGaucheLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneGaucheLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneDerriereLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneDerriereLabelHauteurLabel;
    private JLabel MurPannelTabbedPaneDroitLabelLongueurlabel;
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

    private JTextField AccessoirePanelCoordonneeX;

    private JTextField AccessoirePanelCoordonnee;

    private JTextField AccessoirePanelCoordonneeY;

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
    private JButton supprimmerLAccessoireButton;
    private JButton Selection;
    private JLabel AccessoirePanelLargeurPorte;
    private JTextField AccessoireLargeurPorteField;
    private JLabel AccessoirePanelHauteurPorte;
    private JTextField AccessoineHauteurPortefield;

    private Controleur.AffichageVue selectedVue;


    public MainWindow() {
        controleur = new Controleur();
        initComponents();

        //Gestion de l'ajout d'accessoires
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
        PannelAffichage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isAddingPorte)
                {
                    Point mousePoint = e.getPoint();
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutReussi = controleur.ajouterPorte(mousePoint,nomMur,listeMursDrawer);
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(ajoutReussi);
                        System.out.println("ajoutPortereussi");
                        DrawingPanel.repaint();

                    }
                    isAddingPorte = false;

                }
                if (isAddingFenetre)
                {

                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0.2,"NORD");
                    Point mousePoint = e.getPoint();
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mousePoint,nomMur,listeMursDrawer);
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(ajoutFenetrereussi);
                        System.out.println("ajoutFenetrereussi");
                        DrawingPanel.repaint();
                    }

                    isAddingFenetre = false;

                }
            }
        });

        //Gestion des vues
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


        MurPannelTabbedPaneFaçadeLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelLongueurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double longueurChaletMN= Double.parseDouble(inputText);
                Controleur.setLongueurChalet(longueurChaletMN);
                System.out.println(longueurChaletMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
        MurPannelTabbedPaneDerriereLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String inputText = MurPannelTabbedPaneDerriereLabelHauteurTextField.getText();
            //VÉRIFIER SI IL A APPUYER
            double hauteurMursMN= Double.parseDouble(inputText);
            Controleur.setHauteurMurs(hauteurMursMN);
            System.out.println(hauteurMursMN+" entered by you..");
            DrawingPanel.repaint();
            }
        });


        MurPannelTabbedPaneDroitLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelLongueurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double largeurChaletMN= Double.parseDouble(inputText);
                Controleur.setLargeurChalet(largeurChaletMN);
                System.out.println(largeurChaletMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
        MurPannelTabbedPaneDroitLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelHauteurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= Double.parseDouble(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
        MurPannelTabbedPaneGaucheLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelLongueurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double largeurChaletMN= Double.parseDouble(inputText);
                Controleur.setLargeurChalet(largeurChaletMN);
                System.out.println(largeurChaletMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
        MurPannelTabbedPaneGaucheLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelHauteurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= Double.parseDouble(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();            }
        });
        AccessoirePanelLongeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = AccessoirePanelLongeurTextField.getText();
            }
        });
        MurPannelTabbedPaneDerriereLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDerriereLabelLongueurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double longueurChaletMN= Double.parseDouble(inputText);
                Controleur.setLongueurChalet(longueurChaletMN);
                System.out.println(longueurChaletMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
        MurPannelTabbedPaneFaçadeLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelHauteurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= Double.parseDouble(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();
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




        AccessoirePanelCoordonneeX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        AccessoirePanelCoordonneeY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        AccessoireLargeurPorteField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputext = AccessoireLargeurPorteField.getText();
                Pouces nouvellelargeur = Pouces.fromString(inputext);
                if (nouvellelargeur != null) {

                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    boolean success = controleur.setLargeurPorte(nouvellelargeur, nomMur, listeMursDrawer);
                    System.out.println(ui.DrawingPanel.selectedAffichageVue);
                    System.out.println(success);
                    System.out.println("ModificationPortereussi");
                    DrawingPanel.repaint();

                }
            }
        });
}


        /*supprimmerLAccessoireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isAddingPorte)
                {
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutReussi = controleur.supprimerPorte(nomMur,listeMursDrawer);
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(ajoutReussi);
                        System.out.println("ajoutPortereussi");
                        DrawingPanel.repaint();

                    }
                    isAddingPorte = false;

                }
                if (isAddingFenetre)
                {

                    /*String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0.2,"NORD");
                    Point mousePoint = e.getPoint();
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mousePoint,nomMur,listeMursDrawer);
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(ajoutFenetrereussi);
                        System.out.println("ajoutFenetrereussi");
                        DrawingPanel.repaint();
                    }

                    isAddingFenetre = false;

                }
    }
    }
                            */



    private void initComponents() {
        DrawingPanel = new DrawingPanel(this);
        PannelAffichage.setLayout(new BorderLayout());
        PannelAffichage.add(DrawingPanel, BorderLayout.CENTER);
        DrawingPanel.setPreferredSize(new java.awt.Dimension(500, 500));
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

    }

    }

