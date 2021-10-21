package Affichage.Defilement;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFond implements Initializable {
    public AnchorPane Fond;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(6000));
        transition.setNode(Fond);
        transition.setFromY(0);
        transition.setFromX(0);
        transition.setToY(-1600);
        transition.setToX(0);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(false);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.play();

    }

}
