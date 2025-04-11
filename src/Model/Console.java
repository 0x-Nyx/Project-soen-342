package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DB.Connect;
import Mapper.ClientMapper;

public class Console {

    public static void main(String[] args) {
        menuUser();

    }

    public static void menuUser() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========== MENU ==========");
            System.out.println("[1] Login as the Admin");
            System.out.println("[2] Login as the Expert");
            System.out.println("[3] Login as the Client");
            System.out.println("[4] Sign up as the Client");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    loginAdmin(scanner);
                    break;
                case "2":
                    loginExpert(scanner);
                    break;
                case "3":
                    loginClient(scanner);
                    break;
                case "4":
                    signUpClient(scanner);
                    break;
                case "0":
                    System.out.println("Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    private static int loginExpert(Scanner scanner) {
        System.out.println("Enter your license number: ");
        String licenseNumber = scanner.nextLine().trim();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine().trim();

        String query = """
                SELECT Expert_ID, Name_Expert FROM Expert
                    WHERE LicenseNumber= ? AND Password = ?;""";

        int expertId = -1;
        String nameExpert = " ";
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, licenseNumber);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                expertId = rs.getInt("Expert_ID");
                nameExpert = rs.getString("Name_Expert");
                System.out.println("Login successful! Welcome, expert " + nameExpert);
                ExpertConsole.runConsole(expertId);
            } else {
                System.out.println("Invalid license number or password.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return expertId;
    }

    private static int loginAdmin(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine().trim();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine().trim();

        String query = """
                SELECT Admin_ID, Username
                FROM Admin
                WHERE Username = ? AND Password = ?;
                """;

        int adminId = -1;

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                adminId = rs.getInt("Admin_ID");
                String user = rs.getString("Username");
                System.out.println("Login successful! Welcome, " + user);
                AdminConsole.printMenu(scanner);
            } else {
                System.out.println("Invalid username or password.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return adminId;
    }

    private static int loginClient(Scanner scanner) {
        System.out.println("Enter your email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine().trim();

        String query = """
                SELECT Client_ID, Name_Client, status
                FROM Client
                    WHERE Email = ? AND Password = ?;""";

        String status = " ";
        int clientId = -1;
        String nameClient = " ";

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                clientId = rs.getInt("Client_ID");
                nameClient = rs.getString("Name_Client");
                status = rs.getString("status");

                if ("pending".equalsIgnoreCase(status)) {
                    System.out.println("Your account is pending approval by the admin.");
                    clientId = -1;

                } else {
                    System.out.println("Login successful! Welcome, " + nameClient);
                    ClientConsole.displayMenu(scanner, clientId);
                }
            } else {
                System.out.println("Invalid email or password.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return clientId;
    }

    private static void signUpClient(Scanner scanner) {
        System.out.print("Enter the name : ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter your affiliation: ");
        String affiliation = scanner.nextLine().trim();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("Enter your Password: ");
        String password = scanner.nextLine().trim();

        System.out.print("A brief text explaining your intent to use the system: ");
        String text = scanner.nextLine().trim();

        String status = "pending";

        Client newClient = new Client(name, affiliation, email, password, phoneNumber, text,
                status);

        boolean success = ClientMapper.save(newClient);
        if (success) {
            System.out.println("Client created successfully with ID: " + newClient.getClientId());
        } else {
            System.out.println("Error: Client creation failed.");
        }
    }
}