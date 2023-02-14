package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import model.terrain.TerrainTypesCentre;
import model.terrain.TerrainTypesCotes;
import model.tuiles.*;
import model.zones.Abbaye;
import model.zones.Chateau;
import model.zones.Route;

public class Plateau extends Historique<Coup> implements Cloneable {
    int score1 = 0;
    int score2 = 0;
    public int nbPionsJoueur1 = 7;
    public int nbPionsJoueur2 = 7;
    Tuiles[][] terrain;
    int lignes = 62;
    int colonnes = 62;
    public boolean pasDeRotationLastTuile = false;
    ArrayList<Tuiles> tuilesPoses;
    public ArrayList<Tuiles> pioche;
    public ArrayList<Tuiles> defausse;
    TuilesDefinition jeu;
    public Tuiles tuilePioche;
    Random r = new Random();
    Chateau[] chateaux;
    Route[] routes;
    Abbaye[] abbayes;
    public ArrayList<Point> rotationActif;
    ArrayList<Tuiles> listAbbayes;
    Point rotationTuile;
    int indicerotation = 0;

    public int tour;

    public Plateau(TuilesDefinition jeu) throws CloneNotSupportedException {
        terrain = new Tuiles[lignes][colonnes];
        tuilesPoses = new ArrayList<>();
        listAbbayes = new ArrayList<>();
        pioche = jeu.getPioche();
        defausse = new ArrayList<>();
        initTuiles();
        this.jeu = jeu;
        chateaux = new Chateau[40];
        routes = new Route[40];
        abbayes = new Abbaye[20];
        rotationActif = new ArrayList<>();
    }

    public void repioche() {
        pioche.add(tuilePioche);
        int carte = r.nextInt(pioche.size());
        tuilePioche = pioche.get(carte);
        pioche.remove(tuilePioche);
    }

    public int getNbPions(int j) {
        switch (j) {
            case 1:
                return nbPionsJoueur1;
            case 2:
                return nbPionsJoueur2;
            default:
                break;
        }
        return -1;
    }

    public int getNbScore(int j) {
        switch (j) {
            case 1:
                return score1;
            case 2:
                return score2;
            default:
                break;
        }
        return -1;
    }

    public void updateChateaux() {
        for (int i = 0; i < chateaux.length; i++) {
            if (chateaux[i] == null) {
                break;
            } else {
                Chateau chateau = chateaux[i];
                if (chateau.terminer && !chateau.scorePrisEnCompte && chateau.getProprietaireList().size() > 0) {
                    int joueur = chateau.getProprio();
                    if (joueur != -1) {
                        if (joueur == 1) {
                            score1 += chateau.getChateauList().size() * 2;
                            nbPionsJoueur1 += chateau.getNbPionsJoueur1();
                        } else if (joueur == 2) {
                            score2 += chateau.getChateauList().size() * 2;
                            nbPionsJoueur2 += chateau.getNbPionsJoueur2();
                        } else if (joueur == 3) {
                            score1 += chateau.getChateauList().size();
                            score2 += chateau.getChateauList().size();
                            nbPionsJoueur1 += chateau.getNbPionsJoueur1();
                            nbPionsJoueur2 += chateau.getNbPionsJoueur2();
                        }
                        chateau.scorePrisEnCompte = true;
                    }
                }
            }
        }
    }

    public void updateChateauxFinJeu() {
        for (int i = 0; i < chateaux.length; i++) {
            if (chateaux[i] == null) {
                break;
            } else {
                Chateau chateau = chateaux[i];
                if (!chateau.terminer && chateau.getProprietaireList().size() > 0) {
                    int joueur = chateau.getProprio();
                    if (joueur != -1) {
                        if (joueur == 1) {
                            score1 += chateau.getChateauList().size();
                            nbPionsJoueur1 += chateau.getNbPionsJoueur1();
                        } else if (joueur == 2) {
                            score2 += chateau.getChateauList().size();
                            nbPionsJoueur2 += chateau.getNbPionsJoueur2();
                        } else if (joueur == 3) {
                            score1 += chateau.getChateauList().size();
                            score2 += chateau.getChateauList().size();
                            nbPionsJoueur1 += chateau.getNbPionsJoueur1();
                            nbPionsJoueur2 += chateau.getNbPionsJoueur2();
                        }
                        chateau.scorePrisEnCompte = true;
                    }
                }
            }
        }
    }

    public void updateRoute() {
        for (int i = 0; i < routes.length; i++) {
            if (routes[i] == null) {
                break;
            } else {
                Route route = routes[i];
                if (route.terminer && !route.scorePrisEnCompte && route.getProprietaireList().size() > 0) {
                    int joueur = route.getProprio();
                    if (joueur != -1) {
                        if (joueur == 1) {
                            score1 += route.getRouteList().size();
                            nbPionsJoueur1 += route.getNbPionsJoueur1();
                        } else if (joueur == 2) {
                            score2 += route.getRouteList().size();
                            nbPionsJoueur2 += route.getNbPionsJoueur2();
                        } else if (joueur == 3) {
                            score1 += route.getRouteList().size();
                            score2 += route.getRouteList().size();
                            nbPionsJoueur1 += route.getNbPionsJoueur1();
                            nbPionsJoueur2 += route.getNbPionsJoueur2();
                        }
                        route.scorePrisEnCompte = true;
                    }
                }
            }
        }
    }

    public void updateRouteFinJeu() {
        for (int i = 0; i < routes.length; i++) {
            if (routes[i] == null) {
                break;
            } else {
                Route route = routes[i];
                if (!route.terminer && route.getProprietaireList().size() > 0) {
                    int joueur = route.getProprio();
                    if (joueur != -1) {
                        if (joueur == 1) {
                            score1 += route.getRouteList().size();
                            nbPionsJoueur1 += route.getNbPionsJoueur1();
                        } else if (joueur == 2) {
                            score2 += route.getRouteList().size();
                            nbPionsJoueur2 += route.getNbPionsJoueur2();
                        } else if (joueur == 3) {
                            score1 += route.getRouteList().size();
                            score2 += route.getRouteList().size();
                            nbPionsJoueur1 += route.getNbPionsJoueur1();
                            nbPionsJoueur2 += route.getNbPionsJoueur2();
                        }
                        route.scorePrisEnCompte = true;
                    }
                }
            }
        }
    }

