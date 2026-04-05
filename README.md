# Delivery Tracker App

A comprehensive Java application for managing package deliveries with both console and graphical user interfaces. Track delivery status, search packages, and maintain organized delivery records with automatic data persistence.

## 🚀 Features

### Core Functionality
- ✅ **Add Deliveries** - Create new delivery records with package ID, recipient, address, and status
- ✅ **View All Deliveries** - Display complete list of all tracked deliveries
- ✅ **Search by Package ID** - Quickly find specific deliveries
- ✅ **Update Delivery Status** - Change status through validation (Pending, Out for Delivery, Delivered, Failed, Returned)
- ✅ **Remove Deliveries** - Delete delivery records with confirmation
- ✅ **Duplicate Prevention** - Automatic checking to prevent duplicate package IDs

### Advanced Features
- ✅ **Filter by Status** - Show deliveries with specific status
- ✅ **Sort by Status** - Priority-based sorting (Pending → Out for Delivery → Delivered → Failed → Returned)
- ✅ **Sort by Package ID** - Alphabetical sorting of package IDs
- ✅ **Active Deliveries View** - Show only pending and out for delivery packages
- ✅ **Total Count** - Display total number of deliveries
- ✅ **Data Persistence** - Automatic save/load from file

### User Interfaces
- ✅ **Console Interface** - Traditional command-line interface with menu system
- ✅ **GUI Interface** - Modern Swing-based graphical interface with buttons and dialogs

## 🖥️ Screenshots

### Console Interface
```
==============================
 Delivery Management System
==============================
1. Add Delivery
2. View All Deliveries
3. Search Delivery by Package ID
4. Update Delivery Status
5. Remove Delivery
6. Show Total Deliveries
7. Filter Deliveries by Status
8. Sort Deliveries by Status
9. Sort Deliveries by Package ID
10. Show Delivered and Pending Deliveries
11. Exit
Choose an option:
```

### GUI Interface
- **Main Window**: Clean layout with action buttons on the left, results display on the right
- **Input Dialogs**: User-friendly popups for data entry with dropdown status selection
- **Confirmation Dialogs**: Safety prompts for destructive operations
- **Real-time Display**: Immediate feedback for all operations

## 🛠️ Technologies Used
- **Java** - Core programming language
- **Swing** - GUI framework for graphical interface
- **Collections Framework** - List and ArrayList for data management
- **File I/O** - Data persistence with robust pipe-separated format
- **Exception Handling** - Safe input validation and error recovery

## 📁 Project Structure
```
DeliveryTracker/
├── src/
│   ├── Delivery.java              # Data model class
│   ├── DeliveryManager.java       # Business logic and data management
│   ├── Main.java                  # Console interface
│   ├── DeliveryTrackerGUI.java    # Swing GUI interface
│   └── deliveries.txt             # Data persistence file
├── README.md                      # This documentation
└── .git/                          # Version control
```

## 🚀 How to Run

### Prerequisites
- Java 8 or higher installed
- Command line or IDE with Java support

### Console Version
```bash
cd src
javac *.java
java Main
```

### GUI Version (Recommended)
```bash
cd src
javac *.java
java DeliveryTrackerGUI
```

## 📋 Usage Examples

### Adding a Delivery
1. Click "Add Delivery" (GUI) or choose option 1 (Console)
2. Enter Package ID: `PKG013`
3. Enter Recipient Name: `Sarah Johnson`
4. Enter Address: `555 Park Avenue, Suite 100`
5. Select Status: `Pending`

### Searching Deliveries
- Enter Package ID to find specific delivery details
- Results show complete delivery information

### Status Management
- Update delivery status through validated dropdown/console input
- Automatic data saving after each operation

## 📊 Sample Data

The application includes 12 sample deliveries for testing:
- **PKG001-PKG012** with various statuses and realistic addresses
- Mix of all status types for comprehensive testing
- Safe, fictional data for development and demonstration

## 🔒 Data Safety

- **Input Validation**: All inputs validated for correctness
- **Duplicate Prevention**: Package IDs must be unique
- **Safe File Format**: Pipe-separated values prevent parsing errors
- **Confirmation Dialogs**: Destructive operations require confirmation
- **Automatic Saving**: Changes saved immediately to prevent data loss

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes with proper testing
4. Submit a pull request

## 📝 License

This project is open source and available under the MIT License.

## 👨‍💻 Author

Wojciech Klonowski

---

**Enjoy managing your deliveries efficiently! 📦✨**
- **Status selection dropdowns** - Easy status selection from predefined options

## Usage
The program starts with a console menu offering the following options:
1. Add Delivery - Create a new delivery record
2. View All Deliveries - Display all delivery records
3. Search Delivery by Package ID - Find a specific delivery
4. Update Delivery Status - Change the status of an existing delivery
5. Remove Delivery - Delete a delivery record
6. Show Total Deliveries - Display the count of all deliveries
7. Filter Deliveries by Status - Show deliveries with a specific status
8. Sort Deliveries by Status - Order deliveries by status priority
9. Sort Deliveries by Package ID - Order deliveries alphabetically
10. Show Active Deliveries - Display only active deliveries
11. Exit - Close the program

## Example Status Values
- Pending
- Out for Delivery
- Delivered
- Failed
- Returned

## Sorting Behavior
- **Sort by Status**: Orders deliveries by status priority (Pending → Out for Delivery → Delivered → Failed → Returned)
- **Sort by Package ID**: Alphabetical ordering of package IDs
- **Active Deliveries**: Shows deliveries with status "Pending" or "Out for Delivery"

## File Storage
The program stores delivery data in `deliveries.txt` so records can be loaded again the next time the program runs.

## Author
Wojciech Klonowski