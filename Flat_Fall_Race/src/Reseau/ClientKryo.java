package Reseau;

import Affichage.ChargementController;
import Affichage.Defilement.LancementDefil;
import Gameplay.Joueurs;
import Generation.Troncons;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ClientKryo extends Listener {


    private static Client client;
    private ArrayList<Object> newList = new ArrayList<Object>();
    public static Joueurs lobby = new Joueurs();
    private static Troncons troncons = new Troncons();


    public ClientKryo() {
        Log.set(Log.LEVEL_DEBUG);

        client = new com.esotericsoftware.kryonet.Client(1024 * 1024, 1024 * 1024);
        KryoUtil.registerClientClass(client);

        new Thread(client).start();

        client.addListener(new Listener() {

            public ArrayList<Object> convertObjectToALO(Object obj) { //Convertir l'objet recu en ArrayListObjet
                List<?> list = new ArrayList<>();
                if (obj.getClass().isArray()) {
                    list = Arrays.asList((Object[]) obj);
                } else if (obj instanceof Collection) {
                    list = new ArrayList<>((Collection<?>) obj);
                }
                return (ArrayList<Object>) list;
            }

            @Override
            public void connected(Connection connection) {
                System.out.println("Connecté");
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof ArrayList<?>) {
                    newList = convertObjectToALO(object);
                    if (((ArrayList<Object>) newList.get(0)).size() == 7) {
                        System.out.println("Received Lobby from host : " + newList);
                        lobby.equalsJoueurs(newList);
                        //LancementDefil.movePlayers();
                    } else {
                        System.out.println("Received Map from host : " + newList);
                        try {
                            troncons.addTroncons(newList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void disconnected(Connection connection) {
            }
        });

        try {
            /* Make sure to connect using both tcp and udp port */
            client.connect(5000, "SERVER_IP", KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void ClientLaunch() {

        System.out.println("Client start...");
        Log.set(Log.LEVEL_DEBUG);
        client = new Client();
        new Thread(client).start();
        System.out.println("Client started");
        client.addListener(new ClientKryo());
        System.out.println("Client listening and sending");

    }

    public void SendWingsuit(ArrayList<Object> wingsuit) {
        client.sendUDP(wingsuit);
        System.out.println("Position envoyée");
    }

    public void SendLaunchCommand() {
        client.sendUDP("Launch");
    }

    public ArrayList<String> getIdentifiantsGroupe() {
        System.out.println("lobby : " + lobby.getIdentifiants());
        return lobby.getIdentifiants();
    }

}
