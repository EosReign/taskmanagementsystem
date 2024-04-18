package ru.eosreign.taskmanagementsystem.exception;

public class CreatingTaskIsFailedException extends RuntimeException {
    public CreatingTaskIsFailedException() {
    }

    public CreatingTaskIsFailedException(String message) {
        super(message);
    }

    public CreatingTaskIsFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
