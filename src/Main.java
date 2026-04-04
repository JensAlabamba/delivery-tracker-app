import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DeliveryManager deliveryManager = new DeliveryManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Delivery Management System");
            System.out.println("1. Add Delivery");
            System.out.println("2. View All Deliveries");
            System.out.println("3. Search Delivery by Package ID");
            System.out.println("4. Update Delivery Status");
            System.out.println("5. Remove Delivery");
            System.out.println("6. Exit");
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
                    deliveryManager.addDelivery(delivery);
                    break;
                case 2:
                    deliveryManager.displayAllDeliveries();
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
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter package ID to remove: ");
                    String removeId = scanner.nextLine();
                    boolean removed = deliveryManager.removeDeliveryById(removeId);
                    
                    if (removed) {
                        System.out.println("Delivery removed successfully.");
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    break;                
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

            scanner.close();
        }
    }