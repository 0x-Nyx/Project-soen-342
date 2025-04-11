package Model;

import java.sql.SQLException;

import java.util.*;

import Mapper.ObjectOfInterestMapper;
import TDG.AuctionTDG;
import TDG.AvailabilityTDG;
import TDG.ObjectTDG;
import TDG.ServiceRequestTDG;

public class ExpertConsole {
    public static void main(String[] args) {

    }

    public static void runConsole(int expertId) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========== EXPERT MENU ==========");
            System.out.println("[1] View Auctions");
            System.out.println("[2] Add My Availability");
            System.out.println("[3] View Pending Services");
            System.out.println("[4] View All Objects");
            System.out.println("[5] View Clients' Objects of Interest");
            System.out.println("[6] View My Services");
            System.out.println("[0] Logout");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewAuctionExpert(scanner, expertId);
                    break;
                case "2":
                    addAvailabilityExpert(scanner, expertId);
                    break;
                case "3":
                    viewService(scanner, expertId);
                    break;
                case "4":
                    viewAllObject(scanner);
                    break;
                case "5":
                    viewObjectInterestClient(scanner, expertId);
                    break;
                case "6":
                    viewMyServices(scanner, expertId);
                    break;
                case "0":
                    running = false;
                    System.out.println("Logging out of Expert Console. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

    }

    private static void viewAuctionExpert(Scanner scanner, int expertId) {
        Map<Auction, String> auctionMap = AuctionTDG.findAuctionsByExpertId(expertId);

        if (auctionMap.isEmpty()) {
            System.out.println("No auctions found matching your criteria.");
        } else {
            System.out.println("Matching auctions:");
            for (Map.Entry<Auction, String> entry : auctionMap.entrySet()) {
                Auction auction = entry.getKey();
                String auctionHouse = entry.getValue();

                System.out.printf("Auction #%d | %s to %s | %s | %s | Auction House: %s%n",
                        auction.getAuctionId(),
                        auction.getBeginTime(),
                        auction.getEndTime(),
                        auction.getDate(),
                        auction.getType(),
                        auctionHouse);
            }
        }
    }

    private static void addAvailabilityExpert(Scanner scanner, int expertId) {
        List<Availability> availabilityList = new ArrayList<>();

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

    private static void viewService(Scanner scanner, int expertId) {
        System.out.println("Pending Requests:");
        List<ServiceRequest> requests = ServiceRequestTDG.findAllPendingRequests();

        if (requests.isEmpty()) {
            System.out.println("No request available. Come back later");
        } else {
            System.out.println("\n Enter the Request ID you want to accept:");
            int requestId = scanner.nextInt();

            if (requestId == 0) {
                System.out.println("You chose to skip this request.");
            } else {
                boolean success = ServiceRequestTDG.acceptRequest(requestId, expertId);

                if (success) {
                    System.out.println("Request " + requestId + " has been accepted!");
                } else {
                    System.out.println("Failed to accept the request.");
                }
            }
        }

    }

    private static void viewAllObject(Scanner scanner) {
        try {
            List<ArtObject> objects = ObjectTDG.findAll();
            if (objects.isEmpty()) {
                System.out.println("No objects found in the catalog.");
            } else {
                System.out.println("\n========== OBJECT CATALOG ==========");
                for (ArtObject obj : objects) {
                    if (obj.isOwnedByInstitution()) {
                        System.out.println("ID: " + obj.getId()
                                + ", Name: " + obj.getName()
                                + ", Type: " + obj.getType()
                                + ", Owned: " + obj.isOwnedByInstitution()
                                + ", Auctioned: " + obj.isAuctioned());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving objects: " + e.getMessage());
        }
    }

    private static void viewObjectInterestClient(Scanner scanner, int expertId) {
        try {

            List<Client> clients = ObjectOfInterestMapper.getClientsForExpert(expertId);

            if (clients.isEmpty()) {
                System.out.println("No clients found for this expert.");
                return;
            }

            System.out.println(" Clients you are serving:");
            for (int i = 0; i < clients.size(); i++) {
                Client client = clients.get(i);
                System.out.printf("%d. Client ID: %d | Name: %s%n", i + 1, client.getClientId(), client.getName());
            }

            System.out.print("Enter the number of the client you want to view objects of interest for: ");
            int clientChoice = Integer.parseInt(scanner.nextLine()) - 1;
            if (clientChoice < 0 || clientChoice >= clients.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            Client selectedClient = clients.get(clientChoice);
            int clientId = selectedClient.getClientId();

            List<ObjectOfInterest> interests = ObjectOfInterestMapper.getInterestsForClient(clientId);

            if (interests.isEmpty()) {
                System.out.println("No objects of interest found for this client.");
            } else {
                System.out.println(" Objects of interest for " + selectedClient.getName() + ":");
                for (ObjectOfInterest obj : interests) {
                    System.out.printf("Object ID: %d | Name: %s | Type: %s%n",
                            obj.getId(), obj.getName(), obj.getType());
                }
            }
        } catch (SQLException e) {
            System.out.println(" Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(" Unexpected error: " + e.getMessage());
        }
    }

    private static void viewMyServices(Scanner scanner, int expertId) {
        System.out.println("Pending Requests:");
        List<ServiceRequest> requests = ServiceRequestTDG.findAllRequests(expertId);

        if (requests.isEmpty()) {
            System.out.println("No request service. Go accept some");
        }
    }
}