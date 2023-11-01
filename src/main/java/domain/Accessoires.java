package domain;
import java.awt.Point;

public abstract class Accessoires{
    private Point point;
    private String AID;

    public Accessoires(Point point, String AID){
        this.point = point;
        this.AID = AID;
    }
    public String getAID() {
        return AID;
    }
    public Point getPoint() {
        return point;
    }
}