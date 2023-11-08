package ui;

import domain.Chalet;
import domain.Controleur;
import domain.Mur;

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

    private JLabel CoordoneesAccessoiresLabel;
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
    private JButton confirmerMesuresButton;

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


                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);

                    //java.util.List<Mur> listeMursDrawer = chalet.getMursUsines() ;
                    //java.util.List<Mur> listeMursDrawer = null ;

                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");

                    boolean ajoutReussi = controleur.ajouterPorte(mousePoint,nomMur,listeMursDrawer);
                    System.out.println(ui.DrawingPanel.selectedAffichageVue);
                    System.out.println(ajoutReussi);
                    System.out.println("ajoutPortereussi");
                    isAddingPorte = false;
                    //ui.Chaletdrawer.changerVue(ui.DrawingPanel.selectedAffichageVue);
                    DrawingPanel.repaint();

                }
                if (isAddingFenetre)
                {


                    String nomMur = String.valueOf(ui.DrawingPanel.selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0.2,"NORD");

                    Point mousePoint = e.getPoint();

                    boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mousePoint,nomMur,listeMursDrawer);
                    System.out.println(ui.DrawingPanel.selectedAffichageVue);

                    System.out.println(ajoutFenetrereussi);
                    System.out.println("ajoutFenetrereussi");
                    isAddingFenetre = false;
                    DrawingPanel.repaint();


                }
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
        MurPannelTabbedPaneFaçadeLabelLongeurTextField.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                //recoit la longueur de facade voulu et update le controleur
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
    }

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

