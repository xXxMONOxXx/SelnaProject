package by.mishastoma.web.controller;

import org.springframework.http.ResponseEntity;

public interface CrudController<T> {
    ResponseEntity<?> save(T t);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<T> findById(Long id);

    ResponseEntity<?> update(T t);
}
