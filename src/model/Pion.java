package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Pion {
    int indice;
    BufferedImage img;
    String name = "meeple";
    int joueur;
    Float[] coord;
    int width = 0;
    int height = 0;
    int x = 0;
    int y = 0;

    public Pion(int indice) {
        this.indice = indice;
        coord = new Float[2];
    }

    public int getIndice() {
        return indice;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    public void setImagePion() {
        String filename = "pions/" + name + joueur + ".png";
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);

        try {
            img = ImageIO.read(in);
        } catch (Exception e) {
            System.err.println("Problème de chargement d'image pour " + name + ".png");
        }
    }

    public void setCoord(float x, float y) {
        coord[0] = x;
        coord[1] = y;
    }

    public Float[] getCoord() {
        return coord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getJoueur() {
        return joueur;
    }

}
