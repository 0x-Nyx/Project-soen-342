package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import DB.Connect;
import Mapper.AuctionMapper;
import Mapper.ExpertMapper;
//import TDG.AuctionTDG;
import TDG.ObjectTDG;
import TDG.AuctionHouseTDG;
//import Model.Expert;
import TDG.ExpertTDG;
import TDG.AvailabilityTDG;
import TDG.ClientTDG;
//import Model.Client;

public class AdminConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu(scanner);

    }

    public static void printMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("[1] Add a new Object");
            System.out.println("[2] View all Objects");
            System.out.println("[3] Add an Auction House");
            System.out.println("[4] Add an Auction");
            System.out.println("[5] Add Experts");
            System.out.println("[6] View Clients Waiting to be Accepted");
            System.out.println("[7] Add Availability of an Expert");
            System.out.println("[8] Update the Object");
            System.out.println("[9] Delete a Client");
            System.out.println("[10] Delete an Expert");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObject(scanner);
                    break;
                case "2":
                    displayAllObjects();
                    break;
                case "3":
                    createAuctionHouse(scanner);
                    break;
                case "4":
                    createAuction(scanner);
                    break;
                case "5":
                    createExperts(scanner);
                    break;
                case "6":
                    pendingClient(scanner);
                    break;
                case "7":
                    addAvailabilityExpert(scanner);
                    break;
                case "8":
                    updateObject(scanner);
                    break;
                case "9":
                    deleteClient(scanner);
                    break;
                case "10":
                    deleteExpert(scanner);
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting the Admin Console. Good bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        // scanner.close();
    }

    private static void createNewObject(Scanner scanner) {
        System.out.print("Enter the name of the object: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter the type of the object (e.g. Painting): ");
        String type = scanner.nextLine().trim();

        System.out.print("Is the object owned by the institution? (yes/no): ");
        boolean owned = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Is the object currently auctioned? (yes/no): ");
        boolean auctioned = scanner.nextLine().equalsIgnoreCase("yes");

        ArtObject newObj = new ArtObject(name, type, owned, auctioned);

        try {
            int newId = ObjectTDG.insert(newObj);
            if (newId != -1) {
                System.out.println("Object created successfully with ID: " + newId);
            } else {
                System.out.println("Error: Could not retrieve new object ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error saving object: " + e.getMessage());
        }
    }

    private static void displayAllObjects() {
        try {
            List<ArtObject> objects = ObjectTDG.findAll();
            if (objects.isEmpty()) {
                System.out.println("No objects found in the catalog.");
            } else {
                System.out.println("\n========== OBJECT CATALOG ==========");
                for (ArtObject obj : objects) {
                    System.out.println("ID: " + obj.getId() + ", Name: " + obj.getName() + ", Type: " + obj.getType()
                            + ", Owned: " + obj.isOwnedByInstitution() + ", Auctioned: " + obj.isAuctioned());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving objects: " + e.getMessage());
        }
    }

    private static void createAuctionHouse(Scanner scanner) {
        System.out.println("\nCreating a new Auction House...");

        System.out.print("Enter Auction House Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Auction House Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter City: ");
        String city = scanner.nextLine().trim();

        AuctionHouse newAuctionHouse = new AuctionHouse(name, address, city);

        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO AuctionHouse (Name, Address, City) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, newAuctionHouse.getName());
            stmt.setString(2, newAuctionHouse.getAddress());
            stmt.setString(3, newAuctionHouse.getCity());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int auctionHouseId = generatedKeys.getInt(1);
                        newAuctionHouse.setAuctionHouseId(auctionHouseId);
                        System.out.println("Auction House created successfully with ID: " + auctionHouseId);
                    }
                }
            } else {
                System.out.println("Error: Could not create the Auction House.");
            }
        } catch (SQLException e) {
            System.err.println("Error creating Auction House: " + e.getMessage());
        }
    }

    private static void createAuction(Scanner scanner) {
        System.out.print("Enter the auction start time (HH:mm:ss): ");
        String beginTimeStr = scanner.nextLine().trim();
        Time beginTime = Time.valueOf(beginTimeStr);

        System.out.print("Enter the auction end time (HH:mm:ss): ");
        String endTimeStr = scanner.nextLine().trim();
        Time endTime = Time.valueOf(endTimeStr);

        System.out.print("Enter the auction date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        Date date = Date.valueOf(dateStr);

        System.out.print("Enter the type of auction (e.g., Art Auction, Car Auction): ");
        String type = scanner.nextLine().trim();

        System.out.println("\nAvailable Expertise Types:");
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement("SELECT Expertise_ID, Type FROM Type_Expertise");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("Expertise_ID");
                String expertiseType = rs.getString("Type");
                System.out.println(id + ": " + expertiseType);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching expertise types: " + e.getMessage());
            return;
        }

        List<Integer> expertiseIds = new ArrayList<>();
        System.out.print("Enter expertise IDs (comma separated): ");
        String expertiseInput = scanner.nextLine().trim();
        if (!expertiseInput.isEmpty()) {
            String[] expertiseArray = expertiseInput.split(",");
            for (String id : expertiseArray) {
                try {
                    expertiseIds.add(Integer.parseInt(id.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid expertise ID: " + id);
                }
            }
        }

        System.out.print("Is the auction currently being viewed? (true/false): ");
        boolean isViewing = Boolean.parseBoolean(scanner.nextLine().trim());

        System.out.println("\nAvailable Auction Houses:");
        try {
            List<AuctionHouse> auctionHouses = AuctionHouseTDG.findAll();

            for (AuctionHouse auctionHouse : auctionHouses) {
                System.out.println(auctionHouse.getAuctionHouseId() + ": " + auctionHouse.getName());
            }
        } catch (SQLException e) {
            System.err.println("Error fetching Auction Houses: " + e.getMessage());
            return;
        }

        System.out.print("Enter the Auction House ID: ");
        int auctionHouseId = Integer.parseInt(scanner.nextLine().trim());

        Auction newAuction = new Auction(beginTime, endTime, date, type, expertiseIds, isViewing, auctionHouseId);

        boolean success = AuctionMapper.insertAuctionWithDetails(newAuction);
        if (success) {
            System.out.println("Client created successfully with ID: " + newAuction.getAuctionId());
        } else {
            System.out.println("Error: Client creation failed.");
        }
    }

    private static void createExperts(Scanner scanner) {
        System.out.print("Enter the name of the Expert: ");
        String name = scanner.nextLine().trim();

        System.out.print("Your contact address: ");
        String contactAddress = scanner.nextLine().trim();

        System.out.print("Your city: ");
        String city = scanner.nextLine().trim();

        System.out.print("License number: ");
        String licenseNumber = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        List<Integer> expertiseIds = new ArrayList<>();
        System.out.println("\nAvailable Expertise Types:");
        try (Connection conn = Connect.connect();
                PreparedStatement stmt = conn.prepareStatement("SELECT Expertise_ID, Type FROM Type_Expertise");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("Expertise_ID");
                String expertiseType = rs.getString("Type");
                System.out.println(id + ": " + expertiseType);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching expertise types: " + e.getMessage());
            return;
        }

        System.out.print("Enter expertise IDs (comma separated): ");
        String expertiseInput = scanner.nextLine().trim();
        if (!expertiseInput.isEmpty()) {
            String[] expertiseArray = expertiseInput.split(",");
            for (String id : expertiseArray) {
                try {
                    expertiseIds.add(Integer.parseInt(id.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid expertise ID: " + id);
                }
            }
        }

        Expert newExpert = new Expert(name, contactAddress, city, licenseNumber, password, expertiseIds,
                new ArrayList<>());

        boolean success = ExpertMapper.insertExpertWithDetails(newExpert);
        if (success) {
            System.out.println("Client created successfully with ID: " + newExpert.getExpertId());
        } else {
            System.out.println("Error: Client creation failed.");
        }
    }

    private static void pendingClient(Scanner scanner) {
        try {
            List<Client> clients = ClientTDG.findAll();
            List<Client> pendingClients = new ArrayList<>();

            for (Client cli : clients) {
                if ("pending".equalsIgnoreCase(cli.getStatus())) {
                    pendingClients.add(cli);
                }
            }

            if (pendingClients.isEmpty()) {
                System.out.println("No pending clients found.");
                return;
            }

            System.out.println("\n========== PENDING CLIENTS ==========");
            for (Client cli : pendingClients) {
                System.out.println("ID: " + cli.getClientId() +
                        ", Name: " + cli.getName() +
                        ", Email: " + cli.getEmail() +
                        ", Phone: " + cli.getPhoneNumber() +
                        ", Text: " + cli.getText());
            }

            System.out.print("\nEnter the IDs of the clients to ACCEPT (comma-separated, or press Enter to skip): ");
            String acceptInput = scanner.nextLine().trim();
            if (!acceptInput.isEmpty()) {
                String[] acceptIds = acceptInput.split(",");
                for (String idStr : acceptIds) {
                    try {
                        int id = Integer.parseInt(idStr.trim());
                        Client client = ClientTDG.findById(id);
                        if (client != null && "pending".equalsIgnoreCase(client.getStatus())) {
                            client.setStatus("accepted");
                            ClientTDG.update(client);
                            System.out.println("Client ID " + id + " accepted.");
                        } else {
                            System.out.println("Client ID " + id + " not found or not pending.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format: " + idStr.trim());
                    }
                }
            } else {
                System.out.println("No clients were accepted.");
            }

            System.out.print("\nEnter the IDs of the clients to REFUSE (comma-separated, or press Enter to skip): ");
            String refuseInput = scanner.nextLine().trim();
            if (!refuseInput.isEmpty()) {
                String[] refuseIds = refuseInput.split(",");
                for (String idStr : refuseIds) {
                    try {
                        int id = Integer.parseInt(idStr.trim());
                        Client client = ClientTDG.findById(id);
                        if (client != null && "pending".equalsIgnoreCase(client.getStatus())) {
                            client.setStatus("refused");
                            ClientTDG.update(client);
                            System.out.println("Client ID " + id + " refused.");
                        } else {
                            System.out.println("Client ID " + id + " not found or not pending.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format: " + idStr.trim());
                    }
                }
            } else {
                System.out.println("No clients were refused.");
            }

        } catch (Exception e) {
            System.err.println("Error processing client updates: " + e.getMessage());
        }
    }

    private static void addAvailabilityExpert(Scanner scanner) {
        List<Availability> availabilityList = new ArrayList<>();
        List<Expert> allExpert = ExpertTDG.findAll();

        System.out.println("\nList of Experts:");
        for (Expert expert : allExpert) {
            System.out.println(expert.getExpertId() + ": " + expert.getName());
        }

        System.out.print("Enter the ID of the expert to assign availability: ");
        int expertId = Integer.parseInt(scanner.nextLine().trim());

        String moreAvailability = "yes";
        while (moreAvailability.equalsIgnoreCase("yes")) {
            System.out.print("Enter the day of availability (e.g., Monday, Tuesday): ");
            String day = scanner.nextLine().trim();

            System.out.print("Enter the start time (HH:mm format): ");
            String startTime = scanner.nextLine().trim();

            System.out.print("Enter the end time (HH:mm format): ");
            String endTime = scanner.nextLine().trim();

            Availability availability = new Availability(day, startTime, endTime);
            availabilityList.add(availability);

            System.out.print("Do you want to add another availability? (yes/no): ");
            moreAvailability = scanner.nextLine().trim();
        }

        try {
            List<Integer> availabilityIds = new ArrayList<>();
            for (Availability availability : availabilityList) {
                int availabilityId = AvailabilityTDG.insertAvailability(availability.getDay(),
                        availability.getStartTime(), availability.getEndTime());
                availabilityIds.add(availabilityId);
            }

            AvailabilityTDG.insertExpertAvailability(expertId, availabilityIds);

            System.out.println("Availability added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding availability: " + e.getMessage());
        }
    }

    private static void updateObject(Scanner scanner) {
        try {
            List<ArtObject> objects = ObjectTDG.findAll();

            if (objects.isEmpty()) {
                System.out.println("No objects found in the catalog.");
                return;
            }

            System.out.println("\n========== OBJECTS NOT OWNED BY INSTITUTION ==========");
            for (ArtObject obj : objects) {
                if (!obj.isOwnedByInstitution()) {
                    System.out.println("ID: " + obj.getId()
                            + ", Name: " + obj.getName()
                            + ", Type: " + obj.getType()
                            + ", Owned: " + obj.isOwnedByInstitution()
                            + ", Auctioned: " + obj.isAuctioned());
                }
            }

            System.out.print(
                    "\nEnter the IDs of the Objects to mark as owned by the Institution (comma-separated, or press Enter to skip): ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                String[] idStrings = input.split(",");
                for (String idStr : idStrings) {
                    try {
                        int objectId = Integer.parseInt(idStr.trim());
                        ArtObject object = ObjectTDG.findById(objectId);

                        if (object != null && !object.isOwnedByInstitution()) {
                            object.setOwnedByInstitution(true);
                            ObjectTDG.update(object);
                            System.out
                                    .println(" Object ID " + objectId + " is now marked as owned by the institution.");
                        } else {
                            System.out.println(" Object ID " + objectId + " not found or already owned.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid ID format: " + idStr.trim());
                    } catch (SQLException e) {
                        System.err.println("SQL Error for object ID " + idStr.trim() + ": " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No ownership changes made.");
            }

            System.out.println("\n========== OBJECTS OWNED BY INSTITUTION ==========");
            for (ArtObject obj : objects) {
                if (obj.isOwnedByInstitution()) {
                    System.out.println("ID: " + obj.getId()
                            + ", Name: " + obj.getName()
                            + ", Type: " + obj.getType()
                            + ", Owned: " + obj.isOwnedByInstitution()
                            + ", Auctioned: " + obj.isAuctioned());
                }
            }

            System.out.print(
                    "\nEnter the IDs of the Objects to remove from institution ownership (comma-separated, or press Enter to skip): ");
            String inputStr = scanner.nextLine().trim();

            if (!inputStr.isEmpty()) {
                String[] idStrings = inputStr.split(",");
                for (String idStr : idStrings) {
                    try {
                        int objectId = Integer.parseInt(idStr.trim());
                        ArtObject object = ObjectTDG.findById(objectId);

                        if (object != null && object.isOwnedByInstitution()) {
                            object.setOwnedByInstitution(false);
                            ObjectTDG.update(object);
                            System.out.println(
                                    " Object ID " + objectId + " is no longer marked as owned by the institution.");
                        } else {
                            System.out.println(" Object ID " + objectId + " not found or already not owned.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid ID format: " + idStr.trim());
                    } catch (SQLException e) {
                        System.err.println("SQL Error for object ID " + idStr.trim() + ": " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No changes to ownership were made.");
            }

            System.out.println("\n========== OBJECTS NOT AUCTIONED ==========");
            for (ArtObject obj : objects) {
                if (!obj.isAuctioned()) {
                    System.out.println("ID: " + obj.getId()
                            + ", Name: " + obj.getName()
                            + ", Type: " + obj.getType()
                            + ", Owned: " + obj.isOwnedByInstitution()
                            + ", Auctioned: " + obj.isAuctioned());
                }
            }

            System.out.print(
                    "\nEnter the IDs of the Objects to mark as auctioned (comma-separated, or press Enter to skip): ");
            String inputAuction = scanner.nextLine().trim();

            if (!inputAuction.isEmpty()) {
                String[] idStrings = inputAuction.split(",");
                for (String idStr : idStrings) {
                    try {
                        int objectId = Integer.parseInt(idStr.trim());
                        ArtObject object = ObjectTDG.findById(objectId);

                        if (object != null && !object.isAuctioned()) {
                            object.setAuctioned(true);
                            ObjectTDG.update(object);
                            System.out.println(" Object ID " + objectId + " is now marked as auctioned.");
                        } else {
                            System.out.println(" Object ID " + objectId + " not found or already auctioned.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid ID format: " + idStr.trim());
                    } catch (SQLException e) {
                        System.err.println("SQL Error for object ID " + idStr.trim() + ": " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No auction updates made.");
            }

            System.out.println("\n========== OBJECTS CURRENTLY AUCTIONED ==========");
            for (ArtObject obj : objects) {
                if (obj.isAuctioned()) {
                    System.out.println("ID: " + obj.getId()
                            + ", Name: " + obj.getName()
                            + ", Type: " + obj.getType()
                            + ", Owned: " + obj.isOwnedByInstitution()
                            + ", Auctioned: " + obj.isAuctioned());
                }
            }

            System.out.print(
                    "\nEnter the IDs of the Objects to remove from auction (comma-separated, or press Enter to skip): ");
            String inputAuc = scanner.nextLine().trim();

            if (!inputAuc.isEmpty()) {
                String[] idStrings = inputAuc.split(",");
                for (String idStr : idStrings) {
                    try {
                        int objectId = Integer.parseInt(idStr.trim());
                        ArtObject object = ObjectTDG.findById(objectId);

                        if (object != null && object.isAuctioned()) {
                            object.setAuctioned(false);
                            ObjectTDG.update(object);
                            System.out.println("Object ID " + objectId + " is no longer marked as auctioned.");
                        } else {
                            System.out.println(" Object ID " + objectId + " not found or already not auctioned.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid ID format: " + idStr.trim());
                    } catch (SQLException e) {
                        System.err.println("SQL Error for object ID " + idStr.trim() + ": " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No auction status changes made.");
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving objects: " + e.getMessage());
        }
    }

    private static void deleteClient(Scanner scanner) {
        try {
            List<Client> clients = ClientTDG.findAll();
            if (clients.isEmpty()) {
                System.out.println("No clients found in the catalog.");
                return;
            }

            System.out.println("\n========== CLIENT CATALOG ==========");
            for (Client cli : clients) {
                System.out.println("ID: " + cli.getClientId() + ", Name: " + cli.getName());
            }

            System.out.print("Enter the ID of the client you want to delete: ");
            int clientIdToDelete = Integer.parseInt(scanner.nextLine());

            boolean success = ClientTDG.delete(clientIdToDelete);
            if (success) {
                System.out.println("Client deleted successfully.");
            } else {
                System.out.println("Failed to delete client.");
            }

        } catch (SQLException e) {
            System.err.println("SQL error while retrieving or deleting clients: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid number.");
        }
    }

    private static void deleteExpert(Scanner scanner) {
        try {
            List<Expert> experts = ExpertTDG.findAll();
            if (experts.isEmpty()) {
                System.out.println("No clients found in the catalog.");
                return;
            }

            System.out.println("\n========== CLIENT CATALOG ==========");
            for (Expert exp : experts) {
                System.out.println("ID: " + exp.getExpertId() + ", Name: " + exp.getName());
            }

            System.out.print("Enter the ID of the expert you want to delete: ");
            int expertIdToDelete = Integer.parseInt(scanner.nextLine());

            boolean success = ExpertTDG.delete(expertIdToDelete);
            if (success) {
                System.out.println("Client deleted successfully.");
            } else {
                System.out.println("Failed to delete client.");
            }

        } catch (SQLException e) {
            System.err.println("SQL error while retrieving or deleting clients: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid number.");
        }
    }

}
