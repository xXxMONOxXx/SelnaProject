package by.mishastoma.controller;

public interface CrudController {
    void insert(String obj);

    void delete(Long id);

    String findById(Long id);

    void update(String obj);
}
