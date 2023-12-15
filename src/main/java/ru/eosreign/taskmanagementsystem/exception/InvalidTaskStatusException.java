package ru.eosreign.taskmanagementsystem.exception;

public class InvalidTaskStatusException extends RuntimeException {
    public InvalidTaskStatusException() {
    }

    public InvalidTaskStatusException(String message) {
        super(message);
    }

    public InvalidTaskStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
