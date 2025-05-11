package org.biblio.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.Livre;
import org.biblio.service.ViewLoader;

import java.util.List;

public class LivresDisponiblesController {

    @FXML
    private ListView<String> livreListView;

    @FXML
    private Label emptyLabel;

    @FXML
    public void initialize() {
        List<Livre> livres = LivreDAO.getTousLesLivres(); // Utilise tous les livres pour voir empruntés aussi

        if (livres.isEmpty()) {
            emptyLabel.setText("❌ Aucun livre en base de données.");
            emptyLabel.setVisible(true);
        } else {
            emptyLabel.setVisible(false);
            livreListView.setItems(FXCollections.observableArrayList(
                    livres.stream()
                            .map(Livre::getDetailsAsString)
                            .toList()
            ));
        }
    }

    @FXML
    private void retourMenuBib() {
        ViewLoader.loadView("bibliothecaire_menu.fxml","lecteur");
    }
    @FXML
    private void retourMenuAdmin() {
        ViewLoader.loadView("AdminMenu.fxml", "admin");
    }



}
