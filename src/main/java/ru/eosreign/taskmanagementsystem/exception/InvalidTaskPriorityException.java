package ru.eosreign.taskmanagementsystem.exception;

public class InvalidTaskPriorityException extends RuntimeException {
    public InvalidTaskPriorityException() {
    }

    public InvalidTaskPriorityException(String message) {
        super(message);
    }

    public InvalidTaskPriorityException(String message, Throwable cause) {
        super(message, cause);
    }
}
