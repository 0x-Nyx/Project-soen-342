package Model;

public class Admin {
    private static Admin instance = null;
    private int adminId;
    private String username;
    private String password;

    private Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public static synchronized Admin getInstance(int adminId, String username, String password) {
        if (instance == null) {
            instance = new Admin(adminId, username, password);
        }
        return instance;
    }

    public static Admin getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Admin instance not initialized");
        }
        return instance;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
