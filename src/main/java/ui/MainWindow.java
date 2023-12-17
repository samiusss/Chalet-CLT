package ui;

import Utilitaires.Pouces;
//import Utilitaires.STLWriterSecondaire;
import domain.Chalet;
import domain.ChaletDTO;
import domain.Controleur;
import domain.Mur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.*;


public class MainWindow extends javax.swing.JFrame {
    private Controleur controleur;
    private ChaletDTO chaletdto;
    private Chalet chalet;

    /*private AccessoiresModes actualMode;
    // Ces attributs servent à la gestion du déplacement.
    public Point actualMousePoint = new Point();
    public Point delta = new Point();*/

    public static boolean isAddingPorte = false; // État pour indiquer si l'utilisateur est en mode d'ajout de porte
    public static boolean isAddingFenetre = false; // Moyen de valider si l'utilisateur ajoute une fenetre
    private boolean isSupprimer = false;
    public static boolean isSelection = false;
    public static Point mousePointClicked = null;
    private JPanel PanelGauche;
    private JPanel PanelHaut;
    private JPanel PannelDroit;
    public JPanel FenetrePrincipale;
    private JButton UndoButton;
    private JButton RedoButton;
    public  JComboBox VueComboBox;
    private JLabel ChalCLTPanel;
    private JLabel MurPanelLabel;
    private JPanel MurPanel;
    private JLabel ToitPanelLabel;
    private JPanel ToitPanel;
    private JLabel NomProjetLabel;
    private JLabel VueLabel;
    public JPanel PannelAffichage;
    public JPanel DrawingPanel;
    private JLabel DrawingPanelCoordonéesLabel;
    public  JButton PannelDroitAjoutPorteButton;
    public  JButton PannelDroitAjoutFenetreButton;
    private JTabbedPane MurPannelTabbedPane;
    private JTextField MurPannelTabbedPaneFaçadeLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneFaçadeLabelLargeurTextField;
    private JPanel MurPannelTabbedPaneFaçadeLabel;
    private JPanel MurPannelTabbedPaneDerrièreLabel;
    private JPanel MurPannelTabbedPaneGaucheLabel;
    private JPanel MurPannelTabbedPaneDroitLabel;
    public JTextField MurPannelTabbedPaneDerriereLabelLongueurTextField;
    public JTextField MurPannelTabbedPaneDerriereLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneFaçadeLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneFaçadeLabelLargeurLabel;
    private JTextField MurPannelTabbedPaneDroitLabelLongeurTextField;
    private JTextField MurPannelTabbedPaneDroitLabelHauturTextField;
    private JTextField MurPannelTabbedPaneGaucheLabelLongeurTextField;
    public JTextField MurPannelTabbedPaneGaucheLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneDerriereLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneDerriereLabelHauteurLabel;
    private JLabel MurPannelTabbedPaneDroitLabelLongeurlabel;
    private JLabel MurPannelTabbedPaneDroitLabelHauteurLabel;
    private JLabel MurPannelTabbedPaneGaucheLabelLongeurLabel;
    private JLabel MurPannelTabbedPaneGaucheLabelHauteurLabel;
    public JTextField AccessoirePanelLongeurTextField;
    public  JTextField AccessoirePanelLargeurTextField;
    private JLabel AccessoireLabel;
    private JLabel AccessoirePanelLongeurPanel;
    private JLabel AccessoirePanelIDPanel;
    private JLabel AccessoireID;
    private JLabel AccessoirePanelLargeurPanel;
    public JTextField ToitPaneltabbedPaneDroitPanelAngleTextField;
    public JTextField ToitPaneltabbedPaneDroitPanelHauteurTextField;
    public JTextField ToitPaneltabbedPaneGauchePanelAngleTextField;
    public JTextField ToitPaneltabbedPaneGauchePanelHauteurTextField;

    public JTextField XPorteField;

    private JTextField AccessoirePanelCoordonnee;

