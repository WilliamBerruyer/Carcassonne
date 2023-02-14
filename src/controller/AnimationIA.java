package controller;

import model.Coup;
import model.Jeu;
import model.Plateau;
import structures.Sequence;
import structures.SequenceTableau;

public class AnimationIA extends Animation {
    IA ia;
    Jeu jeu;
    Sequence<Coup> coups;
    Controleur control;

    AnimationIA(Controleur c, Jeu j) {
        super(20);
        ia = new IAAleatoire(j);
        jeu = j;
        coups = new SequenceTableau<>();
        control = c;
    }

    @Override
    void miseAJour() {

        Plateau p = jeu.getPlateau().clone();
        if (coups.estVide()) {
            //coups = ia.joue(p);
        }
        if (coups.estVide()) {
            System.out.println("L'IA n'a pas joué de coup");
        } else {
            Coup cp = coups.extraitTete();
            control.jouerCoup(cp);
        }
    }
}
