package ui;

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
import java.util.Optional;

import static ui.MainWindow.mousePointClicked;


public class DrawingPanel extends JPanel implements Serializable {
    private int xOffsetDrag;
    private int yOffsetDrag;
    private int nombreFenetresSelectionnees = 0;

    private MainWindow mainWindow;
    public Controleur controleur;
    public Dimension initialDimensionNonStatic = getPreferredSize();
    public int w = (int) initialDimensionNonStatic.getWidth();
    public int h = (int) initialDimensionNonStatic.getHeight();
    public Dimension initialDimensionReturn = new Dimension(w, h);
    private double zoomFactor = 1.0;
    private boolean porteSelectionnee = false;
    private boolean fenetreSelectionnee = false;


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
            public void mousePressed(MouseEvent e) {
                if (!MainWindow.isAddingPorte) {
                    // Réinitialisez le décalage ici
                    setPorteSelectionnee(false);
                    setFenetreSelectionnee(false);
                    xOffsetDrag = 0;
                    yOffsetDrag = 0;
                    // Sélection de la porte
                    mousePointClicked = e.getPoint();
                    int adjustedX = (int) ((e.getX() - controleur.getOffsetX()) / controleur.getZoom());
                    int adjustedY = (int) ((e.getY() - controleur.getOffsetY()) / controleur.getZoom());
                    Point mousePoint = new Point(adjustedX, adjustedY);

                    Chalet chalet = controleur.getChaletProduction();
                    String nomMur = String.valueOf(selectedAffichageVue);
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");
                    setPorteSelectionnee(controleur.MethodeTest(nomMur, listeMursDrawer, mousePoint));
                }

                // Drag de la fenêtre
                if (!MainWindow.isAddingFenetre) {
                    setFenetreSelectionnee(false);

                    // Sélection de la fenêtre
                    mousePointClicked = e.getPoint();
                    int adjustedX = (int) ((e.getX() - controleur.getOffsetX()) / controleur.getZoom());
                    int adjustedY = (int) ((e.getY() - controleur.getOffsetY()) / controleur.getZoom());
                    Point mousePoint = new Point(adjustedX, adjustedY);

                    Chalet chalet = controleur.getChaletProduction();
                    String nomMur = String.valueOf(selectedAffichageVue);
                    List<Mur> listeMursDrawer = chalet.getMursUsines(0, "NORD");
                    setFenetreSelectionnee(controleur.MethodeTestFenetre(nomMur, listeMursDrawer, mousePoint));
                }
                if (isFenetreSelectionnee()) {
                    nombreFenetresSelectionnees++;
                    System.out.println("Nombre de fenêtres sélectionnées : " + nombreFenetresSelectionnees);
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!MainWindow.isAddingPorte) {
                    if (SwingUtilities.isLeftMouseButton(e) && isPorteSelectionnee()) {
                        System.out.println("Avant mise à jour : " + isPorteSelectionnee());
                        int newX = (int) ((e.getX() - controleur.getOffsetX()) / controleur.getZoom());
                        Chalet chalet = controleur.getChaletProduction();
                        chalet.modifierXporte(newX, selectedAffichageVue.toString(), initialDimensionReturn);
                        repaint();
                    }
                }
                if (!MainWindow.isAddingFenetre) {
                    if (SwingUtilities.isLeftMouseButton(e) && isFenetreSelectionnee()) {
                        System.out.println("Avant mise à jour : " + isFenetreSelectionnee());
                        int newX = (int) ((e.getX() - controleur.getOffsetX()) / controleur.getZoom());
                        int newY = (int) ((e.getY() - controleur.getOffsetY()) / controleur.getZoom());
                        Chalet chalet = controleur.getChaletProduction();
                        chalet.modifierXfenetre(newX, selectedAffichageVue.toString(), initialDimensionReturn);
                        chalet.modifierYfenetre(newY, selectedAffichageVue.toString(), initialDimensionReturn);
                        repaint();
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mousePointClicked = e.getPoint();
                Point Clicked = e.getPoint();

                Point t = MainWindow.mousePointClicked;
                System.out.println("MainWindows" + t);

                System.out.println("DrawinfPanel" + Clicked);

                int adjustedX = (int) ((e.getX() - controleur.getOffsetX()) / controleur.getZoom());
                int adjustedY = (int) ((e.getY() - controleur.getOffsetY()) / controleur.getZoom());
                Point mPoint = new Point(adjustedX, adjustedY);

                String nomMur = String.valueOf(selectedAffichageVue);
                Chalet chalet = controleur.getChaletProduction();
                List<Mur> listeMursDrawer = nomMur.equals("SURPLOMB") ?
                        chalet.getMursUsines(1700, "NORD") : chalet.getMursUsines(0, "NORD");

                Dimension intitalDimension = getPreferredSize();

                if (MainWindow.isAddingPorte) {
                    boolean ajoutReussi = controleur.ajouterPorte(mPoint, nomMur, listeMursDrawer, intitalDimension);
                    if (!ajoutReussi) {
                        JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    MainWindow.isAddingPorte = false;
                }

                if (MainWindow.isAddingFenetre && !MainWindow.isSelection) {
                    boolean ajoutFenetrereussi = Controleur.ajouterFenetre(mPoint, nomMur, listeMursDrawer, intitalDimension);
                    if (!ajoutFenetrereussi) {
                        JOptionPane.showMessageDialog(null, "Position Invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    MainWindow.isAddingFenetre = false;
                }

                repaint();
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

        }
    }


    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setPorteSelectionnee(boolean porteSelectionnee) {
        this.porteSelectionnee = porteSelectionnee;
    }

    public boolean isPorteSelectionnee() {
        return porteSelectionnee;
    }

    public void setFenetreSelectionnee(boolean fenetreSelectionnee) {
        this.fenetreSelectionnee = fenetreSelectionnee;
    }

    public boolean isFenetreSelectionnee() {
        return fenetreSelectionnee;
    }}


