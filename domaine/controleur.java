import java.awt.Point;
import java.util.List;

public class controleur {
    private Chalet chalet;
    private murDTO murSelectionne;
    private accessoireDTO AccessoireSelectionne;
    private float zoom;
    private float offset;

    public Controleur (Chalet chalet, murDTO murSelectionne, accessoireDTO AccessoireSelectionne,float zoom, float offset){
        this.chalet = chalet;
        this.murSelectionne = murSelectionne;
        this.AccessoireSelectionne = AccessoireSelectionne;
        this.zoom = zoom;
        this.offset = offset;
    }

    public Chalet getChalet() {
        return chalet;
    }

    public murDTO getMurSelectionne() {
        return murSelectionne;
    }

    public accessoireDTO getAccessoireSelectionne() {
        return AccessoireSelectionne;
    }
    public float getZoom () {
        return zoom;
    }

}
