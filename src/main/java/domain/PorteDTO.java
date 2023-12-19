package domain;

import Utilitaires.pointPouces;

import java.awt.*;
import java.util.List;

public class PorteDTO {

    private final List<pointPouces> sommetsPorte;
    public Point mousePoint;

    public PorteDTO(Porte bi){
        sommetsPorte = bi.getSommetsPorte();
        mousePoint = bi.getPoint();
    }
}
