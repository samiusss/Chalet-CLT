package Utilitaires;

import java.awt.*;

public class Rectangle {
    Point pointSuperieurGauche ;

    Point mousePoint ;

    float length;
    float height;

    boolean accessoires = false;

    public Rectangle(Point pointSuperieurGauche , float length, float height) {
        this.pointSuperieurGauche  = pointSuperieurGauche ;
        this.length = length;
        this.height = height;
    }


    // Méthode pour afficher les informations du rectangle
    public void afficherInformations() {
        System.out.println("Point Supérieur Gauche: " + pointSuperieurGauche);
        System.out.println("Longueur: " + length);
        System.out.println("Hauteur: " + height);
    }


}