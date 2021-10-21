package Reseau;

import Gameplay.Joueurs;
import Gameplay.Obstacle;
import Gameplay.Parcours;
import Gameplay.Wingsuit;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class KryoUtil {

    public static final int TCP_PORT = 54555;
    public static final int UDP_PORT = 54777;

    public static void registerServerClasses(Server server) {
        register(server.getKryo());
    }

    public static void registerClientClass(Client client) {
        register(client.getKryo());
    }

    private static void register(Kryo kryo) {
        kryo.register(Joueurs.class);
        kryo.register(Parcours.class);
        kryo.register(Wingsuit.class);
        kryo.register(java.util.ArrayList.class);

    }
}