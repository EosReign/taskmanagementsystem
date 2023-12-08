package ru.eosreign.taskmanagementsystem.exception;

public class EmptyCustomerTableException extends RuntimeException {
    public EmptyCustomerTableException() {
    }

    public EmptyCustomerTableException(String message) {
        super(message);
    }

    public EmptyCustomerTableException(String message, Throwable cause) {
        super(message, cause);
    }
}
