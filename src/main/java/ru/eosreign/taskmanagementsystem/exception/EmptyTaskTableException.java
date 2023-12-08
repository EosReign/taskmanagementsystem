package ru.eosreign.taskmanagementsystem.exception;

public class EmptyTaskTableException extends RuntimeException {
    public EmptyTaskTableException() {
    }

    public EmptyTaskTableException(String message) {
        super(message);
    }

    public EmptyTaskTableException(String message, Throwable cause) {
        super(message, cause);
    }
}
