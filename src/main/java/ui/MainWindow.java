package ui;

import Utilitaires.ConvertisseurMesures;
import Utilitaires.Pouces;
import domain.Chalet;
import domain.Controleur;
import domain.Mur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.*;


public class MainWindow extends javax.swing.JFrame {
    private Controleur controleur;
    /*private AccessoiresModes actualMode;
    // Ces attributs servent à la gestion du déplacement.
    public Point actualMousePoint = new Point();
    public Point delta = new Point();*/

    private boolean isAddingPorte = false; // État pour indiquer si l'utilisateur est en mode d'ajout de porte
    private boolean isAddingFenetre = false; // Moyen de valider si l'utilisateur ajoute une fenetre
    private boolean isSupprimer = false;
    private boolean isSelection = false;
    private Point mousePointClicked = null;
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

    private JTextField XPorteField;

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
    private JButton confirmerMesuresButton;
    private JLabel AccessoirePanelLargeurPorte;
    private JTextField AccessoireLargeurPorteField;
    private JLabel AccessoirePanelHauteurPorte;
    private JTextField AccessoineHauteurPortefield;
    private JTextField MurPannelTabbedPaneGaucheLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneDroitLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneDroitLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneDroitLabelLongueurlabel;
    private JTextField MurPannelTabbedPaneFaçadeLabelLongueurTextField;
    private JTextField MurPannelTabbedPaneFaçadeLabelHauteurTextField;
    private JLabel XPorte;
    private JLabel YPorte;
    private JLabel XFenetre;
    private JTextField XFenetreField;
    private JLabel YFenetre;
    private JTextField textField1;
    private JTextField longueurchaletMN;
    private JTextField largeurchaletMN;
    private JTextField epaisseurchaletMN;
    private JTextField hauteurchaletMN;

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
                mousePointClicked = e.getPoint();

                System.out.println(mousePointClicked);
                if (isAddingPorte)
                {

                    XPorteField.setText(String.valueOf(mousePointClicked.getX()));
                    AccessoirePanelCoordonneeY.setText(String.valueOf(mousePointClicked.getY()));

                    Point mousePoint = e.getPoint();
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    Dimension intitalDimension = DrawingPanel.getPreferredSize();
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutReussi = controleur.ajouterPorte(mousePoint,nomMur,listeMursDrawer,intitalDimension);
                        if(ajoutReussi == false){
                            JOptionPane.showMessageDialog(null, "Une erreur s'est produite !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(ajoutReussi);
                        System.out.println("ajoutPortereussi");
                        DrawingPanel.repaint();

                    }
                    isAddingPorte = false;

                }
                if (isAddingFenetre && !isSelection)
                {
                    XFenetreField.setText(String.valueOf(mousePointClicked.getY()));
                    textField1.setText(String.valueOf(mousePointClicked.getY()));


                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0.2,"NORD");
                    Point mousePoint = e.getPoint();
                    Dimension intitalDimension = DrawingPanel.getPreferredSize();
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mousePoint,nomMur,listeMursDrawer, intitalDimension);
                        if(ajoutFenetrereussi == false){
                            JOptionPane.showMessageDialog(null, "Une erreur s'est produite !", "Erreur", JOptionPane.ERROR_MESSAGE);

                        }
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





        AccessoirePanelCoordonneeY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        AccessoireLargeurPorteField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = AccessoireLargeurPorteField.getText();
                Pouces nouvelleLargeur = convertirStringImperialEnPouces(inputText);

