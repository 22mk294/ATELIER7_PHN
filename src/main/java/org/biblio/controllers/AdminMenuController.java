package org.biblio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.biblio.service.ViewLoader;

import java.io.IOException;
import java.util.Scanner;

public class AdminMenuController {

    @FXML
    private void ajouterLivre(ActionEvent event) {
        //System.out.println("Ajouter un livre");
        // appel à ta méthode existante ici
        org.biblio.model.utilisateurs.Admin.ajouterLivre();
    }

    @FXML
    private void supprimerLivre(ActionEvent event) {
        org.biblio.model.utilisateurs.Admin.supprimerLivre();

    }

    @FXML
    private void voirLivresDisponibles(ActionEvent event) {
        org.biblio.model.utilisateurs.Admin.voirLivresDisponibles();
    }

    @FXML
    private void voirLivresEmpruntes(ActionEvent event) {
        System.out.println("Voir livres empruntés");
    }

    @FXML
    private void ajouterBibliothecaire(ActionEvent event) {
        org.biblio.model.utilisateurs.Admin.ajouterBibliothecaire();
    }

    @FXML
    private void supprimerBibliothecaire(ActionEvent event) {
        System.out.println("Supprimer un bibliothécaire");
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
        ViewLoader.loadView("login.fxml", "login");
        // tu peux fermer la fenêtre ici ou revenir à l'écran de login
    }

    @FXML
    private void retourMenuAdmin() {
        ViewLoader.loadView("AdminMenu.fxml", "admin");
    }

}