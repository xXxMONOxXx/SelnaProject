package by.mishastoma.exception;

import java.io.Serializable;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Serializable id) {
        super("Item not found, id: " + id);
    }
}
