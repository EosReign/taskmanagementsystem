package ru.eosreign.taskmanagementsystem.exception;

public class AuthorityNotFoundException extends RuntimeException {
    public AuthorityNotFoundException() {
    }

    public AuthorityNotFoundException(String message) {
        super(message);
    }

    public AuthorityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
