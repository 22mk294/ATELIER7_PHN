package org.biblio.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.biblio.dao.UtilisateurDAO;
import org.biblio.model.utilisateurs.Utilisateur;
import org.biblio.service.ViewLoader;

import java.util.List;

public class AjouterBibliothecaireController {

    @FXML private TextField nomField;
    @FXML private TextField emailField;
    @FXML private PasswordField mdpField;
    @FXML private TableView<Utilisateur> tableView;
    @FXML private TableColumn<Utilisateur, Integer> idColumn;
    @FXML private TableColumn<Utilisateur, String> nomColumn;
    @FXML private TableColumn<Utilisateur, String> emailColumn;
    @FXML private TableColumn<Utilisateur, Void> actionColumn;
    @FXML
    private void retourMenuAdmin() {
        ViewLoader.loadView("AdminMenu.fxml", "admin");
    }
    private final ObservableList<Utilisateur> bibliothecaires = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nomColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        emailColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        addButtonToTable();
        chargerBibliothecaires();
    }

    private void chargerBibliothecaires() {
        List<Utilisateur> liste = UtilisateurDAO.getUtilisateursParRole("BIBLIOTHECAIRE");
        bibliothecaires.setAll(liste);
        tableView.setItems(bibliothecaires);
    }

    @FXML
    private void ajouterBibliothecaire() {
        String nom = nomField.getText();
        String email = emailField.getText();
        String mdp = mdpField.getText();

        if (nom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
            afficherAlerte(Alert.AlertType.ERROR, "Tous les champs sont obligatoires.");
            return;
        }

        UtilisateurDAO.ajouterUtilisateur(nom, email, mdp, "BIBLIOTHECAIRE");
        afficherAlerte(Alert.AlertType.INFORMATION, "✅ Bibliothécaire ajouté !");
        nomField.clear(); emailField.clear(); mdpField.clear();
        chargerBibliothecaires();
    }

    private void addButtonToTable() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Supprimer");

            {
                btn.setOnAction(event -> {
                    Utilisateur utilisateur = getTableView().getItems().get(getIndex());
                    UtilisateurDAO.supprimerUtilisateur(utilisateur.getId());
                    bibliothecaires.remove(utilisateur);
                });
                btn.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private void afficherAlerte(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
