import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeliveryTrackerGUI extends JFrame {
    private DeliveryManager deliveryManager;
    private JTextArea displayArea;
    private JButton addButton, viewButton, searchButton, updateButton, removeButton;
    private JButton totalButton, filterButton, sortStatusButton, sortIdButton, showActiveButton;

    public DeliveryTrackerGUI() {
        deliveryManager = new DeliveryManager();
        deliveryManager.loadFromFile();

        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Delivery Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 2, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addButton = new JButton("Add Delivery");
        viewButton = new JButton("View All Deliveries");
        searchButton = new JButton("Search by Package ID");
        updateButton = new JButton("Update Delivery Status");
        removeButton = new JButton("Remove Delivery");
        totalButton = new JButton("Show Total Deliveries");
        filterButton = new JButton("Filter by Status");
        sortStatusButton = new JButton("Sort by Status");
        sortIdButton = new JButton("Sort by Package ID");
        showActiveButton = new JButton("Show Active Deliveries");

        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(totalButton);
        buttonPanel.add(filterButton);
        buttonPanel.add(sortStatusButton);
        buttonPanel.add(sortIdButton);
        buttonPanel.add(showActiveButton);

        add(buttonPanel, BorderLayout.WEST);

        // Add action listeners
        addButton.addActionListener(new AddDeliveryListener());
        viewButton.addActionListener(new ViewDeliveriesListener());
        searchButton.addActionListener(new SearchDeliveryListener());
        updateButton.addActionListener(new UpdateDeliveryListener());
        removeButton.addActionListener(new RemoveDeliveryListener());
        totalButton.addActionListener(new TotalDeliveriesListener());
        filterButton.addActionListener(new FilterDeliveriesListener());
        sortStatusButton.addActionListener(new SortByStatusListener());
        sortIdButton.addActionListener(new SortByIdListener());
        showActiveButton.addActionListener(new ShowActiveDeliveriesListener());

        // Initial display
        displayWelcomeMessage();

        setVisible(true);
    }

    private void displayWelcomeMessage() {
        displayArea.setText("Welcome to Delivery Management System!\n\n");
        displayArea.append("Loaded " + deliveryManager.getTotalDeliveries() + " deliveries from file.\n\n");
        displayArea.append("Use the buttons on the left to manage deliveries.\n");
    }

    // Action Listeners
    private class AddDeliveryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String packageId = JOptionPane.showInputDialog("Enter Package ID:");
            if (packageId == null || packageId.trim().isEmpty()) return;

            String recipientName = JOptionPane.showInputDialog("Enter Recipient Name:");
            if (recipientName == null || recipientName.trim().isEmpty()) return;

            String address = JOptionPane.showInputDialog("Enter Address:");
            if (address == null || address.trim().isEmpty()) return;

            String[] statuses = {"Pending", "Out for Delivery", "Delivered", "Failed", "Returned"};
            String status = (String) JOptionPane.showInputDialog(null, "Select Status:",
                "Status Selection", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
            if (status == null) return;

            Delivery delivery = new Delivery(packageId, recipientName, address, status);
            boolean added = deliveryManager.addDelivery(delivery);

            if (added) {
                displayArea.setText("Delivery added successfully!\n\n");
                deliveryManager.saveToFile();
                displayArea.append("Delivery details:\n" + delivery + "\n");
            } else {
                displayArea.setText("Error: Package ID already exists!\n");
            }
        }
    }

    private class ViewDeliveriesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayArea.setText("All Deliveries:\n\n");
            deliveryManager.displayAllDeliveries();
            // Since displayAllDeliveries prints to console, we need to capture it
            // For now, let's implement a method to get the string representation
            displayArea.append(getAllDeliveriesText());
        }
    }

    private class SearchDeliveryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String packageId = JOptionPane.showInputDialog("Enter Package ID to search:");
            if (packageId == null || packageId.trim().isEmpty()) return;

            Delivery delivery = deliveryManager.findDeliveryById(packageId);
            if (delivery != null) {
                displayArea.setText("Delivery found:\n\n" + delivery + "\n");
            } else {
                displayArea.setText("Delivery not found for Package ID: " + packageId + "\n");
            }
        }
    }

    private class UpdateDeliveryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String packageId = JOptionPane.showInputDialog("Enter Package ID to update:");
            if (packageId == null || packageId.trim().isEmpty()) return;

            String[] statuses = {"Pending", "Out for Delivery", "Delivered", "Failed", "Returned"};
            String newStatus = (String) JOptionPane.showInputDialog(null, "Select New Status:",
                "Status Selection", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
            if (newStatus == null) return;

            boolean updated = deliveryManager.updateDeliveryStatus(packageId, newStatus);
            if (updated) {
                displayArea.setText("Delivery status updated successfully!\n\n");
                deliveryManager.saveToFile();
                Delivery delivery = deliveryManager.findDeliveryById(packageId);
                if (delivery != null) {
                    displayArea.append("Updated delivery:\n" + delivery + "\n");
                }
            } else {
                displayArea.setText("Delivery not found for Package ID: " + packageId + "\n");
            }
        }
    }

    private class RemoveDeliveryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String packageId = JOptionPane.showInputDialog("Enter Package ID to remove:");
            if (packageId == null || packageId.trim().isEmpty()) return;

            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to remove delivery with Package ID: " + packageId + "?",
                "Confirm Removal", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean removed = deliveryManager.removeDeliveryById(packageId);
                if (removed) {
                    displayArea.setText("Delivery removed successfully!\n\n");
                    deliveryManager.saveToFile();
                    displayArea.append("Total deliveries remaining: " + deliveryManager.getTotalDeliveries() + "\n");
                } else {
                    displayArea.setText("Delivery not found for Package ID: " + packageId + "\n");
                }
            }
        }
    }

    private class TotalDeliveriesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayArea.setText("Total Deliveries: " + deliveryManager.getTotalDeliveries() + "\n");
        }
    }

    private class FilterDeliveriesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] statuses = {"Pending", "Out for Delivery", "Delivered", "Failed", "Returned"};
            String status = (String) JOptionPane.showInputDialog(null, "Select Status to Filter:",
                "Status Selection", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
            if (status == null) return;

            displayArea.setText("Deliveries with status '" + status + "':\n\n");
            deliveryManager.displayDeliveriesByStatus(status);
            displayArea.append(getDeliveriesByStatusText(status));
        }
    }

    private class SortByStatusListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deliveryManager.sortByStatus();
            displayArea.setText("Deliveries sorted by status (Pending first, Delivered last):\n\n");
            displayArea.append(getAllDeliveriesText());
        }
    }

    private class SortByIdListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deliveryManager.sortByPackageId();
            displayArea.setText("Deliveries sorted by Package ID:\n\n");
            displayArea.append(getAllDeliveriesText());
        }
    }

    private class ShowActiveDeliveriesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayArea.setText("Active Deliveries (Pending and Out for Delivery):\n\n");
            deliveryManager.displayDeliveredAndPending();
            displayArea.append(getActiveDeliveriesText());
        }
    }

    // Helper methods to get text representations
    private String getAllDeliveriesText() {
        StringBuilder sb = new StringBuilder();
        for (Delivery delivery : deliveryManager.getAllDeliveries()) {
            sb.append(delivery).append("\n-------------------------\n");
        }
        return sb.toString();
    }

    private String getDeliveriesByStatusText(String status) {
        StringBuilder sb = new StringBuilder();
        for (Delivery delivery : deliveryManager.getAllDeliveries()) {
            if (delivery.getStatus().equalsIgnoreCase(status)) {
                sb.append(delivery).append("\n-------------------------\n");
            }
        }
        if (sb.length() == 0) {
            sb.append("No deliveries found with status: ").append(status).append("\n");
        }
        return sb.toString();
    }

    private String getActiveDeliveriesText() {
        StringBuilder sb = new StringBuilder();
        for (Delivery delivery : deliveryManager.getAllDeliveries()) {
            String status = delivery.getStatus().toLowerCase();
            if (status.equals("pending") || status.equals("out for delivery")) {
                sb.append(delivery).append("\n-------------------------\n");
            }
        }
        if (sb.length() == 0) {
            sb.append("No active deliveries found.\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeliveryTrackerGUI());
    }
}