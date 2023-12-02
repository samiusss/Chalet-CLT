package ui;

import Utilitaires.Pouces;
import domain.Chalet;
import domain.ChaletDTO;
import domain.Controleur;
import domain.Mur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.List;

import static Utilitaires.ConvertisseurMesures.*;
import static Utilitaires.ConvertisseurMesures.imperialToDoubleUniversel;
import static ui.MainWindow.*;


public class DrawingPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    public Controleur controleur;
    public Dimension initialDimensionNonStatic = getPreferredSize();
    public int w = (int) initialDimensionNonStatic.getWidth();
    public int h = (int) initialDimensionNonStatic.getHeight();
    public Dimension initialDimensionReturn = new Dimension(w, h);
    private double zoomFactor = 1.0;

    /*public DrawingPanel() {
        controleur = new Controleur();
        setPreferredSize(new Dimension(1000, 1000));
    }*/

    public static ChaletDTO.AffichageVue selectedAffichageVue;



    public DrawingPanel(final MainWindow mainWindow) {
        selectedAffichageVue = ChaletDTO.AffichageVue.SURPLOMB;

        this.mainWindow = mainWindow;
        controleur = new Controleur();
        setPreferredSize(new Dimension(500, 500));


        addMouseWheelListener(new MouseWheelListener() {
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mousePointClicked = e.getPoint();

                System.out.println(mousePointClicked);
                if (MainWindow.isAddingPorte)
                {
                    int adjustedX = (int)((e.getX() - controleur.getOffsetX()) / controleur.getZoom());
                    int adjustedY = (int)((e.getY() - controleur.getOffsetY()) / controleur.getZoom());
                    Point mPoint = new Point(adjustedX, adjustedY);
                    String nomMur = String.valueOf(selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    java.util.List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                    Dimension intitalDimension = getPreferredSize();
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutReussi = controleur.ajouterPorte(mPoint,nomMur,listeMursDrawer,intitalDimension);
                        if(ajoutReussi == false){
                            JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        repaint();

                    }
                    MainWindow.isAddingPorte = false;

                }
                if (MainWindow.isAddingFenetre && !MainWindow.isSelection)
                {

                    String nomMur = String.valueOf(selectedAffichageVue);
                    Chalet chalet = controleur.getChaletProduction();
                    List<Mur> listeMursDrawer = chalet.getMursUsines(1700,"NORD");
                    Point mousePoint = e.getPoint();
                    int adjustedX = (int)((e.getX() - controleur.getOffsetX()) / controleur.getZoom());
                    int adjustedY = (int)((e.getY() - controleur.getOffsetY()) / controleur.getZoom());
                    Point mPoint = new Point(adjustedX, adjustedY);
                    Dimension intitalDimension = getPreferredSize();
                    if(nomMur != "SURPLOMB") {
                        boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mPoint,nomMur,listeMursDrawer, intitalDimension);
                        if(ajoutFenetrereussi == false){
                            JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        repaint();
                    }
                    MainWindow.isAddingFenetre = false;
                }

            }
        });


    }

    public static boolean changerVue(ChaletDTO.AffichageVue selectedVue) {
        selectedAffichageVue = selectedVue;
        return true;
    }

    public Dimension getDimensionPanel() {
        return initialDimensionReturn;
    }

    public boolean repaintMode() {
        repaint();
        return true;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D topG = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(controleur.getOffsetX(), controleur.getOffsetY());
        transform.scale(1, 1);
        topG.transform(transform);

        if (mainWindow != null) {

            if (selectedAffichageVue == ChaletDTO.AffichageVue.SURPLOMB) {
                SurplombDrawer mainDrawer = new SurplombDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);

            }
            if (selectedAffichageVue == ChaletDTO.AffichageVue.FACADE) {
                FacadeDrawer mainDrawer = new FacadeDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);

            }
            if (selectedAffichageVue == ChaletDTO.AffichageVue.ARRIERE) {
                ArriereDrawer mainDrawer = new ArriereDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);

            }
            if (selectedAffichageVue == ChaletDTO.AffichageVue.DROITE) {
                DroitDrawer mainDrawer = new DroitDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);

            }
            if (selectedAffichageVue == ChaletDTO.AffichageVue.GAUCHE) {
                GaucheDrawer mainDrawer = new GaucheDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);
