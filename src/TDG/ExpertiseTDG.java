package TDG;

//DONT USE IT 
import java.sql.*;

import DB.Connect;

public class ExpertiseTDG {
    public boolean insertExpertise(String expertiseName) {
        String sql = "INSERT INTO Expertise (expertise_name) VALUES (?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, expertiseName);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting expertise: " + e.getMessage());
            return false;
        }
    }

}
