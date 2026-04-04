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

    @Override
    public String toString() {
        return "Package ID: " + packageId +
                "\nCustomer Name: " + customerName +
                "\nAddress: " + address +
                "\nStatus: " + status;
    }
}
