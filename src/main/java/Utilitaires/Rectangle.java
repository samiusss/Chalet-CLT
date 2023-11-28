package Utilitaires;

import java.awt.Point;

public class Rectangle {
    public Point pointSuperieurGauche;
    public Point mousePoint;
    public float length;
    public float height;

    public Rectangle(Point pointSuperieurGauche, float length, float height) {
        this.pointSuperieurGauche = pointSuperieurGauche;
        this.length = length;
        this.height = height;
    }

    // Méthode pour afficher les informations du rectangle
    public void afficherInformations() {
        System.out.println("Point Supérieur Gauche: " + pointSuperieurGauche);
        System.out.println("Longueur: " + length);
        System.out.println("Hauteur: " + height);
    }

    // Méthode pour calculer l'intersection avec un autre rectangle
    public Rectangle intersection(Rectangle r) {
        int x = Math.max((int) pointSuperieurGauche.getX(), (int) r.pointSuperieurGauche.getX());
        int y = Math.max((int) pointSuperieurGauche.getY(), (int) r.pointSuperieurGauche.getY());
        int width = Math.min((int) (pointSuperieurGauche.getX() + length), (int) (r.pointSuperieurGauche.getX() + r.length)) - x;
        int height = Math.min((int) (pointSuperieurGauche.getY() + this.height), (int) (r.pointSuperieurGauche.getY() + r.height)) - y;

        if (width > 0 && height > 0) {
            return new Rectangle(new Point(x, y), width, height);
        } else {
            return new Rectangle(new Point(0,0),0,0);
        }
    }

    // Méthode pour vérifier si ce rectangle contient un autre rectangle
    public boolean contains(Rectangle r) {
        return this.pointSuperieurGauche.getX() <= r.pointSuperieurGauche.getX() &&
                this.pointSuperieurGauche.getY() <= r.pointSuperieurGauche.getY() &&
                (this.pointSuperieurGauche.getX() + this.length) >= (r.pointSuperieurGauche.getX() + r.length) &&
                (this.pointSuperieurGauche.getY() + this.height) >= (r.pointSuperieurGauche.getY() + r.height);
    }

    // Méthode pour vérifier si le rectangle est vide
    public boolean isEmpty() {
        return this.length <= 0 || this.height <= 0;
    }
}
