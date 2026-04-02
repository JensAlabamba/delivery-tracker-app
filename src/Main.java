import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DeliveryManager deliveryManager = new DeliveryManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Delivery Management System");
            System.out.println("1. Add Delivery");
            System.out.println("2. Display All Deliveries");
            System.out.println("3. Find Delivery by Package ID");
            System.out.println("4. Update Delivery Status");
            System.out.println("5. Exit");
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
                    System.out.print("Enter Status: ");
                    String status = scanner.nextLine();
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
                    System.out.print("Enter new status: ");
                    String newStatus = scanner.nextLine();
                    boolean updated = deliveryManager.updateDeliveryStatus(updateId, newStatus);
                    if (updated) {
                        System.out.println("Delivery status updated successfully.");
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

            scanner.close();
        }
    }