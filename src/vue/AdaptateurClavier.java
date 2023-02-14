package vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdaptateurClavier extends KeyAdapter {
    CollecteurEvenements controle;

    AdaptateurClavier(CollecteurEvenements c) {
        controle = c;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                controle.commande("zoom");
                break;
            case KeyEvent.VK_D:
                controle.commande("dezoom");
                break;
            case KeyEvent.VK_C:
                controle.commande("center");
                break;
            case KeyEvent.VK_F:
                controle.commande("fs");
                break;
            case KeyEvent.VK_ESCAPE:
                controle.commande("menuJeu");
                break;
        }
    }
}
