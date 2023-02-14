package Patterns;

import structures.Iterateur;
import structures.SequenceTableau;

public class Observable {
    SequenceTableau<Observateur> observateurs;

    public Observable() {
        observateurs = new SequenceTableau<>();
    }

    public void ajouterObservateur(Observateur l) {
        observateurs.insereQueue(l);
    }

    public void miseAJour() {
        Iterateur<Observateur> it;
        it = observateurs.iterateur();
        while (it.aProchain()) {
            it.prochain().metAJour();
        }
    }
}