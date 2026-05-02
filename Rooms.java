package com.example.hotel.room;

/**
 * Represents an abstract hotel room with basic reservation functionality.
 * Subclasses are responsible for implementing the specific pricing logic.
 */
public abstract class Room {

    private static final String MSG_ROOM_RESERVED = "The room is now reserved.";

    private final int roomNumber;
    private int daysOfReservation;
    private boolean reserved;

    /**
     * Constructs a new Room with a specified room number.
     * The room is initially not reserved.
     *
     * @param roomNumber The unique number identifying this room.
     */
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.reserved = false;
        this.daysOfReservation = 0;
    }

    /**
     * Reserves the room for a specified number of days.
     * If the room is already reserved or the number of days is not positive,
     * the reservation will not be made.
     *
     * @param numberOfDays The number of days to reserve the room for.
     */
    public void reserve(int numberOfDays) {
        if (this.reserved) {
            System.err.println("Error: Room " + this.roomNumber + " is already reserved.");
            return;
        }
        if (numberOfDays > 0) {
            this.reserved = true;
            this.daysOfReservation = numberOfDays;
            System.out.println(MSG_ROOM_RESERVED);
        } else {
            System.err.println("Error: Number of reservation days must be positive.");
        }
    }
    
    /**
     * Cancels the current reservation for the room, resetting its state.
     */
    public void cancelReservation() {
        this.reserved = false;
        this.daysOfReservation = 0;
    }

    /**
     * Checks if the room is currently reserved.
     *
     * @return true if the room is reserved, false otherwise.
     */
    public boolean isReserved() {
        return this.reserved;
    }

    /**
     * Returns the room number.
     *
     * @return The integer room number.
     */
    public int getRoomNumber() {
        return this.roomNumber;
    }

    /**
     * Returns the number of days for which the room is reserved.
     *
     * @return The number of reservation days, or 0 if not reserved.
     */
    public int getDaysOfReservation() {
        return this.daysOfReservation;
    }

    /**
     * Calculates the total price for the current reservation.
     * This abstract method must be implemented by concrete subclasses.
     *
     * @return The total reservation price as a double.
     */
    public abstract double getTotalPriceOfReservation();
}