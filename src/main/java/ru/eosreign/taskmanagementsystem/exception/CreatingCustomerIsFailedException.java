package ru.eosreign.taskmanagementsystem.exception;

public class CreatingCustomerIsFailedException extends RuntimeException {
    public CreatingCustomerIsFailedException() {
    }

    public CreatingCustomerIsFailedException(String message) {
        super(message);
    }

    public CreatingCustomerIsFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
