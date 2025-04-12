package TDG;

import Model.AuctionHouse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DB.Connect;

public class AuctionHouseTDG {

    public static int insert(AuctionHouse auctionHouse) throws SQLException {
        String sql = "INSERT INTO AuctionHouse (Name, Address, City) VALUES (?, ?, ?)";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, auctionHouse.getName());
            stmt.setString(2, auctionHouse.getAddress());
            stmt.setString(3, auctionHouse.getCity());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
            return -1;
        }
    }

    public static AuctionHouse findById(int auctionHouseId) throws SQLException {
        String sql = "SELECT * FROM AuctionHouse WHERE AuctionHouse_ID = ?";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, auctionHouseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new AuctionHouse(
                        rs.getInt("AuctionHouse_ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("City"));
            }
            return null;
        }
    }

    public static List<AuctionHouse> findAll() throws SQLException {
        String sql = "SELECT * FROM AuctionHouse";
        List<AuctionHouse> auctionHouses = new ArrayList<>();
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                auctionHouses.add(new AuctionHouse(
                        rs.getInt("AuctionHouse_ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("City")));
            }
        }
        return auctionHouses;
    }

    public static boolean update(AuctionHouse auctionHouse) throws SQLException {
        String sql = "UPDATE AuctionHouse SET Name = ?, Address = ?, City = ? WHERE AuctionHouse_ID = ?";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, auctionHouse.getName());
            stmt.setString(2, auctionHouse.getAddress());
            stmt.setString(3, auctionHouse.getCity());
            stmt.setInt(4, auctionHouse.getAuctionHouseId());

            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean delete(int auctionHouseId) throws SQLException {
        String sql = "DELETE FROM AuctionHouse WHERE AuctionHouse_ID = ?";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, auctionHouseId);
            return stmt.executeUpdate() > 0;
        }
    }
}
