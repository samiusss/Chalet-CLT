package domain;
import java.awt.Point;

public abstract class Accessoires{
    //private Point point;
    private String AID;

    public Accessoires(String AID){
        this.AID = AID;
    }
    public String getAID() {
        return AID;
    }

}