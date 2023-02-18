package by.mishastoma.exception;

import java.io.Serializable;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Serializable id) {
        super("Book not found, id: " + id);
    }

    public BookNotFoundException(String isbn) {
        super("Book not found, isbn: " + isbn);
    }
}
