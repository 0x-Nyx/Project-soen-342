package Model;

public class Client {
    private int clientId;
    private String name;
    private String affiliation;
    private String email;
    private String password;
    private String phoneNumber;
    private String text;
    private String status;

    public Client(String name, String affiliation, String email, String password, String phoneNumber, String text,
            String status) {
        this.name = name;
        this.affiliation = affiliation;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.text = text;
        this.status = status;
    }

    public Client(int clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getText() {
        return text;
    }

    public String getStatus() {
        return status;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
