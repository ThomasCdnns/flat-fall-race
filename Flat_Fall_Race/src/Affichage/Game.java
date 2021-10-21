package Affichage;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static Affichage.LobbyController.wingsuit;

public class Game extends Application {

    private double x, y;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenHeight = screenSize.height;
    double screenWidth = screenSize.width;
    public double W = 500, H = 400;
    private Node wing;
    boolean goEast, goWest;
    private ArrayList<Object> setPositionClient;
    private int count = 0;
    private final Text text = new Text(Integer.toString(count));

    @Override
    public void start(Stage stage) throws Exception {


        Image icon = new Image("/Images/FFR.png");
        stage.getIcons().add(icon);
        stage.setTitle("Flat Fall Race");
        String URLImage = LobbyController.getCouleur();
        System.out.println("URLImage : " + URLImage);
        wing = new ImageView(new Image(URLImage));
        //LobbyController.wingsuit.getCouleur()

        Group ravin = new Group(wing);

        moveWingsuitTo(W / 2, H / 2);

        Scene scene = new Scene(ravin, W, H, Color.WHITESMOKE);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Q:
                        goWest = true;
                        break;
                    case D:
                        goEast = true;
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Q:
                        goWest = false;
                        break;
                    case D:
                        goEast = false;
                        break;
                }
            }
        });
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if (goEast) dx += 10;
                if (goWest) dx -= 10;

                moveWingsuitBy(dx, dy);
            }
        };
        timer.start();
    }

    private void moveWingsuitBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = wing.getBoundsInLocal().getWidth() / 2;
        final double cy = wing.getBoundsInLocal().getHeight() / 2;

        x = cx + wing.getLayoutX() + dx;
        y = cy + wing.getLayoutY() + dy;

        getNewPosition();
        moveWingsuitTo(x, y);

    }

    public void getNewPosition() {
        setPositionClient = new ArrayList<Object>();
        setPositionClient.add(x);
        setPositionClient.add(y);
        wingsuit.refreshWingsuit(this.setPositionClient);
        System.out.println(wingsuit.getWingsuit());
        LobbyController.client.SendWingsuit(wingsuit.getWingsuit());
    }

    private void moveWingsuitTo(double x, double y) {
        final double cx = wing.getBoundsInLocal().getWidth() / 2;
        final double cy = wing.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            wing.relocate(x - cx, y - cy);
        }
    }

    public void main(String[] args) throws IOException {
        launch(args);
    }

}