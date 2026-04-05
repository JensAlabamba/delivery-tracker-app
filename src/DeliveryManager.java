import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;
import java.util.Scanner;

public class DeliveryManager {
    private ArrayList<Delivery> deliveries;

    /**
     * Initializes an empty in-memory delivery collection.
     */
    public DeliveryManager() {
        deliveries = new ArrayList<>();
    }

    /**
     * Adds a delivery if its package ID is not already present.
     * Returns true when added, false when duplicate.
     */
    public boolean addDelivery(Delivery delivery) {
        if (findDeliveryById(delivery.getPackageId()) != null) {
            return false;
        }
        deliveries.add(delivery);
        return true;
    }

    /**
     * Prints every delivery to the console.
     */
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

    /**
     * Finds one delivery by package ID (case-insensitive).
     * Returns null when not found.
     */
    public Delivery findDeliveryById(String packageId) {
        for (Delivery delivery : deliveries) {
            if (delivery.getPackageId().equalsIgnoreCase(packageId)) {
                return delivery;
            }
        }
        return null; // Not found
    }

    /**
     * Updates the status of a delivery by package ID.
     * Returns true when updated, false when delivery is missing.
     */
    public boolean updateDeliveryStatus(String packageId, String newStatus) {
        Delivery delivery = findDeliveryById(packageId);
        if (delivery != null) {
            delivery.setStatus(newStatus);
            return true; // Update successful
        }
        return false; // Delivery not found
    }

    /**
     * Returns the internal delivery list reference.
     */
    public ArrayList<Delivery> getAllDeliveries() {
        return deliveries;
    }

    /**
     * Validates that the status is one of the supported values.
     */
    public boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Out for Delivery") ||
               status.equalsIgnoreCase("Delivered") || status.equalsIgnoreCase("Failed") || status.equalsIgnoreCase("Returned");
    }

    /**
     * Removes a delivery by package ID.
     * Returns true when removed, false when not found.
     */
    public boolean removeDeliveryById(String packageId) {
        Delivery delivery = findDeliveryById(packageId);

        if (delivery != null) {
            deliveries.remove(delivery);
            return true;
        }

        return false;
    }

    /**
     * Returns the current number of tracked deliveries.
     */
    public int getTotalDeliveries() {
        return deliveries.size();
    }

    /**
     * Prints deliveries that match a given status.
     */
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

    /**
     * Sorts deliveries by business status order.
     */
    public void sortByStatus() {
        Collections.sort(deliveries, new Comparator<Delivery>() {
            @Override
            public int compare(Delivery d1, Delivery d2) {
                int statusOrder1 = getStatusOrder(d1.getStatus());
                int statusOrder2 = getStatusOrder(d2.getStatus());
                return Integer.compare(statusOrder1, statusOrder2);
            }
        });
        System.out.println("Deliveries sorted by status (Pending first, Delivered last).");
    }

    /**
     * Converts a status string to its sort priority.
     */
    private int getStatusOrder(String status) {
        switch (status.toLowerCase()) {
            case "pending": return 1;
            case "out for delivery": return 2;
            case "delivered": return 3;
            case "failed": return 4;
            case "returned": return 5;
            default: return 6;
        }
    }

    /**
     * Sorts deliveries alphabetically by package ID.
     */
    public void sortByPackageId() {
        Collections.sort(deliveries, new Comparator<Delivery>() {
            @Override
            public int compare(Delivery d1, Delivery d2) {
                return d1.getPackageId().compareToIgnoreCase(d2.getPackageId());
            }
        });
        System.out.println("Deliveries sorted by Package ID.");
    }

    /**
     * Prints active deliveries (Pending or Out for Delivery).
     */
    public void displayDeliveredAndPending() {
        boolean found = false;

        for (Delivery delivery : deliveries) {
            String status = delivery.getStatus().toLowerCase();
            if (status.equals("pending") || status.equals("out for delivery")) {
                System.out.println(delivery);
                System.out.println("-------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No active deliveries found.");
        }
    }

    /**
     * Returns active deliveries (Pending or Out for Delivery).
     */
    public ArrayList<Delivery> getActiveDeliveries() {
        ArrayList<Delivery> result = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            String status = delivery.getStatus().toLowerCase();
            if (status.equals("pending") || status.equals("out for delivery")) {
                result.add(delivery);
            }
        }
        return result;
    }

    /**
     * Returns a defensive copy of all deliveries.
     */
    public ArrayList<Delivery> getAllDeliveriesList() {
        return new ArrayList<>(deliveries); // Return a copy
    }

    /**
     * Returns deliveries that match a specific status.
     */
    public ArrayList<Delivery> getDeliveriesByStatus(String status) {
        ArrayList<Delivery> result = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            if (delivery.getStatus().equalsIgnoreCase(status)) {
                result.add(delivery);
            }
        }
        return result;
    }

    /**
     * Saves all deliveries to deliveries.txt.
     */
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

    /**
     * Loads deliveries from deliveries.txt when the file exists.
     */
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
