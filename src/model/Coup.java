package model;

import model.tuiles.Tuiles;
import structures.SequenceTableau;

public class Coup extends Commande {

    Plateau p;

    SequenceTableau<plateauStateSave> savedPlateauState;

    public Coup() {
        savedPlateauState = new SequenceTableau<>();
    }

    void fixerPlateau(Plateau pla) {
        p = pla;
    }

    void determinePoseTuile() {


    }

    public void poseTuile(int l, int c, Tuiles t) {

    }

    public SequenceTableau<plateauStateSave> poses() {
        return savedPlateauState;
    }

    @Override
    void execute() {
        determinePoseTuile();
    }

    @Override
    void desexecute() {
        determinePoseTuile();
    }

    public void pose(int l, int c, Plateau plateau) {
        plateau.setTuileVeritable(plateau.terrain[l][c], l, c);
    }
}