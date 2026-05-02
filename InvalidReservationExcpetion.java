package com.mycompany.hotel.exceptions;

/**
 * Thrown to indicate that a reservation attempt has failed due to invalid
 * conditions, such as overlapping dates or incorrect guest information.
 */
public class InvalidReservationException extends Exception {

    /**
     * Constructs a new InvalidReservationException with {@code null} as its
     * detail message.
     */
    public InvalidReservationException() {
        super();
    }

    /**
     * Constructs a new InvalidReservationException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidReservationException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidReservationException with the specified detail message
     * and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).
     */
    public InvalidReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidReservationException with the specified cause.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).
     */
    public InvalidReservationException(Throwable cause) {
        super(cause);
    }
}
