public class Delivery {
    private String packageId;
    private String customerName;
    private String address;
    private String status;

    /**
     * Creates a delivery record with the core package details.
     */
    public Delivery(String packageId, String customerName, String address, String status) {
        this.packageId = packageId;
        this.customerName = customerName;
        this.address = address;
        this.status = status;
    }

    /**
     * Returns the unique package identifier.
     */
    public String getPackageId() {
        return packageId;
    }

    /**
     * Returns the recipient/customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Returns the delivery address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the current delivery status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the current delivery status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Converts the delivery to a pipe-separated format for file storage.
     */
    public String toFileString() {
        return packageId + "|" + customerName + "|" + address + "|" + status;
    }

    /**
     * Parses a delivery from a pipe-separated line.
     * Returns null if the line format is invalid.
     */
    public static Delivery fromFileString(String line) {
        String[] parts = line.split("\\|");

        if (parts.length == 4) {
            return new Delivery(parts[0], parts[1], parts[2], parts[3]);
        }

        return null;
    }

    /**
     * Returns a human-readable multi-line summary of the delivery.
     */
    @Override
    public String toString() {
        return "=== Delivery ===\n" +
               "Package ID: " + packageId + "\n" +
               "Customer: " + customerName + "\n" +
               "Address: " + address + "\n" +
               "Status: " + status;
    }
}
