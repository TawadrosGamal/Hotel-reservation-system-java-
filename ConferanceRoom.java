package hotel;

/**
 * Represents a conference room in a hotel, inheriting basic room properties.
 * This class calculates reservation costs based on the number of seats and price per seat.
 */
public class ConferenceRoom extends Rooms {

    private final int numberOfSeats;
    private final double seatPrice;

    /**
     * Constructs a ConferenceRoom with a specific room number, number of seats, and price per seat.
     *
     * @param roomNumber The unique number identifying the room.
     * @param numberOfSeats The total number of seats available in the conference room. Must be positive.
     * @param seatPrice The price per seat for a reservation. Must be non-negative.
     * @throws IllegalArgumentException if numberOfSeats is not positive or seatPrice is negative.
     */
    public ConferenceRoom(int roomNumber, int numberOfSeats, double seatPrice) {
        super(roomNumber);
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be positive.");
        }
        if (seatPrice < 0.0) {
            throw new IllegalArgumentException("Seat price cannot be negative.");
        }
        this.numberOfSeats = numberOfSeats;
        this.seatPrice = seatPrice;
    }

    /**
     * Calculates the total price of the reservation for the conference room.
     * The price is determined by the number of seats, the price per seat, and the number of reservation days.
     *
     * @return The total reservation price.
     */
    @Override
    public double totalpriceofreservation() {
        return numberOfSeats * seatPrice * daysofreservation;
    }

    /**
     * Returns a string representation of the ConferenceRoom, including its details and reservation status.
     *
     * @return A formatted string describing the room.
     */
    @Override
    public String toString() {
        String baseInfo = String.format("the room number is %d, the number of seats is %d, the price per seat is %.2f",
                                        roomnumber, numberOfSeats, seatPrice);

        if (isreserved) {
            return String.format("%s, the days of reservation are %d, the room is reserved", baseInfo, daysofreservation);
        } else {
            return String.format("%s, the room is not reserved", baseInfo);
        }
    }

    /**
     * Gets the number of seats in the conference room.
     * @return the number of seats.
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Gets the price per seat for reservation.
     * @return the price per seat.
     */
    public double getSeatPrice() {
        return seatPrice;
    }
}
