package TDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.Connect;
import Model.Client;
import Model.ObjectOfInterest;

public class ObjectOfInterestTDG {

    public static void insertObjectOfInterest(ObjectOfInterest obj) throws SQLException {
        String sql = "INSERT INTO ObjectOfInterest (object_name, object_type, client_id) VALUES (?, ?, ?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getType());
            stmt.setInt(3, obj.getClientId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                    System.out.println("Generated ID: " + obj.getId());
                } else {
                    System.out.println("No generated key returned.");
                }
            }
        }
    }

    public static List<ObjectOfInterest> getInterestsWithMatchesByClient(int clientId) throws SQLException {
        List<ObjectOfInterest> interests = new ArrayList<>();

        String sql = """
                    SELECT oi.id, oi.object_name, oi.object_type, oi.client_id
                    FROM ObjectOfInterest oi
                    WHERE oi.client_id = ?
                    AND EXISTS (
                        SELECT 1
                        FROM Object o
                        WHERE
                            (oi.object_name IS NULL AND o.type = oi.object_type AND o.IsOwned = true)
                            OR
                            (oi.object_name IS NOT NULL AND o.Name_object = oi.object_name AND o.type = oi.object_type AND o.IsOwned = true)
                    )
                """;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clientId); // Set clientId in the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    interests.add(new ObjectOfInterest(
                            rs.getInt("id"),
                            rs.getString("object_name"),
                            rs.getString("object_type"),
                            rs.getInt("client_id")));
                }
            }
        }

        return interests;
    }

    public static List<Client> getClientsForExpert(int expertId) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = """
                SELECT DISTINCT r.Client_ID, c.Name_client
                    FROM Request r
                    JOIN Client c ON r.Client_ID = c.Client_ID
                    WHERE r.Expert_ID = ?"""

        ;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, expertId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    clients.add(new Client(
                            rs.getInt("Client_ID"),
                            rs.getString("Name_client")));
                }
            }
        }

        return clients;
    }

    public static List<ObjectOfInterest> getInterestsForClient(int clientId) throws SQLException {
        List<ObjectOfInterest> interests = new ArrayList<>();
        String sql = """
                SELECT oi.id, oi.object_name, oi.object_type
                        FROM objectofinterest oi
                        JOIN Object o ON (oi.object_name = o.Name_Object OR oi.object_name IS NULL)
                        AND oi.object_type = o.Type
                        WHERE oi.client_id = ? AND o.IsOwned = true""";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    interests.add(new ObjectOfInterest(
                            rs.getInt("id"),
                            rs.getString("object_name"),
                            rs.getString("object_type"),
                            clientId));
                }
            }
        }

        return interests;
    }

    public static boolean deleteObjectOfInterest(int id) {
        String sql = "DELETE FROM objectofinterest WHERE id = ?";
        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }

}
