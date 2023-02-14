package controller;

import model.Coup;
import model.plateauStateSave;
import structures.Iterateur;
import vue.Carcassonne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationCoup implements ActionListener {

    Coup cp;

    double progres, vitesseAnim;
    Carcassonne carcassonne;

    Timer temps;

    AnimationCoup() {
        vitesseAnim = 0.15;
        temps = new Timer(16, this);
    }


    void anime() {
        temps.start();
        progres = 0;
        progres += vitesseAnim;
        if (progres > 1) {
            progres = 1;
            temps.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
