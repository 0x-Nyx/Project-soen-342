public class Client {
    private String name;
    private String password;
    private String contactInfo;
    private String affiliation;

    public Client(String name, String password, String contactInfo, String affiliation) {
        this.name = name;
        this.password = password;
        this.contactInfo = contactInfo;
        this.affiliation = affiliation;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

}