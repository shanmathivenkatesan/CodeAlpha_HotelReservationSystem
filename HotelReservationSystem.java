import java.io.*;
import java.util.*;

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "bookings.txt";

    public static void main(String[] args) {
        initializeRooms();
        int choice=0;

        do {
            System.out.println("\n==== HOTEL RESERVATION SYSTEM ====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            if (!sc.hasNextInt()) {
                 System.out.println("Number mattum type pannunga!");
                 sc.next();   
                 continue;    
            }
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> System.out.println("Thank you for using the system!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 2000));
        rooms.add(new Room(102, "Standard", 2000));
        rooms.add(new Room(201, "Deluxe", 3500));
        rooms.add(new Room(202, "Deluxe", 3500));
        rooms.add(new Room(301, "Suite", 5000));
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            System.out.printf("Room %d | %s | INR %.2f | %s%n",
        r.roomNumber,
        r.category,
        r.price,
        r.isBooked ? "Booked" : "Available");

            
        }
    }

    static void bookRoom() {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();

        viewRooms();
        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && !r.isBooked) {
                r.isBooked = true;

                System.out.printf("Processing payment of INR %.2f...%n", r.price);
                System.out.println("Payment Successful!");


                Booking booking = new Booking(name, r.roomNumber, r.category, r.price);
                saveBooking(booking);

                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void cancelBooking() {
        System.out.print("Enter room number to cancel booking: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && r.isBooked) {
                r.isBooked = false;
                removeBooking(roomNo);
                System.out.println("Booking cancelled successfully!");
                return;
            }
        }
        System.out.println("No booking found for this room!");
    }

    static void saveBooking(Booking booking) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(booking.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving booking!");
        }
    }

    static void removeBooking(int roomNo) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            List<String> lines = new ArrayList<>();
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.contains("," + roomNo + ",")) {
                    lines.add(line);
                }
            }
            fileScanner.close();

            FileWriter fw = new FileWriter(FILE_NAME);
            for (String line : lines) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error updating booking file!");
        }
    }
}
