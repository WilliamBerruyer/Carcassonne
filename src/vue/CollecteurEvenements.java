package vue;

import model.Jeu;

public interface CollecteurEvenements {
    void fixerJeu(Jeu j);

    void fixerInterfaceUtilisateur(Carcassonne c);

    public void commande(String commande);

    void clicSouris(int l, int c, int x, int y);

    void tictac();

    void zoomIn();

    void zoomOut();
}
