package Affichage;

import Affichage.Defilement.LancementDefil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ChargementController {
    public static void downloadDone() throws Exception {
        System.out.println("Lancement du jeu...");

        Thread.sleep(1000);

        Platform.runLater(() -> {
            //Fermer la fenêtre de login
            LobbyController.chargement.close();
        });

        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    new LancementDefil() .start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




    }
}
