package Affichage.Defilement.Obstacle.Controller;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class RocherController implements Initializable
{
    public AnchorPane Rocher;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(3750));
        transition.setNode(Rocher);
        transition.setFromY(800);
        transition.setFromX(900);
        transition.setToY(-200);
        transition.setToX(900);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(false);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.play();

    }
}