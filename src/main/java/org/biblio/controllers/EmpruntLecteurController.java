package org.biblio.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.Livre;
import org.biblio.service.ViewLoader;

public class EmpruntLecteurController {

    @FXML
    private TableView<Livre> livreTable;

    @FXML
    private TableColumn<Livre, Integer> idCol;

    @FXML
    private TableColumn<Livre, String> titreCol;

    @FXML
    private TableColumn<Livre, String> auteurCol;

    @FXML
    private TableColumn<Livre, Integer> anneeCol;

    @FXML
    private Label messageLabel;

    private final ObservableList<Livre> livres = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Associer les colonnes avec les méthodes getter du modèle Livre
        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        titreCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitre()));
        auteurCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAuteur()));
        anneeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAnneePublication()).asObject());

        // Charger les livres disponibles
        chargerLivresDisponibles();
    }

    private void chargerLivresDisponibles() {
        livres.clear();
        livres.addAll(LivreDAO.getLivresDisponibles());
        livreTable.setItems(livres);
    }

    @FXML
    private void handleEmprunt() {
        Livre livreSelectionne = livreTable.getSelectionModel().getSelectedItem();
        if (livreSelectionne == null) {
            messageLabel.setText("Veuillez sélectionner un livre.");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        boolean success = LivreDAO.marquerCommeEmprunte(livreSelectionne.getId());
        if (success) {
            messageLabel.setText("Livre emprunté avec succès !");
            messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            chargerLivresDisponibles(); // rafraîchir
        } else {
            messageLabel.setText("Erreur lors de l'emprunt.");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }
    @FXML
    private void retourMenuLecteur() {
        ViewLoader.loadView("lecteur_menu.fxml", "admin");
    }
}
