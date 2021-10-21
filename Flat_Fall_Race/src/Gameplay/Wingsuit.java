package Gameplay;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Wingsuit<newPosition> {
    private ArrayList<Object> wingsuitContainer = new ArrayList<Object>();
    private String identifiant;
    private String couleur;
    private ArrayList<Object> position = new ArrayList<Object>();
    private Integer vitesse = 1;
    private Double altitude = 0.0;
    private Double direction = 0.0;
    private String etat = "none";
    private int x;
    private int y;
    private int dx;
    private int dy;
    private Double malus = 4.0;
    private Double bonus = 2.0;
    private static final String ROSE_IMAGE_LOC = "Images/rose64.png";
    private static final String BLEU_IMAGE_LOC = "Images/bleu64.png";
    private static final String ORANGE_IMAGE_LOC = "Images/orange64.png";
    private static final String VERT_IMAGE_LOC = "Images/vert64.png";


    public Wingsuit() {
    }

    public void initWingsuit() {
        this.setEtat("none");
        position.add(0.0);
        position.add(0.0);
    }

    public void setIdentifiant(String newvar) {
        identifiant = newvar;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setCouleur(String radioChoice) {
        couleur = radioChoice;
        wingsuitContainer.set(2, radioChoice);
    }

    public String getCouleur() {
        return couleur;
    }

    public void setPosition(ArrayList<Object> newPosition) {
        position.add(newPosition.get(0));
        position.add(newPosition.get(1));
    }

    public ArrayList<Object> getPosition() {
        return position;
    }

    public void setVitesse(Integer newVar) {
        vitesse = newVar;
    }

    public Integer getVitesse() {
        return vitesse;
    }

    public void setAltitude(Double newVar) {
        altitude = newVar;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setDirection(Double newVar) {
        direction = newVar;
    }

    public Double getDirection() {
        return direction;
    }

    public void setEtat(String newVar) {
        etat = newVar;
    }

    public String getEtat() {
        return etat;
    }

    public void setWingsuit() {
        wingsuitContainer.add(this.identifiant);
        wingsuitContainer.add(this.etat);
        wingsuitContainer.add(this.couleur);
        wingsuitContainer.add(this.position);
        wingsuitContainer.add(this.vitesse);
        wingsuitContainer.add(this.altitude);
        wingsuitContainer.add(this.direction);
    }

    public ArrayList<Object> getWingsuit() {
        return wingsuitContainer;
    }


    public ArrayList<Object> refreshWingsuit(ArrayList<Object> newPosition) {
        wingsuitContainer.set(3, newPosition);
        return wingsuitContainer;
    }

    public void deplacer() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }

    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Q) {
            dx = -10;
        }
        if (key == KeyEvent.VK_D) {
            dx = 10;
        }
    }

    public void updateWingsuit() {
        while (altitude > 0) {
            this.deplacer();
            System.out.println(this.getPosition());
        }

    }

    public Boolean collision() {
        return null;
    }

    public double descente() {
        if (this.collision()) {
            altitude -= malus;
            return altitude;
        } else {
            return altitude;
        }
    }

    public double montee() {
        if (this.collision()) {
            altitude -= bonus;
            return altitude;
        } else {
            return altitude;
        }
    }
}
