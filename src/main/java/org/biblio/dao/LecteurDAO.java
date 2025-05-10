package org.biblio.dao;

import org.biblio.model.utilisateurs.Lecteur;
import org.biblio.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static List<Lecteur> getAllLecteurs() {
        List<Lecteur> lecteurs = new ArrayList<>();
        String sql = "SELECT id, nom, email, mot_de_passe FROM utilisateurs WHERE role = 'LECTEUR'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Lecteur l = new Lecteur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                );
                lecteurs.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecteurs;
    }

}
