package model;

import structures.SequenceTableau;

public class Historique<E extends Commande> {
    SequenceTableau<E> passe, futur;

    Historique() {
        passe = new SequenceTableau<>();
        futur = new SequenceTableau<>();
    }

    void nouveau(E c) {
        passe.insereTete(c);
        c.execute();
        while (!futur.estVide()) {
            futur.extraitTete();
        }
    }

    public boolean peutAnnuler() {
        return !passe.estVide();
    }

    public E annuler() {
        if (peutAnnuler()) {
            E c = passe.extraitTete();
            c.desexecute();
            futur.insereTete(c);
            return c;
        } else {
            return null;
        }
    }

    public boolean peutRefaire() {
        return !futur.estVide();
    }

    E refaire() {
        if (peutRefaire()) {
            E c = futur.extraitTete();
            c.execute();
            passe.insereTete(c);
            return c;
        } else {
            return null;
        }
    }
}