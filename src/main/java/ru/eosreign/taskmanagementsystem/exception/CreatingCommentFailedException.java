package ru.eosreign.taskmanagementsystem.exception;

public class CreatingCommentFailedException extends RuntimeException {
    public CreatingCommentFailedException() {
    }

    public CreatingCommentFailedException(String message) {
        super(message);
    }

    public CreatingCommentFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
