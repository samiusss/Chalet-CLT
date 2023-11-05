package Utilitaires;

import java.awt.Toolkit;


public class ConvertisseurMesures {

    public static Pouces convertirPixelsEnPouces(int pixels){
        final int DPI = Toolkit.getDefaultToolkit().getScreenResolution();
        double pouces = pixels/(DPI);
        int entier = (int) pouces;
        int[] fraction = decimalVersFraction(pouces-entier);
        return new Pouces(entier, fraction[0], fraction[1]);
    }

    public static int convertirPoucesEnPixels(Pouces pouces){
        final int DPI = Toolkit.getDefaultToolkit().getScreenResolution();
        double pixes = (double)(pouces.getValeurEntiere() +(pouces.getNumerateur()/pouces.getDenominateur())) * DPI;
        return (int) pixes;
    }

    public static Pouces convertirDoubleEnPouces(double pouces)
    {
        int entier = (int) pouces;
        int [] fraction = decimalVersFraction(pouces-entier);
        return new Pouces(entier, fraction[0], fraction[1]);
    }

    public static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        else if (b == 0)
            return a;
        if (a < b)
            return gcd(a, b % a);
        else
            return gcd(b, a % b);
    }

    // Fonction qui convertit decimal en fraction
    public static int[] decimalVersFraction(double number)
    {

        // Récupère la valeur intégrale de la décimale
        double intVal = Math.floor(number);

        // Récupère la partie fractionnaire de la décimale
        double fVal = number - intVal;

        // Considérez la valeur de précision pour
        // convertit une partie fractionnaire en
        // équivalent intégral
        final long pVal = 1000000000;

        // Calculer le PGCD de l'intégrale
        // équivalent de fractionnaire
        // valeur de la pièce et de la précision
        long gcdVal = gcd(Math.round(
                fVal * pVal), pVal);

        // Calculer numerateur and denominateur
        long num = Math.round(fVal * pVal) / gcdVal;
        long deno = pVal / gcdVal;

        int [] arr = {(int) num, (int) deno};
        for(int i: arr) {
            System.out.println(i);
        }
        return arr;


    }


}