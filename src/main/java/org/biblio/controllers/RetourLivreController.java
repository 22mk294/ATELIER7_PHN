package org.biblio.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.biblio.dao.EmpruntDAO;

public class RetourLivreController {

    @FXML
    private TextField idEmpruntField;

    @FXML
    private void handleRetour() {
        String input = idEmpruntField.getText();
        try {
            int idEmprunt = Integer.parseInt(input);
            EmpruntDAO.retournerLivre(idEmprunt);
            showAlert("✅ Livre retourné avec succès.");
            idEmpruntField.clear();
        } catch (NumberFormatException e) {
            showAlert("❌ Veuillez entrer un ID valide.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