    public void updateAbbaye() {
        for (int i = 0; i < abbayes.length; i++) {
            if (abbayes[i] == null) {
                break;
            } else {
                Abbaye abbaye = abbayes[i];
                if (abbaye.terminer && !abbaye.scorePrisEnCompte && abbaye.getProprio() != -1) {
                    int joueur = abbaye.getProprio();
                    if (joueur != -1) {
                        if (joueur == 1) {
                            score1 += abbaye.getAbbaye().size();
                            nbPionsJoueur1 += 1;
                        } else {
                            score2 += abbaye.getAbbaye().size();
                            nbPionsJoueur2 += 1;
                        }
                        abbaye.scorePrisEnCompte = true;
                    }
                }
            }
        }
    }

    public void updateAbbayeFinJeu() {
        for (int i = 0; i < abbayes.length; i++) {
            if (abbayes[i] == null) {
                break;
            } else {
                Abbaye abbaye = abbayes[i];
                if (!abbaye.terminer && abbaye.getProprio() != -1) {
                    int joueur = abbaye.getProprio();
                    if (joueur != -1) {
                        if (joueur == 1) {
                            score1 += abbaye.getAbbaye().size();
                            nbPionsJoueur1 += 1;
                        } else {
                            score2 += abbaye.getAbbaye().size();
                            nbPionsJoueur2 += 1;
                        }
                        abbaye.scorePrisEnCompte = true;
                    }
                }
            }
        }
    }

    @Override
    public Plateau clone() {
        Plateau clone = null;
        try {
            clone = (Plateau) super.clone();
            clone.terrain = terrain.clone();

        } catch (CloneNotSupportedException e) {
            System.out.println("Bug interne avec le clone");
            System.exit(1);
        }
        return clone;
    }

    public void piocherCarte() { // Permet de piocher une carte à chaque tour
        int position = r.nextInt(3) + 1;
        defausse.add(tuilePioche);
        if (tuilesPoses.size() == 1) {
            tuilePioche = getTuilesPosable(position, tuilesPoses.get(0));
        } else {
            int carte = r.nextInt(pioche.size());
            tuilePioche = pioche.get(carte);
        }
        pioche.remove(tuilePioche);


    }

    public Tuiles getTuilesPosable(int position, Tuiles origine) {
        ArrayList<Tuiles> tuilesPosables = new ArrayList<>();
        for (Tuiles tuiles : pioche) {
            if (tuilePosable(origine, origine.i - 1, origine.j, position, tuiles)) {
                tuilesPosables.add(tuiles);
            }
        }
        if (tuilesPosables.size() == 0) {
            return getTuilesPosable(r.nextInt(3) + 1, origine);
        }
        int indice = r.nextInt(tuilesPosables.size());
        return tuilesPosables.get(indice);
    }


    public void initTuiles() { // Regarde si la tuile possède un chateau ou une route et update les booléens.
        for (Tuiles tuiles : pioche) {
            for (Tuiles tuilesRotations : tuiles.rotations) {
                initTuilesMethod(tuiles, tuilesRotations);
            }
            initTuilesMethod(tuiles, tuiles);

        }
    }

    private void initTuilesMethod(Tuiles tuiles, Tuiles tuilesRotations) {
        for (TerrainTypesCentre type : tuiles.centre) {

            if (type == TerrainTypesCentre.ROUTE) {
                tuilesRotations.possedeRoute = true;
            }
            if (type == TerrainTypesCentre.CHATEAUC) {
                tuilesRotations.possedeChateauCentre = true;
            }
        }
        for (TerrainTypesCotes type : tuiles.cotes) {
            if (type == TerrainTypesCotes.CHATEAU) {
                tuilesRotations.possedeChateauCote = true;
                break;
            }

        }
    }

    public Integer[] removePionEmplacementChateau(Tuiles tuile) {
        Integer[] indicePionsClone = tuile.indicePions.clone();
        int compteur = 0;
        for (TerrainTypesCotes tcotes : tuile.cotes) {
            if (tcotes == TerrainTypesCotes.CHATEAU) {
                indicePionsClone[compteur] = 0;
            }
            compteur++;
        }
        if (tuile.centre[4] == TerrainTypesCentre.CHATEAUC) {
            indicePionsClone[12] = 0;
        }
        return indicePionsClone;
    }

    public Integer[] removePionEmplacementRoute(Tuiles tuile) {
        Integer[] indicePionsClone = tuile.indicePions.clone();
        int compteur = 0;
        for (TerrainTypesCentre tcentre : tuile.centre) {
            if (tcentre == TerrainTypesCentre.ROUTE) {
                if (compteur < 5) {
                    indicePionsClone[compteur + 8] = 0;
                }
            }
            compteur++;
        }
        return indicePionsClone;
    }

    public void enlevePionScore(Tuiles tuile) {
        if (tuile.getPion() != null) {
            if (tuile.getPion().joueur == 1) {
                if (nbPionsJoueur1 > 0) {
                    nbPionsJoueur1 -= 1;
                }
            } else if (tuile.getPion().joueur == 2) {
                if (nbPionsJoueur2 > 0) {
                    nbPionsJoueur2 -= 1;
                }
            }
        }
    }

    public Tuiles setPionsEmplacement(Tuiles tuile) throws CloneNotSupportedException {
        ArrayList<Tuiles> voisinsList = adjChateau(tuile, tuile.i, tuile.j);
        for (Tuiles voisins : voisinsList) {
            for (int i = 0; i < chateaux.length; i++) {
                if (chateaux[i] != null) {
                    if (chateaux[i].contientTuile(voisins)) {
                        if (chateaux[i].getProprietaireList().size() != 0) {
                            tuile.indicePions = removePionEmplacementChateau(tuile);
                        }
                    }
                } else {
                    break;
                }
            }
        }
        ArrayList<Tuiles> voisinsListRoute = adjRoute(tuile, tuile.i, tuile.j);
        for (Tuiles voisins : voisinsListRoute) {
            for (int i = 0; i < routes.length; i++) {
                if (routes[i] != null) {
                    if (routes[i].contientTuile(voisins)) {
                        if (routes[i].getProprietaireList().size() != 0) {
                            tuile.indicePions = removePionEmplacementRoute(tuile);
                        }
                    }
                } else {
                    break;
                }
            }
        }
        for(int i=0;i<tuile.pionsEmplacement.length;i++){
            if(tuile.pionsEmplacement[i] != null){
                tuile.pionsEmplacement[i] = null;
            }
        }
        Integer[] listEmplacement = tuile.indicePions.clone();
        int j = 0;
        for(int i = 0;i<listEmplacement.length;i++){
            if(listEmplacement[i] == 1){
                tuile.pionsEmplacement[j] = new Pion(i);
                switch (i) {
                    case 0:
                    case 1:
                    case 8:
                        tuile.pionsEmplacement[j].setCoord(2F, 6F);
                        break;
                    case 2:
                    case 3:
                    case 9:
                        tuile.pionsEmplacement[j].setCoord(1.15F, 2F);
                        break;
                    case 4:
                    case 5:
                    case 10:
                        tuile.pionsEmplacement[j].setCoord(2F, 1.05F);
                        break;
                    case 6:
                    case 7:
                    case 11:
                        tuile.pionsEmplacement[j].setCoord(6F, 2F);
                        break;
                    case 12:
                        tuile.pionsEmplacement[j].setCoord(2F, 2F);
                        break;
                }
                j++;
            }
        }
        return tuile;
    }

