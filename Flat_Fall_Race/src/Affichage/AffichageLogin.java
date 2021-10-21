package Affichage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AffichageLogin extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Login.fxml")));

        Scene scene = new Scene(root);

        stage.setTitle("Login Flat Fall Race");
        stage.setScene(scene);
        stage.show();

    }


}
