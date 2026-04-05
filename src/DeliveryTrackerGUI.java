import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        // Initialize buttons
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

        // Create display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create button panel with sections
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 📦 Delivery Actions Section
        JPanel deliveryActionsPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        deliveryActionsPanel.setBorder(BorderFactory.createTitledBorder("📦 Delivery Actions"));
        deliveryActionsPanel.add(addButton);
        deliveryActionsPanel.add(updateButton);
        deliveryActionsPanel.add(removeButton);
        buttonPanel.add(deliveryActionsPanel);

        // Add spacing
        buttonPanel.add(Box.createVerticalStrut(10));

        // 🔍 Search & Filter Section
        JPanel searchFilterPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        searchFilterPanel.setBorder(BorderFactory.createTitledBorder("🔍 Search & Filter"));
        searchFilterPanel.add(searchButton);
        searchFilterPanel.add(filterButton);
        searchFilterPanel.add(showActiveButton);
        buttonPanel.add(searchFilterPanel);

        // Add spacing
        buttonPanel.add(Box.createVerticalStrut(10));

        // 📊 Sorting & Stats Section
        JPanel sortingStatsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        sortingStatsPanel.setBorder(BorderFactory.createTitledBorder("📊 Sorting & Stats"));
        sortingStatsPanel.add(viewButton);
        sortingStatsPanel.add(totalButton);
        sortingStatsPanel.add(sortStatusButton);
        sortingStatsPanel.add(sortIdButton);
        buttonPanel.add(sortingStatsPanel);

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
            ArrayList<Delivery> allDeliveries = deliveryManager.getAllDeliveriesList();
            if (allDeliveries.isEmpty()) {
                displayArea.append("No deliveries to display.\n");
            } else {
                for (Delivery delivery : allDeliveries) {
                    displayArea.append(delivery + "\n-------------------------\n");
                }
            }
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
            ArrayList<Delivery> filteredDeliveries = deliveryManager.getDeliveriesByStatus(status);
            if (filteredDeliveries.isEmpty()) {
                displayArea.append("No deliveries found with status: " + status + "\n");
            } else {
                for (Delivery delivery : filteredDeliveries) {
                    displayArea.append(delivery + "\n-------------------------\n");
                }
            }
        }
    }

    private class SortByStatusListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deliveryManager.sortByStatus();
            displayArea.setText("Deliveries sorted by status (Pending first, Delivered last):\n\n");
            ArrayList<Delivery> sortedDeliveries = deliveryManager.getAllDeliveriesList();
            for (Delivery delivery : sortedDeliveries) {
                displayArea.append(delivery + "\n-------------------------\n");
            }
        }
    }

    private class SortByIdListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deliveryManager.sortByPackageId();
            displayArea.setText("Deliveries sorted by Package ID:\n\n");
            ArrayList<Delivery> sortedDeliveries = deliveryManager.getAllDeliveriesList();
            for (Delivery delivery : sortedDeliveries) {
                displayArea.append(delivery + "\n-------------------------\n");
            }
        }
    }

    private class ShowActiveDeliveriesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayArea.setText("Active Deliveries (Pending and Out for Delivery):\n\n");
            ArrayList<Delivery> activeDeliveries = deliveryManager.getActiveDeliveries();
            if (activeDeliveries.isEmpty()) {
                displayArea.append("No active deliveries found.\n");
            } else {
                for (Delivery delivery : activeDeliveries) {
                    displayArea.append(delivery + "\n-------------------------\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeliveryTrackerGUI());
    }
}