    public boolean pionPoseSurChateau(Tuiles tuile) {
        if (tuile.getPion() != null) {
            if (tuile.getPion().indice == 12) {
                return tuile.centre[4] == TerrainTypesCentre.CHATEAUC;
            }
            if (tuile.getPion().indice >= 8) return false;
            return tuile.cotes[tuile.getPion().indice] == TerrainTypesCotes.CHATEAU;
        }
        return false;
    }

    public boolean pionPoseSurRoute(Tuiles tuile) {
        if (tuile.getPion() != null) {
            if (tuile.getPion().indice < 8 || tuile.getPion().indice == 12) return false;
            return tuile.centre[tuile.getPion().indice - 8] == TerrainTypesCentre.ROUTE;
        }
        return false;
    }

    public boolean pionPoseSurAbbaye(Tuiles tuile) {
        if (tuile.getPion() != null) {
            if (tuile.getPion().indice == 12) {
                return tuile.centre[4] == TerrainTypesCentre.MONASTERE;
            }
        }
        return false;
    }

    public void setTuile(Tuiles tuile, int i, int j) { // Fonction pour ajouter la tuile
        if (tuile != null) {
            terrain[i][j] = tuile;
            terrain[i][j].setCoord(i, j);

            Tuiles nouveau = null;
            try {
                nouveau = setPionsEmplacement(tuile);
            } catch (CloneNotSupportedException e) {
                System.out.println("Problème clonage suppression pions.");
            }
            terrain[i][j] = nouveau;
            tuilesPoses.add(nouveau);
        }
    }

