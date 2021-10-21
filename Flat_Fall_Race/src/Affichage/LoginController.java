package Affichage;

import Reseau.Compte;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static Reseau.Compte.getAmis;
import static Reseau.Compte.getLogged;

public class LoginController {
    public Button ConnexionFX;
    public PasswordField MdpFX;
    public TextField IdentifiantFX;
    public static String user;

    public void submit(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        //Stocker le nom du user
        user = IdentifiantFX.getText();
        getLogged();
        getAmis();

        //Afficher la fenêtre du lobby
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/Lobby.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Lobby");
        Image icon = new Image("/Images/FFR.png");
        newStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();



        loadSceneAndSendMessage();

        //Fermer la fenêtre de login
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void loadSceneAndSendMessage() {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Lobby.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            LobbyController lobbyController = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            lobbyController.transferIdFromLogin(IdentifiantFX.getText());

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}