package model.zones;

import model.tuiles.Tuiles;
import java.util.ArrayList;

public class Route {
    ArrayList<Tuiles> listTuiles;// Les tuiles qui composent le chateau
    ArrayList<Integer> proprio;// Tableau pour les proprios si plusieurs du chateaux
    public boolean terminer = false;
    public boolean scorePrisEnCompte = false;

    public Route() {
        listTuiles = new ArrayList<>();
        proprio = new ArrayList<>();
    }

    public void setProprietaire(int proprio) {
        this.proprio.add(proprio);
    }

    public void addTuiles(Tuiles t) {
        listTuiles.add(t);
    }

    public ArrayList<Integer> getProprietaireList() {
        return proprio;
    }

    public int getProprio() {
        int joueur1 = 0, joueur2 = 0;
        for (int i = 0; i < proprio.size(); i++) {
            if (proprio.get(i) == 1) {
                joueur1 += 1;
            } else if (proprio.get(i) == 2) {
                joueur2 += 1;
            }
        }
        if (joueur1 == joueur2) {
            return 3; // Cas d'égalité on partage les points
        }
        int valeur = Math.max(joueur1, joueur2);
        if (valeur == joueur1) {
            return 1; // Joueur 1 qui a le plus de pions il est majoritaire.
        } else if (valeur == joueur2) {
            return 2; // Joueur 2 qui a le plus de pions il est majoritaire.
        }
        return -1;
    }

    public ArrayList<Tuiles> getRouteList() {
        return listTuiles;
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

    public int getNbPionsJoueur1() {
        int compteur = 0;
        for (Integer pion : proprio) {
            if (pion == 1) {
                compteur++;
            }
        }
        return compteur;
    }

    public int getNbPionsJoueur2() {
        int compteur = 0;
        for (Integer pion : proprio) {
            if (pion == 2) {
                compteur++;
            }
        }
        return compteur;
    }


}
