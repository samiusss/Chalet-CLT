package domain;
import Utilitaires.*;
import java.awt.Point;
import java.io.Serializable;
import java.util.UUID;


public abstract class Accessoires implements Serializable {
    private final UUID AID;
    private Point mousepoint;
    public Pouces largeur;
    public Pouces hauteur;

    public Accessoires() {
        this.AID = UUID.randomUUID();
    }
    public UUID getIdAccessoire() {
        return AID;
    }

    public pointPouces getPointPouces(Point lepoint) {
        Pouces xPouces = ConvertisseurMesures.convertirPixelsEnPouces(lepoint.x);
        Pouces yPouces = ConvertisseurMesures.convertirPixelsEnPouces(lepoint.y);
        return new pointPouces (xPouces,yPouces);

    }


}

