import java.util.Scanner;

public class Main {
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
            System.out.println("10. Show Delivered and Pending Deliveries");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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
                    deliveryManager.displayAllDeliveries();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 3:
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
                    System.out.println("Total deliveries: " + deliveryManager.getTotalDeliveries());
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;             
                case 7:
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
                    deliveryManager.sortByStatus();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 9:
                    deliveryManager.sortByPackageId();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 10:
                    deliveryManager.displayDeliveredAndPending();
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