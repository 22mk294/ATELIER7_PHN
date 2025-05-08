package org.biblio.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.Livre;
import org.biblio.service.ViewLoader;

import java.util.List;

public class CatalogueLecteurController {

    @FXML
    private ListView<String> catalogueListView;

    @FXML
    private Label messageLabel;

    @FXML
    private Button retourButton;

    @FXML
    public void initialize() {
        List<Livre> livres = LivreDAO.getAllLivres();

        if (livres.isEmpty()) {
            messageLabel.setText("❌ Aucun livre enregistré.");
            messageLabel.setVisible(true);
        } else {
            messageLabel.setVisible(false);
            catalogueListView.setItems(FXCollections.observableArrayList(
                    livres.stream().map(l -> {
                        String dispo = l.isDisponible() ? "✅ Disponible" : "❌ Emprunté";
                        return l.getTitre() + " - " + l.getAuteur()
                                + " (" + l.getAnneePublication() + ")"
                                + " [" + l.getClass().getSimpleName() + "] - " + dispo;
                    }).toList()
            ));
        }
    }

    @FXML
    private void retourMenuLecteur() {
        ViewLoader.loadView("lecteur_menu.fxml", "lecteur");
    }
}
