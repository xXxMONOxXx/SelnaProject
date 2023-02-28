package by.mishastoma.handler;

import by.mishastoma.exception.AuthorNotFoundException;
import by.mishastoma.exception.BookNotFoundException;
import by.mishastoma.exception.GenreNotFoundException;
import by.mishastoma.exception.ItemNotFoundException;
import by.mishastoma.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({AuthorNotFoundException.class, BookNotFoundException.class, GenreNotFoundException.class,
            ItemNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<String> handleControllerException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
