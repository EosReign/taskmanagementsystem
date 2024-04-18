package ru.eosreign.taskmanagementsystem.exception;

public class TaskTableIsEmptyException extends RuntimeException {
    public TaskTableIsEmptyException() {
    }

    public TaskTableIsEmptyException(String message) {
        super(message);
    }

    public TaskTableIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
