package domain;

import Utilitaires.Pouces;

import java.awt.*;
import java.util.UUID;

public class AccessoiresDTO {
    private UUID AID;
    /*private Point mousepoint;
    public Pouces largeur;
    public Pouces hauteur; */

    public AccessoiresDTO(Accessoires bi){
        AID = bi.getIdAccessoire();
       /* mousepoint = bi.getPoint();
        largeur = bi.getLargeur();
        hauteur = bi.getHauteur(); */
    }
}
