package Gameplay;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {

    private String type;
    private String effet;
    public ArrayList<String> types;
    private double X;
    private double Y;
    private ArrayList<Double> positionObstacle;
    private ArrayList<Object> obstacle;

    public ArrayList<String> setTypes() {
        types = new ArrayList<String>();
        types.add("Arbre");
        types.add("Rocher");
        types.add("Maison");
        types.add("Souche");
        types.add("Up");
        types.add("Down");
        return types;
    }

    public ArrayList<Object> getObstacle() {
        this.setTypes();
        Random a = new Random();
        int indexLow = 0;
        int indexHigh = 5;
        int index = a.nextInt(indexHigh - indexLow) + indexLow;
        type = types.get(index);


        if (type == "Up") {
            effet = "upAltitude";
        }

        if (type == "Down") {
            effet = "downAltitude";
        }

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //double screenHeight = screenSize.height;
        //double screenWidth = screenSize.width;
        double W = 1920, H = 1080;
        double Wath = (20 * W) / 100, Hath = H;      /*Largeur et hauteur de l'ATH d'un seul côté (on a une partie d'ATH à gauche une autre à droite
                                                       les deux font la même taille (WathxHath)*/
        double Wtroncon = W - Wath, Htroncon = H;    //Largeur et hauteur du tronçon

        Random b = new Random();
        double Xmin = 0 + Wath;
        double Xmax = Wtroncon;
        X = Xmin + (Xmax - Xmin) * (Math.floor(b.nextDouble() * 100) / 100);
        X = Math.floor(X * 100) / 100;

        Random c = new Random();
        double Ymin = 0;
        double Ymax = H;
        Y = Ymin + (Ymax - Ymin) * (Math.floor(c.nextDouble() * 100) / 100);
        Y = Math.floor(Y * 100) / 100;

        positionObstacle = new ArrayList<>();

        positionObstacle.add(X);
        positionObstacle.add(Y);

        obstacle = new ArrayList<Object>();
        obstacle.add(type);
        obstacle.add(effet);
        obstacle.add(positionObstacle);

        return obstacle;

    }
}