    public JTextField YporteField;

    private JLabel AjoutPorteLabel;
    private JLabel AjoutFenetreLabel;
    public JTextField ToitPaneltabbedPaneDevantPanelAngleTextField;
    public JTextField ToitPaneltabbedPaneDevantPanelHauteurTextField;
    public JTextField ToitPaneltabbedPaneDerrierePanelAngleTextField;
    public JTextField ToitPaneltabbedPaneDerrierePanelHauteurTextField;
    private JComboBox AccessoireComboBox;
    public JButton supprimmerLAccessoireButton;
    public JButton Selection;
    private JButton confirmerMesuresButton;
    private JLabel AccessoirePanelLargeurPorte;
    public JTextField AccessoireLargeurPorteField;
    private JLabel AccessoirePanelHauteurPorte;
    public JTextField AccessoineHauteurPortefield;
    public JTextField MurPannelTabbedPaneGaucheLabelLongueurTextField;
    public JTextField MurPannelTabbedPaneDroitLabelLongueurTextField;
    public JTextField MurPannelTabbedPaneDroitLabelHauteurTextField;
    private JLabel MurPannelTabbedPaneDroitLabelLongueurlabel;
    public JTextField MurPannelTabbedPaneFaçadeLabelLongueurTextField;
    public JTextField MurPannelTabbedPaneFaçadeLabelHauteurTextField;
    private JLabel XPorte;
    private JLabel YPorte;
    private JLabel XFenetre;
    public JTextField XFenetreField;
    private JLabel YFenetre;
    public JTextField YfenetreField;
    public JTextField longueurchaletMN;
    public JTextField largeurchaletMN;
    public JTextField epaisseurchaletMN;
    public JTextField hauteurchaletMN;
    private JButton BrutExport;
    private JButton ExporterFinit;
    private JButton ExporterRetrait;
    public JButton nouveauChaletButton;
    private JButton changeOrientationButton;
    public JTextField retrait;
    private JTextField angleTextField;
    private JComboBox orientation;
    private JTextField grilleTextField;
    private Point ZoomOrigin;

    private ChaletDTO.AffichageVue selectedVue;

    private double zoomFactor = 1.0;
    private int xOffsetDrag;
    private int yOffsetDrag;


