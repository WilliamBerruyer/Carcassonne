package model.zones;

import model.tuiles.Tuiles;

import java.util.ArrayList;

public class Abbaye {
    ArrayList<Tuiles> listTuiles;
    public boolean terminer = false;
    public boolean scorePrisEnCompte = false;
    int proprio = -1;

    public Abbaye() {
        listTuiles = new ArrayList<>();
    }

    public void addTuiles(Tuiles t) {
        listTuiles.add(t);
    }

    public ArrayList<Tuiles> getAbbaye() {
        return listTuiles;
    }

    public void setProprietaire(int proprietaire) {
        this.proprio = proprietaire;
    }


    public boolean contientTuile(Tuiles tuile) {
        if (tuile == null) {
            return false;
        }
        for (Tuiles tmp : listTuiles) {
            if (tmp.i == tuile.getI() && tmp.j == tuile.getJ()) {
                return true;
            }
        }
        return false;
    }

    public int getProprio() {
        if (proprio == 1) {
            return 1;
        } else if (proprio == 2) {
            return 2;
        }
        return -1;
    }

}
