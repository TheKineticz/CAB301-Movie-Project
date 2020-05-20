package com.kineticz.videostoremanager.members;

/**
 * Error thrown if and attempt is made to add to the member collection once it is full
 */
public class MemberCollectionException extends Exception {

    /**
     * Creates a standard MemberCollectionFullException
     */
    public MemberCollectionException() {
        super();
    }

    /**
     * Creates a new MemberCollectionFullException with a custom error message
     *
     * @param message The error message
     */
    public MemberCollectionException(String message) {
        super(message);
    }

    /**
     * Creates a MemberCollectionFullException with an exception that caused it to be thrown
     *
     * @param cause The original exception
     */
    public MemberCollectionException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a MemberCollectionFullException with a custom error message and the original exception that led to it being thrown
     *
     * @param message The error message
     * @param cause   The original exception
     */
    public MemberCollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
