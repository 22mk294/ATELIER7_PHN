package org.biblio.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.*;
import org.biblio.service.ViewLoader;

public class AjouterLivreController {

    @FXML private TextField titreField;
    @FXML private TextField auteurField;
    @FXML private TextField anneeField;
    @FXML private TextField isbnField;
    @FXML private ComboBox<String> typeComboBox;

    // Roman
    @FXML private Label genreLabel;
    @FXML private TextField genreField;
    @FXML private Label pagesLabel;
    @FXML private TextField pagesField;

    // Biographie
    @FXML private Label sujetLabel;
    @FXML private TextField sujetField;

    // Magazine
    @FXML private Label numeroLabel;
    @FXML private TextField numeroField;
    @FXML private Label moisLabel;
    @FXML private TextField moisField;

    // Science-Fiction
    @FXML private Label universLabel;
    @FXML private TextField universField;

    @FXML
    public void initialize() {
        typeComboBox.getItems().addAll("Roman", "Biographie", "Magazine", "ScienceFiction");
        typeComboBox.setOnAction(e -> afficherChampsSpecifiques(typeComboBox.getValue()));
    }

    private void afficherChampsSpecifiques(String type) {
        // Réinitialiser la visibilité
        genreLabel.setVisible(false);     genreField.setVisible(false);
        pagesLabel.setVisible(false);     pagesField.setVisible(false);
        sujetLabel.setVisible(false);     sujetField.setVisible(false);
        numeroLabel.setVisible(false);    numeroField.setVisible(false);
        moisLabel.setVisible(false);      moisField.setVisible(false);
        universLabel.setVisible(false);   universField.setVisible(false);

        switch (type) {
            case "Roman" -> {
                genreLabel.setVisible(true);
                genreField.setVisible(true);
                pagesLabel.setVisible(true);
                pagesField.setVisible(true);
            }
            case "Biographie" -> {
                sujetLabel.setVisible(true);
                sujetField.setVisible(true);
            }
            case "Magazine" -> {
                numeroLabel.setVisible(true);
                numeroField.setVisible(true);
                moisLabel.setVisible(true);
                moisField.setVisible(true);
            }
            case "ScienceFiction" -> {
                universLabel.setVisible(true);
                universField.setVisible(true);
            }
        }
    }

    @FXML
    private void ajouterLivre() {
        try {
            String titre = titreField.getText();
            String auteur = auteurField.getText();
            int annee = Integer.parseInt(anneeField.getText());
            String isbn = isbnField.getText();
            String type = typeComboBox.getValue();

            Livre livre = LivreDAOHelper.createLivre(
                    type, titre, auteur, annee, isbn,
                    genreField.getText(),
                    pagesField.getText().isEmpty() ? null : Integer.parseInt(pagesField.getText()),
                    sujetField.getText(),
                    numeroField.getText().isEmpty() ? null : Integer.parseInt(numeroField.getText()),
                    moisField.getText(),
                    universField.getText()
            );


            switch (type) {
                case "Roman" -> {
                    String genre = genreField.getText();
                    int pages = Integer.parseInt(pagesField.getText());
                    livre = new Roman(0, titre, auteur, annee, isbn, true, genre, pages);
                }
                case "Biographie" -> {
                    String sujet = sujetField.getText();
                    livre = new Biographie(0, titre, auteur, annee, isbn, true, sujet);
                }
                case "Magazine" -> {
                    int numero = Integer.parseInt(numeroField.getText());
                    String mois = moisField.getText();
                    livre = new Magazine(0, titre, auteur, annee, isbn, true, numero, mois);
                }
                case "ScienceFiction" -> {
                    String univers = universField.getText();
                    livre = new ScienceFiction(0, titre, auteur, annee, isbn, true, univers);
                }
                default -> {
                    afficherErreur("Veuillez sélectionner un type de livre.");
                    return;
                }
            }

            LivreDAO.ajouterLivre(livre);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("📚 Livre ajouté avec succès !");
            alert.showAndWait();

            clearForm();

        } catch (NumberFormatException e) {
            afficherErreur("Veuillez vérifier les champs numériques (année, numéro, pages).");
        } catch (Exception e) {
            afficherErreur("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    private void clearForm() {
        titreField.clear();
        auteurField.clear();
        anneeField.clear();
        isbnField.clear();
        genreField.clear();
        pagesField.clear();
        sujetField.clear();
        numeroField.clear();
        moisField.clear();
        universField.clear();
        typeComboBox.getSelectionModel().clearSelection();
        afficherChampsSpecifiques(""); // Masque tout
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void retourMenuAdmin() {
        ViewLoader.loadView("AdminMenu.fxml", "admin");
    }
}