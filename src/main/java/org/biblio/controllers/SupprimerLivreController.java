package org.biblio.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.Livre;
import org.biblio.service.ViewLoader;

import java.io.IOException;
import java.util.List;

public class SupprimerLivreController {

    @FXML private TableView<Livre> tableView;
    @FXML private TableColumn<Livre, Integer> idCol;
    @FXML private TableColumn<Livre, String> titreCol;
    @FXML private TableColumn<Livre, String> auteurCol;
    @FXML private TableColumn<Livre, Integer> anneeCol;
    @FXML private TableColumn<Livre, String> isbnCol;
    @FXML private TextField idField;

    private ObservableList<Livre> livreList;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        titreCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitre()));
        auteurCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuteur()));
        anneeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAnneePublication()).asObject());
        isbnCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIsbn()));

        loadLivres();
    }

    private void loadLivres() {
        List<Livre> livres = LivreDAO.getAllLivres(); // assure-toi que cette méthode existe
        livreList = FXCollections.observableArrayList(livres);
        tableView.setItems(livreList);
    }

    @FXML
    private void supprimerLivre() {
        try {
            int id = Integer.parseInt(idField.getText());
            boolean success = LivreDAO.supprimerLivre(id);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "📕 Livre supprimé avec succès !");
                alert.showAndWait();
                loadLivres(); // recharge la table
                idField.clear();
            } else {
                showError("❌ Aucun livre trouvé avec cet ID.");
            }
        } catch (NumberFormatException e) {
            showError("Veuillez entrer un ID valide.");
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.setTitle("Erreur");
        alert.showAndWait();
    }

    @FXML
    private void retourMenuAdmin() {
        ViewLoader.loadView("AdminMenu.fxml","lecteur");
    }
    @FXML
    private void retourMenuBib() {
        ViewLoader.loadView("bibliothecaire_menu.fxml","lecteur");
    }
}
