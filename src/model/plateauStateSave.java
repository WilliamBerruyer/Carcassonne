package model;

public class plateauStateSave {

    public int ligne, colonne;

    int tour;

    public Plateau plateauCoup;

    plateauStateSave(int l, int c, Plateau p, int joueur) {
        ligne = l;
        colonne = c;
        plateauCoup = p;
        tour = joueur;
    }
}