                if (nouvelleLargeur != null) {

                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    Dimension initialDimension = DrawingPanel.getPreferredSize();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    boolean success = controleur.setLargeurPorte(nouvelleLargeur, nomMur, listeMursDrawer, initialDimension);
                    if(success == false){
                        JOptionPane.showMessageDialog(null, "Une erreur s'est produite !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(ui.DrawingPanel.selectedAffichageVue);
                    System.out.println(success);
                    System.out.println("ModificationPortereussi");
                    DrawingPanel.repaint();

                }

            }
        });

        AccessoirePanelLongeurTextField.addActionListener(new ActionListener() {
            //LargeurFenetre
            @Override
            public void actionPerformed(ActionEvent e) {
                //Largeur
                String inputText = AccessoirePanelLongeurTextField.getText();
                //double nouvelleLargeurDouble = imperialToDoubleUniversel(inputText);
                //Pouces nouvelleLargeur = convertirDoubleEnPouces(nouvelleLargeurDouble);
                Pouces nouvelleLargeur = convertirStringImperialEnPouces(inputText);
                System.out.println(nouvelleLargeur+" Largeur fenetre en pouces");
                Dimension initialDimension = DrawingPanel.getPreferredSize();
                XFenetreField.setText(String.valueOf(mousePointClicked.getY()));
                textField1.setText(String.valueOf(mousePointClicked.getY()));



                if (nouvelleLargeur != null) {
                    if(mousePointClicked != null && isSelection) {
                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");
                        boolean success = controleur.setLargeurFenetre(mousePointClicked,nouvelleLargeur, nomMur, listeMursDrawer,initialDimension);
                        if(success == false){
                            JOptionPane.showMessageDialog(null, "Une erreur s'est produite !", "Erreur", JOptionPane.ERROR_MESSAGE);

                        }
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(success);
                        System.out.println("ModificationLargeurFenetreReussi");
                        DrawingPanel.repaint();
                    }

                }
            }
        });

        AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
            //HauteurFenetre
            @Override
            public void actionPerformed(ActionEvent e) {
                //Hauteur
                String inputText = AccessoirePanelLargeurTextField.getText();
                //double nouvelleLongueurDouble = imperialToDoubleUniversel(inputText);
                //Pouces nouvelleLongueur = convertirDoubleEnPouces(nouvelleLongueurDouble);
                Pouces nouvelleLongueur = convertirStringImperialEnPouces(inputText);
                System.out.println(nouvelleLongueur+" Hauteur fenetre en pouces");

                if (nouvelleLongueur != null) {

                    if(mousePointClicked != null && isSelection) {
                        XFenetreField.setText(String.valueOf(mousePointClicked.getY()));
                        textField1.setText(String.valueOf(mousePointClicked.getY()));


                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        Dimension initialDimension = DrawingPanel.getPreferredSize();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                        boolean success = controleur.setHauteurFenetre(mousePointClicked,nouvelleLongueur, nomMur, listeMursDrawer,initialDimension);
                        if(success == false){
                            JOptionPane.showMessageDialog(null, "Une erreur s'est produite !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(success);
                        System.out.println(mousePointClicked);
                        System.out.println("ModificationLongueurFenetreReussi");
                        DrawingPanel.repaint();

                    }

                }
            }
        });

        AccessoineHauteurPortefield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = AccessoineHauteurPortefield.getText();
                Pouces nouvelleHauteur = convertirStringImperialEnPouces(inputText);
                Dimension initialDimension = DrawingPanel.getPreferredSize();

                if (nouvelleHauteur != null) {

                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    boolean success = controleur.setHauteurPorte(nouvelleHauteur, nomMur, listeMursDrawer,initialDimension);
                    if(success == false){
                        JOptionPane.showMessageDialog(null, "Une erreur s'est produite !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(ui.DrawingPanel.selectedAffichageVue);
                    System.out.println(success);
                    System.out.println("ModificationPortereussi");
                    DrawingPanel.repaint();

                }
            }
        });

        // Modifier X Porte ---> EN int, les pouces semblent avoir un prob de conversion
        XPorteField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = XPorteField.getText();
                int nouveauXPorte = Integer.parseInt(inputText);
                //Pouces nouveauXPorte = convertirStringImperialEnPouces(inputText);
                Dimension initialDimension = DrawingPanel.getPreferredSize();
                System.out.println(nouveauXPorte);
                if (nouveauXPorte!=0){
                    System.out.println("Yes");
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    // On convertir mon point pouces en Point int
                    //int nouveauXporteint = convertirPoucesEnInt(nouveauXPorte);
                    int nouveauXporteint = nouveauXPorte;
                    boolean xportemodifie = controleur.modifierXPorte(mousePointClicked, nouveauXporteint, nomMur, listeMursDrawer,initialDimension );
                    System.out.println(mousePointClicked);
                    if (xportemodifie == true) {
                        // Redessiner le panneau uniquement si la modification est réussie
                        DrawingPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Une erreur s'est produite en essayant de modifier le X de la porte !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
            }
            }
        });


        supprimmerLAccessoireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //isSupprimer = true;
                if (isAddingPorte) {
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");
                    if (nomMur != "SURPLOMB") {
                        boolean ajoutReussi = controleur.supprimerPorte(nomMur, listeMursDrawer);
                        System.out.println(ui.DrawingPanel.selectedAffichageVue);
                        System.out.println(ajoutReussi);
                        System.out.println("ajoutPortereussi");
                        DrawingPanel.repaint();

                    }
                    isAddingPorte = false;
                    //isSupprimer = false;

                }
                if (isAddingFenetre && isSelection) {
                    if (mousePointClicked != null) {

                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0.2, "NORD");
                        //Point mousePoint = e.getPoint();
                        if (nomMur != "SURPLOMB") {
                            boolean suppFenetrereussi = Controleur.supprimerFenetre(mousePointClicked, nomMur, listeMursDrawer);
                            System.out.println(ui.DrawingPanel.selectedAffichageVue);
                            System.out.println(suppFenetrereussi);
                            System.out.println("suppFenetrereussi");
                            DrawingPanel.repaint();


                        }
                    } else {

                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0.2, "NORD");
                        //Point mousePoint = e.getPoint();
                        if (nomMur != "SURPLOMB") {
                            boolean suppFenetrereussi = Controleur.supprimerToutesFenetre(nomMur, listeMursDrawer);
                            System.out.println(ui.DrawingPanel.selectedAffichageVue);
                            System.out.println(suppFenetrereussi);
                            System.out.println("supToutesFenetres");
                            DrawingPanel.repaint();

                        }
                        isAddingFenetre = false;
                        //isSupprimer = false;
                    }


                }
            }


        });

        Selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSelection)
                {
                isSelection = false;
                    Selection.setBackground(UIManager.getColor("Button.background")); // Restaure la couleur par défaut du bouton
                } else {
                    mousePointClicked = null;
                    isSelection = true;
                    Color rougeTresLeger = new Color(255, 200, 200);
                    Selection.setBackground(rougeTresLeger);
                }
            }
        });
        XFenetreField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        longueurchaletMN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = longueurchaletMN.getText();
                //VÉRIFIER SI IL A APPUYER
                double longueurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLongueurChalet(longueurChaletMN);
                System.out.println(longueurChaletMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
        largeurchaletMN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = largeurchaletMN.getText();

                double largeurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLargeurChalet(largeurChaletMN);
                System.out.println(largeurChaletMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
        hauteurchaletMN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = hauteurchaletMN.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= imperialToDoubleUniversel(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
        epaisseurchaletMN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = epaisseurchaletMN.getText();
                //VÉRIFIER SI IL A APPUYER
                double epaisseurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setEpaisseurChalet(epaisseurChaletMN);
                System.out.println(epaisseurChaletMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
    }


    private void initComponents() {

        //LINKER DRAWINGPANEL AU JPANEL
        DrawingPanel = new DrawingPanel(this);
        PannelAffichage.setLayout(new BorderLayout());
        PannelAffichage.add(DrawingPanel, BorderLayout.CENTER);
        DrawingPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        DrawingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setContentPane(FenetrePrincipale);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // EDITION DES MURS, IL RESTE UN BUG SUR VUE DE SURPLOMB, peut-etre du backend...
        MurPannelTabbedPaneFaçadeLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelLongueurTextField.getText();
                //! MUR ARRIERE, LONGUEUR !\\

                double longueurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLongueurChalet(longueurChaletMN);
                System.out.println(longueurChaletMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
        MurPannelTabbedPaneDerriereLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDerriereLabelLongueurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double longueurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLongueurChalet(longueurChaletMN);
                System.out.println(longueurChaletMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
        MurPannelTabbedPaneDroitLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelLongueurTextField.getText();

                double largeurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLargeurChalet(largeurChaletMN);
                System.out.println(largeurChaletMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
        MurPannelTabbedPaneGaucheLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelLongueurTextField.getText();

                double largeurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLargeurChalet(largeurChaletMN);
                System.out.println(largeurChaletMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });


        MurPannelTabbedPaneFaçadeLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelHauteurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= imperialToDoubleUniversel(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();

            }
        });
        MurPannelTabbedPaneDerriereLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDerriereLabelHauteurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= imperialToDoubleUniversel(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
        MurPannelTabbedPaneDroitLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneDroitLabelHauteurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= imperialToDoubleUniversel(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
        MurPannelTabbedPaneGaucheLabelHauteurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneGaucheLabelHauteurTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double hauteurMursMN= imperialToDoubleUniversel(inputText);
                Controleur.setHauteurMurs(hauteurMursMN);
                System.out.println(hauteurMursMN+" entered by you..");
                DrawingPanel.repaint();
            }
        });
    }
    }

