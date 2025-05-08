package org.biblio.dao;

import org.biblio.model.utilisateurs.Lecteur;
import org.biblio.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LecteurDAO {

    public static boolean ajouterLecteur(Lecteur lecteur) {
        String sql = "INSERT INTO utilisateurs (nom, email, mot_de_passe, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lecteur.getNom());
            ps.setString(2, lecteur.getEmail());
            ps.setString(3, lecteur.getMotDePasse());
            ps.setString(4, "LECTEUR");
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
