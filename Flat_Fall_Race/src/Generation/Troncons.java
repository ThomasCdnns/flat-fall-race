package Generation;

import Affichage.ChargementController;
import Affichage.Game;
import Gameplay.Obstacle;
import com.esotericsoftware.kryo.serializers.MapSerializer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static javafx.application.Application.launch;


public class Troncons {

    private Integer numeroTroncon;
    private Integer nbObstacleMax = 10;
    private Integer nbObstacleMin = 8;
    private static int nbTroncons = 50;
    private static ArrayList<Object> troncons = new ArrayList<Object>();;
    private static ArrayList<Object> troncon;
    private Obstacle obstacle;
    private int i = 0;
    private static ChargementController chargementController = new ChargementController();


    public int getNbTroncons() {
        return nbTroncons;
    }
    public void generateTroncons() {
        numeroTroncon = 0;


        for (int i = 0; i < nbTroncons; i++) {
            //i = numeroTroncon
            troncon = new ArrayList<Object>();

            Random r = new Random();
            int low = nbObstacleMin;
            int high = nbObstacleMax + 1;
            int nbObstacle = r.nextInt(high - low) + low;

            for (int k = 0; k < nbObstacle; k++) {
                obstacle = new Obstacle();
                troncon.add(obstacle.getObstacle());

            }

            numeroTroncon = numeroTroncon + 1;
            troncons.add(troncon);
        }

        System.out.println("Troncons set");
    }

    public void addTroncons(ArrayList<Object> newList) throws Exception {
        if (troncons.isEmpty()){
            troncons = newList;
        }
        else {
            troncons.add(newList);
            if (troncons.size() == 50){
                chargementController.downloadDone();
            }
        }

    }

    public ArrayList<Object> getTroncons() {
        return troncons;
    }

    public ArrayList<Object> getTroncon(int indexTroncon) {
        ArrayList<Object> obstaclesTroncon = new ArrayList<Object>();
        obstaclesTroncon = ((ArrayList<Object>) troncons.get(indexTroncon));
        return obstaclesTroncon;
    }

    /*public void loadObstacle(obstaclesTroncon) {
        for (element obstacle : obstaclesTroncon){
            String type = obstacle.get(0);
            Double X = ((ArrayList<Double>) obstacle.get(2)).get(0);
            Double Y = ((ArrayList<Double>) obstacle.get(2)).get(1);
        }
    }*/

}



        /*return obstacles;
    }

    /*public ArrayList<Object> setTroncons(){
        troncons = new ArrayList<Object>();
        for (int i = 0; i < nbTroncons; i++){
            //i = numeroTroncon
            troncon = new ArrayList<Object>();
            troncon.add(i);
            troncon.add(setTroncon(i));
            System.out.println(troncon);
            troncons.add(troncon);
        }

        return troncons;
    }
}*/

