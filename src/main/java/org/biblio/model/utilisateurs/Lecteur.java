package org.biblio.model.utilisateurs;

import org.biblio.dao.EmpruntDAO;
import org.biblio.dao.LivreDAO;
import org.biblio.model.livres.Livre;
import org.biblio.service.ViewLoader;

import java.util.List;
import java.util.Scanner;

public class Lecteur extends Utilisateur {

    public Lecteur(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
    }
    public String getMotDePasse() {
        return motDePasse;
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public String toString() {
        return "Lecteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }

    @Override
    public void afficherMenu() {
        ViewLoader.loadView("lecteur_menu.fxml","lecteur");
    }

    public static void voirLivresDisponibles() {
        ViewLoader.loadView("livres_disponible_lecteur.fxml","livres");
    }

    public static void consulterCatalogue() {
        ViewLoader.loadView("CatalogueLecteur.fxml","livres");

    }

    public static void emprunterLivre() {
        ViewLoader.loadView("EmpruntLecteur.fxml","livres");
    }

    public static void demanderEmprunt() {
        ViewLoader.loadView("DemandeEmpruntView.fxml","livres");

    }


    public static void retournerLivre() {
        ViewLoader.loadView("retour_livre.fxml","livres");

    }


}
