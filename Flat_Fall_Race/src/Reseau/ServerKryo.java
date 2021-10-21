package Reseau;

import Gameplay.Joueurs;
import Generation.Troncons;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.ArrayList;

public class ServerKryo {

    int playersReady = 0;

    public ServerKryo() {
        Log.set(Log.LEVEL_DEBUG);

        final Boolean[] added = {false};


        Joueurs lobby = new Joueurs();
        Server server = new Server(1024 * 1024, 1024 * 1024);
        KryoUtil.registerServerClasses(server);
        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                System.out.println("Connected to : " + connection.getRemoteAddressTCP().getHostString());
                connection.sendTCP("Connected successfully");
            }

            @Override
            public void disconnected(Connection connection) {
                System.out.println("A client disconnected");
            }

            @Override
            public void received(Connection connection, Object object) {

                System.out.println("Message from client : " + object);
                if (object instanceof ArrayList<?>) {
                    lobby.initJoueurs(object);
                    if (!lobby.getJoueurs().isEmpty()) {
                        lobby.checkJoueurs(object);
                    }

                    server.sendToAllUDP(lobby.getJoueurs());
                    System.out.println("Lobby actualisé et envoyé");
                }

                if ((object.toString()).equals("Launch")) {
                    playersReady++;
                    if (playersReady == lobby.getIdentifiants().size()) {
                        System.out.println("Génération de la map en cours...");
                        Troncons troncons = new Troncons();
                        troncons.generateTroncons(); // <----- Long ici ?
                        System.out.println("Génération de la map terminée");
                        for (int i = 0; i < troncons.getNbTroncons(); i++) {
                            server.sendToAllUDP(troncons.getTroncons().get(i));
                            System.out.println("Troncon Map " + i + "/" + troncons.getNbTroncons() + " envoyé");
                        }
                        server.sendToAllUDP(troncons.getTroncons());
                        System.out.println("Map envoyée");
                    }

                }


            }
        });

        try {
            server.bind(KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
        } catch (
                IOException ex) {
            System.out.println(ex);
        }

        server.start();
    }


    public static void main(String[] args) {
        System.out.println("Server starts...");
        ServerKryo server = new ServerKryo();
        System.out.println("Server started");
        System.out.println("Sending and Listening");

    }
}