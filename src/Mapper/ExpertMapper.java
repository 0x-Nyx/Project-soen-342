package Mapper;

import Model.Expert;
import TDG.ExpertTDG;

import java.sql.*;

import DB.Connect;

public class ExpertMapper {
    public static boolean insertExpertWithDetails(Expert expert) {
        boolean success = false;
        String insertExpertSQL = "INSERT INTO expert (name_Expert, contactAddress, city, licenseNumber, password) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(insertExpertSQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, expert.getName());
            pstmt.setString(2, expert.getContactAddress());
            pstmt.setString(3, expert.getCity());
            pstmt.setString(4, expert.getLicenseNumber());
            pstmt.setString(5, expert.getPassword());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int expertId = rs.getInt(1);
                        expert.setExpertId(expertId);

                        ExpertTDG.insertExpertExpertise(expertId, expert.getExpertiseIds());

                        success = true;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error inserting expert with details: " + e.getMessage());
        }

        return success;
    }

}
