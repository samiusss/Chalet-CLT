package domain;

public class Controleur {
    private Chalet chalet;
//    private murDTO murSelectionne;
//    private accessoireDTO AccessoireSelectionne;
    private float zoom;
    private float offset;

    //public enum typeAccessoire{
       // PORTE,FENETRE
   // }

   public Controleur (/*Chalet chalet,float zoom, float offset*/){
//       this.chalet = chalet;
//       this.zoom = zoom;
//       this.offset = offset;
    }

    public Chalet getChalet() {
        return chalet;
    }

//    public murDTO getMurSelectionne() {
//        return murSelectionne;
//    }
//
//    public accessoireDTO getAccessoireSelectionne() {
//        return AccessoireSelectionne;
//    }
    public float getZoom () {
        return zoom;
    }

    public float getOffset() {
        return offset;
    }

    //public Controleur(){
     // chalet = new Chalet();
//    }
//
//    private void ajouterFenetre (Point mousePoint,Pouces largeur, Pouces longueur, String AID)
//    {
//        Fenetre newFenetre = new Fenetre (mousePoint,largeur,longueur,AID);
//        // Chalet.add(newFenetre)
//    }

}
