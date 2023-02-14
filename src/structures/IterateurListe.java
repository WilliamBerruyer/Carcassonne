package structures;

public class IterateurListe<T> extends Iterateur<T> {
    Maillon<T> courant, precedent, arrierePrecedent;
    SequenceListe_<T> s;

    IterateurListe(SequenceListe_<T> l){
        courant = l.tete;
        s = l;
    }

    @Override
    public boolean aProchain() {
        return courant != null;
    }

    @Override
    public T prochain() {
        super.prochain();
        T resultat = courant.element;
        arrierePrecedent = precedent;
        precedent = courant;
        courant = courant.suivant;
        peutSupprimer = true;
        return resultat;
    }

    @Override
    public void supprime() {
        super.supprime();
        if (arrierePrecedent == null) {
            s.tete = courant;
        } else {
            arrierePrecedent.suivant = courant;
        }
        if (s.queue == precedent) {
            s.queue = arrierePrecedent;
        }
        precedent = arrierePrecedent;
    }
}
