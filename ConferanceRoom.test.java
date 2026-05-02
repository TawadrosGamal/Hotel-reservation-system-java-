package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Stub for the abstract parent class to allow compilation and testing of the concrete class.
// This mirrors the expected behavior and fields inherited by ConferenceRoom.
abstract class Rooms {
    protected int roomnumber;
    protected boolean isreserved;
    protected int daysofreservation;

    public Rooms(int roomNumber) {
        this.roomnumber = roomNumber;
        this.isreserved = false;
        this.daysofreservation = 0;
    }

    public abstract double totalpriceofreservation();

    public void reserveRoom() {
        this.isreserved = true;
    }

    public void setDaysofreservation(int days) {
        if (days >= 0) {
            this.daysofreservation = days;
        }
    }
}


class ConferenceRoomTest {

    private ConferenceRoom conferenceRoom;

    @BeforeEach
    void setUp() {
        conferenceRoom = new ConferenceRoom(101, 50, 25.0);
    }

    @Test
    @DisplayName("Constructor should initialize fields correctly with valid arguments")
    void testConstructor_ValidArguments() {
        assertEquals(101, conferenceRoom.roomnumber);
        assertEquals(50, conferenceRoom.getNumberOfSeats());
        assertEquals(25.0, conferenceRoom.getSeatPrice());
        assertFalse(conferenceRoom.isreserved, "Room should not be reserved by default");
        assertEquals(0, conferenceRoom.daysofreservation, "Reservation days should be 0 by default");
    }

    @Test
    @DisplayName("Constructor should allow a seat price of zero")
    void testConstructor_ZeroSeatPrice() {
        ConferenceRoom room = new ConferenceRoom(102, 20, 0.0);
        assertEquals(20, room.getNumberOfSeats());
        assertEquals(0.0, room.getSeatPrice());
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException for zero seats")
    void testConstructor_ZeroSeats() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConferenceRoom(103, 0, 30.0);
        });
        assertEquals("Number of seats must be positive.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException for a negative number of seats")
    void testConstructor_NegativeSeats() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConferenceRoom(104, -10, 30.0);
        });
        assertEquals("Number of seats must be positive.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException for a negative seat price")
    void testConstructor_NegativeSeatPrice() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ConferenceRoom(105, 40, -5.0);
        });
        assertEquals("Seat price cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("totalpriceofreservation should calculate correctly for multiple days")
    void testTotalPriceOfReservation_MultipleDays() {
        conferenceRoom.setDaysofreservation(5);
        // 50 seats * 25.0 price/seat * 5 days = 6250.0
        assertEquals(6250.0, conferenceRoom.totalpriceofreservation());
    }

    @Test
    @DisplayName("totalpriceofreservation should be zero for zero days of reservation")
    void testTotalPriceOfReservation_ZeroDays() {
        conferenceRoom.setDaysofreservation(0);
        assertEquals(0.0, conferenceRoom.totalpriceofreservation());
    }

    @Test
    @DisplayName("totalpriceofreservation should be zero if the seat price is zero")
    void testTotalPriceOfReservation_ZeroSeatPrice() {
        ConferenceRoom zeroPriceRoom = new ConferenceRoom(106, 50, 0.0);
        zeroPriceRoom.setDaysofreservation(10);
        assertEquals(0.0, zeroPriceRoom.totalpriceofreservation());
    }

    @Test
    @DisplayName("totalpriceofreservation should be zero if days are not set (defaults to 0)")
    void testTotalPriceOfReservation_DefaultDays() {
        assertEquals(0.0, conferenceRoom.totalpriceofreservation());
    }

    @Test
    @DisplayName("toString should return correct format when not reserved")
    void testToString_NotReserved() {
        String expected = "the room number is 101, the number of seats is 50, the price per seat is 25.00, the room is not reserved";
        assertEquals(expected, conferenceRoom.toString());
    }

    @Test
    @DisplayName("toString should return correct format when reserved")
    void testToString_Reserved() {
        conferenceRoom.reserveRoom();
        conferenceRoom.setDaysofreservation(3);
        String expected = "the room number is 101, the number of seats is 50, the price per seat is 25.00, the days of reservation are 3, the room is reserved";
        assertEquals(expected, conferenceRoom.toString());
    }

    @Test
    @DisplayName("toString should format floating point prices to two decimal places")
    void testToString_FloatingPointFormat() {
        ConferenceRoom roomWithFloatPrice = new ConferenceRoom(107, 10, 15.755);
        String expected = "the room number is 107, the number of seats is 10, the price per seat is 15.76, the room is not reserved";
        assertEquals(expected, roomWithFloatPrice.toString());
    }

    @Test
    @DisplayName("getNumberOfSeats should return the correct number of seats")
    void testGetNumberOfSeats() {
        assertEquals(50, conferenceRoom.getNumberOfSeats());
    }

    @Test
    @DisplayName("getSeatPrice should return the correct seat price")
    void testGetSeatPrice() {
        assertEquals(25.0, conferenceRoom.getSeatPrice());
    }
}
