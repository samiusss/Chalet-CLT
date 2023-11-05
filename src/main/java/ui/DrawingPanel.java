package ui;

import domain.Controleur;

import javax.swing.*;
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
        this.controleur = new Controleur();
        setPreferredSize(new Dimension(1000, 1000));


//        JPanel drawingPanel = mainWindow.getPannelAffichage();
//        drawingPanel.setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
//
//        int largeurPanel = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width * 0.85);
//        int preferredWidth = largeurPanel;
//        int preferredHeight = (int) (preferredWidth * 0.5);
//        drawingPanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
//
//        // drawingPanel.setPreferredSize(new Dimension(largeurPanel, 1));
//
//        //int hauteurPanel = (int) (largeurPanel * 0.5);
//        //initialDimension = new Dimension(largeurPanel, hauteurPanel);
//
//        drawingPanel.setVisible(true);


//        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
//        int largeurPanel = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*0.85);
//        setPreferredSize(new Dimension(largeurPanel,1));
//        setVisible(true);
//        int hauteurPanel = (int)(largeurPanel*0.5);
//        initialDimension = new Dimension(largeurPanel,hauteurPanel);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (mainWindow != null){
            Chaletdrawer mainDrawer = new Chaletdrawer(controleur,getInitialDimension());
            mainDrawer.draw(g);
        }
    }

        public MainWindow getMainWindow() {
        return mainWindow;
    }

        public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

        public Dimension getInitialDimension() {
        return getPreferredSize();
    }
};
