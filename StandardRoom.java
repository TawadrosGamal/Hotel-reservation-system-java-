package com.example.hotel;

/**
 * Represents a standard hotel room with a specific number of beds.
 * This class extends the abstract Room class.
 */
public class StandardRoom extends Room {

    private final int numberOfBeds;
    private final double bedPrice;

    /**
     * Constructs a StandardRoom with specified properties.
     *
     * @param roomNumber   The unique number for the room.
     * @param numberOfBeds The number of beds in the room (must be positive).
     * @param bedPrice     The price per bed (cannot be negative).
     * @throws IllegalArgumentException if numberOfBeds is not positive or bedPrice is negative.
     */
    public StandardRoom(int roomNumber, int numberOfBeds, double bedPrice) {
        super(roomNumber);
        if (numberOfBeds <= 0) {
            throw new IllegalArgumentException("Number of beds must be positive.");
        }
        if (bedPrice < 0) {
            throw new IllegalArgumentException("Bed price cannot be negative.");
        }
        this.bedPrice = bedPrice;
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * Calculates the total price for a reservation based on the number of beds,
     * bed price, and duration of the stay.
     *
     * @return The total reservation price. Returns 0.0 if not reserved.
     */
    @Override
    public double totalPriceOfReservation() {
        if (!isReserved) {
            return 0.0;
        }
        return numberOfBeds * bedPrice * daysOfReservation;
    }

    /**
     * Returns a string representation of the StandardRoom object, including its properties
     * and reservation status.
     *
     * @return A formatted string describing the room.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room Number: ").append(roomNumber);
        sb.append(", Days of Reservation: ").append(daysOfReservation);
        sb.append(", Number of Beds: ").append(numberOfBeds);
        sb.append(", Price per Bed: ").append(String.format("%.2f", bedPrice));
        sb.append(", Status: ").append(isReserved ? "Reserved" : "Not Reserved");
        return sb.toString();
    }
}

/**
 * Abstract base class for different types of hotel rooms.
 * (Provided for context to make StandardRoom compilable)
 */
abstract class Room {
    protected int roomNumber;
    protected int daysOfReservation;
    protected boolean isReserved;

    public Room(int roomNumber) {
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be positive.");
        }
        this.roomNumber = roomNumber;
        this.isReserved = false;
        this.daysOfReservation = 0;
    }

    public abstract double totalPriceOfReservation();

    // Example methods to manage reservation state
    public void reserve(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days of reservation must be positive.");
        }
        this.isReserved = true;
        this.daysOfReservation = days;
    }

    public void cancelReservation() {
        this.isReserved = false;
        this.daysOfReservation = 0;
    }
}