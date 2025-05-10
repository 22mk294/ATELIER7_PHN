package org.biblio.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.biblio.dao.DemandeEmpruntDAO;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.Livre;

import java.net.URL;
import java.util.ResourceBundle;

public class DemandeEmpruntController implements Initializable {

    @FXML private TableView<Livre> livresTableView;
    @FXML private TableColumn<Livre, Integer> colIdLivre;
    @FXML private TableColumn<Livre, String> colTitre;
    @FXML private TableColumn<Livre, String> colAuteur;

    private final ObservableList<Livre> livres = FXCollections.observableArrayList();

    private int idLecteur; // à définir dynamiquement à la connexion

    public void setIdLecteur(int idLecteur) {
        this.idLecteur = idLecteur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIdLivre.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colTitre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitre()));
        colAuteur.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuteur()));

        livres.addAll(LivreDAO.getLivresDisponibles());
        livresTableView.setItems(livres);
    }

    @FXML
    private void envoyerDemande() {
        Livre livre = livresTableView.getSelectionModel().getSelectedItem();

        if (livre == null) {
            showAlert("Veuillez sélectionner un livre.");
            return;
        }

        boolean success = DemandeEmpruntDAO.envoyerDemande(idLecteur, livre.getId());

        if (success) {
            showAlert("✅ Demande envoyée !");
            livres.remove(livre);
        } else {
            showAlert("❌ Échec de la demande (déjà demandée ?)");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Demande d'emprunt");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
