package Gameplay;

import Affichage.Defilement.LancementDefil;
import Affichage.LobbyController;
import Affichage.LoginController;
import javafx.beans.binding.ObjectExpression;

import java.util.ArrayList;
import java.util.List;

import static Affichage.Defilement.LancementDefil.*;

public class Joueurs {

    private ArrayList<Object> joueurs = new ArrayList<Object>();
    boolean added = false;
    private static String couleurJoueur;

    public Joueurs() {
    }


    public void equalsJoueurs(ArrayList<Object> newLobby) {
        joueurs = newLobby;
    }

    public ArrayList<Object> getJoueurs() {
        return joueurs;
    }

    public ArrayList<Object> getJoueur(Integer indexJoueur) {
        ArrayList<Object> joueur = new ArrayList<>();
        joueur = (ArrayList<Object>) this.getJoueurs().get(indexJoueur);
        return joueur;
    }

    public ArrayList<Object> getPositions() {
        return null;
    }

    public ArrayList<Object> getPosition(String idJoueur) {
        return null;
    }

    public void initJoueurs(Object object) {
        if (joueurs.isEmpty()) {
            joueurs.add(object);
            System.out.println("Nouveau joueur ajouté : " + joueurs);
        }

    }

    public void checkJoueurs(Object object) {
        added = false;
        String identifiantRequete = ((List<?>) object).get(0).toString();
        int index = 0;
        for (Object joueur : joueurs) {
            String identifiantJoueurs = ((List<?>) joueur).get(0).toString();
            if (identifiantRequete.equals(identifiantJoueurs)) {
                joueurs.set(index, object);
                added = true;
                System.out.println("Joueur actualisé : " + joueurs);
                break;
            }
            index++;
        }
        if (!added) {
            joueurs.add(object);
            System.out.println("Nouveau joueur ajouté : " + joueurs);
        }
    }


    public ArrayList<String> getIdentifiants() {
        ArrayList<String> listeId = new ArrayList<String>();
        for (Object joueur : joueurs) {
            String identifiantJoueur = ((List<?>) joueur).get(0).toString();
            listeId.add(identifiantJoueur);
        }
        return listeId;
    }

    public ArrayList<String> getCouleurLobby() {
        ArrayList<String> listeCouleurs = new ArrayList<String>();
        for (Object joueur : joueurs) {
            if (((List<?>) joueur).get(0).toString().equals(LoginController.user)) { //Exclure la couleur du joueur local
                continue;
            } else {
                String couleurJoueur = ((List<?>) joueur).get(2).toString();
                listeCouleurs.add(couleurJoueur);
            }
        }
        return listeCouleurs;
    }

}
