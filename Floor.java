package hotel;

import java.util.Arrays;

// Assuming the Rooms class exists and has at least these methods:
// public boolean check(); // Returns true if occupied, false if free
// public double totalpriceofreservation();
// public int getRoomnumber();

/**
 * Represents a single floor in a hotel, managing a collection of rooms.
 * This class has been refactored for improved safety, encapsulation, and clarity.
 * @author tawadros
 */
public class Floor {
    // Fields are now private to enforce encapsulation.
    private final int floornumber;
    private final Rooms[] rooms; // Renamed from 'a' for clarity.

    /**
     * Constructs a Floor with a given number but no rooms.
     * The internal rooms array is initialized to an empty, non-null array.
     *
     * @param floornumber The number of the floor.
     */
    public Floor(int floornumber) {
        this.floornumber = floornumber;
        this.rooms = new Rooms[0];
    }

    /**
     * Constructs a Floor with a specific number and an array of rooms.
     * A defensive copy of the rooms array is made to prevent external modification.
     * The redundant 'numberofrooms' parameter has been removed.
     *
     * @param floornumber The number of the floor.
     * @param rooms An array of Rooms objects for this floor. If null, it will be treated as an empty array.
     */
    public Floor(int floornumber, Rooms[] rooms) {
        this.floornumber = floornumber;
        // Defensive copy to prevent aliasing and external modification of internal state.
        if (rooms == null) {
            this.rooms = new Rooms[0];
        } else {
            this.rooms = Arrays.copyOf(rooms, rooms.length);
        }
    }
    
    /**
     * Displays the room numbers of all free rooms on the floor.
     * This method is safe from NullPointerException as 'rooms' is always initialized.
     */
    public void displayfree() {
        System.out.println("Free rooms on floor " + this.floornumber + ":");
        boolean anyFree = false;
        for (Rooms room : this.rooms) {
            // Assumes room.check() returns false if the room is free.
            if (room != null && !room.check()) {
                // Accessing room number via a getter to respect encapsulation of the Rooms class.
                System.out.println("Room number: " + room.getRoomnumber());
                anyFree = true;
            }
        }
        if (!anyFree) {
            System.out.println("No free rooms on this floor.");
        }
    }
    
    /**
     * Calculates and prints the total profit from all reserved rooms on the floor.
     * This method is safe from NullPointerException.
     */
    public void totalprofit() {
        double sum = 0.0;
        // Using an enhanced for-loop for better readability.
        for (Rooms room : this.rooms) {
            if (room != null) {
                sum += room.totalpriceofreservation();
            }
        }
        System.out.println("The total profit of floor " + this.floornumber + " is " + sum);
    }
    
    // --- Getters ---

    /**
     * @return The floor number.
     */
    public int getFloornumber() {
        return floornumber;
    }

    /**
     * Returns a copy of the rooms array to maintain encapsulation.
     * The caller cannot modify the internal state of the Floor object.
     *
     * @return A copy of the rooms on this floor.
     */
    public Rooms[] getRooms() {
        // Return a defensive copy to protect the internal array.
        return Arrays.copyOf(rooms, rooms.length);
    }

    /**
     * @return The number of rooms on this floor.
     */
    public int getNumberOfRooms() {
        return this.rooms.length;
    }
}