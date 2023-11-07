package ui;

import domain.Controleur;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class DrawingPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;


    private Controleur controleur;
    private double zoomFactor = 1.0;

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
//            @Override
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

    private void zoomIn() {
        zoomFactor += 0.1; // You can adjust the zoom level as needed
        applyZoom();
    }

    private void zoomOut() {
        zoomFactor -= 0.1; // You can adjust the zoom level as needed
        applyZoom();
    }

    private void applyZoom() {
        // Ensure the zoom factor is within a reasonable range (e.g., prevent negative zoom)
        if (zoomFactor < 0.1) {
            zoomFactor = 0.1;
        }

        // Update the size of the drawing
        int newWidth = (int) (getPreferredSize().getWidth() * zoomFactor);
        int newHeight = (int) (getPreferredSize().getHeight() * zoomFactor);
        setPreferredSize(new Dimension(newWidth, newHeight));

        // Repaint the drawing to reflect the changes
        revalidate();
        repaint();
    }


}
