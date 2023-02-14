package structures;

public abstract class Iterateur<T> {
    boolean peutSupprimer;
    public abstract boolean aProchain();

    public T prochain(){
        peutSupprimer = true;
        return null;
    }

    public void supprime(){
        if (!peutSupprimer){
            throw new IllegalStateException("Deux suppressions d'affilée");
        }
        peutSupprimer = false;
    }
}
