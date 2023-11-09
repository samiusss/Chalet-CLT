package Utilitaires;

import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ConvertisseurMesures {



    public static double imperialToDoubleUniversel(String inputText) {
        String[] parts = inputText.split("\\s+");  // Divise la chaîne par des espaces

        double feet = 0;
        double inches = 0;
        double fraction = 0;

        for (String part : parts) {
            if (part.contains("'")) {
                // Trouvé la partie en pieds
                feet = Double.parseDouble(part.replace("'", ""));
            } else if (part.contains("\"")) {
                // Trouvé la partie en pouces
                inches = Double.parseDouble(part.replace("\"", ""));
            } else if (part.contains("/")) {
                // Trouvé la partie fractionnaire
                String[] fractionParts = part.split("/");
                if (fractionParts.length == 2) {
                    double numerator = Double.parseDouble(fractionParts[0]);
                    double denominator = Double.parseDouble(fractionParts[1]);
                    fraction = numerator / denominator;
                }
            }
        }

        // Conversion en pouces
        double totalInches = feet * 12 + inches + fraction;
        return totalInches;
    }

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
    public static double convertirPoucesEnDouble(Pouces pouces)
    {
        double total = (double) pouces.getValeurEntiere() + (double) pouces.getNumerateur() /(double) pouces.getDenominateur();
        return total;
    }

    public static int convertirPoucesEnInt(Pouces pouces)
    {
        int total = (int) pouces.getValeurEntiere() + (int) pouces.getNumerateur() /(int) pouces.getDenominateur();
        return total;
    }

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

    public static Pouces convertirStringImperialEnPouces(String valeurImperiale) {
        Pattern pattern = Pattern.compile("(\\d+)'? ?(\\d+)?\"?(?: ?(\\d+)/(\\d+))?");

        Matcher matcher = pattern.matcher(valeurImperiale);

        if (matcher.matches()) {
            int pieds = Integer.parseInt(matcher.group(1));
            int pouces = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
            int numerateur = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
            int denominateur = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 1;

            int valeurEntiere = pieds * 12 + pouces;
            return new Pouces(valeurEntiere, numerateur, denominateur);
        }

        return new Pouces(0,0,1); //Null
    }





}
