package ui;

import domain.Controleur;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class DrawingPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    private Controleur controleur;

    /*public DrawingPanel() {
        controleur = new Controleur();
        setPreferredSize(new Dimension(1000, 1000));
    }*/

    public DrawingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        controleur = new Controleur();
        setPreferredSize(new Dimension(700, 700));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mainWindow != null) {
            Chaletdrawer mainDrawer = new Chaletdrawer(controleur, getPreferredSize());
            mainDrawer.draw(g);
        }
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
