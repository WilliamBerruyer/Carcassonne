package controller;

import model.Coup;
import model.Plateau;
import structures.Sequence;

public abstract class IA {
    abstract Coup joue(Plateau n);

    abstract Coup joue(Plateau p, int l, int c);

}