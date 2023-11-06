package domain;

import Utilitaires.PointDouble;
import Utilitaires.pointPouces;

import java.awt.*;
import java.util.List;

public class FenetreDTO {
    public List<pointPouces> sommetsFenetre;
    public Point mousePoint;

    public FenetreDTO(Fenetre bi){
        sommetsFenetre = bi.getSommetsFenetre();
        mousePoint = bi.getPoint();
    }

}
