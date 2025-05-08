package org.biblio.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.biblio.dao.EmpruntDAO;
import org.biblio.model.utilisateurs.Utilisateur;
import org.biblio.service.ViewLoader;
import org.biblio.systeme.Authentification;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String mdp = passwordField.getText();

        Utilisateur user = Authentification.seConnecter(email, mdp);
        if (user != null) {
            statusLabel.setText("Bienvenue " + user.getNom());

            // Vérification des retards
            EmpruntDAO.verifierEtSanctionnerRetards();

            // Navigation par rôle
            user.afficherMenu();

        } else {
            statusLabel.setText("Connexion échouée.");
        }
    }

    @FXML
    private void handleGoToInscription() {
        ViewLoader.loadView("inscription.fxml", "admin");
    }

}
