public class Delivery {
    private String packageId;
    private String customerName;
    private String address;
    private String status;

    public Delivery(String packageId, String customerName, String address, String status) {
        this.packageId = packageId;
        this.customerName = customerName;
        this.address = address;
        this.status = status;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return packageId + "|" + customerName + "|" + address + "|" + status;
    }

    public static Delivery fromFileString(String line) {
        String[] parts = line.split("\\|");

        if (parts.length == 4) {
            return new Delivery(parts[0], parts[1], parts[2], parts[3]);
        }

        return null;
    }

    @Override
    public String toString() {
        return "=== Delivery ===\n" +
               "Package ID: " + packageId + "\n" +
               "Customer: " + customerName + "\n" +
               "Address: " + address + "\n" +
               "Status: " + status;
    }
}
