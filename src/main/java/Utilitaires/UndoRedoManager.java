package Utilitaires;
import domain.Chalet;
import domain.Fenetre;
import domain.Mur;
import domain.Porte;
import java.util.LinkedList;
import java.util.List;
public class UndoRedoManager {



        public static class CopieChaletUR {
            public double largeurChalet;
            public double longueurChalet;
            public double hauteurMurs;
            public double epaisseurChalet;
            public double angleToit;
            public double retraitChalet;
            public List<Mur> listeMurs;
            public String orientationToit;
            public double zoom;
            public float offsetX;
            public float offsetY;
            public double grilleP;
        }
        public static List<CopieChaletUR> listeVersionsChalets = new LinkedList<>();
        public static int compteurNbrUndoRedo = 0 ;
        public static int compteurUndoRedo;

        public static CopieChaletUR undo()
        {
            if(compteurNbrUndoRedo == 0){
                compteurUndoRedo = listeVersionsChalets.size() -1;
            }
            compteurNbrUndoRedo = compteurNbrUndoRedo + 1;
            if(compteurUndoRedo == 0){
                compteurUndoRedo = 0;
                CopieChaletUR chaletDuUNDO = listeVersionsChalets.get(compteurUndoRedo);
                return chaletDuUNDO;
            }
            if (listeVersionsChalets.size()==1)
            {
                compteurUndoRedo = 0;
                CopieChaletUR chaletDuUNDO = listeVersionsChalets.get(compteurUndoRedo);
                return chaletDuUNDO;
            }
            else {
                compteurUndoRedo -= 1;
                CopieChaletUR chaletDuUNDO = listeVersionsChalets.get(compteurUndoRedo);
                return chaletDuUNDO;
            }
        }

        public static CopieChaletUR redo()
        {
            if(compteurNbrUndoRedo == 0){
                compteurUndoRedo = listeVersionsChalets.size() -1;
            }
            compteurNbrUndoRedo = compteurNbrUndoRedo + 1;
            if(compteurUndoRedo >= listeVersionsChalets.size()-1){
                compteurUndoRedo = listeVersionsChalets.size()-1;
                CopieChaletUR chaletDuREDO = listeVersionsChalets.get(compteurUndoRedo);
                return chaletDuREDO;
            }
            if (listeVersionsChalets.size()==1)
            {
                compteurUndoRedo = 0;
                CopieChaletUR chaletDuREDO = listeVersionsChalets.get(compteurUndoRedo);
                return chaletDuREDO;
            }
            else {
                compteurUndoRedo += 1;
                CopieChaletUR chaletDuREDO = listeVersionsChalets.get(compteurUndoRedo);
                return chaletDuREDO;
            }
        }

        public static CopieChaletUR versionURCHALET() {
            //Crée un nouveau CopieCHaletUR et récupère les informations
            CopieChaletUR copieChalet = new CopieChaletUR();
            copieChalet.largeurChalet = Chalet.largeurChalet;
            copieChalet.longueurChalet = Chalet.longueurChalet;
            copieChalet.hauteurMurs = Chalet.hauteurMurs;
            copieChalet.epaisseurChalet = Chalet.epaisseurChalet;
            copieChalet.angleToit = Chalet.angleToit;
            copieChalet.retraitChalet = Chalet.retraitChalet;
            copieChalet.listeMurs = Chalet.listeMurs;
            copieChalet.orientationToit = Chalet.orientationToit;
            copieChalet.zoom = Chalet.zoom;
            copieChalet.offsetX = Chalet.offsetX;
            copieChalet.offsetY = Chalet.offsetY;
            copieChalet.grilleP = Chalet.grilleP;
            List<Porte> listePorteDAVANTf = new LinkedList<>();
            List<Porte> listePorteDAVANTa = new LinkedList<>();
            List<Porte> listePorteDAVANTg = new LinkedList<>();
            List<Porte> listePorteDAVANTd = new LinkedList<>();
            List<Fenetre> listeFenetreDAVANTf = new LinkedList<>();
            List<Fenetre> listeFenetreDAVANTa = new LinkedList<>();
            List<Fenetre> listeFenetreDAVANTg = new LinkedList<>();
            List<Fenetre> listeFenetreDAVANTd = new LinkedList<>();

            List<Mur> listeMur = copieChalet.listeMurs;
            if (!listeMur.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    Mur mur = listeMur.get(i);
                    List<Porte> listePorte = mur.getListePorte();
                    List<Fenetre> listeFenetre = mur.getListeFenetre();
                    if (i == 0) {
                        listePorteDAVANTf = listePorte;
                        listeFenetreDAVANTf = listeFenetre;
                    } else if (i == 1) {
                        listePorteDAVANTa = listePorte;
                        listeFenetreDAVANTa = listeFenetre;
                    } else if (i == 2) {
                        listePorteDAVANTg = listePorte;
                        listeFenetreDAVANTg = listeFenetre;
                    } else if (i == 3) {
                        listePorteDAVANTd = listePorte;
                        listeFenetreDAVANTd = listeFenetre;
                    }
                }
            }
            copieChalet.listeMurs.get(0).porteMur = listePorteDAVANTf;
            copieChalet.listeMurs.get(1).porteMur = listePorteDAVANTa;
            copieChalet.listeMurs.get(2).porteMur = listePorteDAVANTg;
            copieChalet.listeMurs.get(3).porteMur = listePorteDAVANTd;
            copieChalet.listeMurs.get(0).fenetreMur = listeFenetreDAVANTf;
            copieChalet.listeMurs.get(1).fenetreMur = listeFenetreDAVANTa;
            copieChalet.listeMurs.get(2).fenetreMur = listeFenetreDAVANTg;
            copieChalet.listeMurs.get(3).fenetreMur = listeFenetreDAVANTd;

            listeVersionsChalets.add(copieChalet);
            System.out.printf("Les données viennent d'être copié, voici la liste: "+listeVersionsChalets);
            return copieChalet;
        }
        public static CopieChaletUR CopieChaletVersion1() {
            //Crée un nouveau CopieChaletUR et récupère les informations
            CopieChaletUR copieChalet = new CopieChaletUR();
            copieChalet.largeurChalet = Chalet.largeurChalet;
            copieChalet.longueurChalet = Chalet.longueurChalet;
            copieChalet.hauteurMurs = Chalet.hauteurMurs;
            copieChalet.epaisseurChalet = Chalet.epaisseurChalet;
            copieChalet.angleToit = Chalet.angleToit;
            copieChalet.retraitChalet = Chalet.retraitChalet;
            copieChalet.listeMurs = Chalet.listeMurs;
            copieChalet.orientationToit = Chalet.orientationToit;
            copieChalet.zoom = Chalet.zoom;
            copieChalet.offsetX = Chalet.offsetX;
            copieChalet.offsetY = Chalet.offsetY;
            copieChalet.grilleP = Chalet.grilleP;
            //CopieChaletUR chaletV1 = new CopieChaletUR(copieChalet.largeurChalet, copieChalet.longueurChalet, copieChalet.epaisseurChalet, copieChalet.angleToit,
            //copieChalet.hauteurMurs, copieChalet.listeMurs, copieChalet.orientationToit);

            listeVersionsChalets.add(copieChalet);
            System.out.printf("Le chalet de base est copié dans la liste : "+listeVersionsChalets);
            return copieChalet;
        }

    }

