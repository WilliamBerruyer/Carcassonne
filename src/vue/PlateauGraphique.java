package vue;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface PlateauGraphique {

    int largeur();

    int hauteur();

    void tracerImage(Image img, int x, int y, int largeurCase, int hauteurCase);

    void tracerLigne(int x1, int y1, int x2, int y2);
}