    public MainWindow() {
        controleur = new Controleur();
        initComponents();

        PannelDroitAjoutPorteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.isAddingPorte = true;
            }
        });
        PannelDroitAjoutFenetreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.isAddingFenetre = true;
            }
        });
        VueComboBox.addItemListener(new ItemListener() {
            private ChaletDTO.AffichageVue selectedVue;
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
                            this.setVue(ChaletDTO.AffichageVue.FACADE);
                            ui.DrawingPanel.changerVue(selectedVue);
                            DrawingPanel.repaint();
                            break;
                        case "Arriere":
                            this.setVue(ChaletDTO.AffichageVue.ARRIERE);
                            ui.DrawingPanel.changerVue(selectedVue);
                            DrawingPanel.repaint();
                            break;
                        case "Droit":
                            this.setVue(ChaletDTO.AffichageVue.DROITE);
                            ui.DrawingPanel.changerVue(selectedVue);
                            DrawingPanel.repaint();
                            break;
                        case "Gauche":
                            this.setVue(ChaletDTO.AffichageVue.GAUCHE);
                            ui.DrawingPanel.changerVue(selectedVue);
                            DrawingPanel.repaint();
                            break;
                        case "Surplomb":
                            this.setVue(ChaletDTO.AffichageVue.SURPLOMB);
                            ui.DrawingPanel.changerVue(selectedVue);
                            DrawingPanel.repaint();
                            break;
                        default:
                            break;
                    }
                }
            }

            private void setVue(ChaletDTO.AffichageVue facade) {
                this.selectedVue = facade;
            }

        });

        AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = AccessoirePanelLargeurTextField.getText();
            }
        });

        AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = AccessoirePanelLargeurTextField.getText();
            }
        });


        PannelAffichage.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double oldZoomFactor = zoomFactor;
                double rotation = e.getPreciseWheelRotation();
                zoomFactor *= (rotation < 0) ? 1.02 : 0.98;

                double factor = zoomFactor / oldZoomFactor;
                float offsetX = controleur.getOffsetX();
                float offsetY = controleur.getOffsetY();
                offsetX += (e.getX() - offsetX) * (1 - factor);
                offsetY += (e.getY() - offsetY) * (1 - factor);

                controleur.setOffsetX(offsetX);
                controleur.setOffsetY(offsetY);
                controleur.setZoom(zoomFactor);
                repaint();
            }
        });


        YporteField.addActionListener(new ActionListener() {
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
                        JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
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
                int adjustedX = (int)((mousePointClicked.getX() - controleur.getOffsetX()) / controleur.getZoom());
                int adjustedY = (int)((mousePointClicked.getY() - controleur.getOffsetY()) / controleur.getZoom());
                Point mPoint = new Point(adjustedX, adjustedY);
                XFenetreField.setText(String.valueOf(adjustedX));
                YfenetreField.setText(String.valueOf(adjustedY));


                if (nouvelleLargeur != null) {
                    if(mPoint != null && isSelection) {
                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");
                        boolean success = controleur.setLargeurFenetre(mPoint,nouvelleLargeur, nomMur, listeMursDrawer,initialDimension);
                        if(success == false){
                            JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        DrawingPanel.repaint();
                    }

                }
            }
        });

        AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
            //HauteurFenetre
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText = AccessoirePanelLargeurTextField.getText();
                Pouces nouvelleLongueur = convertirStringImperialEnPouces(inputText);
                int adjustedX = (int)((mousePointClicked.getX() - controleur.getOffsetX()) / controleur.getZoom());
                int adjustedY = (int)((mousePointClicked.getY() - controleur.getOffsetY()) / controleur.getZoom());
                Point mPoint = new Point(adjustedX, adjustedY);

                if (nouvelleLongueur != null) {

                    if(mPoint != null && isSelection) {
                        XFenetreField.setText(String.valueOf(adjustedX));
                        YfenetreField.setText(String.valueOf(adjustedY));

                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        Dimension initialDimension = DrawingPanel.getPreferredSize();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                        boolean success = controleur.setHauteurFenetre(mPoint,nouvelleLongueur, nomMur, listeMursDrawer,initialDimension);
                        if(success == false){
                            JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
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
                        JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(ui.DrawingPanel.selectedAffichageVue);
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
                Pouces nouveauXPorte = convertirStringImperialEnPouces(inputText);

                if (nouveauXPorte != null) {
                    System.out.println("Yes");
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");

                    int adjustedX = (int) ((mousePointClicked.getX() - controleur.getOffsetX()) / controleur.getZoom());
                    int adjustedY = (int) ((mousePointClicked.getY() - controleur.getOffsetY()) / controleur.getZoom());
                    Point mPoint = new Point(adjustedX, adjustedY);

                    int nouveauXporteint = convertirPoucesEnInt(nouveauXPorte);
                    nouveauXporteint = Math.round(nouveauXporteint);

                    boolean xporteModifie = controleur.modifierXPorte(nouveauXporteint, nomMur, DrawingPanel.getPreferredSize());

                    if (xporteModifie) {
                        DrawingPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Une erreur s'est produite en essayant de modifier le X de la porte !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });



        XFenetreField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = XFenetreField.getText();

                Pouces nouveauXFenetre = convertirStringImperialEnPouces(inputText);
                Dimension initialDimension = DrawingPanel.getPreferredSize();
                int adjustedX = (int)((mousePointClicked.getX() - controleur.getOffsetX()) / controleur.getZoom());
                int adjustedY = (int)((mousePointClicked.getY() - controleur.getOffsetY()) / controleur.getZoom());
                Point mPoint = new Point(adjustedX, adjustedY);

                if (nouveauXFenetre!=null&isSelection){
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");

                    int nouveauXFenetreint = convertirPoucesEnInt(nouveauXFenetre);
                    nouveauXFenetreint = Math.round(nouveauXFenetreint);
                    System.out.println("Nouveau X" + nouveauXFenetreint);

                    boolean xFenetremodifie = controleur.modifierXFenetre(nouveauXFenetreint, nomMur, DrawingPanel.getPreferredSize());

                    if (xFenetremodifie)
                    {
                        DrawingPanel.repaint();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Une erreur s'est produite en essayant de modifier le X de la fenetre !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        YfenetreField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = YfenetreField.getText();

                Pouces nouveauYFenetre = convertirStringImperialEnPouces(inputText);
                Dimension initialDimension = DrawingPanel.getPreferredSize();
                int adjustedX = (int)((mousePointClicked.getX() - controleur.getOffsetX()) / controleur.getZoom());
                int adjustedY = (int)((mousePointClicked.getY() - controleur.getOffsetY()) / controleur.getZoom());
                Point mPoint = new Point(adjustedX, adjustedY);

                if (nouveauYFenetre!=null&isSelection){
                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    int nouveauYFenetreint = convertirPoucesEnInt(nouveauYFenetre);
                    nouveauYFenetreint = Math.round(nouveauYFenetreint);
                    System.out.println("Nouveau Y" + nouveauYFenetreint);

                    boolean yFenetremodifie = controleur.modifierYFenetre(nouveauYFenetreint, nomMur, DrawingPanel.getPreferredSize());
                    System.out.println(mPoint);
                    if (yFenetremodifie == true) {
                        DrawingPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Une erreur s'est produite en essayant de modifier le Y de la fenetre !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        supprimmerLAccessoireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int adjustedX = (int)((mousePointClicked.getX() - controleur.getOffsetX()) / controleur.getZoom());
                int adjustedY = (int)((mousePointClicked.getY() - controleur.getOffsetY()) / controleur.getZoom());
                Point mPoint = new Point(adjustedX, adjustedY);

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

                }
                if (isAddingFenetre && isSelection) {
                    if (mPoint != null) {

                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(3.0, "NORD");
                        //Point mousePoint = e.getPoint();
                        if (nomMur != "SURPLOMB") {
                            boolean suppFenetrereussi = Controleur.supprimerFenetre(mPoint, nomMur, listeMursDrawer);
                            DrawingPanel.repaint();
                        }
                    }
                    else
                    {

                        String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(3.0, "NORD");
                        if (nomMur != "SURPLOMB") {
                            boolean suppFenetrereussi = Controleur.supprimerToutesFenetre(nomMur, listeMursDrawer);

                            DrawingPanel.repaint();

                        }
                        isAddingFenetre = false;
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
        YfenetreField.addActionListener(new ActionListener() {
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
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();
            }
        });

        largeurchaletMN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = largeurchaletMN.getText();

                double largeurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLargeurChalet(largeurChaletMN);
                System.out.println(largeurChaletMN);
                System.out.println(largeurChaletMN+" entered by you..");
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();

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
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();

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
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();

            }
        });
        nouveauChaletButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);

                Chalet chalet = controleur.getChaletProduction();
                List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");

                controleur.supprimerPorte("Facade", listeMursDrawer);
                Controleur.supprimerToutesFenetre("Facade", listeMursDrawer);
                controleur.supprimerPorte("DROITE", listeMursDrawer);
                Controleur.supprimerToutesFenetre("DROITE", listeMursDrawer);
                controleur.supprimerPorte("GAUCHE", listeMursDrawer);
                Controleur.supprimerToutesFenetre("GAUCHE", listeMursDrawer);
                controleur.supprimerPorte("ARRIERE", listeMursDrawer);
                Controleur.supprimerToutesFenetre("ARRIERE", listeMursDrawer);
                Controleur.setEpaisseurChalet(imperialToDoubleUniversel("2'"));
                Controleur.setLongueurChalet(imperialToDoubleUniversel("20'"));
                Controleur.setLargeurChalet(imperialToDoubleUniversel("20'"));
                Controleur.setHauteurMurs(imperialToDoubleUniversel("17'"));

                System.out.println("Nouveau Chalet Créer");

                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();
                isAddingPorte = false;
                isAddingFenetre = false;
            }
        });
        retrait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = retrait.getText();
                //VÉRIFIER SI IL A APPUYER
                double retraitChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setRetraitChalet(retraitChaletMN);
                System.out.println(retraitChaletMN+" entered by you..");
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();
            }
        });
