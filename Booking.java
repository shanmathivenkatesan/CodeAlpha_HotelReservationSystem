public class Booking {
    String customerName;
    int roomNumber;
    String category;
    double price;

    public Booking(String customerName, int roomNumber, String category, double price) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return customerName + "," + roomNumber + "," + category + "," + price;
    }
}


