package domain;
import Utilitaires.*;
import java.util.UUID;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Fenetre extends Accessoires implements Serializable {

    private List<pointPouces> sommetsFenetre;

    public Point mousePoint;
    public static Pouces FENETRE_LARGEUR_STANDARD = new Pouces(10, 0, 1) ;
    public static Pouces FENETRE_HAUTEUR_STANDARD = new Pouces(10, 0, 1) ;

    public Fenetre(UUID AID,Point mousepoint, List<pointPouces> sommetsFenetre, Pouces largeur, Pouces hauteur) {
        super(AID,mousepoint, largeur, hauteur);
        this.sommetsFenetre = sommetsFenetre;
    }

    public Point getPoint() {
        return mousePoint;
    }

    public boolean modifierMousePoint(Point newMousePoint) {
        mousePoint = newMousePoint;
        return true;
    }

    public boolean modifierLargeur(Pouces valeur) {
        largeur = valeur;
        this.FENETRE_LARGEUR_STANDARD = valeur;

        return true;
    }
    public boolean modifierHauteur(Pouces valeur) {
        hauteur = valeur;
        this.FENETRE_HAUTEUR_STANDARD = valeur;
        return true;
    }
    // version pas tout a fait finie reste a voir la convertion avec des pixels
    public void CreersommetFenetre(){
        pointPouces pointFenetreSupDroit = new pointPouces(getPointPouces(mousePoint).getX().addPouces(getLargeur().diviserPouces(2)),getPointPouces(mousePoint).getY().addPouces(getHauteur().diviserPouces(2)));
        pointPouces pointFenetreSupGauche=new pointPouces(getPointPouces(mousePoint).getX().substractPouces(getLargeur().diviserPouces(2)),getPointPouces(mousePoint).getY().addPouces(getHauteur().diviserPouces(2)));
        pointPouces pointFenetreInfGauche = new pointPouces(getPointPouces(mousePoint).getX().substractPouces(getLargeur().diviserPouces(2)),getPointPouces(mousePoint).getY().substractPouces(getHauteur().diviserPouces(2)));
        pointPouces pointFenetreInfDroit = new pointPouces(getPointPouces(mousePoint).getX().addPouces(getLargeur().diviserPouces(2)),getPointPouces(mousePoint).getY().substractPouces(getHauteur().diviserPouces(2)));
        sommetsFenetre.add(pointFenetreSupDroit);
        sommetsFenetre.add(pointFenetreSupGauche);
        sommetsFenetre.add(pointFenetreInfGauche);
        sommetsFenetre.add(pointFenetreInfDroit);

    }

}