    public void setTuileVeritable(Tuiles tuile, int i, int j) { // Fonction pour ajouter la tuile
        if (tuile != null) {
            terrain[i][j] = tuile;
            terrain[i][j].setCoord(i, j);
            tuilesPoses.add(tuile);
            if (tuile.possedeChateauCote) {
                ArrayList<Tuiles> list = adjChateau(tuile, i, j);
                if (list.size() == 1) {
                    Tuiles voisin = list.get(0);
                    int indice = positionTuilesChateauTableau(voisin);
                    if (indice != -1) {
                        chateaux[indice].addTuiles(tuile);
                        if (pionPoseSurChateau(tuile)) {
                            chateaux[indice].setProprietaire(tuile.getPion().joueur);
                            enlevePionScore(tuile);
                        }
                        if (chateauTerminer(indice)) {
                            chateaux[indice].terminer = true;
                            removePionChateauProjetTerminer(indice);
                        }
                    }

                } else if (list.size() > 1) {
                    ArrayList<Integer> indicesList = new ArrayList<>();
                    for (Tuiles tmp : list) {
                        int pos;
                        if ((pos = positionTuilesChateauTableau(tmp)) != -1) {
                            indicesList.add(pos);
                        }
                    }
                    ArrayList<Tuiles> indicesListFusion = new ArrayList<>();
                    ArrayList<Integer> indicesListFusionProprio = new ArrayList<>();
                    for (int k = 1; k < indicesList.size(); k++) {
                        indicesListFusion = (ArrayList<Tuiles>) chateaux[indicesList.get(k)].getChateauList().clone();
                        indicesListFusionProprio = (ArrayList<Integer>) chateaux[indicesList.get(k)].getProprietaireList().clone();
                        for (Tuiles tuiles : indicesListFusion) {
                            chateaux[indicesList.get(0)].addTuiles(tuiles);
                        }
                        for (Integer proprio : indicesListFusionProprio) {
                            chateaux[indicesList.get(0)].setProprietaire(proprio);
                        }
                        int w =0;
                        while(!chateaux[indicesList.get(k)].getChateauList().isEmpty()){
                            chateaux[indicesList.get(k)].getChateauList().remove(chateaux[indicesList.get(k)].getChateauList().get(w));
                        }
                        w = 0;
                        while(!chateaux[indicesList.get(k)].getProprietaireList().isEmpty()){
                            chateaux[indicesList.get(k)].getProprietaireList().remove(chateaux[indicesList.get(k)].getProprietaireList().get(w));
                        }/*
                        for (int w = 0; w < chateaux[indicesList.get(k)].getChateauList().size(); w++) {
                            chateaux[indicesList.get(k)].getChateauList().remove(chateaux[indicesList.get(k)].getChateauList().get(w));
                        }
                        for (int w = 0; w < chateaux[indicesList.get(k)].getProprietaireList().size(); w++) {
                            chateaux[indicesList.get(k)].getProprietaireList().remove(chateaux[indicesList.get(k)].getProprietaireList().get(w));
                        }*/
                    }
                    chateaux[indicesList.get(0)].addTuiles(tuile); //Ajouter la tuile posée au bon endroit
                    if (pionPoseSurChateau(tuile)) {
                        chateaux[indicesList.get(0)].setProprietaire(tuile.getPion().joueur);
                        enlevePionScore(tuile);
                    }

                    if (chateauTerminer(indicesList.get(0))) {
                        chateaux[indicesList.get(0)].terminer = true;
                        removePionChateauProjetTerminer(indicesList.get(0));
                    }
                } else if (list.size() == 0) {
                    int w = indiceValideChateau();
                    if (w >= 0) {
                        chateaux[w] = new Chateau();
                        chateaux[w].addTuiles(tuile);
                        if (pionPoseSurChateau(tuile)) {
                            chateaux[w].setProprietaire(tuile.getPion().joueur);
                            enlevePionScore(tuile);
                        }
                    }
                }

            }
            if (tuile.possedeRoute) {
                ArrayList<Tuiles> list = adjRoute(tuile, i, j);
                if (list.size() == 1) {
                    Tuiles voisin = list.get(0);
                    int indice = positionTuilesRoute(voisin);
                    if (indice != -1) {
                        if (pionPoseSurRoute(tuile)) {
                            routes[indice].setProprietaire(tuile.getPion().joueur);
                            enlevePionScore(tuile);
                        }
                        routes[indice].addTuiles(tuile);
                        if (routeTerminer(indice)) {
                            routes[indice].terminer = true;
                            removePionRouteProjetTerminer(indice);
                        }
                    }

                } else if (list.size() > 1) {
                    ArrayList<Integer> indicesList = new ArrayList<>();
                    for (Tuiles tmp : list) {
                        int pos;
                        if ((pos = positionTuilesRoute(tmp)) != -1) {
                            indicesList.add(pos);
                        }
                    }
                    ArrayList<Tuiles> indicesListFusion = new ArrayList<>();
                    ArrayList<Integer> indicesListFusionProprio = new ArrayList<>();
                    for (int k = 1; k < indicesList.size(); k++) {
                        indicesListFusion = (ArrayList<Tuiles>) routes[indicesList.get(k)].getRouteList().clone();
                        indicesListFusionProprio = (ArrayList<Integer>) routes[indicesList.get(k)].getProprietaireList().clone();
                        for (Tuiles tuiles : indicesListFusion) {
                            routes[indicesList.get(0)].addTuiles(tuiles);
                        }
                        for (Integer proprio : indicesListFusionProprio) {
                            routes[indicesList.get(0)].setProprietaire(proprio);
                        }
                        int w =0;
                        while(!routes[indicesList.get(k)].getRouteList().isEmpty()){
                            routes[indicesList.get(k)].getRouteList().remove(routes[indicesList.get(k)].getRouteList().get(w));
                            //w++;
                        }
                        w=0;
                        while(!routes[indicesList.get(k)].getProprietaireList().isEmpty()){
                            routes[indicesList.get(k)].getProprietaireList().remove(routes[indicesList.get(k)].getProprietaireList().get(w));
                            //w++;
                        }
                    }
                    routes[indicesList.get(0)].addTuiles(tuile);
                    if (pionPoseSurRoute(tuile)) {
                        routes[indicesList.get(0)].setProprietaire(tuile.getPion().joueur);
                        enlevePionScore(tuile);
                    }//Ajouter la tuile posée au bon endroit
                    if (routeTerminer(indicesList.get(0))) {
                        routes[indicesList.get(0)].terminer = true;
                        removePionRouteProjetTerminer(indicesList.get(0));
                    }
                } else if (list.size() == 0) {
                    int w = indiceValideRoute();
                    routes[w] = new Route();
                    routes[w].addTuiles(tuile);
                    if (pionPoseSurRoute(tuile)) {
                        routes[w].setProprietaire(tuile.getPion().joueur);
                        enlevePionScore(tuile);
                    }
                }
            }
            if (tuile.possedeAbbaye) {
                listAbbayes.add(tuile);
            }
            for (Tuiles tuileAbbaye : listAbbayes) {
                int pos;
                if ((pos = contientAbbaye(tuileAbbaye)) == -1) {
                    int indiceValide = indiceValideAbbaye();

                    abbayes[indiceValide] = new Abbaye();
                    abbayes[indiceValide].addTuiles(tuileAbbaye);
                    if (pionPoseSurAbbaye(tuileAbbaye)) {
                        abbayes[indiceValide].setProprietaire(tuileAbbaye.getPion().joueur);
                        enlevePionScore(tuileAbbaye);
                    }
                    ArrayList<Tuiles> voisinList = tuilesAdjAbbaye(tuileAbbaye);
                    for (Tuiles voisins : voisinList) {
                        abbayes[indiceValide].addTuiles(voisins);
                    }
                    if (abbayes[indiceValide].getAbbaye().size() == 9) {
                        abbayes[indiceValide].terminer = true;
                        updateAbbaye();
                    }
                } else {
                    ArrayList<Tuiles> voisinList = tuilesAdjAbbaye(tuileAbbaye);
                    for (Tuiles voisins : voisinList) {
                        if (!abbayes[pos].contientTuile(voisins)) {
                            abbayes[pos].addTuiles(voisins);
                        }
                    }
                    if (abbayes[pos].getAbbaye().size() == 9) {
                        abbayes[pos].terminer = true;
                        updateAbbaye();
                        abbayes[pos] = null;
                        tuileAbbaye.pion = null;
                    }
                }
            }
        }
        updateChateaux();
        updateRoute();
    }

    public void removePionChateauProjetTerminer(int indice) {
        for (int y = 0; y < chateaux[indice].getChateauList().size(); y++) {
            if (chateaux[indice].getChateauList().get(y).getPion() != null) {
                int ind = chateaux[indice].getChateauList().get(y).getPion().getIndice();
                if (ind == 12 || ind >= 0 && ind <= 7) {
                    chateaux[indice].getChateauList().get(y).setPion(null);
                }
            }
        }
    }

    public void removePionRouteProjetTerminer(int indice) {
        for (int y = 0; y < routes[indice].getRouteList().size(); y++) {
            if (routes[indice].getRouteList().get(y).getPion() != null) {
                int ind = routes[indice].getRouteList().get(y).getPion().getIndice();
                if (ind >= 8 && ind <= 11) {
                    routes[indice].getRouteList().get(y).setPion(null);
                }
            }
        }
    }

