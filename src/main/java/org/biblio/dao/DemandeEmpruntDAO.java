package org.biblio.dao;

import org.biblio.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemandeEmpruntDAO {

    public enum Statut {
        EN_ATTENTE, ACCEPTEE, REFUSEE
    }

    public static boolean envoyerDemande(int idLecteur, int idLivre) {
        String sql = "INSERT INTO demandes_emprunt(id_utilisateur, id_livre, date_demande, statut) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLecteur);
            ps.setInt(2, idLivre);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setString(4, Statut.EN_ATTENTE.name());

            ps.executeUpdate();

            // Mettre à jour l'état du livre comme "demandé"
            String updateLivre = "UPDATE livres SET demande = true WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateLivre)) {
                updateStmt.setInt(1, idLivre);
                updateStmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<Demande> getDemandesEnAttente() {
        List<Demande> demandes = new ArrayList<>();
        String sql = """
                SELECT d.id, d.id_utilisateur, u.nom, d.id_livre, l.titre, d.date_demande
                FROM demandes_emprunt d
                JOIN utilisateurs u ON d.id_utilisateur = u.id
                JOIN livres l ON d.id_livre = l.id
                WHERE d.statut = 'EN_ATTENTE'
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                demandes.add(new Demande(
                        rs.getInt("id"),
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getInt("id_livre"),
                        rs.getString("titre"),
                        rs.getDate("date_demande").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return demandes;
    }

    public static void validerDemande(int idDemande) {
        String getInfo = "SELECT id_utilisateur, id_livre FROM demandes_emprunt WHERE id = ?";
        String updateDemande = "UPDATE demandes_emprunt SET statut = 'ACCEPTEE' WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement getStmt = conn.prepareStatement(getInfo)) {

            getStmt.setInt(1, idDemande);
            ResultSet rs = getStmt.executeQuery();

            if (rs.next()) {
                int idLecteur = rs.getInt("id_utilisateur");
                int idLivre = rs.getInt("id_livre");

                // Enregistrer l'emprunt
                EmpruntDAO.enregistrerEmprunt(idLivre, idLecteur);

                // Valider la demande
                try (PreparedStatement updateStmt = conn.prepareStatement(updateDemande)) {
                    updateStmt.setInt(1, idDemande);
                    updateStmt.executeUpdate();
                }

                // Mettre à jour l'état "demandé" du livre
                String updateLivre = "UPDATE livres SET demande = false WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(updateLivre)) {
                    ps.setInt(1, idLivre);
                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void refuserDemande(int idDemande) {
        String sql = "UPDATE demandes_emprunt SET statut = 'REFUSEE' WHERE id = ?";
        String getLivreId = "SELECT id_livre FROM demandes_emprunt WHERE id = ?";
        String updateLivre = "UPDATE livres SET demande = false WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(sql)) {

            updateStmt.setInt(1, idDemande);
            updateStmt.executeUpdate();

            try (PreparedStatement getStmt = conn.prepareStatement(getLivreId)) {
                getStmt.setInt(1, idDemande);
                ResultSet rs = getStmt.executeQuery();
                if (rs.next()) {
                    int idLivre = rs.getInt("id_livre");
                    try (PreparedStatement updateLivreStmt = conn.prepareStatement(updateLivre)) {
                        updateLivreStmt.setInt(1, idLivre);
                        updateLivreStmt.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Classe interne représentant une demande
    public static class Demande {
        public final int id;
        public final int idLecteur;
        public final String nomLecteur;
        public final int idLivre;
        public final String titreLivre;
        public final LocalDate dateDemande;

        public Demande(int id, int idLecteur, String nomLecteur, int idLivre, String titreLivre, LocalDate dateDemande) {
            this.id = id;
            this.idLecteur = idLecteur;
            this.nomLecteur = nomLecteur;
            this.idLivre = idLivre;
            this.titreLivre = titreLivre;
            this.dateDemande = dateDemande;
        }

        @Override
        public String toString() {
            return "Demande #" + id + " | " + nomLecteur + " -> " + titreLivre + " | " + dateDemande;
        }
    }
}
