package org.biblio.dao;


import org.biblio.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SanctionDAO {
    public static void enregistrerSanction(int idEmprunt, int jours, double montant) {
        String sql = "INSERT INTO sanctions(id_emprunt, jours_retard, montant) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmprunt);
            ps.setInt(2, jours);
            ps.setDouble(3, montant);
            ps.executeUpdate();
            System.out.println("⚠️ Sanction enregistrée.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
