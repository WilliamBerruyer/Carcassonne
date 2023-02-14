package model;

import Patterns.Observable;
import model.tuiles.Tuiles;
import model.zones.Chateau;

import java.util.ArrayList;
import java.util.Random;

public class Jeu extends Observable {
    int score1;
    int score2;
    int tour = 1;
    int joueur = 1;
    Plateau plateau;
    int nbPions1;
    int nbPions2;
    int zoomFactor = 128;
    public boolean tuilePosee = false;
    public boolean pionPose = false;
    public boolean coupSuivant = false;
    Tuiles tuilesTempPreRotation;
    public int ligneTempLastTuile;
    public int colonneTempLastTuile;
    boolean finInit = true;
    boolean jeuTermine = false;


    public Jeu(Plateau p) {
        this.plateau = p;
        this.score1 = p.score1;
        this.score2 = p.score2;
        nbPions1 = p.nbPionsJoueur1;
        nbPions2 = p.nbPionsJoueur2;
        plateau.piocherCarte();
        plateau.toStringTuiles(plateau.tuilePioche);
        remplirJeu();
    }

    public void changerTour() {
        tour += 1;
    }

    public int getTour() {
        return tour;
    }

    public int getNbPions(int j) {
        switch (j) {
            case 1:
                return nbPions1;
            case 2:
                return nbPions2;
            default:
                break;
        }
        return -1;
    }

    public void changerJoueur() {
        if (joueur == 1) joueur = 2;
        else joueur = 1;
    }

    public int getJoueur() {
        return joueur;
    }

    public boolean jeuFini() {
        if (plateau.pioche.isEmpty()) {
            plateau.updateChateauxFinJeu();
            plateau.updateRouteFinJeu();
            plateau.updateAbbayeFinJeu();
            jeuTermine = true;
            miseAJour();
            return true;
        }
        return false;
    }

    public boolean isJeuTermine() {
        return jeuTermine;
    }

    public void remplirJeu() {
        int i = 0;
        int j;
        int centreX = plateau.colonnes / 2;
        int centreY = plateau.lignes / 2;
        System.out.println(centreX + centreY);
        while (i < plateau.lignes) {
            j = 0;
            while (j < plateau.colonnes) {
                if (i == centreX && j == centreY) {
                    plateau.setTuileVeritable(plateau.tuilePioche, i, j);
                    ligneTempLastTuile = i;
                    colonneTempLastTuile = j;
                    plateau.piocherCarte();
                } else {
                    plateau.terrain[i][j] = null;
                }
                j++;
            }
            i++;
        }
        miseAJour();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public boolean getNombreRotation() {
        ArrayList<Point> pointlist = new ArrayList<>();
        pointlist.add(plateau.rotationActif.get(0));
        boolean t = false;
        for (Point point : plateau.rotationActif) {

            for (Point p2 : pointlist) {
                if (p2.indice != point.indice) {
                    t = true;
                }
            }
        }
        return t;
    }

    public void jouerCoupTemp(int l, int c) {
        try {
            plateau.setRotationTuiles(plateau.getPosesPossibles(), l, c);
            System.out.println(plateau.rotationActif.size());
            if (getNombreRotation() == false) {
                plateau.pasDeRotationLastTuile = true;
            } else {
                plateau.pasDeRotationLastTuile = false;
            }
        } catch (CloneNotSupportedException e) {
            System.out.println("Problème clonage");
        }

        ligneTempLastTuile = l;
        colonneTempLastTuile = c;
        // cette ligne a modifié je pense
        tuilesTempPreRotation = plateau.tuilePioche;
        miseAJour();
    }

    public void rotateTuile() {
        try {
            plateau.tournerTuiles(ligneTempLastTuile, colonneTempLastTuile);
        } catch (CloneNotSupportedException e) {
            System.out.println("Problème clonage");
        }
        miseAJour();
    }

    public void jouerCoup(Coup cp) {
        if (cp == null) {
            System.out.println("Deplacement impossible");
        } else {
            plateau.jouerCoup(cp);
            miseAJour();
        }
        miseAJour();
    }

    public void annulerPose() {
        plateau.getTuile(ligneTempLastTuile, colonneTempLastTuile).removePion();
        plateau.removeTuile(ligneTempLastTuile, colonneTempLastTuile);
        tuilePosee = false;
        pionPose = false;
        miseAJour();
    }

    public void coupSuivant() {
        if (!jeuFini()) {
            plateau.piocherCarte();
            tuilePosee = false;
            pionPose = false;
            coupSuivant = true;
            miseAJour();
        } else {
            System.out.println("jeu fini");
        }

    }

    public boolean posePossible(int i, int j, Plateau p) {
        if (p.getPosesPossibles().size() == 0) {
            p.repioche();
            if (p.pioche.size() == 1) {
                p.pioche.remove(p.tuilePioche);
            }
            miseAJour();
        }
        for (Point possible : p.getPosesPossibles()) {
            if (possible.x == i && possible.y == j) {
                return true;
            }
        }
        return false;
    }

    public boolean autoriserPose(int l, int c) {
        if (l >= plateau.lignes || c >= plateau.colonnes || l <= -1 || c <= -1) {
            return false;
        }
        if (plateau.terrain[l][c] != null || posePossible(l, c, plateau) == false) {
            return false;
        }
        return true;
    }

    public Coup determinerCoup(int l, int c) {
        ligneTempLastTuile = l;
        colonneTempLastTuile = c;
        tuilesTempPreRotation = plateau.tuilePioche;
        plateau.fixerTour(tour);
        return plateau.determinerCoup(l, c);
    }

    public void setZoomFactor(int i, boolean zooming) {
        if (zoomFactor * plateau.lignes < 9000 && zooming) {
            zoomFactor += i;
        }
        if (zoomFactor * plateau.lignes > 3500 && !zooming) {
            zoomFactor += i;
        }
    }

    public int getZoomFactor() {
        return zoomFactor;
    }

    public int getLigneTempLastTuile() {
        return ligneTempLastTuile;
    }

    public int getColonneTempLastTuile() {
        return colonneTempLastTuile;
    }

    public boolean getFinInit() {
        return finInit;
    }

    public void finInit() {
        setZoomFactor(0, false);
        finInit = false;
        miseAJour();
    }
}
