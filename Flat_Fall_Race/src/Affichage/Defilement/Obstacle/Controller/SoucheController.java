package Affichage.Defilement.Obstacle.Controller;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SoucheController implements Initializable
{
    public AnchorPane Souche;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(3375));
        transition.setNode(Souche);
        transition.setFromY(800);
        transition.setFromX(700);
        transition.setToY(-100);
        transition.setToX(700);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(false);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.play();

    }
}