package Model;

public class Availability {
    private int availabilityId;
    private String day;
    private String startTime;
    private String endTime;

    public Availability(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Availability(int availabilityId, String day, String startTime, String endTime) {
        this.availabilityId = availabilityId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Availability ID: " + availabilityId + ", Day: " + day +
                ", Start Time: " + startTime + ", End Time: " + endTime;
    }
}
