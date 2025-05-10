package org.biblio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.biblio.service.ViewLoader;


public class BibliothecaireMenuController {

    @FXML
    public void ajouterLivre(ActionEvent event) {
        org.biblio.model.utilisateurs.Bibliothecaire.ajouterLivre();
    }

    @FXML
    public void supprimerLivre(ActionEvent event) {
        org.biblio.model.utilisateurs.Bibliothecaire.supprimerLivre();
    }

    @FXML
    public void enregistrerEmprunt(ActionEvent event) {
        org.biblio.model.utilisateurs.Bibliothecaire.enregistrerEmprunt();

    }

    @FXML
    public void appliquerSanction(ActionEvent event) {

    }

    @FXML
    public void voirLivresDisponibles(ActionEvent event) {
        org.biblio.model.utilisateurs.Bibliothecaire.voirLivresDisponibles();
    }


    @FXML
    public void voirLivresEmpruntes(ActionEvent event) {
       }

    @FXML
    private void deconnexion(ActionEvent event) {
        ViewLoader.loadView("login.fxml", "login");
        // tu peux fermer la fenêtre ici ou revenir à l'écran de login
    }
}
