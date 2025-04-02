public class Auction {
   
    private String time;
    private String date;
    private String type;
    private boolean isViewing;
    
    public Auction(String time, String date, String type, boolean isViewing) {
        this.time = time;
        this.date = date;
        this.type = type;
        this.isViewing = isViewing;
    }
    public String getTime() {
        return time;
    }
    public String getDate() {
        return date;
    }
    public String getType() {
        return type;
    }
    public boolean isViewing() {
        return isViewing;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setViewing(boolean isViewing) {
        this.isViewing = isViewing;
    }

    
}