    public ArrayList<Tuiles> tuilesAdjAbbaye(Tuiles tuile) { // Fonction qui fournit les voisins d'une tuile
        ArrayList<Tuiles> listTuiles = new ArrayList<>();
        int i = tuile.i;
        int j = tuile.j;
        if (i == 0 || j == 0 || i == lignes - 1 || j == colonnes - 1) {
            System.out.println("tuile en dehors du plateau");
        } else {
            if (terrain[i - 1][j] != null) {
                listTuiles.add(terrain[i - 1][j]);
            }
            if (terrain[i][j + 1] != null) {
                listTuiles.add(terrain[i][j + 1]);
            }
            if (terrain[i + 1][j] != null) {
                listTuiles.add(terrain[i + 1][j]);
            }
            if (terrain[i][j - 1] != null) {
                listTuiles.add(terrain[i][j - 1]);
            }
            if (terrain[i - 1][j + 1] != null) {
                listTuiles.add(terrain[i - 1][j + 1]);
            }
            if (terrain[i + 1][j + 1] != null) {
                listTuiles.add(terrain[i + 1][j + 1]);
            }
            if (terrain[i + 1][j - 1] != null) {
                listTuiles.add(terrain[i + 1][j - 1]);
            }
            if (terrain[i - 1][j - 1] != null) {
                listTuiles.add(terrain[i - 1][j - 1]);
            }
        }
        return listTuiles;
    }

    public int indiceValideAbbaye() {
        int indice = 0;
        for (int i = 0; i < abbayes.length; i++) {
            if (abbayes[i] == null) {
                return i;
            }
        }
        return indice;
    }

    public int contientAbbaye(Tuiles tuile) {
        for (int i = 0; i < abbayes.length; i++) {
            if (abbayes[i] != null) {
                if (abbayes[i].getAbbaye().get(0) == tuile) {
                    return i;
                }
            } else {
                return -1;
            }
        }
        return -1;
    }


