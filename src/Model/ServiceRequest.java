package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceRequest {

    private int requestId;
    private int clientId;
    private int expertId;
    private LocalDate requestDate;
    private LocalTime requestStartTime;
    private LocalTime requestEndTime;
    private int requestTypeId;
    private String status;

    public ServiceRequest(int clientId, int expertId,
            LocalDate requestDate, LocalTime requestStartTime, LocalTime requestEndTime,
            int requestTypeId, String status) {
        this.clientId = clientId;
        this.expertId = expertId;
        this.requestDate = requestDate;
        this.requestStartTime = requestStartTime;
        this.requestEndTime = requestEndTime;
        this.requestTypeId = requestTypeId;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public void setRequestStartTime(LocalTime requestStartTime) {
        this.requestStartTime = requestStartTime;
    }

    public void setRequestEndTime(LocalTime requestEndTime) {
        this.requestEndTime = requestEndTime;
    }

    public void setRequestTypeId(int requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getExpertId() {
        return expertId;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public LocalTime getRequestStartTime() {
        return requestStartTime;
    }

    public LocalTime getRequestEndTime() {
        return requestEndTime;
    }

    public int getRequestTypeId() {
        return requestTypeId;
    }

    public String getStatus() {
        return status;
    }

}
