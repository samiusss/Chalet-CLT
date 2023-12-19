package Utilitaires;

import java.awt.*;

public class PointDouble extends Point implements java.io.Serializable{
    private double x;
    private double y;
    private PointDouble(){
        super();
        this.x = 0.0;
        this.y = 0.0;
    }
    private static final PointDouble instance = new PointDouble(); // c'est le GRASP Singleton
    public static PointDouble getInstance(){
        return instance;
    }

    public PointDouble(double x, double y){
        super((int)x,(int) y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void setLocation(double x, double y) {
        super.setLocation((int)x, (int)y);
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX(){
        return this.x;
    }

    @Override
    public double getY(){
        return this.y;
    }

    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

}
