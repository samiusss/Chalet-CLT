package main.java.Utilitaires;

public class Pouces {
    private int numerateur;
    private int denominateur;
    private int valeurEntiere;

    public Pouces(int numerateur, int denominateur) {
        this.numerateur = numerateur;
        this.denominateur = denominateur;
        recalculerValeurEntiere();
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

    public void setNumerateur(int numerateur) {
        this.numerateur = numerateur;
        recalculerValeurEntiere();
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
        recalculerValeurEntiere();
    }

    private void recalculerValeurEntiere() {
        this.valeurEntiere = numerateur / denominateur;
    }

    @Override
    public String toString() {
        return numerateur + "/" + denominateur + " (" + valeurEntiere + ")";
    }


    // Méthode pour calculer la distance en pouces


    // Méthode pour additionner deux objets Pouces
    public Pouces additionner(Pouces autrePouces) {
        int nouveauNumerateur = (this.numerateur * autrePouces.denominateur)
                + (autrePouces.numerateur * this.denominateur);
        int nouveauDenominateur = this.denominateur * autrePouces.denominateur;
        return new Pouces(nouveauNumerateur, nouveauDenominateur);
    }

    // Méthode pour soustraire deux objets Pouces
    public Pouces soustraire(Pouces autrePouces) {
        int nouveauNumerateur = (this.numerateur * autrePouces.denominateur)
                - (autrePouces.numerateur * this.denominateur);
        int nouveauDenominateur = this.denominateur * autrePouces.denominateur;
        return new Pouces(nouveauNumerateur, nouveauDenominateur);
    }

    // Méthode pour multiplier deux objets Pouces
    public Pouces multiplier(Pouces autrePouces) {
        int nouveauNumerateur = this.numerateur * autrePouces.numerateur;
        int nouveauDenominateur = this.denominateur * autrePouces.denominateur;
        return new Pouces(nouveauNumerateur, nouveauDenominateur);
    }

    // Méthode pour diviser deux objets Pouces
    public Pouces diviser(Pouces autrePouces) {
        int nouveauNumerateur = this.numerateur * autrePouces.denominateur;
        int nouveauDenominateur = this.denominateur * autrePouces.numerateur;
        return new Pouces(nouveauNumerateur, nouveauDenominateur);
    }
}

