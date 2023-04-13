package by.mishastoma.exception;

public class UniqueIdentifierIsTaken extends RuntimeException {
    public UniqueIdentifierIsTaken(String message) {
        super(message);
    }
}
