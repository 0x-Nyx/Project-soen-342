package Model;

public class AuctionHouse {
    private int auctionHouseId;
    private String name;
    private String address;
    private String city;

    public AuctionHouse(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public AuctionHouse(int auctionHouseId, String name, String address, String city) {
        this.auctionHouseId = auctionHouseId;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public int getAuctionHouseId() {
        return auctionHouseId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAuctionHouseId(int auctionHouseId) {
        this.auctionHouseId = auctionHouseId;
    }
}
