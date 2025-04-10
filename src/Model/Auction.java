package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Auction {

    private int auctionId;
    private Time beginTime;
    private Time endTime;
    private Date date;
    private String type;
    private List<Integer> expertiseIds;
    private boolean isViewing;
    private int auctionHouseId;

    public Auction(int auctionId, Time beginTime, Time endTime, Date date,
            String type, List<Integer> expertiseIds, boolean isViewing, int auctionHouseId) {
        this.auctionId = auctionId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.date = date;
        this.type = type;
        this.expertiseIds = expertiseIds;
        ;
        this.isViewing = isViewing;
        this.auctionHouseId = auctionHouseId;
    }

    public Auction(Time beginTime, Time endTime, Date date,
            String type, List<Integer> expertiseIds, boolean isViewing, int auctionHouseId) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.date = date;
        this.type = type;
        this.expertiseIds = expertiseIds;
        ;
        this.isViewing = isViewing;
        this.auctionHouseId = auctionHouseId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExpertiseIds(List<Integer> expertiseIds) {
        this.expertiseIds = expertiseIds;
    }

    public List<Integer> getExpertiseIds() {
        return expertiseIds;
    }

    public boolean isViewing() {
        return isViewing;
    }

    public void setViewing(boolean isViewing) {
        this.isViewing = isViewing;
    }

    public int getAuctionHouseId() {
        return auctionHouseId;
    }

    public void setAuctionHouseId(int auctionHouseId) {
        this.auctionHouseId = auctionHouseId;
    }

    @Override
    public String toString() {
        return String.format("AuctionID: %d, Type: %s, Date: %s, Begin Time: %s, End Time: %s",
                auctionId, type, date, beginTime, endTime);
    }
}
