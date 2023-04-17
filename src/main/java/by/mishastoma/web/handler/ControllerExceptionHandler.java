package by.mishastoma.web.handler;

import by.mishastoma.exception.AuthorNotFoundException;
import by.mishastoma.exception.BookNotFoundException;
import by.mishastoma.exception.GenreNotFoundException;
import by.mishastoma.exception.ItemNotFoundException;
import by.mishastoma.exception.NoFreeBooksFoundException;
import by.mishastoma.exception.UniqueIdentifierIsTakenException;
import by.mishastoma.exception.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<?> handleAuthorNotFound() {
        return new ResponseEntity<>("Author not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFound() {
        return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<?> handleGenreNotFound() {
        return new ResponseEntity<>("Genre not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFound() {
        return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound() {
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueIdentifierIsTakenException.class)
    public ResponseEntity<?> handleUniqueIdentifier(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFreeBooksFoundException.class)
    public ResponseEntity<?> handleNoFreeBooks(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidRequest(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getBindingResult().getFieldErrors().stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(java.util.stream.Collectors.joining(", ")), HttpStatus.BAD_REQUEST);
    }
}
