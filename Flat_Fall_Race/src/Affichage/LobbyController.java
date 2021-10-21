package Affichage;

import Gameplay.Wingsuit;
import Reseau.ClientKryo;
import Reseau.Compte;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Reseau.Compte.connexion;

public class LobbyController {


    private static final String ROSE_IMAGE_LOC = "Images/rose64.png";
    private static final String BLEU_IMAGE_LOC = "Images/bleu64.png";
    private static final String ORANGE_IMAGE_LOC = "Images/orange64.png";
    private static final String VERT_IMAGE_LOC = "Images/vert64.png";

    public ListView AmisListeFX;
    public ListView JoueursListeFX;
    public Button JouerFX;
    public TextField AmiTexteFX;
    public ToggleGroup CouleurGroupeFX;
    public RadioButton VertRadioFX;
    public RadioButton RoseRadioFX;
    public RadioButton OrangeRadioFX;
    public RadioButton BleuRadioFX;
    public Slider ViesFX;
    public Button AmiBoutonFX;
    public Button MusiqueChoixFX;

    private static String identifiantUser;
    private static Integer vies;

    public static Wingsuit wingsuit = new Wingsuit();
    public static ClientKryo client = new ClientKryo();
    private Boolean isSet = false;

    public static Stage chargement = new Stage();
    private static int count;
    public static String friend;
    public static String friends;
    public static int nbrePlayers;
    private boolean sent = false;
    private boolean chefLobby = false;
    private int refreshCliked = 1;

    public void transferIdFromLogin(String identifiantLogin) {
        this.identifiantUser = identifiantLogin;
        wingsuit.setIdentifiant(this.identifiantUser);


    }

    public static String getCouleur() {
        System.out.println("Couleur : " + wingsuit.getCouleur());
        return wingsuit.getCouleur();
    }

    public static ArrayList<String> getCouleurJoueurs() {
        return ClientKryo.lobby.getCouleurLobby();
    }


    public void readCouleur(ActionEvent actionEvent) throws IOException, SQLException {
        CouleurGroupeFX.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (CouleurGroupeFX.getSelectedToggle() == RoseRadioFX) {
                    wingsuit.setCouleur(ROSE_IMAGE_LOC);
                }
                if (CouleurGroupeFX.getSelectedToggle() == BleuRadioFX) {
                    wingsuit.setCouleur(BLEU_IMAGE_LOC);
                }
                if (CouleurGroupeFX.getSelectedToggle() == VertRadioFX) {
                    wingsuit.setCouleur(VERT_IMAGE_LOC);
                }
                if (CouleurGroupeFX.getSelectedToggle() == OrangeRadioFX) {
                    wingsuit.setCouleur(ORANGE_IMAGE_LOC);
                }
            }
        });
        System.out.println(wingsuit.getWingsuit());
        client.SendWingsuit(wingsuit.getWingsuit());


    }


    public void addPlayerList() {

        ObservableList list = FXCollections.observableArrayList();
        list.addAll(client.getIdentifiantsGroupe());
        System.out.println("client.getIdentifiantsGroupe() : " + client.getIdentifiantsGroupe());
        JoueursListeFX.setItems(list);


    }


    public void Refresh() throws SQLException {
        if (!isSet) {
            wingsuit.setWingsuit();
            wingsuit.setIdentifiant(identifiantUser);
            wingsuit.setCouleur(BLEU_IMAGE_LOC);
            wingsuit.initWingsuit();

            isSet = true;
        }
        System.out.println(wingsuit.getWingsuit());
        client.SendWingsuit(wingsuit.getWingsuit());
        if (ClientKryo.lobby.getJoueur(0).get(0).toString().equals(LoginController.user)) {
            System.out.println("Chef de groupe : " + client.getIdentifiantsGroupe().get(0) == LoginController.user);
            JouerFX.setDisable(false);
            chefLobby = true;
        } else {
            System.out.println("Nbre players : "+ getNbrePlayers());
            if (getNbrePlayers() > 1) {
                if (!sent && !chefLobby) {
                    System.out.println("Pas encore sent et pas chef");
                    client.SendLaunchCommand();
                    sent = true;
                    System.out.println("Sent");
                    JouerFX.setDisable(true);
                    JouerFX.setText("En attente de " + client.getIdentifiantsGroupe().get(0));
                }
            }
            else {
                System.out.println("Nbre players : "+ getNbrePlayers());
                JouerFX.setDisable(false);
            }
        }
        refreshCliked = refreshCliked+1;
        System.out.println("Refreshes : "+ refreshCliked);
        if (refreshCliked == 2){
            RoseRadioFX.setDisable(false);
            BleuRadioFX.setDisable(false);
            VertRadioFX.setDisable(false);
            OrangeRadioFX.setDisable(false);
            ViesFX.setDisable(false);
            AmiBoutonFX.setDisable(false);
            AmiTexteFX.setDisable(false);
            if (chefLobby){
                JouerFX.setDisable(false);
            }
        }
        addPlayerList();
        addFriendsList();


    }

    public void AddFriend(ActionEvent actionEvent) throws SQLException, ClassNotFoundException { // Ajoute l'ami à la liste des amis du joueur
        friend = AmiTexteFX.getText();
        Compte.getAmis();
        String sql = "UPDATE users SET amis = CONCAT(?,',',?) WHERE pseudo = ?";

        PreparedStatement preparedStatement = connexion.prepareStatement(sql);

        preparedStatement.setString(1, LobbyController.friends);
        preparedStatement.setString(2, friend);
        preparedStatement.setString(3, LoginController.user);


        try {
            preparedStatement.executeUpdate();
            addFriendsList();
            AmiTexteFX.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //...
        }

    }

    public void addFriendsList() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        List<String> items = Arrays.asList(Compte.getAmis().split("\\s*,\\s*"));
        list.addAll(items);
        System.out.println("friends : " + friends);
        AmisListeFX.setItems(list);
    }


    public void getVies(MouseEvent dragEvent) {
        ViesFX.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                vies = Math.toIntExact(Math.round(ViesFX.getValue()));
                System.out.println("Vies : " + vies);
            }
        });
    }

    public void LaunchGame(ActionEvent actionEvent) throws IOException {
        //Envoie la commande au serveur pour générer les troncons
        client.SendLaunchCommand();

        //Afficher la fenêtre du chargement
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/Chargement.fxml"));
        Parent root = loader.load();

        chargement.setTitle("Chargement");
        Image icon = new Image("/Images/FFR.png");
        chargement.getIcons().add(icon);
        Scene scene = new Scene(root);
        chargement.setResizable(false);
        chargement.setScene(scene);
        chargement.show();

        //Fermer la fenêtre du lobby
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static int getNbrePlayers() {
        nbrePlayers = getCouleurJoueurs().size() + 1;
        return nbrePlayers;
    }


}