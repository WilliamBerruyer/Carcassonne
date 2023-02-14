package structures;

public class FAP_Tableau<T extends Comparable<T>> extends FAP<T> {
    SequenceTableau<T> s;

    public FAP_Tableau(){
        s = new SequenceTableau<>();
        super.s = s;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insere(T element) {
       s.insereTete(element);
       int position = 0;
       int courant = (s.debut + position)%s.elements.length;
       int suivant = (courant + 1)%s.elements.length;
       T eltCourant = (T) s.elements[courant];
       T eltSuivant = (T) s.elements[suivant];
       while ((position < s.taille - 1) && (eltCourant.compareTo(eltSuivant) > 0)) {
            T tmp = eltCourant;
            s.elements[courant] = eltSuivant;
            s.elements[suivant] = eltCourant;
            position++;
            courant = suivant;
            suivant = (courant + 1)%s.elements.length;
            eltSuivant = (T) s.elements[suivant];

       }

    }
}