//                controleur.setZoom(1);
//                controleur.setOffsetX(100);
//                controleur.setOffsetY(170);
            }
   /*         mainWindow.Selection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(isSelection)
                    {
                        isSelection = false;
                        mainWindow.Selection.setBackground(UIManager.getColor("Button.background")); // Restaure la couleur par défaut du bouton
                    } else {
                        mousePointClicked = null;
                        isSelection = true;
                        Color rougeTresLeger = new Color(255, 200, 200);
                        mainWindow.Selection.setBackground(rougeTresLeger);
                    }
                }
            });
            mainWindow.PannelDroitAjoutPorteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainWindow.isAddingPorte = true;
                }
            });
            mainWindow.PannelDroitAjoutFenetreButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    MainWindow.isAddingFenetre = true;
                }
            });

            mainWindow.VueComboBox.addItemListener(new ItemListener() {
                private ChaletDTO.AffichageVue selectedVue;
                private String getSelectedVueOption() {
                    return (String) mainWindow.VueComboBox.getSelectedItem();
                }
                @Override
                public void itemStateChanged(ItemEvent e) {
                    int vueSelecteur;
                    String selectedOption = (String) mainWindow.VueComboBox.getSelectedItem();

                    if (selectedOption != null) {
                        switch (selectedOption) {
                            case "Facade":
                                //drawFacade();
                                this.setVue(ChaletDTO.AffichageVue.FACADE);
                                changerVue(selectedVue);
                                repaint();
                                break;
                            case "Arriere":
                                this.setVue(ChaletDTO.AffichageVue.ARRIERE);
                                changerVue(selectedVue);
                                repaint();
                                break;
                            case "Droit":
                                this.setVue(ChaletDTO.AffichageVue.DROITE);
                                changerVue(selectedVue);
                                repaint();
                                break;
                            case "Gauche":
                                this.setVue(ChaletDTO.AffichageVue.GAUCHE);
                                changerVue(selectedVue);
                                repaint();
                                break;
                            case "Surplomb":
                                this.setVue(ChaletDTO.AffichageVue.SURPLOMB);
                                changerVue(selectedVue);
                                repaint();
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
            mainWindow.AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.AccessoirePanelLargeurTextField.getText();
                }
            });

            mainWindow.AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.AccessoirePanelLargeurTextField.getText();
                }
            });

            mainWindow.ToitPaneltabbedPaneDroitPanelAngleTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneDroitPanelAngleTextField.getText();
                }
            });
            mainWindow.ToitPaneltabbedPaneDroitPanelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneDroitPanelHauteurTextField.getText();
                }
            });



            mainWindow.ToitPaneltabbedPaneGauchePanelAngleTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneGauchePanelAngleTextField.getText();
                }
            });

            mainWindow.ToitPaneltabbedPaneGauchePanelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneGauchePanelHauteurTextField.getText();
                }
            });

            mainWindow.ToitPaneltabbedPaneDevantPanelAngleTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneDevantPanelAngleTextField.getText();
                }
            });
            mainWindow.ToitPaneltabbedPaneDevantPanelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneDevantPanelHauteurTextField.getText();

                }
            });
            mainWindow.ToitPaneltabbedPaneDerrierePanelAngleTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneDerrierePanelAngleTextField.getText();
                }
            });
            mainWindow.ToitPaneltabbedPaneDerrierePanelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.ToitPaneltabbedPaneDerrierePanelHauteurTextField.getText();
                }
            });

            mainWindow.YporteField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                }
            });
            mainWindow.AccessoireLargeurPorteField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.AccessoireLargeurPorteField.getText();
                    Pouces nouvelleLargeur = convertirStringImperialEnPouces(inputText);

                    if (nouvelleLargeur != null) {

                        String nomMur = String.valueOf(selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        Dimension initialDimension = getPreferredSize();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                        boolean success = controleur.setLargeurPorte(nouvelleLargeur, nomMur, listeMursDrawer, initialDimension);
                        if(success == false){
                            JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println(selectedAffichageVue);
                        System.out.println(success);
                        System.out.println("ModificationPortereussi");
                        repaint();
                    }

                }
            });

            mainWindow.AccessoirePanelLongeurTextField.addActionListener(new ActionListener() {
                //LargeurFenetre
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Largeur
                    String inputText = mainWindow.AccessoirePanelLongeurTextField.getText();
                    //double nouvelleLargeurDouble = imperialToDoubleUniversel(inputText);
                    //Pouces nouvelleLargeur = convertirDoubleEnPouces(nouvelleLargeurDouble);
                    Pouces nouvelleLargeur = convertirStringImperialEnPouces(inputText);
                    System.out.println(nouvelleLargeur+" Largeur fenetre en pouces");
                    Dimension initialDimension = getPreferredSize();
                    mainWindow.XFenetreField.setText(String.valueOf(mousePointClicked.getY()));
                    mainWindow.YfenetreField.setText(String.valueOf(mousePointClicked.getY()));



                    if (nouvelleLargeur != null) {
                        if(mousePointClicked != null && isSelection) {
                            String nomMur = String.valueOf(selectedAffichageVue);
                            Chalet chalet = controleur.getChaletProduction();
                            List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");
                            boolean success = controleur.setLargeurFenetre(mousePointClicked,nouvelleLargeur, nomMur, listeMursDrawer,initialDimension);
                            if(success == false){
                                JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);

                            }
                            System.out.println(selectedAffichageVue);
                            System.out.println("ModificationLargeurFenetre" + success);
                            //System.out.println("ModificationLargeurFenetreReussi");
                            repaint();
                        }

                    }
                }
            });

            mainWindow.AccessoirePanelLargeurTextField.addActionListener(new ActionListener() {
                //HauteurFenetre
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Hauteurr
                    String inputText = mainWindow.AccessoirePanelLargeurTextField.getText();
                    //double nouvelleLongueurDouble = imperialToDoubleUniversel(inputText);
                    //Pouces nouvelleLongueur = convertirDoubleEnPouces(nouvelleLongueurDouble);
                    Pouces nouvelleLongueur = convertirStringImperialEnPouces(inputText);
                    System.out.println(nouvelleLongueur+" Hauteur fenetre en pouces");

                    if (nouvelleLongueur != null) {

                        if(mousePointClicked != null && isSelection) {
                            mainWindow.XFenetreField.setText(String.valueOf(mousePointClicked.getY()));
                            mainWindow.YfenetreField.setText(String.valueOf(mousePointClicked.getY()));


                            String nomMur = String.valueOf(selectedAffichageVue);
                            Chalet chalet = controleur.getChaletProduction();
                            Dimension initialDimension = getPreferredSize();
                            List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                            boolean success = controleur.setHauteurFenetre(mousePointClicked,nouvelleLongueur, nomMur, listeMursDrawer,initialDimension);
                            if(success == false){
                                JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                            System.out.println(selectedAffichageVue);
                            System.out.println(success);
                            System.out.println(mousePointClicked);
                            System.out.println("ModificationLongueurFenetreReussi");
                            repaint();

                        }

                    }
                }
            });

            mainWindow.AccessoineHauteurPortefield.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.AccessoineHauteurPortefield.getText();
                    Pouces nouvelleHauteur = convertirStringImperialEnPouces(inputText);
                    Dimension initialDimension = getPreferredSize();

                    if (nouvelleHauteur != null) {

                        String nomMur = String.valueOf(selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                        boolean success = controleur.setHauteurPorte(nouvelleHauteur, nomMur, listeMursDrawer,initialDimension);
                        if(success == false){
                            JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println(selectedAffichageVue);
                        System.out.println(success);
                        System.out.println("ModificationPortereussi");
                        repaint();

                    }
                }
            });

            // Modifier X Porte ---> EN int, les pouces semblent avoir un prob de conversion
            mainWindow.XPorteField.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.XPorteField.getText();
                    //int nouveauXPorte = Integer.parseInt(inputText);
                    Pouces nouveauXPorte = convertirStringImperialEnPouces(inputText);
                    Dimension initialDimension = getPreferredSize();
                    System.out.println(nouveauXPorte);
                    if (nouveauXPorte!=null){
                        System.out.println("Yes");
                        String nomMur = String.valueOf(selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                        // On convertir mon point pouces en Point int
                        int nouveauXporteint = convertirPoucesEnInt(nouveauXPorte);
                        //int nouveauXporteint = nouveauXPorte;
                        boolean xportemodifie = controleur.modifierXPorte(mousePointClicked, nouveauXporteint, nomMur, listeMursDrawer,initialDimension );
                        System.out.println(mousePointClicked);
                        if (xportemodifie == true) {
                            // Redessiner le panneau uniquement si la modification est réussie
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Une erreur s'est produite en essayant de modifier le X de la porte !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            mainWindow.XFenetreField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.XFenetreField.getText();
                    //int nouveauXFenetre = Integer.parseInt(inputText);
                    Pouces nouveauXFenetre = convertirStringImperialEnPouces(inputText);
                    Dimension initialDimension = getPreferredSize();
                    System.out.println(nouveauXFenetre);
                    if (nouveauXFenetre!=null&isSelection){
                        System.out.println("Yes");
                        String nomMur = String.valueOf(selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                        // On convertir mon point pouces en Point int
                        int nouveauXFenetreint = convertirPoucesEnInt(nouveauXFenetre);
                        //int nouveauXFenetreint = nouveauXFenetre;
                        boolean xFenetremodifie = controleur.modifierXFenetre(mousePointClicked, nouveauXFenetreint, nomMur, listeMursDrawer,initialDimension );
                        System.out.println(mousePointClicked);
                        if (xFenetremodifie == true) {
                            // Redessiner le panneau uniquement si la modification est réussie
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Une erreur s'est produite en essayant de modifier le X de la fenetre !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            mainWindow.YfenetreField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.YfenetreField.getText();
                    //int nouveauYFenetre = Integer.parseInt(inputText);
                    Pouces nouveauYFenetre = convertirStringImperialEnPouces(inputText);
                    Dimension initialDimension = getPreferredSize();
                    System.out.println(nouveauYFenetre);
                    if (nouveauYFenetre!=null&isSelection){
                        System.out.println("Yes");
                        String nomMur = String.valueOf(selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0,"NORD");
                        // On convertir mon point pouces en Point int
                        int nouveauYFenetreint = convertirPoucesEnInt(nouveauYFenetre);
                        //int nouveauYFenetreint = nouveauYFenetre;
                        boolean yFenetremodifie = controleur.modifierYFenetre(mousePointClicked, nouveauYFenetreint, nomMur, listeMursDrawer,initialDimension );
                        System.out.println(mousePointClicked);
                        if (yFenetremodifie == true) {
                            // Redessiner le panneau uniquement si la modification est réussie
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Une erreur s'est produite en essayant de modifier le Y de la fenetre !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            mainWindow.supprimmerLAccessoireButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //isSupprimer = true;
                    if (isAddingPorte) {
                        String nomMur = String.valueOf(selectedAffichageVue);
                        Chalet chalet = controleur.getChaletProduction();
                        List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");
                        if (nomMur != "SURPLOMB") {
                            boolean ajoutReussi = controleur.supprimerPorte(nomMur, listeMursDrawer);
                            System.out.println(selectedAffichageVue);
                            System.out.println(ajoutReussi);
                            System.out.println("ajoutPortereussi");
                            repaint();

                        }
                        isAddingPorte = false;
                        //isSupprimer = false;

                    }
                    if (isAddingFenetre && isSelection) {
                        if (mousePointClicked != null) {

                            String nomMur = String.valueOf(selectedAffichageVue);
                            Chalet chalet = controleur.getChaletProduction();
                            List<Mur> listeMursDrawer = chalet.getMursUsines(3.0, "NORD");
                            //Point mousePoint = e.getPoint();
                            if (nomMur != "SURPLOMB") {
                                boolean suppFenetrereussi = Controleur.supprimerFenetre(mousePointClicked, nomMur, listeMursDrawer);
                                System.out.println(selectedAffichageVue);
                                System.out.println(suppFenetrereussi);
                                System.out.println("suppFenetrereussi");
                                repaint();


                            }
                        } else {

                            String nomMur = String.valueOf(selectedAffichageVue);
                            Chalet chalet = controleur.getChaletProduction();
                            List<Mur> listeMursDrawer = chalet.getMursUsines(3.0, "NORD");
                            //Point mousePoint = e.getPoint();
                            if (nomMur != "SURPLOMB") {
                                boolean suppFenetrereussi = Controleur.supprimerToutesFenetre(nomMur, listeMursDrawer);
                                System.out.println(selectedAffichageVue);
                                System.out.println(suppFenetrereussi);
                                System.out.println("supToutesFenetres");
                                repaint();

                            }
                            isAddingFenetre = false;
                            //isSupprimer = false;
                        }


                    }
                }


            });

            mainWindow.MurPannelTabbedPaneFaçadeLabelLongueurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneFaçadeLabelLongueurTextField.getText();
                    //! MUR ARRIERE, LONGUEUR !\\

                    double longueurChaletMN= imperialToDoubleUniversel(inputText);
                    Controleur.setLongueurChalet(longueurChaletMN);
                    System.out.println(longueurChaletMN+" entered by you..");

                    revalidate();
                    repaint();
                }
            });


            mainWindow.MurPannelTabbedPaneDerriereLabelLongueurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneDerriereLabelLongueurTextField.getText();
                    //VÉRIFIER SI IL A APPUYER
                    double longueurChaletMN= imperialToDoubleUniversel(inputText);
                    Controleur.setLongueurChalet(longueurChaletMN);
                    System.out.println(longueurChaletMN+" entered by you..");
                    revalidate();
                    repaint();
                }
            });
            mainWindow.MurPannelTabbedPaneDroitLabelLongueurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneDroitLabelLongueurTextField.getText();

                    double largeurChaletMN= imperialToDoubleUniversel(inputText);
                    Controleur.setLargeurChalet(largeurChaletMN);
                    System.out.println(largeurChaletMN+" entered by you..");
                    revalidate();
                    repaint();
                }
            });
            mainWindow.MurPannelTabbedPaneGaucheLabelLongueurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneGaucheLabelLongueurTextField.getText();

                    double largeurChaletMN= imperialToDoubleUniversel(inputText);
                    Controleur.setLargeurChalet(largeurChaletMN);
                    System.out.println(largeurChaletMN+" entered by you..");
                    revalidate();
                    repaint();
                }
            });


            mainWindow.MurPannelTabbedPaneFaçadeLabelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneFaçadeLabelHauteurTextField.getText();
                    //VÉRIFIER SI IL A APPUYER
                    double hauteurMursMN= imperialToDoubleUniversel(inputText);
                    Controleur.setHauteurMurs(hauteurMursMN);
                    System.out.println(hauteurMursMN+" entered by you..");
                    revalidate();
                    repaint();

                }
            });
            mainWindow.MurPannelTabbedPaneDerriereLabelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneDerriereLabelHauteurTextField.getText();
                    //VÉRIFIER SI IL A APPUYER
                    double hauteurMursMN= imperialToDoubleUniversel(inputText);
                    Controleur.setHauteurMurs(hauteurMursMN);
                    System.out.println(hauteurMursMN+" entered by you..");
                    revalidate();
                    repaint();
                }
            });
            mainWindow.MurPannelTabbedPaneDroitLabelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneDroitLabelHauteurTextField.getText();
                    //VÉRIFIER SI IL A APPUYER
                    double hauteurMursMN= imperialToDoubleUniversel(inputText);
                    Controleur.setHauteurMurs(hauteurMursMN);
                    System.out.println(hauteurMursMN+" entered by you..");
                    revalidate();
                    repaint();
                }
            });
            mainWindow.MurPannelTabbedPaneGaucheLabelHauteurTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = mainWindow.MurPannelTabbedPaneGaucheLabelHauteurTextField.getText();
                    //VÉRIFIER SI IL A APPUYER
                    double hauteurMursMN= imperialToDoubleUniversel(inputText);
                    Controleur.setHauteurMurs(hauteurMursMN);
                    System.out.println(hauteurMursMN+" entered by you..");
                    revalidate();
                    repaint();
                }*/
           /* });*/

        }
    }


    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }



    }

