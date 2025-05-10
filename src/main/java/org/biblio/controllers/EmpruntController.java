package org.biblio.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.biblio.dao.EmpruntDAO;
import org.biblio.dao.LecteurDAO;
import org.biblio.dao.LivreDAO;
import org.biblio.model.utilisateurs.Lecteur;
import org.biblio.model.livres.Livre;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpruntController implements Initializable {

    // TableView des lecteurs
    @FXML
    private TableView<Lecteur> lecteurTableView;
    @FXML
    private TableColumn<Lecteur, Integer> colIdLecteur;
    @FXML
    private TableColumn<Lecteur, String> colNomLecteur;
    @FXML
    private TableColumn<Lecteur, String> colPrenomLecteur;

    // TableView des livres
    @FXML
    private TableView<Livre> livreTableView;
    @FXML
    private TableColumn<Livre, Integer> colIdLivre;
    @FXML
    private TableColumn<Livre, String> colTitreLivre;
    @FXML
    private TableColumn<Livre, String> colAuteurLivre;

    // Listes observables
    private final ObservableList<Lecteur> lecteurs = FXCollections.observableArrayList();
    private final ObservableList<Livre> livres = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation colonnes lecteurs
        colIdLecteur.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNomLecteur.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNom()));
        colPrenomLecteur.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNom()));

        lecteurs.addAll(LecteurDAO.getAllLecteurs());
        lecteurTableView.setItems(lecteurs);

        // Initialisation colonnes livres
        colIdLivre.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colTitreLivre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTitre()));
        colAuteurLivre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getAuteur()));

        livres.addAll(LivreDAO.getLivresDisponibles());
        livreTableView.setItems(livres);
    }

    @FXML
    private void enregistrerEmprunt() {
        Lecteur lecteur = lecteurTableView.getSelectionModel().getSelectedItem();
        Livre livre = livreTableView.getSelectionModel().getSelectedItem();

        if (lecteur == null || livre == null) {
            showAlert("Veuillez sélectionner un lecteur et un livre.");
            return;
        }

        boolean success = EmpruntDAO.enregistrerEmprunt(livre.getId(), lecteur.getId());
        if (success) {
            showAlert("📚 Emprunt enregistré !");
            livres.remove(livre); // mise à jour dynamique de la table
        } else {
            showAlert("❌ Échec de l'enregistrement.");
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
