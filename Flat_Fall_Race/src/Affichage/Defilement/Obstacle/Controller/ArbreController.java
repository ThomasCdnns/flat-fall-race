package Affichage.Defilement.Obstacle.Controller;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ArbreController implements Initializable {
    public AnchorPane Arbre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(4500));
        transition.setNode(Arbre);
        transition.setFromY(800);
        transition.setFromX(280);
        transition.setToY(-400);
        transition.setToX(280);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(false);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.play();

    }

}