import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class DeliveryManager {
    private ArrayList<Delivery> deliveries;

    public DeliveryManager() {
        deliveries = new ArrayList<>();
    }

    public boolean addDelivery(Delivery delivery) {
        if (findDeliveryById(delivery.getPackageId()) != null) {
            return false;
        }
        deliveries.add(delivery);
        return true;
    }

    public void displayAllDeliveries() {
        if (deliveries.isEmpty()) {
            System.out.println("No deliveries to display.");
            return;
        }
        for (Delivery delivery : deliveries) {
            System.out.println(delivery);
            System.out.println("-------------------------");
        }
    }

    public Delivery findDeliveryById(String packageId) {
        for (Delivery delivery : deliveries) {
            if (delivery.getPackageId().equalsIgnoreCase(packageId)) {
                return delivery;
            }
        }
        return null; // Not found
    }

    public boolean updateDeliveryStatus(String packageId, String newStatus) {
        Delivery delivery = findDeliveryById(packageId);
        if (delivery != null) {
            delivery.setStatus(newStatus);
            return true; // Update successful
        }
        return false; // Delivery not found
    }

    public boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Out for Delivery") ||
               status.equalsIgnoreCase("Delivered") || status.equalsIgnoreCase("Failed") || status.equalsIgnoreCase("Returned");
    }

    public boolean removeDeliveryById(String packageId) {
        Delivery delivery = findDeliveryById(packageId);

        if (delivery != null) {
            deliveries.remove(delivery);
            return true;
        }

        return false;
    }

    public int getTotalDeliveries() {
        return deliveries.size();
    }

    public void displayDeliveriesByStatus(String status) {
        boolean found = false;

        for (Delivery delivery : deliveries) {
            if (delivery.getStatus().equalsIgnoreCase(status)) {
                System.out.println(delivery);
                System.out.println("------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No deliveries found with status: " + status);
        }
    }

    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("deliveries.txt"));

            for (Delivery delivery : deliveries) {
                writer.println(delivery.toFileString());
            }

            writer.close();
            System.out.println("Deliveries saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    public void loadFromFile() {
    try {
        File file = new File("deliveries.txt");

        if (!file.exists()) {
            return; // No file yet, nothing to load
        }

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Delivery delivery = Delivery.fromFileString(line);

            if (delivery != null) {
                deliveries.add(delivery);
            }
        }

        fileScanner.close();
        System.out.println("Deliveries loaded from file.");
    } catch (Exception e) {
        System.out.println("Error loading from file.");
    }
    }
}
