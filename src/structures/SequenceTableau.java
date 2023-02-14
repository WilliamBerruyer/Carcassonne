package structures;


public class SequenceTableau<T> implements Sequence<T> {
    Object[] elements;
    int debut, taille;

    public SequenceTableau() {
        elements = new Object[1];
    }

    private void redimensionne() {
        if (taille >= elements.length) {
            System.out.println("Redimensionne de " + elements.length + " à " + elements.length*2);
            Object [] nouveau = new Object[elements.length*2];
            int fin = Math.min(debut + taille, elements.length);
            for (int i=debut; i<fin; i++) {
                nouveau[i] = elements[i];
            }
            fin = (debut + taille)-elements.length;
            for (int i = 0; i <fin; i++) {
                nouveau[i + elements.length] = elements[i];
            }
            elements = nouveau;
        }
    }

    @Override
    public void insereTete(T element) {
        redimensionne();
        debut--;
        if(debut < 0)
            debut += elements.length;
        elements[debut] = element;
        taille++;
    }

    @Override
    public void insereQueue(T element) {
        redimensionne();
        int position = (debut + taille)% elements.length;
        elements[position] = element;
        taille++;
    }

    @Override
    public T extraitTete() {
        if (taille == 0) {
            throw new RuntimeException("Sequence vide");
        }
        T resultat = (T) elements[debut];
        taille--;
        debut = (debut+1)%elements.length;
        return resultat;
    }

    @Override
    public boolean estVide() {
        return taille == 0;
    }

    @Override
    public Iterateur<T> iterateur() {
        return new IterateurTableau<T>(this);
    }

    @Override
    public String toString() {
        String resultat = "Sequence Tableau : [ ";
        int fin = Math.min(debut + taille, elements.length);
        for (int i=debut; i < fin; i++){
            resultat += elements[i] + " ";
        }
        fin = (debut + taille)- elements.length;
        for (int i=0; i < fin; i++){
            resultat += elements[i] + " ";
        }
        resultat += "]";
        return resultat;
    }
}
