import java.util.Scanner;

public class AdminConsole {
    // making the output console
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ObjectCatalog catalog = new ObjectCatalog();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObject(scanner, catalog);
                    break;
                case "2":
                    catalog.displayAllObjects();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting the Admin Console. Good bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }


    private static void printMenu() {
        System.out.println("\n========== ADMIN MENU ==========");
        System.out.println("[1] Create a new Object");
        System.out.println("[2] View Catalog");
        System.out.println("[0] Exit");
        System.out.print("Choose an option: ");
    }

    // creating the object method; admin
    private static void createNewObject(Scanner scanner, ObjectCatalog catalog) {
        System.out.print("Enter the name of the object: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter the type of the object (e.g. Painting): ");
        String type = scanner.nextLine().trim();

        System.out.print("Is the object owned by the institution? (yes/no): ");
        boolean owned = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Is the object currently auctioned? (yes/no): ");
        boolean auctioned = scanner.nextLine().equalsIgnoreCase("yes");

        Object newObject = new Object(name, type, owned, auctioned);


        catalog.addObject(newObject);

        System.out.println("New Object created with ID: " + newObject.getId());
    }
}
