import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n====== HOTEL RESERVATION SYSTEM ======");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    hotel.showAvailableRooms();
                    break;

                case 2:
                    sc.nextLine(); // clear buffer

                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Room Number: ");
                    int roomNumber = sc.nextInt();

                    System.out.print("Number of Days: ");
                    int days = sc.nextInt();

                    Room room = hotel.getRoomByNumber(roomNumber);

                    if (room != null) {
                        double totalAmount = room.getPrice() * days;

                        if (Payment.processPayment(totalAmount)) {
                            room.setAvailable(false);

                            Reservation reservation = new Reservation(
                                    name, roomNumber, days, totalAmount);

                            FileHandler.saveReservation(reservation.toString());

                            System.out.println("Room Booked Successfully!");
                        }
                    } else {
                        System.out.println("Room not available or invalid room number!");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

