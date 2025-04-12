package Model;

public class ArtObject {
    private int id;
    private String name;
    private String type;
    private boolean ownedByInstitution;
    private boolean auctioned;

    public ArtObject(String name, String type, boolean ownedByInstitution, boolean auctioned) {
        this.name = name;
        this.type = type;
        this.ownedByInstitution = ownedByInstitution;
        this.auctioned = auctioned;
    }

    public ArtObject(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ArtObject(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public ArtObject(int id, String name, String type, boolean ownedByInstitution, boolean auctioned) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.ownedByInstitution = ownedByInstitution;
        this.auctioned = auctioned;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isOwnedByInstitution() {
        return ownedByInstitution;
    }

    public boolean isAuctioned() {
        return auctioned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOwnedByInstitution(boolean ownedByInstitution) {
        this.ownedByInstitution = ownedByInstitution;
    }

    public void setAuctioned(boolean auctioned) {
        this.auctioned = auctioned;
    }
}
