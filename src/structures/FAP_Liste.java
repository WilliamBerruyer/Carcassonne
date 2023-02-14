package structures;


public class FAP_Liste<T extends Comparable<T>> extends FAP<T>{
    SequenceListe_<T> s;

    public FAP_Liste(){
        s = new SequenceListe_<>();
        super.s = s;
    }

    @Override
    public void insere(T element) {
        Maillon<T> courant, precedent;

        precedent = null;
        courant = s.tete;
        while ( (courant != null) && (element.compareTo(courant.element) > 0) ){
            precedent = courant;
            courant = courant.suivant;
        }
        Maillon<T> m = new Maillon<>(element, courant);
        if (precedent == null){
            s.tete = m;
        }
        else {
            precedent.suivant = m;
        }
        if (courant == null) {
            s.queue = m;
        }
    }
}
