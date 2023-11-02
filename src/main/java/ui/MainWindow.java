package main.java.ui;

import main.java.domain.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends javax.swing.JFrame {
    private Controleur controleur;
    private ApplicationMode actualMode;
    private JComboBox<String> vueComboBox;
    private JLabel titleLabel;

    // Liste des portes et accessoires
    private DefaultListModel<String> doorsAndWindowsModel;
    private JList<String> doorsAndWindowsList;
    private JScrollPane doorsAndWindowsScrollPane;
    private javax.swing.JPanel drawingPanel;
    private javax.swing.JPanel PanelOptions;
    public MainWindow() {
        controleur = new Controleur();
        initComponents();
        JPanel mainPanel = new JPanel(new GridBagLayout());

        titleLabel = new JLabel("CHALCLT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));

        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        gbcTitle.weightx = 1.0;
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(titleLabel, gbcTitle);

        GridBagConstraints gbcOptions = new GridBagConstraints();
        gbcOptions.gridx = 0;
        gbcOptions.gridy = 1;
        gbcOptions.gridwidth = 1;
        gbcOptions.weightx = 0.3;
        gbcOptions.weighty = 1.0;
        gbcOptions.fill = GridBagConstraints.BOTH;
        mainPanel.add(PanelOptions, gbcOptions);

        GridBagConstraints gbcList = new GridBagConstraints();
        gbcList.gridx = 1;
        gbcList.gridy = 1;
        gbcList.gridwidth = 1;
        gbcList.weightx = 0.7;
        gbcList.weighty = 1.0;
        gbcList.fill = GridBagConstraints.BOTH;
        mainPanel.add(doorsAndWindowsScrollPane, gbcList);

        GridBagConstraints gbcDrawing = new GridBagConstraints();
        gbcDrawing.gridx = 0;
        gbcDrawing.gridy = 2;
        gbcDrawing.gridwidth = 2;
        gbcDrawing.weightx = 1.0;
        gbcDrawing.weighty = 5.0;
        gbcDrawing.fill = GridBagConstraints.BOTH;
        mainPanel.add(drawingPanel, gbcDrawing);

        setContentPane(mainPanel);
        pack();
        setSize(1500, 900);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        drawingPanel = new main.java.ui.DrawingPanel(this);
        PanelOptions = new JPanel();

        String[] vues = {"Vue de facade", "Vue d'arriere", "Vue de droite", "Vue de gauche", "Vue par-dessus"};
        vueComboBox = new JComboBox<>(vues);

        PanelOptions.add(vueComboBox);

        // Bouton pour ajouter une porte
        JButton addDoorButton = new JButton("Ajouter porte");
        addDoorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajouter une porte
                doorsAndWindowsModel.addElement("Porte");
            }
        });
        PanelOptions.add(addDoorButton);

        // Bouton pour ajouter une fenêtre
        JButton addWindowButton = new JButton("Ajouter fenêtre");
        addWindowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajouter une fenêtre
                doorsAndWindowsModel.addElement("Fenêtre");
            }
        });
        PanelOptions.add(addWindowButton);

        // Bouton pour supprimer l'élément sélectionné dans la liste
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = doorsAndWindowsList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    doorsAndWindowsModel.remove(selectedIndex);
                }
            }
        });
        PanelOptions.add(deleteButton);

        PanelOptions.setLayout(new GridLayout(0, 1));  // Affiche les composants en colonne

        // Liste des portes et accessoires
        doorsAndWindowsModel = new DefaultListModel<>();
        doorsAndWindowsList = new JList<>(doorsAndWindowsModel);
        doorsAndWindowsScrollPane = new JScrollPane(doorsAndWindowsList);
    }

    public enum ApplicationMode {
        SELECT, ADD
    }


}
