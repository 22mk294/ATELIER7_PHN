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

    private void enregistrerEmprunt(Scanner sc) {
        System.out.print("ID du lecteur: ");
        int idLecteur = sc.nextInt();
        System.out.print("ID du livre: ");
        int idLivre = sc.nextInt();

        EmpruntDAO.enregistrerEmprunt(idLivre, idLecteur);
    }

    private void appliquerSanction(Scanner sc) {
        EmpruntDAO.verifierEtSanctionnerRetards();
    }

    public static void voirLivresDisponibles() {
        ViewLoader.loadView("livres_disponibles.fxml","Menu");
    }

    private void voirLivresEmpruntes() {
        EmpruntDAO.afficherEmpruntsActifs();
    }
}
