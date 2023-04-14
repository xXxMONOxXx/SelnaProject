package by.mishastoma.exception;

import java.io.Serializable;

public class NoFreeBooksFoundException extends RuntimeException {

    public NoFreeBooksFoundException(Serializable id) {
        super("No free books found for id: " + id);
    }
}
