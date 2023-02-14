package structures;

public class Couple<T, S extends Comparable<S>> implements Comparable<Couple<T,S>>{
    T v;
    S p;

    public Couple(T valeur, S priorite){
        v = valeur;
        p = priorite;
    }

    @Override
    public int compareTo(Couple<T, S> o) {
        return p.compareTo(o.p);
    }

    @Override
    public String toString(){
        return "(" + v + "," + p + ")";
    }

    public boolean equals(Couple<Double, Integer> o) {
        return (v == o.v) && (p == o.p);
    }
}
