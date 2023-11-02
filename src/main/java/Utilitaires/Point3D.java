package main.java.Utilitaires;

public class Point3D {
    private double longueurMur;
    private double hauteurMur;
    private double epaisseurMur;

    private Point3D(double longueurMur, double hauteurMur, double epaisseurMur) {
        this.longueurMur = longueurMur;
        this.hauteurMur = hauteurMur;
        this.epaisseurMur = epaisseurMur;
    }

    public static Point3D create(double longueurMur, double hauteurMur, double epaisseurMur) {
        return new Point3D(longueurMur, hauteurMur, epaisseurMur);
    }

    public double getLongueurMur() {
        return longueurMur;
    }

    public double getHauteurMur() {
        return hauteurMur;
    }

    public double getEpaisseurMur() {
        return epaisseurMur;
    }

    @Override
    public String toString() {
        return "(" + longueurMur + ", " + hauteurMur + ", " + epaisseurMur + ")";
    }
}
