package domain;
import Utilitaires.PointDouble;
import domain.Mur;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Porte extends Accessoires{
    private double largeurporte;
    private double hauteurporte;



    public Porte(String AID, double largeurporte, double hauteurporte) {
        super(AID);
        this.largeurporte = largeurporte;
        this.hauteurporte = hauteurporte;
    }


}
