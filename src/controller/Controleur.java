package controller;

import model.Coup;
import model.Jeu;
import model.Pion;
import vue.Carcassonne;
import vue.CollecteurEvenements;

import javax.swing.*;

public class Controleur implements CollecteurEvenements {
    JFrame frame;
    Carcassonne carcassonne;
    Jeu jeu;
    boolean randomIA1, randomIA2;
    boolean minmaxIA1, minmaxIA2;
    boolean rienSelec1 = true, rienSelec2 = true;
    int lastLigne, lastColonne;
    IAAleatoire IAA1, IAA2;
    IAMinMax IAM1, IAM2;


    public Controleur(JFrame f) {
        frame = f;
        randomIA1 = false;
        minmaxIA1 = false;
        randomIA2 = false;
        minmaxIA2 = false;
    }


    @Override
    public void fixerJeu(Jeu j) {
        jeu = j;
        if (randomIA1) {
            IAA1 = new IAAleatoire(jeu);
        } else if (minmaxIA1) {
            IAM1 = new IAMinMax(jeu);
        }
        if (randomIA2) {
            IAA2 = new IAAleatoire(jeu);
        } else if (minmaxIA2) {
            IAM2 = new IAMinMax(jeu);
        }
    }


    @Override
    public void fixerInterfaceUtilisateur(Carcassonne c) {
        carcassonne = c;
    }

    @Override
    public void commande(String commande) {
        switch (commande) {
            case "q":
                carcassonne.fermerJeu();
                break;
            case "new":
                carcassonne.clearFrame();
                carcassonne.base(false);
                carcassonne.selectionJoueur();
                break;
            case "charger":
                break;
            case "regle":
                carcassonne.popupRegle();
                break;
            case "principal":
                carcassonne.clearFrame();
                carcassonne.base(true);
                carcassonne.ecranPrincipal();
                break;
            case "reprendre":
                carcassonne.reprendreJeu();
                break;
            case "aide":
            	carcassonne.popupAide();
                break;
            case "validerCoup":
                if (!jeu.tuilePosee) {
                    break;
                } else {
                    coupFinal();
                    jeu.coupSuivant();
                    if (randomIA1 || minmaxIA1 || randomIA2 || minmaxIA2) {
                    	if (jeu.getJoueur() == 2) {
                            jeu.changerTour();
                        }
                        jeu.changerJoueur();
                        coupIA();
                    }
                }
                if (jeu.jeuFini()) {
                    carcassonne.popupFin();
                } else {
                	if (jeu.getJoueur() == 2) {
                        jeu.changerTour();
                    }
                    jeu.changerJoueur();
                    jeu.miseAJour();
                }
                break;
            case "annulerPose":
                if (!jeu.tuilePosee) {
                    break;
                } else {
                    jeu.annulerPose();
                }
                break;
            case "rotateTuile":
                if (!jeu.tuilePosee) {
                    break;
                } else if (jeu.tuilePosee && jeu.pionPose) {
                    break;
                } else {
                    jeu.rotateTuile();
                }
                break;
            case "debut":
                if (rienSelec1 || rienSelec2) {
                    break;
                }
                try {
                    if ((randomIA1 || minmaxIA1) && (!randomIA2 && !minmaxIA2))
                        carcassonne.lancerJeu(true, false);
                    else if ((randomIA1 || minmaxIA1) && (randomIA2 || minmaxIA2)) {
                        carcassonne.lancerJeu(true, true);
                    } else if ((!randomIA1 && !minmaxIA1) && (randomIA2 || minmaxIA2)) {
                        carcassonne.lancerJeu(false, true);
                    } else {
                        carcassonne.lancerJeu(false, false);
                    }
                } catch (CloneNotSupportedException e) {
                    System.err.println(e);
                }
                break;
            case "checkCommencer" :
            	if ((!rienSelec1) && (!rienSelec2)) {
            		carcassonne.activerCommencer(true);
            	}
            	else {
            		carcassonne.activerCommencer(false);
            	}
            	break;
            case "activerIAR1":
                randomIA1 = true;
                rienSelec1 = false;
                break;
            case "activerIAR2":
                randomIA2 = true;
                rienSelec2 = false;
                break;
            case "activerIAM1":
                minmaxIA1 = true;
                rienSelec1 = false;
                break;
            case "activerIAM2":
                minmaxIA2 = true;
                rienSelec2 = false;
                break;
            case "j1":
                randomIA1 = false;
                minmaxIA1 = false;
                rienSelec1 = false;
                break;
            case "j2":
                randomIA2 = false;
                minmaxIA2 = false;
                rienSelec2 = false;
                break;
            case "rienSelec1":
                rienSelec1 = true;
                break;
            case "rienSelec2":
                rienSelec2 = true;
                break;
            case "zoom":
                jeu.coupSuivant = false;
                jeu.setZoomFactor(20, true);
                jeu.miseAJour();
                break;
            case "dezoom":
                jeu.coupSuivant = false;
                jeu.setZoomFactor(-20, false);
                jeu.miseAJour();
                break;
            case "center":
                jeu.coupSuivant = false;
                jeu.setZoomFactor(0, false);
                jeu.miseAJour();
                break;
            case "menuJeu":
                carcassonne.menuJeu();
                break;
            case "fs":
                carcassonne.toggleFullscreen();
                break;
            case "lancerIA":
                coupIA();
                if (jeu.getJoueur() == 2) {
                    jeu.changerTour();
                }
                jeu.changerJoueur();
                jeu.miseAJour();
                carcassonne.activerSouris();
                break;
            case "doubleIA":
                while (!jeu.jeuFini()) {
                    coupIA();
                    if (jeu.getJoueur() == 2) {
                        jeu.changerTour();
                    }
                    jeu.changerJoueur();
                    jeu.miseAJour();
                }
                break;
            case "commandesJeu":
                carcassonne.popupCommandesJeu();
                break;
        }
    }

