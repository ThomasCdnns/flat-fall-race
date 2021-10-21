package Affichage.Defilement.Obstacle.Controller;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MaisonController implements Initializable {
    public AnchorPane Maison;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(4125));
        transition.setNode(Maison);
        transition.setFromY(800);
        transition.setFromX(500);
        transition.setToY(-300);
        transition.setToX(500);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(false);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.play();
    }

}