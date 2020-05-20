package com.kineticz.videostoremanager.exception;

/**
 * Error thrown if an invalid value is entered when adding or removing movie stock
 */
public class MovieStockException extends Exception {

    /**
     * Creates a standard MovieStockException
     */
    public MovieStockException() {
        super();
    }

    /**
     * Creates a new MovieStockException with a custom error message
     *
     * @param message The error message
     */
    public MovieStockException(String message) {
        super(message);
    }

    /**
     * Creates a MovieStockException with an exception that caused it to be thrown
     *
     * @param cause The original exception
     */
    public MovieStockException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a MovieStockException with a custom error message and the original exception that led to it being thrown
     *
     * @param message The error message
     * @param cause   The original exception
     */
    public MovieStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
