package org.biblio.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import org.biblio.service.ViewLoader;

import static sun.io.Win32ErrorMode.initialize;


public class LecteurMenuController {

    @FXML
    private void handleVoirLivres(ActionEvent event) {
        // Appel de la méthode correspondante
        //voirLivresDisponibles();
        org.biblio.model.utilisateurs.Lecteur.voirLivresDisponibles();
    }

    @FXML
    private void handleConsulterCatalogue(ActionEvent event) {
        org.biblio.model.utilisateurs.Lecteur.consulterCatalogue();
    }

    @FXML
    private void handleEmprunterLivre(ActionEvent event) {
        org.biblio.model.utilisateurs.Lecteur.emprunterLivre();
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
        ViewLoader.loadView("login.fxml", "login");
        // tu peux fermer la fenêtre ici ou revenir à l'écran de login
    }

    @FXML
    private void handleRetournerLivre(ActionEvent event) {
        org.biblio.model.utilisateurs.Lecteur.retournerLivre();

    }

    @FXML
    private void handleDeconnexion(ActionEvent event) {
        // Revenir à l'écran de connexion
        //NavigationHelper.retourAccueil(); // À implémenter si besoin
    }

    // --- Méthodes métiers à relier à ton backend ---
    private void voirLivresDisponibles() {

        // Ajouter une nouvelle vue ou une popup ici si besoin
    }

    private void consulterCatalogue() {
      }

    private void emprunterLivre() {
        System.out.println("Emprunter un livre...");
    }


    private void retournerLivre() {
        System.out.println("Retourner un livre...");
    }
    @FXML
    public void demanderEmprunt(ActionEvent event) {
        org.biblio.model.utilisateurs.Lecteur.demanderEmprunt();
    }
}