public class Expert {
    private String name;
    private String contactAddress;
    private int licenseNumber;
    private String areaOfExpertise;
    private String availability;

    public Expert(String name, String contactAddress, int licenseNumber, String areaOfExpertise, String availability) {
        this.name = name;
        this.contactAddress = contactAddress;
        this.licenseNumber = licenseNumber;
        this.areaOfExpertise = areaOfExpertise;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public String getAvailability() {
        return availability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}