package Model;

import java.sql.Date;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Mapper.ObjectMapper;
import Mapper.ObjectOfInterestMapper;
import Mapper.ServiceRequestMapper;
import TDG.AuctionHouseTDG;
import TDG.AuctionTDG;
import TDG.ServiceRequestTDG;

public class ClientConsole {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int clientId = 1;

        displayMenu(scanner, clientId);

    }

    public static void displayMenu(Scanner scanner, int clientId) {
        boolean running = true;

        while (running) {
            System.out.println("\n========== CLIENT MENU ==========");
            System.out.println("[1] Search for Auctions");
            System.out.println("[2] Request a Service");
            System.out.println("[3] Add Object of Interest");
            System.out.println("[4] View My Objects of Interest");
            System.out.println("[5] Delete Object of Interest");
            System.out.println("[0] Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    searchAuction(scanner);
                    break;
                case "2":
                    requestService(scanner, clientId);
                    break;
                case "3":
                    addObjectInterest(scanner, clientId);
                    break;
                case "4":
                    viewObjectInterest(scanner, clientId);
                    break;
                case "5":
                    deleteObjectInterest(scanner, clientId);
                    break;
                case "0":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }

    private static void searchAuction(Scanner scanner) {
        try {
            System.out.println("When do you want to participate in the auction (YYYY-MM-DD):");
            String dateStr = scanner.nextLine().trim();
            Date date = Date.valueOf(dateStr);

            System.out.println("At what time (HH:mm:ss):");
            String timeStr = scanner.nextLine().trim();
            Time time = Time.valueOf(timeStr);

            System.out.println("What type of object are you interested in?");
            String type = scanner.nextLine().trim();

            System.out.println("In which city do you want to participate?");
            String city = scanner.nextLine().trim();

            List<Auction> auctions = AuctionTDG.filterAuctions(date, time, type, city);

            if (auctions.isEmpty()) {
                System.out.println("No auctions found matching your criteria.");
            } else {
                System.out.println("\nAvailable auctions:");
                for (Auction auction : auctions) {
                    int auctionHouseId = auction.getAuctionHouseId();

                    AuctionHouse auctionHouse = AuctionHouseTDG.findById(auctionHouseId);

                    String auctionCity = auctionHouse != null ? auctionHouse.getCity() : "Unknown City";
                    String auctionAdress = auctionHouse != null ? auctionHouse.getAddress() : "Unknown Adress";

                    System.out.println("Auction ID: " + auction.getAuctionId() +
                            ", Type: " + auction.getType() +
                            ", Starts: " + auction.getBeginTime() +
                            ", Ends: " + auction.getEndTime() +
                            ", City: " + auctionCity +
                            ", Adress: " + auctionAdress);
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void requestService(Scanner scanner, int clientId) {
        try {
            System.out.println("What date do you want the service? (format: YYYY-MM-DD)");
            String strDate = scanner.nextLine().trim();
            LocalDate chosenDate = LocalDate.parse(strDate);

            System.out.println("What time do you want the service? (format: HH:MM)");
            String strTime = scanner.nextLine().trim();
            LocalTime chosenStartTime = LocalTime.parse(strTime);

            System.out.println("What time do you want the service to end? (format: HH:MM)");
            String endTime = scanner.nextLine().trim();
            LocalTime chosenEndTime = LocalTime.parse(endTime);

            if (ServiceRequestTDG.hasOverlappingRequest(clientId, chosenDate, chosenStartTime, chosenEndTime)) {
                System.out.println("You already have a request that overlaps with this time.");
                return;
            }

            List<String> allServices = ServiceRequestTDG.findAllServices();
            System.out.println("Available services:");
            for (String s : allServices) {
                System.out.println(s);
            }

            System.out.println("Choose the ID of the service you want to take:");
            int serviceTypeId = scanner.nextInt();
            scanner.nextLine();

            ServiceRequest request = ServiceRequestMapper.createRequest(clientId, serviceTypeId, chosenDate,
                    chosenStartTime, chosenEndTime);
            boolean success = ServiceRequestTDG.insertRequest(request);

            if (success) {
                System.out.println("Request created with ID: " + request.getRequestId());
            } else {
                System.out.println("Failed to create request.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addObjectInterest(Scanner scanner, int clientId) {
        while (true) {
            try {
                System.out.println("Enter object name (or press Enter to skip):");
                String name = scanner.nextLine();
                if (name.isEmpty())
                    name = null;

                System.out.println("Enter object type:");
                String type = scanner.nextLine();

                if (type.isEmpty()) {
                    System.out.println("Type is required. Please try again.");
                    continue;
                }

                ObjectOfInterest obj = new ObjectOfInterest(name, type, clientId);
                ObjectOfInterestMapper.save(obj);
                System.out.println("Object of interest saved.\n");

            } catch (Exception e) {
                System.out.println(" Error saving object of interest: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Continue? (yes/no)");
            if (!scanner.nextLine().equalsIgnoreCase("yes"))
                break;
        }
    }

    private static void viewObjectInterest(Scanner scanner, int clientId) {
        try {
            List<ObjectOfInterest> matched = ObjectOfInterestMapper.getInterestsWithMatchesByClient(clientId);

            if (matched.isEmpty()) {
                System.out.println("No matched objects of interest found for this client.");
            } else {
                System.out.println("Matched objects of interest:");

                for (ObjectOfInterest obj : matched) {

                    if (obj.getName() == null || obj.getName().isEmpty()) {

                        List<ArtObject> matchedObjects = ObjectMapper.getObjectsByType(obj.getType());

                        for (ArtObject o : matchedObjects) {

                            System.out.printf("Name: %s | Type: %s%n", o.getName(),
                                    o.getType());

                        }
                    } else {

                        System.out.printf("Name: %s | Type: %s%n", obj.getName(), obj.getType());
                    }

                }
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving matched interests: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deleteObjectInterest(Scanner scanner, int clientId) {
        try {
            viewObjectInterest(scanner, clientId);

            System.out.println("Which Object do you want to delete? Enter the ID:");

            int objectID = 0;
            if (scanner.hasNextInt()) {
                objectID = scanner.nextInt();
            } else {
                System.err.println("Invalid input. Please enter a valid object ID.");
                return;
            }

            ObjectOfInterestMapper.deleteObjectOfInterest(objectID);

            System.out.println("Object of interest with ID " + objectID + " has been deleted.");

        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a valid object ID.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

}
