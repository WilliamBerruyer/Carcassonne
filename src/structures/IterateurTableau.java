package structures;


public class IterateurTableau<T> extends Iterateur<T> {
    int position;
    SequenceTableau<T> s;

    IterateurTableau(SequenceTableau<T> t) {
        s = t;
    }

    @Override
    public boolean aProchain() {
        return position < s.taille;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T prochain() {
        super.prochain();
        int indice = (s.debut+position)%s.elements.length;
        position++;
        return (T) s.elements[indice];
    }

    @Override
    public void supprime() {
        super.supprime();
        assert (position > 0);
        for (int i = position; i<s.taille; i++) {
            int prec = (s.debut + i - 1)%s.elements.length;
            int suiv = (prec + 1)%s.elements.length;
            s.elements[prec] = s.elements[suiv];
        }
        s.taille--;
        position--;
    }
}
