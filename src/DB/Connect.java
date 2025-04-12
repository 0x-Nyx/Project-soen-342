package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    private static Connection conn = null;

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/soen342"; // Replace with your actual DB name
            String user = "root";
            String password = "Password1234";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to MySQL has been established.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return conn;
    }

    // TO VERIFY THE CONENCTION IS DONE CORRECTLY

    public static void listTables() {
        if (conn == null) {
            System.out.println("No active database connection.");
            return;
        }

        String query = "SHOW TABLES";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Tables in the database:");
            while (rs.next()) {
                System.out.println("- " + rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving tables: " + e.getMessage());
        }
    }

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Disconnected from MySQL.");
            }
        } catch (SQLException ex) {
            System.out.println("Error while disconnecting: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection testConn = connect();

        listTables();

        disconnect();
    }
}
