package domain;

import Utilitaires.PointDouble;

import java.util.List;

public class MurDTO {
    private final String nomMur;
    private final List<PointDouble> sommetsMur;
    private static List<String> accessoiresMur;
    private final List<Porte> porteMur;
    private final List<Fenetre> fenetreMur;

    public MurDTO(Mur bi){
        nomMur = bi.getNomMur();
        sommetsMur = bi.getSommetsMur();
        accessoiresMur = Mur.getAccessoiresMur();
        porteMur = bi.getListePorte();
        fenetreMur = bi.getListeFenetre();
    }
}
