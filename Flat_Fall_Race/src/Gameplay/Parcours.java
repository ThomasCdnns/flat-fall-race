package Gameplay;

import Gameplay.Joueurs;
import Gameplay.Obstacle;

import java.util.ArrayList;

public class Parcours {

    private ArrayList ligneDepart;
    private ArrayList bordureG;
    private ArrayList bordureD;
    private ArrayList<Object> obstacles;
    private ArrayList<Joueurs> positions;

    public Parcours() {
    }

    public void setLigneDepart(ArrayList newVar) {
        ligneDepart = newVar;
    }

    public ArrayList getLigneDepart() {
        return ligneDepart;
    }

    public void setBordureG(ArrayList newVar) {
        bordureG = newVar;
    }

    public ArrayList getBordureG() {
        return bordureG;
    }

    public void setBordureD(ArrayList newVar) {
        bordureD = newVar;
    }

    public ArrayList getBordureD() {
        return bordureD;
    }

    public void setObstacles(ArrayList<Object> newVar) {

        obstacles = newVar;
    }

    public ArrayList<Object> getObstacles() {
        return obstacles;
    }

    public void setPositions(ArrayList<Joueurs> newVar) {
        positions = newVar;
    }

    public ArrayList<Joueurs> getPositions() {
        return positions;
    }

    public void Parcours() {
    }

    public ArrayList getStart() {
        return null;
    }

    public ArrayList getTroncon(Integer numeroTroncon) {
        return null;
    }


}
