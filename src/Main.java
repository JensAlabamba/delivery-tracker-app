import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    /**
     * Runs the console-based delivery management workflow.
     */
    public static void main(String[] args) {
        DeliveryManager deliveryManager = new DeliveryManager();
        deliveryManager.loadFromFile();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==============================");
            System.out.println(" Delivery Management System ");
            System.out.println("==============================");
            System.out.println("1. Add Delivery");
            System.out.println("2. View All Deliveries");
            System.out.println("3. Search Delivery by Package ID");
            System.out.println("4. Update Delivery Status");
            System.out.println("5. Remove Delivery");
            System.out.println("6. Show Total Deliveries");
            System.out.println("7. Filter Deliveries by Status");
            System.out.println("8. Sort Deliveries by Status");
            System.out.println("9. Sort Deliveries by Package ID");
            System.out.println("10. Show Active Deliveries");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            if (choice < 1 || choice > 11) {
                System.out.println("Invalid option. Please choose a number between 1 and 11.");
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    // Add a new delivery after validating status input.
                    System.out.print("Enter Package ID: ");
                    String packageId = scanner.nextLine();
                    System.out.print("Enter Recipient Name: ");
                    String recipientName = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    String status;

                    do {
                        System.out.print("Enter status (Pending, Out for Delivery, Delivered, Failed, Returned): ");
                        status = scanner.nextLine();

                        if (!deliveryManager.isValidStatus(status)) {
                            System.out.println("Invalid status. Try again.");
                        }

                    } while (!deliveryManager.isValidStatus(status));
                    Delivery delivery = new Delivery(packageId, recipientName, address, status);
                    boolean added = deliveryManager.addDelivery(delivery);

                    if (added) {
                        System.out.println("Delivery added successfully.");
                        deliveryManager.saveToFile();
                    } else {
                        System.out.println("Error: Package ID already exists.");
                    }
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 2:
                    // Display every tracked delivery.
                    deliveryManager.displayAllDeliveries();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 3:
                    // Search for one delivery by package ID.
                    System.out.print("Enter Package ID to find: ");
                    String searchId = scanner.nextLine();
                    Delivery foundDelivery = deliveryManager.findDeliveryById(searchId);
                    if (foundDelivery != null) {
                        System.out.println(foundDelivery);
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 4:
                    // Update status for an existing delivery.
                    System.out.print("Enter Package ID to update: ");
                    String updateId = scanner.nextLine();
                    String newStatus;
                    do {
                        System.out.print("Enter new status (Pending, Out for Delivery, Delivered, Failed, Returned): ");
                        newStatus = scanner.nextLine();

                        if (!deliveryManager.isValidStatus(newStatus)) {
                            System.out.println("Invalid status. Try again.");
                        }
                    } while (!deliveryManager.isValidStatus(newStatus));
                    boolean updated = deliveryManager.updateDeliveryStatus(updateId, newStatus);
                    if (updated) {
                        System.out.println("Delivery status updated successfully.");
                        deliveryManager.saveToFile();
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 5:
                    // Remove one delivery by ID.
                    System.out.print("Enter package ID to remove: ");
                    String removeId = scanner.nextLine();
                    boolean removed = deliveryManager.removeDeliveryById(removeId);
                    
                    if (removed) {
                        System.out.println("Delivery removed successfully.");
                        deliveryManager.saveToFile();
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;   
                case 6:
                    // Print the total number of deliveries.
                    System.out.println("Total deliveries: " + deliveryManager.getTotalDeliveries());
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;             
                case 7:
                    // Filter and show deliveries with a selected status.
                    String filterStatus;
                    do {
                        System.out.print("Enter status to filter by (Pending, Out for Delivery, Delivered, Failed, Returned): ");
                        filterStatus = scanner.nextLine();

                        if (!deliveryManager.isValidStatus(filterStatus)) {
                            System.out.println("Invalid status. Try again.");
                        }
                    } while (!deliveryManager.isValidStatus(filterStatus));

                    deliveryManager.displayDeliveriesByStatus(filterStatus);
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 8:
                    // Sort by status order, then display results.
                    deliveryManager.sortByStatus();
                    deliveryManager.displayAllDeliveries();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 9:
                    // Sort alphabetically by package ID, then display results.
                    deliveryManager.sortByPackageId();
                    deliveryManager.displayAllDeliveries();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 10:
                    // Show only active deliveries (Pending / Out for Delivery).
                    ArrayList<Delivery> activeDeliveries = deliveryManager.getActiveDeliveries();
                    if (activeDeliveries.isEmpty()) {
                        System.out.println("No active deliveries found.");
                    } else {
                        System.out.println("Active Deliveries (Pending and Out for Delivery):");
                        System.out.println("==================================================");
                        for (Delivery d : activeDeliveries) {
                            System.out.println(d);
                            System.out.println("-------------------------");
                        }
                    }
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
            }
        }

        scanner.close();
    }
}