    public boolean routeTerminer(int indice) {

        for (Tuiles tuiles : routes[indice].getRouteList()) {
            ArrayList<Integer> indiceList = localisationCentreRoute(tuiles);
            if (!estRouteFerme(indiceList, indice, tuiles, tuiles.getI(), tuiles.getJ())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Tuiles> adjRoute(Tuiles tuile, int i, int j) { // Donne la tuile qui est la continuation de la route avec la tuile pioche et posé
        ArrayList<Tuiles> listTuiles = tuilesAdj(tuile);
        ArrayList<Tuiles> listVoisins = new ArrayList<>();
        Tuiles tuileAdjRoute = null;
        int orientation = 0;
        ArrayList<Integer> listOrientation;
        for (Tuiles tmptuiles : listTuiles) {
            orientation = orientationAdj(tmptuiles, terrain[i][j]);
            listOrientation = indicePositionCentre(orientation);
            if (tmptuiles.possedeRoute) {
                if (orientationRoute(listOrientation, tmptuiles, terrain[i][j])) {
                    listVoisins.add(tmptuiles);
                }
            }

        }
        return listVoisins;

    }

    public ArrayList<Integer> indicePositionCentre(int orientationAdj) {
        switch (orientationAdj) {
            case 1:
                return new ArrayList<Integer>(Arrays.asList(2, 0));
            case 2:
                return new ArrayList<Integer>(Arrays.asList(3, 1));
            case 3:
                return new ArrayList<Integer>(Arrays.asList(0, 2));
            case 4:
                return new ArrayList<Integer>(Arrays.asList(1, 3));
        }
        return null;
    }

    public boolean orientationRoute(ArrayList<Integer> listOrientation, Tuiles voisine, Tuiles tuilesPose) { // Regarde si il est possible de coller les routes
        return voisine.centre[listOrientation.get(0)] == TerrainTypesCentre.ROUTE && tuilesPose.centre[listOrientation.get(1)] == TerrainTypesCentre.ROUTE;
    }

    public int positionTuilesRoute(Tuiles tuile) { // Retourne l'indice de la route où se situe une certaine tuile
        int taille = indiceValideRoute();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            for (Tuiles tmp : routes[i].getRouteList()) {
                if (tmp.i == tuile.i && tmp.j == tuile.j) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int indiceValideRoute() {
        if (routes.length == 0) {
            return 0;
        }
        for (int i = 0; i < routes.length; i++) {
            if (routes[i] != null) {
                if (routes[i].getRouteList().size() == 0) {
                    return i;
                }
            } else {
                return i;
            }

        }
        return -1;
    }

    public Tuiles getTuile(int i, int j) {
        return terrain[i][j];
    }

    public void removeTuile(int i, int j) {
        Tuiles tmp = terrain[i][j];
        tuilesPoses.remove(tmp);
        terrain[i][j] = null;
    }

    public ArrayList<Tuiles> tuilesAdj(Tuiles tuile) { // Fonction qui fournit les voisins d'une tuile
        ArrayList<Tuiles> listTuiles = new ArrayList<>();
        int i = tuile.i;
        int j = tuile.j;
        if (i == 0 || j == 0 || i == lignes - 1 || j == colonnes - 1) {
            System.out.println("tuile en dehors du plateau");
        } else {
            if (terrain[i - 1][j] != null) {
                listTuiles.add(terrain[i - 1][j]);
            }
            if (terrain[i][j + 1] != null) {
                listTuiles.add(terrain[i][j + 1]);
            }
            if (terrain[i + 1][j] != null) {
                listTuiles.add(terrain[i + 1][j]);
            }
            if (terrain[i][j - 1] != null) {
                listTuiles.add(terrain[i][j - 1]);
            }
        }
        return listTuiles;
    }

    public int orientationAdj(Tuiles tuileVoisine, Tuiles tuilePose) { // Fonction qui va determiner le positionnement de la tuile posée et voisine
        int i = tuilePose.i;
        int j = tuilePose.j;
        if (terrain[i - 1][j] == tuileVoisine) { //TuileA derrière
            return 1;
        }
        if (terrain[i][j + 1] == tuileVoisine) {
            return 2;
        }
        if (terrain[i + 1][j] == tuileVoisine) {
            return 3;
        }
        if (terrain[i][j - 1] == tuileVoisine) {
            return 4;
        }
        return -1;
    }

    public ArrayList<Tuiles> adjChateau(Tuiles tuile, int i, int j) { // Donne la tuile qui est la continuation du chateau avec la tuile pioche
        ArrayList<Tuiles> listTuiles = tuilesAdj(tuile);
        ArrayList<Tuiles> listVoisins = new ArrayList<>();
        Tuiles tuileAdjChateau = null;
        int orientation = 0;
        ArrayList<Integer> listOrientation;
        for (Tuiles tmptuiles : listTuiles) {
            orientation = orientationAdj(tmptuiles, terrain[i][j]);
            listOrientation = indicePositionCotes(orientation);
            if (tmptuiles.possedeChateauCote || tmptuiles.possedeChateauCentre) {
                if (orientationChateau(listOrientation, tmptuiles, terrain[i][j])) {
                    listVoisins.add(tmptuiles);
                }
            }

        }
        return listVoisins;

    }


    public ArrayList<Integer> indicePositionCotes(int orientationAdj) {
        switch (orientationAdj) {
            case 1:
                return new ArrayList<Integer>(Arrays.asList(4, 5, 0, 1));
            case 2:
                return new ArrayList<Integer>(Arrays.asList(6, 7, 2, 3));
            case 3:
                return new ArrayList<Integer>(Arrays.asList(0, 1, 4, 5));
            case 4:
                return new ArrayList<Integer>(Arrays.asList(2, 3, 6, 7));
        }
        return null;
    }

    public boolean orientationChateau(ArrayList<Integer> listOrientation, Tuiles voisine, Tuiles tuilesPose) { // Regarde si il est possible de coller les chateaux
        if (voisine.cotes[listOrientation.get(0)] == TerrainTypesCotes.CHATEAU && tuilesPose.cotes[listOrientation.get(3)] == TerrainTypesCotes.CHATEAU
                && voisine.cotes[listOrientation.get(1)] == TerrainTypesCotes.CHATEAU && tuilesPose.cotes[listOrientation.get(2)] == TerrainTypesCotes.CHATEAU) {
            return true;
        }
        return false;
    }

    public int indiceValideChateau() {
        if (chateaux.length == 0) {
            return 0;
        }
        for (int i = 0; i < chateaux.length; i++) {
            if (chateaux[i] != null) {
                if (chateaux[i].getChateauList().size() == 0) {//.getChateauList() == null) {
                    return i;
                }
            } else {
                return i;
            }

        }
        return -1;
    }

    public int positionTuilesChateauTableau(Tuiles tuile) { // Retourne l'indice du chateau où se situe une certaine tuile
        int taille = indiceValideChateau();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            for (Tuiles tmp : chateaux[i].getChateauList()) {
                if (tmp.i == tuile.i && tmp.j == tuile.j) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Tuiles[] tuilesAdjIJ(int i, int j) {
        Tuiles[] listTuiles = new Tuiles[4];
        if (i > 0) {
            if (terrain[i - 1][j] != null) {
                listTuiles[0] = terrain[i - 1][j];
            }
        }
        if (j < colonnes) {
            if (terrain[i][j + 1] != null) {
                listTuiles[1] = terrain[i][j + 1];
            }
        }
        if (i < lignes) {
            if (terrain[i + 1][j] != null) {
                listTuiles[2] = terrain[i + 1][j];
            }
        }
        if (j > 0) {
            if (terrain[i][j - 1] != null) {
                listTuiles[3] = terrain[i][j - 1];
            }
        }
        return listTuiles;
    }

    public boolean tuilePosable(Tuiles actuel, int i, int j, int position, Tuiles tuilePiocheN) {
        if (!notOutOfRange(i, j)) {
            return false;
        }
        if (terrain[i][j] == null) {
            if (position == 1) { // Si la tuile pioché peut se mettre en haut
                if (tuilePiocheN.possedeRoute && actuel.possedeRoute) {
                    if (tuilePiocheN.centre[2] != TerrainTypesCentre.ROUTE && TerrainTypesCentre.ROUTE != actuel.centre[0]) {
                        return tuilePiocheN.cotes[4] == actuel.cotes[1] && tuilePiocheN.cotes[5] == actuel.cotes[0];
                    }
                    return tuilePiocheN.centre[2] == actuel.centre[0];
                } else {
                    if (tuilePiocheN.centre[2] == TerrainTypesCentre.ROUTE || actuel.centre[0] == TerrainTypesCentre.ROUTE)
                        return false;
                    return tuilePiocheN.cotes[4] == actuel.cotes[1] && tuilePiocheN.cotes[5] == actuel.cotes[0];
                }
            } else if (position == 2) { // Si la tuile pioché peut se mettre a droite
                if (tuilePiocheN.possedeRoute && actuel.possedeRoute) {
                    if (tuilePiocheN.centre[3] != TerrainTypesCentre.ROUTE && TerrainTypesCentre.ROUTE != actuel.centre[1]) {
                        return tuilePiocheN.cotes[6] == actuel.cotes[3] && tuilePiocheN.cotes[7] == actuel.cotes[2];
                    }
                    return tuilePiocheN.centre[3] == actuel.centre[1];

                } else {
                    if (tuilePiocheN.centre[3] == TerrainTypesCentre.ROUTE || actuel.centre[1] == TerrainTypesCentre.ROUTE)
                        return false;
                    return tuilePiocheN.cotes[6] == actuel.cotes[3] && tuilePiocheN.cotes[7] == actuel.cotes[2];
                }
            } else if (position == 3) { // Si la tuile pioché peut se mettre en bas
                if (tuilePiocheN.possedeRoute && actuel.possedeRoute) {
                    if (tuilePiocheN.centre[0] != TerrainTypesCentre.ROUTE && TerrainTypesCentre.ROUTE != actuel.centre[2]) {
                        return tuilePiocheN.cotes[0] == actuel.cotes[5] && tuilePiocheN.cotes[1] == actuel.cotes[4];
                    }
                    return tuilePiocheN.centre[0] == actuel.centre[2];
                } else {
                    if (tuilePiocheN.centre[0] == TerrainTypesCentre.ROUTE || actuel.centre[2] == TerrainTypesCentre.ROUTE)
                        return false;
                    return tuilePiocheN.cotes[0] == actuel.cotes[5] && tuilePiocheN.cotes[1] == actuel.cotes[4];
                }
            } else if (position == 4) { // Si la tuile pioché peut se mettre a gauche
                if (tuilePiocheN.possedeRoute && actuel.possedeRoute) {
                    if (tuilePiocheN.centre[1] != TerrainTypesCentre.ROUTE && TerrainTypesCentre.ROUTE != actuel.centre[3]) {
                        return tuilePiocheN.cotes[2] == actuel.cotes[7] && tuilePiocheN.cotes[3] == actuel.cotes[6];
                    }
                    return tuilePiocheN.centre[1] == actuel.centre[3];
                } else {
                    if (tuilePiocheN.centre[1] == TerrainTypesCentre.ROUTE || actuel.centre[3] == TerrainTypesCentre.ROUTE)
                        return false;
                    return tuilePiocheN.cotes[2] == actuel.cotes[7] && tuilePiocheN.cotes[3] == actuel.cotes[6];
                }
            }
        }
        return false;
    }

    public boolean tableauFalse(ArrayList<Boolean> tableauBool) {
        return tableauBool.contains(Boolean.FALSE);
    }

    public ArrayList<Point> getPosesPossibles() {
        ArrayList<Point> casesPossibles = new ArrayList<>();
        for (Tuiles tuilePose : tuilesPoses) {
            for (int indice = 0; indice < tuilePioche.getRotationsPoss(); indice++) {
                int[] tableauPosition = tuilesAdjv2(tuilePose);
                if (tableauPosition[0] == 0) {
                    if (tuilePosable(tuilePose, tuilePose.i - 1, tuilePose.j, 1, tuilePioche.rotations[indice])) {
                        //possible= true;
                        ArrayList<Boolean> tableaubool = new ArrayList<Boolean>();
                        Tuiles[] tableauVoisin = tuilesAdjIJ(tuilePose.i - 1, tuilePose.j);
                        if (tableauVoisin[0] != null) {
                            if (tuilePosable(tableauVoisin[0], tuilePose.i - 1, tuilePose.j, 3, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[1] != null) {
                            if (tuilePosable(tableauVoisin[1], tuilePose.i - 1, tuilePose.j, 4, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[2] != null) {
                            if (tuilePosable(tableauVoisin[2], tuilePose.i - 1, tuilePose.j, 1, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[3] != null) {
                            if (tuilePosable(tableauVoisin[3], tuilePose.i - 1, tuilePose.j, 2, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (!tableauFalse(tableaubool))
                            casesPossibles.add(new Point(tuilePose.i - 1, tuilePose.j, indice));
                    }
                }
                if (tableauPosition[1] == 0) {
                    ArrayList<Boolean> tableaubool = new ArrayList<Boolean>();
                    if (tuilePosable(tuilePose, tuilePose.i, tuilePose.j + 1, 2, tuilePioche.rotations[indice])) {
                        Tuiles[] tableauVoisin = tuilesAdjIJ(tuilePose.i, tuilePose.j + 1);
                        if (tableauVoisin[0] != null) {
                            if (tuilePosable(tableauVoisin[0], tuilePose.i, tuilePose.j + 1, 3, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[1] != null) {
                            if (tuilePosable(tableauVoisin[1], tuilePose.i, tuilePose.j + 1, 4, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[2] != null) {
                            if (tuilePosable(tableauVoisin[2], tuilePose.i, tuilePose.j + 1, 1, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[3] != null) {
                            if (tuilePosable(tableauVoisin[3], tuilePose.i, tuilePose.j + 1, 2, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (!tableauFalse(tableaubool))
                            casesPossibles.add(new Point(tuilePose.i, tuilePose.j + 1, indice));
                    }
                }
                if (tableauPosition[2] == 0) {
                    ArrayList<Boolean> tableaubool = new ArrayList<Boolean>();
                    if (tuilePosable(tuilePose, tuilePose.i + 1, tuilePose.j, 3, tuilePioche.rotations[indice])) {
                        Tuiles[] tableauVoisin = tuilesAdjIJ(tuilePose.i + 1, tuilePose.j);
                        if (tableauVoisin[0] != null) {
                            if (tuilePosable(tableauVoisin[0], tuilePose.i + 1, tuilePose.j, 3, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[1] != null) {
                            if (tuilePosable(tableauVoisin[1], tuilePose.i + 1, tuilePose.j, 4, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[2] != null) {
                            if (tuilePosable(tableauVoisin[2], tuilePose.i + 1, tuilePose.j, 1, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[3] != null) {
                            if (tuilePosable(tableauVoisin[3], tuilePose.i + 1, tuilePose.j, 2, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (!tableauFalse(tableaubool))
                            casesPossibles.add(new Point(tuilePose.i + 1, tuilePose.j, indice));
                    }
                }
                if (tableauPosition[3] == 0) {
                    ArrayList<Boolean> tableaubool = new ArrayList<Boolean>();
                    if (tuilePosable(tuilePose, tuilePose.i, tuilePose.j - 1, 4, tuilePioche.rotations[indice])) {
                        Tuiles[] tableauVoisin = tuilesAdjIJ(tuilePose.i, tuilePose.j - 1);
                        if (tableauVoisin[0] != null) {
                            if (tuilePosable(tableauVoisin[0], tuilePose.i, tuilePose.j - 1, 3, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[1] != null) {
                            if (tuilePosable(tableauVoisin[1], tuilePose.i, tuilePose.j - 1, 4, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[2] != null) {
                            if (tuilePosable(tableauVoisin[2], tuilePose.i, tuilePose.j - 1, 1, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (tableauVoisin[3] != null) {
                            if (tuilePosable(tableauVoisin[3], tuilePose.i, tuilePose.j - 1, 2, tuilePioche.rotations[indice])) {
                                tableaubool.add(true);
                            } else {
                                tableaubool.add(false);
                            }
                        }
                        if (!tableauFalse(tableaubool))
                            casesPossibles.add(new Point(tuilePose.i, tuilePose.j - 1, indice));
                    }
                }
            }
        }
        return casesPossibles;
    }

    public void tournerTuiles(int i, int j) throws CloneNotSupportedException {
        if (indicerotation == rotationActif.size() - 1) {
            indicerotation = 0;
            rotationTuile = rotationActif.get(indicerotation);
        } else {
            indicerotation = indicerotation + 1;
            rotationTuile = rotationActif.get(indicerotation);
        }
        if (terrain[i][j] != null) {
            removeTuile(i, j);
        }
        Tuiles tuilePiocheRotation = (Tuiles) tuilePioche.rotations[rotationTuile.indice].clone();
        tuilePiocheRotation.setCoord(i, j);
        setTuile(tuilePiocheRotation, i, j);
    }

    public void setRotationTuiles(ArrayList<Point> casesPossibles, int i, int j) throws CloneNotSupportedException {
        int compteur = 0;
        //ArrayList<Point> rotationsMultiples = new ArrayList<>();
        boolean memevaleur = true;
        for (Point cases : casesPossibles) {
            if (cases.x == i && cases.y == j) {
                compteur++;
            }
        }
        if (compteur == 0) {
            System.err.println("Tentative d'accès à une case impossible");
            return;
        }
        if (rotationActif.size() == 0) {
            for (Point point : casesPossibles) {
                if (point.x == i && point.y == j) {
                    rotationActif.add(point);
                }
            }
            rotationTuile = rotationActif.get(0);
            Tuiles tuilePiocheRotation = (Tuiles) tuilePioche.rotations[rotationTuile.indice].clone();
            tuilePiocheRotation.setCoord(i, j);
            setTuile(tuilePiocheRotation, i, j);
        } else {
            System.out.println("Rotation active " + rotationActif.size());
            ArrayList<Point> tmp = (ArrayList<Point>) rotationActif.clone();
            for (Point point : rotationActif) {
                if (point.x != i || point.y != j) {
                    tmp.remove(point);
                    memevaleur = false;
                }
            }
            if (!memevaleur) {
                rotationActif = (ArrayList<Point>) tmp.clone();
                for (Point point : casesPossibles) {
                    if (point.x == i && point.y == j) {
                        rotationActif.add(point);
                    }
                }
            }
            rotationTuile = rotationActif.get(0);
            Tuiles tuilePiocheRotation = (Tuiles) tuilePioche.rotations[rotationTuile.indice].clone();
            tuilePiocheRotation.setCoord(i, j);
            setTuile(tuilePiocheRotation, i, j);
        }
    }

    public int[] tuilesAdjv2(Tuiles tuile) { // Fonctions qui fournit si il y a une tuile en position 0 1 2 3
        int[] tableauPosition = new int[4];
        int i = tuile.i;
        int j = tuile.j;
        if (i == 0 || j == 0 || i == lignes - 1 || j == colonnes - 1) {
            System.out.println("tuile en dehors du plateau");
        } else {
            if (terrain[i - 1][j] != null) {
                tableauPosition[0] = 1;
            }
            if (terrain[i][j + 1] != null) {
                tableauPosition[1] = 1;
            }
            if (terrain[i + 1][j] != null) {
                tableauPosition[2] = 1;
            }
            if (terrain[i][j - 1] != null) {
                tableauPosition[3] = 1;
            }
        }
        return tableauPosition;
    }

    public void toStringTuiles(Tuiles tuile) {
        for (TerrainTypesCotes cotes : tuile.cotes) {
            System.out.print(cotes + " ");
        }
    }

    public int getLignes() {
        return lignes;
    }

    public int getColonnes() {
        return colonnes;
    }

    public Tuiles getTerrain(int i, int j) {
        return terrain[i][j];
    }

    public boolean notOutOfRange(int i, int j) {
        if (i == 0 || i >= lignes) {
            return false;
        } else return j != 0 && j < colonnes;
    }

    public Coup determinerCoup(int l, int c) {
        Coup resultat = new Coup();
        resultat.pose(l, c, this);
        return resultat;
    }

    public void fixerTour(int t) {
        tour = t + 1;
    }

    public void jouerCoup(Coup cp) {
        cp.fixerPlateau(this);
        nouveau(cp);
    }

    public ArrayList<Tuiles> getTuilesPoses() {
        return tuilesPoses;
    }

    public boolean chateauTerminer(int indice) {

        for (Tuiles tuiles : chateaux[indice].getChateauList()) {
            ArrayList<Integer> indiceList = localisationCoteChateau(tuiles);
            if (!estChateauFerme(indiceList, indice, tuiles, tuiles.getI(), tuiles.getJ())) {
                return false;
            }
        }
        return true;
    }

    public boolean estChateauFerme(ArrayList<Integer> indiceList, int indiceChateau, Tuiles tuilesPose, int i, int j) {
        boolean tuileFerme = true;
        for (Integer integer : indiceList) {
            if (integer == 0 || integer == 1) {
                if (!chateaux[indiceChateau].contientTuile(terrain[i - 1][j])) {
                    tuileFerme = false;
                }
            }
            if (integer == 2 || integer == 3) {
                if (!chateaux[indiceChateau].contientTuile(terrain[i][j + 1])) {
                    tuileFerme = false;
                }
            }
            if (integer == 4 || integer == 5) {
                if (!chateaux[indiceChateau].contientTuile(terrain[i + 1][j])) {
                    tuileFerme = false;
                }
            }
            if (integer == 6 || integer == 7) {
                if (!chateaux[indiceChateau].contientTuile(terrain[i][j - 1])) {
                    tuileFerme = false;
                }
            }
        }
        return tuileFerme;
    }

    public ArrayList<Integer> localisationCoteChateau(Tuiles tuile) { // Va me donner les cotes qui sont des chateaux de la tuile
        int i = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (TerrainTypesCotes tcotes : tuile.cotes) {
            if (tcotes == TerrainTypesCotes.CHATEAU) {
                list.add(i);
            }
            i += 1;
        }
        return list;
    }

    public ArrayList<Integer> localisationCentreRoute(Tuiles tuile) { // Va me donner les centres qui sont des routes de la tuile
        int i = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j = 0; j < tuile.centre.length - 1; j++) {
            if (tuile.centre[j] == TerrainTypesCentre.ROUTE) {
                list.add(i);
            }
            i += 1;
        }
        return list;
    }

    public boolean estRouteFerme(ArrayList<Integer> indiceList, int indiceRoute, Tuiles tuilesPose, int i, int j) {
        boolean tuileFerme = true;
        for (Integer integer : indiceList) {
            if (integer == 0) {
                if (!routes[indiceRoute].contientTuile(terrain[i - 1][j])) {
                    tuileFerme = false;
                }
            }
            if (integer == 1) {
                if (!routes[indiceRoute].contientTuile(terrain[i][j + 1])) {
                    tuileFerme = false;
                }
            }
            if (integer == 2) {
                if (!routes[indiceRoute].contientTuile(terrain[i + 1][j])) {
                    tuileFerme = false;
                }
            }
            if (integer == 3) {
                if (!routes[indiceRoute].contientTuile(terrain[i][j - 1])) {
                    tuileFerme = false;
                }
            }
        }
        return tuileFerme;
    }

    public String getVainqueur() {
        if (getNbScore(1) == getNbScore(2)) {
            return "Égalité !";
        } else if (getNbScore(1) > getNbScore(2)) {
            return "Victoire du Joueur 1 !";
        } else if (getNbScore(1) < getNbScore(2)) {
            return "Victoire du Joueur 2 !";
        } else return null;
    }

}