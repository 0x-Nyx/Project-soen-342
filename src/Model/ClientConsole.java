import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientConsole {
    private ObjectCatalog catalog;
    private List<Object> interestedObjects = new ArrayList<>();

    public ClientConsole(ObjectCatalog catalog) {
        this.catalog = catalog;
    }

    public void viewAllOwnedObjects() {
        List<Object> ownedObjects = catalog.getObjects().stream()
                .filter(Object::isOwnedByInstitution)
                .collect(Collectors.toList());

        if (ownedObjects.isEmpty()) {
            System.out.println("No objects owned by the institution.");
        } else {
            System.out.println("\n----- Owned by Institution -----");
            System.out.println("\n ID   | Name                 | Type       | Owned by | Auctioned");
            System.out.println("-------------------------------------------------------------");
            for (Object obj : ownedObjects) {
                System.out.println(obj);
            }
        }
    }

    public void expressInterestByType(Scanner scanner) {
        System.out.print("Enter the type of object you are interested in (Painting, Sculpture, etc.): ");
        String typeInput = scanner.nextLine().trim();

        List<Object> matchingObjects = catalog.getObjects().stream()
                .filter(obj -> obj.getType().equalsIgnoreCase(typeInput))
                .collect(Collectors.toList());

        if (matchingObjects.isEmpty()) {
            System.out.println("No objects found of type '" + typeInput + "'.");
            return;
        }

        System.out.println("\n----- Objects of Type: " + typeInput + " -----");
        System.out.println("\n ID   | Name                 | Type       | Owned by | Auctioned");
        System.out.println("-------------------------------------------------------------");
        for (Object obj : matchingObjects) {
            System.out.println(obj);
        }

        System.out.print("\nEnter the ID of the object you want to mark as interested: ");
        String idStr = scanner.nextLine().trim();
        int objectId;

        try {
            objectId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Returning to menu...");
            return;
        }

        Object chosen = matchingObjects.stream()
                .filter(o -> o.getId() == objectId)
                .findFirst()
                .orElse(null);

        if (chosen == null) {
            System.out.println("No matching object with ID: " + objectId + ". Returning to menu...");
        } else {
            interestedObjects.add(chosen);
            System.out.println("You have expressed interest in: " + chosen.getName());
        }
    }

    public void viewInterestedObjects() {
        if (interestedObjects.isEmpty()) {
            System.out.println("You have no objects of interest yet.");
        } else {
            System.out.println("\n----- Your Interested Objects -----");
            System.out.println("\n ID   | Name                 | Type       | Owned by | Auctioned");
            System.out.println("-------------------------------------------------------------");
            for (Object obj : interestedObjects) {
                System.out.println(obj);
            }
        }
    }

    public static void main(String[] args) {

        ObjectCatalog catalog = new ObjectCatalog();
        catalog.addObject(new Object("Mona Lisa", "Painting", true, false));
        catalog.addObject(new Object("The Thinker", "Sculpture", false, true));
        catalog.addObject(new Object("Starry Night", "Painting", true, false));
        catalog.addObject(new Object("Black Basalt", "Vase", false, false));
        catalog.addObject(new Object("Dipylon Amphora", "Vase", true, true));

        ClientConsole clientConsole = new ClientConsole(catalog);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========== CLIENT MENU ==========");
            System.out.println("[1] View ALL Owned Objects by the Instutition");
            System.out.println("[2] Express Interest by the Type of Object");
            System.out.println("[3] View Your Objects of Interest");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    clientConsole.viewAllOwnedObjects();
                    break;
                case "2":
                    clientConsole.expressInterestByType(scanner);
                    break;
                case "3":
                    clientConsole.viewInterestedObjects();
                    break;
                case "0":
                    running = false;
                    System.out.println("Thank you for using Client's Console!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }
}