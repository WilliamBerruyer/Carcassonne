package structures;


class Maillon<T> {
     T element;
     Maillon<T> suivant;

     Maillon(T element, Maillon suivant){
         this.element = element;
         this.suivant = suivant;
     }
}

public class SequenceListe_<T> implements Sequence<T> {
    Maillon<T> tete;
    Maillon<T> queue;

    @Override
    public void insereTete(T element) {
        Maillon<T> nouveau = new Maillon<>(element, tete);
        nouveau.element = element;
        nouveau.suivant = tete;
        if (tete == null){
            tete = nouveau;
            queue = nouveau;
        } else {
            tete = nouveau;
        }
    }

    @Override
    public void insereQueue(T element) {
        Maillon nouveau = new Maillon(element, null);
        nouveau.element = element;
        nouveau.suivant = null;
        if (tete == null){
            tete = nouveau;
            queue = nouveau;
        } else {
            queue.suivant = nouveau;
            queue = nouveau;
        }
    }

    @Override
    public T extraitTete() {
        if (tete == null)
            throw new RuntimeException("Séquence vide.");
        T resultat = tete.element;
        tete = tete.suivant;
        return resultat;
    }

    @Override
    public boolean estVide() {
        return tete == null;
    }

    @Override
    public Iterateur<T> iterateur() {
        return new IterateurListe<>(this);
    }

    @Override
    public String toString() {
        String resultat = "Sequence liste : [ ";
        Maillon<T> courant = tete;
        while (courant != null) {
            resultat += courant.element + " ";
            courant = courant.suivant;
        }
        resultat += "]";
        return resultat;
    }
}
