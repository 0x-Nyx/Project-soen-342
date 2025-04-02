
public class Admin {
    private static Admin instance = null;
    private String username;
    private String password;

    private Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static synchronized Admin getInstance(String username, String password) {
        if (instance == null) {
            instance = new Admin(username, password);
        }
        return instance;
    }

    public static Admin getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                    "Admin instance not initialized");
        }
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}