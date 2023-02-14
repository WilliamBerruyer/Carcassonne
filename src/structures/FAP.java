package structures;

public abstract class FAP<T> {
    Sequence<T> s;


    public abstract void insere(T element);
    public T extrait(){
        return s.extraitTete();
    }
    public boolean estVide(){
        return s.estVide();
    }
}