    @Override
    public void clicSouris(int l, int c, int x, int y) {
        if (!jeu.isJeuTermine()) {
            System.out.println("Clic à la position (" + x + "," + y + ")");
            System.out.println("Clic dans la case (" + l + "," + c + ")");
            if (!jeu.tuilePosee && !jeu.pionPose) {
                if (jeu.autoriserPose(l, c)) {
                    jeu.coupSuivant = false;
                    poserTuile(l, c);
                    jeu.miseAJour();
                } else {
                    System.out.println("Vous ne pouvez pas poser votre tuile ici");
                }
            } else if (jeu.tuilePosee) {
                int index = jeu.getPlateau().getTuilesPoses().size() - 1;
                int iTPose = jeu.getPlateau().getTuilesPoses().get(index).i;
                int jTPose = jeu.getPlateau().getTuilesPoses().get(index).j;
                if (iTPose == l && jTPose == c) {
                    for (Pion emplacement : jeu.getPlateau().getTerrain(l, c).getPionsEmplacement()) {
                        if (emplacement != null) {
                            if (x > emplacement.getX() && x < emplacement.getX() + emplacement.getWidth() && y > emplacement.getY() && y < emplacement.getY() + emplacement.getHeight()) {
                                System.out.println("Clic sur un pion");
                                if (jeu.getPlateau().getTuile(l, c).getPion() == null) {
                                    jeu.getPlateau().getTuile(l, c).setPion(emplacement);
                                    jeu.pionPose = true;
                                    jeu.miseAJour();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void poserTuile(int l, int c) {
        jeu.tuilePosee = true;
        lastLigne = l;
        lastColonne = c;
        jouerCoupTemp(lastLigne, lastColonne);
    }

    void jouerCoup(Coup cp) {
        jeu.jouerCoup(cp);
    }

    void jouerCoupTemp(int l, int c) {
        jeu.jouerCoupTemp(l, c);
    }

    void coupIA() {
        switch (jeu.getJoueur()) {
            case 1:
                if (randomIA1) {
                    Coup cp = IAA1.joue(jeu.getPlateau());
                    jouerCoup(cp);
                } else if (minmaxIA1) {
                    Coup cp = IAM1.minMax(jeu.getPlateau(), 1, true, 1).getCoup();
                    jouerCoup(cp);
                }
                break;
            case 2:
                if (randomIA2) {
                    Coup cp = IAA2.joue(jeu.getPlateau());
                    jouerCoup(cp);
                } else if (minmaxIA2) {
                    Coup cp = IAM2.minMax(jeu.getPlateau(), 1, true, 1).getCoup();
                    jouerCoup(cp);
                }
                break;
            default:
                break;
        }
        jeu.coupSuivant = true;
        jeu.coupSuivant();

    }

    void coupFinal() {
        Coup cp = jeu.determinerCoup(lastLigne, lastColonne);
        if (cp != null) {
            jouerCoup(cp);
            jeu.coupSuivant = true;
        }
    }

    public void zoomIn() {
        jeu.setZoomFactor(20, true);
    }

    public void zoomOut() {
        jeu.setZoomFactor(-20, false);
    }

    @Override
    public void tictac() {
    }
}