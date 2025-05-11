package org.biblio.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.biblio.dao.LecteurDAO;
import org.biblio.model.utilisateurs.Lecteur;
import org.biblio.service.ViewLoader;

public class InscriptionController {

    @FXML private TextField nomField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    @FXML
    private void handleInscription() {
        String nom = nomField.getText();
        String email = emailField.getText();
        String motDePasse = passwordField.getText();

        if (nom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
            statusLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        Lecteur nouveauLecteur = new Lecteur(0, nom, email, motDePasse);
        boolean success = LecteurDAO.ajouterLecteur(nouveauLecteur);

        if (success) {
            statusLabel.setText("Inscription réussie !");
        } else {
            statusLabel.setText("Erreur lors de l'inscription.");
        }
    }
    @FXML
    private void retour() {
        ViewLoader.loadView("login.fxml", "admin");
    }
}
