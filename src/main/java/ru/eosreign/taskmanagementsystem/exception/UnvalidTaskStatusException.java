package ru.eosreign.taskmanagementsystem.exception;

public class UnvalidTaskStatusException extends RuntimeException {
    public UnvalidTaskStatusException() {
    }

    public UnvalidTaskStatusException(String message) {
        super(message);
    }

    public UnvalidTaskStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
