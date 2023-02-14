package model.tuiles;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

import model.Pion;
import model.terrain.*;

public class Tuiles implements Cloneable {
    int exemplaire;
    int rotationsPoss;
    public boolean possedeAbbaye = false;
    public TerrainTypesCotes[] cotes;
    public Tuiles[] rotations;
    public TerrainTypesCentre[] centre;
    public boolean possedeRoute = false;
    public boolean possedeChateauCentre = false;
    public boolean possedeChateauCote = false;
    String name;
    BufferedImage img;
    public int i, j;
    public Pion pion;
    public Integer[] indicePions;
    public Pion[] pionsEmplacement;
    Tuiles tuileOriginel;

    public Tuiles() {

    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();    // return shallow copy
    }

    public Tuiles(int exemplaire, int rotationsPoss, String nom) {
        this.exemplaire = exemplaire;
        this.rotationsPoss = rotationsPoss;
        this.name = nom;
        this.rotations = new Tuiles[rotationsPoss];
        tuileOriginel = this;
        pionsEmplacement = new Pion[8];
    }

    public void setTypesCotes(TerrainTypesCotes... cotes) {
        this.cotes = cotes;
    }

    public void setTypesCentre(TerrainTypesCentre... centre) {
        this.centre = centre;
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    public void creerRotation() {
        int i;
        for (i = 0; i < rotationsPoss - 1; i++) {
            rotations[i] = new Tuiles(exemplaire, rotationsPoss, name);
            String tuileNom = removeLastChar(name) + (i + 1);
            rotations[i].setName(tuileNom);
            rotations[i].setImage();
            rotations[i].i = 0;
            rotations[i].j = 0;
        }
        rotations[i] = tuileOriginel;
        rotations[i].i = 0;
        rotations[i].j = 0;
    }

    public void rotationCote() {
        TerrainTypesCotes[] tmpTypes;
        TerrainTypesCotes[] nouveau = cotes.clone();
        int j;
        int w;
        for (w = 0; w < rotationsPoss - 1; w++) {
            tmpTypes = nouveau.clone();
            j = 0;
            for (int i = 2; i < cotes.length; i += 2) {
                nouveau[i] = tmpTypes[j];
                nouveau[i + 1] = tmpTypes[j + 1];
                j += 2;
            }
            nouveau[0] = tmpTypes[j];
            nouveau[1] = tmpTypes[j + 1];
            rotations[w].cotes = nouveau.clone();
        }
        rotations[w].cotes = cotes;
    }

    public void rotationAxes() {
        TerrainTypesCentre[] tmp = null;
        TerrainTypesCentre[] nouvelle = centre.clone();
        int j;
        int w;
        for (w = 0; w < rotationsPoss - 1; w++) {
            tmp = nouvelle.clone();
            j = 0;
            for (int k = 1; k < centre.length - 1; k++) {
                nouvelle[k] = tmp[j];
                j++;
            }
            nouvelle[0] = tmp[j];
            rotations[w].centre = nouvelle.clone();

        }
        rotations[w].centre = centre;
    }

    public void rotationIndicePions() {

        Integer[] nouvelle = indicePions.clone();
        int j;
        int w;
        for (w = 0; w < rotationsPoss - 1; w++) {
            Integer[] tmp = nouvelle.clone();
            j = 0;
            for (int i = 2; i < 8; i += 2) {
                nouvelle[i] = tmp[j];
                nouvelle[i + 1] = tmp[j + 1];
                j += 2;
            }
            nouvelle[0] = tmp[j];
            nouvelle[1] = tmp[j + 1];

            int r = 8;
            for (int k = 9; k < indicePions.length - 1; k++) {
                nouvelle[k] = tmp[r];
                r++;
            }
            nouvelle[8] = tmp[r];
            rotations[w].indicePions = nouvelle.clone();
        }
        rotations[w].indicePions = indicePions;
    }

    public void setCoord(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public void setImage() {
        String filename = "tuiles/" + name + ".jpg";
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);

        try {
            img = ImageIO.read(in);
        } catch (Exception e) {
            System.err.println("Problème de chargement d'image pour " + name + ".jpg");
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public int getRotationsPoss() {
        return rotationsPoss;
    }

    public void setIndicePions(Integer... indicePions) {
        this.indicePions = indicePions;
    }

    public Pion[] getPionsEmplacement() {
        return pionsEmplacement;
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }

    public void removePion() {
        pion = null;
    }

    public Pion getPion() {
        return pion;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
