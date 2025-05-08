package org.biblio.model.utilisateurs;

import org.biblio.dao.EmpruntDAO;
import org.biblio.dao.LivreDAO;
import org.biblio.dao.UtilisateurDAO;
import org.biblio.model.livres.Livre;
import org.biblio.model.livres.LivreDAOHelper;
import org.biblio.service.ViewLoader;

import java.util.List;
import java.util.Scanner;

public class Admin extends Utilisateur {

    public Admin(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
    }

    @Override
    public void afficherMenu() {
        ViewLoader.loadView("AdminMenu.fxml","Menu");
    }

    public static void ajouterLivre() {
        ViewLoader.loadView("AjouterLivre.fxml","Admin");

    }

    public static void supprimerLivre() {
        ViewLoader.loadView("SupprimerLivre.fxml","Suppression");

    }

    public static void voirLivresDisponibles() {
        ViewLoader.loadView("livres_disponibles.fxml","voirLeLivre");
    }


    private void voirLivresEmpruntes() {
        EmpruntDAO.afficherEmpruntsActifs();
    }

    public static void ajouterBibliothecaire() {
        ViewLoader.loadView("AjouterBibliothecaire.fxml","Livre");
    }

    private void supprimerBibliothecaire(Scanner sc) {
        System.out.print("ID du bibliothécaire à supprimer : ");
        int id = sc.nextInt();
        UtilisateurDAO.supprimerUtilisateur(id);
    }
}
