import java.util.*;

public class ExpertConsole {
    private ObjectCatalog catalog;
    private Map<Integer, List<Object>> clientInterests = new HashMap<>();
    private Map<Integer, String> clientNames = new HashMap<>();
    private Expert expert;

    public ExpertConsole(ObjectCatalog catalog, Expert expert) {
        this.catalog = catalog;
        this.expert = expert;
    }

    public void listClients() {
        if (clientInterests.isEmpty()) {
            System.out.println("No clients with interests available.");
            return;
        }
        // Display Clients
        System.out.println("\n----- List of Clients -----\n");
        System.out.printf("%-10s | %-20s%n", "ID", "Name Client");
        System.out.println("---------------------------------");

        clientNames.forEach((id, name) -> System.out.printf("%-10s | %-20s%n", id, name));

    }

    // Display Object for a specific Client
    public void viewClientInterests(int clientId) {
        List<Object> objects = clientInterests.get(clientId);
        String clientName = clientNames.get(clientId);

        if (objects == null || objects.isEmpty()) {
            System.out.println("No interests found for Client ID: " + clientId);
            return;
        }

        System.out.println("\n----- Objects of Interest for " + clientName + " -----");
        System.out.println("\n ID   | Name                 | Type       | Owned by | Auctioned");
        System.out.println("-------------------------------------------------------------");
        objects.forEach(System.out::println);
    }

    public void registerClientInterest(int clientId, String clientName, Object object) {
        clientInterests.computeIfAbsent(clientId, k -> new ArrayList<>()).add(object);
        clientNames.putIfAbsent(clientId, clientName);
    }

    // Display Expert
    public void displayExpertInfo() {
        System.out.println("\n ----------Personal Information---------");
        System.out.println("Name: " + expert.getName());
        System.out.println("Address: " + expert.getContactAddress());
        System.out.println("License Number: " + expert.getLicenseNumber());
        System.out.println("Area of Expertise: " + expert.getAreaOfExpertise());
        System.out.println("Availability: " + expert.getAvailability());
    }

    // Display Menu
    public void runConsole() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n========== EXPERT MENU ==========");
            System.out.println("[1] View all clients");
            System.out.println("[2] Choose a client to view interests");
            System.out.println("[3] View my personal information");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listClients();
                    break;
                case "2":
                    System.out.print("Enter Client ID to view interests: ");
                    int clientId = Integer.parseInt(scanner.nextLine().trim());
                    viewClientInterests(clientId);
                    break;
                case "3":
                    displayExpertInfo();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting the Expert Console. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ObjectCatalog catalog = new ObjectCatalog();

        // hardcode objects
        catalog.addObject(new Object("Mona Lisa", "Painting", true, false));
        catalog.addObject(new Object("The Thinker", "Sculpture", false, true));
        catalog.addObject(new Object("Starry Night", "Painting", true, false));

        Expert expert = new Expert("Dr. John Smith", "123 Art St, City", 123456, "Painting", "Available");
        ExpertConsole expertConsole = new ExpertConsole(catalog, expert);

        // hardcode the clients
        expertConsole.registerClientInterest(1, "Maria-Christine", catalog.getObjects().get(0));
        expertConsole.registerClientInterest(2, "Jessica", catalog.getObjects().get(2));

        expertConsole.runConsole();
    }
}