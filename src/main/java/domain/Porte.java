package domain;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import Utilitaires.*;


public class Porte extends Accessoires implements Serializable {

    private List<pointPouces> sommetsPorte;
    public static Pouces PORTE_LARGEUR_STANDARD = new Pouces(15, 0, 1);
    public static Pouces PORTE_HAUTEUR_STANDARD = new Pouces(35, 0, 1);

    public Point mousePoint;

    public Porte(Point mousepoint,Pouces largeur, Pouces hauteur) {
        super(largeur, hauteur);
        this.mousePoint = mousepoint;
    }

    public Point getPoint() {
        return mousePoint;
    }

    public boolean modifierMousePoint(Point newMousePoint) {
        mousePoint = newMousePoint;
        return true;
    }

    public boolean modifierLargeur(Pouces valeur) {
        PORTE_LARGEUR_STANDARD = valeur;
        return true;
    }

    public boolean modifierHauteur(Pouces valeur) {
        PORTE_HAUTEUR_STANDARD = valeur;
        return true;
    }
    // version pas tout a fait finie reste a voir la convertion avec des pixels
    public void CreersommetPorte(){
        pointPouces pointPorteSupDroit = new pointPouces(getPointPouces(mousePoint).getX().addPouces(getLargeur().diviserPouces(2)),getPointPouces(mousePoint).getY().addPouces(getHauteur().diviserPouces(2)));
        pointPouces pointPorteSupGauche=new pointPouces(getPointPouces(mousePoint).getX().substractPouces(getLargeur().diviserPouces(2)),getPointPouces(mousePoint).getY().addPouces(getHauteur().diviserPouces(2)));
        pointPouces pointPorteInfGauche = new pointPouces(getPointPouces(mousePoint).getX().substractPouces(getLargeur().diviserPouces(2)),new Pouces(0, 0, 1));
        pointPouces pointPorteInfDroit = new pointPouces(getPointPouces(mousePoint).getX().addPouces(getLargeur().diviserPouces(2)),new Pouces(0,0,1));
        sommetsPorte.add(pointPorteSupDroit);
        sommetsPorte.add(pointPorteSupGauche);
        sommetsPorte.add(pointPorteInfGauche);
        sommetsPorte.add(pointPorteInfDroit);

        }

    }

