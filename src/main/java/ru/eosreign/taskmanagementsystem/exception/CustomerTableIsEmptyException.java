package ru.eosreign.taskmanagementsystem.exception;

public class CustomerTableIsEmptyException extends RuntimeException {
    public CustomerTableIsEmptyException(String customerTableIsEmpty) {
    }

    public CustomerTableIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerTableIsEmptyException() {
    }
}
