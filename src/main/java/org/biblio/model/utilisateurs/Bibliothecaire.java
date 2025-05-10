package org.biblio.model.utilisateurs;

import org.biblio.dao.EmpruntDAO;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.Livre;
import org.biblio.model.livres.LivreDAOHelper;
import org.biblio.service.ViewLoader;

import java.util.List;
import java.util.Scanner;

public class Bibliothecaire extends Utilisateur {

    public Bibliothecaire(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
    }


    public void afficherMenu() {
        ViewLoader.loadView("bibliothecaire_menu.fxml","Menu");
    }

    public static void ajouterLivre() {
        ViewLoader.loadView("AjouterLivre.fxml","Menu");
    }

    public static void supprimerLivre() {
        ViewLoader.loadView("SupprimerLivre.fxml","Menu");
    }


    public static void enregistrerEmprunt() {
        ViewLoader.loadView("EnregistrerEmprunt.fxml","Menu");

    }

    private void appliquerSanction(Scanner sc) {
        EmpruntDAO.verifierEtSanctionnerRetards();
    }

    public static void voirLivresDisponibles() {
        ViewLoader.loadView("livres_disponibles.fxml","Menu");
    }

    public static void verifiedemande() {
        ViewLoader.loadView("ValidationDemandesEmprunt.fxml","Menu");
    }

    private void voirLivresEmpruntes() {
        EmpruntDAO.afficherEmpruntsActifs();
    }
}
