package controller;

import model.Coup;
import model.Jeu;
import model.Pion;
import model.Plateau;
import model.Point;

import java.util.Random;

public class IAAleatoire extends IA {
    Random r;
    Jeu jeu;

    IAAleatoire(Jeu j) {
        jeu = j;
        r = new Random();
    }

    Coup joue(Plateau p) {
        int l = 0;
        int c = 0;

        int i = 0;
        int j = 0;

        Coup cp;

        int size = p.getPosesPossibles().size();
        while (size == 0) {
            p.repioche();
            size = p.getPosesPossibles().size();
        }
        int poseToChoose = r.nextInt(size);

        for (Point possible : p.getPosesPossibles()) {
            if (i == poseToChoose) {
                l = possible.x;
                c = possible.y;
                break;
            }
            i++;
        }

        try {
            p.setRotationTuiles(p.getPosesPossibles(), l, c);
        } catch (CloneNotSupportedException e) {
            System.out.println("Problème clonage");
        }
        jeu.tuilePosee = true;
        int probaPosePions = r.nextInt(2);
        int nbPion = 0;
        if (jeu.getJoueur() == 1) {
            nbPion = p.nbPionsJoueur1;
        } else if (jeu.getJoueur() == 2) {
            nbPion = p.nbPionsJoueur2;
        }
        if (probaPosePions > 0 && nbPion > 0) {
            for (Pion emplacement : jeu.getPlateau().getTerrain(l, c).getPionsEmplacement()) {
                if (emplacement != null) {
                    if (jeu.getPlateau().getTuile(l, c).getPion() == null) {
                        emplacement.setJoueur(jeu.getJoueur());
                        jeu.getPlateau().getTuile(l, c).setPion(emplacement);
                        jeu.pionPose = true;
                    }
                }
                j++;
            }
        }
        cp = p.determinerCoup(l, c);
        return cp;
    }

    @Override
    Coup joue(Plateau p, int l, int c) {
        return null;
    }
}
