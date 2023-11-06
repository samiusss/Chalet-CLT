package domain;

import java.util.List;

public class ChaletDTO {
    public static double largeurChalet = 10.0;
    public static double longueurChalet = 10.0;
    public static double hauteurMurs=8.0;
    public static double epaisseurChalet = 2.0;
    public static double angleToit;
    public static List<Mur> listeMurs;
    public static String orientationToit;

    public ChaletDTO(Chalet bi){
        largeurChalet = bi.getLargeurChalet();
        longueurChalet = bi.getLongueurChalet();
        hauteurMurs = bi.getHauteurMurs();
        epaisseurChalet = bi.getEpaisseurChalet();
        angleToit = bi.getAngleToit();
        listeMurs = bi.getListeMurs();
        orientationToit = bi.getOrientationToit();
    }
}
