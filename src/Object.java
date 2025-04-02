public class Object {
    private static int counter = 1;
    private int id;
    private String name;
    private String type;
    private boolean ownedByInstitution;
    private boolean auctioned;

    public Object(String name, String type, boolean ownedByInstitution, boolean auctioned) {
        this.id = counter++;
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

    @Override
    public String toString() {
        return String.format("%-5d | %-20s | %-10s | %-8b | %-8b",
                id, name, type, ownedByInstitution, auctioned);
    }
}