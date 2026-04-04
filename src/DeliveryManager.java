import java.util.ArrayList;
import java.util.List;
public class DeliveryManager {
    private List<Delivery> deliveries;

    public DeliveryManager() {
        deliveries = new ArrayList<>();
    }

    public void addDelivery(Delivery delivery) {
        if (findDeliveryById(delivery.getPackageId()) != null) {
            System.out.println("Error: Package ID already exists.");
            return;
        }
        deliveries.add(delivery);
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
        if (!isValidStatus(newStatus)) {
            System.out.println("Invalid status.");
            return false;
        }
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
}
