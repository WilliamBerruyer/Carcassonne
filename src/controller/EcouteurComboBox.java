package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import vue.CollecteurEvenements;

public class EcouteurComboBox implements ActionListener {

    CollecteurEvenements control;
    JComboBox<String> choix;

    public EcouteurComboBox(JComboBox<String> box, CollecteurEvenements c) {
        choix = box;
        control = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        e.getSource();
        String test = (String) choix.getSelectedItem();
        switch (test) {
            case "J1 IA facile":
                control.commande("activerIAR1");
                control.commande("checkCommencer");
                break;
            case "J2 IA facile":
                control.commande("activerIAR2");
                control.commande("checkCommencer");
                break;
            case "Joueur 1":
                control.commande("j1");
                control.commande("checkCommencer");
                break;
            case "Joueur 2":
                control.commande("j2");
                control.commande("checkCommencer");
                break;
            case "Sélectionnez un joueur 1":
                control.commande("rienSelec1");
                control.commande("checkCommencer");
                break;
            case "Sélectionnez un joueur 2":
                control.commande("rienSelec2");
                control.commande("checkCommencer");
                break;
        }
    }

}
