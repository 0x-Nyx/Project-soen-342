package Model;

public class ObjectOfInterest {
    private int id;
    private String name;
    private String type;
    private int clientId;

    public ObjectOfInterest(String name, String type, int clientId) {
        this.name = name;
        this.type = type;
        this.clientId = clientId;
    }

    public ObjectOfInterest(int id, String name, String type, int clientId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.clientId = clientId;
    }

    public ObjectOfInterest(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public int getClientId() {
        return clientId;
    }

    public void setId(int id) {
        this.id = id;
    }
}