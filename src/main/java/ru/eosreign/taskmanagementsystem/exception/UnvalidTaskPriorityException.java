package ru.eosreign.taskmanagementsystem.exception;

public class UnvalidTaskPriorityException extends RuntimeException {
    public UnvalidTaskPriorityException() {
    }

    public UnvalidTaskPriorityException(String message) {
        super(message);
    }

    public UnvalidTaskPriorityException(String message, Throwable cause) {
        super(message, cause);
    }
}
