package TDG;

import DB.Connect;
import java.sql.*;
import java.util.List;

public class AvailabilityTDG {

    public static int insertAvailability(String day, String startTime, String endTime) throws SQLException {
        String sql = "INSERT INTO Availability (Day, StartTime, endTime) VALUES (?, ?, ?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, day);
            stmt.setString(2, startTime);
            stmt.setString(3, endTime);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to insert availability and retrieve generated ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting availability: " + e.getMessage());
            throw e;
        }
    }

    public static void insertExpertAvailability(int expertId, List<Integer> availabilityIds) throws SQLException {
        String sql = "INSERT INTO Expert_Availability (Expert_ID, Availability_ID) VALUES (?, ?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            for (Integer availabilityId : availabilityIds) {
                stmt.setInt(1, expertId);
                stmt.setInt(2, availabilityId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error inserting expert availability: " + e.getMessage());
            throw e;
        }
    }
}
