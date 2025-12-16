public class Reservation {
    private String customerName;
    private int roomNumber;
    private int days;
    private double totalAmount;

    public Reservation(String customerName, int roomNumber, int days, double totalAmount) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.days = days;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return customerName + "," + roomNumber + "," + days + "," + totalAmount;
    }
}
