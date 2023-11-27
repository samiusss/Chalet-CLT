package ui;

import domain.ChaletDTO;
import domain.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.io.Serializable;


public class DrawingPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    public Controleur controleur;
    private double zoomFactor = 1.0;


    public Dimension initialDimensionNonStatic = getPreferredSize();
    public int w = (int) initialDimensionNonStatic.getWidth();
    public int h = (int) initialDimensionNonStatic.getHeight();
    public Dimension initialDimensionReturn = new Dimension(w, h);

    /*public DrawingPanel() {
        controleur = new Controleur();
        setPreferredSize(new Dimension(1000, 1000));
    }*/

    public static ChaletDTO.AffichageVue selectedAffichageVue;



    public DrawingPanel(MainWindow mainWindow) {
        selectedAffichageVue = ChaletDTO.AffichageVue.SURPLOMB;

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



    /*public void changerVue(Controleur.AffichageVue nouvelleVue)
    {
        selectedAffichageVue = nouvelleVue;
        repaint();
    } */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(controleur.getOffsetX(), controleur.getOffsetY());
        transform.scale(zoomFactor, zoomFactor);
        g2.transform(transform);

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

//    public void zoomIn() {
//        float leZoom = controleur.getZoom();
//        leZoom -= 0.05;
//        if (leZoom < 0)
//        {
//            leZoom = 0;
//        }
//        controleur.setZoom(leZoom);
//        revalidate();
//        repaint();
//    }
//
//    public void zoomOut() {
//        float leZoom = controleur.getZoom();
//        leZoom += 0.01F;
//        controleur.setZoom(leZoom);
//        revalidate();
//        repaint();
//    }

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

