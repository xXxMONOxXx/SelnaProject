package by.mishastoma.web.handler;

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

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<?> handleAuthorNotFound(){
        return new ResponseEntity<>("Author not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFound(){
        return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<?> handleGenreNotFound(){
        return new ResponseEntity<>("Genre not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFound(){
        return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(){
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }
}
