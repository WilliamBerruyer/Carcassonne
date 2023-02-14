package vue;

import model.Jeu;
import model.tuiles.Tuiles;

import javax.swing.*;
import java.awt.*;

public class PlateauGraphiqueSwing extends JComponent implements PlateauGraphique {
    int largeur, hauteur;
    Graphics2D drawable;
    VueJeu vue;

    Dimension preferredSize = null;


    public PlateauGraphiqueSwing(Jeu j) {
        vue = new VueJeu(j, this);

    }

    @Override
    public void paintComponent(Graphics g) {
        // Graphics 2D est le vrai type de l'objet passé en paramètre
        // Le cast permet d'avoir acces a un peu plus de primitives de dessin
        drawable = (Graphics2D) g;

        largeur = getSize().width;
        hauteur = getSize().height;

        drawable.clearRect(0, 0, largeur, hauteur);

        vue.setTailleCase();
        vue.tracerJeu();

    }

    @Override
    public Dimension getPreferredSize() {
        if (preferredSize == null) {
            return new Dimension(250, 100);
        } else {
            return super.getPreferredSize();
        }
    }

    @Override
    public void setPreferredSize(Dimension newPrefSize) {
        preferredSize = newPrefSize;
        super.setPreferredSize(newPrefSize);
    }

    @Override
    public int largeur() {
        return largeur;
    }

    @Override
    public int hauteur() {
        return hauteur;
    }

    @Override
    public void tracerImage(Image img, int x, int y, int largeurCase, int hauteurCase) {
        drawable.drawImage(img, x, y, largeurCase, hauteurCase, null);
    }

    @Override
    public void tracerLigne(int x1, int y1, int x2, int y2) {
        drawable.drawLine(x1, y1, x2, y2);
    }

    int largeurCase() {
        return vue.largeurCase();
    }

    int hauteurCase() {
        return vue.hauteurCase();
    }

    public void pose(int ligne, int colonne, Tuiles t) {
        try {
            vue.fixerPose(ligne, colonne, t);
        } catch (CloneNotSupportedException e) {
            System.out.println("Problème de clone à : pose");
        }

        repaint();
    }
}