//        orientationTextField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String inputText = orientationTextField.getText();
//                Controleur.setOrientation(inputText);
//                FenetrePrincipale.revalidate();
//                FenetrePrincipale.repaint();
//                System.out.println(inputText + " comme orientation a été rentré...");
//
//            }
//        });
        angleTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double inputText = Double.parseDouble(angleTextField.getText());
                Controleur.setAngleToit(inputText);
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();
                System.out.println(inputText + " comme orientation a été rentré...");
            }
        });
        BrutExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controleur.ExporterPanneauxBrut();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Vous avez exporté Chalet Brut", "Exportations STL", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /*ExporterFinit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controleur.ExporterPanneauxFinit();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Vous avez exporté Chalet Fini", "Exportations STL", JOptionPane.INFORMATION_MESSAGE);
            }
        });*/

        ExporterRetrait.addActionListener(new ActionListener(){
            @Override
           public void actionPerformed(ActionEvent e){
                try{
                    controleur.ExporterPanneauxRetrait();
                } catch(IOException ex){
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Vous avez exporté Chalet Retrait", "Exportations STL", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        orientation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenez la valeur sélectionnée dans le JComboBox
                String selectedOrientation = (String) orientation.getSelectedItem();

                // Utilisez la valeur sélectionnée comme orientation
                Controleur.setOrientation(selectedOrientation);

                // Rafraîchissez la fenêtre principale
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();

                System.out.println(selectedOrientation + " comme orientation a été sélectionné...");
            }
        });
        grilleTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = grilleTextField.getText();
                //VÉRIFIER SI IL A APPUYER
                double grilleMN= imperialToDoubleUniversel(inputText);
                Controleur.setGrille(grilleMN);
                System.out.println(grilleMN+" entered by you..");
                FenetrePrincipale.revalidate();
                FenetrePrincipale.repaint();
            }
        });
    }


    private void initComponents() {

        //LINKER DRAWINGPANEL AU JPANEL
        DrawingPanel = new DrawingPanel(this);
        PannelAffichage.setLayout(new BorderLayout());
        PannelAffichage.add(DrawingPanel, BorderLayout.CENTER);
        DrawingPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        PannelAffichage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setContentPane(FenetrePrincipale);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        MurPannelTabbedPaneFaçadeLabelLongueurTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = MurPannelTabbedPaneFaçadeLabelLongueurTextField.getText();
                //! MUR ARRIERE, LONGUEUR !\\

                double longueurChaletMN= imperialToDoubleUniversel(inputText);
                Controleur.setLongueurChalet(longueurChaletMN);
                System.out.println(longueurChaletMN+" entered by you..");

                revalidate();
                repaint();
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
                DrawingPanel.revalidate();
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
                DrawingPanel.revalidate();
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
                DrawingPanel.revalidate();
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
                DrawingPanel.revalidate();
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
                DrawingPanel.revalidate();
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
                DrawingPanel.revalidate();
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
                DrawingPanel.revalidate();
                DrawingPanel.repaint();
            }
        });

    }
    }

