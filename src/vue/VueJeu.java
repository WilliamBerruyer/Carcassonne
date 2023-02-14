package vue;

import model.Jeu;
import model.Pion;
import model.Plateau;
import model.tuiles.Tuiles;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class VueJeu {
    int largeurCase;
    int hauteurCase;
    int nbL, nbC;
    BufferedImage surbrillance, meeplePose1, meeplePose2, meeple1, meeple2, wood, redOverlay, blueOverlay;
    PlateauGraphiqueSwing pg;
    Jeu jeu;

    public VueJeu(Jeu j, PlateauGraphiqueSwing p) {
        jeu = j;
        pg = p;

        InputStream inSurbrillance = ClassLoader.getSystemClassLoader().getResourceAsStream("tuiles/wood6GlowOutside.jpg");
        InputStream inMeeplePose1 = ClassLoader.getSystemClassLoader().getResourceAsStream("pions/Meeple_position1.png");
        InputStream inMeeplePose2 = ClassLoader.getSystemClassLoader().getResourceAsStream("pions/Meeple_position2.png");
        InputStream inMeeple1 = ClassLoader.getSystemClassLoader().getResourceAsStream("pions/meeple1.png");
        InputStream inMeeple2 = ClassLoader.getSystemClassLoader().getResourceAsStream("pions/meeple2.png");
        InputStream inWood = ClassLoader.getSystemClassLoader().getResourceAsStream("tuiles/wood6.jpg");
        InputStream inOverlayTR = ClassLoader.getSystemClassLoader().getResourceAsStream("IHM/Jeu/redOverlay.png");
        InputStream inOverlayTB = ClassLoader.getSystemClassLoader().getResourceAsStream("IHM/Jeu/blueOverlay.png");

        nbL = jeu.getPlateau().getLignes();
        nbC = jeu.getPlateau().getColonnes();

        try {
            meeplePose1 = ImageIO.read(inMeeplePose1);
            meeplePose2 = ImageIO.read(inMeeplePose2);
            meeple1 = ImageIO.read(inMeeple1);
            meeple2 = ImageIO.read(inMeeple2);
            surbrillance = ImageIO.read(inSurbrillance);
            wood = ImageIO.read(inWood);
            redOverlay = ImageIO.read(inOverlayTR);
            blueOverlay = ImageIO.read(inOverlayTB);

        } catch (Exception e) {
            System.err.println("Problème de chargement d'image pour tuiles nulle ou tuile nulle C.png");
        }
    }

    public void tracerJeu() {
        int i = 0;
        int j;
        Plateau plateau = jeu.getPlateau();
        int index = plateau.getTuilesPoses().size() - 1;

        while (i < nbL) {
            j = 0;
            while (j < nbC) {
                if (jeu.posePossible(i, j, plateau)) {
                    if (!jeu.tuilePosee) {
                        if (jeu.isJeuTermine()) {
                            pg.tracerImage(wood, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                        } else {
                            //trace case en surbrillance
                            pg.tracerImage(surbrillance, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                        }

                    } else {
                        //trace case retourné du plateau
                        pg.tracerImage(wood, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                    }
                } else if (plateau.getTerrain(i, j) == null) {
                    //trace case retourné du plateau
                    pg.tracerImage(wood, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                } else {
                    //trace tuile du plateau
                    pg.tracerImage(jeu.getPlateau().getTerrain(i, j).getImg(), j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                    if (jeu.coupSuivant || jeu.getPlateau().getTuilesPoses().size() > 2 && !jeu.tuilePosee && !jeu.pionPose) {
                        if (jeu.getPlateau().getTuilesPoses().size() > 0 && jeu.getPlateau().getTuile(i, j) == jeu.getPlateau().getTuilesPoses().get(index - 1)) {
                            if (jeu.getJoueur() == 1) {
                                pg.tracerImage(redOverlay, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                            } else {
                                pg.tracerImage(blueOverlay, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                            }
                        }
                    } else {
                        //overlay
                        if (jeu.getPlateau().getTuile(i, j) == jeu.getPlateau().getTuilesPoses().get(index) && jeu.tuilePosee) {
                            if (jeu.getJoueur() == 1) {
                                pg.tracerImage(blueOverlay, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                            } else {
                                pg.tracerImage(redOverlay, j * largeurCase + 1, i * hauteurCase + 1, largeurCase, hauteurCase);
                            }
                        }
                    }
                    if (plateau.getTuile(i, j).getPion() != null) {
                        if (plateau.getTuile(i, j).getPion().getJoueur() == 1) {
                            tracerPion(i, j, plateau, meeple2);
                        } else {
                            tracerPion(i, j, plateau, meeple1);
                        }
                    }
                }
                j++;
            }
            i++;
        }

        int iTPose = plateau.getTuilesPoses().get(index).i;
        int jTPose = plateau.getTuilesPoses().get(index).j;
        if (jeu.getNbPions(jeu.getJoueur()) != 0) {
            if (jeu.tuilePosee && plateau.getTuile(iTPose, jTPose).getPion() == null) {
                for (Pion emplacement : plateau.getTerrain(iTPose, jTPose).getPionsEmplacement()) {
                    if (emplacement != null) {
                        int widthPion = largeurCase / 5;
                        int heightPion = hauteurCase / 4;
                        float positionX = 0;
                        float positionY = 0;
                        if (emplacement.getCoord() != null) {
                            positionX = emplacement.getCoord()[0];
                            positionY = emplacement.getCoord()[1];
                        }
                        if (positionX != 0 && positionY != 0) {
                            System.out.println("position pion " + (int) ((jTPose * largeurCase + (largeurCase / positionX)) - widthPion) / largeurCase + ", " + (int) ((iTPose * hauteurCase + (hauteurCase / positionY)) - heightPion) / hauteurCase);
                            emplacement.setWidth(widthPion);
                            emplacement.setHeight(heightPion);
                            emplacement.setX((int) ((iTPose * hauteurCase + (hauteurCase / positionY)) - heightPion));
                            emplacement.setY((int) ((jTPose * largeurCase + (largeurCase / positionX)) - widthPion / 2));
                            System.out.println("Largeur pion: " + widthPion + "Hauteur pion : " + heightPion);
                            if (jeu.getJoueur() == 1) {
                                if (plateau.nbPionsJoueur1 > 0) {
                                    emplacement.setJoueur(1);
                                    pg.tracerImage(meeplePose2, (int) ((jTPose * largeurCase + (largeurCase / positionX)) - widthPion / 2), (int) ((iTPose * hauteurCase + (hauteurCase / positionY)) - heightPion), widthPion, heightPion);
                                }
                            } else {
                                if (plateau.nbPionsJoueur2 > 0) {
                                    emplacement.setJoueur(2);
                                    pg.tracerImage(meeplePose1, (int) ((jTPose * largeurCase + (largeurCase / positionX)) - widthPion / 2), (int) ((iTPose * hauteurCase + (hauteurCase / positionY)) - heightPion), widthPion, heightPion);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (jeu.getFinInit()) {
            jeu.finInit();
        }
    }

    private void tracerPion(int i, int j, Plateau plateau, BufferedImage meeple) {
        pg.tracerImage(meeple, (int) ((j * largeurCase + (largeurCase / plateau.getTuile(i, j).getPion().getCoord()[0])) - (largeurCase / 5) / 2), (int) ((i * hauteurCase + (hauteurCase / plateau.getTuile(i, j).getPion().getCoord()[1])) - hauteurCase / 4), largeurCase / 5, hauteurCase / 4);
    }

    int largeurCase() {
        return largeurCase;
    }

    int hauteurCase() {
        return hauteurCase;
    }

    public void fixerPose(int ligne, int colonne, Tuiles t) throws CloneNotSupportedException {
        Tuiles nouveau = (Tuiles) t.clone();
        jeu.getPlateau().setTuile(nouveau, ligne, colonne);
    }

    public void setTailleCase() {
        largeurCase = jeu.getZoomFactor();
        hauteurCase = jeu.getZoomFactor();

    }
}


