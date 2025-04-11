package Mapper;

import Model.AuctionHouse;
import TDG.AuctionHouseTDG;

import java.sql.SQLException;
import java.util.List;

public class AuctionHouseMapper {

    public static AuctionHouse createAuctionHouse(String name, String address, String city) throws SQLException {
        AuctionHouse auctionHouse = new AuctionHouse(name, address, city);
        int id = AuctionHouseTDG.insert(auctionHouse);
        if (id != -1) {
            auctionHouse.setAuctionHouseId(id);
            return auctionHouse;
        }
        return null;
    }

    public static AuctionHouse getAuctionHouseById(int auctionHouseId) throws SQLException {
        return AuctionHouseTDG.findById(auctionHouseId);
    }

    public static List<AuctionHouse> getAllAuctionHouses() throws SQLException {
        return AuctionHouseTDG.findAll();
    }

    public static boolean updateAuctionHouse(AuctionHouse auctionHouse) throws SQLException {
        return AuctionHouseTDG.update(auctionHouse);
    }

    public static boolean deleteAuctionHouse(int auctionHouseId) throws SQLException {
        return AuctionHouseTDG.delete(auctionHouseId);
    }
}
