package TDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import DB.Connect;
import Mapper.AuctionMapper;
import Model.Auction;
import Model.Expert;

public class ExpertTDG {

    public static int insertExpert(String name, String contactAddress, String city, String licenseNumber,
            String password)
            throws SQLException {
        String sql = "INSERT INTO Expert (Name_Expert, ContactAddress, City, LicenseNumber,Password) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, contactAddress);
            stmt.setString(3, city);
            stmt.setString(4, licenseNumber);
            stmt.setString(5, password);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to insert expert and retrieve generated ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting expert: " + e.getMessage());
            throw e;
        }
    }

    public static void insertExpertExpertise(int expertId, List<Integer> expertiseIds) throws SQLException {
        String sql = "INSERT INTO Expert_Expertises (Expert_ID, Expertise_ID) VALUES (?, ?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            for (Integer expertiseId : expertiseIds) {
                stmt.setInt(1, expertId);
                stmt.setInt(2, expertiseId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error inserting expert expertise: " + e.getMessage());
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

    public static List<Expert> findAll() {
        List<Expert> experts = new ArrayList<>();
        String sql = "SELECT * FROM Expert";

        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int expertId = rs.getInt("Expert_ID");
                String name = rs.getString("Name_Expert");
                String contactAddress = rs.getString("contactAddress");
                String city = rs.getString("city");
                String licenseNumber = rs.getString("licenseNumber");
                String password = rs.getString("password");

                List<Integer> expertiseIds = new ArrayList<>();
                List<Integer> availabilityIds = new ArrayList<>();

                Expert expert = new Expert(expertId, name, contactAddress, city, licenseNumber, password, expertiseIds,
                        availabilityIds);

                experts.add(expert);
            }
        } catch (SQLException e) {
            System.out.println("FindAll error: " + e.getMessage());
        }

        return experts;
    }

    public static Map<Auction, String> findAuctionsByExpertId(int expertId) {
        Map<Auction, String> result = new LinkedHashMap<>();

        String query = """
                SELECT a.Auction_ID, a.Begin_time, a.End_time, a.Type, a.Date, a.isViewing, ah.city, a.AuctionHouse_ID
                    FROM Auction a
                    JOIN AuctionHouse ah ON a.AuctionHouse_ID = ah.AuctionHouse_ID
                    WHERE a.Date = ?
                    AND a.Begin_time <= ?
                    AND a.End_time >= ?
                    AND a.Type = ?
                    AND ah.city = ?;"""

        ;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, expertId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Auction auction = AuctionMapper.map(rs);
                String auctionHouseInfo = rs.getString("AuctionHouseName") + " (" + rs.getString("AuctionHouseCity")
                        + ")";
                result.put(auction, auctionHouseInfo);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return result;
    }

    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM expert WHERE Expert_ID = ?";
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
