package ui;

import domain.Controleur;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class DrawingPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    private Controleur controleur;
    private double zoomFactor = 1.0;


    public Dimension initialDimensionNonStatic = getPreferredSize();
    public int w = (int) initialDimensionNonStatic.getWidth();
    public int h = (int) initialDimensionNonStatic.getHeight();
    public Dimension initialDimensionReturn = new Dimension(w, h);

    /*public DrawingPanel() {
        controleur = new Controleur();
        setPreferredSize(new Dimension(1000, 1000));
    }*/

    public static Controleur.AffichageVue selectedAffichageVue;


    public DrawingPanel(MainWindow mainWindow) {
        selectedAffichageVue = Controleur.AffichageVue.SURPLOMB;

        this.mainWindow = mainWindow;
        controleur = new Controleur();
        setPreferredSize(new Dimension(500, 500));


//        addMouseWheelListener(new MouseWheelListener() {
//            //@Override
//            public void mouseWheelMoved(MouseWheelEvent e) {
//                int notches = e.getWheelRotation();
//                if (notches < 0) {
//                    zoomIn();
//                } else {
//                    zoomOut();
//                }
//            }
//        });
    }

    public static boolean changerVue(Controleur.AffichageVue selectedVue) {
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



    /*public void changerVue(Controleur.AffichageVue nouvelleVue)
    {
        selectedAffichageVue = nouvelleVue;
        repaint();
    } */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mainWindow != null) {


            if (selectedAffichageVue == Controleur.AffichageVue.SURPLOMB) {
                SurplombDrawer mainDrawer = new SurplombDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);
            }
            if (selectedAffichageVue == Controleur.AffichageVue.FACADE) {
                FacadeDrawer mainDrawer = new FacadeDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);
            }
            if (selectedAffichageVue == Controleur.AffichageVue.ARRIERE) {
                ArriereDrawer mainDrawer = new ArriereDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);
            }
            if (selectedAffichageVue == Controleur.AffichageVue.DROITE) {
                DroitDrawer mainDrawer = new DroitDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);
            }
            if (selectedAffichageVue == Controleur.AffichageVue.GAUCHE) {
                GaucheDrawer mainDrawer = new GaucheDrawer(controleur, getPreferredSize());
                mainDrawer.draw(g/*, vueSelecteur*/);
            }


        }
    }


    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void zoomIn() {
        float leZoom = controleur.getZoom();
        leZoom -= 0.01;
        controleur.setZoom(leZoom);
        revalidate();
        repaint();
    }

    public void zoomOut() {
        float leZoom = controleur.getZoom();
        leZoom += 0.01F;
        controleur.setZoom(leZoom);
        revalidate();
        repaint();
    }

//    public void applyZoom() {
//        if (zoomFactor < 0.1) {
//            zoomFactor = 0.1;
//        }
//
//        Dimension currentSize = getSize();
//        Point currentPosition = getParent().getMousePosition();
//
//        // Calculate the new size based on the zoom factor
//        int newWidth = (int) (1  * zoomFactor);
//        int newHeight = (int) (initialDimensionReturn.getHeight() * zoomFactor);
//        setPreferredSize(new Dimension(newWidth, newHeight));
//
//        // If there's a current position, adjust it based on the zoom factor
//        if (currentPosition != null) {
//            int deltaX = (int) ((currentSize.getWidth() - newWidth) * (currentPosition.getX() / currentSize.getWidth()));
//            int deltaY = (int) ((currentSize.getHeight() - newHeight) * (currentPosition.getY() / currentSize.getHeight()));
//            getParent().setComponentZOrder(this, 0); // Move the panel to the front
//            setLocation(getX() + deltaX, getY() + deltaY);
//        }
//
//
//            revalidate();
//            repaint();
//        }


    }

