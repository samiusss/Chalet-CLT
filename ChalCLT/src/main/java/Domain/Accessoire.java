package Domain;
import java.awt.Point;

public abstract class Accessoires{
    private Point point;
    private String AID;

    public Accessoires(Point point, string AID){
        this.point = point;
        this.id_accessoire = AID;
    }
    public string getAID() {
        return AID;
    }
    public Point getPoint() {
        return point;
    }
}