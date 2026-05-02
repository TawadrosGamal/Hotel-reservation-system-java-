package hotel;

/**
 * Represents a conference room in a hotel, extending the base Rooms class.
 * It includes specific properties like the number of seats and the price per seat.
 */
public class ConferenceRoom extends Rooms {
    private int numberOfSeats;
    private double seatPrice;

    /**
     * Constructs a new ConferenceRoom.
     *
     * @param roomNumber    The unique number identifying the room.
     * @param numberOfSeats The total number of seats available in the conference room.
     * @param seatPrice     The price per seat for a reservation.
     */
    public ConferenceRoom(int roomNumber, int numberOfSeats, double seatPrice) {
        super(roomNumber);
        this.numberOfSeats = numberOfSeats;
        this.seatPrice = seatPrice;
    }

    /**
     * Calculates the total price for a reservation based on the number of seats,
     * the price per seat, and the number of days for the reservation.
     *
     * @return The total price of the reservation as a double.
     */
    @Override
    public double totalPriceOfReservation() {
        return numberOfSeats * seatPrice * daysofreservation;
    }

    /**
     * Provides a string representation of the ConferenceRoom object, including its properties
     * and reservation status.
     *
     * @return A formatted string describing the conference room.
     */
    @Override
    public String toString() {
        String baseInfo = "the room number is " + roomnumber
                + " the days of reservation are " + daysofreservation
                + " the number of seats is " + numberOfSeats
                + " the price per seat is " + seatPrice;

        if (isreserved) {
            return baseInfo + " the room is reserved";
        } else {
            return baseInfo + " the room is not reserved";
        }
    }

    /**
     * Gets the number of seats in the conference room.
     *
     * @return The number of seats.
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Gets the price per seat.
     *
     * @return The price per seat.
     */
    public double getSeatPrice() {
        return seatPrice;
    }
}
