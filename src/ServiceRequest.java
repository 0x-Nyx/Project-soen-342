public class ServiceRequest {

    private String requestType;
    private String requestDate;
    private boolean Status;
    
    public ServiceRequest(String requestType, String requestDate, boolean status) {
        this.requestType = requestType;
        this.requestDate = requestDate;
        Status = status;
    }
    
    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public String getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
    public boolean isStatus() {
        return Status;
    }
    public void setStatus(boolean status) {
        Status = status;
    } 

    
    
}