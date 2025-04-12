package TDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Client;
import DB.Connect;

public class ClientTDG {

    public static int insert(Client client) {
        String sql = "INSERT INTO client (name_Client, affiliation, email, password, phoneNumber, text, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getAffiliation());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getPassword());
            pstmt.setString(5, client.getPhoneNumber());
            pstmt.setString(6, client.getText());
            pstmt.setString(7, client.getStatus());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    client.setClientId(id);
                    System.out.println("Generated ID: " + id);
                    return id;
                }
            }
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return -1;
    }

    public static boolean update(Client client) {
        String sql = "UPDATE client SET name_Client = ?, affiliation = ?, email = ?, password = ?, phoneNumber = ?, text = ?, status = ? WHERE Client_ID = ?";
        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getAffiliation());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getPassword());
            pstmt.setString(5, client.getPhoneNumber());
            pstmt.setString(6, client.getText());
            pstmt.setString(7, client.getStatus());
            pstmt.setInt(8, client.getClientId());

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM client WHERE Client_ID = ?";
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

    public static Client findById(int id) {
        String sql = "SELECT * FROM client WHERE Client_ID = ?";
        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client(
                            rs.getString("name_Client"),
                            rs.getString("affiliation"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("phoneNumber"),
                            rs.getString("text"),
                            rs.getString("status"));
                    client.setClientId(rs.getInt("Client_ID"));
                    return client;
                }
            }
        } catch (SQLException e) {
            System.out.println("Find error: " + e.getMessage());
        }
        return null;
    }

    public static List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Client client = new Client(
                        rs.getString("name_Client"),
                        rs.getString("affiliation"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phoneNumber"),
                        rs.getString("text"),
                        rs.getString("status"));
                client.setClientId(rs.getInt("Client_ID"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("FindAll error: " + e.getMessage());
        }

        return clients;
    }
}
