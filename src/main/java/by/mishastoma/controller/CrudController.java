package by.mishastoma.controller;

import org.springframework.http.ResponseEntity;

public interface CrudController<T> {
    void insert(String obj);

    void delete(Long id);

    ResponseEntity<T> findById(Long id);

    void update(String obj);
}
