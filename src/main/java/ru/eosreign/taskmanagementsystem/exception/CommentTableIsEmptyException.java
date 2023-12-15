package ru.eosreign.taskmanagementsystem.exception;

public class CommentTableIsEmptyException extends RuntimeException {
    public CommentTableIsEmptyException() {
    }

    public CommentTableIsEmptyException(String message) {
        super(message);
    }

    public CommentTableIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
