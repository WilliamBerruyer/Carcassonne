import javax.swing.*;

import vue.Carcassonne;


public class Main implements Runnable {

    Carcassonne crc;

    JFrame frame;

    @Override
    public void run() {

        frame = new JFrame("Carcassonne");
        crc = new Carcassonne();
        crc.demarre(frame);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
}

