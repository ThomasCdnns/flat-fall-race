package Reseau;

import Affichage.LobbyController;
import Affichage.LoginController;
import com.esotericsoftware.kryonet.Client;

import java.sql.*;
import java.util.Properties;

import static Affichage.LobbyController.friends;

public class Compte {
    private Boolean logged;
    private String id;
    private Client client;
    public static Connection connexion;
    private static int count;

    public Compte() throws SQLException {
    }

    public void setId(String identifiant) {
        this.id = identifiant;
    }

    public String getId() {
        return id;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }



    public static void getLogged() throws ClassNotFoundException, SQLException { //Se connecte à la BDD et vérifie l'existence du joueur
        Class.forName("com.mysql.jdbc.Driver");
        Properties props = new Properties();
        String url = "jdbc:mysql://SERVER_IP/ffr";
        props.setProperty("user", "USER");
        props.setProperty("password", "PASSWORD");
        try {
            connexion = DriverManager.getConnection(url, props);
            System.out.println("=> Connecté à la base de donné");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql = "select count(id) as count from users where pseudo=?";

        PreparedStatement preparedStatement = connexion.prepareStatement(sql);

        preparedStatement.setString(1, LoginController.user);

        try {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                count = result.getInt("count");
                if (count >= 1) {
                    System.out.println(LoginController.user + " existe");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //...
        }
    }
    public static String getAmis() throws SQLException {
        String sql = "SELECT amis FROM users WHERE pseudo = ?"; //Récupère la liste existante des amis

        PreparedStatement preparedStatement = Compte.connexion.prepareStatement(sql);

        preparedStatement.setString(1, LoginController.user);

        try {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                friends = result.getString("amis");
                System.out.println("Friends : " + friends);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //...
        }
        return friends;
    }


}

/**/
