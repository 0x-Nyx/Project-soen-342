package Model;

import java.util.List;

public class Expert {
    private int expertId;
    private String name;
    private String contactAddress;
    private String city;
    private String licenseNumber;
    private String password;
    private List<Integer> expertiseIds;
    private List<Integer> availabilityIds;

    public Expert(String name, String contactAddress, String city, String licenseNumber, String password,
            List<Integer> expertiseIds, List<Integer> availabilityIds) {
        this.name = name;
        this.contactAddress = contactAddress;
        this.city = city;
        this.licenseNumber = licenseNumber;
        this.password = password;
        this.expertiseIds = expertiseIds;
        this.availabilityIds = availabilityIds;
    }

    public Expert(int expertId, String name, String contactAddress, String city, String licenseNumber, String password,
            List<Integer> expertiseIds,
            List<Integer> availabilityIds) {
        this.expertId = expertId;
        this.name = name;
        this.contactAddress = contactAddress;
        this.city = city;
        this.licenseNumber = licenseNumber;
        this.password = password;
        this.expertiseIds = expertiseIds;
        this.availabilityIds = availabilityIds;
    }

    public int getExpertId() {
        return expertId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public List<Integer> getExpertiseIds() {
        return expertiseIds;
    }

    public void setExpertiseIds(List<Integer> expertiseIds) {
        this.expertiseIds = expertiseIds;
    }

    public List<Integer> getAvailabilityIds() {
        return availabilityIds;
    }

    public void setAvailabilityIds(List<Integer> availabilityIds) {
        this.availabilityIds = availabilityIds;
    }

    @Override
    public String toString() {
        return "Expert ID: " + expertId + ", Name: " + name + ", License Number: " + licenseNumber +
                ", Expertise: " + expertiseIds + ", Availability: " + availabilityIds;
    }

}
