package ui;

import domain.Controleur;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.Serializable;


public class DrawingPanel extends JPanel implements Serializable {

    public Dimension initialDimension;
    private MainWindow mainWindow;

    public DrawingPanel(){
    }
    Controleur controleur = new Controleur();
    public DrawingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        int largeurPanel = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*0.85);
        setPreferredSize(new Dimension(largeurPanel,1));
        setVisible(true);
        int hauteurPanel = (int)(largeurPanel*0.5);
        initialDimension = new Dimension(largeurPanel,hauteurPanel);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        if (mainWindow != null){
            super.paintComponent(g);
            Chaletdrawer mainDrawer = new Chaletdrawer(controleur,initialDimension);
            mainDrawer.draw(g);
        }
    }

    public MainWindow getMainWindow(){
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public Dimension getInitialDimension(){
        return initialDimension;
    }
}
