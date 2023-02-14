package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.CollecteurEvenements;

public class AdaptateurBoutons extends JButton implements ActionListener {

    CollecteurEvenements control;
    String commande;

    public AdaptateurBoutons(CollecteurEvenements c, String com) {
        control = c;
        commande = com;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        control.commande(commande);
    }
}
