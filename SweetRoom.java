package hotel;

/**
 * Represents a suite room in the hotel, inheriting basic room properties.
 * It calculates the total reservation price based on the price per night.
 */
public class SuiteRoom extends Rooms {
    private double pricePerNight;

    /**
     * Constructs a new SuiteRoom.
     *
     * @param roomNumber    The unique number for the room.
     * @param pricePerNight The cost for a single night's stay.
     */
    public SuiteRoom(int roomNumber, double pricePerNight) {
        super(roomNumber);
        this.pricePerNight = pricePerNight;
    }

    /**
     * Calculates the total price for the reservation based on the number of days.
     * Returns 0 if the number of reservation days is not positive.
     *
     * @return The total price of the reservation.
     */
    @Override
    public double totalpriceofreservation() {
        if (daysofreservation <= 0) {
            return 0.0;
        }
        return pricePerNight * daysofreservation;
    }

    /**
     * Returns a string representation of the suite room, including its number,
     * reservation details, price, and reservation status.
     *
     * @return A formatted string describing the room.
     */
    @Override
    public String toString() {
        String status = isreserved ? "reserved" : "not reserved";
        return String.format(
                "the room number is %d, the days of reservation are %d, the price per night is %.2f, the room is %s",
                roomnumber,
                daysofreservation,
                pricePerNight,
                status
        );
    }
}