package by.mishastoma.exception;

import java.io.Serializable;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(Serializable id) {
        super("Genre not found, id: " + id);
    }

    public GenreNotFoundException(String name) {
        super("Genre not found, name: " + name);
    }
}
