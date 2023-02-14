package vue;


import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class AdaptateurSouris extends MouseAdapter {
    PlateauGraphiqueSwing plateau;
    CollecteurEvenements controle;

    AdaptateurSouris(PlateauGraphiqueSwing p, CollecteurEvenements c) {
        plateau = p;
        controle = c;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int c = e.getX() / plateau.largeurCase();
        int l = e.getY() / plateau.hauteurCase();
        controle.clicSouris(l, c, e.getY(), e.getX());
    }


    //evenement consumé par le scroll pane avant d'arrivé ici donc ne fonctionne pas :(
    /*@Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0 && e.getModifiers() == InputEvent.CTRL_DOWN_MASK) {
            System.out.println("molette bougé");
            controle.zoomIn();
        } else {
            controle.zoomOut();
        }
    }*/
}
