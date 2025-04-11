package Mapper;

import Model.Auction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import TDG.AuctionTDG;
import DB.Connect;

public class AuctionMapper {

    public static void mapAuctionToInsertStatement(Auction auction, PreparedStatement stmt) throws SQLException {
        stmt.setTime(1, auction.getBeginTime());
        stmt.setTime(2, auction.getEndTime());
        stmt.setDate(3, auction.getDate());
        stmt.setString(4, auction.getType());
        stmt.setBoolean(6, auction.isViewing());
        stmt.setInt(7, auction.getAuctionHouseId());
    }

    public static void mapAuctionToUpdateStatement(Auction auction, PreparedStatement stmt) throws SQLException {
        stmt.setTime(1, auction.getBeginTime());
        stmt.setTime(2, auction.getEndTime());
        stmt.setDate(3, auction.getDate());
        stmt.setString(4, auction.getType());
        stmt.setBoolean(6, auction.isViewing());
        stmt.setInt(7, auction.getAuctionHouseId());
        stmt.setInt(8, auction.getAuctionId());
    }

    public static boolean insertAuctionWithDetails(Auction auction) {
        boolean success = false;
        String query = "INSERT INTO Auction (Begin_time, End_time, Date, Type, isViewing, AuctionHouse_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setTime(1, auction.getBeginTime());
            pstmt.setTime(2, auction.getEndTime());
            pstmt.setDate(3, auction.getDate());
            pstmt.setString(4, auction.getType());
            pstmt.setBoolean(5, auction.isViewing());
            pstmt.setInt(6, auction.getAuctionHouseId());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int auctionId = rs.getInt(1);
                        auction.setAuctionId(auctionId);

                        AuctionTDG.insertAuctionExpertise(auctionId, auction.getExpertiseIds());

                        success = true;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error inserting expert with details: " + e.getMessage());
        }

        return success;
    }

    public static Auction mapRowToAuction(ResultSet rs) throws SQLException {
        int auctionId = rs.getInt("Auction_ID");
        Time beginTime = rs.getTime("Begin_time");
        Time endTime = rs.getTime("End_time");
        Date date = rs.getDate("Date");
        String type = rs.getString("type");
        boolean isViewing = rs.getBoolean("isViewing");
        int auctionHouseId = rs.getInt("AuctionHouse_ID");
        Auction auction = new Auction(auctionId, beginTime, endTime, date, type, null, isViewing, auctionHouseId);

        return auction;
    }

    public static Auction map(ResultSet rs) throws SQLException {
        int auctionId = rs.getInt("Auction_ID");
        Time beginTime = rs.getTime("Begin_time");
        Time endTime = rs.getTime("End_time");
        Date date = rs.getDate("Date");
        String type = rs.getString("Type");
        int auctionHouseId = rs.getInt("AuctionHouse_ID");

        List<Integer> expertiseIds = new ArrayList<>();
        boolean isViewing = false;

        return new Auction(auctionId, beginTime, endTime, date, type, expertiseIds, isViewing, auctionHouseId);
    }
}