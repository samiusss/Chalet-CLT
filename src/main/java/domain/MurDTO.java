package domain;

import Utilitaires.PointDouble;

import java.util.List;

public class MurDTO {
    private String nomMur;
    private List<PointDouble> sommetsMur;
    private static List<String> accessoiresMur;
    private List<Porte> porteMur;
    private List<Fenetre> fenetreMur;

    public MurDTO(Mur bi){
        nomMur = bi.getNomMur();
        sommetsMur = bi.getSommetsMur();
        accessoiresMur = bi.getAccessoiresMur();
        porteMur = bi.getListePorte();
        fenetreMur = bi.getListeFenetre();
    }
}
