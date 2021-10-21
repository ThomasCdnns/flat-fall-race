import Affichage.AffichageLogin;
import Affichage.Game;
import Reseau.Compte;
import javafx.application.Application;

public class FlatFallRace {

    public static void main(String[] args) throws Exception {

        AffichageLogin login = new AffichageLogin();
        new Thread(() -> Application.launch(AffichageLogin.class)).start();
    }
}
