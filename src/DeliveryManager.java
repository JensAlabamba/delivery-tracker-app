import java.util.ArrayList;
public class DeliveryManager {
    private ArrayList<Delivery> deliveries;

    public DeliveryManager() {
        deliveries = new ArrayList<>();
    }

    public void addDelivery(Delivery delivery) {
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
        Delivery delivery = findDeliveryById(packageId);
        if (delivery != null) {
            delivery.setStatus(newStatus);
            return true; // Update successful
        }
        return false; // Delivery not found
    }

    public boolean isValidStatus(String status) {
        return status.equals("Pending") || status.equals("Out for Delivery") ||
               status.equals("Delivered") || status.equals("Failed") || status.equals("Returned");
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
