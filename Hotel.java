import java.util.ArrayList;

public class Hotel {
    private ArrayList<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));
    }

    public void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room " + room.getRoomNumber() +
                        " | " + room.getCategory() +
                        " | INR " + room.getPrice());
            }
        }
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }
}

