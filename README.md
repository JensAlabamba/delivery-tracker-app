# Delivery Tracker App

A Java console application for managing package deliveries.  
This project allows users to add, search, update, remove, and filter delivery records, with data saved to a text file.

## Features
- Add a new delivery
- View all deliveries
- Search delivery by package ID
- Update delivery status
- Remove a delivery
- Show total number of deliveries
- Filter deliveries by status
- Sort deliveries by status (Pending first, Delivered last)
- Sort deliveries by package ID (alphabetical)
- Show only delivered and pending deliveries
- Save and load deliveries from a file

## Technologies Used
- Java
- List and ArrayList collections
- File I/O
- Scanner

## Project Structure
- `Delivery.java` - delivery object model
- `DeliveryManager.java` - business logic and file handling
- `Main.java` - user interface and menu system

## How to Run
1. Open the project in VS Code or another Java IDE
2. Compile the files inside `src`
3. Run `Main.java`

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
10. Show Delivered and Pending Deliveries - Display only active deliveries
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

## File Storage
The program stores delivery data in `deliveries.txt` so records can be loaded again the next time the program runs.

## Author
Wojciech Klonowski