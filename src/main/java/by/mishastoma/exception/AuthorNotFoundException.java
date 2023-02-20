package by.mishastoma.exception;

import java.io.Serializable;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Serializable id) {
        super("Author not found, id: " + id);
    }
}
