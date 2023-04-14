package by.mishastoma.exception;

public class UniqueIdentifierIsTakenException extends RuntimeException {
    public UniqueIdentifierIsTakenException(String message) {
        super(message);
    }
}
