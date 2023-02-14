package vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;

public class AdaptateurSourisBoutons extends MouseAdapter {

    String nom;
    String path;

    JButton button;

    AdaptateurSourisBoutons(JButton b, String p, String nomBouton) {
        nom = nomBouton;
        button = b;
        path = p;

    }

    public void mouseEntered(java.awt.event.MouseEvent evt) {
        ImageIcon npi = new ImageIcon(getImage(path + nom + "_surbrillance.png"));
        button.setIcon(npi);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        ImageIcon npi = new ImageIcon(getImage(path + nom + ".png"));
        button.setIcon(npi);
    }

    private Image getImage(String filename) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
