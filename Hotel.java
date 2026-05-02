package hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// --- DOMAIN MODEL CLASSES ---

abstract class Room {
    protected final int roomNumber;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Room #" + roomNumber;
    }
}

class StandardRoom extends Room {
    private final int numberOfBeds;
    private final double bedPrice;

    public StandardRoom(int roomNumber, int numberOfBeds, double bedPrice) {
        super(roomNumber);
        this.numberOfBeds = numberOfBeds;
        this.bedPrice = bedPrice;
    }

    @Override
    public String toString() {
        return String.format("Standard Room #%d (%d beds @ $%.2f each)", roomNumber, numberOfBeds, bedPrice);
    }
}

class ConferenceRoom extends Room {
    private final int numberOfSeats;
    private final double seatPrice;

    public ConferenceRoom(int roomNumber, int numberOfSeats, double seatPrice) {
        super(roomNumber);
        this.numberOfSeats = numberOfSeats;
        this.seatPrice = seatPrice;
    }

    @Override
    public String toString() {
        return String.format("Conference Room #%d (%d seats @ $%.2f each)", roomNumber, numberOfSeats, seatPrice);
    }
}

class SuiteRoom extends Room {
    private final double pricePerNight;

    public SuiteRoom(int roomNumber, double pricePerNight) {
        super(roomNumber);
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return String.format("Suite Room #%d ($%.2f per night)", roomNumber, pricePerNight);
    }
}

class Floor {
    private final int floorNumber;
    private final List<Room> rooms;

    public Floor(int floorNumber, List<Room> rooms) {
        this.floorNumber = floorNumber;
        this.rooms = Collections.unmodifiableList(rooms);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("--- Floor %d (%d rooms) ---\n", floorNumber, rooms.size()));
        for (Room room : rooms) {
            sb.append("  - ").append(room.toString()).append("\n");
        }
        return sb.toString();
    }
}

class Hotel {
    private final List<Floor> floors;

    public Hotel(List<Floor> floors) {
        this.floors = Collections.unmodifiableList(floors);
    }

    public void printSummary() {
        System.out.println("Hotel Summary:");
        System.out.println("Total Floors: " + floors.size());
        floors.forEach(System.out::print);
    }
}

// --- PARSER CLASS ---

enum RoomType {
    STANDARD, CONFERENCE, SUITE
}

class HotelFileParser {

    public Hotel parse(String filePath) throws FileNotFoundException, ParseException {
        File hotelDataFile = new File(filePath);
        List<Floor> floors = new ArrayList<>();

        try (Scanner scanner = new Scanner(hotelDataFile)) {
            // Read number of floors
            if (!scanner.hasNextLine()) throw new ParseException("File is empty or missing header line.");
            int numberOfFloors = parseFloorCount(scanner.nextLine());

            for (int i = 0; i < numberOfFloors; i++) {
                if (!scanner.hasNextLine()) throw new ParseException("Unexpected end of file while reading floor data.");
                int numberOfRooms = parseRoomCount(scanner.nextLine());
                List<Room> rooms = new ArrayList<>();
                for (int j = 0; j < numberOfRooms; j++) {
                    if (!scanner.hasNextLine()) throw new ParseException("Unexpected end of file while reading room data.");
                    rooms.add(parseRoom(scanner.nextLine()));
                }
                floors.add(new Floor(i + 1, rooms));
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid number format in file: " + e.getMessage(), e);
        }

        return new Hotel(floors);
    }

    private int parseFloorCount(String line) throws ParseException {
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            throw new ParseException("Invalid floor count line: " + line);
        }
        return Integer.parseInt(parts[1]);
    }

    private int parseRoomCount(String line) throws ParseException {
        String[] parts = line.split(" ");
        if (parts.length < 3) {
            throw new ParseException("Invalid room count line: " + line);
        }
        return Integer.parseInt(parts[2]);
    }

    private Room parseRoom(String line) throws ParseException {
        String[] parts = line.split(", ");
        if (parts.length < 3) {
            throw new ParseException("Invalid room data line: " + line);
        }
        
        RoomType roomType;
        try {
            // Allow matching "Sweet" from original file, but map it to SUITE enum
            String typeString = parts[0].equalsIgnoreCase("Sweet") ? "SUITE" : parts[0].toUpperCase();
            roomType = RoomType.valueOf(typeString);
        } catch (IllegalArgumentException e) {
            throw new ParseException("Unknown room type: " + parts[0]);
        }

        int roomNumber = Integer.parseInt(parts[1]);

        switch (roomType) {
            case STANDARD:
                if (parts.length < 4) throw new ParseException("Missing data for Standard Room: " + line);
                int numberOfBeds = Integer.parseInt(parts[2]);
                double bedPrice = Double.parseDouble(parts[3]);
                return new StandardRoom(roomNumber, numberOfBeds, bedPrice);
            case CONFERENCE:
                if (parts.length < 4) throw new ParseException("Missing data for Conference Room: " + line);
                int numberOfSeats = Integer.parseInt(parts[2]);
                double seatPrice = Double.parseDouble(parts[3]);
                return new ConferenceRoom(roomNumber, numberOfSeats, seatPrice);
            case SUITE:
                double pricePerNight = Double.parseDouble(parts[2]);
                return new SuiteRoom(roomNumber, pricePerNight);
            default:
                throw new ParseException("Unsupported room type: " + roomType);
        }
    }
}

class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}

// --- MAIN APPLICATION CLASS ---

public class HotelApplication {
    private static final String HOTEL_DATA_FILE = "hotel.txt";

    public static void main(String[] args) {
        HotelFileParser parser = new HotelFileParser();
        try {
            Hotel hotel = parser.parse(HOTEL_DATA_FILE);
            System.out.println("Hotel data loaded successfully!\n");
            hotel.printSummary();
        } catch (FileNotFoundException e) {
            System.err.println("Error: The data file '" + HOTEL_DATA_FILE + "' was not found.");
        } catch (ParseException e) {
            System.err.println("Error parsing hotel data file: " + e.getMessage());
        }
    }
}
