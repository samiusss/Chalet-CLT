package Utilitaires;

import java.io.Serializable;

public class Pouces implements Serializable {
    private int numerateur;
    private int denominateur;
    private int valeurEntiere;

    public Pouces(int valeurEntiere, int numerateur, int denominateur) {
        if (denominateur == 0)
        {
            throw new ArithmeticException("Le dénominateur de pouces ne peut pas être 0");
        }
        this.valeurEntiere = valeurEntiere;
        this.numerateur = numerateur;
        this.denominateur = denominateur;
    }

    public Pouces(Pouces autreValeur){
        this.valeurEntiere = autreValeur.valeurEntiere;
        this.numerateur = autreValeur.numerateur;
        this.denominateur = autreValeur.denominateur;
    }

    public int getNumerateur() {
        return numerateur;
    }

    public int getDenominateur() {
        return denominateur;
    }

    public int getValeurEntiere() {
        return valeurEntiere;
    }

    public int getCalcul() {
        return (valeurEntiere + (numerateur/denominateur));
    }

    public void setNumerateur(int numerateur) {
        this.numerateur = numerateur;
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
    }

    // Cette methode permet d additionner deux objets Pouces

    public Pouces addPouces (Pouces autreValeur) {
        int nouveauEntier = valeurEntiere +autreValeur.valeurEntiere;
        int nouveauNumerateur = numerateur + autreValeur.numerateur;
        int nouveauDenominateur = denominateur + autreValeur.denominateur;
        if (nouveauNumerateur < 0)
        {
            //cas ou la fraction serait négative
            nouveauNumerateur = nouveauEntier*nouveauDenominateur + nouveauNumerateur;
            Pouces resultat = new Pouces(0, nouveauNumerateur, nouveauDenominateur);
            resultat.reduire();
            return resultat;
        }
        Pouces resultat = new Pouces(nouveauEntier, nouveauNumerateur, nouveauDenominateur);
        resultat.reduire();
        return resultat;
    }



    // Cette methode permet de soustraire deux objets Pouces
    public Pouces substractPouces (Pouces autreValeur){
        int nouveauEntier = valeurEntiere - autreValeur.valeurEntiere;
        int nouveauNumerateur = numerateur*autreValeur.denominateur - autreValeur.numerateur*denominateur;
        int nouveauDenominateur = denominateur*autreValeur.denominateur;
        Pouces resultat = new Pouces(nouveauEntier, nouveauNumerateur, nouveauDenominateur);
        resultat.reduire();
        return resultat;
    }


    // Cette methode permet de determiner la valeur absolue d'un objets Pouces
    public Pouces absolue()
    {
        //On réduit au meme denominateur
        int nouveauNumerateur = valeurEntiere*denominateur + numerateur;
        //On multiplie par -1 pour rendre positive
        if (nouveauNumerateur <0 ) nouveauNumerateur *= -1;
        Pouces resultat = new Pouces(0, nouveauNumerateur, denominateur);
        resultat.reduire();
        return resultat;
    }

    // Cette methode permet de calculer la distance en pouces

    public Pouces distancePouces(Pouces autreValeur)
    {
        /*
        Contrairement à substractPouces, cette méthode retourne une distance
        Plus grande ou égale à 0
        */
        int nouveauEntier = valeurEntiere - autreValeur.valeurEntiere;
        int nouveauNumerateur = numerateur*autreValeur.denominateur - autreValeur.numerateur*denominateur;
        int nouveauDenominateur = denominateur*autreValeur.denominateur;
        Pouces resultat = new Pouces(nouveauEntier, nouveauNumerateur, nouveauDenominateur);
        resultat.reduire();

        if (resultat.valeurEntiere < 0 || resultat.numerateur < 0)
        {
            int numAbsolu = resultat.valeurEntiere*resultat.denominateur+resultat.numerateur;
            int denumAbsolu = resultat.denominateur;
            if (numAbsolu < 0) numAbsolu *=-1;
            resultat = new Pouces(0, numAbsolu, denumAbsolu);
            resultat.reduire();
        }

        return resultat;
    }



    // Cette methode permet de multiplier deux objets Pouces
    public void multiplierPouces (Pouces autreValeur)
    {
        valeurEntiere *= autreValeur.getValeurEntiere();
        numerateur *= autreValeur.getNumerateur();
        denominateur *= autreValeur.getDenominateur();
    }



    // Cette méthode permet de diviser deux objets Pouces
    public Pouces diviserPouces (int diviseur)
    {
        double nouvelEntier = (double) valeurEntiere/diviseur;
        int entierDivisee = (int) nouvelEntier;
        double reste = nouvelEntier - entierDivisee;
        if (reste != 0)
        {
            Pouces resultat = new Pouces(0, valeurEntiere, diviseur);
            Pouces nouvelleFraction = new Pouces(0, numerateur, denominateur*diviseur);
            Pouces total = resultat.addPouces(nouvelleFraction);
            total.reduire();
            return total;
        }
        else
        {
            Pouces resultat = new Pouces(entierDivisee, numerateur, denominateur*diviseur);
            resultat.reduire();
            return resultat;
        }
    }



    //Cette methode permet de verifier si un objet Pouces est null
    public boolean estNull()
    {
        boolean valeurNull = false;
        if (valeurEntiere == 0 && numerateur == 0 && denominateur == 1) {
            valeurNull = true;
        };
        return (valeurNull);
    }


    public void reduire() {
        while (numerateur % 2 == 0 && denominateur % 2 == 0) {
            numerateur = numerateur/2;
            denominateur = denominateur/2;
        }
        while (numerateur >= denominateur)
        {
            valeurEntiere = valeurEntiere + 1;
            numerateur = numerateur - denominateur;
        }
        if (numerateur == 0) {
            denominateur = 1;
        }
    }

    public boolean estPlusGrand(Pouces autreValeur) {
        double temp = (double)this.valeurEntiere + (double)this.numerateur/(double)this.denominateur;
        double autreDouble = (double)autreValeur.valeurEntiere + (double)autreValeur.numerateur/(double)autreValeur.denominateur;
        return temp>autreDouble;
    }

    public boolean estPlusGrandOuEgal(Pouces autreValeur)
    {
        double temp = (double)this.valeurEntiere + (double)this.numerateur/(double)this.denominateur;
        double autreDouble = (double)autreValeur.valeurEntiere + (double)autreValeur.numerateur/(double)autreValeur.denominateur;
        return temp>=autreDouble;
    }

    // Cette methodoes retournes les pouces sous forme de string
    @Override
    public String toString() {

        String poucesEnString = "";
        poucesEnString += Integer.toString(this.valeurEntiere);
        if (this.numerateur != 0)
        {
            poucesEnString += " + ";
            poucesEnString += Integer.toString(this.numerateur);
            poucesEnString += "/";
            poucesEnString += Integer.toString(this.denominateur);
        }
        return poucesEnString;
    }

    public double toDouble()
    {
        double total = (double) valeurEntiere + (double) numerateur/(double) denominateur;
        return total;
    }

}

