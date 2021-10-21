package Affichage.Defilement;

import Affichage.LobbyController;
import Affichage.LoginController;
import Reseau.ClientKryo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LancementDefil extends Application {
    //Décors
    URL urlArbreFXML = new File("src/FXML/Obstacle/Arbre.fxml").toURI().toURL();
    URL urlSoucheFXML = new File("src/FXML/Obstacle/Souche.fxml").toURI().toURL();
    URL urlRocherFXML = new File("src/FXML/Obstacle/Rocher.fxml").toURI().toURL();
    URL urlMaisonFXML = new File("src/FXML/Obstacle/Maison.fxml").toURI().toURL();
    URL urlFondFXML = new File("src/FXML/Fond.fxml").toURI().toURL();

    //skins
    URL urlBleuFXML = new File("src/FXML/Skin/Bleu.fxml").toURI().toURL();
    URL urlVertFXML = new File("src/FXML/Skin/Vert.fxml").toURI().toURL();
    URL urlRoseFXML = new File("src/FXML/Skin/Rose.fxml").toURI().toURL();
    URL urlOrangeFXML = new File("src/FXML/Skin/Orange.fxml").toURI().toURL();

    private double x, y;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenHeight = screenSize.height;
    double screenWidth = screenSize.width;
    public double W = 1280, H = 800;
    boolean goEast, goWest;
    private ArrayList<Object> setPositionClient;
    private int count = 0;
    private final Text text = new Text(Integer.toString(count));

    private static final String ROSE_IMAGE_LOC = "Images/rose64.png";
    private static final String BLEU_IMAGE_LOC = "Images/bleu64.png";
    private static final String ORANGE_IMAGE_LOC = "Images/orange64.png";
    private static final String VERT_IMAGE_LOC = "Images/vert64.png";

    private static Parent[] Wing = new Parent[2];
    private static Parent Wing1;
    private static int[] places = {1, 2, 3, 4};


    public LancementDefil() throws IOException {
    }


    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {

        final Parent Arbre = FXMLLoader.load(urlArbreFXML);
        final Parent Souche = FXMLLoader.load(urlSoucheFXML);
        final Parent Rocher = FXMLLoader.load(urlRocherFXML);
        final Parent Maison = FXMLLoader.load(urlMaisonFXML);
        final Parent Fond = FXMLLoader.load(urlFondFXML);
        String URLImage = LobbyController.getCouleur();


        if (URLImage == ROSE_IMAGE_LOC) {
            Wing1 = FXMLLoader.load(urlRoseFXML);
        }
        if (URLImage == BLEU_IMAGE_LOC) {
            Wing1 = FXMLLoader.load(urlBleuFXML);
        }
        if (URLImage == VERT_IMAGE_LOC) {
            Wing1 = FXMLLoader.load(urlVertFXML);
        }
        if (URLImage == ORANGE_IMAGE_LOC) {
            Wing1 = FXMLLoader.load(urlOrangeFXML);
        }

        Group group = new Group();

        group.getChildren().setAll(Fond, Arbre, Souche, Rocher, Maison, Wing1);


        for (int i = 0; i < LobbyController.getCouleurJoueurs().size(); i++) {
            String couleurJoueur = LobbyController.getCouleurJoueurs().get(i);
            if (couleurJoueur.equals(ROSE_IMAGE_LOC)) {
                Wing[i] = FXMLLoader.load(urlRoseFXML);
            }
            if (couleurJoueur.equals(BLEU_IMAGE_LOC)) {
                Wing[i] = FXMLLoader.load(urlBleuFXML);
            }
            if (couleurJoueur.equals(VERT_IMAGE_LOC)) {
                Wing[i] = FXMLLoader.load(urlVertFXML);
            }
            if (couleurJoueur.equals(ORANGE_IMAGE_LOC)) {
                Wing[i] = FXMLLoader.load(urlOrangeFXML);
            }
            System.out.println("Couleur Joueur : " + couleurJoueur + " et Wing[" + i + "] : " + Wing[i]);
            group.getChildren().add(Wing[i]);

        }

        final Pane root = new Pane();
        root.getChildren().setAll(group);
        final Scene scene = new Scene(root, W, H);

        //Set positions joueurs équidistantes en début de partie
        int nbrePlayers = LobbyController.getNbrePlayers();
        System.out.println("nbrePlayers : " + nbrePlayers);

        int placeLocale = ClientKryo.lobby.getIdentifiants().indexOf(LoginController.user);
        if (placeLocale == 0) {
            placeLocale = 1;
        }
        ArrayList<String> listeCouleurs = ClientKryo.lobby.getCouleurLobby();
        int[] placeRestantes = places;
        int[] placesJoueursMulti = new int[placeRestantes.length - 1];
        System.arraycopy(placeRestantes, 0, placesJoueursMulti, 0, placeLocale);
        System.arraycopy(placeRestantes, placeLocale + 1, placesJoueursMulti, placeLocale, placeRestantes.length - placeLocale - 1);

        moveWingsuitTo(1280 / (nbrePlayers + 1) * placeLocale, 400, Wing1);
        System.out.println("Place Locale : " + placeLocale);

        for (String couleur : listeCouleurs) {
            int i = listeCouleurs.indexOf(couleur);
            moveWingsuitTo((1280 / (nbrePlayers + 1)) * placesJoueursMulti[i] + 1, 400, Wing[i]);
            System.out.println("placesJoueursMulti[i] : " + (placesJoueursMulti[i] + 1));
        }


        Image icon = new Image("/Images/FFR.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Flat Fall Race");
        primaryStage.setScene(scene);
        primaryStage.show();

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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if (goEast) dx += 3;
                if (goWest) dx -= 3;

                moveWingsuitBy(dx, dy);
            }
        };
        timer.start();
    }


    public static void movePlayers() {
        for (int k = 0; k < ClientKryo.lobby.getJoueurs().size() - 1; k++) {
            ArrayList<Object> joueursMulti = ClientKryo.lobby.getJoueurs();
            if (((List<?>) joueursMulti.get(k)).get(0).toString().equals(LoginController.user)) { //Exclure la couleur du joueur local
                continue;
            }
            else{
                ArrayList<Double> position = (ArrayList<Double>) ((ArrayList<Object>) ClientKryo.lobby.getJoueur(k)).get(3);
                moveWingsuitTo(position.get(0), position.get(1), Wing[k]);
            }


        }

    }

    private void moveWingsuitBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = Wing1.getBoundsInLocal().getWidth() / 2;
        final double cy = Wing1.getBoundsInLocal().getHeight() / 2;

        x = cx + Wing1.getLayoutX() + dx;
        y = cy + Wing1.getLayoutY() + dy;

        getNewPosition();
        moveWingsuitTo(x, y, Wing1);
        movePlayers();

    }

    public void getNewPosition() {
        setPositionClient = new ArrayList<Object>();
        setPositionClient.add(x);
        setPositionClient.add(y);
        LobbyController.wingsuit.refreshWingsuit(this.setPositionClient);
        System.out.println(LobbyController.wingsuit.getWingsuit());
        LobbyController.client.SendWingsuit(LobbyController.wingsuit.getWingsuit());
    }

    public static void moveWingsuitTo(double x, double y, Parent Wing) {
        final double cx = Wing.getBoundsInLocal().getWidth() / 2;
        final double cy = Wing.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= 1280 &&
                y - cy >= 0 &&
                y + cy <= 800) {
            Wing.relocate(x - cx, y - cy);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
