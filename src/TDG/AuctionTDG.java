package TDG;

import Mapper.AuctionMapper;
import Model.Auction;
import DB.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AuctionTDG {

    public static int insert(Auction auction) throws SQLException {
        String query = "INSERT INTO Auction (Begin_time, End_time, Date, Type, Expertise_ID, isViewing, AuctionHouse_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            AuctionMapper.mapAuctionToInsertStatement(auction, stmt);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        }
        return -1;
    }

    public static void update(Auction auction) throws SQLException {
        String query = "UPDATE Auction SET Begin_time=?, End_time=?, Date=?, Type=?, Expertise_ID=?, isViewing=?, AuctionHouse_ID=? WHERE Auction_ID=?";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            AuctionMapper.mapAuctionToUpdateStatement(auction, stmt);
            stmt.executeUpdate();
        }
    }

    public static void delete(int auctionId) throws SQLException {
        String query = "DELETE FROM Auction WHERE Auction_ID=?";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, auctionId);
            stmt.executeUpdate();
        }
    }

    public static void insertAuctionExpertise(int auctionId, List<Integer> expertiseIds) throws SQLException {
        String sql = "INSERT INTO Auction_Expertise (Auction_ID, Expertise_ID) VALUES (?, ?)";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            for (Integer expertiseId : expertiseIds) {
                stmt.setInt(1, auctionId);
                stmt.setInt(2, expertiseId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error inserting expert expertise: " + e.getMessage());
            throw e;
        }
    }

    public static List<Auction> filterAuctions(Date date, Time time, String type, String city) throws SQLException {
        System.out.println("Received");
        List<Auction> auctions = new ArrayList<>();

        String query = "SELECT a.Auction_ID, a.Begin_time, a.End_time, a.Type, a.Date, a.isViewing, ah.city, a.AuctionHouse_ID "
                +
                "FROM Auction a " +
                "JOIN AuctionHouse ah ON a.AuctionHouse_ID = ah.AuctionHouse_ID " +
                "WHERE a.Date = ? " +
                "AND a.Begin_time <= ? " +
                "AND a.End_time >= ? " +
                "AND a.Type = ? " +
                "AND ah.city = ?;";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, date);
            stmt.setTime(2, time);
            stmt.setTime(3, time);
            stmt.setString(4, type);
            stmt.setString(5, city);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Auction auction = AuctionMapper.mapRowToAuction(rs);
                auctions.add(auction);

            }

        } catch (SQLException e) {

            System.err.println("SQL Error: " + e.getMessage());
        }

        System.out.println("Total auctions found: " + auctions.size());

        return auctions;
    }

    public static Map<Auction, String> findAuctionsByExpertId(int expertId) {
        Map<Auction, String> result = new LinkedHashMap<>();

        String query = "SELECT DISTINCT a.Auction_ID, a.Begin_time, a.End_time, a.Type, a.Date, a.isViewing, " +
                "ah.Name AS AuctionHouseName, ah.City AS AuctionHouseCity, a.AuctionHouse_ID " +
                "FROM Auction a " +
                "JOIN AuctionHouse ah ON a.AuctionHouse_ID = ah.AuctionHouse_ID " +
                "JOIN Auction_Expertise ae ON a.Auction_ID = ae.Auction_ID " +
                "JOIN Expert_Expertises ee ON ae.Expertise_ID = ee.Expertise_ID " +
                "JOIN Expert e ON ee.Expert_ID = e.Expert_ID " +
                "WHERE e.Expert_ID = ?;";

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

}
