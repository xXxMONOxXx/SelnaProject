package by.mishastoma.exception;

import java.io.Serializable;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Serializable id) {
        super("User not found, id: " + id);
    }

    public UserNotFoundException(String username) {
        super("User not found, username: " + username);
    }
}
